package org.chromium.components.navigation_interception;

import android.text.TextUtils;
import org.chromium.url.Origin;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NavigationParams {

    /* renamed from: a  reason: collision with root package name */
    public final String f10856a;
    public final String b;
    public final boolean c;
    public final int d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final boolean i;
    public final Origin j;

    public NavigationParams(String str, String str2, long j2, boolean z, boolean z2, int i2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Origin origin) {
        this.f10856a = str;
        this.b = TextUtils.isEmpty(str2) ? null : str2;
        this.c = z2;
        this.d = i2;
        this.e = z3;
        this.f = z4;
        this.h = z5;
        this.i = z6;
        this.g = z7;
        this.j = origin;
    }

    public static NavigationParams create(String str, String str2, long j2, boolean z, boolean z2, int i2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, Origin origin) {
        return new NavigationParams(str, str2, j2, z, z2, i2, z3, z4, z5, z6, z7, origin);
    }
}
