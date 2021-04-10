package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;
import com.google.android.material.datepicker.MaterialCalendarGridView;
import com.google.android.material.datepicker.Month;
import com.oculus.browser.R;

/* renamed from: Fl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0337Fl0 extends AbstractC5750yK0 {
    public final CalendarConstraints I;

    /* renamed from: J  reason: collision with root package name */
    public final DateSelector f8038J;
    public final C0678Lc0 K;
    public final int L;

    public C0337Fl0(Context context, DateSelector dateSelector, CalendarConstraints calendarConstraints, C0678Lc0 lc0) {
        Month month = calendarConstraints.F;
        Month month2 = calendarConstraints.G;
        Month month3 = calendarConstraints.H;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        } else if (month3.compareTo(month2) <= 0) {
            this.L = (C0032Al0.F * context.getResources().getDimensionPixelSize(R.dimen.f21530_resource_name_obfuscated_RES_2131165772)) + (C1531Zc0.m1(context) ? context.getResources().getDimensionPixelSize(R.dimen.f21530_resource_name_obfuscated_RES_2131165772) : 0);
            this.I = calendarConstraints;
            this.f8038J = dateSelector;
            this.K = lc0;
            r(true);
        } else {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
    }

    @Override // defpackage.AbstractC5750yK0
    public int b() {
        return this.I.K;
    }

    @Override // defpackage.AbstractC5750yK0
    public long c(int i) {
        return this.I.F.t(i).F.getTimeInMillis();
    }

    @Override // defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        C0276El0 el0 = (C0276El0) xk0;
        Month t = this.I.F.t(i);
        el0.Z.setText(t.G);
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) el0.a0.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !t.equals(materialCalendarGridView.getAdapter().G)) {
            C0032Al0 al0 = new C0032Al0(t, this.f8038J, this.I);
            materialCalendarGridView.setNumColumns(t.f9692J);
            materialCalendarGridView.setAdapter((ListAdapter) al0);
        } else {
            materialCalendarGridView.getAdapter().notifyDataSetChanged();
        }
        materialCalendarGridView.setOnItemClickListener(new C0215Dl0(this, materialCalendarGridView));
    }

    @Override // defpackage.AbstractC5750yK0
    public XK0 m(ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) AbstractC2531fV.r(viewGroup, R.layout.f39600_resource_name_obfuscated_RES_2131624269, viewGroup, false);
        if (!C1531Zc0.m1(viewGroup.getContext())) {
            return new C0276El0(linearLayout, false);
        }
        linearLayout.setLayoutParams(new JK0(-1, this.L));
        return new C0276El0(linearLayout, true);
    }

    public Month s(int i) {
        return this.I.F.t(i);
    }

    public int t(Month month) {
        return this.I.F.x(month);
    }
}
