package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import android.util.Log;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.util.FileUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
public class FileManager {
    public static File mCacheDir;
    public static boolean sInitialized;

    @DoNotStrip
    public static native void nativeInitialize();

    @DoNotStrip
    public static String getCacheDirectory() {
        return mCacheDir.toString();
    }

    public static synchronized File getFileFromPathWithOptionalScheme(String str) {
        File file;
        synchronized (FileManager.class) {
            if (str.startsWith("file://")) {
                try {
                    file = new File(new URI(str));
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            } else if (str.startsWith("cache://")) {
                file = new File(mCacheDir, str.substring(8));
            } else {
                file = new File(str);
            }
        }
        return file;
    }

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    public static boolean copyFile(String str, String str2) {
        File fileFromPathWithOptionalScheme = getFileFromPathWithOptionalScheme(str);
        File fileFromPathWithOptionalScheme2 = getFileFromPathWithOptionalScheme(str2);
        if (fileFromPathWithOptionalScheme2.exists()) {
            return false;
        }
        File parentFile = getFileFromPathWithOptionalScheme(str2).getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(fileFromPathWithOptionalScheme);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileFromPathWithOptionalScheme2);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read >= 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileOutputStream.close();
                            fileInputStream.close();
                            return true;
                        }
                    }
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
            }
        } catch (IOException unused3) {
            return false;
        }
        throw th;
        throw th;
    }

    @DoNotStrip
    public static boolean createDirectory(String str) {
        File fileFromPathWithOptionalScheme = getFileFromPathWithOptionalScheme(str);
        if (!fileFromPathWithOptionalScheme.exists() || !fileFromPathWithOptionalScheme.isDirectory()) {
            return fileFromPathWithOptionalScheme.mkdirs();
        }
        return true;
    }

    @DoNotStrip
    public static boolean deleteItem(String str) {
        return deleteItemRecursive(getFileFromPathWithOptionalScheme(str));
    }

    public static boolean deleteItemRecursive(File file) {
        boolean delete;
        if (!file.isDirectory()) {
            return file.delete();
        }
        File[] listFiles = file.listFiles();
        boolean z = true;
        for (File file2 : listFiles) {
            if (file2.isDirectory()) {
                if (z) {
                    delete = deleteItemRecursive(file2);
                }
                z = false;
            } else {
                if (z) {
                    delete = file2.delete();
                }
                z = false;
            }
            z = true;
            if (delete) {
            }
            z = false;
        }
        if (!file.delete() || !z) {
            return false;
        }
        return true;
    }

    @DoNotStrip
    public static boolean itemExists(String str) {
        return getFileFromPathWithOptionalScheme(str).exists();
    }

    @DoNotStrip
    @Nullable
    public static String[] listDirectory(String str) {
        File[] listFiles = getFileFromPathWithOptionalScheme(str).listFiles();
        if (listFiles == null) {
            return null;
        }
        int length = listFiles.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = listFiles[i].getAbsolutePath();
        }
        return strArr;
    }

    @DoNotStrip
    public static boolean moveFile(String str, String str2) {
        File fileFromPathWithOptionalScheme = getFileFromPathWithOptionalScheme(str);
        File fileFromPathWithOptionalScheme2 = getFileFromPathWithOptionalScheme(str2);
        if (fileFromPathWithOptionalScheme.equals(fileFromPathWithOptionalScheme2) || fileFromPathWithOptionalScheme.renameTo(fileFromPathWithOptionalScheme2)) {
            return true;
        }
        if (copyFile(str, str2)) {
            return fileFromPathWithOptionalScheme.delete();
        }
        return false;
    }

    @DoNotStrip
    @Nullable
    public static byte[] readFile(String str) {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(getFileFromPathWithOptionalScheme(str), "r");
            try {
                long length = randomAccessFile.length();
                if (length <= 2147483647L) {
                    byte[] bArr = new byte[((int) length)];
                    randomAccessFile.readFully(bArr);
                    randomAccessFile.close();
                    return bArr;
                }
                Log.e(FileUtils.TAG, "Cannot read more than 2GB into an array");
                throw new IOException("Cannot read more than 2GB into an array");
            } catch (Throwable unused) {
            }
            throw th;
        } catch (IOException unused2) {
            return null;
        }
    }

    @DoNotStrip
    public static boolean writeDataToFile(byte[] bArr, String str) {
        File parentFile = getFileFromPathWithOptionalScheme(str).getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        File fileFromPathWithOptionalScheme = getFileFromPathWithOptionalScheme(str);
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileFromPathWithOptionalScheme);
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int read = byteArrayInputStream.read(bArr2);
                        if (read >= 0) {
                            fileOutputStream.write(bArr2, 0, read);
                        } else {
                            fileOutputStream.close();
                            byteArrayInputStream.close();
                            return true;
                        }
                    }
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } catch (IOException unused3) {
            return false;
        }
    }
}
