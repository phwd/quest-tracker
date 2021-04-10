package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.data.UserInfoField;
import org.chromium.chrome.browser.keyboard_accessory.sheet_tabs.AddressAccessoryInfoView;
import org.chromium.ui.widget.ChipView;

/* renamed from: N3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class N3 extends AbstractC5180v0 {
    public N3(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f38960_resource_name_obfuscated_RES_2131624205);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        C3319k50 k50 = (C3319k50) obj;
        AddressAccessoryInfoView addressAccessoryInfoView = (AddressAccessoryInfoView) view;
        z(addressAccessoryInfoView.F, (UserInfoField) k50.b.get(0));
        z(addressAccessoryInfoView.G, (UserInfoField) k50.b.get(1));
        z(addressAccessoryInfoView.H, (UserInfoField) k50.b.get(2));
        z(addressAccessoryInfoView.I, (UserInfoField) k50.b.get(3));
        z(addressAccessoryInfoView.f10691J, (UserInfoField) k50.b.get(4));
        z(addressAccessoryInfoView.K, (UserInfoField) k50.b.get(5));
        z(addressAccessoryInfoView.L, (UserInfoField) k50.b.get(6));
        z(addressAccessoryInfoView.M, (UserInfoField) k50.b.get(7));
        z(addressAccessoryInfoView.N, (UserInfoField) k50.b.get(8));
        z(addressAccessoryInfoView.O, (UserInfoField) k50.b.get(9));
    }

    public void z(ChipView chipView, UserInfoField userInfoField) {
        chipView.F.setText(userInfoField.getDisplayText());
        chipView.F.setContentDescription(userInfoField.getA11yDescription());
        if (!userInfoField.isSelectable() || userInfoField.getDisplayText().isEmpty()) {
            chipView.setVisibility(8);
            return;
        }
        chipView.setVisibility(0);
        chipView.setOnClickListener(new M3(userInfoField));
        chipView.setClickable(true);
        chipView.setEnabled(true);
    }
}
