package defpackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateSelector;

/* renamed from: md0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3747md0 extends JC0 {
    public CalendarConstraints A0;
    public DateSelector z0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void C0(Bundle bundle) {
        bundle.putParcelable("DATE_SELECTOR_KEY", this.z0);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.A0);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void h0(Bundle bundle) {
        super.h0(bundle);
        if (bundle == null) {
            bundle = this.K;
        }
        this.z0 = (DateSelector) bundle.getParcelable("DATE_SELECTOR_KEY");
        this.A0 = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.z0.r(layoutInflater, viewGroup, bundle, this.A0, new C3576ld0(this));
    }
}
