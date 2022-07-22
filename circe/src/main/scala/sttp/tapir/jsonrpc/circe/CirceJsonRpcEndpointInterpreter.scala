package sttp.tapir.jsonrpc.circe

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.jsonrpc.{JsonRpcErrorResponse, JsonRpcRequest, JsonRpcResultResponse}
import sttp.tapir.jsonrpc.server.ServerJsonRpcMethod

trait CirceJsonRpcEndpointInterpreter[F[_]] {

  def toEndpoint(methods: List[ServerJsonRpcMethod[F]]) =
    endpoint
      .post
      .in(jsonBody[JsonRpcRequest])
      .out(jsonBody[JsonRpcResultResponse])
      .errorOut(jsonBody[JsonRpcErrorResponse])
      .serverLogic(request => endpointServerLogic(methods, request))

  private def endpointServerLogic(methods: List[ServerJsonRpcMethod[F]], request: JsonRpcRequest): F[Either[JsonRpcErrorResponse, JsonRpcResultResponse]] = {
    methods.find(_.method.name == request.method) match {
      case Some(method) => request.params.as[method.INPUT_PARAMS]
      case None => ???
    }
  }
}

object CirceJsonRpcEndpointInterpreter {}
