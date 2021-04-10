package X;

import android.os.Build;
import android.os.Process;
import java.util.ArrayList;
import java.util.TreeSet;

public final class KM {
    public static String[] A01() {
        KH kh;
        String[] strArr = Build.SUPPORTED_ABIS;
        TreeSet treeSet = new TreeSet();
        if (A00()) {
            treeSet.add(KH.AARCH64.toString());
            kh = KH.X86_64;
        } else {
            treeSet.add(KH.ARM.toString());
            kh = KH.X86;
        }
        treeSet.add(kh.toString());
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (treeSet.contains(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static boolean A00() {
        return Process.is64Bit();
    }
}
