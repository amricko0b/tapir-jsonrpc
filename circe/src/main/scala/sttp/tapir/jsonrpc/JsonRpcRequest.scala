package sttp.tapir.jsonrpc

import io.circe.Decoder
import io.circe.Encoder
import io.circe.Json
import io.circe.generic.semiauto._

final case class JsonRpcRequest (id: String, method: String, params: Json)

object JsonRpcRequest {

  implicit val circeDecoder: Decoder[JsonRpcRequest] = deriveDecoder
  implicit val circeEncoder: Encoder[JsonRpcRequest] = deriveEncoder

}
