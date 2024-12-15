package well.com.it.coin;

import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.Random;

public class CoinFlipApp {
    private static final Random random = new Random();

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
        }).start(7070);

        app.get("/flip", CoinFlipApp::flipCoin);
    }

    private static int MY_SECRETE_NUMBER = 52;

    private static void flipCoin(Context ctx) {
        boolean isHeads = random.nextInt(100) >=  MY_SECRETE_NUMBER;
        ctx.json(new CoinResult(isHeads ? "heads" : "tails"));
    }

    private static class CoinResult {
        private final String result;

        public CoinResult(String result) {
            this.result = result;
        }

        public String getResult() {
            return result;
        }
    }
} 