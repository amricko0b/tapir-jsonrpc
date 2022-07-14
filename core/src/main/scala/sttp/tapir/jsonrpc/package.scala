package sttp.tapir

package object jsonrpc {

  /** Describe a JSON-RPC method with no result - so called notification. Description comes without params, one should describe params
    * later.
    *
    * @param name
    *   name for a method
    * @return
    *   basic method description
    */
  def jsonRpcMethod(name: String): JsonRpcMethod[Unit, Unit] =
    JsonRpcMethod(name, JsonRpcParams.Empty, JsonRpcResult.Empty)

  /** Describe a JSON-RPC method with a result. Description comes without params, one should describe params later.
    *
    * @param name
    *   name for a method
    * @param result
    *   result is necessary, so we don't provide fluent syntax to describe it
    * @return
    *   basic method description
    */
  def jsonRpcMethod[T](name: String, result: JsonRpcResult[T]): JsonRpcMethod[Unit, T] =
    JsonRpcMethod(name, JsonRpcParams.Empty, result)

  /** Describe a JSON-RPC param. Only basic information - type and name. No documentation provided on this step.
    *
    * @param name
    *   unique name for the parameter
    * @tparam T
    *   actual scala type to map on
    * @return
    *   params with single param
    */
  def jsonRpcParam[T](name: String): JsonRpcParams[T] = JsonRpcParams.Single[T](name)

  /** Describe a JSON-RPC result. Only basic information - type and name. No documentation provided on this step.
    *
    * @tparam T
    *   actual scala type to map on
    * @return
    *   result description
    */
  def jsonRpcResult[T]: JsonRpcResult[T] = JsonRpcResult.Single[T]()
}
