package org.chromium.chrome.browser.privacy_sandbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrivacySandboxSettingsFragment extends AbstractC2324eF0 {
    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        u().setTitle(R.string.f59160_resource_name_obfuscated_RES_2131953233);
        AbstractC2870hT0.a(this, R.xml.f76340_resource_name_obfuscated_RES_2132213790);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = (LinearLayout) super.l0(layoutInflater, viewGroup, bundle);
        linearLayout.addView((LinearLayout) layoutInflater.inflate(R.layout.f40920_resource_name_obfuscated_RES_2131624401, (ViewGroup) linearLayout, false), 0);
        return linearLayout;
    }
}
