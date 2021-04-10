package com.facebook.tigon.oktigon;

import X.AbstractC08210wB;
import X.AbstractC08570wn;
import X.AnonymousClass006;
import X.AnonymousClass0Lw;
import X.AnonymousClass0Mz;
import X.C03250cX;
import X.C08160w5;
import X.C08220wC;
import com.facebook.jni.HybridData;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class OkTigonRequestToken extends AbstractRequestToken implements AbstractC08570wn {
    public static final String TAG = "OkTigonRequestToken";
    public AnonymousClass0Mz mActiveCall;

    private native void failure(int i, String str, int i2, String str2);

    private native void success(int i, String[] strArr, long j, byte[] bArr);

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeHttpPriority(byte b, boolean z) {
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeTigonPriority(int i) {
    }

    static {
        C03250cX.A01("oktigon");
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void cancel() {
        this.mActiveCall.A02();
    }

    @Override // X.AbstractC08570wn
    public void onFailure(AnonymousClass0Mz r5, IOException iOException) {
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
    @Override // X.AbstractC08570wn
    public void onResponse(AnonymousClass0Mz r15, C08220wC r16) throws IOException {
        String str;
        AbstractC08210wB r1 = r16.A0B;
        long A00 = r1.A00();
        if (A00 <= 2147483647L) {
            AnonymousClass0Lw A03 = r1.A03();
            try {
                byte[] A7r = A03.A7r();
                C08160w5.A06(A03);
                if (A00 != -1) {
                    int length = A7r.length;
                    if (A00 != ((long) length)) {
                        StringBuilder sb = new StringBuilder("Content-Length (");
                        sb.append(A00);
                        sb.append(") and stream length (");
                        sb.append(length);
                        sb.append(") disagree");
                        str = sb.toString();
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
                success(i, strArr2, (long) A7r.length, A7r);
                r1.close();
                return;
            } catch (Throwable th) {
                C08160w5.A06(A03);
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
