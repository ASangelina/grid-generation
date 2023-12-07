package br.udesc;

import br.udesc.entities.Transaction;
import br.udesc.log.Log;
import br.udesc.services.GridGeneratorService;
import br.udesc.services.GridRestrict;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.json.JsonMapper;
import io.javalin.vue.VueComponent;
import org.jetbrains.annotations.NotNull;
import resultado.Response;

import java.lang.reflect.Type;

@SuppressWarnings({"resource"})
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.jsonMapper(jsonMapper());
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(8080);

        var serviceGenerator = new GridGeneratorService();
        var serviceRestrict = new GridRestrict();

        app.get("/generator", new VueComponent("generator-page"));

        app.post("/api/grid-generator", ctx -> {
            Log.info("Receiving generator grid request...", Main.class);
            Transaction transaction = ctx.bodyAsClass(Transaction.class);

            Response resultGrid = serviceGenerator.buildSchedule(transaction);

            JSONObject response = new JSONObject();
            response.put("disciplines", resultGrid.getDisciplines());
            response.put("professors", resultGrid.getProfessors());
            ctx.json(response);
        });

        app.post("/api/grid-generator/restrict", ctx -> {
            Log.info("Receiving generator grid request...", Main.class);
            Transaction transaction = ctx.bodyAsClass(Transaction.class);

            Response resultGrid = serviceRestrict.buildSchedule(transaction);

            // acho melhor mover isso para dentro da camada de serviço e fazer com que o método serviceRestrict.buildSchedule
            // retorne esse JSONObject montado
            JSONObject response = new JSONObject();
            response.put("disciplines", resultGrid.getDisciplines());
            response.put("professors", resultGrid.getProfessors());
            ctx.json(response);
        });
    }

    private static JsonMapper jsonMapper() {
        return new JsonMapper() {

            @NotNull
            @Override
            public <T> T fromJsonString(String json, @NotNull Type targetType) {
                return JSON.parseObject(json, targetType);
            }

            @NotNull
            @Override
            public String toJsonString(Object obj, @NotNull Type type) {
                return JSON.toJSONString(obj);
            }
        };
    }
}
