package com.mamouros.backend.reports;

import com.mamouros.backend.auth.User.Role;
import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.ecoIsland.EcoIslandRepository;
import com.mamouros.backend.exceptions.ReportNotFoundException;
import com.mamouros.backend.helpers.CSVService;
import com.mamouros.backend.helpers.GlobalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private EcoIslandRepository ecoIslandRepository;

    @Autowired
    private CSVService csvService;

    public void addReport(ReportDto reportDto){
        EcoIsland ecoIsland = ecoIslandRepository.findById(reportDto.getEcoIslandId()).orElseThrow(RuntimeException::new);

        Report n = new Report(
                ecoIsland,
                reportDto.getSeparation(),
                reportDto.getFull(),
                reportDto.getDirty(),
                reportDto.getTime(),
                reportDto.getMessage()
        );

        reportRepository.save(n);
    }

    public Iterable<Report> getAllReports(){
        User user = GlobalHelper.getUserFromSecurityContext();
        if(!user.getRole().equals(Role.ADMIN)) {
            return reportRepository.findAllByUsername(user.getUsername());
        }
        else {
            return reportRepository.findAll();
        }
    }

    public Iterable<Report> allReports(){

            return reportRepository.findAll();
    }

    public void deleteById(Long id) {

        reportRepository.deleteById(id);
    }

    public Report findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    public ResponseEntity<Resource> exportReports() {
        //TODO Change this to getAllReports
        List<Report> reports = (List<Report>) getAllReports();
        return csvService.exportReports(reports, "reports.csv");
    }
}
