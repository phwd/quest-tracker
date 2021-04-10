package com.facebook.proxygen;

import com.facebook.proxygen.utils.RequestDefragmentingOutputStream;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpUriRequest;

public class HTTPRequestHandler {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final int MAX_BUFFER_COUNT = 20;
    public static final int SMALL_REQUESTS_BODY_BUFFER_SIZE = 4096;
    public ArrayList mBodyBuffersPool = new ArrayList(20);
    public JniHandler mDelegate;
    public HandlerInterface mHandlerInterface = new HandlerInterface();
    public boolean mIsCanceled;

    public class AndroidBufferedOutputStream extends BufferedOutputStream {
        public boolean mClosed;

        private void checkNotClosed() {
            if (this.mClosed) {
                throw new IOException("stream already closed");
            }
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
        public void close() {
            if (!this.mClosed) {
                try {
                    super.close();
                } finally {
                    this.mClosed = true;
                }
            }
        }

        @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.BufferedOutputStream, java.io.Flushable
        public void flush() {
            checkNotClosed();
            super.flush();
        }

        public AndroidBufferedOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.BufferedOutputStream
        public void write(int i) {
            checkNotClosed();
            super.write(i);
        }

        @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.BufferedOutputStream
        public void write(byte[] bArr, int i, int i2) {
            checkNotClosed();
            super.write(bArr, i, i2);
        }
    }

    public class CloseSuppressingOutputStream extends FilterOutputStream {
        @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
        public void close() {
        }

        public void reallyClose() {
            this.out.close();
        }

        public CloseSuppressingOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        @Override // java.io.OutputStream, java.io.FilterOutputStream
        public void write(int i) {
            this.out.write(i);
        }

        @Override // java.io.OutputStream, java.io.FilterOutputStream
        public void write(byte[] bArr) {
            this.out.write(bArr);
        }

        @Override // java.io.OutputStream, java.io.FilterOutputStream
        public void write(byte[] bArr, int i, int i2) {
            this.out.write(bArr, i, i2);
        }
    }

    public class HandlerInterface implements RequestDefragmentingOutputStream.HandlerInterface {
        @Override // com.facebook.proxygen.utils.RequestDefragmentingOutputStream.HandlerInterface
        public boolean sendBody(byte[] bArr, int i, int i2) {
            return HTTPRequestHandler.this.sendBody(bArr, i, i2);
        }

        @Override // com.facebook.proxygen.utils.RequestDefragmentingOutputStream.HandlerInterface
        public boolean sendEOM() {
            return HTTPRequestHandler.this.sendEOM();
        }

        @Override // com.facebook.proxygen.utils.RequestDefragmentingOutputStream.HandlerInterface
        public boolean sendHeaders(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
            return HTTPRequestHandler.this.sendHeaders((HttpUriRequest) httpEntityEnclosingRequest);
        }

        @Override // com.facebook.proxygen.utils.RequestDefragmentingOutputStream.HandlerInterface
        public boolean sendRequestWithBodyAndEom(HttpEntityEnclosingRequest httpEntityEnclosingRequest, byte[] bArr, int i, int i2) {
            return HTTPRequestHandler.this.sendHeadersWithBodyAndEom((HttpUriRequest) httpEntityEnclosingRequest, bArr, i, i2);
        }

        public HandlerInterface() {
        }
    }

    private synchronized byte[] acquireBodyBuffer() {
        byte[] bArr;
        ArrayList arrayList = this.mBodyBuffersPool;
        if (arrayList.isEmpty()) {
            bArr = new byte[SMALL_REQUESTS_BODY_BUFFER_SIZE];
        } else {
            bArr = (byte[]) arrayList.remove(arrayList.size() - 1);
        }
        return bArr;
    }

