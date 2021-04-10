package defpackage;

import org.chromium.base.Callback;

/* renamed from: Rl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1067Rl extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public Callback f8850a;
    public final /* synthetic */ C1128Sl b;

    public C1067Rl(C1128Sl sl, Callback callback, AbstractC0945Pl pl) {
        this.b = sl;
        this.f8850a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1006Ql Y = C1006Ql.Y(this.b.b.readLock());
        try {
            Callback callback = this.f8850a;
            if (callback != null) {
                callback.onResult(obj);
            }
            Y.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
