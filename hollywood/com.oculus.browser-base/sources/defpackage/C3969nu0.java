package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/* renamed from: nu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3969nu0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10518a;

    public C3969nu0(Context context) {
        this.f10518a = context;
    }

    public ApplicationInfo a(String str, int i) {
        return this.f10518a.getPackageManager().getApplicationInfo(str, i);
    }
}
