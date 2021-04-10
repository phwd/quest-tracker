package com.oculus.panellib.modules;

import org.json.JSONArray;

public interface RCTResolveCaller {
    void invoke(int i, String str);

    void invokeJSON(int i, JSONArray jSONArray);
}
