package com.oculus.vrshell.sharedprefs;

public interface SharedKeys {
    public static final PrefKey FLAGS;
    public static final PrefKey ROOT;
    public static final PrefKey SETTINGS;

    static {
        PrefKey prefKey = new PrefKey("/");
        ROOT = prefKey;
        SETTINGS = new PrefKey(prefKey, "settings/");
        FLAGS = new PrefKey(prefKey, "flags/");
    }
}