    private synchronized void releaseBodyBuffer(byte[] bArr) {
        ArrayList arrayList = this.mBodyBuffersPool;
        if (arrayList.size() != 20) {
            arrayList.add(bArr);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean sendBody(byte[] bArr, int i, int i2) {
        JniHandler jniHandler = this.mDelegate;
        if (jniHandler != null) {
            return jniHandler.sendBody(bArr, i, i2);
        }
        return this.mIsCanceled;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean sendEOM() {
        JniHandler jniHandler = this.mDelegate;
        if (jniHandler != null) {
            return jniHandler.sendEOM();
        }
        return this.mIsCanceled;
    }

    public void cancel() {
        JniHandler jniHandler = this.mDelegate;
        if (jniHandler != null) {
            jniHandler.cancel();
            this.mIsCanceled = true;
            this.mDelegate = null;
        }
    }

    public void changePriority(byte b, boolean z) {
        JniHandler jniHandler = this.mDelegate;
        if (jniHandler != null) {
            jniHandler.changePriority(b, z);
        }
    }

    public void executeWithDefragmentation(HttpUriRequest httpUriRequest) {
        if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpUriRequest;
            if (httpEntityEnclosingRequest.getEntity() != null) {
                HttpEntity entity = httpEntityEnclosingRequest.getEntity();
                byte[] acquireBodyBuffer = acquireBodyBuffer();
                try {
                    RequestDefragmentingOutputStream requestDefragmentingOutputStream = new RequestDefragmentingOutputStream(this.mHandlerInterface, httpEntityEnclosingRequest, acquireBodyBuffer);
                    entity.writeTo(requestDefragmentingOutputStream);
                    requestDefragmentingOutputStream.writeEomIfNecessary();
                    return;
                } finally {
                    releaseBodyBuffer(acquireBodyBuffer);
                }
            }
        }
        sendHeadersWithBodyAndEom(httpUriRequest, EMPTY_BYTE_ARRAY, 0, 0);
    }

    public boolean sendHeaders(HttpUriRequest httpUriRequest) {
        JniHandler jniHandler = this.mDelegate;
        if (jniHandler != null) {
            return jniHandler.sendHeaders(httpUriRequest);
        }
        return this.mIsCanceled;
    }

    public boolean sendHeadersWithBodyAndEom(HttpUriRequest httpUriRequest, byte[] bArr, int i, int i2) {
        JniHandler jniHandler = this.mDelegate;
        if (jniHandler != null) {
            return jniHandler.sendRequestWithBodyAndEom(httpUriRequest, bArr, i, i2);
        }
        return this.mIsCanceled;
    }

    public void sendRequestBody(HttpUriRequest httpUriRequest) {
        if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
            try {
                processEntityRequest((HttpEntityEnclosingRequest) httpUriRequest);
            } catch (LigerNetworkException unused) {
            } catch (IOException e) {
                cancel();
                throw e;
            }
        }
    }

    private void processEntityRequest(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        HttpEntity entity = httpEntityEnclosingRequest.getEntity();
        if (entity != null) {
            CloseSuppressingOutputStream closeSuppressingOutputStream = new CloseSuppressingOutputStream(new AndroidBufferedOutputStream(new LigerBodyOutputStream()));
            try {
                entity.writeTo(closeSuppressingOutputStream);
                closeSuppressingOutputStream.flush();
            } finally {
                closeSuppressingOutputStream.reallyClose();
            }
        }
    }

    public void setDelegate(JniHandler jniHandler) {
        this.mDelegate = jniHandler;
    }

    public class LigerBodyOutputStream extends OutputStream {
        public LigerBodyOutputStream() {
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            if (!HTTPRequestHandler.this.sendBody(new byte[]{(byte) i}, 0, 1)) {
                throw new LigerNetworkException();
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            if (!HTTPRequestHandler.this.sendBody(bArr, i, i2)) {
                throw new LigerNetworkException();
            }
        }
    }

    public class LigerNetworkException extends IOException {
        public LigerNetworkException() {
        }

        public /* synthetic */ LigerNetworkException(AnonymousClass1 r1) {
        }
    }
}
