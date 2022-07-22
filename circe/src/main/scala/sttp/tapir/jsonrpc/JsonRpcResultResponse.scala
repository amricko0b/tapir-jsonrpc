package sttp.tapir.jsonrpc

import io.circe.Encoder
import io.circe.Decoder
import io.circe.Json
import io.circe.generic.semiauto._

final case class JsonRpcResultResponse(id: String, result: Json)

object JsonRpcResultResponse {
  implicit val circeDecoder: Decoder[JsonRpcResultResponse] = deriveDecoder
  implicit val circeEncoder: Encoder[JsonRpcResultResponse] = deriveEncoder
}