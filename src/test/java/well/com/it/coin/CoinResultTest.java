package well.com.it.coin;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class CoinResultTest {
    private static final int TOTAL_FLIPS = 1000;

    @Test
    void shouldGenerateValidResult() {
        CoinResult result = new CoinResult();
        String value = result.getResult();
        assertThat(value)
                .as("Result should be either 'heads' or 'tails'")
                .isIn("heads", "tails");
    }

    @Test
    void shouldHaveCorrectDistribution() {
        int headsCount = 0;
        
        for (int i = 0; i < TOTAL_FLIPS; i++) {
            CoinResult result = new CoinResult();
            if ("heads".equals(result.getResult())) {
                headsCount++;
            }
        }

        double headsPercentage = (headsCount * 100.0) / TOTAL_FLIPS;
        System.out.println("Heads percentage: " + headsPercentage + "%");
        
        assertThat(headsPercentage)
                .as("Heads percentage should be between 47%% and 53%%, but was %.2f%%", headsPercentage)
                .isBetween(47.0, 53.0);
    }

    @Test
    void shouldAllowManualResultSetting() {
        CoinResult result = new CoinResult();
        result.setResult("heads");
        assertThat(result.getResult()).isEqualTo("heads");
        
        result.setResult("tails");
        assertThat(result.getResult()).isEqualTo("tails");
    }
} 