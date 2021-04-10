package com.oculus.panelapp.debug.settings;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum SettingType {
    INVALID("Invalid"),
    CATEGORY_HEADER("CategoryHeader"),
    ACTION("Action"),
    STRING("String"),
    INTEGER("Integer"),
    FLOAT("Float"),
    BOOLEAN("Boolean");
    
    private static final Map<String, SettingType> values = new HashMap();
    private final String text;

    static {
        Iterator it = EnumSet.allOf(SettingType.class).iterator();
        while (it.hasNext()) {
            SettingType settingType = (SettingType) it.next();
            values.put(settingType.toString(), settingType);
        }
    }

    private SettingType(String str) {
        this.text = str;
    }

    public String toString() {
        return this.text;
    }

    public static SettingType fromString(String str) {
        if (values.containsKey(str)) {
            return values.get(str);
        }
        throw new IllegalArgumentException(String.format("No enum constant com.oculus.panelapp.debug.settings.SettingType.%s", str));
    }
}
