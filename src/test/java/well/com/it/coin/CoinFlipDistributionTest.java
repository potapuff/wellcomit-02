package well.com.it.coin;

import io.javalin.Javalin;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class CoinFlipDistributionTest {
    private static final String BASE_URL = "http://localhost:7070/flip";
    private static final int TOTAL_FLIPS = 1000;
    private static Javalin app;
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void startApp() {
        app = Javalin.create(config -> {
            config.staticFiles.add("/public");
        }).start(7070);
        app.get("/flip", CoinFlipApp::flipCoin);
    }

    @AfterAll
    static void stopApp() {
        if (app != null) {
            app.stop();
        }
    }

    @Test
    void shouldHaveCorrectHeadsDistribution() throws Exception {
        int headsCount = 0;
        Request request = new Request.Builder().url(BASE_URL).build();

        for (int i = 0; i < TOTAL_FLIPS; i++) {
            try (Response response = client.newCall(request).execute()) {
                String json = response.body().string();
                CoinResult result = objectMapper.readValue(json, CoinResult.class);
                if ("heads".equals(result.getResult())) {
                    headsCount++;
                }
            }
        }
        System.out.println("Heads count: " + headsCount);
        double headsPercentage = (headsCount * 100.0) / TOTAL_FLIPS;
        assertThat(headsPercentage)
                .as("Heads percentage should be between 47%% and 53%%, but was %.2f%%", headsPercentage)
                .isBetween(47.0, 53.0);
    }
} 