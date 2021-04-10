package com.facebook.secure.fileprovider;

import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import androidx.annotation.VisibleForTesting;
import com.facebook.secure.fileprovider.common.FileStatHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

public class FileUtil {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final String FD_PATH = "/proc/self/fd";

    public static String getExtension(File file) {
        int lastIndexOf = file.getName().lastIndexOf(46);
        if (lastIndexOf == -1) {
            return "";
        }
        return file.getName().substring(lastIndexOf + 1);
    }

    public static String getBaseName(File file) {
        int lastIndexOf = file.getName().lastIndexOf(46);
        if (lastIndexOf == -1) {
            return file.getName();
        }
        return file.getName().substring(0, lastIndexOf);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0023 A[SYNTHETIC, Splitter:B:20:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void copy(java.io.File r2, java.io.File r3) throws java.io.IOException {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x001f }
            r1.<init>(r2)     // Catch:{ all -> 0x001f }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x001d }
            r2.<init>(r3)     // Catch:{ all -> 0x001d }
            copy(r1, r2)     // Catch:{ all -> 0x001a }
            r1.close()     // Catch:{ all -> 0x0015 }
            r2.close()
            return
        L_0x0015:
            r3 = move-exception
            r2.close()
            throw r3
        L_0x001a:
            r3 = move-exception
            r0 = r2
            goto L_0x0021
        L_0x001d:
            r3 = move-exception
            goto L_0x0021
        L_0x001f:
            r3 = move-exception
            r1 = r0
        L_0x0021:
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ all -> 0x0027 }
            goto L_0x002e
        L_0x0027:
            r2 = move-exception
            if (r0 == 0) goto L_0x002d
            r0.close()
        L_0x002d:
            throw r2
        L_0x002e:
            if (r0 == 0) goto L_0x0033
            r0.close()
        L_0x0033:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.secure.fileprovider.FileUtil.copy(java.io.File, java.io.File):void");
    }

    public static void copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static File buildPath(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                String trim = str.trim();
                if (trim.trim().length() != 0) {
                    file = new File(file, trim);
                }
            }
        }
        return file;
    }

    public static int modeToMode(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    public static boolean isFileOnSdCard(@Nullable ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        if (parcelFileDescriptor == null) {
            return false;
        }
        boolean z = FileStatHelper.statOpenFile(parcelFileDescriptor).device == getSdCardDevice();
        if (z) {
            return z;
        }
        try {
            return isFileOnSdCard(new File(FD_PATH, String.format("%d", Integer.valueOf(FileStatHelper.getFDFromPFD(parcelFileDescriptor)))).getCanonicalPath());
        } catch (FileNotFoundException unused) {
            return false;
        }
    }

    public static boolean isFileOnSdCard(@Nullable String str) throws IOException {
        boolean z = false;
        if (str == null) {
            return false;
        }
        ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(str), 268435456);
        try {
            if (FileStatHelper.statOpenFile(open).device == getSdCardDevice()) {
                z = true;
            }
            return z;
        } finally {
            open.close();
        }
    }

    public static boolean isFileOwnedByRunningApplication(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        return Process.myUid() == FileStatHelper.statOpenFile(parcelFileDescriptor).ownerUid;
    }

    @VisibleForTesting
    protected static ParcelFileDescriptor getParcelFDFromFile(File file) throws FileNotFoundException {
        return ParcelFileDescriptor.open(file, 805306368);
    }

    @VisibleForTesting
    protected static void createNewFile(File file) throws IOException {
        createNewFile(file, false);
    }

    @VisibleForTesting
    protected static void createNewDirectory(File file) throws IOException {
        createNewFile(file, true);
    }

    private static void createNewFile(File file, boolean z) throws IOException {
        do {
            if (z) {
                file.mkdirs();
            } else {
                file.createNewFile();
            }
        } while (!file.exists());
    }

    private static long getSdCardDevice() throws IOException {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return -1;
        }
        ParcelFileDescriptor open = ParcelFileDescriptor.open(externalStorageDirectory, 268435456);
        try {
            return FileStatHelper.statOpenFile(open).device;
        } finally {
            open.close();
        }
    }
}
