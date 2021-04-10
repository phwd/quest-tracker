package defpackage;

import android.util.Log;
import dalvik.system.DexClassLoader;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;

/* renamed from: KE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class KE {
    public static File a(File file) {
        File file2;
        try {
            File parentFile = file.getParentFile();
            file2 = new File(parentFile, "oat/" + JE.a());
        } catch (NoSuchMethodException unused) {
            file2 = null;
        }
        if (file2 != null) {
            if (!file2.exists()) {
                if (file2.mkdirs()) {
                    File parentFile2 = file.getParentFile();
                    File file3 = file2;
                    while (file3 != null && !parentFile2.equals(file3)) {
                        if (file3.setExecutable(true, false)) {
                            file3 = file3.getParentFile();
                        } else {
                            StringBuilder i = AbstractC2531fV.i("Failed to make odex directory world traversable: ");
                            i.append(file3.getAbsolutePath());
                            throw new IOException(i.toString());
                        }
                    }
                } else {
                    throw new IOException("Failed to create odex cache directory in data directory.");
                }
            }
            return file2;
        }
        throw new IOException("Failed to create odex cache directory. Could not determine odex directory.");
    }

    public static boolean b(File file) {
        File file2;
        if (!file.exists()) {
            StringBuilder i = AbstractC2531fV.i("Dex file does not exist! ");
            i.append(file.getAbsolutePath());
            Log.e("DexOptimzer", i.toString());
            return false;
        }
        try {
            if (!DexFile.isDexOptNeeded(file.getAbsolutePath())) {
                return true;
            }
        } catch (Exception e) {
            StringBuilder i2 = AbstractC2531fV.i("Failed to check optimization status: ");
            i2.append(e.toString());
            i2.append(" : ");
            i2.append(e.getMessage());
            Log.e("DexOptimzer", i2.toString());
        }
        try {
            File a2 = a(file);
            if (a2.equals(file.getParentFile())) {
                file2 = new File(a2, "optimized");
                if (!file2.exists() && !file2.mkdirs()) {
                    return false;
                }
            } else {
                file2 = a2;
            }
            new DexClassLoader(file.getAbsolutePath(), file2.getAbsolutePath(), null, ClassLoader.getSystemClassLoader());
            File file3 = new File(file2, file.getName());
            if (!file3.exists()) {
                Log.e("DexOptimzer", "Failed to create dex.");
                return false;
            }
            String name = file3.getName();
            int lastIndexOf = name.lastIndexOf(".");
            StringBuilder sb = new StringBuilder(lastIndexOf + 4);
            sb.append((CharSequence) name, 0, lastIndexOf + 1);
            sb.append("odex");
            File file4 = new File(a2, sb.toString());
            if (!file3.renameTo(file4)) {
                Log.e("DexOptimzer", "Failed to rename optimized file.");
                return false;
            } else if (file4.setReadable(true, false)) {
                return true;
            } else {
                Log.e("DexOptimzer", "Failed to make odex world readable.");
                return false;
            }
        } catch (IOException e2) {
            StringBuilder i3 = AbstractC2531fV.i("Failed to create odex directory! ");
            i3.append(e2.getMessage());
            Log.e("DexOptimzer", i3.toString());
            return false;
        }
    }
}
