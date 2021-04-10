package X;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Process;
import com.facebook.soloader.DoNotOptimize;
import java.util.ArrayList;
import java.util.TreeSet;

@DoNotOptimize
@TargetApi(23)
/* renamed from: X.0l3  reason: invalid class name */
public final class AnonymousClass0l3 {
    @DoNotOptimize
    public static String[] A01() {
        EnumC03060ku r0;
        String[] strArr = Build.SUPPORTED_ABIS;
        TreeSet treeSet = new TreeSet();
        if (A00()) {
            treeSet.add(EnumC03060ku.AARCH64.toString());
            r0 = EnumC03060ku.X86_64;
        } else {
            treeSet.add(EnumC03060ku.ARM.toString());
            r0 = EnumC03060ku.X86;
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
