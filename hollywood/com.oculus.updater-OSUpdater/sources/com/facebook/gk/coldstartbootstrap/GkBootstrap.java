package com.facebook.gk.coldstartbootstrap;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.SynchronizedCollection;
import com.facebook.infer.annotation.ThreadSafe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

@ThreadSafe
public final class GkBootstrap {
    private static volatile int sCrashDetectionMaxCrashes = -1;
    private static volatile int sCurrentCrashCount = -1;
    @SynchronizedCollection
    private static final Map<String, Integer> sKnownValues = Collections.synchronizedMap(new HashMap());

    private GkBootstrap() {
    }

    public static void updateGKValueInt(@Nullable Context context, String str, int i) {
        writeGKValueInt(context, str, i, getCurrentCrashCount(context));
    }

    public static int getGkValueInt(Context context, String str, int i) {
        return getGkValueIntWorker(context, str, i, isCrashDetectionOn());
    }

    public static int getGkValueIntNoCrashTracking(@Nullable Context context, String str, int i) {
        return getGkValueIntWorker(context, str, i, false);
    }

    private static int getGkValueIntWorker(@Nullable Context context, String str, int i, boolean z) {
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
        return getGkValueInt(context, str, z ? 1 : 0) == 1;
    }

    private static int getGkValueIntCore(Context context, String str, boolean z, int i) throws IOException {
        boolean isNewLocation = isNewLocation(context, str);
        File file = isNewLocation ? getFile(context, str) : null;
        if (file == null && !getLegacyFile(context, str).exists()) {
            return i;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(isNewLocation ? new FileInputStream(file) : context.openFileInput(str));
            try {
                int readInt = dataInputStream.readInt();
                if (z && file != null && file.length() >= 8) {
                    checkCrashCount(context, file, dataInputStream.readInt());
                }
                if (!isNewLocation) {
                    updateGKValueInt(context, str, readInt);
                }
                dataInputStream.close();
                return readInt;
            } catch (Throwable unused) {
            }
        } finally {
            if (!isNewLocation) {
                try {
                    context.deleteFile(str);
                } catch (SecurityException unused2) {
                }
            }
        }
        throw th;
    }

    private static void writeGKValueInt(@Nullable Context context, String str, int i, int i2) {
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

    private static void checkCrashCount(Context context, File file, int i) throws IOException {
        if (i >= 0) {
            int currentCrashCount = getCurrentCrashCount(context);
            int i2 = sCrashDetectionMaxCrashes;
            if (i + i2 < currentCrashCount) {
                BLog.w("GkBootstrap", "Detected crash loop valueCrashCount=%d currentCrashCount=%d maxCrashes=%d with %s", Integer.valueOf(i), Integer.valueOf(currentCrashCount), Integer.valueOf(i2), file);
                throw new IOException("Crash Count");
            }
        }
    }

    private static int getCurrentCrashCount(@Nullable Context context) {
        if (!isCrashDetectionOn()) {
            return -1;
        }
        int i = sCurrentCrashCount;
        if (i > -1) {
            return i;
        }
        synchronized (GkBootstrap.class) {
            int i2 = sCurrentCrashCount;
            if (i2 > -1) {
                return i2;
            }
            int gkValueIntNoCrashTracking = getGkValueIntNoCrashTracking(context, "GKBOOTSTRAP_CRASH_COUNTER", 0);
            sCurrentCrashCount = gkValueIntNoCrashTracking;
            writeGKValueInt(context, "GKBOOTSTRAP_CRASH_COUNTER", gkValueIntNoCrashTracking + 1, -1);
            return gkValueIntNoCrashTracking;
        }
    }

    private static boolean isCrashDetectionOn() {
        return sCrashDetectionMaxCrashes > -1;
    }

    private static File getFile(Context context, String str) {
        return new File(getDir(context), str);
    }

    private static File getLegacyFile(Context context, String str) {
        return new File(context.getFilesDir(), str);
    }

    private static File getDir(Context context) {
        return new File(context.getFilesDir(), "GkBootstrap");
    }

    private static boolean isNewLocation(Context context, String str) {
        return getFile(context, str).exists();
    }
}
