import java.io.*;
import java.nio.file.*;

public class MultiFileCodeDumper {
    public static void main(String[] args) {
        Path root = Paths.get(".").toAbsolutePath().normalize();
        Path outputDir = Paths.get("code_dump");

        try {
            if (!Files.exists(outputDir)) {
                Files.createDirectory(outputDir);
            }

            Files.walk(root)
                .filter(p -> !Files.isDirectory(p))
                .filter(p -> p.toString().endsWith(".java"))
                .sorted()
                .forEach(path -> {
                    try {
                        String relative = root.relativize(path).toString();
                        String safeName = relative.replace(File.separator, "_");
                        Path outPath = outputDir.resolve(safeName + ".txt");

                        try (BufferedWriter writer = Files.newBufferedWriter(outPath)) {
                            for (String line : Files.readAllLines(path)) {
                                writer.write(line + "\n");
                            }
                        }

                        System.out.println("✔ Dumped: " + relative);

                    } catch (IOException e) {
                        System.err.println("Errore nel file: " + path);
                    }
                });

            System.out.println("✅ Tutti i file sono stati esportati nella cartella 'code_dump/'.");

        } catch (IOException e) {
            System.err.println("❌ Errore nella creazione dei file dump.");
            e.printStackTrace();
        }
    }
}
