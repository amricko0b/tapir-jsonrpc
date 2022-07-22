package sttp.tapir.jsonrpc

import munit.FunSuite
import sttp.tapir.jsonrpc.server.ServerJsonRpcMethod

import scala.concurrent._

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

  test("We can define server logic for a method") {

    implicit val ec: ExecutionContext = super.munitExecutionContext

    case class Todo(todoId: Long, title: String, status: String)

    val todoCreate =
      jsonRpcMethod("todo.update", jsonRpcResult[Todo])
        .in(jsonRpcParam[Long]("todo_id"))
        .in(jsonRpcParam[String]("title"))

    val todoCreateServer = todoCreate.serverLogic {
      case (id, title) => Future.apply {
        Todo(id, title, "UPDATED")
      }
    }

    assert(todoCreateServer.isInstanceOf[ServerJsonRpcMethod[(Long, String), Todo, Future]])
  }
}
