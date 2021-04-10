package X;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Parcel;
import android.util.Log;
import java.io.File;
import java.io.IOException;

/* renamed from: X.0l4  reason: invalid class name */
public final class AnonymousClass0l4 {
    public static void A00(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    A00(file2);
                }
            } else {
                return;
            }
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.canWrite() && !parentFile.setWritable(true)) {
            StringBuilder sb = new StringBuilder("Enable write permission failed: ");
            sb.append(parentFile);
            Log.e("SysUtil", sb.toString());
        }
        if (!file.delete() && file.exists()) {
            StringBuilder sb2 = new StringBuilder("Could not delete file ");
            sb2.append(file);
            throw new IOException(sb2.toString());
        }
    }

    public static byte[] A01(File file, Context context) throws IOException {
        int i;
        File canonicalFile = file.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 1);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                try {
                    i = packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
                } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
                }
                obtain.writeInt(i);
                return obtain.marshall();
            }
            i = 0;
            obtain.writeInt(i);
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }
}
