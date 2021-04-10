package com.facebook.crudolib.prefs;

import com.facebook.infer.annotation.Nullsafe;
import java.io.File;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class LspfBridgeForInfra {
    private LspfBridgeForInfra() {
    }

    public static File getSharedPrefsFile(LightSharedPreferencesFactory lightSharedPreferencesFactory, String str) {
        return lightSharedPreferencesFactory.getSharedPrefsFile(str);
    }

    public static File getSharedPrefsBaseDir(LightSharedPreferencesFactory lightSharedPreferencesFactory) {
        return lightSharedPreferencesFactory.getPrivateDir();
    }
}
