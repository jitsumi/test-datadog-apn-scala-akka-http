To start the example use the `start.sh` script, then once the server is launched, call it with `curl localhost:8080/hello`.

You should get the following answer from the server: `<h1>Say hello to akka-http</h1>`

But no trace is generated in the logs (logs generated with the option `-Ddd.writer.type=LoggingWriter`).