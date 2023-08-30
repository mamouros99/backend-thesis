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

    @Value("${desktop.base.url}")
    private String desktopBaseUrl;

    @Value("${mobile.base.url}")
    private String mobileBaseUrl;

    @Value("${desktop.oauth.consumer.key}")
    private String desktopOauthConsumerKey;

    @Value("${mobile.oauth.consumer.key}")
    private String mobileOauthConsumerKey;

    @Value("${desktop.oauth.consumer.secret}")
    private String desktopOauthConsumerSecret;

    @Value("${mobile.oauth.consumer.secret}")
    private String mobileOauthConsumerSecret;

    @Value("${desktop.callback.url}")
    private String desktopCallbackUrl;

    @Value("${mobile.callback.url}")
    private String mobileCallbackUrl;
   @GetMapping("/fenix/desktop/{code}")
    public @ResponseBody AuthResponseDto fenixAuthDesktop(@PathVariable String code) {

        FenixEdu fenix = new FenixEdu(desktopBaseUrl, desktopOauthConsumerKey, desktopOauthConsumerSecret, desktopCallbackUrl);
        fenix.setPerson(code);
        //check if on db
        return authService.fenixAuth(fenix);
    }

    @GetMapping("/fenix/mobile/{code}")
    public @ResponseBody AuthResponseDto fenixAuthMobile(@PathVariable String code) {

        FenixEdu fenix = new FenixEdu(mobileBaseUrl, mobileOauthConsumerKey, mobileOauthConsumerSecret, mobileCallbackUrl);
        fenix.setPerson(code);
        //check if on db
        return authService.fenixAuth(fenix);
    }

}
