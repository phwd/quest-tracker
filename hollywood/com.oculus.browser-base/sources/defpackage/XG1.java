package defpackage;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

/* renamed from: XG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class XG1 extends Binder {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractServiceC2158dG1 f9199a;

    public XG1(AbstractServiceC2158dG1 dg1) {
        this.f9199a = dg1;
    }

    public final void a(EG1 eg1) {
        if (Binder.getCallingUid() == Process.myUid()) {
            Log.isLoggable("EnhancedIntentService", 3);
            Log.isLoggable("EnhancedIntentService", 3);
            this.f9199a.F.execute(new RunnableC3185jH1(this, eg1));
            return;
        }
        throw new SecurityException("Binding only allowed within app");
    }
}
