package defpackage;

import android.graphics.Color;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.components.embedder_support.delegate.ColorPickerAdvanced;

/* renamed from: Pv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC0965Pv implements View.OnClickListener {
    public final /* synthetic */ AlertDialogC1026Qv F;

    public View$OnClickListenerC0965Pv(AlertDialogC1026Qv qv) {
        this.F = qv;
    }

    public void onClick(View view) {
        AlertDialogC1026Qv qv = this.F;
        qv.H.setVisibility(8);
        qv.findViewById(R.id.color_picker_simple).setVisibility(8);
        qv.F.setVisibility(0);
        ColorPickerAdvanced colorPickerAdvanced = qv.F;
        colorPickerAdvanced.I = qv;
        int i = qv.M;
        colorPickerAdvanced.f10842J = i;
        Color.colorToHSV(i, colorPickerAdvanced.K);
        colorPickerAdvanced.b();
    }
}
