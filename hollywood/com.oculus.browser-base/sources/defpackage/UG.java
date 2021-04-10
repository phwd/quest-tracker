package defpackage;

import J.N;
import android.util.Pair;

/* renamed from: UG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class UG extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final long f9017a;

    public UG(long j) {
        this.f9017a = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Pair pair = (Pair) obj;
        N.MLbF8aR_(this.f9017a, ((Boolean) pair.first).booleanValue(), (String) pair.second);
    }
}
