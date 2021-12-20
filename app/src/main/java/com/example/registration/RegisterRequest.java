package com.example.registration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://musclejava.cafe24.com/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userGender, String userEmail, String userEntrance, String userPeriod, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userEmail", userEmail);
        parameters.put("userEntrance", userEntrance);
        parameters.put("userPeriod", userPeriod);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
