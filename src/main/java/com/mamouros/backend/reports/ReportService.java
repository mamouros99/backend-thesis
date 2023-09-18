package com.mamouros.backend.reports;

import com.mamouros.backend.auth.User.Role;
import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.buildings.UserBuildingsRepository;
import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.ecoIsland.EcoIslandRepository;
import com.mamouros.backend.email.EmailService;
import com.mamouros.backend.exceptions.ReportNotFoundException;
import com.mamouros.backend.helpers.CSVService;
import com.mamouros.backend.helpers.GlobalHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private EcoIslandRepository ecoIslandRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserBuildingsRepository buildingsRepository;


    @Autowired
    private CSVService csvService;

    @Value("${desktop.url}")
    private String myURL;

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

        //Send Email to all Editors of building.
        List<String> emails = (List<String>) buildingsRepository.findAllUsersByBuildingNameThatReceiveEmails(ecoIsland.getBuilding());

        for (String email: emails) {

            emailService.sendEmail(email, "Novo Relatório no Edifício: " + ecoIsland.getBuilding(), "Um relatório foi efetuado às " +
                    Instant.ofEpochMilli(Long.parseLong(reportDto.getTime())).atZone(ZoneId.systemDefault()).toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss d-MM-yyyy "))
                    + ". \n " +
                    "Para aceder ao relatório siga o link: " + myURL + "/report/" + n.getId() + "\n" +
                    "Para aceder à ecoílha siga o link: " + myURL + "/ecoisland/" + ecoIsland.getId());
        }
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

    public void deleteById(Long id) {

        reportRepository.deleteById(id);
    }

    public Report findById(Long id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }

    public ResponseEntity<Resource> exportReports() {
        List<Report> reports = (List<Report>) getAllReports();
        return csvService.exportReports(reports, "reports.csv");
    }

    public void archiveReport(Long id) {

        Report report = findById(id);
        report.setArchived(!report.getArchived());
        reportRepository.save(report);

    }
}
