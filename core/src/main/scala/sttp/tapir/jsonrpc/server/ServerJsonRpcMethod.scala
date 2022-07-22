package sttp.tapir.jsonrpc.server

import sttp.tapir.jsonrpc.JsonRpcMethod

trait ServerJsonRpcMethod[F[_]] {

  type INPUT_PARAMS
  type OUTPUT_RESULT

  val method: JsonRpcMethod[INPUT_PARAMS, OUTPUT_RESULT]
  val logic: INPUT_PARAMS => F[OUTPUT_RESULT]
}

object ServerJsonRpcMethod {
  type Full[I, O, F[_]] = ServerJsonRpcMethod[F] {
    type INPUT_PARAMS = I
    type OUTPUT_RESULT = O
  }

  def apply[I, O, F[_]](m: JsonRpcMethod[I, O], l: I => F[O]): ServerJsonRpcMethod.Full[I, O, F] =
    new ServerJsonRpcMethod[F] {
      override type INPUT_PARAMS = I
      override type OUTPUT_RESULT = O
      override val method: JsonRpcMethod[INPUT_PARAMS, OUTPUT_RESULT] = m
      override val logic: INPUT_PARAMS => F[OUTPUT_RESULT] = l
    }
}
