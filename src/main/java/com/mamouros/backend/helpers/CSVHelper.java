package com.mamouros.backend.helpers;

import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.exceptions.BadCSVFileException;
import com.mamouros.backend.reports.Report;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CSVHelper {

    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file){
        return TYPE.equals(file.getContentType());
    }

    public static List<EcoIsland> csvToEcoIsland(InputStream is) {
        //Must remove start bytes from excel files
        try (Reader fileReader = new InputStreamReader(new BOMInputStream(is), StandardCharsets.ISO_8859_1);
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.EXCEL.withHeader())) {

            List<EcoIsland> ecoIslandList = new ArrayList<>();

            List<CSVRecord> csvRecords = csvParser.getRecords();


            for (CSVRecord csvRecord : csvRecords) {
                EcoIsland ecoIsland;

                if(csvRecord.get("xPos").length() == 0 || csvRecord.get("yPos").length() == 0){
                    ecoIsland = new EcoIsland(
                            csvRecord.get("id"),
                            csvRecord.get("building"),
                            csvRecord.get("buildingId"),
                            csvRecord.get("floor"),
                            csvRecord.get("description"),
                            csvRecord.get("bins"),
                            csvRecord.get("identifier")
                    );
                }
                else {
                    ecoIsland = new EcoIsland(
                            csvRecord.get("id"),
                            csvRecord.get("building"),
                            csvRecord.get("buildingId"),
                            csvRecord.get("floor"),
                            csvRecord.get("description"),
                            csvRecord.get("bins"),
                            Integer.parseInt(csvRecord.get("xPos")),
                            Integer.parseInt(csvRecord.get("yPos")),
                            csvRecord.get("identifier")
                    );
                }
                ecoIslandList.add(ecoIsland);
            }

            csvParser.close();
            fileReader.close();

            return ecoIslandList;
        } catch (IllegalArgumentException e){
            e.printStackTrace();
            throw new BadCSVFileException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayOutputStream ReportsToCSV(List<Report> reports){
        String[] csvHeader = {
                "id", "ecoislandId", "separation", "full", "dirty", "message", "time"
        };

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            CSVPrinter printer = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(csvHeader));

            for (Report report: reports) {
                printer.printRecord(
                        report.getId(),
                        report.getEcoIsland().getId(),
                        report.getSeparation(),
                        report.getFull(),
                        report.getDirty(),
                        report.getMessage(),
                        report.getTime()
                );
            }

            printer.flush();

            return out;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static ByteArrayOutputStream EcoislandsToCSV(List<EcoIsland> ecoIslands) {
        String[] csvHeader = {
                "id", "building", "buildingId", "floor", "description", "bins", "xPos", "yPos", "identifier"
        };
        ByteArrayInputStream byteArrayOutputStream = null;

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            CSVPrinter printer = new CSVPrinter(new PrintWriter(out), CSVFormat.DEFAULT.withHeader(csvHeader));

            for (EcoIsland ecoisland: ecoIslands) {
                printer.printRecord(
                        ecoisland.getId(),
                        ecoisland.getBuilding(),
                        ecoisland.getBuildingId(),
                        ecoisland.getFloor(),
                        ecoisland.getDescription(),
                        ecoisland.getBins(),
                        ecoisland.getxPos(),
                        ecoisland.getyPos(),
                        ecoisland.getIdentifier()
                );
            }

            printer.flush();

            return out;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
