package sttp.tapir

package object jsonrpc {

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
