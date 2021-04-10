package defpackage;

import java.util.Objects;

/* renamed from: pz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4325pz implements AbstractC5507ww {

    /* renamed from: a  reason: collision with root package name */
    public final C4666rz f11105a;

    public C4325pz(C4666rz rzVar) {
        this.f11105a = rzVar;
    }

    @Override // defpackage.AbstractC5507ww
    public void a(C5677xw xwVar) {
        C4666rz rzVar = this.f11105a;
        Objects.requireNonNull(rzVar);
        float a2 = xwVar.a();
        rzVar.i = Math.max(1.0f - (a2 / 0.75f), 0.0f);
        rzVar.j = Math.max(a2 - 0.25f, 0.0f) / 0.75f;
    }
}
