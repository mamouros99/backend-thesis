package com.mamouros.backend.helpers;

import com.mamouros.backend.BackendApplication;
import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.exceptions.BadCSVFileException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

    public static List<EcoIsland> csvToEcoIsland(InputStream is) {
        //Must remove start bytes from excel files
        try (Reader fileReader = new InputStreamReader(new BOMInputStream(is), StandardCharsets.UTF_8);
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.EXCEL.withHeader())) {

            List<EcoIsland> ecoIslandList = new ArrayList<>();

            List<CSVRecord> csvRecords = csvParser.getRecords();


            for (CSVRecord csvRecord : csvRecords) {

                EcoIsland ecoIsland = new EcoIsland(
                        csvRecord.get("building"),
                        csvRecord.get("buildingId"),
                        csvRecord.get("floor"),
                        csvRecord.get("description"),
                        csvRecord.get("bins")
                );

                ecoIslandList.add(ecoIsland);
            }

            csvParser.close();
            fileReader.close();

            return ecoIslandList;
        } catch (IllegalArgumentException e){
            throw new BadCSVFileException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
