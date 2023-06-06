package com.mamouros.backend.helpers;

import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.ecoIsland.EcoIslandRepository;
import com.mamouros.backend.reports.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    EcoIslandRepository ecoIslandRepository;

    public void saveEcoislands(MultipartFile multipartFile){
        try {
            List<EcoIsland> ecoIslandList = CSVHelper.csvToEcoIsland(multipartFile.getInputStream());
            ecoIslandRepository.saveAll(ecoIslandList);
        } catch (IOException e){
            throw new RuntimeException("Failed To Store CSV data " + e.getMessage());
        }

    }

    public ResponseEntity<Resource> exportReports(List<Report> reports, String filename){
            ByteArrayOutputStream out =  CSVHelper.ReportsToCSV(reports);
            assert out != null : "Out is null";
            ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
            InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
            headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

            return new ResponseEntity<>(
                    fileInputStream,
                    headers,
                    HttpStatus.OK
            );
    }

    public ResponseEntity<Resource> exportEcoislands(List<EcoIsland> ecoIslands, String filename){
        ByteArrayOutputStream out =  CSVHelper.EcoislandsToCSV(ecoIslands);
        assert out != null : "Out is null";
        ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>(
                fileInputStream,
                headers,
                HttpStatus.OK
        );
    }

}
