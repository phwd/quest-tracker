package defpackage;

import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.data.UserInfoField;
import org.chromium.ui.widget.ChipView;

/* renamed from: pB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4191pB extends AbstractC5180v0 {
    public C4191pB(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f38970_resource_name_obfuscated_RES_2131624206);
    }

    public static void z(ChipView chipView, UserInfoField userInfoField) {
        chipView.F.setText(userInfoField.getDisplayText());
        chipView.F.setContentDescription(userInfoField.getA11yDescription());
        chipView.setVisibility(userInfoField.getDisplayText().isEmpty() ? 8 : 0);
        if (!userInfoField.isSelectable()) {
            chipView.setEnabled(false);
            return;
        }
        chipView.setOnClickListener(new View$OnClickListenerC4020oB(userInfoField));
        chipView.setClickable(true);
        chipView.setEnabled(true);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00cc, code lost:
        if (r9.equals("unionPayCC") == false) goto L_0x0068;
     */
    @Override // defpackage.AbstractC5180v0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void y(java.lang.Object r9, android.view.View r10) {
        /*
        // Method dump skipped, instructions count: 352
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4191pB.y(java.lang.Object, android.view.View):void");
    }
}
