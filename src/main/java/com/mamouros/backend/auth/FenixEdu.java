package com.mamouros.backend.auth;
import com.google.gson.JsonObject;
import org.fenixedu.sdk.ApplicationConfiguration;
import org.fenixedu.sdk.FenixEduClientImpl;
import org.fenixedu.sdk.FenixEduUserDetails;

public class FenixEdu {
    private final FenixEduClientImpl client;

    private JsonObject person;

    public FenixEdu(String baseUrl, String oauthConsumerKey, String oauthConsumerSecret, String callbackUrl) {
        try{
            ApplicationConfiguration config =  new ApplicationConfiguration(baseUrl, oauthConsumerKey, oauthConsumerSecret, callbackUrl);
            client = new FenixEduClientImpl(config);
        }
        catch (Exception e){
            e.printStackTrace();
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

    public String getEmail(){
        return person.get("email").toString().replaceAll("\"", "");
    }

}
