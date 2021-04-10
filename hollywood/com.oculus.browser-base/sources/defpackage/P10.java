package defpackage;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import com.oculus.browser.R;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* renamed from: P10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class P10 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8661a;
    public boolean b;
    public AlertDialog c;
    public final C3342kD d;

    public P10(Context context, C3342kD kDVar) {
        this.f8661a = context;
        this.d = kDVar;
    }

    public final void a() {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.c.dismiss();
        }
    }

    public void b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (!this.b) {
            this.b = true;
            if (i == 11) {
                this.d.a((double) (((i2 - 1970) * 12) + i3));
            } else if (i == 13) {
                this.d.a((double) Gy1.l(i2, i9).getTimeInMillis());
            } else if (i == 12) {
                this.d.a((double) (TimeUnit.SECONDS.toMillis((long) i7) + TimeUnit.MINUTES.toMillis((long) i6) + TimeUnit.HOURS.toMillis((long) i5) + ((long) i8)));
            } else {
                Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                instance.clear();
                instance.set(1, i2);
                instance.set(2, i3);
                instance.set(5, i4);
                instance.set(11, i5);
                instance.set(12, i6);
                instance.set(13, i7);
                instance.set(14, i8);
                this.d.a((double) instance.getTimeInMillis());
            }
        }
    }

    public void c(int i, double d2, double d3, double d4, double d5) {
        GregorianCalendar gregorianCalendar;
        if (Double.isNaN(d2)) {
            Calendar instance = Calendar.getInstance();
            instance.set(14, 0);
            gregorianCalendar = instance;
        } else if (i == 11) {
            gregorianCalendar = C0093Bl0.k(d2);
        } else if (i == 13) {
            gregorianCalendar = Gy1.k(d2);
        } else {
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
            gregorianCalendar2.setGregorianChange(new Date(Long.MIN_VALUE));
            gregorianCalendar2.setTimeInMillis((long) d2);
            gregorianCalendar = gregorianCalendar2;
        }
        if (i == 8) {
            d(i, gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), 0, 0, 0, 0, 0, d3, d4, d5);
        } else if (i == 12) {
            d(i, 0, 0, 0, gregorianCalendar.get(11), gregorianCalendar.get(12), 0, 0, 0, d3, d4, d5);
        } else if (i == 9 || i == 10) {
            d(i, gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), gregorianCalendar.get(11), gregorianCalendar.get(12), gregorianCalendar.get(13), gregorianCalendar.get(14), 0, d3, d4, d5);
        } else if (i == 11) {
            d(i, gregorianCalendar.get(1), gregorianCalendar.get(2), 0, 0, 0, 0, 0, 0, d3, d4, d5);
        } else if (i == 13) {
            d(i, Gy1.m(gregorianCalendar), 0, 0, 0, 0, 0, 0, gregorianCalendar.get(3), d3, d4, d5);
        }
    }

    public void d(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, double d2, double d3, double d4) {
        AlertDialog alertDialog = this.c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.c.dismiss();
        }
        int i10 = (int) d4;
        if (i == 8) {
            DatePickerDialogC3171jD jDVar = new DatePickerDialogC3171jD(this.f8661a, new K10(this, i), i2, i3, i4);
            AbstractC3001iD.a(jDVar.getDatePicker(), jDVar, i2, i3, i4, (long) d2, (long) d3);
            jDVar.setTitle(this.f8661a.getText(R.string.f50720_resource_name_obfuscated_RES_2131952389));
            this.c = jDVar;
        } else if (i == 12) {
            if (i10 < 0 || i10 >= 60000) {
                Context context = this.f8661a;
                this.c = new TimePickerDialog(context, new O10(this, i), i5, i6, DateFormat.is24HourFormat(context));
            } else {
                Context context2 = this.f8661a;
                this.c = new AlertDialogC1556Zl0(context2, 0, i5, i6, i7, i8, (int) d2, (int) d3, i10, DateFormat.is24HourFormat(context2), new M10(this, i));
            }
        } else if (i == 9 || i == 10) {
            Context context3 = this.f8661a;
            this.c = new AlertDialogC3513lD(context3, new L10(this, i), i2, i3, i4, i5, i6, DateFormat.is24HourFormat(context3), d2, d3);
        } else if (i == 11) {
            this.c = new AlertDialogC0154Cl0(this.f8661a, new N10(this, i), i2, i3, d2, d3);
        } else if (i == 13) {
            this.c = new Hy1(this.f8661a, new N10(this, i), i2, i9, d2, d3);
        }
        this.c.setButton(-1, this.f8661a.getText(R.string.f50710_resource_name_obfuscated_RES_2131952388), (DialogInterface.OnClickListener) this.c);
        this.c.setButton(-2, this.f8661a.getText(17039360), (DialogInterface.OnClickListener) null);
        this.c.setButton(-3, this.f8661a.getText(R.string.f50690_resource_name_obfuscated_RES_2131952386), new I10(this));
        this.c.setOnDismissListener(new J10(this));
        this.b = false;
        this.c.show();
    }
}
