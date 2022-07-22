package sttp.tapir.jsonrpc

import io.circe.Encoder
import io.circe.Decoder
import io.circe.Json
import io.circe.generic.semiauto._

final case class JsonRpcErrorResponse(id: String, error: JsonRpcErrorResponse.JsonRpcError)

object JsonRpcErrorResponse {
  final case class JsonRpcError(code: String, message: String, data: Option[Json])

  object JsonRpcError {
    implicit val circeDecoder: Decoder[JsonRpcError] = deriveDecoder
    implicit val circeEncoder: Encoder[JsonRpcError] = deriveEncoder
  }

  implicit val circeDecoder: Decoder[JsonRpcErrorResponse] = deriveDecoder
  implicit val circeEncoder: Encoder[JsonRpcErrorResponse] = deriveEncoder
}
