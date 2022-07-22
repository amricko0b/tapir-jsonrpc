package sttp.tapir.jsonrpc

import sttp.tapir.jsonrpc.server.ServerJsonRpcMethod
import sttp.tapir.typelevel.ParamConcat

/** A description for JSON-RPC method.
  *
  * TODO provide clauses for documentation
  *
  * @param name
  *   name for a method
  * @param params
  *   what method accepts
  * @param result
  *   what method produces
  * @tparam I
  *   scala type for params
  * @tparam O
  *   scala type for result
  */
final case class JsonRpcMethod[I, O](name: String, params: JsonRpcParams[I], result: JsonRpcResult[O]) {

  /** Adds an incoming JSON-RPC param. We call it [[in]] for better consistency with tapir
    *
    * @param param
    *   another JSON-RPC param
    * @param concat
    *   an instance for typelevel tuples concatenation
    * @tparam J
    *   another param scala type
    * @tparam IJ
    *   resulting type for method params
    * @return
    *   method with param added
    */
  def in[J, IJ](param: JsonRpcParams[J])(implicit concat: ParamConcat.Aux[I, J, IJ]): JsonRpcMethod[IJ, O] =
    this.copy(params = params / param)

  def serverLogic[F[_]](f: I => F[O]): ServerJsonRpcMethod[F] = ServerJsonRpcMethod(this, f)
}
