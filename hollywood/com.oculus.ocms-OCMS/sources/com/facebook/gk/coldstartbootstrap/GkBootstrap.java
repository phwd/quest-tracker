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
    private static final int CRASH_FILE_SIZE = 8;
    static final String DIR_NAME = "GkBootstrap";
    static final int DISABLE_CRASH_TRACKING = -1;
    public static final int FALSE = 0;
    public static final String GKBOOTSTRAP_CRASH_COUNTER = "GKBOOTSTRAP_CRASH_COUNTER";
    public static final String TAG = "GkBootstrap";
    public static final int TRUE = 1;
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
    public static void updateGKFlagFile(Context context, String str, boolean z) {
        if (context != null) {
            updateGKValueInt(context, str, z ? 1 : 0);
        }
    }

    public static boolean checkAndClearGk(Context context, String str) {
        return checkAndClearGk(context, str, false);
    }

    public static boolean checkAndClearGk(Context context, String str, boolean z) {
        try {
            return checkIfGkEnabled(context, str, z);
        } finally {
            cleanUpGkFile(context, str);
        }
    }

    @Deprecated
    public static boolean checkIfGkEnabled(Context context, String str) {
        return checkIfGkEnabled(context, str, false);
    }

    public static boolean checkIfGkEnabled(Context context, String str, boolean z) {
        return getGkValueInt(context, str, z ? 1 : 0) == 1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0026  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void cleanUpGkFile(android.content.Context r3, java.lang.String r4) {
        /*
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            boolean r0 = isNewLocation(r3, r4)
            if (r0 == 0) goto L_0x000e
            java.io.File r3 = getFile(r3, r4)
            goto L_0x0012
        L_0x000e:
            java.io.File r3 = getLegacyFile(r3, r4)
        L_0x0012:
            r0 = 0
            r1 = 1
            boolean r2 = r3.delete()     // Catch:{ SecurityException -> 0x0023 }
            if (r2 != 0) goto L_0x0021
            boolean r3 = r3.exists()     // Catch:{ SecurityException -> 0x0023 }
            if (r3 == 0) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            r3 = 0
            goto L_0x0024
        L_0x0023:
            r3 = 1
        L_0x0024:
            if (r3 == 0) goto L_0x0031
            java.lang.Object[] r3 = new java.lang.Object[r1]
            r3[r0] = r4
            java.lang.String r4 = "GkBootstrap"
            java.lang.String r0 = "Unable to clean up GK file %s"
            com.facebook.debug.log.BLog.w(r4, r0, r3)
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.gk.coldstartbootstrap.GkBootstrap.cleanUpGkFile(android.content.Context, java.lang.String):void");
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
            int gkValueIntNoCrashTracking = getGkValueIntNoCrashTracking(context, GKBOOTSTRAP_CRASH_COUNTER, 0);
            sCurrentCrashCount = gkValueIntNoCrashTracking;
            writeGKValueInt(context, GKBOOTSTRAP_CRASH_COUNTER, gkValueIntNoCrashTracking + 1, -1);
            return gkValueIntNoCrashTracking;
        }
    }

    public static void enableCrashDetection(int i) {
        sCrashDetectionMaxCrashes = i;
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
