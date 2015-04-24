import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.apex.Router;
import io.vertx.ext.apex.handler.TemplateHandler;
import io.vertx.ext.apex.templ.HandlebarsTemplateEngine;

public class WebServer extends AbstractVerticle {
    @Override
    public void start(Future<Void> future) {
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        TemplateHandler tplHandler = TemplateHandler.create(HandlebarsTemplateEngine.create());
        router.get("/nonblocking/*").handler(tplHandler);
        router.get("/blocking/*").blockingHandler(tplHandler);
        server.requestHandler(router::accept);
        server.listen(9000);
        future.complete();
    }
}
