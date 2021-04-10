package defpackage;

import android.content.Context;
import com.oculus.browser.R;
import org.chromium.base.task.PostTask;

/* renamed from: x41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5534x41 implements J41 {

    /* renamed from: a  reason: collision with root package name */
    public final C41 f11585a;
    public final Context b;

    public C5534x41(C41 c41, Context context) {
        this.f11585a = c41;
        this.b = context;
    }

    @Override // defpackage.J41
    public void a() {
        C41 c41 = this.f11585a;
        Context context = this.b;
        c41.j();
        C3070if1 if1 = Zo1.f9374a;
        if (c41.L == null) {
            c41.L = new RunnableC5874z41(c41);
        }
        PostTask.b(if1, c41.L, 7500);
        if (c41.N == null) {
            c41.N = context.getResources().getString(R.string.f45830_resource_name_obfuscated_RES_2131951900);
        }
        c41.H.announceForAccessibility(c41.N);
        c41.I.q();
        AbstractC3535lK0.a("MobilePullGestureReload");
    }
}
