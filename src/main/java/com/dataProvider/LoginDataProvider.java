package com.dataProvider;

import java.util.HashMap;

public class LoginDataProvider {

    public static HashMap<String, String> login_invalid_data() {
        HashMap<String, String> data = new HashMap<>();
        data.put("username", "passone");
        return data;
    }

}
