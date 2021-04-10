package org.chromium.chrome.browser.payments;

import J.N;
import org.chromium.components.payments.WebAppManifestSection;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentManifestWebDataService {

    /* renamed from: a  reason: collision with root package name */
    public long f10745a = N.M9X__o$R(this);

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface PaymentManifestWebDataServiceCallback {
        void onPaymentMethodManifestFetched(String[] strArr);

        void onPaymentWebAppManifestFetched(WebAppManifestSection[] webAppManifestSectionArr);
    }

    public static void addFingerprintToSection(WebAppManifestSection[] webAppManifestSectionArr, int i, int i2, byte[] bArr) {
        webAppManifestSectionArr[i].c[i2] = bArr;
    }

    public static void addSectionToManifest(WebAppManifestSection[] webAppManifestSectionArr, int i, String str, long j, int i2) {
        webAppManifestSectionArr[i] = new WebAppManifestSection(str, j, i2);
    }

    public static WebAppManifestSection[] createManifest(int i) {
        return new WebAppManifestSection[i];
    }

    public static byte[][] getFingerprintsFromSection(WebAppManifestSection webAppManifestSection) {
        return webAppManifestSection.c;
    }

    public static String getIdFromSection(WebAppManifestSection webAppManifestSection) {
        return webAppManifestSection.f10876a;
    }

    public static long getMinVersionFromSection(WebAppManifestSection webAppManifestSection) {
        return webAppManifestSection.b;
    }
}
