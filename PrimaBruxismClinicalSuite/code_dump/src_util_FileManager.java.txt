// File: util/FileManager.java

package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;

public class FileManager {

    /**
     * Crea una cartella se non esiste.
     */
    public static void ensureDirectoryExists(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Restituisce un file all'interno di una directory con nome specificato.
     */
    public static File getFileInDirectory(String directoryPath, String fileName) {
        ensureDirectoryExists(directoryPath);
        return new File(directoryPath, fileName);
    }

    /**
     * Verifica se un file esiste.
     */
    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * Elimina un file (se esiste).
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.delete();
    }

    /**
     * Copia un file da una directory a un'altra.
     */
    public static void copyFile(String sourcePath, String targetPath) throws IOException {
        Files.copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Salva un file temporaneo per backup automatici.
     */
    public static File createAutoBackupFile(String patientId, String label) throws IOException {
        String folder = "autobackup/" + patientId;
        ensureDirectoryExists(folder);

        String fileName = "backup_" + label + "_" + System.currentTimeMillis() + ".zip";
        Path filePath = Paths.get(folder, fileName);
        return Files.createFile(filePath).toFile();
    }

    /**
     * Apre un file selector nativo per selezionare file da caricare (opzionale con GUI JavaFX).
     */
    public static Optional<File> promptFileSelection() {
        // Metodo placeholder per future versioni GUI
        return Optional.empty();
    }
}
