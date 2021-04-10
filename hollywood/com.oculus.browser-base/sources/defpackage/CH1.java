package defpackage;

import android.content.Context;
import java.util.concurrent.Callable;

/* renamed from: CH1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CH1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f7800a;

    public CH1(Context context) {
        this.f7800a = context;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.f7800a.getSharedPreferences("google_sdk_flags", 0);
    }
}
