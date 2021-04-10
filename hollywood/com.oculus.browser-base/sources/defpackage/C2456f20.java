package defpackage;

import android.util.Pair;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: f20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2456f20 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4677s20 f9893a;
    public final C5527x20 b;

    public C2456f20(C4677s20 s20, C5527x20 x20) {
        this.f9893a = s20;
        this.b = x20;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5922zL0[] zl0Arr;
        C4677s20 s20 = this.f9893a;
        C5527x20 x20 = this.b;
        Pair pair = (Pair) obj;
        Objects.requireNonNull(s20);
        ArrayList arrayList = (ArrayList) pair.first;
        Integer num = (Integer) pair.second;
        if (s20.F.f9741a.a()) {
            zl0Arr = new C5922zL0[0];
        } else {
            zl0Arr = new C5922zL0[arrayList.size()];
            arrayList.toArray(zl0Arr);
        }
        PostTask.b(Zo1.f9374a, new RunnableC3652m20(x20, zl0Arr), (long) num.intValue());
    }
}
