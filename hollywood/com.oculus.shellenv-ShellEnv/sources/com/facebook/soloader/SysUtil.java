package com.facebook.soloader;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.facebook.soloader.g;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class SysUtil {
    private static SysUtil a;
    private final boolean b;
    private final String c;

    /* access modifiers changed from: private */
    public static final class LollipopSysdeps {
        private LollipopSysdeps() {
        }

        public static void fallocateIfSupported(FileDescriptor fileDescriptor, long j) {
            try {
                Os.posix_fallocate(fileDescriptor, 0, j);
            } catch (ErrnoException e) {
                if (e.errno != OsConstants.EOPNOTSUPP && e.errno != OsConstants.ENOSYS && e.errno != OsConstants.EINVAL) {
                    throw new IOException(e.toString(), e);
                }
            }
        }

        public static String[] getSupportedAbis() {
            String bVar;
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            try {
                if (is64Bit()) {
                    treeSet.add(g.b.d.toString());
                    bVar = g.b.c.toString();
                } else {
                    treeSet.add(g.b.b.toString());
                    bVar = g.b.a.toString();
                }
                treeSet.add(bVar);
                ArrayList arrayList = new ArrayList();
                for (String str : strArr) {
                    if (treeSet.contains(str)) {
                        arrayList.add(str);
                    }
                }
                return (String[]) arrayList.toArray(new String[arrayList.size()]);
            } catch (ErrnoException e) {
                Log.e("SysUtil", String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", Arrays.toString(strArr), Integer.valueOf(e.errno), e.getMessage()));
                return Build.SUPPORTED_ABIS;
            }
        }

        public static boolean is64Bit() {
            return Os.readlink("/proc/self/exe").contains("64");
        }
    }

    static final class a {
        public static String[] a() {
            g.b bVar;
            String[] strArr = Build.SUPPORTED_ABIS;
            TreeSet treeSet = new TreeSet();
            if (Process.is64Bit()) {
                treeSet.add(g.b.d.toString());
                bVar = g.b.c;
            } else {
                treeSet.add(g.b.b.toString());
                bVar = g.b.a;
            }
            treeSet.add(bVar.toString());
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (treeSet.contains(str)) {
                    arrayList.add(str);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        }
    }

    private SysUtil(boolean z, String str) {
        this.b = z;
        this.c = String.format("LifecycleEvents (%s)", str);
    }

    public static int a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            try {
                return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException | RuntimeException unused) {
            }
        }
        return 0;
    }

    static int a(RandomAccessFile randomAccessFile, InputStream inputStream, int i, byte[] bArr) {
        int read;
        int i2 = 0;
        while (i2 < Integer.MAX_VALUE && (read = inputStream.read(bArr, 0, Math.min(bArr.length, Integer.MAX_VALUE - i2))) != -1) {
            randomAccessFile.write(bArr, 0, read);
            i2 += read;
        }
        return i2;
    }

    public static int a(String[] strArr, String str) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null && str.equals(strArr[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    a(file2);
                }
            } else {
                return;
            }
        }
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.canWrite() && !parentFile.setWritable(true)) {
            Log.e("SysUtil", "Enable write permission failed: " + parentFile);
        }
        if (!file.delete() && file.exists()) {
            throw new IOException("Could not delete file " + file);
        }
    }

    private void a(String str) {
        Log.i(this.c, str);
    }

    public static void a(boolean z, String str) {
        if (a == null) {
            a = new SysUtil(z, str);
        }
    }

    public static String[] a() {
        if (Build.VERSION.SDK_INT >= 23) {
            return a.a();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return LollipopSysdeps.getSupportedAbis();
        }
        return new String[]{Build.CPU_ABI, Build.CPU_ABI2};
    }

    static void b(File file) {
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    b(file2);
                }
                return;
            }
            throw new IOException("cannot list directory " + file);
        } else if (!file.getPath().endsWith("_lock")) {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            try {
                randomAccessFile.getFD().sync();
                randomAccessFile.close();
                return;
            } catch (Throwable unused) {
            }
        } else {
            return;
        }
        throw th;
    }

    public static boolean b() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        try {
            return LollipopSysdeps.is64Bit();
        } catch (Exception e) {
            Log.e("SysUtil", String.format("Could not read /proc/self/exe. Err msg: %s", e.getMessage()));
            return false;
        }
    }

    public static SysUtil c() {
        return a;
    }

    public void a(String str, boolean z) {
        if (this.b) {
            Object[] objArr = new Object[2];
            objArr[0] = z ? "sent" : "failed to send";
            objArr[1] = str;
            a(String.format("ShellIPCManager - lifecycle message %s (state: %s)", objArr));
        }
    }

    public void a(boolean z) {
        if (this.b) {
            a(String.format("ShellIPCManager - service disconnected (%s)", z ? "unexpected" : "expected"));
        }
    }

    public void d() {
        if (this.b) {
            a("Activity - onCreate");
        }
    }

    public void e() {
        if (this.b) {
            a("Activity - onResume");
        }
    }

    public void f() {
        if (this.b) {
            a("ShellIPCManager - startServiceConnection");
        }
    }

    public void g() {
        if (this.b) {
            a("ShellIPCManager - serviceConnected");
        }
    }

    public void h() {
        if (this.b) {
            a("ShellIPCManager - disconnectService sent");
        }
    }

    public void i() {
        if (this.b) {
            a("ShellIPCManager - shutdownService");
        }
    }

    public void j() {
        if (this.b) {
            a("Activity - onPause");
        }
    }

    public void k() {
        if (this.b) {
            a("Activity - onDestroy");
        }
    }
}
