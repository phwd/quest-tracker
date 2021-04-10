package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TriggeredLoggingInfo {
    private final Map<String, TriggeredLoggingConfig> mConfigMap;

    public TriggeredLoggingInfo() {
        this.mConfigMap = new HashMap();
    }

    public TriggeredLoggingInfo(TriggeredLoggingConfig triggeredLoggingConfig) {
        this();
        this.mConfigMap.put(triggeredLoggingConfig.useCaseId(), triggeredLoggingConfig);
    }

    public TriggeredLoggingInfo(Map<String, String> map) {
        this();
        for (String str : map.values()) {
            TriggeredLoggingConfig deserialize = TriggeredLoggingConfig.deserialize(str);
            if (deserialize != null) {
                this.mConfigMap.put(deserialize.useCaseId(), deserialize);
            }
        }
    }

    public Map<String, String> useCase2ConfigMap() {
        HashMap hashMap = new HashMap();
        for (TriggeredLoggingConfig triggeredLoggingConfig : this.mConfigMap.values()) {
            hashMap.put(triggeredLoggingConfig.useCaseId(), triggeredLoggingConfig.serialize());
        }
        return hashMap;
    }

    public void add(TriggeredLoggingConfig triggeredLoggingConfig) {
        this.mConfigMap.put(triggeredLoggingConfig.useCaseId(), triggeredLoggingConfig);
    }
}
