package org.chromium.chrome.browser.share.qrcode;

import J.N;
import android.graphics.Bitmap;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QRCodeGenerationRequest {

    /* renamed from: a  reason: collision with root package name */
    public long f10758a;
    public C4557rJ0 b;

    public QRCodeGenerationRequest(String str, C4557rJ0 rj0) {
        this.b = rj0;
        this.f10758a = N.Ms6T0$zG(this, str);
    }

    public final void onQRCodeAvailable(Bitmap bitmap) {
        String str;
        C4557rJ0 rj0 = this.b;
        if (rj0 != null) {
            if (bitmap != null) {
                rj0.b.b.m(AbstractC5578xJ0.f11604a, bitmap);
            } else {
                int M37SqSAy = N.M37SqSAy("ChromeShareQRCode", "max_url_length", 122);
                String str2 = rj0.f11198a;
                if (str2 == null || str2.length() <= M37SqSAy) {
                    str = rj0.b.f11337a.getResources().getString(R.string.f59690_resource_name_obfuscated_RES_2131953286);
                } else {
                    str = rj0.b.f11337a.getResources().getString(R.string.f59680_resource_name_obfuscated_RES_2131953285, Integer.valueOf(M37SqSAy));
                }
                rj0.b.b.m(AbstractC5578xJ0.b, str);
            }
            this.b = null;
        }
        long j = this.f10758a;
        if (j != 0) {
            N.MQBcgs6S(j);
            this.f10758a = 0;
        }
    }
}
