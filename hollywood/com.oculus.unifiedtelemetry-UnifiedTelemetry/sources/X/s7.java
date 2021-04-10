package X;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Process;
import com.facebook.soloader.DoNotOptimize;
import java.util.ArrayList;
import java.util.TreeSet;

@DoNotOptimize
@TargetApi(23)
public final class s7 {
    @DoNotOptimize
    public static String[] A01() {
        EnumC0504sX sXVar;
        String[] strArr = Build.SUPPORTED_ABIS;
        TreeSet treeSet = new TreeSet();
        if (A00()) {
            treeSet.add(EnumC0504sX.AARCH64.toString());
            sXVar = EnumC0504sX.X86_64;
        } else {
            treeSet.add(EnumC0504sX.ARM.toString());
            sXVar = EnumC0504sX.X86;
        }
        treeSet.add(sXVar.toString());
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
