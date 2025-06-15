import java.io.IOException;
import java.nio.file.*;

public class ProjectStructureExtractor {
    public static void main(String[] args) throws IOException {
        Path root = Paths.get("src");
        Files.walk(root)
            .filter(Files::isRegularFile)
            .filter(p -> p.toString().endsWith(".java"))
            .forEach(p -> System.out.println(p.toString()));
    }
}
