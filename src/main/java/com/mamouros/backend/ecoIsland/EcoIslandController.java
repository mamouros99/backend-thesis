package com.mamouros.backend.ecoIsland;

import com.mamouros.backend.auth.User.Role;
import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.exceptions.IslandNotFoundException;
import com.mamouros.backend.exceptions.WrongFileException;
import com.mamouros.backend.helpers.CSVService;
import com.mamouros.backend.helpers.CSVHelper;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public @ResponseBody void deleteEcoIslandById(@PathVariable Long id){
        ecoIslandRepository.deleteById(id);
    }

    @GetMapping(path="/{id}")
    public @ResponseBody EcoIsland getEcoIslandById(@PathVariable Long id){
        return ecoIslandRepository.findById(id)
                .orElseThrow(() -> new IslandNotFoundException(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER','ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<EcoIsland> getAllEcoIslands() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role role = ((User) principal).getRole();
        String username = ((User) principal).getUsername();
        if(!role.equals(Role.ADMIN)) {
            return ecoIslandRepository.findAllByUsername(username);
        }
        else {
            return ecoIslandRepository.findAll();
        }
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

    @PreAuthorize("hasAnyRole('ROLE_EDITOR', 'ROLE_ADMIN')")
    @PutMapping("/update")
    public @ResponseBody void updateEcoisland(@RequestBody EcoIsland ecoIsland ){
        ecoIslandRepository.findById(ecoIsland.getId()).orElseThrow(() -> new IslandNotFoundException(ecoIsland.getId()));
        ecoIslandRepository.save(ecoIsland);
    }
}
