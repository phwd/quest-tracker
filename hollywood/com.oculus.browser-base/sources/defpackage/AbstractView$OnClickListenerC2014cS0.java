package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.components.browser_ui.contacts_picker.ContactsPickerToolbar;
import org.chromium.components.browser_ui.widget.NumberRollView;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListLayout;

/* renamed from: cS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractView$OnClickListenerC2014cS0 extends Toolbar implements AbstractC3039iS0, View.OnClickListener, TextView.OnEditorActionListener, AbstractC3180jG {
    public EditText A0;
    public ImageButton B0;
    public AbstractC1843bS0 C0;
    public boolean D0;
    public NumberRollView E0;
    public Drawable F0;
    public Drawable G0;
    public Drawable H0;
    public int I0;
    public int J0;
    public int K0;
    public int L0;
    public int M0;
    public int N0;
    public int O0;
    public int P0;
    public ColorStateList Q0;
    public ColorStateList R0;
    public Yo1 S0;
    public int T0;
    public int U0;
    public int V0;
    public int W0;
    public boolean X0;
    public boolean Y0;
    public int Z0;
    public int a1;
    public boolean v0;
    public C3209jS0 w0;
    public boolean x0;
    public boolean y0;
    public LinearLayout z0;

    public AbstractView$OnClickListenerC2014cS0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void I(CharSequence charSequence) {
        super.I(charSequence);
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof TextView) {
                childAt.setFocusable(true);
                if (!(childAt instanceof Button)) {
                    childAt.setFocusableInTouchMode(true);
                }
            }
        }
    }

    public void M(Yo1 yo1) {
        this.T0 = getResources().getDimensionPixelSize(R.dimen.f26460_resource_name_obfuscated_RES_2131166265);
        this.S0 = yo1;
        yo1.b.add(this);
        a(yo1.f9298a);
    }

    public void N() {
        C3493l60.F.d(this.A0);
    }

    public void O() {
        if (this.x0) {
            this.x0 = false;
            this.A0.setText("");
            N();
            U();
            IC0 ic0 = (IC0) this.C0;
            ic0.N.t("");
            C0472Hs hs = ic0.N;
            hs.U = false;
            hs.g();
            ContactsPickerToolbar contactsPickerToolbar = ic0.K;
            contactsPickerToolbar.h();
            contactsPickerToolbar.I.setOnClickListener(ic0);
            ic0.U.setVisibility(0);
            ic0.S.setVisibility(0);
            HashSet hashSet = new HashSet();
            for (C3638ly lyVar : ic0.Q.c) {
                hashSet.add(lyVar);
            }
            ic0.K.O();
            for (C3638ly lyVar2 : ic0.T) {
                hashSet.add(lyVar2);
            }
            ic0.getHandler().post(new RunnableC5897zC0(ic0, hashSet));
        }
    }

    public void P(C3209jS0 js0, int i, int i2, int i3, boolean z) {
        this.J0 = i;
        this.L0 = i2;
        this.M0 = i3;
        this.w0 = js0;
        js0.d.b(this);
        this.U0 = getResources().getDimensionPixelSize(R.dimen.f24940_resource_name_obfuscated_RES_2131166113);
        this.V0 = getResources().getDimensionPixelSize(R.dimen.f24910_resource_name_obfuscated_RES_2131166110);
        this.W0 = getResources().getDimensionPixelSize(R.dimen.f24920_resource_name_obfuscated_RES_2131166111);
        int color = getResources().getColor(R.color.f10840_resource_name_obfuscated_RES_2131099774);
        this.N0 = color;
        setBackgroundColor(color);
        this.O0 = getResources().getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800);
        Context context = getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        this.Q0 = context.getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829);
        this.R0 = getContext().getColorStateList(R.color.f11310_resource_name_obfuscated_RES_2131099821);
        J(getContext(), R.style.f71300_resource_name_obfuscated_RES_2132017703);
        int i4 = this.J0;
        if (i4 != 0) {
            I(getContext().getText(i4));
        }
        this.F0 = AbstractC2417ep1.f(getContext(), R.drawable.f30910_resource_name_obfuscated_RES_2131231131, R.color.f11390_resource_name_obfuscated_RES_2131099829);
        this.G0 = AbstractC2417ep1.f(getContext(), R.drawable.f30910_resource_name_obfuscated_RES_2131231131, R.color.f11310_resource_name_obfuscated_RES_2131099821);
        this.H0 = AbstractC2417ep1.f(getContext(), R.drawable.f29550_resource_name_obfuscated_RES_2131230995, R.color.f11390_resource_name_obfuscated_RES_2131099829);
        this.Y0 = true;
        this.Z0 = R.string.f61820_resource_name_obfuscated_RES_2131953499;
        this.a1 = R.string.f52690_resource_name_obfuscated_RES_2131952586;
    }

    public final /* synthetic */ void Q() {
        this.A0.setText("");
    }

    public void R() {
        if (this.y0 && this.x0) {
            O();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0030  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void S(int r2) {
        /*
            r1 = this;
            r1.I0 = r2
            r1.h()
            android.widget.ImageButton r2 = r1.I
            r2.setOnClickListener(r1)
            int r2 = r1.I0
            if (r2 == 0) goto L_0x002b
            r0 = 1
            if (r2 == r0) goto L_0x0020
            r0 = 2
            if (r2 == r0) goto L_0x0015
            goto L_0x002b
        L_0x0015:
            android.graphics.drawable.Drawable r2 = r1.H0
            android.content.res.ColorStateList r0 = r1.R0
            r2.setTintList(r0)
            r2 = 2131951846(0x7f1300e6, float:1.9540118E38)
            goto L_0x002c
        L_0x0020:
            android.graphics.drawable.Drawable r2 = r1.H0
            android.content.res.ColorStateList r0 = r1.Q0
            r2.setTintList(r0)
            r2 = 2131951935(0x7f13013f, float:1.9540299E38)
            goto L_0x002c
        L_0x002b:
            r2 = 0
        L_0x002c:
            if (r2 != 0) goto L_0x0030
            r0 = 0
            goto L_0x0032
        L_0x0030:
            android.graphics.drawable.Drawable r0 = r1.H0
        L_0x0032:
            r1.D(r0)
            r1.B(r2)
            r1.X()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractView$OnClickListenerC2014cS0.S(int):void");
    }

    public void T(boolean z) {
        if (this.y0) {
            this.D0 = z;
            Y();
        }
    }

    public void U() {
        s();
        ((C4616ri0) p()).setGroupVisible(this.L0, true);
        ((C4616ri0) p()).setGroupVisible(this.M0, false);
        if (this.y0) {
            this.z0.setVisibility(8);
            Y();
        }
        S(0);
        setBackgroundColor(this.N0);
        F(this.F0);
        int i = this.J0;
        if (i != 0) {
            I(getContext().getText(i));
        }
        this.E0.setVisibility(8);
        this.E0.a(0, false);
        X();
    }

    public final void V() {
        ((C4616ri0) p()).setGroupVisible(this.L0, false);
        ((C4616ri0) p()).setGroupVisible(this.M0, false);
        this.E0.setVisibility(8);
        this.z0.setVisibility(0);
        S(1);
        setBackgroundResource(R.drawable.f34830_resource_name_obfuscated_RES_2131231523);
        Z(this.P0);
        X();
    }

    public void W(List list, boolean z) {
        ((C4616ri0) p()).setGroupVisible(this.L0, false);
        ((C4616ri0) p()).setGroupVisible(this.M0, true);
        ((C4616ri0) p()).setGroupEnabled(this.M0, !list.isEmpty());
        if (this.y0) {
            this.z0.setVisibility(8);
        }
        S(2);
        setBackgroundColor(this.O0);
        F(this.G0);
        I(null);
        this.E0.setVisibility(0);
        if (!z) {
            this.E0.a(0, false);
        }
        this.E0.a(list.size(), true);
        if (this.x0) {
            N();
        }
        X();
    }

    public final void X() {
        Yo1 yo1 = this.S0;
        if (yo1 != null) {
            a(yo1.f9298a);
        }
    }

    public final void Y() {
        MenuItem findItem;
        if (this.y0 && (findItem = ((C4616ri0) p()).findItem(this.K0)) != null) {
            findItem.setVisible(this.D0 && !this.v0 && !this.x0);
        }
    }

    public final void Z(int i) {
    }

    @Override // defpackage.AbstractC3180jG
    public void a(Xo1 xo1) {
        int i;
        int d = SelectableListLayout.d(xo1, getResources());
        int i2 = 0;
        boolean z = this.x0 && !this.v0;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int i3 = xo1.f9235a;
        int i4 = (i3 != 2 || this.x0 || this.v0 || this.I0 != 0) ? 0 : this.T0;
        if (i3 != 2 || !z) {
            marginLayoutParams.setMargins(0, marginLayoutParams.topMargin, 0, marginLayoutParams.bottomMargin);
        } else {
            marginLayoutParams.setMargins(d, marginLayoutParams.topMargin, d, marginLayoutParams.bottomMargin);
            d = 0;
        }
        setLayoutParams(marginLayoutParams);
        if (this.I0 != 0) {
            i2 = this.U0;
        }
        if (this.v0) {
            i = this.V0;
        } else {
            i = this.W0;
        }
        int i5 = i4 + d + i2;
        int paddingTop = getPaddingTop();
        int i6 = d + i;
        int paddingBottom = getPaddingBottom();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setPaddingRelative(i5, paddingTop, i6, paddingBottom);
    }

    @Override // defpackage.AbstractC3039iS0
    public void b(List list) {
        boolean z = this.v0;
        this.v0 = this.w0.d();
        if (this.E0 == null) {
            this.E0 = (NumberRollView) findViewById(R.id.selection_mode_number);
        }
        if (this.v0) {
            W(list, z);
        } else if (this.x0) {
            V();
        } else {
            U();
        }
        if (this.v0) {
            announceForAccessibility(getContext().getString(z ? R.string.f46310_resource_name_obfuscated_RES_2131951948 : R.string.f46320_resource_name_obfuscated_RES_2131951949, Integer.toString(list.size())));
        }
    }

    public void onClick(View view) {
        int i;
        if (this.X0 || (i = this.I0) == 0) {
            return;
        }
        if (i == 1) {
            R();
        } else if (i == 2) {
            this.w0.a();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!this.X0) {
            this.w0.a();
            if (this.x0) {
                O();
            }
        }
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        C3493l60.F.d(textView);
        return false;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.f40080_resource_name_obfuscated_RES_2131624317, this);
        NumberRollView numberRollView = (NumberRollView) findViewById(R.id.selection_mode_number);
        this.E0 = numberRollView;
        numberRollView.K = R.plurals.f42960_resource_name_obfuscated_RES_2131820588;
        numberRollView.L = R.string.f61170_resource_name_obfuscated_RES_2131953434;
    }

    public void setBackgroundColor(int i) {
        super.setBackgroundColor(i);
        Z(i);
    }
}
