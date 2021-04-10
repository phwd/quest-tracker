package com.facebook.stetho.websocket;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

/* access modifiers changed from: package-private */
public class WebSocketSession implements SimpleSession {
    private final SimpleEndpoint mEndpoint;
    private final WriteCallback mErrorForwardingWriteCallback = new WriteCallback() {
        /* class com.facebook.stetho.websocket.WebSocketSession.AnonymousClass2 */

        @Override // com.facebook.stetho.websocket.WriteCallback
        public void onSuccess() {
        }

        @Override // com.facebook.stetho.websocket.WriteCallback
        public void onFailure(IOException iOException) {
            WebSocketSession.this.signalError(iOException);
        }
    };
    private AtomicBoolean mIsOpen = new AtomicBoolean(false);
    private final ReadCallback mReadCallback = new ReadCallback() {
        /* class com.facebook.stetho.websocket.WebSocketSession.AnonymousClass1 */

        private void handlePong(byte[] bArr, int i) {
        }

        @Override // com.facebook.stetho.websocket.ReadCallback
        public void onCompleteFrame(byte b, byte[] bArr, int i) {
            if (b == 1) {
                handleTextFrame(bArr, i);
            } else if (b != 2) {
                switch (b) {
                    case 8:
                        handleClose(bArr, i);
                        return;
                    case 9:
                        handlePing(bArr, i);
                        return;
                    case 10:
                        handlePong(bArr, i);
                        return;
                    default:
                        WebSocketSession webSocketSession = WebSocketSession.this;
                        webSocketSession.signalError(new IOException("Unsupported frame opcode=" + ((int) b)));
                        return;
                }
            } else {
                handleBinaryFrame(bArr, i);
            }
        }

        private void handleClose(byte[] bArr, int i) {
            String str;
            int i2;
            if (i >= 2) {
                i2 = ((bArr[0] & 255) << 8) | (bArr[1] & 255);
                str = i > 2 ? new String(bArr, 2, i - 2) : null;
            } else {
                i2 = 1006;
                str = "Unparseable close frame";
            }
            if (!WebSocketSession.this.mSentClose) {
                WebSocketSession.this.sendClose(1000, "Received close frame");
            }
            WebSocketSession.this.markAndSignalClosed(i2, str);
        }

        private void handlePing(byte[] bArr, int i) {
            WebSocketSession.this.doWrite(FrameHelper.createPongFrame(bArr, i));
        }

        private void handleTextFrame(byte[] bArr, int i) {
            WebSocketSession.this.mEndpoint.onMessage(WebSocketSession.this, new String(bArr, 0, i));
        }

        private void handleBinaryFrame(byte[] bArr, int i) {
            WebSocketSession.this.mEndpoint.onMessage(WebSocketSession.this, bArr, i);
        }
    };
    private final ReadHandler mReadHandler;
    private volatile boolean mSentClose;
    private final WriteHandler mWriteHandler;

    public WebSocketSession(InputStream inputStream, OutputStream outputStream, SimpleEndpoint simpleEndpoint) {
        this.mReadHandler = new ReadHandler(inputStream, simpleEndpoint);
        this.mWriteHandler = new WriteHandler(outputStream);
        this.mEndpoint = simpleEndpoint;
    }

    public void handle() throws IOException {
        markAndSignalOpen();
        try {
            this.mReadHandler.readLoop(this.mReadCallback);
        } catch (EOFException unused) {
            markAndSignalClosed(1011, "EOF while reading");
        } catch (IOException e) {
            markAndSignalClosed(1006, null);
            throw e;
        }
    }

    @Override // com.facebook.stetho.websocket.SimpleSession
    public void sendText(String str) {
        doWrite(FrameHelper.createTextFrame(str));
    }

    @Override // com.facebook.stetho.websocket.SimpleSession
    public void sendBinary(byte[] bArr) {
        doWrite(FrameHelper.createBinaryFrame(bArr));
    }

    @Override // com.facebook.stetho.websocket.SimpleSession
    public void close(int i, String str) {
        sendClose(i, str);
        markAndSignalClosed(i, str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void sendClose(int i, String str) {
        doWrite(FrameHelper.createCloseFrame(i, str));
        markSentClose();
    }

    /* access modifiers changed from: package-private */
    public void markSentClose() {
        this.mSentClose = true;
    }

    /* access modifiers changed from: package-private */
    public void markAndSignalOpen() {
        if (!this.mIsOpen.getAndSet(true)) {
            this.mEndpoint.onOpen(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void markAndSignalClosed(int i, String str) {
        if (this.mIsOpen.getAndSet(false)) {
            this.mEndpoint.onClose(this, i, str);
        }
    }

    @Override // com.facebook.stetho.websocket.SimpleSession
    public boolean isOpen() {
        return this.mIsOpen.get();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doWrite(Frame frame) {
        if (!signalErrorIfNotOpen()) {
            this.mWriteHandler.write(frame, this.mErrorForwardingWriteCallback);
        }
    }

    private boolean signalErrorIfNotOpen() {
        if (isOpen()) {
            return false;
        }
        signalError(new IOException("Session is closed"));
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void signalError(IOException iOException) {
        this.mEndpoint.onError(this, iOException);
    }
}
