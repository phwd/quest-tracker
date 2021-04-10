package com.facebook.tigon.oktigon;

import com.facebook.jni.HybridData;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.tigon.javaservice.AbstractRequestToken;
import java.io.IOException;
import java.net.SocketTimeoutException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Response;

public class OkTigonRequestToken extends AbstractRequestToken implements Callback {
    private static final String TAG = OkTigonRequestToken.class.getSimpleName();
    private Call mActiveCall;

    private native void failure(int i, String str, int i2, String str2);

    private native void success(int i, String[] strArr, long j, byte[] bArr);

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeHttpPriority(byte b, boolean z) {
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void changeTigonPriority(int i) {
    }

    static {
        NativeLoader.loadLibrary("oktigon");
    }

    protected OkTigonRequestToken(HybridData hybridData) {
        super(hybridData);
    }

    public void attachActiveCall(Call call) {
        this.mActiveCall = call;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException iOException) {
        failure(mapOkTigonException(iOException), "OkTigonErrorDomain", 0, iOException.toString());
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) throws IOException {
        byte[] bytes = response.body().bytes();
        success(response.code(), flattenHeaders(response.headers()), (long) bytes.length, bytes);
        response.body().close();
    }

    @Override // com.facebook.tigon.javaservice.AbstractRequestToken
    public void cancel() {
        this.mActiveCall.cancel();
    }

    private static int mapOkTigonException(IOException iOException) {
        if (iOException instanceof SocketTimeoutException) {
            return 2;
        }
        return "Canceled".equals(iOException.getMessage()) ? 1 : 3;
    }

    private static String[] flattenHeaders(Headers headers) {
        String[] strArr = new String[(headers.size() * 2)];
        int i = 0;
        for (int i2 = 0; i2 < headers.size(); i2++) {
            int i3 = i + 1;
            strArr[i] = headers.name(i2);
            i = i3 + 1;
            strArr[i3] = headers.value(i2);
        }
        return strArr;
    }
}
