package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* renamed from: dM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2172dM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3538lM f9774a;

    public C2172dM(C3538lM lMVar) {
        this.f9774a = lMVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3538lM lMVar = this.f9774a;
        List<C4324py1> list = (List) obj;
        Objects.requireNonNull(lMVar);
        ArrayList arrayList = new ArrayList(list.size());
        for (C4324py1 py1 : list) {
            C3982ny1 ny1 = py1.i;
            if (ny1 == null) {
                C3982ny1 ny12 = C3982ny1.e;
                ny1 = C3982ny1.e;
            }
            long millis = TimeUnit.NANOSECONDS.toMillis((long) ny1.i) + TimeUnit.SECONDS.toMillis(ny1.h);
            String str = py1.h;
            int i = AbstractC5580xK0.i(py1.j);
            if (i == 0) {
                i = 1;
            }
            arrayList.add(new C3811my1(millis, str, AbstractC5580xK0.a(i)));
        }
        lMVar.b.b(arrayList);
    }
}
