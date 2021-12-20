package com.example.registration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PeriodRequest extends StringRequest {

    final static private String URL = "http://musclejava.cafe24.com/UserPeriod.php?userID=1";
    private Map<String, String> parameters;

    public PeriodRequest( Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        //parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
