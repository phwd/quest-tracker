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

    public static void updateGKValueInt(Context context, String gkFileName, int valueOnNextStart) {
        writeGKValueInt(context, gkFileName, valueOnNextStart, getCurrentCrashCount(context));
    }

    public static int getGkValueInt(Context context, String gkFileName, int defaultValue) {
        return getGkValueIntWorker(context, gkFileName, defaultValue, isCrashDetectionOn());
    }

    public static int getGkValueIntNoCrashTracking(Context context, String gkFileName, int defaultValue) {
        return getGkValueIntWorker(context, gkFileName, defaultValue, false);
    }

    private static int getGkValueIntWorker(Context context, String gkFileName, int defaultValue, boolean enableCrashDetection) {
        int val;
        if (context == null) {
            return defaultValue;
        }
        Integer value = sKnownValues.get(gkFileName);
        if (value != null) {
            return value.intValue();
        }
        try {
            val = getGkValueIntCore(context, gkFileName, enableCrashDetection, defaultValue);
        } catch (IOException e) {
            val = defaultValue;
        }
        sKnownValues.put(gkFileName, Integer.valueOf(val));
        return val;
    }

    @Deprecated
    public static void updateGKFlagFile(Context context, String gkFileName, boolean enableOnNextStart) {
        if (context != null) {
            updateGKValueInt(context, gkFileName, enableOnNextStart ? 1 : 0);
        }
    }

    public static boolean checkAndClearGk(Context context, String gkFilename) {
        return checkAndClearGk(context, gkFilename, false);
    }

    public static boolean checkAndClearGk(Context context, String gkFilename, boolean defaultValue) {
        try {
            return checkIfGkEnabled(context, gkFilename, defaultValue);
        } finally {
            cleanUpGkFile(context, gkFilename);
        }
    }

    @Deprecated
    public static boolean checkIfGkEnabled(Context context, String gkFileName) {
        return checkIfGkEnabled(context, gkFileName, false);
    }

    public static boolean checkIfGkEnabled(Context context, String gkFileName, boolean defaultValue) {
        return getGkValueInt(context, gkFileName, defaultValue ? 1 : 0) == 1;
    }

    @Deprecated
    public static void cleanUpGkFile(Context context, String gkFileName) {
        File gkFile;
        boolean deleteFailed;
        if (context != null) {
            if (isNewLocation(context, gkFileName)) {
                gkFile = getFile(context, gkFileName);
            } else {
                gkFile = getLegacyFile(context, gkFileName);
            }
            try {
                if (gkFile.delete() || !gkFile.exists()) {
                    deleteFailed = false;
                } else {
                    deleteFailed = true;
                }
            } catch (SecurityException e) {
                deleteFailed = true;
            }
            if (deleteFailed) {
                BLog.w("GkBootstrap", "Unable to clean up GK file %s", gkFileName);
            }
        }
    }

    private static int getGkValueIntCore(Context context, String gkFileName, boolean enableCrashDetection, int defaultValue) throws IOException {
        boolean isNewLocation = isNewLocation(context, gkFileName);
        File gkFile = isNewLocation ? getFile(context, gkFileName) : null;
        if (gkFile == null && !getLegacyFile(context, gkFileName).exists()) {
            return defaultValue;
        }
        try {
            DataInputStream dis = new DataInputStream(isNewLocation ? new FileInputStream(gkFile) : context.openFileInput(gkFileName));
            try {
                int val = dis.readInt();
                if (enableCrashDetection && gkFile != null && gkFile.length() >= 8) {
                    checkCrashCount(context, gkFile, dis.readInt());
                }
                if (!isNewLocation) {
                    updateGKValueInt(context, gkFileName, val);
                }
                dis.close();
                return val;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } finally {
            if (!isNewLocation) {
                try {
                    context.deleteFile(gkFileName);
                } catch (SecurityException e) {
                }
            }
        }
        throw th;
    }

    private static void writeGKValueInt(Context context, String gkFileName, int valueOnNextStart, int crashCount) {
        if (context != null) {
            try {
                File gkDir = getDir(context);
                if (gkDir.exists() || gkDir.mkdir()) {
                    File gkFile = getFile(context, gkFileName);
                    if (crashCount != -1) {
                        crashCount = adjustCrashCount(gkFile, crashCount);
                    }
                    try {
                        DataOutputStream dos = new DataOutputStream(new FileOutputStream(gkFile));
                        try {
                            dos.writeInt(valueOnNextStart);
                            if (crashCount != -1) {
                                dos.writeInt(crashCount);
                            }
                            dos.close();
                            return;
                        } catch (Throwable th) {
                            th.addSuppressed(th);
                        }
                    } catch (IOException | SecurityException ex) {
                        BLog.w("GkBootstrap", ex, "Unable to persist GK value to %s", gkFile);
                        return;
                    }
                } else {
                    return;
                }
            } catch (SecurityException e) {
                BLog.e("GkBootstrap", e, "Unable to create %s directory", "GkBootstrap");
                return;
            }
        } else {
            return;
        }
        throw th;
    }

    private static int adjustCrashCount(File gkFile, int crashCount) {
        try {
            if (gkFile.length() < 8) {
                return crashCount;
            }
            DataInputStream dis = new DataInputStream(new FileInputStream(gkFile));
            try {
                dis.skipBytes(4);
                if (dis.readInt() > crashCount) {
                    dis.close();
                    return -1;
                }
                dis.close();
                return crashCount;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
        } catch (IOException | SecurityException ex) {
            BLog.w("GkBootstrap", ex, "Unable to read crash value of %s", gkFile);
            return crashCount;
        }
    }

    private static void checkCrashCount(Context context, File gkFile, int valueCrashCount) throws IOException {
        if (valueCrashCount >= 0) {
            int currentCrashCount = getCurrentCrashCount(context);
            int maxCrashes = sCrashDetectionMaxCrashes;
            if (valueCrashCount + maxCrashes < currentCrashCount) {
                BLog.w("GkBootstrap", "Detected crash loop valueCrashCount=%d currentCrashCount=%d maxCrashes=%d with %s", Integer.valueOf(valueCrashCount), Integer.valueOf(currentCrashCount), Integer.valueOf(maxCrashes), gkFile);
                throw new IOException("Crash Count");
            }
        }
    }

    private static int getCurrentCrashCount(Context context) {
        if (!isCrashDetectionOn()) {
            return -1;
        }
        int currentCrashCount = sCurrentCrashCount;
        if (currentCrashCount > -1) {
            return currentCrashCount;
        }
        synchronized (GkBootstrap.class) {
            int currentCrashCount2 = sCurrentCrashCount;
            if (currentCrashCount2 > -1) {
                return currentCrashCount2;
            }
            int currentCrashCount3 = getGkValueIntNoCrashTracking(context, GKBOOTSTRAP_CRASH_COUNTER, 0);
            sCurrentCrashCount = currentCrashCount3;
            writeGKValueInt(context, GKBOOTSTRAP_CRASH_COUNTER, currentCrashCount3 + 1, -1);
            return currentCrashCount3;
        }
    }

    public static void enableCrashDetection(int maxCrashes) {
        sCrashDetectionMaxCrashes = maxCrashes;
    }

    private static boolean isCrashDetectionOn() {
        return sCrashDetectionMaxCrashes > -1;
    }

    private static File getFile(Context context, String gkFileName) {
        return new File(getDir(context), gkFileName);
    }

    private static File getLegacyFile(Context context, String gkFileName) {
        return new File(context.getFilesDir(), gkFileName);
    }

    private static File getDir(Context context) {
        return new File(context.getFilesDir(), "GkBootstrap");
    }

    private static boolean isNewLocation(Context context, String gkFileName) {
        return getFile(context, gkFileName).exists();
    }
}
