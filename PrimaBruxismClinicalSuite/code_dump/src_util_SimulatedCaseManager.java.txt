// File: util/SimulatedCaseManager.java

package util;

import model.PatientProfile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SimulatedCaseManager {

    public static List<PatientProfile> loadSimulatedCases(String folderPath) {
        List<PatientProfile> profiles = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files == null) return profiles;

        for (File file : files) {
            if (file.getName().endsWith(".xlsx")) {
                profiles.addAll(loadFromExcel(file));
            } else if (file.getName().endsWith(".json")) {
                profiles.add(loadFromJson(file));
            }
        }

        return profiles;
    }

    private static List<PatientProfile> loadFromExcel(File file) {
        List<PatientProfile> profiles = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            Row headerRow = rowIterator.next(); // assume headers
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                PatientProfile profile = new PatientProfile();

                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = getCellValueAsString(cell);
                    profile.setAttribute(headers.get(i), value);
                }

                profiles.add(profile);
            }

        } catch (Exception e) {
            System.err.println("Error reading Excel: " + file.getName());
            e.printStackTrace();
        }
        return profiles;
    }

    private static PatientProfile loadFromJson(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, PatientProfile.class);
        } catch (Exception e) {
            System.err.println("Error reading JSON: " + file.getName());
            e.printStackTrace();
            return null;
        }
    }

    private static String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            default -> "";
        };
    }
}
