package defpackage;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

/* renamed from: bZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractServiceC1857bZ0 extends Service {
    public String F;
    public AbstractC1677aZ0 G;

    public AbstractServiceC1857bZ0(String str) {
        this.F = str;
    }

    public static int a(AbstractServiceC1857bZ0 bz0, Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public static boolean b(AbstractServiceC1857bZ0 bz0, Intent intent) {
        return super.onUnbind(intent);
    }

    public void attachBaseContext(Context context) {
        Context a2 = AbstractC2369eZ0.a(context);
        AbstractC1677aZ0 az0 = (AbstractC1677aZ0) AbstractC2369eZ0.b(a2, this.F);
        this.G = az0;
        az0.f9437a = this;
        super.attachBaseContext(a2);
    }

    public IBinder onBind(Intent intent) {
        return this.G.a(intent);
    }

    public void onCreate() {
        super.onCreate();
        this.G.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.G.c();
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.G.d();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return this.G.e(intent, i, i2);
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        this.G.f(intent);
    }

    public boolean onUnbind(Intent intent) {
        return this.G.g(intent);
    }
}
