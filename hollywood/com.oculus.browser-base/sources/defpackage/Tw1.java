package defpackage;

import android.content.Context;
import java.util.concurrent.Callable;

/* renamed from: Tw1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Tw1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final Xw1[] f8996a;
    public final Context b;

    public Tw1(Xw1[] xw1Arr, Context context) {
        this.f8996a = xw1Arr;
        this.b = context;
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        Xw1[] xw1Arr = this.f8996a;
        Context context = this.b;
        for (Xw1 xw1 : xw1Arr) {
            context.unbindService(xw1);
        }
        return Boolean.TRUE;
    }
}
