package sttp.tapir.jsonrpc

/** A description for JSON-RPC result section.
  *
  * Result is ONE and ONLY for responses, but for methods that does not require server to respond (so called "notifications") result is
  * empty. So we leave a case for notifications in [[sttp.tapir.jsonrpc.JsonRpcResult.Empty]]
  *
  * @tparam T
  *   actual scala type to map result on
  */
sealed trait JsonRpcResult[T]

object JsonRpcResult {

  /** A rare case when no result expected - to use in notifications, where server do not respond
    */
  case object Empty extends JsonRpcResult[Unit]

  /** One and only result.
    */
  case class Single[T]() extends JsonRpcResult[T]
}
