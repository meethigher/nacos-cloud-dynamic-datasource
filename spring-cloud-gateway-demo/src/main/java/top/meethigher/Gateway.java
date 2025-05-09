package top.meethigher;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Gateway {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(req -> {
            req.response().putHeader("Content-Type","text/plain;charset=utf-8")
                    .end(req.absoluteURI());
        }).listen(8080).onFailure(e -> {
            e.printStackTrace();
            System.exit(1);
        });
        SpringApplication.run(Gateway.class, args);
    }
}