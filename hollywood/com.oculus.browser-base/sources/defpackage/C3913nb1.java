package defpackage;

import android.os.SystemClock;
import java.util.Locale;
import org.chromium.base.TraceEvent;

/* renamed from: nb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3913nb1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4084ob1 f10500a;
    public final long b;

    public C3913nb1(C4084ob1 ob1, long j) {
        this.f10500a = ob1;
        this.b = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4084ob1 ob1 = this.f10500a;
        long j = this.b;
        byte[] bArr = (byte[]) obj;
        TraceEvent.g0("LoadCriticalPersistedTabData", (long) ob1.f10561a.f11206a);
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        objArr[0] = bArr == null ? "Null" : "Exists";
        AbstractC3364kK0.k(String.format(locale, "Tabs.SavedTabLoadTime.CriticalPersistedTabData.%s", objArr), SystemClock.elapsedRealtime() - j);
        if (bArr == null) {
            ob1.b();
        } else {
            C4766sb1.c(ob1.d, ob1.f10561a, null, bArr);
        }
    }
}
