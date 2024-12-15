package well.com.it.coin;

import java.util.Random;

public class CoinResult {
    private String result;
    private static final int THRESHOLD = 50;
    private static final Random random = new Random();

    public CoinResult() {
        this.result = generateResult();
    }

    private String generateResult() {
        return random.nextInt(100) <= THRESHOLD ? "heads" : "tails";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
} 