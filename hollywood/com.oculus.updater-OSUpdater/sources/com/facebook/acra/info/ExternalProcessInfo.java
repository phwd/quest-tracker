package com.facebook.acra.info;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExternalProcessInfo {
    protected final LinkedHashMap<String, String> acraFields = new LinkedHashMap<>();
    @Nullable
    private String mMessage;
    @Nullable
    protected Integer pid;

    @Nullable
    public Integer getPid() {
        return this.pid;
    }

    public void setPid(@Nullable Integer num) {
        this.pid = num;
    }

    @Nullable
    public String getAcraField(String str) {
        return this.acraFields.get(str);
    }

    public void setAcraField(String str, String str2) {
        this.acraFields.put(str, str2);
    }

    public Map<String, String> getAcraFields() {
        return Collections.unmodifiableMap(this.acraFields);
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    @Nullable
    public String getMessage() {
        return this.mMessage;
    }
}
