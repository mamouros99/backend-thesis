package com.mamouros.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${oauth.consumer.key}")
    private String oauthConsumerKey;

    @Value("${oauth.consumer.secret}")
    private String oauthConsumerSecret;

    @Value("${callback.url}")
    private String callbackUrl;
   @GetMapping("/fenix/{code}")
    public @ResponseBody AuthResponseDto fenixAuth(@PathVariable String code) {

        FenixEdu fenix = new FenixEdu(baseUrl, oauthConsumerKey, oauthConsumerSecret, callbackUrl);
        fenix.setPerson(code);
        //check if on db
        return authService.fenixAuth(fenix);
    }

}
