package com.example.registration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EntranceSumRequest extends StringRequest {

    final static private String URL = "http://musclejava.cafe24.com/EntranceSum.php";
    private Map<String, String> parameters;

    public EntranceSumRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
    }
    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
