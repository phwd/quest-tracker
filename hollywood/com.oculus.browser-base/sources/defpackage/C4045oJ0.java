package defpackage;

import android.content.Context;
import android.view.View;

/* renamed from: oJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4045oJ0 implements ZI0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5238vJ0 f10542a;
    public final C4898tJ0 b;

    public C4045oJ0(Context context, Runnable runnable, String str) {
        UH0 uh0 = new UH0(AbstractC5578xJ0.f);
        C4898tJ0 tj0 = new C4898tJ0(context, uh0, runnable, str);
        this.b = tj0;
        C5238vJ0 vj0 = new C5238vJ0(context, new View$OnClickListenerC3874nJ0(tj0));
        this.f10542a = vj0;
        ZH0.a(uh0, vj0, new C5408wJ0());
    }

    @Override // defpackage.ZI0
    public void a() {
    }

    @Override // defpackage.ZI0
    public View b() {
        return this.f10542a.b;
    }

    @Override // defpackage.ZI0
    public void c() {
        C4898tJ0 tj0 = this.b;
        tj0.a();
        tj0.b.j(AbstractC5578xJ0.e, true);
        AbstractC3535lK0.a("SharingQRCode.TabVisible.Share");
    }

    @Override // defpackage.ZI0
    public void d() {
        this.b.b.j(AbstractC5578xJ0.e, false);
    }
}
