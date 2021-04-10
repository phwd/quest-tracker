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

    public void setPid(@Nullable Integer pid2) {
        this.pid = pid2;
    }

    @Nullable
    public String getAcraField(String field) {
        return this.acraFields.get(field);
    }

    public void setAcraField(String field, String value) {
        this.acraFields.put(field, value);
    }

    public Map<String, String> getAcraFields() {
        return Collections.unmodifiableMap(this.acraFields);
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    @Nullable
    public String getMessage() {
        return this.mMessage;
    }
}
