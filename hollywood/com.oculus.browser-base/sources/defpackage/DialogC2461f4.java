package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: f4  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC2461f4 extends AbstractDialogC3498l8 {
    public final C2120d4 H = new C2120d4(getContext(), this, getWindow());

    public DialogC2461f4(Context context, int i) {
        super(context, d(context, i));
    }

    public static int d(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.f1690_resource_name_obfuscated_RES_2130968615, typedValue, true);
        return typedValue.resourceId;
    }

    public Button c(int i) {
        C2120d4 d4Var = this.H;
        Objects.requireNonNull(d4Var);
        if (i == -3) {
            return d4Var.w;
        }
        if (i == -2) {
            return d4Var.s;
        }
        if (i != -1) {
            return null;
        }
        return d4Var.o;
    }

    @Override // defpackage.AbstractDialogC3498l8
    public void onCreate(Bundle bundle) {
        int i;
        boolean z;
        View view;
        ListAdapter listAdapter;
        View view2;
        View findViewById;
        super.onCreate(bundle);
        C2120d4 d4Var = this.H;
        if (d4Var.K == 0) {
            i = d4Var.f9743J;
        } else {
            i = d4Var.f9743J;
        }
        d4Var.b.setContentView(i);
        View findViewById2 = d4Var.c.findViewById(R.id.parentPanel);
        View findViewById3 = findViewById2.findViewById(R.id.topPanel);
        View findViewById4 = findViewById2.findViewById(R.id.contentPanel);
        View findViewById5 = findViewById2.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById2.findViewById(R.id.customPanel);
        View view3 = d4Var.h;
        int i2 = 0;
        if (view3 == null) {
            view3 = d4Var.i != 0 ? LayoutInflater.from(d4Var.f9744a).inflate(d4Var.i, viewGroup, false) : null;
        }
        boolean z2 = view3 != null;
        if (!z2 || !C2120d4.a(view3)) {
            d4Var.c.setFlags(131072, 131072);
        }
        if (z2) {
            FrameLayout frameLayout = (FrameLayout) d4Var.c.findViewById(R.id.custom);
            frameLayout.addView(view3, new ViewGroup.LayoutParams(-1, -1));
            if (d4Var.n) {
                frameLayout.setPadding(d4Var.j, d4Var.k, d4Var.l, d4Var.m);
            }
            if (d4Var.g != null) {
                ((C5545x80) viewGroup.getLayoutParams()).f11593a = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View findViewById6 = viewGroup.findViewById(R.id.topPanel);
        View findViewById7 = viewGroup.findViewById(R.id.contentPanel);
        View findViewById8 = viewGroup.findViewById(R.id.buttonPanel);
        ViewGroup d = d4Var.d(findViewById6, findViewById3);
        ViewGroup d2 = d4Var.d(findViewById7, findViewById4);
        ViewGroup d3 = d4Var.d(findViewById8, findViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) d4Var.c.findViewById(R.id.scrollView);
        d4Var.A = nestedScrollView;
        nestedScrollView.setFocusable(false);
        d4Var.A.setNestedScrollingEnabled(false);
        TextView textView = (TextView) d2.findViewById(16908299);
        d4Var.F = textView;
        if (textView != null) {
            CharSequence charSequence = d4Var.f;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setVisibility(8);
                d4Var.A.removeView(d4Var.F);
                if (d4Var.g != null) {
                    ViewGroup viewGroup2 = (ViewGroup) d4Var.A.getParent();
                    int indexOfChild = viewGroup2.indexOfChild(d4Var.A);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView(d4Var.g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    d2.setVisibility(8);
                }
            }
        }
        Button button = (Button) d3.findViewById(16908313);
        d4Var.o = button;
        button.setOnClickListener(d4Var.R);
        if (!TextUtils.isEmpty(d4Var.p) || d4Var.r != null) {
            d4Var.o.setText(d4Var.p);
            Drawable drawable = d4Var.r;
            if (drawable != null) {
                int i3 = d4Var.d;
                drawable.setBounds(0, 0, i3, i3);
                d4Var.o.setCompoundDrawables(d4Var.r, null, null, null);
            }
            d4Var.o.setVisibility(0);
            z = true;
        } else {
            d4Var.o.setVisibility(8);
            z = false;
        }
        Button button2 = (Button) d3.findViewById(16908314);
        d4Var.s = button2;
        button2.setOnClickListener(d4Var.R);
        if (!TextUtils.isEmpty(d4Var.t) || d4Var.v != null) {
            d4Var.s.setText(d4Var.t);
            Drawable drawable2 = d4Var.v;
            if (drawable2 != null) {
                int i4 = d4Var.d;
                drawable2.setBounds(0, 0, i4, i4);
                d4Var.s.setCompoundDrawables(d4Var.v, null, null, null);
            }
            d4Var.s.setVisibility(0);
            z |= true;
        } else {
            d4Var.s.setVisibility(8);
        }
        Button button3 = (Button) d3.findViewById(16908315);
        d4Var.w = button3;
        button3.setOnClickListener(d4Var.R);
        if (!TextUtils.isEmpty(d4Var.x) || d4Var.z != null) {
            d4Var.w.setText(d4Var.x);
            Drawable drawable3 = d4Var.z;
            if (drawable3 != null) {
                int i5 = d4Var.d;
                drawable3.setBounds(0, 0, i5, i5);
                view = null;
                d4Var.w.setCompoundDrawables(d4Var.z, null, null, null);
            } else {
                view = null;
            }
            d4Var.w.setVisibility(0);
            z |= true;
        } else {
            d4Var.w.setVisibility(8);
            view = null;
        }
        Context context = d4Var.f9744a;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.f1670_resource_name_obfuscated_RES_2130968613, typedValue, true);
        if (typedValue.data != 0) {
            if (z) {
                d4Var.b(d4Var.o);
            } else if (z) {
                d4Var.b(d4Var.s);
            } else if (z) {
                d4Var.b(d4Var.w);
            }
        }
        if (!(z)) {
            d3.setVisibility(8);
        }
        if (d4Var.G != null) {
            d.addView(d4Var.G, 0, new ViewGroup.LayoutParams(-1, -2));
            d4Var.c.findViewById(R.id.title_template).setVisibility(8);
        } else {
            d4Var.D = (ImageView) d4Var.c.findViewById(16908294);
            if (!(!TextUtils.isEmpty(d4Var.e)) || !d4Var.P) {
                d4Var.c.findViewById(R.id.title_template).setVisibility(8);
                d4Var.D.setVisibility(8);
                d.setVisibility(8);
            } else {
                TextView textView2 = (TextView) d4Var.c.findViewById(R.id.alertTitle);
                d4Var.E = textView2;
                textView2.setText(d4Var.e);
                int i6 = d4Var.B;
                if (i6 != 0) {
                    d4Var.D.setImageResource(i6);
                } else {
                    Drawable drawable4 = d4Var.C;
                    if (drawable4 != null) {
                        d4Var.D.setImageDrawable(drawable4);
                    } else {
                        d4Var.E.setPadding(d4Var.D.getPaddingLeft(), d4Var.D.getPaddingTop(), d4Var.D.getPaddingRight(), d4Var.D.getPaddingBottom());
                        d4Var.D.setVisibility(8);
                    }
                }
            }
        }
        boolean z3 = viewGroup.getVisibility() != 8;
        int i7 = (d == null || d.getVisibility() == 8) ? 0 : 1;
        boolean z4 = d3.getVisibility() != 8;
        if (!z4 && (findViewById = d2.findViewById(R.id.textSpacerNoButtons)) != null) {
            findViewById.setVisibility(0);
        }
        if (i7 != 0) {
            NestedScrollView nestedScrollView2 = d4Var.A;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            if (d4Var.f == null && d4Var.g == null) {
                view2 = view;
            } else {
                view2 = d.findViewById(R.id.titleDividerNoCustom);
            }
            if (view2 != null) {
                view2.setVisibility(0);
            }
        } else {
            View findViewById9 = d2.findViewById(R.id.textSpacerNoTitle);
            if (findViewById9 != null) {
                findViewById9.setVisibility(0);
            }
        }
        ListView listView = d4Var.g;
        if (listView instanceof AlertController$RecycleListView) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) listView;
            Objects.requireNonNull(alertController$RecycleListView);
            if (!z4 || i7 == 0) {
                alertController$RecycleListView.setPadding(alertController$RecycleListView.getPaddingLeft(), i7 != 0 ? alertController$RecycleListView.getPaddingTop() : alertController$RecycleListView.F, alertController$RecycleListView.getPaddingRight(), z4 ? alertController$RecycleListView.getPaddingBottom() : alertController$RecycleListView.G);
            }
        }
        if (!z3) {
            View view4 = d4Var.g;
            if (view4 == null) {
                view4 = d4Var.A;
            }
            if (view4 != null) {
                if (z4) {
                    i2 = 2;
                }
                View findViewById10 = d4Var.c.findViewById(R.id.scrollIndicatorUp);
                View findViewById11 = d4Var.c.findViewById(R.id.scrollIndicatorDown);
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                view4.setScrollIndicators(i7 | i2, 3);
                if (findViewById10 != null) {
                    d2.removeView(findViewById10);
                }
                if (findViewById11 != null) {
                    d2.removeView(findViewById11);
                }
            }
        }
        ListView listView2 = d4Var.g;
        if (listView2 != null && (listAdapter = d4Var.H) != null) {
            listView2.setAdapter(listAdapter);
            int i8 = d4Var.I;
            if (i8 > -1) {
                listView2.setItemChecked(i8, true);
                listView2.setSelection(i8);
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.H.A;
        if (nestedScrollView != null && nestedScrollView.o(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.H.A;
        if (nestedScrollView != null && nestedScrollView.o(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Dialog, defpackage.AbstractDialogC3498l8
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        C2120d4 d4Var = this.H;
        d4Var.e = charSequence;
        TextView textView = d4Var.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
