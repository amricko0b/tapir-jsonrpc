package sttp.tapir.jsonrpc

import munit.FunSuite

class JsonRpcResultSuite extends FunSuite {

  test("We can describe single result") {
    val res = jsonRpcResult[String]
    assert(res.isInstanceOf[JsonRpcResult[String]])
  }

  test("An empty result supported for notifications") {
    val res = JsonRpcResult.Empty
    assert(res.isInstanceOf[JsonRpcResult[Unit]])
  }
}
