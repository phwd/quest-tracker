package defpackage;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.UUID;

/* renamed from: b20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1773b20 {

    /* renamed from: a  reason: collision with root package name */
    public static String f9509a;
    public static final Object b = new Object();

    public static String a(Context context) {
        synchronized (b) {
            if (f9509a == null) {
                File filesDir = context.getFilesDir();
                if (filesDir == null) {
                    return "n/a";
                }
                File file = new File(filesDir.getParent(), "ACRA-INSTALLATION");
                try {
                    if (!file.exists()) {
                        c(file);
                    }
                    f9509a = b(file);
                } catch (Exception unused) {
                    return "n/a";
                }
            }
            return f9509a;
        }
    }

    public static String b(File file) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            byte[] bArr = new byte[((int) randomAccessFile.length())];
            randomAccessFile.readFully(bArr);
            return new String(bArr);
        } finally {
            randomAccessFile.close();
        }
    }

    public static void c(File file) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(UUID.randomUUID().toString().getBytes());
        } finally {
            fileOutputStream.close();
        }
    }
}
