package com.facebook.gk.coldstartbootstrap;

import android.content.Context;
import com.facebook.debug.log.BLog;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class GkBootstrap {
    private static volatile int sCrashDetectionMaxCrashes = -1;
    private static volatile int sCurrentCrashCount = -1;
    private static final Map<String, Integer> sKnownValues = Collections.synchronizedMap(new HashMap());

    private GkBootstrap() {
    }

    public static int getGkValueInt(Context context, String str, int i) {
        return getGkValueIntWorker(context, str, i, isCrashDetectionOn());
    }

    private static int getGkValueIntWorker(Context context, String str, int i, boolean z) {
        if (context == null) {
            return i;
        }
        Integer num = sKnownValues.get(str);
        if (num != null) {
            return num.intValue();
        }
        try {
            i = getGkValueIntCore(context, str, z, i);
        } catch (IOException unused) {
        }
        sKnownValues.put(str, Integer.valueOf(i));
        return i;
    }

    @Deprecated
    public static boolean checkIfGkEnabled(Context context, String str) {
        return checkIfGkEnabled(context, str, false);
    }

    public static boolean checkIfGkEnabled(Context context, String str, boolean z) {
        return getGkValueInt(context, str, 0) == 1;
    }

    private static void writeGKValueInt(Context context, String str, int i, int i2) {
        if (context != null) {
            try {
                File dir = getDir(context);
                if (dir.exists() || dir.mkdir()) {
                    File file = getFile(context, str);
                    if (i2 != -1) {
                        i2 = adjustCrashCount(file, i2);
                    }
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
                        try {
                            dataOutputStream.writeInt(i);
                            if (i2 != -1) {
                                dataOutputStream.writeInt(i2);
                            }
                            dataOutputStream.close();
                            return;
                        } catch (Throwable unused) {
                        }
                    } catch (IOException | SecurityException e) {
                        BLog.w("GkBootstrap", e, "Unable to persist GK value to %s", file);
                        return;
                    }
                } else {
                    return;
                }
            } catch (SecurityException e2) {
                BLog.e("GkBootstrap", e2, "Unable to create %s directory", "GkBootstrap");
                return;
            }
        } else {
            return;
        }
        throw th;
    }

    private static int adjustCrashCount(File file, int i) {
        try {
            if (file.length() >= 8) {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                try {
                    dataInputStream.skipBytes(4);
                    if (dataInputStream.readInt() > i) {
                        dataInputStream.close();
                        return -1;
                    }
                    dataInputStream.close();
                } catch (Throwable unused) {
                }
            }
        } catch (IOException | SecurityException e) {
            BLog.w("GkBootstrap", e, "Unable to read crash value of %s", file);
        }
        return i;
        throw th;
    }

    private static int getCurrentCrashCount(Context context) {
        if (!isCrashDetectionOn()) {
            return -1;
        }
        int i = sCurrentCrashCount;
        if (i >= 0) {
            return i;
        }
        synchronized (GkBootstrap.class) {
            int i2 = sCurrentCrashCount;
            if (i2 >= 0) {
                return i2;
            }
            int gkValueIntWorker = getGkValueIntWorker(context, "GKBOOTSTRAP_CRASH_COUNTER", 0, false);
            sCurrentCrashCount = gkValueIntWorker;
            writeGKValueInt(context, "GKBOOTSTRAP_CRASH_COUNTER", gkValueIntWorker + 1, -1);
            return gkValueIntWorker;
        }
    }

    private static boolean isCrashDetectionOn() {
        return sCrashDetectionMaxCrashes >= 0;
    }

    private static File getFile(Context context, String str) {
        return new File(getDir(context), str);
    }

    private static File getDir(Context context) {
        return new File(context.getFilesDir(), "GkBootstrap");
    }

    private static int getGkValueIntCore(Context context, String str, boolean z, int i) throws IOException {
        File file;
        int readInt;
        boolean exists = getFile(context, str).exists();
        if (exists) {
            file = getFile(context, str);
        } else {
            file = null;
        }
        if (file == null && !new File(context.getFilesDir(), str).exists()) {
            return i;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(exists ? new FileInputStream(file) : context.openFileInput(str));
            try {
                int readInt2 = dataInputStream.readInt();
                if (z && file != null && file.length() >= 8 && (readInt = dataInputStream.readInt()) >= 0) {
                    int currentCrashCount = getCurrentCrashCount(context);
                    int i2 = sCrashDetectionMaxCrashes;
                    if (readInt + i2 < currentCrashCount) {
                        BLog.w("GkBootstrap", "Detected crash loop valueCrashCount=%d currentCrashCount=%d maxCrashes=%d with %s", Integer.valueOf(readInt), Integer.valueOf(currentCrashCount), Integer.valueOf(i2), file);
                        throw new IOException("Crash Count");
                    }
                }
                if (!exists) {
                    writeGKValueInt(context, str, readInt2, getCurrentCrashCount(context));
                }
                dataInputStream.close();
                return readInt2;
            } catch (Throwable unused) {
            }
        } finally {
            if (!exists) {
                try {
                    context.deleteFile(str);
                } catch (SecurityException unused2) {
                }
            }
        }
        throw th;
    }
}
