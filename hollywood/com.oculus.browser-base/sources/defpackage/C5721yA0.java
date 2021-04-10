package defpackage;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: yA0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5721yA0 extends BA0 {
    public final List W = new ArrayList();
    public boolean a0 = true;
    public final int b0;
    public final ArrayList c0 = new ArrayList();
    public final int d0;
    public final int e0;
    public GridLayout f0;
    public View g0;
    public C5084uR0 h0;
    public boolean i0;
    public boolean j0 = true;
    public boolean k0;
    public boolean l0;
    public AbstractC5381wA0 m0;

    public C5721yA0(Context context, String str, AbstractView$OnClickListenerC5891zA0 za0) {
        super(context, str, za0, null);
        this.b0 = context.getResources().getDimensionPixelSize(R.dimen.f19010_resource_name_obfuscated_RES_2131165520);
        this.d0 = context.getResources().getDimensionPixelSize(R.dimen.f18970_resource_name_obfuscated_RES_2131165516);
        this.e0 = context.getResources().getDimensionPixelSize(R.dimen.f23940_resource_name_obfuscated_RES_2131166013);
        f(null, null);
    }

    @Override // defpackage.BA0
    public void a(LinearLayout linearLayout) {
        Context context = linearLayout.getContext();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.f40550_resource_name_obfuscated_RES_2131624364, (ViewGroup) null);
        ((TextView) viewGroup.findViewById(R.id.message)).setText(getContext().getString(R.string.f58510_resource_name_obfuscated_RES_2131953168));
        this.g0 = viewGroup;
        GridLayout gridLayout = new GridLayout(context, null);
        this.f0 = gridLayout;
        gridLayout.q(4);
        linearLayout.addView(this.f0, new LinearLayout.LayoutParams(-1, -2));
    }

    @Override // defpackage.BA0
    public int b() {
        C5084uR0 ur0 = this.h0;
        if (ur0 == null) {
            return 0;
        }
        if (ur0.e() == 0 && this.a0) {
            return 2;
        }
        if (this.h0.d() == null) {
            return 1;
        }
        return 0;
    }

    @Override // defpackage.BA0
    public void c(View view) {
        int i;
        for (int i2 = 0; i2 < this.c0.size(); i2++) {
            C5551xA0 xa0 = (C5551xA0) this.c0.get(i2);
            boolean z = xa0.c == view || xa0.d == view || xa0.e == view;
            C1997cK cKVar = xa0.b;
            if (cKVar == null && z) {
                ((TA0) this.F).k(this);
                return;
            } else if (cKVar != null && xa0.f == view) {
                TA0 ta0 = (TA0) this.F;
                int m = this == ta0.c0 ? ((AB0) ta0.I).m(1, cKVar, ta0.Q) : 3;
                if (this == ta0.e0) {
                    m = ((AB0) ta0.I).m(3, cKVar, null);
                }
                if (this == ta0.f0) {
                    m = ((AB0) ta0.I).m(4, cKVar, null);
                }
                ta0.r(this, m);
                return;
            }
        }
        for (int i3 = 0; i3 < this.c0.size(); i3++) {
            C5551xA0 xa02 = (C5551xA0) this.c0.get(i3);
            View view2 = xa02.c;
            boolean z2 = view2 == view || xa02.d == view || xa02.e == view;
            if (xa02.b != null) {
                ((RadioButton) view2).setChecked(z2);
                if (z2) {
                    xa02.g.l(xa02.b);
                    C5721yA0 ya0 = xa02.g;
                    AbstractView$OnClickListenerC5891zA0 za0 = ya0.F;
                    C1997cK cKVar2 = xa02.b;
                    TA0 ta02 = (TA0) za0;
                    if (ya0 == ta02.c0 && ta02.q0.d() != cKVar2) {
                        ta02.q0.g(cKVar2);
                        i = ((AB0) ta02.I).n(1, cKVar2, ta02.Q);
                    } else if (ya0 == ta02.d0 && ta02.r0.d() != cKVar2) {
                        ta02.r0.g(cKVar2);
                        i = ((AB0) ta02.I).n(2, cKVar2, ta02.Q);
                    } else if (ya0 == ta02.e0) {
                        ta02.s0.g(cKVar2);
                        i = ((AB0) ta02.I).n(3, cKVar2, ta02.Q);
                    } else if (ya0 == ta02.f0) {
                        ta02.p0.g(cKVar2);
                        i = ((AB0) ta02.I).n(4, cKVar2, null);
                    } else {
                        i = 3;
                    }
                    ta02.r(ya0, i);
                }
            }
        }
    }

    @Override // defpackage.BA0
    public void g() {
        if (this.I) {
            int i = this.f7721J;
            if (i == 5) {
                this.V = false;
                this.f0.setVisibility(0);
                j(false);
            } else if (i == 6) {
                this.V = false;
                this.f0.setVisibility(8);
                j(true);
            } else {
                this.V = true;
                this.f0.setVisibility(8);
                j(false);
            }
            super.g();
        }
    }

    public final CharSequence h(C1997cK cKVar, boolean z, boolean z2, boolean z3) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (!z) {
            spannableStringBuilder.append((CharSequence) cKVar.a());
            if (z2) {
                spannableStringBuilder.setSpan(new StyleSpan(1), 0, spannableStringBuilder.length(), 0);
            }
        }
        String string = z3 ? getContext().getString(R.string.f47030_resource_name_obfuscated_RES_2131952020) : "\n";
        if (!TextUtils.isEmpty(cKVar.c())) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append((CharSequence) string);
            }
            spannableStringBuilder.append((CharSequence) cKVar.c());
        }
        if (!TextUtils.isEmpty(cKVar.i[2])) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append((CharSequence) string);
            }
            spannableStringBuilder.append((CharSequence) cKVar.i[2]);
        }
        if (!TextUtils.isEmpty(cKVar.f)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append((CharSequence) string);
            }
            spannableStringBuilder.append((CharSequence) cKVar.f);
        }
        if (!cKVar.d() && !TextUtils.isEmpty(cKVar.d)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append((CharSequence) string);
            }
            String str = cKVar.d;
            spannableStringBuilder.append((CharSequence) str);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getContext().getResources().getColor(R.color.f11590_resource_name_obfuscated_RES_2131099849));
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(14, true);
            int length = spannableStringBuilder.length() - str.length();
            spannableStringBuilder.setSpan(foregroundColorSpan, length, spannableStringBuilder.length(), 0);
            spannableStringBuilder.setSpan(absoluteSizeSpan, length, spannableStringBuilder.length(), 0);
        }
        return spannableStringBuilder;
    }

    public void i(boolean z) {
        C5084uR0 ur0 = this.h0;
        if ((ur0 != null && ur0.e() > 0) || !z) {
            AbstractC5381wA0 wa0 = this.m0;
            if (wa0 != null) {
                int i = this.h0.b;
                AB0 ab0 = (AB0) wa0;
                if (ab0.C.d() != null) {
                    C2892hd hdVar = (C2892hd) ab0.C.d();
                    if (z) {
                        hdVar.m();
                    } else {
                        hdVar.n();
                    }
                    ab0.w.o(1, ab0.C);
                }
            }
            int i2 = this.f7721J;
            this.f7721J = z ? 5 : 4;
            g();
            C5084uR0 ur02 = this.h0;
            if (ur02 != null && i2 == 3) {
                l(ur02.d());
                return;
            }
            return;
        }
        this.f7721J = 3;
        g();
    }

    public final void j(boolean z) {
        if (z) {
            if (this.g0.getParent() == null) {
                ViewGroup viewGroup = (ViewGroup) this.f0.getParent();
                viewGroup.addView(this.g0, viewGroup.indexOfChild(this.f0));
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.g0.getLayoutParams();
                marginLayoutParams.width = -1;
                marginLayoutParams.height = -2;
                marginLayoutParams.bottomMargin = getContext().getResources().getDimensionPixelSize(R.dimen.f23980_resource_name_obfuscated_RES_2131166017);
                this.g0.requestLayout();
            }
        } else if (this.g0.getParent() != null) {
            ((ViewGroup) this.g0.getParent()).removeView(this.g0);
        }
    }

    public void k(C5084uR0 ur0) {
        C5084uR0 ur02;
        int i;
        this.h0 = ur0;
        C1997cK d = ur0.d();
        l(d);
        this.f0.removeAllViews();
        this.c0.clear();
        this.W.clear();
        TA0 ta0 = (TA0) this.F;
        String str = null;
        if (this == ta0.c0) {
            C5084uR0 ur03 = ta0.q0;
            int i2 = ur03.c;
            if (i2 == -1 || i2 == -2) {
                str = ur03.e;
                if (i2 != -2 || TextUtils.isEmpty(str)) {
                    Context context = ta0.H;
                    if (i2 == -1) {
                        i = ta0.R.c;
                    } else {
                        i = ta0.R.d;
                    }
                    str = context.getString(i);
                }
            }
        } else if (this == ta0.f0) {
            Objects.requireNonNull(ta0.p0);
        }
        if (!TextUtils.isEmpty(str)) {
            GridLayout gridLayout = this.f0;
            int size = this.c0.size();
            TA0 ta02 = (TA0) this.F;
            C5551xA0 xa0 = new C5551xA0(this, gridLayout, size, this == ta02.c0 && (ur02 = ta02.q0) != null && ur02.c == -2 ? 3 : 2, null, false);
            this.c0.add(xa0);
            xa0.d.setText(str);
        }
        int i3 = -1;
        for (int i4 = 0; i4 < ur0.e(); i4++) {
            int size2 = this.c0.size();
            if (i3 == -1) {
                i3 = size2;
            }
            C1997cK c = ur0.c(i4);
            C5551xA0 xa02 = new C5551xA0(this, this.f0, size2, 0, c, c == d);
            this.c0.add(xa02);
            this.W.add(xa02.d);
        }
        if (i3 != -1) {
            ((C5551xA0) this.c0.get(i3)).c.setId(R.id.payments_first_radio_button);
        }
        if (ur0.b() != 0 && this.a0) {
            GridLayout gridLayout2 = this.f0;
            C5551xA0 xa03 = new C5551xA0(this, gridLayout2, gridLayout2.getChildCount(), 1, null, false);
            xa03.d.setText(getContext().getString(ur0.b()));
            xa03.c.setId(R.id.payments_add_option_button);
            this.c0.add(xa03);
        }
        g();
    }

    public final void l(C1997cK cKVar) {
        if (cKVar == null || (this.f7721J == 3 && this.j0)) {
            if (!this.i0) {
                e(TextUtils.TruncateAt.END, true, null, false);
                this.i0 = true;
            }
        } else if (this.i0) {
            e(null, false, null, false);
            this.i0 = false;
        }
        if (cKVar == null) {
            this.U = null;
            this.O.setBackgroundResource(0);
            this.O.setImageDrawable(this.U);
            if (!this.l0) {
                AbstractC3153j7.i(this.S, R.style.f72010_resource_name_obfuscated_RES_2132017774);
                this.l0 = true;
            }
            Context context = getContext();
            C5084uR0 ur0 = this.h0;
            TextView textView = this.S;
            int e = ur0.e();
            if (e == 0) {
                textView.setText((CharSequence) null);
            } else {
                if (textView.getLayout() == null && e > 1) {
                    textView.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC5254vR0(ur0, textView, context));
                }
                textView.setText(AbstractC5424wR0.a(context, ur0, textView.getLayout(), textView.getPaint()));
            }
        } else {
            this.U = cKVar.h;
            this.O.setBackgroundResource(0);
            this.O.setImageDrawable(this.U);
            if (this.l0) {
                AbstractC3153j7.i(this.S, R.style.f71850_resource_name_obfuscated_RES_2132017758);
                this.l0 = false;
            }
            if (this.k0 && this.f7721J == 3) {
                f(cKVar.a(), h(cKVar, true, false, this.i0));
            } else {
                f(h(cKVar, false, false, this.i0), null);
            }
        }
        g();
    }
}
