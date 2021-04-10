package com.facebook.tigon.oktigon;

import X.AnonymousClass0lD;
import com.facebook.jni.HybridData;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public class OkTigonRequestToken extends AbstractRequestToken implements Callback {
    public static final String TAG = "OkTigonRequestToken";
    public Call mActiveCall;

    private native void failure(int i, String str, int i2, String str2);

    private native void success(int i, String[] strArr, long j, byte[] bArr);

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeHttpPriority(byte b, boolean z) {
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeTigonPriority(int i) {
    }

    static {
        AnonymousClass0lD.A01("oktigon");
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void cancel() {
        this.mActiveCall.cancel();
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException iOException) {
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

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) throws IOException {
        byte[] bytes = response.body.bytes();
        int i = response.code;
        Headers headers = response.headers;
        String[] strArr = headers.namesAndValues;
        int length = strArr.length >> 1;
        String[] strArr2 = new String[(length << 1)];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i2 + 1;
            strArr2[i2] = strArr[i3 << 1];
            i2 = i4 + 1;
            strArr2[i4] = headers.value(i3);
        }
        success(i, strArr2, (long) bytes.length, bytes);
        response.body.close();
    }

    public OkTigonRequestToken(HybridData hybridData) {
        super(hybridData);
    }
}
