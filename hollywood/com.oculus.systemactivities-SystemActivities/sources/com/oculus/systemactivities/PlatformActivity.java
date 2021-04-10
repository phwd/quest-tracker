package com.oculus.systemactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import java.io.IOException;
import java.io.StringReader;

public class PlatformActivity extends Activity {
    public static final String INTENT_KEY_CMD = "intent_cmd";
    private static final String INTENT_KEY_DATA = "intent_data";
    public static final String INTENT_KEY_FROM_PKG = "intent_pkg";
    private static final String SA_INTENT_COMMAND_CONFIRMQUIT = "confirmQuit";
    private static final String SA_INTENT_COMMAND_FAILMENU = "failMenu";
    private static final String SA_INTENT_COMMAND_GLOBAL_MENU = "globalMenu";
    private static final int SA_INTENT_FORWARD_ANYTIMEUI_FAILMENU = 4;
    private static final int SA_INTENT_FORWARD_ANYTIMEUI_QUIT = 3;
    private static final int SA_INTENT_FORWARD_ANYTIMEUI_SETTINGS = 2;
    private static final int SA_INTENT_FORWARD_VALID = 1;
    public static final String TAG = "PlatformActivity";

    static {
        Log.d(TAG, "LoadLibrary");
        System.loadLibrary("systemactivities");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public String parseCommandString(String cmdString) {
        JsonReader jr = new JsonReader(new StringReader(cmdString));
        try {
            jr.beginObject();
            while (jr.hasNext()) {
                if (jr.nextName().equals("Command")) {
                    return jr.nextString();
                }
            }
            return BuildConfig.FLAVOR;
        } catch (IOException ex) {
            Log.d(TAG, "IO Exception parsing commandString: " + ex);
            return BuildConfig.FLAVOR;
        } catch (IllegalStateException ex2) {
            Log.d(TAG, "State exception parsing commandString: " + ex2);
            return BuildConfig.FLAVOR;
        }
    }

    private static String getCommandStringFromIntent(Intent intent) {
        String commandStr;
        if (intent == null || (commandStr = intent.getStringExtra(INTENT_KEY_CMD)) == null) {
            return BuildConfig.FLAVOR;
        }
        return commandStr;
    }

    private static String getPackageStringFromIntent(Intent intent) {
        String packageStr;
        if (intent == null || (packageStr = intent.getStringExtra(INTENT_KEY_FROM_PKG)) == null) {
            return BuildConfig.FLAVOR;
        }
        return packageStr;
    }

    /* access modifiers changed from: package-private */
    public void forwardIntent(Intent intent) {
        Log.d(TAG, "forwardIntent(), old cname = " + intent.getComponent().toString());
        String cmdString = getCommandStringFromIntent(intent);
        if (cmdString != null || !cmdString.isEmpty()) {
            String commandVal = parseCommandString(cmdString);
            int uriPathId = SA_INTENT_FORWARD_VALID;
            if (commandVal != null && !commandVal.isEmpty()) {
                if (commandVal.equals(SA_INTENT_COMMAND_GLOBAL_MENU)) {
                    uriPathId = SA_INTENT_FORWARD_ANYTIMEUI_SETTINGS;
                } else if (commandVal.equals(SA_INTENT_COMMAND_CONFIRMQUIT)) {
                    uriPathId = SA_INTENT_FORWARD_ANYTIMEUI_QUIT;
                } else if (commandVal.equals(SA_INTENT_COMMAND_FAILMENU)) {
                    uriPathId = SA_INTENT_FORWARD_ANYTIMEUI_FAILMENU;
                }
            }
            Intent launchIntent = new Intent("com.oculus.vrshell.intent.action.LAUNCH");
            launchIntent.putExtra(INTENT_KEY_FROM_PKG, getPackageStringFromIntent(intent));
            if (uriPathId == SA_INTENT_FORWARD_ANYTIMEUI_QUIT) {
                launchIntent.putExtra(INTENT_KEY_DATA, "systemux://dialog/exit");
            } else if (uriPathId == SA_INTENT_FORWARD_ANYTIMEUI_FAILMENU) {
                launchIntent.putExtra(INTENT_KEY_DATA, "systemux://dialog/system-failure");
                launchIntent.putExtra(INTENT_KEY_CMD, cmdString);
            } else {
                launchIntent.putExtra(INTENT_KEY_DATA, "systemux://settings");
            }
            sendBroadcast(launchIntent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        forwardIntent(getIntent());
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.d(TAG, "PlatformActivity::onDestroy()");
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Log.v(TAG, "onNewIntent");
        setIntent(intent);
        forwardIntent(intent);
        finish();
    }

    public static void dumpIntent(Intent i) {
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            Log.e(TAG, "Dumping Intent start");
            Log.e(TAG, i.toString());
            for (String key : bundle.keySet()) {
                Log.e(TAG, "[" + key + "=" + bundle.get(key) + "]");
            }
            Log.e(TAG, "Dumping Intent end");
        }
    }
}
