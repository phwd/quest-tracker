package org.chromium.chrome.browser.browsing_data;

import android.os.Bundle;
import com.oculus.browser.R;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearBrowsingDataFragmentBasic extends ClearBrowsingDataFragment {
    public static final /* synthetic */ int M0 = 0;

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void h0(Bundle bundle) {
        super.h0(bundle);
        boolean z = false;
        ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference = (ClearBrowsingDataCheckBoxPreference) f1(ClearBrowsingDataFragment.p1(0));
        ClearBrowsingDataCheckBoxPreference clearBrowsingDataCheckBoxPreference2 = (ClearBrowsingDataCheckBoxPreference) f1(ClearBrowsingDataFragment.p1(1));
        clearBrowsingDataCheckBoxPreference.A0 = new RunnableC0659Ku();
        if (C5949zZ.a().c(Profile.b()).c()) {
            ProfileSyncService b = ProfileSyncService.b();
            if (b != null && b.m() && ((HashSet) b.c()).contains(18)) {
                z = true;
            }
            clearBrowsingDataCheckBoxPreference.S(z ? R.string.f48990_resource_name_obfuscated_RES_2131952216 : R.string.f48980_resource_name_obfuscated_RES_2131952215);
            clearBrowsingDataCheckBoxPreference2.S(R.string.f49020_resource_name_obfuscated_RES_2131952219);
        }
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public int m1() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public List o1() {
        return Arrays.asList(0, 1, 2);
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public void s1() {
        AbstractC3364kK0.g("History.ClearBrowsingData.UserDeletedFromTab", 0, 2);
        AbstractC3535lK0.a("ClearBrowsingData_BasicTab");
    }
}
