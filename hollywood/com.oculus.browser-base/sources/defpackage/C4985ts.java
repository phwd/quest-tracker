package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: ts  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4985ts extends AbstractC1922bv0 {
    public final WebContents i;
    public Q31 j;
    public final Context k;
    public final Profile l;
    public String m;
    public AbstractC1385Wr0 n;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C4985ts(android.content.Context r9, org.chromium.content_public.browser.WebContents r10, defpackage.Q31 r11, defpackage.AbstractC1385Wr0 r12) {
        /*
        // Method dump skipped, instructions count: 205
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4985ts.<init>(android.content.Context, org.chromium.content_public.browser.WebContents, Q31, Wr0):void");
    }

    @Override // defpackage.AbstractC1922bv0
    public KS a() {
        AbstractActivityC3892nS nSVar = (AbstractActivityC3892nS) this.k;
        if (nSVar.isFinishing()) {
            return null;
        }
        return nSVar.Y();
    }

    @Override // defpackage.AbstractC1922bv0
    public String b() {
        int i2 = this.f;
        if (i2 == 2) {
            return String.format(this.k.getString(R.string.f56970_resource_name_obfuscated_RES_2131953014), this.m);
        } else if (i2 != 3) {
            return null;
        } else {
            if (TextUtils.isEmpty(this.m)) {
                return this.k.getString(R.string.f57120_resource_name_obfuscated_RES_2131953029);
            }
            return String.format(this.k.getString(R.string.f57110_resource_name_obfuscated_RES_2131953028), this.m);
        }
    }

    @Override // defpackage.AbstractC1922bv0
    public boolean d() {
        TabImpl tabImpl = (TabImpl) N.MMqeq$AW(this.i);
        return tabImpl != null && C1872be1.e(tabImpl).h();
    }
}
