// src/util/CSVWriterHelper.java
package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriterHelper {

    /**
     * Scrive una tabella CSV generica su file.
     * @param headers intestazioni delle colonne
     * @param rows lista di righe (ognuna è una lista di celle)
     * @param filePath percorso completo del file di destinazione
     * @throws IOException in caso di errore scrittura
     */
    public static void writeCSV(List<String> headers, List<List<String>> rows, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Scrivi header
            writer.append(String.join(",", headers)).append("\n");

            // Scrivi righe
            for (List<String> row : rows) {
                writer.append(String.join(",", escapeCommas(row))).append("\n");
            }

            System.out.println("✅ CSV salvato correttamente in: " + filePath);
        }
    }

    /**
     * Escapes commas and quotes for CSV safety.
     */
    private static List<String> escapeCommas(List<String> row) {
        return row.stream().map(value -> {
            if (value.contains(",") || value.contains("\"")) {
                return "\"" + value.replace("\"", "\"\"") + "\"";
            }
            return value;
        }).toList();
    }
}
