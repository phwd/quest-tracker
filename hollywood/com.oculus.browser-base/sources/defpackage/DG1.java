package defpackage;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

/* renamed from: DG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class DG1 implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SharedPreferences f7877a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Long c;

    public DG1(SharedPreferences sharedPreferences, String str, Long l) {
        this.f7877a = sharedPreferences;
        this.b = str;
        this.c = l;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Object call() {
        return Long.valueOf(this.f7877a.getLong(this.b, this.c.longValue()));
    }
}
