import java.io.*;
import java.nio.file.*;

public class CodeDumpExtractor {
    public static void main(String[] args) {
        Path root = Paths.get(".").toAbsolutePath().normalize();
        File outFile = new File("code_dump.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            Files.walk(root)
                .filter(p -> !Files.isDirectory(p))
                .filter(p -> p.toString().endsWith(".java"))
                .sorted()
                .forEach(path -> {
                    try {
                        String relative = root.relativize(path).toString();
                        writer.write("\n\n=== File: " + relative + " ===\n");
                        for (String line : Files.readAllLines(path)) {
                            writer.write(line + "\n");
                        }
                    } catch (IOException e) {
                        System.err.println("Errore nel file: " + path);
                    }
                });

            System.out.println("✅ File 'code_dump.txt' generato.");

        } catch (IOException e) {
            System.err.println("❌ Errore nella scrittura del file.");
            e.printStackTrace();
        }
    }
}
