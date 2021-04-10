package defpackage;

import android.content.Intent;
import java.util.HashSet;

/* renamed from: dL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2171dL0 {

    /* renamed from: a  reason: collision with root package name */
    public Intent f9773a;
    public final HashSet b = new HashSet();
    public boolean c;
    public boolean d;
    public long e = -1;
    public boolean f;
    public int g;
    public int h;
    public boolean i;

    public void a() {
        this.c = false;
        this.d = false;
        this.f9773a = null;
        this.b.clear();
        this.g = 0;
        this.f = false;
        this.h = 0;
        this.i = false;
    }

    public final boolean b(boolean z) {
        int i2 = this.g;
        if (i2 == 4) {
            return true;
        }
        if (z) {
            return false;
        }
        return i2 == 3;
    }
}
