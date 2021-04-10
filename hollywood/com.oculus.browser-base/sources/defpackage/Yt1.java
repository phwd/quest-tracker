package defpackage;

import android.os.Build;
import android.view.View;

/* renamed from: Yt1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Yt1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9303a;
    public final Class b;
    public final int c;
    public final int d;

    public Yt1(int i, Class cls, int i2) {
        this.f9303a = i;
        this.b = cls;
        this.d = 0;
        this.c = i2;
    }

    public boolean a(Boolean bool, Boolean bool2) {
        boolean z;
        boolean booleanValue = bool == null ? false : bool.booleanValue();
        if (bool2 == null) {
            z = false;
        } else {
            z = bool2.booleanValue();
        }
        if (booleanValue == z) {
            return true;
        }
        return false;
    }

    public abstract Object b(View view);

    public Object c(View view) {
        if (Build.VERSION.SDK_INT >= this.c) {
            return b(view);
        }
        Object tag = view.getTag(this.f9303a);
        if (this.b.isInstance(tag)) {
            return tag;
        }
        return null;
    }

    public Yt1(int i, Class cls, int i2, int i3) {
        this.f9303a = i;
        this.b = cls;
        this.d = i2;
        this.c = i3;
    }
}
