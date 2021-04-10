package org.chromium.chrome.browser.safety_check;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SafetyCheckSettingsFragment extends AbstractC2324eF0 {
    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, R.xml.f76360_resource_name_obfuscated_RES_2132213792);
        u().setTitle(FY0.b(O(R.string.f59220_resource_name_obfuscated_RES_2131953239), new EY0("<new>", "</new>", new Object[0])).toString().trim());
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = (LinearLayout) super.l0(layoutInflater, viewGroup, bundle);
        LinearLayout linearLayout2 = (LinearLayout) layoutInflater.inflate(R.layout.f41230_resource_name_obfuscated_RES_2131624432, (ViewGroup) linearLayout, false);
        ButtonCompat buttonCompat = (ButtonCompat) linearLayout2.findViewById(R.id.safety_check_button);
        TextView textView = (TextView) linearLayout2.findViewById(R.id.safety_check_timestamp);
        linearLayout.addView(linearLayout2);
        return linearLayout;
    }
}
