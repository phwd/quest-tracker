package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.Month;
import com.oculus.browser.R;

/* renamed from: Sc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1104Sc0 extends JC0 {
    public DateSelector A0;
    public CalendarConstraints B0;
    public Month C0;
    public int D0;
    public C0702Ll E0;
    public RecyclerView F0;
    public RecyclerView G0;
    public View H0;
    public View I0;
    public int z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void C0(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.z0);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.A0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.B0);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.C0);
    }

    public LinearLayoutManager e1() {
        return (LinearLayoutManager) this.G0.U;
    }

    public final void f1(int i) {
        this.G0.post(new RunnableC0495Ic0(this, i));
    }

    public void g1(Month month) {
        C0337Fl0 fl0 = (C0337Fl0) this.G0.T;
        int x = fl0.I.F.x(month);
        int t = x - fl0.t(this.C0);
        boolean z = true;
        boolean z2 = Math.abs(t) > 3;
        if (t <= 0) {
            z = false;
        }
        this.C0 = month;
        if (z2 && z) {
            this.G0.p0(x - 3);
            f1(x);
        } else if (z2) {
            this.G0.p0(x + 3);
            f1(x);
        } else {
            f1(x);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle == null) {
            bundle = this.K;
        }
        this.z0 = bundle.getInt("THEME_RES_ID_KEY");
        this.A0 = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.B0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.C0 = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    public void h1(int i) {
        this.D0 = i;
        if (i == 2) {
            RecyclerView recyclerView = this.F0;
            recyclerView.U.N0(((Gz1) recyclerView.T).s(this.C0.I));
            this.H0.setVisibility(0);
            this.I0.setVisibility(8);
        } else if (i == 1) {
            this.H0.setVisibility(8);
            this.I0.setVisibility(0);
            g1(this.C0);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(x(), this.z0);
        this.E0 = new C0702Ll(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month month = this.B0.F;
        if (C1531Zc0.m1(contextThemeWrapper)) {
            i2 = R.layout.f39630_resource_name_obfuscated_RES_2131624272;
            i = 1;
        } else {
            i2 = R.layout.f39580_resource_name_obfuscated_RES_2131624267;
            i = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        AbstractC1920bu1.n(gridView, new C0556Jc0(this));
        gridView.setAdapter((ListAdapter) new C4026oD());
        gridView.setNumColumns(month.f9692J);
        gridView.setEnabled(false);
        this.G0 = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        this.G0.t0(new C0617Kc0(this, x(), i, false, i));
        this.G0.setTag("MONTHS_VIEW_GROUP_TAG");
        C0337Fl0 fl0 = new C0337Fl0(contextThemeWrapper, this.A0, this.B0, new C0678Lc0(this));
        this.G0.q0(fl0);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.f36040_resource_name_obfuscated_RES_2131492901);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.F0 = recyclerView;
        if (recyclerView != null) {
            recyclerView.e0 = true;
            recyclerView.t0(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.F0.q0(new Gz1(this));
            this.F0.g(new C0738Mc0(this));
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) inflate.findViewById(R.id.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            AbstractC1920bu1.n(materialButton, new C0799Nc0(this));
            MaterialButton materialButton2 = (MaterialButton) inflate.findViewById(R.id.month_navigation_previous);
            materialButton2.setTag("NAVIGATION_PREV_TAG");
            MaterialButton materialButton3 = (MaterialButton) inflate.findViewById(R.id.month_navigation_next);
            materialButton3.setTag("NAVIGATION_NEXT_TAG");
            this.H0 = inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
            this.I0 = inflate.findViewById(R.id.mtrl_calendar_day_selector_frame);
            h1(1);
            materialButton.setText(this.C0.G);
            this.G0.i(new C0860Oc0(this, fl0, materialButton));
            materialButton.setOnClickListener(new View$OnClickListenerC0921Pc0(this));
            materialButton3.setOnClickListener(new View$OnClickListenerC0982Qc0(this, fl0));
            materialButton2.setOnClickListener(new View$OnClickListenerC1043Rc0(this, fl0));
        }
        if (!C1531Zc0.m1(contextThemeWrapper)) {
            new F80().a(this.G0);
        }
        this.G0.p0(fl0.t(this.C0));
        return inflate;
    }
}
