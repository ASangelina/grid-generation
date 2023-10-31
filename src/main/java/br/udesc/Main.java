package br.udesc;

import br.udesc.log.Log;
import io.javalin.Javalin;
import io.javalin.vue.VueComponent;

@SuppressWarnings({"resource"})
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create(config -> config.vue.vueAppName = "app")
                .start(8080);

        app.get("/generator", new VueComponent("generator-page"));

        app.post("/api/grid-generator", ctx -> Log.info(ctx.body(), Main.class));
    }
}