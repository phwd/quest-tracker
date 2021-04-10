package defpackage;

import android.app.Activity;
import android.util.SparseArray;
import com.oculus.browser.R;
import java.util.HashSet;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: l6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3492l6 implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SparseArray f10323a;
    public final /* synthetic */ WindowAndroid b;
    public final /* synthetic */ int[] c;
    public final /* synthetic */ AbstractC3834n6 d;

    public C3492l6(SparseArray sparseArray, WindowAndroid windowAndroid, int[] iArr, AbstractC3834n6 n6Var) {
        this.f10323a = sparseArray;
        this.b = windowAndroid;
        this.c = iArr;
        this.d = n6Var;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        int i;
        String[] strArr2;
        HashSet hashSet = new HashSet();
        int i2 = 0;
        boolean z = true;
        while (true) {
            i = -1;
            if (i2 >= iArr.length) {
                break;
            }
            if (iArr[i2] == -1) {
                SparseArray sparseArray = this.f10323a;
                String str = strArr[i2];
                int i3 = 0;
                while (true) {
                    if (i3 >= sparseArray.size()) {
                        break;
                    }
                    for (String str2 : (String[]) sparseArray.valueAt(i3)) {
                        if (str.equals(str2)) {
                            i = sparseArray.keyAt(i3);
                            break;
                        }
                    }
                    i3++;
                }
                hashSet.add(Integer.valueOf(i));
                if (!this.b.canRequestPermission(strArr[i2])) {
                    z = false;
                }
            }
            i2++;
        }
        Activity activity = (Activity) this.b.s0().get();
        if (z && !hashSet.isEmpty() && activity != null) {
            if (hashSet.size() == 2 && hashSet.contains(9) && hashSet.contains(10)) {
                i = R.string.f53100_resource_name_obfuscated_RES_2131952627;
            } else if (hashSet.size() == 1) {
                if (hashSet.contains(5)) {
                    i = R.string.f53090_resource_name_obfuscated_RES_2131952626;
                } else if (hashSet.contains(9)) {
                    i = R.string.f53110_resource_name_obfuscated_RES_2131952628;
                } else if (hashSet.contains(10)) {
                    i = R.string.f53080_resource_name_obfuscated_RES_2131952625;
                } else if (hashSet.contains(58)) {
                    i = R.string.f53070_resource_name_obfuscated_RES_2131952624;
                }
            }
            WindowAndroid windowAndroid = this.b;
            int[] iArr2 = this.c;
            AbstractC3834n6 n6Var = this.d;
            RunnableC3150j6 j6Var = new RunnableC3150j6(windowAndroid, iArr2, n6Var);
            n6Var.getClass();
            AbstractC4005o6.b(activity, i, j6Var, new RunnableC3321k6(n6Var));
        } else if (hashSet.isEmpty()) {
            this.d.g();
        } else {
            this.d.e();
        }
    }
}
