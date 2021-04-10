package com.facebook.tigon.oktigon;

import X.AbstractC0358df;
import X.AnonymousClass06;
import X.C0350dM;
import X.C0359dg;
import X.C0431hn;
import X.KC;
import X.L1;
import X.L6;
import X.LB;
import X.dL;
import X.dZ;
import com.facebook.jni.HybridData;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class OkTigonRequestToken extends AbstractRequestToken {
    public static final String TAG = "OkTigonRequestToken";
    public LB mActiveCall;

    private native void failure(int i, String str, int i2, String str2);

    private native void success(int i, String[] strArr, long j, byte[] bArr);

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeHttpPriority(byte b, boolean z) {
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeTigonPriority(int i) {
    }

    static {
        C0431hn.A00("oktigon");
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void cancel() {
        dL dLVar;
        L6 l6;
        L1 l1 = this.mActiveCall.A03;
        l1.A04 = true;
        C0350dM dMVar = l1.A01;
        if (dMVar != null) {
            synchronized (dMVar.A08) {
                dMVar.A04 = true;
                dLVar = dMVar.A03;
                l6 = dMVar.A02;
            }
            if (dLVar != null) {
                dLVar.cancel();
            } else if (l6 != null) {
                dZ.A07(l6.A03);
            }
        }
    }

    public void onFailure(LB lb, IOException iOException) {
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
    public void onResponse(LB lb, C0359dg dgVar) throws IOException {
        String str;
        AbstractC0358df dfVar = dgVar.A0B;
        long A00 = dfVar.A00();
        if (A00 <= 2147483647L) {
            KC A02 = dfVar.A02();
            try {
                byte[] A4S = A02.A4S();
                dZ.A06(A02);
                if (A00 != -1) {
                    int length = A4S.length;
                    if (A00 != ((long) length)) {
                        StringBuilder sb = new StringBuilder("Content-Length (");
                        sb.append(A00);
                        sb.append(") and stream length (");
                        sb.append(length);
                        sb.append(") disagree");
                        str = sb.toString();
                    }
                }
                int i = dgVar.A01;
                String[] strArr = dgVar.A06.A00;
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
                success(i, strArr2, (long) A4S.length, A4S);
                dfVar.close();
                return;
            } catch (Throwable th) {
                dZ.A06(A02);
                throw th;
            }
        } else {
            str = AnonymousClass06.A03("Cannot buffer entire body for content length: ", A00);
        }
        throw new IOException(str);
    }

    public OkTigonRequestToken(HybridData hybridData) {
        super(hybridData);
    }
}
