package defpackage;

import java.util.Objects;

/* renamed from: Qj  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1002Qj extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1551Zj f8782a;

    public C1002Qj(C1551Zj zj) {
        this.f8782a = zj;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1551Zj zj = this.f8782a;
        Objects.requireNonNull(zj);
        if (((Integer) obj).intValue() == 1) {
            zj.h();
        }
    }
}
