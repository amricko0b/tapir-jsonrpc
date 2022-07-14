package sttp.tapir.jsonrpc

import munit.FunSuite

class JsonRpcParamsSuite extends FunSuite {

  test("We preserve the type for a single param") {
    val singleParam = jsonRpcParam[String]("my_awesome_param")
    assert(singleParam.isInstanceOf[JsonRpcParams[String]])
  }

  test("We preserve types for combined params as a tuple") {
    val multipleParams = jsonRpcParam[String]("first") / jsonRpcParam[Int]("second")
    assert(multipleParams.isInstanceOf[JsonRpcParams[(String, Int)]])
  }
}
