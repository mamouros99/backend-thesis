package com.mamouros.backend.auth;
import com.google.gson.JsonObject;
import com.mamouros.backend.BackendApplication;
import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.FenixEduClientImpl;
import org.fenixedu.sdk.FenixEduUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FenixEdu {
    private final FenixEduClientImpl client;

    private JsonObject person;


    public FenixEdu(String oathKey, String oathSecret, String callbackUrl, String baseUrl) {

        try{
            ApplicationConfiguration config =  new ApplicationConfiguration(baseUrl, oathKey, oathSecret, callbackUrl);
            client = new FenixEduClientImpl(config);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to connect to IST");
        }
    }

    public FenixEdu() {

        try{
            ApplicationConfiguration config =  ApplicationConfiguration.fromPropertyFilename("/fenixedu.properties");
            client = new FenixEduClientImpl(config);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to connect to IST");
        }
    }

    public void setPerson(String code){
        FenixEduUserDetails userDetails = client.getUserDetailsFromCode(code);
        try{
            person = client.getPerson(userDetails.getAuthorization());
        }
        catch (Exception e){
            throw new RuntimeException("Unable to get users profile");
        }
    }

    public String getUsername(){
        return person.get("username").toString().replaceAll("\"", "");
    }

    public String getName(){
        return person.get("name").toString().replaceAll("\"", "");
    }
}
