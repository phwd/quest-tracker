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
import androidx.core.view.MotionEventCompat;
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

    public static int findAbiScore(String[] supportedAbis, String abi) {
        for (int i = 0; i < supportedAbis.length; i++) {
            if (supportedAbis[i] != null && abi.equals(supportedAbis[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void deleteOrThrow(File file) throws IOException {
        File folder = file.getParentFile();
        if (folder != null && !folder.canWrite() && !folder.setWritable(true)) {
            Log.e(TAG, "Enable write permission failed: " + folder);
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("Could not delete file " + file);
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

    public static void fallocateIfSupported(FileDescriptor fd, long length) throws IOException {
        if (Build.VERSION.SDK_INT >= 21) {
            LollipopSysdeps.fallocateIfSupported(fd, length);
        }
    }

    public static void dumbDeleteRecursive(File file) throws IOException {
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (fileList != null) {
                for (File entry : fileList) {
                    dumbDeleteRecursive(entry);
                }
            } else {
                return;
            }
        }
        deleteOrThrow(file);
    }

    /* access modifiers changed from: private */
    @DoNotOptimize
    @TargetApi(MotionEventCompat.AXIS_WHEEL)
    public static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            String[] supportedAbis = Build.SUPPORTED_ABIS;
            TreeSet<String> allowedAbis = new TreeSet<>();
            try {
                if (is64Bit()) {
                    allowedAbis.add(MinElf.ISA.AARCH64.toString());
                    allowedAbis.add(MinElf.ISA.X86_64.toString());
                } else {
                    allowedAbis.add(MinElf.ISA.ARM.toString());
                    allowedAbis.add(MinElf.ISA.X86.toString());
                }
                ArrayList<String> compatibleSupportedAbis = new ArrayList<>();
                for (String abi : supportedAbis) {
                    if (allowedAbis.contains(abi)) {
                        compatibleSupportedAbis.add(abi);
                    }
                }
                return (String[]) compatibleSupportedAbis.toArray(new String[compatibleSupportedAbis.size()]);
            } catch (ErrnoException e) {
                Log.e(SysUtil.TAG, String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", Arrays.toString(supportedAbis), Integer.valueOf(e.errno), e.getMessage()));
                return Build.SUPPORTED_ABIS;
            }
        }

        @DoNotOptimize
        public static void fallocateIfSupported(FileDescriptor fd, long length) throws IOException {
            try {
                Os.posix_fallocate(fd, 0, length);
            } catch (ErrnoException ex) {
                if (ex.errno != OsConstants.EOPNOTSUPP && ex.errno != OsConstants.ENOSYS && ex.errno != OsConstants.EINVAL) {
                    throw new IOException(ex.toString(), ex);
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
    @TargetApi(MotionEventCompat.AXIS_BRAKE)
    public static final class MarshmallowSysdeps {
        private MarshmallowSysdeps() {
        }

        @DoNotOptimize
        public static String[] getSupportedAbis() {
            String[] supportedAbis = Build.SUPPORTED_ABIS;
            TreeSet<String> allowedAbis = new TreeSet<>();
            if (is64Bit()) {
                allowedAbis.add(MinElf.ISA.AARCH64.toString());
                allowedAbis.add(MinElf.ISA.X86_64.toString());
            } else {
                allowedAbis.add(MinElf.ISA.ARM.toString());
                allowedAbis.add(MinElf.ISA.X86.toString());
            }
            ArrayList<String> compatibleSupportedAbis = new ArrayList<>();
            for (String abi : supportedAbis) {
                if (allowedAbis.contains(abi)) {
                    compatibleSupportedAbis.add(abi);
                }
            }
            return (String[]) compatibleSupportedAbis.toArray(new String[compatibleSupportedAbis.size()]);
        }

        @DoNotOptimize
        public static boolean is64Bit() {
            return Process.is64Bit();
        }
    }

    static void mkdirOrThrow(File dir) throws IOException {
        if (!dir.mkdirs() && !dir.isDirectory()) {
            throw new IOException("cannot mkdir: " + dir);
        }
    }

    static int copyBytes(RandomAccessFile os, InputStream is, int byteLimit, byte[] buffer) throws IOException {
        int bytesCopied = 0;
        while (bytesCopied < byteLimit) {
            int nrRead = is.read(buffer, 0, Math.min(buffer.length, byteLimit - bytesCopied));
            if (nrRead == -1) {
                break;
            }
            os.write(buffer, 0, nrRead);
            bytesCopied += nrRead;
        }
        return bytesCopied;
    }

    static void fsyncRecursive(File fileName) throws IOException {
        if (fileName.isDirectory()) {
            File[] files = fileName.listFiles();
            if (files == null) {
                throw new IOException("cannot list directory " + fileName);
            }
            for (File file : files) {
                fsyncRecursive(file);
            }
            return;
        } else if (!fileName.getPath().endsWith("_lock")) {
            RandomAccessFile file2 = new RandomAccessFile(fileName, "r");
            try {
                file2.getFD().sync();
                file2.close();
                return;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else {
            return;
        }
        throw th;
    }

    public static byte[] makeApkDepBlock(File apkFile, Context context) throws IOException {
        File apkFile2 = apkFile.getCanonicalFile();
        Parcel parcel = Parcel.obtain();
        try {
            parcel.writeByte((byte) 1);
            parcel.writeString(apkFile2.getPath());
            parcel.writeLong(apkFile2.lastModified());
            parcel.writeInt(getAppVersionCode(context));
            return parcel.marshall();
        } finally {
            parcel.recycle();
        }
    }

    public static int getAppVersionCode(Context context) {
        PackageManager pm = context.getPackageManager();
        if (pm == null) {
            return 0;
        }
        try {
            return pm.getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException | RuntimeException e) {
            return 0;
        }
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
