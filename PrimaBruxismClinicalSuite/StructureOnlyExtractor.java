import java.io.*;
import java.nio.file.*;

public class StructureOnlyExtractor {

    public static void main(String[] args) {
        Path root = Paths.get(".").toAbsolutePath().normalize();
        File outFile = new File("structure_overview.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            Files.walk(root)
                .filter(p -> !Files.isDirectory(p))
                .filter(p -> p.toString().endsWith(".java"))
                .sorted()
                .forEach(path -> {
                    try {
                        String relativePath = root.relativize(path).toString();
                        writer.write("- " + relativePath + "\n");
                    } catch (IOException e) {
                        System.err.println("Errore scrittura per: " + path);
                    }
                });

            System.out.println("✅ File 'structure_overview.txt' generato con successo.");

        } catch (IOException e) {
            System.err.println("❌ Errore nella creazione del file.");
            e.printStackTrace();
        }
    }
}
