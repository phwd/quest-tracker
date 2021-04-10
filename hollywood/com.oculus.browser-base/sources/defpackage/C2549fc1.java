package defpackage;

import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: fc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2549fc1 implements AbstractC0211Dj0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2720gc1 f9933a;

    public C2549fc1(C2720gc1 gc1) {
        this.f9933a = gc1;
    }

    @Override // defpackage.AbstractC0211Dj0
    public void a(int i) {
        C2720gc1 gc1 = this.f9933a;
        C2891hc1 hc1 = gc1.c;
        C1529Zb1 zb1 = gc1.f10007a;
        Callback callback = gc1.b;
        Objects.requireNonNull(hc1);
        callback.onResult(new C1686ac1(zb1, 0, null, 0));
    }
}
