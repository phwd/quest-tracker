package com.facebook.soloader;

import X.EnumC03130cE;
import android.annotation.TargetApi;
import android.os.Build;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

@DoNotOptimize
@TargetApi(21)
public final class SysUtil$LollipopSysdeps {
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
    public static String[] getSupportedAbis() {
        String[] strArr = Build.SUPPORTED_ABIS;
        TreeSet treeSet = new TreeSet();
        try {
            if (is64Bit()) {
                treeSet.add(EnumC03130cE.AARCH64.toString());
                treeSet.add(EnumC03130cE.X86_64.toString());
            } else {
                treeSet.add(EnumC03130cE.ARM.toString());
                treeSet.add(EnumC03130cE.X86.toString());
            }
            ArrayList arrayList = new ArrayList();
            for (String str : strArr) {
                if (treeSet.contains(str)) {
                    arrayList.add(str);
                }
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } catch (ErrnoException e) {
            Log.e("SysUtil", String.format("Could not read /proc/self/exe. Falling back to default ABI list: %s. errno: %d Err msg: %s", Arrays.toString(strArr), Integer.valueOf(e.errno), e.getMessage()));
            String[] strArr2 = Build.SUPPORTED_ABIS;
            return strArr;
        }
    }

    @DoNotOptimize
    public static boolean is64Bit() throws ErrnoException {
        return Os.readlink("/proc/self/exe").contains("64");
    }
}
