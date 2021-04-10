package com.oculus.os;

import android.content.Intent;

public class ReturnToVrShell {
    public static Intent getConfirmQuitIntentForPackage(String packageName) {
        Intent confirmQuitIntent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
        confirmQuitIntent.putExtra("intent_data", "systemux://dialog/exit");
        confirmQuitIntent.putExtra("intent_pkg", packageName);
        confirmQuitIntent.putExtra("blackscreen", true);
        confirmQuitIntent.addFlags(65536);
        confirmQuitIntent.addFlags(32);
        confirmQuitIntent.addFlags(268435456);
        return confirmQuitIntent;
    }
}
