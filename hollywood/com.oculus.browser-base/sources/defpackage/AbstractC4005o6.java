package defpackage;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.chromium.components.permissions.PermissionUtil;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: o6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4005o6 {
    public static boolean a(WindowAndroid windowAndroid, int[] iArr, AbstractC3834n6 n6Var) {
        if (windowAndroid == null) {
            return false;
        }
        SparseArray sparseArray = new SparseArray();
        for (int i = 0; i < iArr.length; i++) {
            String[] androidPermissionsForContentSetting = PermissionUtil.getAndroidPermissionsForContentSetting(iArr[i]);
            if (androidPermissionsForContentSetting != null) {
                ArrayList arrayList = new ArrayList();
                for (String str : androidPermissionsForContentSetting) {
                    if (!windowAndroid.hasPermission(str)) {
                        arrayList.add(str);
                    }
                }
                if (!arrayList.isEmpty()) {
                    sparseArray.append(iArr[i], (String[]) arrayList.toArray(new String[arrayList.size()]));
                }
            }
        }
        if (sparseArray.size() == 0) {
            return false;
        }
        C3492l6 l6Var = new C3492l6(sparseArray, windowAndroid, iArr, n6Var);
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            Collections.addAll(hashSet, (String[]) sparseArray.valueAt(i2));
        }
        windowAndroid.i((String[]) hashSet.toArray(new String[hashSet.size()]), l6Var);
        return true;
    }

    public static void b(Activity activity, int i, Runnable runnable, Runnable runnable2) {
        C2746gl0 l0 = ((AbstractActivityC0731Ma) ((AbstractC2917hl0) activity)).l0();
        C3663m6 m6Var = new C3663m6(runnable, l0, runnable2);
        View inflate = activity.getLayoutInflater().inflate(R.layout.f42200_resource_name_obfuscated_RES_2131624529, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.text)).setText(i);
        Map c = UH0.c(AbstractC3258jl0.r);
        TH0 th0 = AbstractC3258jl0.f;
        LH0 lh0 = new LH0(null);
        lh0.f8415a = inflate;
        HashMap hashMap = (HashMap) c;
        hashMap.put(th0, lh0);
        QH0 qh0 = AbstractC3258jl0.m;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        hashMap.put(qh0, gh0);
        TH0 th02 = AbstractC3258jl0.g;
        String string = activity.getString(R.string.f53120_resource_name_obfuscated_RES_2131952629);
        LH0 lh02 = new LH0(null);
        lh02.f8415a = string;
        hashMap.put(th02, lh02);
        OH0 oh0 = AbstractC3258jl0.f10235a;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = m6Var;
        hashMap.put(oh0, lh03);
        l0.i(new UH0(c, null), 0, false);
    }
}
