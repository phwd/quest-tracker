package com.oculus.vrshell.sharedprefs;

public interface SharedKeys {
    public static final PrefKey ROOT = new PrefKey("/");
    public static final PrefKey SETTINGS = ROOT.extend("settings/");
}
