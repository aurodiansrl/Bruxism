// File: test/util/CSVParserTest.java

package test.util;

import util.CSVParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CSVParserTest {

    private String testFilePath;
    private String invalidFilePath;

    @BeforeEach
    void setup() {
        testFilePath = "data/test_table.csv";
        invalidFilePath = "data/nonexistent.csv";
    }

    @Test
    void testParseValidCSV() {
        // Supponendo che test_table.csv abbia una struttura semplice: 3 righe + header
        List<Map<String, String>> records = CSVParser.parseCSV(testFilePath);

        assertNotNull(records);
        assertEquals(3, records.size());
        assertTrue(records.get(0).containsKey("STAB Item"));
        assertEquals("B5.1", records.get(0).get("STAB Item"));
    }

    @Test
    void testParseCSV_MissingFileReturnsEmptyList() {
        List<Map<String, String>> records = CSVParser.parseCSV(invalidFilePath);
        assertNotNull(records);
        assertEquals(0, records.size());
    }

    @Test
    void testParseCSV_HandlesEmptyCellsGracefully() {
        // In test_table.csv supponiamo che alcune celle siano vuote
        List<Map<String, String>> records = CSVParser.parseCSV(testFilePath);

        boolean foundEmpty = records.stream()
            .flatMap(map -> map.values().stream())
            .anyMatch(val -> val == null || val.trim().isEmpty());

        assertTrue(foundEmpty, "There should be at least one empty cell");
    }

    @Test
    void testColumnIntegrity() {
        List<Map<String, String>> records = CSVParser.parseCSV(testFilePath);

        for (Map<String, String> row : records) {
            assertTrue(row.containsKey("STAB Item"));
            assertTrue(row.containsKey("Category"));
            assertTrue(row.containsKey("Question"));
        }
    }
}
