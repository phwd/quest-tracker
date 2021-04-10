package defpackage;

import android.media.VolumeProvider;
import android.os.Build;

/* renamed from: Ug0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1238Ug0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9042a;
    public final int b;
    public final String c;
    public int d;
    public AbstractC0266Eh0 e;
    public VolumeProvider f;
    public final /* synthetic */ C1299Vg0 g;

    public C1238Ug0(C1299Vg0 vg0, int i, int i2, int i3, String str) {
        this.g = vg0;
        this.f9042a = i;
        this.b = i2;
        this.d = i3;
        this.c = str;
    }

    public Object a() {
        if (this.f == null) {
            if (Build.VERSION.SDK_INT >= 30) {
                this.f = new Yv1(this, this.f9042a, this.b, this.d, this.c);
            } else {
                this.f = new Zv1(this, this.f9042a, this.b, this.d);
            }
        }
        return this.f;
    }
}
