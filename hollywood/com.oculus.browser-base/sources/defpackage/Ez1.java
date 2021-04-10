package defpackage;

import android.view.View;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.Month;

/* renamed from: Ez1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ez1 implements View.OnClickListener {
    public final /* synthetic */ int F;
    public final /* synthetic */ Gz1 G;

    public Ez1(Gz1 gz1, int i) {
        this.G = gz1;
        this.F = i;
    }

    public void onClick(View view) {
        Month c = Month.c(this.F, this.G.I.C0.H);
        CalendarConstraints calendarConstraints = this.G.I.B0;
        if (c.compareTo(calendarConstraints.F) < 0) {
            c = calendarConstraints.F;
        } else if (c.compareTo(calendarConstraints.G) > 0) {
            c = calendarConstraints.G;
        }
        this.G.I.g1(c);
        this.G.I.h1(1);
    }
}
