package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.oculus.browser.R;

/* renamed from: Hl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Hl1 {

    /* renamed from: a  reason: collision with root package name */
    public Toolbar f8179a;
    public int b;
    public View c;
    public View d;
    public Drawable e;
    public Drawable f;
    public Drawable g;
    public boolean h;
    public CharSequence i;
    public CharSequence j;
    public CharSequence k;
    public Window.Callback l;
    public boolean m;
    public C4676s2 n;
    public int o = 0;
    public Drawable p;

    public Hl1(Toolbar toolbar, boolean z) {
        Drawable drawable;
        this.f8179a = toolbar;
        CharSequence charSequence = toolbar.f0;
        this.i = charSequence;
        this.j = toolbar.g0;
        this.h = charSequence != null;
        this.g = toolbar.q();
        CharSequence charSequence2 = null;
        C0514Ii1 q = C0514Ii1.q(toolbar.getContext(), null, FJ0.f8010a, R.attr.f1350_resource_name_obfuscated_RES_2130968581, 0);
        int i2 = 15;
        this.p = q.g(15);
        if (z) {
            CharSequence n2 = q.n(27);
            if (!TextUtils.isEmpty(n2)) {
                this.h = true;
                this.i = n2;
                if ((this.b & 8) != 0) {
                    this.f8179a.I(n2);
                }
            }
            CharSequence n3 = q.n(25);
            if (!TextUtils.isEmpty(n3)) {
                this.j = n3;
                if ((this.b & 8) != 0) {
                    this.f8179a.H(n3);
                }
            }
            Drawable g2 = q.g(20);
            if (g2 != null) {
                this.f = g2;
                g();
            }
            Drawable g3 = q.g(17);
            if (g3 != null) {
                this.e = g3;
                g();
            }
            if (this.g == null && (drawable = this.p) != null) {
                this.g = drawable;
                f();
            }
            a(q.j(10, 0));
            int l2 = q.l(9, 0);
            if (l2 != 0) {
                View inflate = LayoutInflater.from(this.f8179a.getContext()).inflate(l2, (ViewGroup) this.f8179a, false);
                View view = this.d;
                if (!(view == null || (this.b & 16) == 0)) {
                    this.f8179a.removeView(view);
                }
                this.d = inflate;
                if (!(inflate == null || (this.b & 16) == 0)) {
                    this.f8179a.addView(inflate);
                }
                a(this.b | 16);
            }
            int k2 = q.k(13, 0);
            if (k2 > 0) {
                ViewGroup.LayoutParams layoutParams = this.f8179a.getLayoutParams();
                layoutParams.height = k2;
                this.f8179a.setLayoutParams(layoutParams);
            }
            int e2 = q.e(7, -1);
            int e3 = q.e(3, -1);
            if (e2 >= 0 || e3 >= 0) {
                Toolbar toolbar2 = this.f8179a;
                int max = Math.max(e2, 0);
                int max2 = Math.max(e3, 0);
                toolbar2.e();
                toolbar2.b0.a(max, max2);
            }
            int l3 = q.l(28, 0);
            if (l3 != 0) {
                Toolbar toolbar3 = this.f8179a;
                toolbar3.J(toolbar3.getContext(), l3);
            }
            int l4 = q.l(26, 0);
            if (l4 != 0) {
                Toolbar toolbar4 = this.f8179a;
                Context context = toolbar4.getContext();
                toolbar4.R = l4;
                TextView textView = toolbar4.H;
                if (textView != null) {
                    textView.setTextAppearance(context, l4);
                }
            }
            int l5 = q.l(22, 0);
            if (l5 != 0) {
                this.f8179a.G(l5);
            }
        } else {
            if (this.f8179a.q() != null) {
                this.p = this.f8179a.q();
            } else {
                i2 = 11;
            }
            this.b = i2;
        }
        q.b.recycle();
        if (R.string.f44980_resource_name_obfuscated_RES_2131951815 != this.o) {
            this.o = R.string.f44980_resource_name_obfuscated_RES_2131951815;
            ImageButton imageButton = this.f8179a.I;
            if (TextUtils.isEmpty(imageButton != null ? imageButton.getContentDescription() : null)) {
                c(this.o);
            }
        }
        ImageButton imageButton2 = this.f8179a.I;
        this.k = imageButton2 != null ? imageButton2.getContentDescription() : charSequence2;
        Toolbar toolbar5 = this.f8179a;
        Fl1 fl1 = new Fl1(this);
        toolbar5.h();
        toolbar5.I.setOnClickListener(fl1);
    }

    public void a(int i2) {
        View view;
        int i3 = this.b ^ i2;
        this.b = i2;
        if (i3 != 0) {
            if ((i3 & 4) != 0) {
                if ((i2 & 4) != 0) {
                    e();
                }
                f();
            }
            if ((i3 & 3) != 0) {
                g();
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 8) != 0) {
                    this.f8179a.I(this.i);
                    this.f8179a.H(this.j);
                } else {
                    this.f8179a.I(null);
                    this.f8179a.H(null);
                }
            }
            if ((i3 & 16) != 0 && (view = this.d) != null) {
                if ((i2 & 16) != 0) {
                    this.f8179a.addView(view);
                } else {
                    this.f8179a.removeView(view);
                }
            }
        }
    }

    public void b(XP0 xp0) {
        Toolbar toolbar;
        View view = this.c;
        if (view != null && view.getParent() == (toolbar = this.f8179a)) {
            toolbar.removeView(this.c);
        }
        this.c = null;
    }

    public void c(int i2) {
        String str;
        if (i2 == 0) {
            str = null;
        } else {
            str = this.f8179a.getContext().getString(i2);
        }
        this.k = str;
        e();
    }

    public Zu1 d(int i2, long j2) {
        Zu1 a2 = AbstractC1920bu1.a(this.f8179a);
        a2.a(i2 == 0 ? 1.0f : 0.0f);
        a2.c(j2);
        Gl1 gl1 = new Gl1(this, i2);
        View view = (View) a2.f9382a.get();
        if (view != null) {
            a2.e(view, gl1);
        }
        return a2;
    }

    public final void e() {
        if ((this.b & 4) == 0) {
            return;
        }
        if (TextUtils.isEmpty(this.k)) {
            this.f8179a.B(this.o);
        } else {
            this.f8179a.C(this.k);
        }
    }

    public final void f() {
        if ((this.b & 4) != 0) {
            Toolbar toolbar = this.f8179a;
            Drawable drawable = this.g;
            if (drawable == null) {
                drawable = this.p;
            }
            toolbar.D(drawable);
            return;
        }
        this.f8179a.D(null);
    }

    public final void g() {
        Drawable drawable;
        int i2 = this.b;
        if ((i2 & 2) == 0) {
            drawable = null;
        } else if ((i2 & 1) != 0) {
            drawable = this.f;
            if (drawable == null) {
                drawable = this.e;
            }
        } else {
            drawable = this.e;
        }
        this.f8179a.A(drawable);
    }
}
