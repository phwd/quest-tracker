package org.chromium.chrome.browser.safe_browsing.settings;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class SafeBrowsingSettingsFragmentBase extends AbstractC2324eF0 {
    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        AbstractC2870hT0.a(this, k1());
        u().setTitle(R.string.f59270_resource_name_obfuscated_RES_2131953244);
        l1(bundle, str);
        V0(true);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void k0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menu.add(0, R.id.menu_id_targeted_help, 0, R.string.f54570_resource_name_obfuscated_RES_2131952774).setIcon(Fs1.a(I(), R.drawable.f30670_resource_name_obfuscated_RES_2131231107, u().getTheme()));
    }

    public abstract int k1();

    public void l1(Bundle bundle, String str) {
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public boolean u0(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.menu_id_targeted_help) {
            return false;
        }
        u();
        O(R.string.f52620_resource_name_obfuscated_RES_2131952579);
        Profile.b();
        throw null;
    }
}
