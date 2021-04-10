package defpackage;

import java.io.File;

/* renamed from: bx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1929bx1 {
    public static long a(File file) {
        if (file == null) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        try {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return 0;
            }
            long j = 0;
            for (File file2 : listFiles) {
                j += a(file2);
            }
            return j;
        } catch (SecurityException unused) {
            return 0;
        }
    }
}
