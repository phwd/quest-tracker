package defpackage;

import java.util.ArrayList;
import java.util.List;

/* renamed from: sl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4794sl {

    /* renamed from: a  reason: collision with root package name */
    public final int f11297a;
    public final String b;
    public final int c;
    public final int d;
    public final int e;
    public final List f = new ArrayList(1);

    public C4794sl(int i, String str, int i2, int i3, int i4) {
        this.f11297a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public synchronized boolean a(int i, String str, int i2, int i3, int i4, int i5) {
        if (this.f.size() >= 256) {
            return false;
        }
        this.f.add(Integer.valueOf(i2));
        return true;
    }
}
