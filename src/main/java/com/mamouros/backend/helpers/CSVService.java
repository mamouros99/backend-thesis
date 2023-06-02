package com.mamouros.backend.helpers;

import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.ecoIsland.EcoIslandRepository;
import com.mamouros.backend.helpers.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    EcoIslandRepository ecoIslandRepository;

    public void save(MultipartFile multipartFile){
        try {
            List<EcoIsland> ecoIslandList = CSVHelper.csvToEcoIsland(multipartFile.getInputStream());
            ecoIslandRepository.saveAll(ecoIslandList);
        } catch (IOException e){
            throw new RuntimeException("Failed To Store CSV data " + e.getMessage());
        }

    }

    public Iterable<EcoIsland> getAllEcoIslands(){
        return ecoIslandRepository.findAll();
    }
}
