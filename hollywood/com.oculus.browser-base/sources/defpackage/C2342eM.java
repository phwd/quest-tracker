package defpackage;

import J.N;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* renamed from: eM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2342eM extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3538lM f9849a;
    public final C3811my1 b;
    public final C5232vH0 c;

    public C2342eM(C3538lM lMVar, C3811my1 my1, C5232vH0 vh0) {
        this.f9849a = lMVar;
        this.b = my1;
        this.c = vh0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3538lM lMVar = this.f9849a;
        C3811my1 my1 = this.b;
        C5232vH0 vh0 = this.c;
        List list = (List) obj;
        Objects.requireNonNull(lMVar);
        int i = 1;
        C4324py1[] py1Arr = new C4324py1[1];
        C4324py1 py1 = C4324py1.e;
        C4324py1 py12 = C4324py1.e;
        C4324py1 py13 = new C4324py1();
        String str = my1.b;
        str.getClass();
        py13.g |= 1;
        py13.h = str;
        long j = my1.f10463a;
        C3982ny1 ny1 = C3982ny1.e;
        C3982ny1 ny12 = C3982ny1.e;
        C3982ny1 ny13 = new C3982ny1();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long seconds = timeUnit.toSeconds(j);
        ny13.g |= 1;
        ny13.h = seconds;
        ny13.g |= 2;
        ny13.i = (int) timeUnit.toNanos(j % 1000);
        C2163dI0 di0 = C2163dI0.f9768a;
        di0.b(ny13).c(ny13);
        if (ny13.i()) {
            py13.i = ny13;
            int i2 = py13.g | 2;
            py13.g = i2;
            int i3 = my1.c;
            if (i3 == 1) {
                i = 2;
            } else if (i3 == 2) {
                i = 3;
            }
            py13.j = AbstractC5580xK0.a(i);
            py13.g = i2 | 4;
            di0.b(py13).c(py13);
            if (py13.i()) {
                py1Arr[0] = py13;
                List asList = Arrays.asList(py1Arr);
                UsageStatsBridge usageStatsBridge = lMVar.f10340a;
                C2001cM cMVar = new C2001cM(list, my1, vh0);
                Objects.requireNonNull(usageStatsBridge);
                byte[][] bArr = new byte[asList.size()][];
                for (int i4 = 0; i4 < asList.size(); i4++) {
                    bArr[i4] = ((C4324py1) asList.get(i4)).c();
                }
                N.M$1mbh6c(usageStatsBridge.b, usageStatsBridge, bArr, cMVar);
                return;
            }
            throw new C5488wp1();
        }
        throw new C5488wp1();
    }
}
