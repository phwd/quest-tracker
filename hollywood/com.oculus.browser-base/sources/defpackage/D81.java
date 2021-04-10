package defpackage;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.tabs.TabLayout;

/* renamed from: D81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D81 {

    /* renamed from: a  reason: collision with root package name */
    public Drawable f7870a;
    public CharSequence b;
    public CharSequence c;
    public int d = -1;
    public View e;
    public TabLayout f;
    public G81 g;

    public boolean a() {
        TabLayout tabLayout = this.f;
        if (tabLayout != null) {
            return tabLayout.h() == this.d;
        }
        throw new IllegalArgumentException("Tab not attached to a TabLayout");
    }

    public void b() {
        TabLayout tabLayout = this.f;
        if (tabLayout != null) {
            tabLayout.o(this, true);
            return;
        }
        throw new IllegalArgumentException("Tab not attached to a TabLayout");
    }

    public D81 c(Drawable drawable) {
        this.f7870a = drawable;
        TabLayout tabLayout = this.f;
        if (tabLayout.f0 == 1 || tabLayout.i0 == 2) {
            tabLayout.v(true);
        }
        e();
        return this;
    }

    public D81 d(CharSequence charSequence) {
        if (TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(charSequence)) {
            this.g.setContentDescription(charSequence);
        }
        this.b = charSequence;
        e();
        return this;
    }

    public void e() {
        G81 g81 = this.g;
        if (g81 != null) {
            g81.a();
        }
    }
}
