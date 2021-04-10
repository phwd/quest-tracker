package com.facebook.tigon.oktigon;

import X.AbstractC0528bR;
import X.AbstractC0555bs;
import X.AnonymousClass08;
import X.C0554br;
import X.C0561by;
import X.C1146tm;
import X.KV;
import X.t4;
import com.facebook.jni.HybridData;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class OkTigonRequestToken extends AbstractRequestToken implements AbstractC0528bR {
    public static final String TAG = "OkTigonRequestToken";
    public C1146tm mActiveCall;

    private native void failure(int i, String str, int i2, String str2);

    private native void success(int i, String[] strArr, long j, byte[] bArr);

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeHttpPriority(byte b, boolean z) {
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeTigonPriority(int i) {
    }

    static {
        KV.A01("oktigon");
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void cancel() {
        this.mActiveCall.A01();
    }

    @Override // X.AbstractC0528bR
    public void onFailure(C1146tm tmVar, IOException iOException) {
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
    @Override // X.AbstractC0528bR
    public void onResponse(C1146tm tmVar, C0554br brVar) {
        AbstractC0555bs bsVar = brVar.A0B;
        long A00 = bsVar.A00();
        if (A00 <= 2147483647L) {
            t4 A01 = bsVar.A01();
            try {
                byte[] A4d = A01.A4d();
                C0561by.A06(A01);
                if (A00 != -1) {
                    int length = A4d.length;
                    if (A00 != ((long) length)) {
                        StringBuilder sb = new StringBuilder("Content-Length (");
                        sb.append(A00);
                        sb.append(") and stream length (");
                        sb.append(length);
                        sb.append(") disagree");
                        throw new IOException(sb.toString());
                    }
                }
                int i = brVar.A02;
                String[] strArr = brVar.A06.A00;
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
                success(i, strArr2, (long) A4d.length, A4d);
                bsVar.close();
            } catch (Throwable th) {
                C0561by.A06(A01);
                throw th;
            }
        } else {
            throw new IOException(AnonymousClass08.A03("Cannot buffer entire body for content length: ", A00));
        }
    }

    public OkTigonRequestToken(HybridData hybridData) {
        super(hybridData);
    }
}
