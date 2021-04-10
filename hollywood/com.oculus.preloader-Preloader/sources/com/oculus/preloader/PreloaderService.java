package com.oculus.preloader;

import android.app.IntentService;
import android.content.Intent;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PreloaderService extends IntentService {
    public static final boolean DEBUG = false;
    public static final String TAG = "Preloader";
    public static final String manifestPath = "/data/oculus_preloads/preload.inf";
    public static final String manifestSignature = "/data/oculus_preloads/preload.sig";
    public static final String preloadPath = "/data/oculus_preloads";

    public PreloaderService() {
        super("PreloaderService");
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        startPreloader();
    }

    private boolean installApk(String str, String str2) {
        FileInputStream fileInputStream;
        boolean z;
        try {
            fileInputStream = new FileInputStream(str);
            z = true;
        } catch (FileNotFoundException unused) {
            z = false;
            fileInputStream = null;
        }
        if (!z) {
            Log.w("Preloader", "Missing source file " + str);
            return true;
        }
        if (z && !(z = Utils.verifyHash(str, str2))) {
            Log.e("Preloader", "Invalid APK hash: " + str);
        }
        if (z) {
            z = Utils.installPackage(this, fileInputStream);
        }
        if (z && !new File(str).delete()) {
            Log.e("Preloader", "Couldn't delete " + str);
        }
        return z;
    }

    private boolean processMedia(String str, String str2, String str3, String str4) {
        int i;
        String str5 = "/data/oculus_preloads/" + str;
        String str6 = str2 + "/" + str;
        File file = new File(str5);
        if (!file.exists()) {
            Log.w("Preloader", "Missing source file " + str5);
            return true;
        }
        if (str3 == null) {
            i = -1;
        } else {
            i = Utils.getUidForPackage(this, str3);
        }
        if (i < 10000) {
            i = Process.myUid();
        }
        boolean copyMedia = Utils.copyMedia(str5, str6, str4, i);
        if (copyMedia && !file.delete()) {
            Log.e("Preloader", "Couldn't delete " + str5);
        }
        return copyMedia;
    }

    private boolean processApk(String str, String str2) {
        return installApk("/data/oculus_preloads/" + str, str2);
    }

    private boolean processLine(String str) {
        String[] split = str.split(",");
        if ("media".equals(split[0]) && split.length == 5) {
            return processMedia(split[1], split[2], split[3], split[4]);
        }
        if ("apk".equals(split[0]) && split.length == 3) {
            return processApk(split[1], split[2]);
        }
        if (split[0].equals("")) {
            return false;
        }
        Log.e("Preloader", "Error parsing " + str);
        return false;
    }

    private boolean processManifest(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean z = true;
        while (z) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return z;
                }
                z = processLine(readLine);
            } catch (IOException unused) {
                return false;
            }
        }
        return z;
    }

    private void startPreloader() {
        boolean z;
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(manifestPath);
            z = true;
        } catch (FileNotFoundException unused) {
            fileInputStream = null;
            z = false;
        }
        if (z && !(z = Utils.verifyFileSignature(this, manifestPath, manifestSignature))) {
            Log.e("Preloader", "Signature verification failed!");
        }
        if (z) {
            z = processManifest(fileInputStream);
        }
        sendBroadcastAsUser(new Intent(Utils.ACTION_ALL_INSTALLS_COMPLETE), UserHandle.SYSTEM);
        if (z) {
            for (File file : new File(preloadPath).listFiles()) {
                Utils.deleteRecursive(file);
            }
        }
    }
}
