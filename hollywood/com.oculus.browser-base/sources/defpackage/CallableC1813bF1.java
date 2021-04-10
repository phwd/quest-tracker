package defpackage;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* renamed from: bF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CallableC1813bF1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SharedPreferences f9525a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Boolean c;

    public CallableC1813bF1(SharedPreferences sharedPreferences, String str, Boolean bool) {
        this.f9525a = sharedPreferences;
        this.b = str;
        this.c = bool;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return Boolean.valueOf(this.f9525a.getBoolean(this.b, this.c.booleanValue()));
    }
}
