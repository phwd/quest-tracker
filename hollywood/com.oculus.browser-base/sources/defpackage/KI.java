package defpackage;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TimePicker;
import com.oculus.browser.R;

/* renamed from: KI  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KI extends TimePickerDialog {
    public int F;
    public int G;
    public final JI H;
    public boolean I;

    public KI(Context context, JI ji, int i, int i2) {
        super(context, R.style.f72930_resource_name_obfuscated_RES_2132017866, null, i, i2, false);
        this.F = i;
        this.G = i2;
        this.H = ji;
        setOnDismissListener(new GI(this));
    }

    public void onTimeChanged(TimePicker timePicker, int i, int i2) {
        this.F = i;
        this.G = i2;
    }

    public void show() {
        super.show();
        Button button = getButton(-1);
        button.setText(R.string.f50940_resource_name_obfuscated_RES_2131952411);
        button.setOnClickListener(new HI(this));
        Button button2 = getButton(-2);
        button2.setText(R.string.f48470_resource_name_obfuscated_RES_2131952164);
        button2.setOnClickListener(new II(this));
    }
}
