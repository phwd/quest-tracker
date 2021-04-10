package defpackage;

import android.app.TimePickerDialog;
import android.widget.TimePicker;

/* renamed from: O10  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class O10 implements TimePickerDialog.OnTimeSetListener {

    /* renamed from: a  reason: collision with root package name */
    public final int f8594a;
    public final /* synthetic */ P10 b;

    public O10(P10 p10, int i) {
        this.b = p10;
        this.f8594a = i;
    }

    public void onTimeSet(TimePicker timePicker, int i, int i2) {
        this.b.b(this.f8594a, 0, 0, 0, i, i2, 0, 0, 0);
    }
}
