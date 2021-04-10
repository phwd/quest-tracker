package defpackage;

import J.N;
import android.view.View;
import android.widget.CheckBox;
import java.util.Objects;
import org.chromium.components.browser_ui.contacts_picker.TopView;

/* renamed from: vC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5217vC0 extends XK0 implements View.OnClickListener {
    public TopView Z;

    public View$OnClickListenerC5217vC0(C0472Hs hs, TopView topView) {
        super(topView);
        this.Z = topView;
        topView.setOnClickListener(this);
    }

    public void onClick(View view) {
        TopView topView = this.Z;
        Objects.requireNonNull(topView);
        if (N.Mk6X8tWe("ContactsPickerSelectAll")) {
            CheckBox checkBox = topView.H;
            checkBox.setChecked(!checkBox.isChecked());
        }
    }
}
