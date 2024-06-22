//package com.cocal.auth.dto.google;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class GoogleAuthCodeRequest {
//
//    private String scope = "https://www.googleapis.com/auth/userinfo.email";
//    private String accessType = "offline";
//    private String includeGrantedScopes = "true";
//    private String responseType = "code";
//   // private String state = "state_parameter_passthrough_value";
//    private String redirectUri = "http://localhost:8080/auth/login";
//    private String clientId = "250316693123-vv2orcfkdkigmir2rbs683h17alos84v.apps.googleusercontent.com";
//
//    public MultiValueMap<String, String> toMultiValueMap() {
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("scope", scope);
//        map.add("access_type", accessType);
//        map.add("include_granted_scopes", includeGrantedScopes);
//        map.add("response_type", responseType);
//     //   map.add("state", state);
//        map.add("redirect_uri", redirectUri);
//        map.add("client_id", clientId);
//        return map;
//    }
//}
