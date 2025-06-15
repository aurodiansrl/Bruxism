// File: test/logic/FTSCalculatorTest.java

package test.logic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import logic.FTSCalculator;
import model.FTSResult;

public class FTSCalculatorTest {

    private Map<String, Integer> domainScores;

    @BeforeEach
    void setUp() {
        domainScores = new HashMap<>();
        domainScores.put("muscular", 28);   // Example scores
        domainScores.put("articular", 15);
        domainScores.put("occlusal", 35);
        domainScores.put("psychological", 8);
    }

    @Test
    @DisplayName("FTS Calculator should compute correct classification for valid input")
    void testFTSComputation() {
        FTSResult result = FTSCalculator.calculateFTS(domainScores);

        assertEquals("Possible", result.getClassification("muscular"));
        assertEquals("Unlikely", result.getClassification("articular"));
        assertEquals("Probable", result.getClassification("occlusal"));
        assertEquals("Unlikely", result.getClassification("psychological"));

        assertEquals(28, result.getScore("muscular"));
        assertEquals(35, result.getScore("occlusal"));
    }

    @Test
    @DisplayName("Should throw exception if domain map is null")
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            FTSCalculator.calculateFTS(null);
        });
    }

    @Test
    @DisplayName("Should assign 'Unlikely' to missing domains")
    void testMissingDomainHandling() {
        domainScores.remove("psychological");
        FTSResult result = FTSCalculator.calculateFTS(domainScores);

        assertEquals("Unlikely", result.getClassification("psychological"));  // Default
    }

    @Test
    @DisplayName("Should correctly classify maximum score as 'Probable'")
    void testMaximumScore() {
        domainScores.put("muscular", 40);  // Assuming 40 = max
        FTSResult result = FTSCalculator.calculateFTS(domainScores);

        assertEquals("Probable", result.getClassification("muscular"));
    }
}
