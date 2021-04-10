package defpackage;

import J.N;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: kE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3345kE implements Runnable {
    public final C3858nE F;
    public final Boolean G;

    public RunnableC3345kE(C3858nE nEVar, Boolean bool) {
        this.F = nEVar;
        this.G = bool;
    }

    public void run() {
        C3858nE nEVar = this.F;
        Boolean bool = this.G;
        Objects.requireNonNull(nEVar);
        boolean booleanValue = bool.booleanValue();
        boolean j = booleanValue ? nEVar.b.j(nEVar) : false;
        C1184Ti1.a(nEVar.f10477a.getContext(), j ? R.string.f57320_resource_name_obfuscated_RES_2131953049 : R.string.f57310_resource_name_obfuscated_RES_2131953048, 1).b.show();
        if (!booleanValue || !j) {
            N.M9gwtxem();
            nEVar.f10477a.I(nEVar.c);
            nEVar.f10477a = null;
            nEVar.b = null;
        }
    }
}
