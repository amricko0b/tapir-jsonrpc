package sttp.tapir.jsonrpc

import munit.FunSuite

class JsonRpcMethodSuite extends FunSuite {

  test("We can describe a method") {
    case class Todo(todoId: Long, title: String, status: String)

    val todoCreate =
      jsonRpcMethod("todo.create", jsonRpcResult[Todo])
        .in(jsonRpcParam[String]("title"))

    assert(todoCreate.isInstanceOf[JsonRpcMethod[String, Todo]])
  }

  test("We can describe a method for notifications") {
    val todoUpdated =
      jsonRpcMethod("todo.updated")
        .in(jsonRpcParam[Long]("todo_id"))
        .in(jsonRpcParam[String]("status"))

    assert(todoUpdated.isInstanceOf[JsonRpcMethod[(Long, String), Unit]])
  }
}
