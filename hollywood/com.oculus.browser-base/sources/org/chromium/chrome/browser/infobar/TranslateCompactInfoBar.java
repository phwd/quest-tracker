package org.chromium.chrome.browser.infobar;

import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.ArrayList;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TranslateCompactInfoBar extends InfoBar implements AbstractC5716y81 {
    public final C5482wn1 I;

    /* renamed from: J  reason: collision with root package name */
    public long f10686J;
    public int K;
    public final AbstractC4928tY0 L;
    public boolean M;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TranslateCompactInfoBar(AbstractC4928tY0 ty0, int i, String str, String str2, boolean z, boolean z2, String[] strArr, String[] strArr2, int[] iArr, int i2) {
        super(R.drawable.f33420_resource_name_obfuscated_RES_2131231382, 0, null, null);
        this.L = ty0;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < strArr.length; i3++) {
            arrayList.add(new C5312vn1(strArr2[i3], strArr[i3], iArr != null ? Integer.valueOf(iArr[i3]) : null));
        }
        this.I = new C5482wn1(str, str2, arrayList, false, false, z, z2, null);
    }

    public static InfoBar create(Tab tab, int i, String str, String str2, boolean z, boolean z2, String[] strArr, String[] strArr2, int[] iArr, int i2) {
        k(0);
        return new TranslateCompactInfoBar((ChromeActivity) AbstractC5112ud1.b(tab), i, str, str2, z, z2, strArr, strArr2, iArr, i2);
    }

    public static void k(int i) {
        AbstractC3364kK0.g("Translate.CompactInfobar.Event", i, 25);
    }

    @Override // defpackage.AbstractC5546x81
    public void a(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void c(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void f(D81 d81) {
        int i = d81.d;
        if (i == 0) {
            int i2 = this.K + 1;
            this.K = i2;
            AbstractC3364kK0.d("Translate.CompactInfobar.TranslationsPerPage", i2);
            k(12);
            this.M = true;
            i(4);
        } else if (i == 1) {
            k(1);
            C5482wn1 wn1 = this.I;
            String str = wn1.b;
            Integer num = !TextUtils.isEmpty(str) && wn1.d.containsKey(str) ? (Integer) wn1.d.get(str) : null;
            if (num != null) {
                AbstractC3100ip1.f10165a.d("Translate.CompactInfobar.Language.Translate", num.intValue());
            }
            this.M = true;
            i(3);
        }
    }

    public final boolean onPageTranslated(int i) {
        int i2 = this.K + 1;
        this.K = i2;
        AbstractC3364kK0.d("Translate.CompactInfobar.TranslationsPerPage", i2);
        return false;
    }

    public void onTargetLanguageChanged(String str) {
        C5482wn1 wn1 = this.I;
        boolean z = false;
        if (wn1.b(wn1.f11569a) && wn1.b(str)) {
            z = true;
        }
        if (z) {
            wn1.b = str;
        }
        if (z) {
            this.I.a(str);
            throw null;
        }
    }

    public final void onTranslating() {
    }

    @Override // org.chromium.components.infobars.InfoBar
    public void resetNativeInfoBar() {
        this.f10686J = 0;
        super.resetNativeInfoBar();
    }

    public final void setAutoAlwaysTranslate() {
        C5482wn1 wn1 = this.I;
        wn1.a(wn1.f11569a);
        C5482wn1 wn12 = this.I;
        wn12.a(wn12.b);
        throw null;
    }

    public final void setNativePtr(long j) {
        this.f10686J = j;
    }
}
