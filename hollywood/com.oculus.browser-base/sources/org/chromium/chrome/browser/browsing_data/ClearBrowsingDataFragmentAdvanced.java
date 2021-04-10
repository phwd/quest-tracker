package org.chromium.chrome.browser.browsing_data;

import java.util.Arrays;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearBrowsingDataFragmentAdvanced extends ClearBrowsingDataFragment {
    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public int m1() {
        return 1;
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public List o1() {
        return Arrays.asList(0, 1, 2, 3, 4, 5);
    }

    @Override // org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment
    public void s1() {
        AbstractC3364kK0.g("History.ClearBrowsingData.UserDeletedFromTab", 1, 2);
        AbstractC3535lK0.a("ClearBrowsingData_AdvancedTab");
    }
}
