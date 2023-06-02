package com.mamouros.backend.buildings;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mamouros.backend.auth.User.Role;
import com.mamouros.backend.auth.User.User;
import com.mamouros.backend.auth.User.UsersRepository;
import com.mamouros.backend.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Controller
@RequestMapping(path = "/building")
public class BuildingController {

    @Autowired
    UserBuildingsRepository userBuildingsRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(path="/all")
    public @ResponseBody Object getBuildings(){
        String result = "test";
        try{
            //Get all campuses
            String id = getAlamedaId();
            if(id.length() == 0){
                throw new RuntimeException("Unable to recover buildings");
            }

            String aux = htmlRequest("https://fenix.tecnico.ulisboa.pt/api/fenix/v1/spaces/" + id);
            JsonObject obj = JsonParser.parseString(aux).getAsJsonObject();
            return obj.get("containedSpaces").getAsJsonArray().toString();

        } catch (Exception e){
            System.out.println(e + " -" + e.getMessage());
        }

        return result;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Object getBuildingsById(@PathVariable String id){
        try{

            String aux = htmlRequest("https://fenix.tecnico.ulisboa.pt/api/fenix/v1/spaces/" + id);
            return JsonParser.parseString(aux).getAsJsonObject().toString();

        } catch (Exception e){
            System.out.println(e + " -" + e.getMessage());
        }

        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EDITOR', 'ROLE_VIEWER')")
    @PostMapping(path = "/add/{username}")
    public @ResponseBody String addNewBuilding(@RequestBody UserBuildingsDto userBuildingsDto, @PathVariable String username){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String requesterUsername = ((User) principal).getUsername();
        Role role = ((User) principal).getRole();

        if(!role.equals(Role.ADMIN) && !requesterUsername.equals(username))
            throw new RuntimeException("You don't have permissions");
        else {
            User user = usersRepository.findByUsername(username)
                    .orElseThrow(() -> new UserNotFoundException(username));

            UserBuildings userBuildings = new UserBuildings();
            userBuildings.setId(new BuildingId(userBuildingsDto.getId(), user));
            userBuildings.setName(userBuildingsDto.getName());

            user.getBuildings().add(userBuildings);

            usersRepository.save(user);
            return "Saved";
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(path = "/get/{username}")
    public @ResponseBody Iterable<UserBuildings> getBuildingsByUsername(@PathVariable String username){
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return user.getBuildings();
    }

    @PreAuthorize("hasAnyRole('ROLE_VIEWER','ROLE_EDITOR', 'ROLE_ADMIN')")
    @GetMapping(path = "/mybuildings")
    public @ResponseBody Iterable<UserBuildings> getMyBuildings(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((User) principal).getUsername();

        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return user.getBuildings();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{username}/{buildingId}")
    public @ResponseBody void deleteBuildingFromUser(@PathVariable String username, @PathVariable String buildingId){
        User user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        user.getBuildings().removeIf(element -> (element.getId().equals(buildingId)));
        usersRepository.save(user);
    }


    public String htmlRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(2000);
        con.setReadTimeout(2000);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content.toString();
    }

    private String getAlamedaId() throws IOException {
        String allCampuses = htmlRequest("https://fenix.tecnico.ulisboa.pt/api/fenix/v1/spaces");
        Object object = JsonParser.parseString(allCampuses);
        JsonArray jsonArray = (JsonArray) object;
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject current = jsonArray.get(i).getAsJsonObject();
            if(Objects.equals(current.get("name").getAsString(), "Alameda"))
                return current.get("id").getAsString();
        }

        return "";
    }

}
