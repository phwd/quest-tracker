package defpackage;

import J.N;
import android.content.Intent;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorResponse;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;
import com.google.android.gms.fido.fido2.api.common.UvmEntries;
import com.google.android.gms.fido.fido2.api.common.UvmEntry;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.webauth.AuthenticatorImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.Origin;

/* renamed from: AP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AP implements Ky1 {

    /* renamed from: a  reason: collision with root package name */
    public SW f7669a;
    public SW b;
    public WebContents c;
    public C2971i3 d;
    public int e;
    public boolean f;
    public AbstractC1143Ss0 g = new C5929zP(this);

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        UvmEntries uvmEntries;
        if (intent == null) {
            AbstractC1220Ua0.a("Fido2Request", "Received a null intent.", new Object[0]);
            e(2);
        } else if (i != -1) {
            if (i != 0) {
                AbstractC1220Ua0.a("Fido2Request", AbstractC2531fV.w("Failed with result code", i), new Object[0]);
                e(21);
                return;
            }
            e(2);
        } else if (intent.hasExtra("FIDO2_CREDENTIAL_EXTRA")) {
            PublicKeyCredential publicKeyCredential = (PublicKeyCredential) AbstractC5928zO0.a(intent.getByteArrayExtra("FIDO2_CREDENTIAL_EXTRA"), PublicKeyCredential.CREATOR);
            AuthenticatorResponse x = publicKeyCredential.x();
            if (x instanceof AuthenticatorErrorResponse) {
                d((AuthenticatorErrorResponse) x);
            } else if (x instanceof AuthenticatorAttestationResponse) {
                try {
                    AuthenticatorImpl authenticatorImpl = (AuthenticatorImpl) this.f7669a;
                    authenticatorImpl.f10806J.a(0, BP.e((AuthenticatorAttestationResponse) publicKeyCredential.x()));
                    authenticatorImpl.close();
                    this.f7669a = null;
                } catch (NoSuchAlgorithmException unused) {
                    e(11);
                }
            } else if (x instanceof AuthenticatorAssertionResponse) {
                SW sw = this.f7669a;
                C5941zV c2 = BP.c((AuthenticatorAssertionResponse) publicKeyCredential.x(), this.f);
                AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs = publicKeyCredential.L;
                if (!(authenticationExtensionsClientOutputs == null || (uvmEntries = authenticationExtensionsClientOutputs.F) == null)) {
                    c2.i = true;
                    List list = uvmEntries.F;
                    C3622ls1[] ls1Arr = new C3622ls1[list.size()];
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        C3622ls1 ls1 = new C3622ls1();
                        ls1.d = ((UvmEntry) list.get(i2)).F;
                        ls1.e = ((UvmEntry) list.get(i2)).G;
                        ls1.f = ((UvmEntry) list.get(i2)).H;
                        ls1Arr[i2] = ls1;
                    }
                    c2.j = ls1Arr;
                }
                AuthenticatorImpl authenticatorImpl2 = (AuthenticatorImpl) sw;
                authenticatorImpl2.K.a(0, c2);
                authenticatorImpl2.close();
                this.f7669a = null;
            }
        } else if (intent.hasExtra("FIDO2_ERROR_EXTRA")) {
            d((AuthenticatorErrorResponse) AbstractC5928zO0.a(intent.getByteArrayExtra("FIDO2_ERROR_EXTRA"), AuthenticatorErrorResponse.CREATOR));
        } else if (intent.hasExtra("FIDO2_RESPONSE_EXTRA")) {
            int i3 = this.e;
            if (i3 == 1) {
                AbstractC1220Ua0.a("Fido2Request", "Received a register response from Google Play Services FIDO2 API", new Object[0]);
                try {
                    AuthenticatorImpl authenticatorImpl3 = (AuthenticatorImpl) this.f7669a;
                    authenticatorImpl3.f10806J.a(0, BP.e((AuthenticatorAttestationResponse) AbstractC5928zO0.a(intent.getByteArrayExtra("FIDO2_RESPONSE_EXTRA"), AuthenticatorAttestationResponse.CREATOR)));
                    authenticatorImpl3.close();
                } catch (NoSuchAlgorithmException unused2) {
                    e(11);
                }
            } else if (i3 == 2) {
                AbstractC1220Ua0.a("Fido2Request", "Received a sign response from Google Play Services FIDO2 API", new Object[0]);
                AuthenticatorImpl authenticatorImpl4 = (AuthenticatorImpl) this.f7669a;
                authenticatorImpl4.K.a(0, BP.c((AuthenticatorAssertionResponse) AbstractC5928zO0.a(intent.getByteArrayExtra("FIDO2_RESPONSE_EXTRA"), AuthenticatorAssertionResponse.CREATOR), this.f));
                authenticatorImpl4.close();
            }
            this.f7669a = null;
        } else {
            AbstractC1220Ua0.a("Fido2Request", "The response is missing FIDO2_KEY_RESPONSE_EXTRA and FIDO2_KEY_CREDENTIAL_EXTRA.", new Object[0]);
            e(21);
        }
    }

    public String b(Origin origin) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(!origin.a() ? origin.f11030a.d : str);
        sb.append("://");
        if (!origin.a()) {
            str = origin.f11030a.e;
        }
        sb.append(str);
        sb.append(":");
        sb.append((int) (!origin.a() ? origin.f11030a.f : 0));
        return N.MpCt7siL(sb.toString());
    }

    public final boolean c() {
        Objects.requireNonNull(YM.f9268a);
        ContextUtils.getApplicationContext();
        return false;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        if (r4.equals("One of the excluded credentials exists on the local device") != false) goto L_0x0070;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void d(com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse r4) {
        /*
        // Method dump skipped, instructions count: 128
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AP.d(com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse):void");
    }

    public final void e(int i) {
        SW sw = this.f7669a;
        if (sw != null) {
            sw.f0(Integer.valueOf(i));
            this.f7669a = null;
        }
    }
}
