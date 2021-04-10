package defpackage;

import android.content.Context;
import android.view.View;

/* renamed from: dJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2166dJ0 implements ZI0 {

    /* renamed from: a  reason: collision with root package name */
    public final C3361kJ0 f9769a;
    public final C2678gJ0 b;

    public C2166dJ0(Context context, XI0 xi0) {
        UH0 uh0 = new UH0(AbstractC3703mJ0.d);
        C2678gJ0 gj0 = new C2678gJ0(context, uh0, xi0);
        this.b = gj0;
        C3361kJ0 kj0 = new C3361kJ0(context, new C1824bJ0(gj0), new C1995cJ0(gj0));
        this.f9769a = kj0;
        ZH0.a(uh0, kj0, new C3532lJ0());
    }

    @Override // defpackage.ZI0
    public void a() {
        C3361kJ0 kj0 = this.f9769a;
        SurfaceHolder$CallbackC2065cm cmVar = kj0.g;
        if (cmVar != null) {
            cmVar.b();
            kj0.g = null;
        }
    }

    @Override // defpackage.ZI0
    public View b() {
        return this.f9769a.b;
    }

    @Override // defpackage.ZI0
    public void c() {
        AbstractC3535lK0.a("SharingQRCode.TabVisible.Scan");
        C2678gJ0 gj0 = this.b;
        gj0.a();
        gj0.b.j(AbstractC3703mJ0.c, true);
    }

    @Override // defpackage.ZI0
    public void d() {
        this.b.b.j(AbstractC3703mJ0.c, false);
    }
}
