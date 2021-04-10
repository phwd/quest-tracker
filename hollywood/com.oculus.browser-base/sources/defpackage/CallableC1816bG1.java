package defpackage;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* renamed from: bG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CallableC1816bG1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SharedPreferences f9527a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Integer c;

    public CallableC1816bG1(SharedPreferences sharedPreferences, String str, Integer num) {
        this.f9527a = sharedPreferences;
        this.b = str;
        this.c = num;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return Integer.valueOf(this.f9527a.getInt(this.b, this.c.intValue()));
    }
}
