package defpackage;

import android.content.Context;
import android.net.Uri;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.chromium.base.ContentUriUtils;

/* renamed from: kQ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3375kQ {

    /* renamed from: a  reason: collision with root package name */
    public static WT f10275a = new C3204jQ();

    public static void a(InputStream inputStream, File file) {
        File file2 = new File(file.getPath() + ".tmp");
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        try {
            AbstractC1220Ua0.d("FileUtils", "Writing to %s", file);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            fileOutputStream.close();
            if (!file2.renameTo(file)) {
                throw new IOException();
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static boolean b(Context context, String str, File file) {
        try {
            InputStream open = context.getAssets().open(str);
            try {
                a(open, file);
                if (open != null) {
                    open.close();
                }
                return true;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
        } catch (IOException unused) {
            return false;
        }
    }

    public static String c(String str) {
        int lastIndexOf = str.lastIndexOf(47);
        int lastIndexOf2 = str.lastIndexOf(46);
        if (lastIndexOf >= lastIndexOf2) {
            return "";
        }
        return str.substring(lastIndexOf2 + 1).toLowerCase(Locale.US);
    }

    public static Uri d(File file) {
        Uri uri;
        try {
            uri = ContentUriUtils.b(file);
        } catch (IllegalArgumentException e) {
            AbstractC1220Ua0.a("FileUtils", "Could not create content uri: " + e, new Object[0]);
            uri = null;
        }
        return uri == null ? Uri.fromFile(file) : uri;
    }

    public static boolean e(File file, WT wt) {
        File[] listFiles;
        if (!file.exists()) {
            file.delete();
            return true;
        } else if (wt != null && !((Boolean) wt.apply(file.getPath())).booleanValue()) {
            return true;
        } else {
            if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File file2 : listFiles) {
                    e(file2, wt);
                }
            }
            boolean delete = file.delete();
            if (!delete) {
                AbstractC1220Ua0.a("FileUtils", "Failed to delete: %s", file);
            }
            return delete;
        }
    }
}
