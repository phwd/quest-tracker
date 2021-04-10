package org.chromium.chrome.browser.browsing_data;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BrowsingDataBridge {

    /* renamed from: a  reason: collision with root package name */
    public static BrowsingDataBridge f10626a;
    public AbstractC0273Ek b;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface ImportantSitesCallback {
        void onImportantRegisterableDomainsReady(String[] strArr, String[] strArr2, int[] iArr, boolean z);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface OtherFormsOfBrowsingHistoryListener {
        void enableDialogAboutOtherFormsOfBrowsingHistory();
    }

    public static BrowsingDataBridge c() {
        Object obj = ThreadUtils.f10596a;
        if (f10626a == null) {
            f10626a = new BrowsingDataBridge();
        }
        return f10626a;
    }

    public void a(AbstractC0273Ek ek, int[] iArr, int i) {
        b(ek, iArr, i, new String[0], new int[0], new String[0], new int[0]);
    }

    public void b(AbstractC0273Ek ek, int[] iArr, int i, String[] strArr, int[] iArr2, String[] strArr2, int[] iArr3) {
        this.b = ek;
        N.McYsV35Z(this, Profile.b(), iArr, i, strArr, iArr2, strArr2, iArr3);
    }

    public void browsingDataCleared() {
        AbstractC0273Ek ek = this.b;
        if (ek != null) {
            ek.e();
            this.b = null;
        }
    }
}
