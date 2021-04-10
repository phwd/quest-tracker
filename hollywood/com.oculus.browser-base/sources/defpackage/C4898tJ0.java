package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Process;
import java.lang.ref.WeakReference;

/* renamed from: tJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4898tJ0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11337a;
    public final UH0 b;
    public final AbstractC2809h6 c;
    public int d;
    public boolean e;
    public String f;
    public Runnable g;

    public C4898tJ0(Context context, UH0 uh0, Runnable runnable, String str) {
        this.f11337a = context;
        this.b = uh0;
        this.g = runnable;
        this.f = str;
        C1321Vq.b().i(new RunnableC4216pJ0(this));
        this.c = new I2(new WeakReference((Activity) context));
        a();
    }

    public final void a() {
        this.b.j(AbstractC5578xJ0.d, Boolean.valueOf(this.c.canRequestPermission("android.permission.WRITE_EXTERNAL_STORAGE")).booleanValue());
        this.b.j(AbstractC5578xJ0.c, Boolean.valueOf(this.f11337a.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) == 0).booleanValue());
    }
}
