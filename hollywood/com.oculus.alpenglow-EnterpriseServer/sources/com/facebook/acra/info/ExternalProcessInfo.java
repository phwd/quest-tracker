package com.facebook.acra.info;

import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExternalProcessInfo {
    public final LinkedHashMap<String, String> acraFields = new LinkedHashMap<>();
    @Nullable
    public String mMessage;
    @Nullable
    public Integer pid;

    @Nullable
    public String getAcraField(String str) {
        return this.acraFields.get(str);
    }

    public Map<String, String> getAcraFields() {
        return Collections.unmodifiableMap(this.acraFields);
    }

    @Nullable
    public String getMessage() {
        return this.mMessage;
    }

    @Nullable
    public Integer getPid() {
        return this.pid;
    }

    public void setAcraField(String str, String str2) {
        this.acraFields.put(str, str2);
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public void setPid(@Nullable Integer num) {
        this.pid = num;
    }
}
