package com.mamouros.backend.reports;

import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.ecoIsland.EcoIslandRepository;
import com.mamouros.backend.exceptions.ReportNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/report")
public class ReportController {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private EcoIslandRepository ecoIslandRepository;




    @PostMapping(path="/add")
    public @ResponseBody String addReport(@RequestBody ReportDto reportDto){

        EcoIsland ecoIsland = ecoIslandRepository.findById(reportDto.getEcoIslandId()).orElseThrow(RuntimeException::new);

        Report n = new Report(
                ecoIsland,
                reportDto.getSeparation(),
                reportDto.getFull(),
                reportDto.getDirty(),
                reportDto.getTime()
        );

        reportRepository.save(n);

        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @DeleteMapping("delete/{id}")
    public @ResponseBody void deleteById(@PathVariable Integer id){
        reportRepository.deleteById(id);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Report getReportById(@PathVariable Integer id){
        return reportRepository.findById(id)
                .orElseThrow(() -> new ReportNotFoundException(id));
    }




}
