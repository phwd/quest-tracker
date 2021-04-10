package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.profiles.ProfileManager;

/* renamed from: nQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3889nQ0 implements AbstractC4371qE, AbstractC4968tm0, AbstractC0061Ba {
    public final Context F;
    public final UH0 G;
    public final ViewGroup H;
    public final List I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public M2 f10489J;
    public C0122Ca K;

    public C3889nQ0(Context context, UH0 uh0, ViewGroup viewGroup) {
        this.F = context;
        this.G = uh0;
        this.H = viewGroup;
        ZH0.a(uh0, viewGroup, new C4231pQ0());
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        C0122Ca ca = this.K;
        if (ca != null) {
            ca.f7820J.b.c(ca);
            ca.K.e.c(ca);
            ProfileManager.f10754a.c(ca);
            this.K = null;
        }
        M2 m2 = this.f10489J;
        if (m2 != null) {
            m2.b(this);
            this.f10489J = null;
        }
    }

    @Override // defpackage.AbstractC0061Ba
    public void g() {
        C0122Ca ca = this.K;
        if (ca != null) {
            this.G.m(AbstractC4060oQ0.e, AbstractC5544x8.a(ca.H, ca.P ? R.drawable.f29800_resource_name_obfuscated_RES_2131231020 : R.drawable.f28490_resource_name_obfuscated_RES_2131230889));
            this.G.m(AbstractC4060oQ0.f, this.K.b(AbstractC2934hr.a(this.F.getResources(), false), this.F));
        }
    }

    @Override // defpackage.AbstractC4968tm0
    public void s() {
        this.K = new C0122Ca(this.F, YM.f9268a, AbstractC0444Hf1.a(), C5259vU.b(this.F), this, NU0.f8549a);
        g();
    }
}
