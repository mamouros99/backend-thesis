package com.mamouros.backend.ecoIsland;

import com.mamouros.backend.auth.User.Role;
import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.exceptions.IslandNotFoundException;
import com.mamouros.backend.exceptions.WrongFileException;
import com.mamouros.backend.helpers.CSVHelper;
import com.mamouros.backend.helpers.CSVService;
import com.mamouros.backend.helpers.GlobalHelper;
import com.mamouros.backend.reports.Report;
import com.mamouros.backend.reports.ReportRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EcoIslandService {

    @Autowired
    private EcoIslandRepository ecoIslandRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CSVService csvService;


    public void save(EcoIsland ecoIsland) {
        ecoIslandRepository.save(ecoIsland);
    }

    public void updateEcoIsland(EcoIsland ecoIsland) {
        ecoIslandRepository.findById(ecoIsland.getId()).orElseThrow(() -> new IslandNotFoundException(ecoIsland.getId()));
        ecoIslandRepository.save(ecoIsland);
    }

    public void deleteById(String id) {
        ecoIslandRepository.deleteById(id);
    }

    public EcoIsland findById(String id) {
        return ecoIslandRepository.findById(id)
                .orElseThrow(() -> new IslandNotFoundException(id));
    }

    public Iterable<EcoIsland> getAllEcoIslands() {
        User requester = GlobalHelper.getUserFromSecurityContext();
        if(!requester.getRole().equals(Role.ADMIN)) {
            return ecoIslandRepository.findAllByUsername(requester.getUsername());
        }
        else {
            return ecoIslandRepository.findAll();
        }
    }

    public ResponseEntity<Resource> exportEcoIslands() {
        List<EcoIsland> ecoIslands = (List<EcoIsland>) getAllEcoIslands();
        return csvService.exportEcoislands(ecoIslands, "ecoislands.csv");
    }

    public void uploadCSVEcoIslands(MultipartFile file) {
        if(CSVHelper.hasCSVFormat(file)){
            csvService.saveEcoislands(file);
        }
        else {
            throw new WrongFileException(FilenameUtils.getExtension(file.getOriginalFilename()));
        }
    }

    public void archiveAllIslandReports(String islandId) {

        List<Report> reports = (List<Report>) reportRepository.findAllByEcoIsland(islandId);
        for (Report report: reports) {
            report.setArchived(true);
        }
        reportRepository.saveAll(reports);
    }
}
