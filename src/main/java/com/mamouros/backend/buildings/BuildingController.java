package com.mamouros.backend.buildings;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mamouros.backend.auth.User.UsersRepository;
import com.mamouros.backend.ecoIsland.EcoIsland;
import com.mamouros.backend.exceptions.IslandNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

@Controller
@RequestMapping(path = "/building")
public class BuildingController {

    @Autowired
    UserBuildingsRepository userBuildingsRepository;

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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(path = "/add")
    public @ResponseBody String addNewBuilding(@RequestBody UserBuildings userBuildings){
        System.out.println(userBuildings);
        userBuildingsRepository.save(userBuildings);
        return "Saved";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(path = "/get/{username}")
    public @ResponseBody Iterable<UserBuildings> getBuildingsByUsername(@PathVariable String username){
        return userBuildingsRepository.findAll();
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
