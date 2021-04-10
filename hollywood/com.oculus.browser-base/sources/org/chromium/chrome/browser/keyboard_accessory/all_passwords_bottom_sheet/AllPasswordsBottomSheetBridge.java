package org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet;

import android.content.Context;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AllPasswordsBottomSheetBridge implements AbstractC3657m4 {

    /* renamed from: a  reason: collision with root package name */
    public long f10688a;
    public C2824hB[] b;
    public final C3828n4 c;

    public AllPasswordsBottomSheetBridge(long j, WindowAndroid windowAndroid, String str) {
        this.f10688a = j;
        C3828n4 n4Var = new C3828n4();
        this.c = n4Var;
        C4511r4 r4Var = n4Var.f10470a;
        r4Var.getClass();
        C3144j4 j4Var = new C3144j4(r4Var);
        C4511r4 r4Var2 = n4Var.f10470a;
        r4Var2.getClass();
        C3315k4 k4Var = new C3315k4(r4Var2);
        Map c2 = UH0.c(AbstractC4852t4.f);
        QH0 qh0 = AbstractC4852t4.f11319a;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = false;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(qh0, gh0);
        OH0 oh0 = AbstractC4852t4.c;
        C1794b90 b90 = new C1794b90();
        LH0 lh0 = new LH0(null);
        lh0.f8415a = b90;
        hashMap.put(oh0, lh0);
        OH0 oh02 = AbstractC4852t4.b;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = j4Var;
        hashMap.put(oh02, lh02);
        OH0 oh03 = AbstractC4852t4.d;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = str;
        hashMap.put(oh03, lh03);
        OH0 oh04 = AbstractC4852t4.e;
        LH0 lh04 = new LH0(null);
        lh04.f8415a = k4Var;
        UH0 o = AbstractC2531fV.o(hashMap, oh04, lh04, c2, null);
        C4511r4 r4Var3 = n4Var.f10470a;
        r4Var3.f11181a = this;
        r4Var3.b = o;
        ZH0.a(o, new C5362w4((Context) windowAndroid.s0().get(), (AbstractC4448qj) AbstractC5978zj.f11762a.e(windowAndroid.U)), new C3486l4());
    }

    public static AllPasswordsBottomSheetBridge create(long j, WindowAndroid windowAndroid, String str) {
        return new AllPasswordsBottomSheetBridge(j, windowAndroid, str);
    }

    public final void createCredentialArray(int i) {
        this.b = new C2824hB[i];
    }

    public final void destroy() {
        this.f10688a = 0;
    }

    public final void insertCredential(int i, String str, String str2, String str3, String str4, boolean z, String str5) {
        this.b[i] = new C2824hB(str, str2, str3, str4, z, str5);
    }

    public final void showCredentials(boolean z) {
        C3828n4 n4Var = this.c;
        C2824hB[] hBVarArr = this.b;
        C4511r4 r4Var = n4Var.f10470a;
        Objects.requireNonNull(r4Var);
        Arrays.sort(hBVarArr, new C3999o4());
        r4Var.c = hBVarArr;
        r4Var.d = z;
        C1794b90 b90 = (C1794b90) r4Var.b.g(AbstractC4852t4.c);
        b90.clear();
        C2824hB[] hBVarArr2 = r4Var.c;
        for (C2824hB hBVar : hBVarArr2) {
            if (!hBVar.b.isEmpty() || !z) {
                b90.q(new C4765sb0(0, AbstractC4682s4.a(hBVar, new C4170p4(r4Var), r4Var.d)));
            }
        }
        r4Var.b.j(AbstractC4852t4.f11319a, true);
    }
}
