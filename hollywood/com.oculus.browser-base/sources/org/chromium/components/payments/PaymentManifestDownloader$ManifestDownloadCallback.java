package org.chromium.components.payments;

import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface PaymentManifestDownloader$ManifestDownloadCallback {
    void onManifestDownloadFailure(String str);

    void onPaymentMethodManifestDownloadSuccess(GURL gurl, Origin origin, String str);

    void onWebAppManifestDownloadSuccess(String str);
}
