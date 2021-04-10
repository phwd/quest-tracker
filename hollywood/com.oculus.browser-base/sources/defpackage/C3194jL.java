package defpackage;

import android.view.View;
import java.util.Objects;

/* renamed from: jL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3194jL extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3536lL f10200a;

    public C3194jL(C3536lL lLVar) {
        this.f10200a = lLVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3536lL lLVar = this.f10200a;
        View view = (View) obj;
        Objects.requireNonNull(lLVar);
        NU0.f8549a.m(AbstractC0533Is.h.b("EnhancedProtectionPromoCard"), true);
        AbstractC3707mL.a(2);
        lLVar.f = false;
        AbstractC3365kL kLVar = lLVar.c;
        if (kLVar != null) {
            ((C2861hP) kLVar).F.c(false, null);
        }
    }
}
