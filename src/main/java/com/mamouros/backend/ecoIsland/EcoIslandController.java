package com.mamouros.backend.ecoIsland;

import com.mamouros.backend.auth.User.Role;
import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.exceptions.IslandNotFoundException;
import com.mamouros.backend.exceptions.WrongFileException;
import com.mamouros.backend.helpers.CSVService;
import com.mamouros.backend.helpers.CSVHelper;

import com.mamouros.backend.helpers.GlobalHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/ecoisland")
public class EcoIslandController {

    @Autowired
    private EcoIslandService ecoIslandService;

    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PostMapping(path="/add")
    public @ResponseBody void addNewEcoIsland(@RequestBody EcoIsland ecoIsland){
        ecoIslandService.save(ecoIsland);
    }

    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteEcoIslandById(@PathVariable Long id){
        ecoIslandService.deleteById(id);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody EcoIsland getEcoIslandById(@PathVariable Long id){
        return ecoIslandService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER','ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<EcoIsland> getAllEcoIslands() {
       return ecoIslandService.getAllEcoIslands();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/export")
    public @ResponseBody ResponseEntity<Resource> exportEcoIslands() {
        return ecoIslandService.exportEcoIslands();
    }

    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PostMapping(path="/upload")
    public @ResponseBody void uploadCSVEcoIslands(@RequestParam("file") MultipartFile file){
        ecoIslandService.uploadCSVEcoIslands(file);
    }

    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PutMapping("/update")
    public @ResponseBody void updateEcoisland(@RequestBody EcoIsland ecoIsland ){
        ecoIslandService.updateEcoIsland(ecoIsland);
    }
}
