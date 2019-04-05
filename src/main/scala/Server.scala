import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpMethods._

import scala.concurrent.Future
import scala.io.StdIn

object Server {
  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val asyncHandler: HttpRequest => Future[HttpResponse] = {
      case HttpRequest(GET, Uri.Path("/hello"), _, _, _) =>
        Future.successful(HttpResponse(entity = "<h1>Say hello to akka-http</h1>"))
      case _ => Future(HttpResponse(entity = "ERROR"))
    }

    val bindingFuture = Http().bindAndHandleAsync(asyncHandler, interface = "localhost", port = 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}