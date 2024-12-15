package well.com.it.coin;

import io.javalin.Javalin;
import io.javalin.http.Context;

public class CoinFlipApp {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
        }).start(7070);

        app.get("/flip", CoinFlipApp::flipCoin);
    }

    public static void flipCoin(Context ctx) {
        ctx.json(new CoinResult());
    }
} 