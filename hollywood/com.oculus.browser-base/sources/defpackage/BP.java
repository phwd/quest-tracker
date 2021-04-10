package defpackage;

import J.N;
import android.util.Base64;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.fido2.api.common.Attachment;
import com.google.android.gms.fido.fido2.api.common.AttestationConveyancePreference;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.chrome.browser.webauth.Fido2Helper$AttestationObjectParts;

/* renamed from: BP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class BP {
    public static double a(C0087Bi1 bi1) {
        if (bi1 == null) {
            return 600.0d;
        }
        return Math.max(10.0d, Math.min(600.0d, (double) TimeUnit.MICROSECONDS.toSeconds(bi1.d)));
    }

    public static List b(C4725sI0[] si0Arr) {
        if (si0Arr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (C4725sI0 si0 : si0Arr) {
            byte[] bArr = si0.e;
            int[] iArr = si0.f;
            ArrayList arrayList2 = new ArrayList();
            for (int i : iArr) {
                Transport transport = Transport.USB;
                if (i != 0) {
                    if (i == 1) {
                        transport = Transport.NFC;
                    } else if (i == 2) {
                        transport = Transport.BLUETOOTH_LOW_ENERGY;
                    } else if (i == 4) {
                        transport = Transport.INTERNAL;
                    }
                }
                arrayList2.add(transport);
            }
            arrayList.add(new PublicKeyCredentialDescriptor("public-key", bArr, arrayList2));
        }
        return arrayList;
    }

    public static C5941zV c(AuthenticatorAssertionResponse authenticatorAssertionResponse, boolean z) {
        C5941zV zVVar = new C5941zV();
        C1753aw awVar = new C1753aw();
        zVVar.e = authenticatorAssertionResponse.I;
        zVVar.g = z;
        awVar.g = authenticatorAssertionResponse.H;
        awVar.d = Base64.encodeToString(authenticatorAssertionResponse.F, 11);
        awVar.e = authenticatorAssertionResponse.F;
        awVar.f = authenticatorAssertionResponse.G;
        zVVar.d = awVar;
        return zVVar;
    }

    public static PublicKeyCredentialCreationOptions d(C4554rI0 ri0) {
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria;
        Attachment attachment;
        C5235vI0 vi0 = ri0.d;
        Cq1 cq1 = vi0.f;
        PublicKeyCredentialRpEntity publicKeyCredentialRpEntity = new PublicKeyCredentialRpEntity(vi0.d, vi0.e, cq1 != null ? cq1.d : null);
        C5745yI0 yi0 = ri0.e;
        Cq1 cq12 = yi0.f;
        PublicKeyCredentialUserEntity publicKeyCredentialUserEntity = new PublicKeyCredentialUserEntity(yi0.d, yi0.e, cq12 != null ? cq12.d : null, yi0.g);
        ArrayList arrayList = new ArrayList();
        C4895tI0[] ti0Arr = ri0.g;
        for (C4895tI0 ti0 : ti0Arr) {
            int i = ti0.e;
            if (i == -7 && ti0.d == 0) {
                arrayList.add(new PublicKeyCredentialParameters("public-key", i));
            }
        }
        if (arrayList.size() != 0 || ri0.g.length == 0) {
            List b = b(ri0.i);
            C0064Bb bb = ri0.j;
            if (bb != null) {
                int i2 = bb.d;
                if (i2 == 0) {
                    attachment = null;
                } else if (i2 == 2) {
                    attachment = Attachment.CROSS_PLATFORM;
                } else {
                    attachment = Attachment.PLATFORM;
                }
                authenticatorSelectionCriteria = new AuthenticatorSelectionCriteria(attachment == null ? null : attachment.I, null, null);
            } else {
                authenticatorSelectionCriteria = null;
            }
            int i3 = ri0.k;
            AttestationConveyancePreference attestationConveyancePreference = AttestationConveyancePreference.NONE;
            if (i3 != 0) {
                if (i3 == 1) {
                    attestationConveyancePreference = AttestationConveyancePreference.INDIRECT;
                } else if (i3 == 2) {
                    attestationConveyancePreference = AttestationConveyancePreference.DIRECT;
                }
            }
            byte[] bArr = ri0.f;
            Objects.requireNonNull(bArr, "null reference");
            return new PublicKeyCredentialCreationOptions(publicKeyCredentialRpEntity, publicKeyCredentialUserEntity, bArr, arrayList, Double.valueOf(a(ri0.h)), b, authenticatorSelectionCriteria, null, null, attestationConveyancePreference.f9661J, null);
        }
        AbstractC1220Ua0.a("Fido2Helper", "None of the requested parameters are supported.", new Object[0]);
        throw new NoSuchAlgorithmException();
    }

    public static C0431Hb0 e(AuthenticatorAttestationResponse authenticatorAttestationResponse) {
        C0431Hb0 hb0 = new C0431Hb0();
        C1753aw awVar = new C1753aw();
        byte[] bArr = authenticatorAttestationResponse.H;
        hb0.e = bArr;
        Fido2Helper$AttestationObjectParts fido2Helper$AttestationObjectParts = new Fido2Helper$AttestationObjectParts();
        if (N.MehZ$ImQ(bArr, fido2Helper$AttestationObjectParts)) {
            hb0.l = fido2Helper$AttestationObjectParts.c;
            awVar.g = fido2Helper$AttestationObjectParts.f10807a;
            hb0.k = fido2Helper$AttestationObjectParts.b;
            hb0.f = new int[0];
            awVar.d = Base64.encodeToString(authenticatorAttestationResponse.F, 11);
            awVar.e = authenticatorAttestationResponse.F;
            awVar.f = authenticatorAttestationResponse.G;
            hb0.d = awVar;
            return hb0;
        }
        throw new NoSuchAlgorithmException();
    }
}
