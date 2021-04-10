package X;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Process;
import com.facebook.soloader.DoNotOptimize;
import java.util.ArrayList;
import java.util.TreeSet;

@DoNotOptimize
@TargetApi(23)
/* renamed from: X.0cN  reason: invalid class name and case insensitive filesystem */
public final class C03180cN {
    @DoNotOptimize
    public static String[] A01() {
        EnumC03130cE r0;
        String[] strArr = Build.SUPPORTED_ABIS;
        TreeSet treeSet = new TreeSet();
        if (A00()) {
            treeSet.add(EnumC03130cE.AARCH64.toString());
            r0 = EnumC03130cE.X86_64;
        } else {
            treeSet.add(EnumC03130cE.ARM.toString());
            r0 = EnumC03130cE.X86;
        }
        treeSet.add(r0.toString());
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (treeSet.contains(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @DoNotOptimize
    public static boolean A00() {
        return Process.is64Bit();
    }
}
