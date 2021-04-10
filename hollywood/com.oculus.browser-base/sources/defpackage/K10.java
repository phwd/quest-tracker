package defpackage;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

/* renamed from: K10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K10 implements DatePickerDialog.OnDateSetListener {

    /* renamed from: a  reason: collision with root package name */
    public final int f8335a;
    public final /* synthetic */ P10 b;

    public K10(P10 p10, int i) {
        this.b = p10;
        this.f8335a = i;
    }

    public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
        this.b.b(this.f8335a, i, i2, i3, 0, 0, 0, 0, 0);
    }
}
