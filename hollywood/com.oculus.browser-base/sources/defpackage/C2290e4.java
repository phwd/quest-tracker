package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import java.util.Objects;

/* renamed from: e4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2290e4 {

    /* renamed from: a  reason: collision with root package name */
    public final C1598a4 f9828a;
    public final int b;

    public C2290e4(Context context) {
        this(context, DialogC2461f4.d(context, 0));
    }

    public DialogC2461f4 a() {
        ListAdapter listAdapter;
        int i;
        DialogC2461f4 f4Var = new DialogC2461f4(this.f9828a.f9407a, this.b);
        C1598a4 a4Var = this.f9828a;
        C2120d4 d4Var = f4Var.H;
        View view = a4Var.e;
        if (view != null) {
            d4Var.G = view;
        } else {
            CharSequence charSequence = a4Var.d;
            if (charSequence != null) {
                d4Var.e = charSequence;
                TextView textView = d4Var.E;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = a4Var.c;
            if (drawable != null) {
                d4Var.C = drawable;
                d4Var.B = 0;
                ImageView imageView = d4Var.D;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    d4Var.D.setImageDrawable(drawable);
                }
            }
        }
        CharSequence charSequence2 = a4Var.f;
        if (charSequence2 != null) {
            d4Var.f = charSequence2;
            TextView textView2 = d4Var.F;
            if (textView2 != null) {
                textView2.setText(charSequence2);
            }
        }
        CharSequence charSequence3 = a4Var.g;
        if (charSequence3 != null) {
            d4Var.e(-1, charSequence3, a4Var.h, null, null);
        }
        CharSequence charSequence4 = a4Var.i;
        if (charSequence4 != null) {
            d4Var.e(-2, charSequence4, a4Var.j, null, null);
        }
        if (!(a4Var.n == null && a4Var.o == null)) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) a4Var.b.inflate(d4Var.L, (ViewGroup) null);
            if (a4Var.t) {
                listAdapter = new X3(a4Var, a4Var.f9407a, d4Var.M, 16908308, a4Var.n, alertController$RecycleListView);
            } else {
                if (a4Var.u) {
                    i = d4Var.N;
                } else {
                    i = d4Var.O;
                }
                listAdapter = a4Var.o;
                if (listAdapter == null) {
                    listAdapter = new C1949c4(a4Var.f9407a, i, 16908308, a4Var.n);
                }
            }
            d4Var.H = listAdapter;
            d4Var.I = a4Var.v;
            if (a4Var.p != null) {
                alertController$RecycleListView.setOnItemClickListener(new Y3(a4Var, d4Var));
            } else if (a4Var.w != null) {
                alertController$RecycleListView.setOnItemClickListener(new Z3(a4Var, alertController$RecycleListView, d4Var));
            }
            if (a4Var.u) {
                alertController$RecycleListView.setChoiceMode(1);
            } else if (a4Var.t) {
                alertController$RecycleListView.setChoiceMode(2);
            }
            d4Var.g = alertController$RecycleListView;
        }
        View view2 = a4Var.r;
        if (view2 != null) {
            d4Var.h = view2;
            d4Var.i = 0;
            d4Var.n = false;
        } else {
            int i2 = a4Var.q;
            if (i2 != 0) {
                d4Var.h = null;
                d4Var.i = i2;
                d4Var.n = false;
            }
        }
        f4Var.setCancelable(this.f9828a.k);
        if (this.f9828a.k) {
            f4Var.setCanceledOnTouchOutside(true);
        }
        f4Var.setOnCancelListener(this.f9828a.l);
        Objects.requireNonNull(this.f9828a);
        f4Var.setOnDismissListener(null);
        DialogInterface.OnKeyListener onKeyListener = this.f9828a.m;
        if (onKeyListener != null) {
            f4Var.setOnKeyListener(onKeyListener);
        }
        return f4Var;
    }

    public C2290e4 b(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
        C1598a4 a4Var = this.f9828a;
        a4Var.o = listAdapter;
        a4Var.p = onClickListener;
        return this;
    }

    public C2290e4 c(int i) {
        C1598a4 a4Var = this.f9828a;
        a4Var.f = a4Var.f9407a.getText(i);
        return this;
    }

    public C2290e4 d(int i, DialogInterface.OnClickListener onClickListener) {
        C1598a4 a4Var = this.f9828a;
        a4Var.i = a4Var.f9407a.getText(i);
        this.f9828a.j = onClickListener;
        return this;
    }

    public C2290e4 e(int i, DialogInterface.OnClickListener onClickListener) {
        C1598a4 a4Var = this.f9828a;
        a4Var.g = a4Var.f9407a.getText(i);
        this.f9828a.h = onClickListener;
        return this;
    }

    public C2290e4 f(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        C1598a4 a4Var = this.f9828a;
        a4Var.g = charSequence;
        a4Var.h = onClickListener;
        return this;
    }

    public C2290e4 g(int i) {
        C1598a4 a4Var = this.f9828a;
        a4Var.d = a4Var.f9407a.getText(i);
        return this;
    }

    public C2290e4 h(View view) {
        C1598a4 a4Var = this.f9828a;
        a4Var.r = view;
        a4Var.q = 0;
        return this;
    }

    public DialogC2461f4 i() {
        DialogC2461f4 a2 = a();
        a2.show();
        return a2;
    }

    public C2290e4(Context context, int i) {
        this.f9828a = new C1598a4(new ContextThemeWrapper(context, DialogC2461f4.d(context, i)));
        this.b = i;
    }
}
