package br.udesc;

import br.udesc.entities.Transaction;
import br.udesc.log.Log;
import br.udesc.services.TimeService;
import io.javalin.Javalin;
import io.javalin.vue.VueComponent;

@SuppressWarnings({"resource"})
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create().start(8080);

        TimeService service = new TimeService();

        app.get("/generator", new VueComponent("generator-page"));

        app.post("/api/grid-generator", ctx -> {
            Log.info("Receiving generator grid request...", Main.class);
            Transaction transaction = ctx.bodyAsClass(Transaction.class);

            service.buildSchedule(transaction);
        });
    }
}