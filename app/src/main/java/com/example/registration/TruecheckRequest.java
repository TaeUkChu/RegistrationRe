package com.example.registration;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class TruecheckRequest extends StringRequest {

    final static private String URL = "http://musclejava.cafe24.com/UserTruecheck.php";
    private Map<String, String> parameters;

    public TruecheckRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}

