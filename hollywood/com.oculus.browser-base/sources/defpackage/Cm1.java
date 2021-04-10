package defpackage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/* renamed from: Cm1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Cm1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Lm1 f7838a;

    public Cm1(Lm1 lm1) {
        this.f7838a = lm1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Lm1 lm1 = this.f7838a;
        Objects.requireNonNull(lm1);
        lm1.e = new HashSet(Arrays.asList((String[]) obj));
        lm1.b(1);
    }
}
