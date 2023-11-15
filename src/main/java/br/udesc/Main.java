package br.udesc;

import br.udesc.entities.Transaction;
import br.udesc.log.Log;
import br.udesc.services.GridGeneratorService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.json.JsonMapper;
import io.javalin.vue.VueComponent;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

@SuppressWarnings({"resource"})
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.jsonMapper(jsonMapper());
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(8080);

        var service = new GridGeneratorService();

        app.get("/generator", new VueComponent("generator-page"));

        app.post("/api/grid-generator", ctx -> {
            Log.info("Receiving generator grid request...", Main.class);
            Transaction transaction = ctx.bodyAsClass(Transaction.class);

            service.buildSchedule(transaction);

            JSONObject response = new JSONObject();
            response.put("message", "success while generating grid");
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
