package com.facebook.soloader;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Parcel;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.facebook.soloader.MinElf;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public final class SysUtil {
    private static final byte APK_SIGNATURE_VERSION = 1;
    private static final String TAG = "SysUtil";

    public static int findAbiScore(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null && str.equals(strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteOrThrow(File file) throws IOException {
        if (!file.delete()) {
            throw new IOException("could not delete file " + file);
        }
    }

    public static String[] getSupportedAbis() {
        if (Build.VERSION.SDK_INT >= 23) {
            return MarshmallowSysdeps.getSupportedAbis();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return LollipopSysdeps.getSupportedAbis();
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            LollipopSysdeps.fallocateIfSupported(fileDescriptor, j);
        }
    }

    public static void dumbDeleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    dumbDeleteRecursive(file2);
                }
            } else {
                return;
            }
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("could not delete: " + file);
        }
    }

    /* access modifiers changed from: private */
    @DoNotOptimize
    @TargetApi(21)
    public static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            try {
                if (is64Bit()) {
                    treeSet.add(MinElf.ISA.AARCH64.toString());
                    treeSet.add(MinElf.ISA.X86_64.toString());
                } else {
                    treeSet.add(MinElf.ISA.ARM.toString());
                    treeSet.add(MinElf.ISA.X86.toString());
                }
                ArrayList arrayList = new ArrayList();
                for (String str : strArr) {
                    if (treeSet.contains(str)) {
                        arrayList.add(str);
                    }
                }
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            } catch (ErrnoException e) {
                Log.e(SysUtil.TAG, String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", Arrays.toString(strArr), Integer.valueOf(e.errno), e.getMessage()));
                return Build.SUPPORTED_ABIS;
            }
        }

        @DoNotOptimize
        public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) throws IOException {
            try {
                Os.posix_fallocate(fileDescriptor, 0, j);
            } catch (ErrnoException e) {
                if (e.errno != OsConstants.EOPNOTSUPP && e.errno != OsConstants.ENOSYS && e.errno != OsConstants.EINVAL) {
                    throw new IOException(e.toString(), e);
                }
            }
        }

        @DoNotOptimize
        public static boolean is64Bit() throws ErrnoException {
            return Os.readlink("/proc/self/exe").contains("64");
        }
    }

    /* access modifiers changed from: private */
    @DoNotOptimize
    @TargetApi(23)
    public static final class MarshmallowSysdeps {
        private MarshmallowSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            if (is64Bit()) {
                treeSet.add(MinElf.ISA.AARCH64.toString());
                treeSet.add(MinElf.ISA.X86_64.toString());
            } else {
                treeSet.add(MinElf.ISA.ARM.toString());
                treeSet.add(MinElf.ISA.X86.toString());
            }
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (treeSet.contains(str)) {
                    arrayList.add(str);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        @DoNotOptimize
        public static boolean is64Bit() {
            return Process.is64Bit();
        }
    }

    static void mkdirOrThrow(File file) throws IOException {
        if (!file.mkdirs() && !file.isDirectory()) {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    static int copyBytes(RandomAccessFile randomAccessFile, InputStream inputStream, int i, byte[] bArr) throws IOException {
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, 0, Math.min(bArr.length, i - i2));
            if (read == -1) {
                break;
            }
            randomAccessFile.write(bArr, 0, read);
            i2 += read;
        }
        return i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        if (r3 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0059, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005a, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void fsyncRecursive(java.io.File r3) throws java.io.IOException {
        /*
            boolean r0 = r3.isDirectory()
            if (r0 == 0) goto L_0x002f
            java.io.File[] r0 = r3.listFiles()
            if (r0 == 0) goto L_0x0018
            r3 = 0
        L_0x000d:
            int r1 = r0.length
            if (r3 >= r1) goto L_0x004e
            r1 = r0[r3]
            fsyncRecursive(r1)
            int r3 = r3 + 1
            goto L_0x000d
        L_0x0018:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "cannot list directory "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L_0x002f:
            java.lang.String r0 = r3.getPath()
            java.lang.String r1 = "_lock"
            boolean r0 = r0.endsWith(r1)
            if (r0 == 0) goto L_0x003c
            goto L_0x004e
        L_0x003c:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile
            java.lang.String r1 = "r"
            r0.<init>(r3, r1)
            r3 = 0
            java.io.FileDescriptor r1 = r0.getFD()     // Catch:{ Throwable -> 0x0051 }
            r1.sync()     // Catch:{ Throwable -> 0x0051 }
            r0.close()
        L_0x004e:
            return
        L_0x004f:
            r1 = move-exception
            goto L_0x0053
        L_0x0051:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x004f }
        L_0x0053:
            if (r3 == 0) goto L_0x005e
            r0.close()     // Catch:{ Throwable -> 0x0059 }
            goto L_0x0061
        L_0x0059:
            r0 = move-exception
            r3.addSuppressed(r0)
            goto L_0x0061
        L_0x005e:
            r0.close()
        L_0x0061:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.SysUtil.fsyncRecursive(java.io.File):void");
    }

    public static byte[] makeApkDepBlock(File file, Context context) throws IOException {
        File canonicalFile = file.getCanonicalFile();
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeByte((byte) 1);
            obtain.writeString(canonicalFile.getPath());
            obtain.writeLong(canonicalFile.lastModified());
            obtain.writeInt(getAppVersionCode(context));
            return obtain.marshall();
        } finally {
            obtain.recycle();
        }
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            }
        }
        return 0;
    }

    @SuppressLint({"CatchGeneralException"})
    public static boolean is64Bit() {
        if (Build.VERSION.SDK_INT >= 23) {
            return MarshmallowSysdeps.is64Bit();
        }
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        try {
            return LollipopSysdeps.is64Bit();
        } catch (Exception e) {
            Log.e(TAG, String.format("Could not read /proc/self/exe. Err msg: %s", e.getMessage()));
            return false;
        }
    }
}
