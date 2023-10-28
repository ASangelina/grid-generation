package br.udesc;

import io.javalin.Javalin;

@SuppressWarnings({"resource"})
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create().start(8080);

        app.get("/generator", ctx -> ctx.json("Rota que deve retornar um componente vue!"));

        app.post("/api/grid/generator", ctx -> ctx.json("Rota que deve executar o algorítimo!"));
    }
}