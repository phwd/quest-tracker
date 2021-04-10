package defpackage;

import android.content.Context;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: tI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4896tI1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11335a;
    public final ScheduledExecutorService b;
    public FI1 c = new FI1(this, null);
    public int d = 1;

    public C4896tI1(Context context) {
        ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, new ThreadFactoryC3945nm0("MessengerIpcClient")));
        this.f11335a = context.getApplicationContext();
        this.b = unconfigurableScheduledExecutorService;
    }
}
