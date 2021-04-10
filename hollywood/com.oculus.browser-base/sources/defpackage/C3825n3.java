package defpackage;

import J.N;
import android.os.Vibrator;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/* renamed from: n3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3825n3 implements TextWatcher {
    public final /* synthetic */ Button F;
    public final /* synthetic */ EditText G;
    public final /* synthetic */ C4167p3 H;

    public C3825n3(C4167p3 p3Var, Button button, EditText editText) {
        this.H = p3Var;
        this.F = button;
        this.G = editText;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int i4;
        String trim = charSequence.toString().trim();
        boolean z = false;
        boolean z2 = trim.length() > 0 && (trim.contains(":") || trim.contains(" ") || trim.startsWith(".") || !N.Mo$6Pn$c(trim));
        if (z2 && i3 != 0 && Settings.System.getInt(this.H.F.getContentResolver(), "haptic_feedback_enabled", 1) == 1) {
            ((Vibrator) this.H.F.getSystemService("vibrator")).vibrate(50);
        }
        Button button = this.F;
        if (!z2 && trim.length() > 0) {
            z = true;
        }
        button.setEnabled(z);
        EditText editText = this.G;
        if (z2) {
            i4 = this.H.x0;
        } else {
            i4 = this.H.y0;
        }
        editText.setTextColor(i4);
    }
}
