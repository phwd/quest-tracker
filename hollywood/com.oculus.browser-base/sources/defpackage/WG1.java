package defpackage;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* renamed from: WG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class WG1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SharedPreferences f9139a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;

    public WG1(SharedPreferences sharedPreferences, String str, String str2) {
        this.f9139a = sharedPreferences;
        this.b = str;
        this.c = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return this.f9139a.getString(this.b, this.c);
    }
}
