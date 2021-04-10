package com.oculus.os;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.io.File;
import java.util.List;

public abstract class DumpsysProxyClientService extends Service {
    public static final String DUMP_FILES_KEY = "dumpFileList";
    public static final String DUMP_STATE_KEY = "dumpState";

    public abstract String dumpState();

    public abstract String getAppName();

    public DumpsysProxyClientService() {
        throw new RuntimeException("Stub!");
    }

    public void onCreate() {
        throw new RuntimeException("Stub!");
    }

    public IBinder onBind(Intent intent) {
        throw new RuntimeException("Stub!");
    }

    public List<File> getDumpFiles() {
        throw new RuntimeException("Stub!");
    }
}
