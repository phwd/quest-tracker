package defpackage;

import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: ki1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3421ki1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3592li1 f10297a;
    public final long b;
    public final Callback c;

    public C3421ki1(C3592li1 li1, long j, Callback callback) {
        this.f10297a = li1;
        this.b = j;
        this.c = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3592li1 li1 = this.f10297a;
        long j = this.b;
        Callback callback = this.c;
        List list = (List) obj;
        Objects.requireNonNull(li1);
        boolean z = list != null && !list.isEmpty();
        long currentTimeMillis = System.currentTimeMillis() - j;
        AbstractC3100ip1.f10165a.a(AbstractC2531fV.h(AbstractC2531fV.i("Search."), li1.b.f8779a, ".Bitmap.Available"), z);
        StringBuilder sb = new StringBuilder();
        sb.append("Search.");
        sb.append(li1.b.f8779a);
        sb.append(z ? ".Bitmap" : ".NoBitmap");
        sb.append(".FetchDuration");
        AbstractC3364kK0.k(sb.toString(), currentTimeMillis);
        callback.onResult(list);
    }
}
