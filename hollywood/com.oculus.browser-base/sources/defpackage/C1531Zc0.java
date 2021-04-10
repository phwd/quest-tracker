package defpackage;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;
import com.google.android.material.internal.CheckableImageButton;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Zc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1531Zc0 extends OE {
    public final LinkedHashSet M0 = new LinkedHashSet();
    public final LinkedHashSet N0 = new LinkedHashSet();
    public final LinkedHashSet O0 = new LinkedHashSet();
    public final LinkedHashSet P0 = new LinkedHashSet();
    public int Q0;
    public DateSelector R0;
    public JC0 S0;
    public CalendarConstraints T0;
    public C1104Sc0 U0;
    public int V0;
    public CharSequence W0;
    public boolean X0;
    public int Y0;
    public TextView Z0;
    public CheckableImageButton a1;
    public C3234jd0 b1;
    public Button c1;

    public static int l1(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.f21510_resource_name_obfuscated_RES_2131165770);
        int i = Month.A().f9692J;
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f21570_resource_name_obfuscated_RES_2131165776) * i;
        return ((i - 1) * resources.getDimensionPixelOffset(R.dimen.f21710_resource_name_obfuscated_RES_2131165790)) + dimensionPixelSize + (dimensionPixelOffset * 2);
    }

    public static boolean m1(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(AbstractC0251Ec0.c(context, R.attr.f6210_resource_name_obfuscated_RES_2130969067, C1104Sc0.class.getCanonicalName()), new int[]{16843277});
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public final void C0(Bundle bundle) {
        super.C0(bundle);
        bundle.putInt("OVERRIDE_THEME_RES_ID", this.Q0);
        bundle.putParcelable("DATE_SELECTOR_KEY", this.R0);
        C0397Gl gl = new C0397Gl(this.T0);
        Month month = this.U0.C0;
        if (month != null) {
            gl.e = Long.valueOf(month.L);
        }
        if (gl.e == null) {
            long j = Month.A().L;
            long j2 = gl.c;
            if (j2 > j || j > gl.d) {
                j = j2;
            }
            gl.e = Long.valueOf(j);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("DEEP_COPY_VALIDATOR_KEY", gl.f);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", new CalendarConstraints(Month.h(gl.c), Month.h(gl.d), Month.h(gl.e.longValue()), (CalendarConstraints.DateValidator) bundle2.getParcelable("DEEP_COPY_VALIDATOR_KEY"), null));
        bundle.putInt("TITLE_TEXT_RES_ID_KEY", this.V0);
        bundle.putCharSequence("TITLE_TEXT_KEY", this.W0);
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void D0() {
        super.D0();
        Window window = h1().getWindow();
        if (this.X0) {
            window.setLayout(-1, -1);
            window.setBackgroundDrawable(this.b1);
        } else {
            window.setLayout(-2, -2);
            int dimensionPixelOffset = I().getDimensionPixelOffset(R.dimen.f21590_resource_name_obfuscated_RES_2131165778);
            Rect rect = new Rect(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset);
            window.setBackgroundDrawable(new InsetDrawable((Drawable) this.b1, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset));
            window.getDecorView().setOnTouchListener(new T10(h1(), rect));
        }
        n1();
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public void E0() {
        this.S0.y0.clear();
        super.E0();
    }

    @Override // defpackage.OE
    public final Dialog g1(Bundle bundle) {
        Context P02 = P0();
        Context P03 = P0();
        int i = this.Q0;
        if (i == 0) {
            i = this.R0.k(P03);
        }
        Dialog dialog = new Dialog(P02, i);
        Context context = dialog.getContext();
        this.X0 = m1(context);
        int c = AbstractC0251Ec0.c(context, R.attr.f3250_resource_name_obfuscated_RES_2130968771, C1531Zc0.class.getCanonicalName());
        C3234jd0 jd0 = new C3234jd0(C3553lT0.b(context, null, R.attr.f6210_resource_name_obfuscated_RES_2130969067, R.style.f75500_resource_name_obfuscated_RES_2132018123).a());
        this.b1 = jd0;
        jd0.H.b = new EK(context);
        jd0.s();
        this.b1.o(ColorStateList.valueOf(c));
        C3234jd0 jd02 = this.b1;
        View decorView = dialog.getWindow().getDecorView();
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        jd02.n(decorView.getElevation());
        return dialog;
    }

    @Override // defpackage.OE, defpackage.AbstractComponentCallbacksC3550lS
    public final void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle == null) {
            bundle = this.K;
        }
        this.Q0 = bundle.getInt("OVERRIDE_THEME_RES_ID");
        this.R0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.T0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.V0 = bundle.getInt("TITLE_TEXT_RES_ID_KEY");
        this.W0 = bundle.getCharSequence("TITLE_TEXT_KEY");
        this.Y0 = bundle.getInt("INPUT_MODE_KEY");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public final View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(this.X0 ? R.layout.f39690_resource_name_obfuscated_RES_2131624278 : R.layout.f39680_resource_name_obfuscated_RES_2131624277, viewGroup);
        Context context = inflate.getContext();
        if (this.X0) {
            inflate.findViewById(R.id.mtrl_calendar_frame).setLayoutParams(new LinearLayout.LayoutParams(l1(context), -2));
        } else {
            View findViewById = inflate.findViewById(R.id.mtrl_calendar_main_pane);
            View findViewById2 = inflate.findViewById(R.id.mtrl_calendar_frame);
            findViewById.setLayoutParams(new LinearLayout.LayoutParams(l1(context), -1));
            Resources resources = P0().getResources();
            int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.f21730_resource_name_obfuscated_RES_2131165792) + resources.getDimensionPixelOffset(R.dimen.f21750_resource_name_obfuscated_RES_2131165794) + resources.getDimensionPixelSize(R.dimen.f21740_resource_name_obfuscated_RES_2131165793);
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f21580_resource_name_obfuscated_RES_2131165777);
            int i = C0032Al0.F;
            findViewById2.setMinimumHeight(dimensionPixelOffset + dimensionPixelSize + (resources.getDimensionPixelOffset(R.dimen.f21720_resource_name_obfuscated_RES_2131165791) * (i - 1)) + (resources.getDimensionPixelSize(R.dimen.f21530_resource_name_obfuscated_RES_2131165772) * i) + resources.getDimensionPixelOffset(R.dimen.f21500_resource_name_obfuscated_RES_2131165769));
        }
        TextView textView = (TextView) inflate.findViewById(R.id.mtrl_picker_header_selection_text);
        this.Z0 = textView;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        textView.setAccessibilityLiveRegion(1);
        this.a1 = (CheckableImageButton) inflate.findViewById(R.id.mtrl_picker_header_toggle);
        TextView textView2 = (TextView) inflate.findViewById(R.id.mtrl_picker_title_text);
        CharSequence charSequence = this.W0;
        if (charSequence != null) {
            textView2.setText(charSequence);
        } else {
            textView2.setText(this.V0);
        }
        this.a1.setTag("TOGGLE_BUTTON_TAG");
        CheckableImageButton checkableImageButton = this.a1;
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842912}, AbstractC5544x8.a(context, R.drawable.f33580_resource_name_obfuscated_RES_2131231398));
        stateListDrawable.addState(new int[0], AbstractC5544x8.a(context, R.drawable.f33600_resource_name_obfuscated_RES_2131231400));
        checkableImageButton.setImageDrawable(stateListDrawable);
        this.a1.setChecked(this.Y0 != 0);
        AbstractC1920bu1.n(this.a1, null);
        p1(this.a1);
        this.a1.setOnClickListener(new View$OnClickListenerC1470Yc0(this));
        this.c1 = (Button) inflate.findViewById(R.id.confirm_button);
        if (this.R0.y()) {
            this.c1.setEnabled(true);
        } else {
            this.c1.setEnabled(false);
        }
        this.c1.setTag("CONFIRM_BUTTON_TAG");
        this.c1.setOnClickListener(new View$OnClickListenerC1287Vc0(this));
        Button button = (Button) inflate.findViewById(R.id.cancel_button);
        button.setTag("CANCEL_BUTTON_TAG");
        button.setOnClickListener(new View$OnClickListenerC1348Wc0(this));
        return inflate;
    }

    public final void n1() {
        JC0 jc0;
        DateSelector dateSelector = this.R0;
        Context P02 = P0();
        int i = this.Q0;
        if (i == 0) {
            i = this.R0.k(P02);
        }
        CalendarConstraints calendarConstraints = this.T0;
        C1104Sc0 sc0 = new C1104Sc0();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.H);
        sc0.U0(bundle);
        this.U0 = sc0;
        if (this.a1.isChecked()) {
            DateSelector dateSelector2 = this.R0;
            CalendarConstraints calendarConstraints2 = this.T0;
            jc0 = new C3747md0();
            Bundle bundle2 = new Bundle();
            bundle2.putParcelable("DATE_SELECTOR_KEY", dateSelector2);
            bundle2.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints2);
            jc0.U0(bundle2);
        } else {
            jc0 = this.U0;
        }
        this.S0 = jc0;
        o1();
        C0317Fe fe = new C0317Fe(w());
        fe.q(R.id.mtrl_calendar_frame, this.S0);
        fe.h();
        JC0 jc02 = this.S0;
        jc02.y0.add(new C1409Xc0(this));
    }

    public final void o1() {
        String j = this.R0.j(x());
        this.Z0.setContentDescription(String.format(O(R.string.f55450_resource_name_obfuscated_RES_2131952862), j));
        this.Z0.setText(j);
    }

    @Override // defpackage.OE
    public final void onCancel(DialogInterface dialogInterface) {
        Iterator it = this.O0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnCancelListener) it.next()).onCancel(dialogInterface);
        }
    }

    @Override // defpackage.OE
    public final void onDismiss(DialogInterface dialogInterface) {
        Iterator it = this.P0.iterator();
        while (it.hasNext()) {
            ((DialogInterface.OnDismissListener) it.next()).onDismiss(dialogInterface);
        }
        ViewGroup viewGroup = (ViewGroup) this.k0;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        if (!this.J0) {
            f1(true, true);
        }
    }

    public final void p1(CheckableImageButton checkableImageButton) {
        String str;
        if (this.a1.isChecked()) {
            str = checkableImageButton.getContext().getString(R.string.f55700_resource_name_obfuscated_RES_2131952887);
        } else {
            str = checkableImageButton.getContext().getString(R.string.f55720_resource_name_obfuscated_RES_2131952889);
        }
        this.a1.setContentDescription(str);
    }
}
