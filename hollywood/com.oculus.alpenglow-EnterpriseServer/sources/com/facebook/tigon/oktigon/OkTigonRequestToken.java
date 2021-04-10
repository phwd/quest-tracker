package com.facebook.tigon.oktigon;

import X.AbstractC05270iw;
import X.AbstractC05650kC;
import X.AbstractC06530n3;
import X.AnonymousClass006;
import X.AnonymousClass0Od;
import X.AnonymousClass0PV;
import X.AnonymousClass0Qd;
import X.C01990Pw;
import X.C05360jA;
import X.C05400jG;
import X.C05570jz;
import X.C05660kD;
import com.facebook.jni.HybridData;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class OkTigonRequestToken extends AbstractRequestToken implements AbstractC06530n3 {
    public static final String TAG = "OkTigonRequestToken";
    public AnonymousClass0Qd mActiveCall;

    private native void failure(int i, String str, int i2, String str2);

    private native void success(int i, String[] strArr, long j, byte[] bArr);

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeHttpPriority(byte b, boolean z) {
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeTigonPriority(int i) {
    }

    static {
        C05400jG.A00("oktigon");
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void cancel() {
        AbstractC05270iw r1;
        C01990Pw r0;
        AnonymousClass0PV r12 = this.mActiveCall.A03;
        r12.A04 = true;
        C05360jA r3 = r12.A01;
        if (r3 != null) {
            synchronized (r3.A08) {
                r3.A04 = true;
                r1 = r3.A03;
                r0 = r3.A02;
            }
            if (r1 != null) {
                r1.cancel();
            } else if (r0 != null) {
                C05570jz.A07(r0.A03);
            }
        }
    }

    @Override // X.AbstractC06530n3
    public void onFailure(AnonymousClass0Qd r5, IOException iOException) {
        int i;
        if (iOException instanceof SocketTimeoutException) {
            i = 2;
        } else {
            i = 3;
            if ("Canceled".equals(iOException.getMessage())) {
                i = 1;
            }
        }
        failure(i, "OkTigonErrorDomain", 0, iOException.toString());
    }

    /* JADX INFO: finally extract failed */
    @Override // X.AbstractC06530n3
    public void onResponse(AnonymousClass0Qd r15, C05660kD r16) throws IOException {
        String str;
        AbstractC05650kC r1 = r16.A0B;
        long A00 = r1.A00();
        if (A00 <= 2147483647L) {
            AnonymousClass0Od A02 = r1.A02();
            try {
                byte[] A79 = A02.A79();
                C05570jz.A06(A02);
                if (A00 != -1) {
                    int length = A79.length;
                    if (A00 != ((long) length)) {
                        str = "Content-Length (" + A00 + ") and stream length (" + length + ") disagree";
                    }
                }
                int i = r16.A01;
                String[] strArr = r16.A06.A00;
                int length2 = strArr.length >> 1;
                String[] strArr2 = new String[(length2 << 1)];
                int i2 = 0;
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i2 + 1;
                    int i5 = i3 << 1;
                    strArr2[i2] = strArr[i5];
                    i2 = i4 + 1;
                    strArr2[i4] = strArr[i5 + 1];
                }
                success(i, strArr2, (long) A79.length, A79);
                r1.close();
                return;
            } catch (Throwable th) {
                C05570jz.A06(A02);
                throw th;
            }
        } else {
            str = AnonymousClass006.A04("Cannot buffer entire body for content length: ", A00);
        }
        throw new IOException(str);
    }

    public OkTigonRequestToken(HybridData hybridData) {
        super(hybridData);
    }
}
