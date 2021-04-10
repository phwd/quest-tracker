package com.facebook.acra.info;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExternalProcessInfo {
    public final LinkedHashMap acraFields = new LinkedHashMap();
    public String mMessage;
    public Integer pid;

    public String getAcraField(String str) {
        return (String) this.acraFields.get(str);
    }

    public Map getAcraFields() {
        return Collections.unmodifiableMap(this.acraFields);
    }

    public void setAcraField(String str, String str2) {
        this.acraFields.put(str, str2);
    }

    public String getMessage() {
        return this.mMessage;
    }

    public Integer getPid() {
        return this.pid;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public void setPid(Integer num) {
        this.pid = num;
    }
}
