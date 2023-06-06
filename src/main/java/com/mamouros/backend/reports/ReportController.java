package com.mamouros.backend.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping(path="/add")
    public @ResponseBody void addReport(@RequestBody ReportDto reportDto){
        reportService.addReport(reportDto);
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER', 'ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER', 'ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping(path = "/export")
    public @ResponseBody ResponseEntity<Resource> exportReports() {
        return reportService.exportReports();
    }

    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @DeleteMapping("delete/{id}")
    public @ResponseBody void deleteById(@PathVariable Long id){
        reportService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER', 'ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping(path="/{id}")
    public @ResponseBody Report getReportById(@PathVariable Long id){
        return reportService.findById(id);
    }
}
