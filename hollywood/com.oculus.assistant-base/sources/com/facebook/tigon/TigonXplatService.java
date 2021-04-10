package com.facebook.tigon;

import com.facebook.tigon.iface.TigonRequest;
import com.facebook.tigon.iface.TigonServiceHolder;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

public class TigonXplatService extends TigonServiceHolder {
    private native byte[] getNetworkStatusInfoNative();

    private native TigonXplatRequestToken sendRequestBodyBufferIntegerBuffer(TigonRequest tigonRequest, byte[] bArr, int i, TigonBodyProvider tigonBodyProvider, TigonCallbacks tigonCallbacks, Executor executor);

    private native TigonXplatRequestToken sendRequestIntegerBuffer(TigonRequest tigonRequest, byte[] bArr, int i, ByteBuffer[] byteBufferArr, int i2, TigonCallbacks tigonCallbacks, Executor executor);

    public native boolean hasSecretaryService();

    public native void releaseBodyBuffer(ByteBuffer byteBuffer);
}
