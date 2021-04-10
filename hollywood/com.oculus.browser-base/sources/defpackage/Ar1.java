package defpackage;

import java.util.List;

/* renamed from: Ar1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ar1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Jr1 f7700a;

    public Ar1(Jr1 jr1) {
        this.f7700a = jr1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f7700a.c((List) obj, true);
    }
}
