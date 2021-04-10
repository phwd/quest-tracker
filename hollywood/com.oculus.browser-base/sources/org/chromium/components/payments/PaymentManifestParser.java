package org.chromium.components.payments;

import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PaymentManifestParser {

    /* renamed from: a  reason: collision with root package name */
    public long f10874a;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface ManifestParseCallback {
        void onManifestParseFailure();

        void onPaymentMethodManifestParseSuccess(GURL[] gurlArr, GURL[] gurlArr2);

        void onWebAppManifestParseSuccess(WebAppManifestSection[] webAppManifestSectionArr);
    }

    public static void addFingerprintToSection(WebAppManifestSection[] webAppManifestSectionArr, int i, int i2, byte[] bArr) {
        webAppManifestSectionArr[i].c[i2] = bArr;
    }

    public static void addSectionToManifest(WebAppManifestSection[] webAppManifestSectionArr, int i, String str, long j, int i2) {
        webAppManifestSectionArr[i] = new WebAppManifestSection(str, j, i2);
    }

    public static boolean addUrl(GURL[] gurlArr, int i, String str) {
        if (!new GURL(str).b) {
            return false;
        }
        gurlArr[i] = new GURL(str);
        return true;
    }

    public static WebAppManifestSection[] createManifest(int i) {
        return new WebAppManifestSection[i];
    }

    public static GURL[] createUrlArray(int i) {
        return new GURL[i];
    }
}
