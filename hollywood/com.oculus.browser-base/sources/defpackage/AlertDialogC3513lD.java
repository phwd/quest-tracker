package defpackage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.oculus.browser.R;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* renamed from: lD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AlertDialogC3513lD extends AlertDialog implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    public final DatePicker F;
    public final TimePicker G;
    public final L10 H;
    public final long I;

    /* renamed from: J  reason: collision with root package name */
    public final long f10331J;

    public AlertDialogC3513lD(Context context, L10 l10, int i, int i2, int i3, int i4, int i5, boolean z, double d, double d2) {
        super(context, 0);
        long j = (long) d;
        this.I = j;
        long j2 = (long) d2;
        this.f10331J = j2;
        this.H = l10;
        setButton(-1, context.getText(R.string.f50710_resource_name_obfuscated_RES_2131952388), this);
        setButton(-2, context.getText(17039360), (DialogInterface.OnClickListener) null);
        setIcon(0);
        setTitle(context.getText(R.string.f50730_resource_name_obfuscated_RES_2131952390));
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.f37780_resource_name_obfuscated_RES_2131624087, (ViewGroup) null);
        setView(inflate);
        DatePicker datePicker = (DatePicker) inflate.findViewById(R.id.date_picker);
        this.F = datePicker;
        AbstractC3001iD.a(datePicker, this, i, i2, i3, j, j2);
        TimePicker timePicker = (TimePicker) inflate.findViewById(R.id.time_picker);
        this.G = timePicker;
        timePicker.setIs24HourView(Boolean.valueOf(z));
        timePicker.setCurrentHour(Integer.valueOf(i4));
        timePicker.setCurrentMinute(Integer.valueOf(i5));
        timePicker.setOnTimeChangedListener(this);
        onTimeChanged(timePicker, timePicker.getCurrentHour().intValue(), timePicker.getCurrentMinute().intValue());
    }

    public static int a(TimePicker timePicker) {
        return timePicker.getCurrentHour().intValue();
    }

    public static int b(TimePicker timePicker) {
        return timePicker.getCurrentMinute().intValue();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.H != null) {
            this.F.clearFocus();
            this.G.clearFocus();
            L10 l10 = this.H;
            l10.b.b(l10.f8402a, this.F.getYear(), this.F.getMonth(), this.F.getDayOfMonth(), a(this.G), b(this.G), 0, 0, 0);
        }
    }

    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        TimePicker timePicker = this.G;
        if (timePicker != null) {
            onTimeChanged(timePicker, a(timePicker), b(this.G));
        }
    }

    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        int year = this.F.getYear();
        int month = this.F.getMonth();
        int dayOfMonth = this.F.getDayOfMonth();
        TimePicker timePicker2 = this.G;
        long j = this.I;
        long j2 = this.f10331J;
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.clear();
        gregorianCalendar.set(year, month, dayOfMonth, timePicker2.getCurrentHour().intValue(), timePicker2.getCurrentMinute().intValue(), 0);
        if (gregorianCalendar.getTimeInMillis() < j) {
            gregorianCalendar.setTimeInMillis(j);
        } else if (gregorianCalendar.getTimeInMillis() > j2) {
            gregorianCalendar.setTimeInMillis(j2);
        }
        timePicker2.setCurrentHour(Integer.valueOf(gregorianCalendar.get(11)));
        timePicker2.setCurrentMinute(Integer.valueOf(gregorianCalendar.get(12)));
    }
}
