package defpackage;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Objects;
import org.chromium.chrome.browser.download.DownloadDialogBridge;

/* renamed from: eH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2329eH implements AbstractC1637aH, JI {

    /* renamed from: a  reason: collision with root package name */
    public DatePickerDialog f9844a;
    public boolean b;
    public KI c;
    public ZG d;
    public final Calendar e = Calendar.getInstance();

    public final void a(DialogInterface dialogInterface, int i) {
        this.b = true;
        if (i == -2) {
            b();
        } else if (i != -1) {
            AbstractC1220Ua0.a("DateTimeDialog", "Unsupported button type clicked in date picker, type: %d", Integer.valueOf(i));
        } else {
            DatePicker datePicker = this.f9844a.getDatePicker();
            this.e.set(1, datePicker.getYear());
            this.e.set(2, datePicker.getMonth());
            this.e.set(5, datePicker.getDayOfMonth());
            this.c.show();
        }
    }

    public final void b() {
        this.e.clear();
        NH nh = (NH) this.d;
        Objects.requireNonNull(nh);
        RH.a(5);
        nh.a();
        DownloadDialogBridge downloadDialogBridge = (DownloadDialogBridge) nh.M;
        Objects.requireNonNull(downloadDialogBridge);
        RH.a(2);
        downloadDialogBridge.c();
    }
}
