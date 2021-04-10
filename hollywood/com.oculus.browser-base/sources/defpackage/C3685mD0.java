package defpackage;

import java.util.Set;
import org.chromium.base.Callback;

/* renamed from: mD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3685mD0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f10406a;
    public final Iv1 b;
    public final Hv1 c;
    public final Set d;

    public C3685mD0(Callback callback, Iv1 iv1, Hv1 hv1, Set set) {
        this.f10406a = callback;
        this.b = iv1;
        this.c = hv1;
        this.d = set;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f10406a.onResult(new Jv1(this.b, this.c, this.d, (Set) obj));
    }
}
