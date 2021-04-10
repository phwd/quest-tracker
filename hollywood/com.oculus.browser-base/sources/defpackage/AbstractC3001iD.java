package defpackage;

import android.widget.DatePicker;

/* renamed from: iD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3001iD {
    public static void a(DatePicker datePicker, DatePicker.OnDateChangedListener onDateChangedListener, int i, int i2, int i3, long j, long j2) {
        C2830hD a2 = C2830hD.a(i, i2, i3);
        C2830hD b = C2830hD.b(j);
        C2830hD b2 = C2830hD.b(j2);
        long j3 = b2.f10054a;
        long j4 = b.f10054a;
        if (j3 < j4) {
            b2 = b;
        }
        long j5 = a2.f10054a;
        if (j5 < j4) {
            a2 = b;
        } else if (j5 > b2.f10054a) {
            a2 = b2;
        }
        long j6 = b2.f10054a;
        if (j4 > datePicker.getMaxDate()) {
            datePicker.setMaxDate(j6);
            datePicker.setMinDate(j4);
        } else {
            datePicker.setMinDate(j4);
            datePicker.setMaxDate(j6);
        }
        datePicker.init(a2.b, a2.c, a2.d, onDateChangedListener);
    }
}
