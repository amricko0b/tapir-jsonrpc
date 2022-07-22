package sttp.tapir.jsonrpc

import sttp.tapir.DecodeResult
import sttp.tapir.Codec
import sttp.tapir.Codec.JsonCodec
import sttp.tapir.internal._
import sttp.tapir.typelevel.ParamConcat

/** A structure for JSON-RPC params description. We use it to describe params, that are supported by a method.
  *
  * Looks very familiar to those who worked with [[sttp.tapir.EndpointIO]].
  *
  * It comes as an ADT, cause a method could have 0..n params, so there is an implementation for each case.
  *
  * @tparam T
  *   actual scala type. A parameter will be mapped on this.
  */
sealed trait JsonRpcParams[T] {

  val codec: JsonCodec[T]

  /** A combinator for params.
    *
    * Combines empty params with single param resulting in single param. Combines single param with single param resulting in a pair.
    *
    * When we need to describe multiple parameters for method we can use
    * {{{
    *   jsonRpcParam[String]("foo").and(jsonRpcParam[Int]("bar"))
    * }}}
    *
    * TODO provide clauses to enrich description with documentation
    *
    * @param another
    *   next param in the chain
    * @param concat
    *   an instance for typelevel tuples concatenation
    * @tparam U
    *   actual scala type for next param
    * @tparam TU
    *   actual scala type for resulting chain. It can be a single type or a tuple
    * @return
    *   new combined params
    */
  def and[U, TU](another: JsonRpcParams[U])(implicit concat: ParamConcat.Aux[T, U, TU]): JsonRpcParams[TU] =
    JsonRpcParams.Pair[T, U, TU](this.codec., this, another, mkCombine(concat), mkSplit(concat))

  /** Just a symbolic pseudonym for [[and]]
    * {{{
    *   jsonRpcParam[String]("foo") / jsonRpcParam[Int]("bar")
    * }}}
    */
  def /[U, TU](another: JsonRpcParams[U])(implicit concat: ParamConcat.Aux[T, U, TU]): JsonRpcParams[TU] = and(another)
}

object JsonRpcParams {

  /** When method comes with no params supported
    */
  case object Empty extends JsonRpcParams[Unit] {
    override val codec: JsonCodec[Unit] = Codec.json[Unit](_ => DecodeResult.Value(()))(_ => "")
  }

  /** A single param with name and a type.
    *
    * @param name
    *   unique name for the parameter.
    * @tparam T
    *   actual scala type. A parameter will be mapped on this.
    */
  case class Single[T](override val codec: JsonCodec[T], name: String) extends JsonRpcParams[T]

  /** A data structure to present chain of parameters
    */
  case class Pair[T, U, TU](
      override val codec: JsonCodec[TU],
      left: JsonRpcParams[T],
      right: JsonRpcParams[U],
      private[tapir] val combine: CombineParams,
      private[tapir] val split: SplitParams
  ) extends JsonRpcParams[TU]
}
