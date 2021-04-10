package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import java.util.ArrayList;

/* renamed from: wL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5412wL0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11542a;
    public final C1543Zg0 b;
    public final Handler c;
    public final PackageManager d;
    public final ArrayList e = new ArrayList();
    public boolean f;
    public final BroadcastReceiver g = new C5072uL0(this);
    public final Runnable h = new RunnableC5242vL0(this);

    public C5412wL0(Context context, C1543Zg0 zg0) {
        this.f11542a = context;
        this.b = zg0;
        this.c = new Handler();
        this.d = context.getPackageManager();
    }

    /* JADX WARNING: Removed duplicated region for block: B:70:0x0047 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
        // Method dump skipped, instructions count: 377
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5412wL0.a():void");
    }
}
