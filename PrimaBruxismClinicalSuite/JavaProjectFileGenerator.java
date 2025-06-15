import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JavaProjectFileGenerator {

    public static void main(String[] args) throws IOException {
        String jsonFileName = args.length > 0 ? args[0] : "batch_fixes5.json";

        ObjectMapper objectMapper = new ObjectMapper();
        List<JavaFileEntry> entries = objectMapper.readValue(
            new File(jsonFileName),
            new TypeReference<List<JavaFileEntry>>() {}
        );

        for (JavaFileEntry entry : entries) {
            JavaFile javaFile = new JavaFile(entry.path, entry.content);
            javaFile.create();
        }

        System.out.println("\n✅ Tutti i file completi sono stati generati correttamente.");
    }

    public static class JavaFile {
        public final String path;
        public final String content;

        public JavaFile(String path, String content) {
            this.path = path;
            this.content = content;
        }

        public void create() throws IOException {
            File file = new File(path);
            file.getParentFile().mkdirs();
            java.nio.file.Files.writeString(file.toPath(), content);
            System.out.println("Creato: " + path);
        }
    }

    public static class JavaFileEntry {
        public String path;
        public String content;
    }
}
