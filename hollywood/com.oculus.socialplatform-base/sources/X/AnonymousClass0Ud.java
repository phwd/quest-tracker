package X;

import android.annotation.SuppressLint;
import com.oculus.certificatepinning.CertificatePinningData;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import okhttp3.CertificatePinner;

/* renamed from: X.0Ud  reason: invalid class name */
public final class AnonymousClass0Ud {
    public static final String[] A00 = {"facebook-hardware.com", "facebook.com", "facebookvirtualassistant.com", "facebookstudy.com", "fbcdn.net", "fbsbx.com", "freebasics.com", "internet.org", "instagram.com", "novi.com", "oculus.com", "viewpointsfromfacebook.com", "whatsapp.com"};

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static CertificatePinner A00(long j) {
        if (j < System.currentTimeMillis() - CertificatePinningData.PINNING_EXPIRATION_MS) {
            return CertificatePinner.DEFAULT;
        }
        CertificatePinner.Builder builder = new CertificatePinner.Builder();
        String[] strArr = AnonymousClass0Ua.A00;
        for (String str : strArr) {
            String[] strArr2 = A00;
            for (String str2 : strArr2) {
                builder.add(str2, AnonymousClass006.A07(FbCertificatePinnerFactory.SHA_256_PIN_PREFIX, str));
                builder.add(AnonymousClass006.A07(CertificatePinner.Pin.WILDCARD, str2), AnonymousClass006.A07(FbCertificatePinnerFactory.SHA_256_PIN_PREFIX, str));
            }
        }
        return builder.build();
    }
}
