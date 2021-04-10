package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.Objects;
import java.util.concurrent.Callable;

/* renamed from: Rw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Rw1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final Zw1 f8863a;
    public final String b;
    public final Context c;
    public final Xw1 d;

    public Rw1(Zw1 zw1, String str, Context context, Xw1 xw1) {
        this.f8863a = zw1;
        this.b = str;
        this.c = context;
        this.d = xw1;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        Zw1 zw1 = this.f8863a;
        String str = this.b;
        Context context = this.c;
        Xw1 xw1 = this.d;
        Objects.requireNonNull(zw1);
        Intent intent = new Intent();
        String str2 = zw1.f9386a;
        if (str2 != null) {
            intent.addCategory(str2);
        }
        String str3 = zw1.b;
        if (str3 != null) {
            intent.setAction(str3);
        }
        intent.setPackage(str);
        try {
            if (context.bindService(intent, xw1, 1)) {
                return Boolean.TRUE;
            }
            context.unbindService(xw1);
            return Boolean.FALSE;
        } catch (SecurityException e) {
            Log.w("WebApkService", "Security exception binding.", e);
        }
    }
}
