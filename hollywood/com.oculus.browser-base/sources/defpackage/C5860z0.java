package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import com.oculus.browser.R;

/* renamed from: z0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5860z0 extends AbstractC5180v0 {
    public C5860z0(ViewGroup viewGroup) {
        super(viewGroup, R.layout.f39000_resource_name_obfuscated_RES_2131624209);
    }

    @Override // defpackage.AbstractC5180v0
    public void y(Object obj, View view) {
        C2978i50 i50 = (C2978i50) obj;
        LinearLayout linearLayout = (LinearLayout) view;
        linearLayout.setClickable(true);
        linearLayout.setOnClickListener(new View$OnClickListenerC5690y0(i50));
        ((TextView) linearLayout.findViewById(R.id.option_toggle_title)).setText(i50.f10120a);
        ((TextView) linearLayout.findViewById(R.id.option_toggle_subtitle)).setText(i50.b ? R.string.f63330_resource_name_obfuscated_RES_2131953650 : R.string.f63320_resource_name_obfuscated_RES_2131953649);
        SwitchCompat switchCompat = (SwitchCompat) linearLayout.findViewById(R.id.option_toggle_switch);
        switchCompat.setChecked(i50.b);
        switchCompat.setBackground(null);
    }
}
