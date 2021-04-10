package org.chromium.chrome.browser.keyboard_accessory;

import J.N;
import android.app.Activity;
import android.util.SparseArray;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.keyboard_accessory.data.UserInfoField;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ManualFillingComponentBridge {

    /* renamed from: a  reason: collision with root package name */
    public final SparseArray f10687a = new SparseArray();
    public C1992cI0 b;
    public final WindowAndroid c;
    public long d;
    public final C2548fc0 e = new C2548fc0(this);

    public ManualFillingComponentBridge(long j, WindowAndroid windowAndroid) {
        this.d = j;
        this.c = windowAndroid;
    }

    public static ManualFillingComponentBridge create(long j, WindowAndroid windowAndroid) {
        return new ManualFillingComponentBridge(j, windowAndroid);
    }

    public static Object createAccessorySheetData(int i, String str, String str2) {
        return new C2465f50(i, str, str2);
    }

    public final C3402kc0 a() {
        ChromeActivity chromeActivity = (ChromeActivity) this.c.s0().get();
        if (chromeActivity == null) {
            return null;
        }
        C3402kc0 kc0 = chromeActivity.S0;
        kc0.b.b(this.e);
        return chromeActivity.S0;
    }

    public final void addFieldToUserInfo(Object obj, int i, String str, String str2, String str3, boolean z, boolean z2) {
        ((C3319k50) obj).b.add(new UserInfoField(str, str2, str3, z, z2 ? new C3061ic0(this, i) : null));
    }

    public final void addFooterCommandToAccessorySheetData(Object obj, String str, int i) {
        ((C2465f50) obj).f.add(new C2807h50(str, new C3231jc0(this, i)));
    }

    public final void addOptionToggleToAccessorySheetData(Object obj, String str, boolean z, int i) {
        ((C2465f50) obj).d = new C2978i50(str, z, i, new C2890hc0(this, i));
    }

    public final Object addUserInfoToAccessorySheetData(Object obj, String str, boolean z) {
        C3319k50 k50 = new C3319k50(str, z);
        ((C2465f50) obj).e.add(k50);
        return k50;
    }

    public final void b(int i) {
        N.MmIaCnPe(this.d, this, i);
    }

    public final void c() {
        AbstractC3364kK0.g("KeyboardAccessory.AccessoryActionSelected", 0, 8);
        N.MmIaCnPe(this.d, this, 0);
    }

    public final void closeAccessorySheet() {
        if (a() != null) {
            a().f10290a.e0();
        }
    }

    public final void destroy() {
        if (a() != null) {
            C3402kc0 a2 = a();
            a2.b.b(this.e);
        }
        for (int i = 0; i < this.f10687a.size(); i++) {
            ((C1992cI0) this.f10687a.valueAt(i)).b(null);
        }
        this.d = 0;
    }

    public void hide() {
        if (a() != null) {
            a().b();
        }
    }

    public final void onAutomaticGenerationStatusChanged(boolean z) {
        C2636g50[] g50Arr;
        Activity activity = (Activity) this.c.s0().get();
        if (!z || activity == null) {
            g50Arr = new C2636g50[0];
        } else {
            g50Arr = new C2636g50[]{new C2636g50(activity.getString(R.string.f57900_resource_name_obfuscated_RES_2131953107), 0, new C2719gc0(this))};
        }
        if (this.b == null && a() != null) {
            this.b = new C1992cI0(0);
            C3402kc0 a2 = a();
            C1992cI0 ci0 = this.b;
            View$OnLayoutChangeListenerC4598rc0 rc0 = a2.f10290a;
            if (rc0.c0()) {
                C5958zc0 a3 = rc0.f11207J.a(rc0.N.R0());
                C4112ol olVar = new C4112ol(ci0, new C2636g50[0], new C5108uc0(a3));
                a3.d = olVar;
                olVar.F.add(rc0.L.f9829a);
            }
        }
        C1992cI0 ci02 = this.b;
        if (ci02 != null) {
            ci02.b(g50Arr);
        }
    }

    public final void onItemsAvailable(Object obj) {
        WebContents R0;
        C2465f50 f50 = (C2465f50) obj;
        int i = f50.c;
        C1992cI0 ci0 = (C1992cI0) this.f10687a.get(i);
        if (ci0 == null) {
            AbstractC4329q0 q0Var = null;
            if (a() == null) {
                ci0 = null;
            } else {
                ci0 = new C1992cI0(Integer.MIN_VALUE);
                this.f10687a.put(i, ci0);
                View$OnLayoutChangeListenerC4598rc0 rc0 = a().f10290a;
                if (rc0.c0()) {
                    C5958zc0 a2 = rc0.f11207J.a(rc0.N.R0());
                    a2.b(i).f11691a = new C4112ol(ci0, null, new C5278vc0(a2));
                    boolean z = false;
                    if (!(!rc0.c0() || i == 0 || i == 5)) {
                        z = (i == 2 || i == 3) ? N.M09VlOh_("AutofillManualFallbackAndroid") : true;
                    }
                    if (z && (R0 = rc0.N.R0()) != null) {
                        C5958zc0 a3 = rc0.f11207J.a(R0);
                        if (a3.b(i).b != null) {
                            q0Var = a3.b(i).b;
                        } else {
                            if (i == 1) {
                                q0Var = new C5681xx0(rc0.N, rc0.M.f10320a.G);
                            } else if (i == 2) {
                                q0Var = new C3336kB(rc0.N, rc0.M.f10320a.G);
                            } else if (i == 3) {
                                q0Var = new L3(rc0.N, rc0.M.f10320a.G);
                            } else if (i == 4) {
                                q0Var = new C4799sm1(rc0.N, rc0.M.f10320a.G);
                            }
                            a3.b(i).b = q0Var;
                            if (a3.b(i).f11691a != null) {
                                a3.b(i).f11691a.F.add(q0Var.a());
                            }
                            rc0.g0();
                        }
                    }
                    if (q0Var != null) {
                        a2.b(i).f11691a.F.add(q0Var.a());
                    }
                }
            }
        }
        if (ci0 != null) {
            ci0.b(f50);
        }
    }

    public void showWhenKeyboardIsVisible() {
        if (a() != null) {
            View$OnLayoutChangeListenerC4598rc0 rc0 = a().f10290a;
            if (rc0.c0()) {
                rc0.F.j(AbstractC4938tc0.f11352a, true);
                if (rc0.b0(4)) {
                    rc0.F.l(AbstractC4938tc0.c, 13);
                }
            }
        }
    }

    public final void swapSheetWithKeyboard() {
        if (a() != null) {
            View$OnLayoutChangeListenerC4598rc0 rc0 = a().f10290a;
            if (rc0.c0() && rc0.M.f10320a.F.h(AbstractC4158p0.c)) {
                rc0.e0();
            }
        }
    }
}
