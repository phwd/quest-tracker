package defpackage;

import android.app.PendingIntent;
import android.graphics.Bitmap;

/* renamed from: xp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5657xp0 {

    /* renamed from: a  reason: collision with root package name */
    public int f11637a;
    public Bitmap b;
    public CharSequence c;
    public PendingIntent d;
    public int e;
    public int f;
    public String g;

    public C5657xp0(int i, CharSequence charSequence, PendingIntent pendingIntent, int i2, String str, int i3) {
        this.f11637a = i;
        this.c = charSequence;
        this.d = pendingIntent;
        this.e = i2;
        this.g = null;
        this.f = i3;
    }

    public C5657xp0(Bitmap bitmap, CharSequence charSequence, PendingIntent pendingIntent, int i, String str) {
        this.b = bitmap;
        this.c = charSequence;
        this.d = pendingIntent;
        this.e = i;
        this.g = str;
        this.f = -1;
    }
}
