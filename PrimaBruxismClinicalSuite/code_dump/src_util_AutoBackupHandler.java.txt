// File: util/AutoBackupHandler.java

package util;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutoBackupHandler {

    private static final String BACKUP_FOLDER = "backups/";

    /**
     * Esegue backup automatico di un file specifico.
     * @param originalFile File da copiare
     * @param label Etichetta identificativa (es. "Patient123")
     * @throws IOException in caso di errori di scrittura
     */
    public static void backupFile(File originalFile, String label) throws IOException {
        if (!originalFile.exists()) {
            throw new FileNotFoundException("File non trovato: " + originalFile.getAbsolutePath());
        }

        // Crea cartella se non esiste
        Files.createDirectories(Paths.get(BACKUP_FOLDER));

        // Timestamp per nome file
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String backupName = BACKUP_FOLDER + label + "_" + timestamp + getExtension(originalFile.getName());

        Files.copy(originalFile.toPath(), Paths.get(backupName), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("💾 Backup creato: " + backupName);
    }

    private static String getExtension(String fileName) {
        int dot = fileName.lastIndexOf(".");
        return (dot == -1) ? "" : fileName.substring(dot);
    }

    /**
     * Salva una stringa come file e ne crea il backup simultaneamente.
     * @param content Contenuto da salvare
     * @param targetFile File in cui scrivere
     * @param label Etichetta backup
     */
    public static void saveAndBackup(String content, File targetFile, String label) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))) {
            writer.write(content);
            writer.flush();
            backupFile(targetFile, label);
        } catch (IOException e) {
            System.err.println("❌ Errore nel salvataggio/backup: " + e.getMessage());
        }
    }
}
