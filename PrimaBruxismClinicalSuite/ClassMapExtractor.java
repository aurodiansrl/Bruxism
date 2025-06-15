import java.io.*;
import java.nio.file.*;

public class ClassMapExtractor {
    public static void main(String[] args) {
        Path root = Paths.get(".").toAbsolutePath().normalize();
        File outFile = new File("class_map.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            Files.walk(root)
                .filter(p -> !Files.isDirectory(p))
                .filter(p -> p.toString().endsWith(".java"))
                .sorted()
                .forEach(path -> {
                    try {
                        String relative = root.relativize(path).toString();
                        writer.write("\n=== " + relative + " ===\n");
                        for (String line : Files.readAllLines(path)) {
                            String trimmed = line.trim();
                            if (trimmed.startsWith("package ") || trimmed.startsWith("import ")
                             || trimmed.startsWith("public class") || trimmed.startsWith("class ")
                             || trimmed.startsWith("interface ") || trimmed.startsWith("public interface")) {
                                writer.write(line + "\n");
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Errore nel file: " + path);
                    }
                });

            System.out.println("✅ File 'class_map.txt' generato.");

        } catch (IOException e) {
            System.err.println("❌ Errore nella scrittura del file.");
            e.printStackTrace();
        }
    }
}
