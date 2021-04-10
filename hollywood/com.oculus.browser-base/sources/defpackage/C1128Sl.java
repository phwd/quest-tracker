package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.chromium.base.Callback;

/* renamed from: Sl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1128Sl {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList f8913a = new ArrayList();
    public final ReadWriteLock b = new ReentrantReadWriteLock(true);

    public void a() {
        C1006Ql Y = C1006Ql.Y(this.b.writeLock());
        try {
            ArrayList arrayList = this.f8913a;
            if (arrayList != null) {
                Iterator it = ((ArrayList) AbstractC0417Gv.e(arrayList)).iterator();
                while (it.hasNext()) {
                    ((C1067Rl) it.next()).f8850a = null;
                }
                this.f8913a = null;
                Y.close();
                return;
            }
            throw new IllegalStateException("This CallbackController has already been destroyed.");
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public Callback b(Callback callback) {
        C1006Ql Y = C1006Ql.Y(this.b.writeLock());
        try {
            ArrayList arrayList = this.f8913a;
            if (arrayList != null) {
                C1067Rl rl = new C1067Rl(this, callback, null);
                arrayList.add(new WeakReference(rl));
                Y.close();
                return rl;
            }
            throw new IllegalStateException("This CallbackController has already been destroyed.");
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }
}
