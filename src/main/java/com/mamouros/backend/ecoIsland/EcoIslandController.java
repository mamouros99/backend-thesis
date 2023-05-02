package com.mamouros.backend.ecoIsland;

import com.mamouros.backend.exceptions.IslandNotFoundException;
import com.mamouros.backend.exceptions.WrongFileException;
import com.mamouros.backend.reports.CSVService;
import com.mamouros.backend.helpers.CSVHelper;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(path = "/ecoisland")
public class EcoIslandController {
    @Autowired
    private EcoIslandRepository ecoIslandRepository;


    @Autowired
    private CSVService csvService;


    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PostMapping(path="/add")
    public @ResponseBody String addNewEcoIsland(@RequestBody EcoIsland ecoIsland){
        ecoIslandRepository.save(ecoIsland);

        return "Saved";
    }


    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteEcoIslandById(@PathVariable Integer id){
        ecoIslandRepository.deleteById(id);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody EcoIsland getEcoIslandById(@PathVariable Integer id){
        return ecoIslandRepository.findById(id)
                .orElseThrow(() -> new IslandNotFoundException(id));

    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER','ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<EcoIsland> getAllEcoIslands() {
        return ecoIslandRepository.findAll();
    }


    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PostMapping(path="/upload")
    public @ResponseBody String uploadCSVEcoIslands(@RequestParam("file") MultipartFile file){

        if(CSVHelper.hasCSVFormat(file)){
            csvService.save(file);
            return "saved";
        }
        else {
            throw new WrongFileException(FilenameUtils.getExtension(file.getOriginalFilename()));
        }

    }


}
