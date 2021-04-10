package com.squareup.okhttp.internal.framed;

import X.AnonymousClass8k;
import X.Dp;
import X.Dv;
import X.WE;
import X.WF;
import X.WG;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public final class FramedStream {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public long bytesLeftInWriteWindow;
    public final FramedConnection connection;
    public ErrorCode errorCode = null;
    public final int id;
    public final StreamTimeout readTimeout = new StreamTimeout();
    public final List<Header> requestHeaders;
    public List<Header> responseHeaders;
    public final FramedDataSink sink;
    public final FramedDataSource source;
    public long unacknowledgedBytesRead = 0;
    public final StreamTimeout writeTimeout = new StreamTimeout();

    public final class FramedDataSink implements WG {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final long EMIT_BUFFER_SIZE = 16384;
        public boolean closed;
        public boolean finished;
        public final AnonymousClass8k sendBuffer = new AnonymousClass8k();

        public FramedDataSink() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
            if (r7 != r9.sendBuffer.A00) goto L_0x0056;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void emitDataFrame(boolean r10) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 121
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedStream.FramedDataSink.emitDataFrame(boolean):void");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, X.WG
        public void close() throws IOException {
            boolean z;
            synchronized (FramedStream.this) {
                z = this.closed;
            }
            if (!z) {
                FramedStream framedStream = FramedStream.this;
                if (!framedStream.sink.finished) {
                    if (this.sendBuffer.A00 <= 0) {
                        framedStream.connection.writeData(framedStream.id, true, null, 0);
                    } else {
                        while (this.sendBuffer.A00 > 0) {
                            emitDataFrame(true);
                        }
                    }
                }
                synchronized (FramedStream.this) {
                    this.closed = true;
                }
                FramedStream.this.connection.flush();
                FramedStream.this.cancelStreamIfNecessary();
            }
        }

        @Override // X.WG, java.io.Flushable
        public void flush() throws IOException {
            synchronized (FramedStream.this) {
                FramedStream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.A00 > 0) {
                emitDataFrame(false);
                FramedStream.this.connection.flush();
            }
        }

        @Override // X.WG
        public WE timeout() {
            return FramedStream.this.writeTimeout;
        }

        @Override // X.WG
        public void write(AnonymousClass8k r6, long j) throws IOException {
            this.sendBuffer.write(r6, j);
            while (this.sendBuffer.A00 >= EMIT_BUFFER_SIZE) {
                emitDataFrame(false);
            }
        }
    }

    public final class FramedDataSource implements WF {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public boolean closed;
        public boolean finished;
        public final long maxByteCount;
        public final AnonymousClass8k readBuffer;
        public final AnonymousClass8k receiveBuffer;

        private void checkNotClosed() throws IOException {
            String str;
            if (!this.closed) {
                ErrorCode errorCode = FramedStream.this.errorCode;
                if (errorCode != null) {
                    StringBuilder sb = new StringBuilder("stream was reset: ");
                    sb.append(errorCode);
                    str = sb.toString();
                } else {
                    return;
                }
            } else {
                str = "stream closed";
            }
            throw new IOException(str);
        }

        private void waitUntilReadable() throws IOException {
            FramedStream.this.readTimeout.enter();
            while (this.readBuffer.A00 == 0 && !this.finished && !this.closed) {
                try {
                    FramedStream framedStream = FramedStream.this;
                    if (framedStream.errorCode != null) {
                        break;
                    }
                    framedStream.waitForIo();
                } finally {
                    FramedStream.this.readTimeout.exitAndThrowIfTimedOut();
                }
            }
        }

        @Override // java.io.Closeable, X.WF, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (FramedStream.this) {
                this.closed = true;
                this.readBuffer.A08();
                FramedStream.this.notifyAll();
            }
            FramedStream.this.cancelStreamIfNecessary();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
            r7 = r13.this$0.connection;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            r8 = r13.this$0.connection;
            r3 = r8.unacknowledgedBytesRead + r11;
            r8.unacknowledgedBytesRead = r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
            if (r3 < ((long) (r8.okHttpSettings.getInitialWindowSize(65536) / 2))) goto L_0x006b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
            r8.writeWindowUpdateLater(0, r3);
            r13.this$0.connection.unacknowledgedBytesRead = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x006b, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x006c, code lost:
            return r11;
         */
        @Override // X.WF
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long read(X.AnonymousClass8k r14, long r15) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 127
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.framed.FramedStream.FramedDataSource.read(X.8k, long):long");
        }

        public void receive(Dp dp, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (FramedStream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = false;
                    if (this.readBuffer.A00 + j > this.maxByteCount) {
                        z3 = true;
                    }
                }
                if (z3) {
                    dp.A3i(j);
                    FramedStream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z) {
                    dp.A3i(j);
                    return;
                } else {
                    long read = dp.read(this.receiveBuffer, j);
                    if (read != -1) {
                        j -= read;
                        synchronized (FramedStream.this) {
                            AnonymousClass8k r3 = this.readBuffer;
                            if (r3.A00 != 0) {
                                z2 = false;
                            }
                            r3.A3x(this.receiveBuffer);
                            if (z2) {
                                FramedStream.this.notifyAll();
                            }
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
        }

        @Override // X.WF
        public WE timeout() {
            return FramedStream.this.readTimeout;
        }

        public FramedDataSource(long j) {
            this.receiveBuffer = new AnonymousClass8k();
            this.readBuffer = new AnonymousClass8k();
            this.maxByteCount = j;
        }
    }

    public class StreamTimeout extends Dv {
        public StreamTimeout() {
        }

        @Override // X.Dv
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // X.Dv
        public void timedOut() {
            FramedStream.this.closeLater(ErrorCode.CANCEL);
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void cancelStreamIfNecessary() throws IOException {
        boolean z;
        boolean isOpen;
        synchronized (this) {
            FramedDataSource framedDataSource = this.source;
            if (!framedDataSource.finished && framedDataSource.closed) {
                FramedDataSink framedDataSink = this.sink;
                if (framedDataSink.finished || framedDataSink.closed) {
                    z = true;
                    isOpen = isOpen();
                }
            }
            z = false;
            isOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL);
        } else if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    private boolean closeInternal(ErrorCode errorCode2) {
        synchronized (this) {
            if (this.errorCode != null || (this.source.finished && this.sink.finished)) {
                return false;
            }
            this.errorCode = errorCode2;
            notifyAll();
            this.connection.removeStream(this.id);
            return true;
        }
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    /* JADX INFO: finally extract failed */
    public synchronized List<Header> getResponseHeaders() throws IOException {
        List<Header> list;
        this.readTimeout.enter();
        while (this.responseHeaders == null && this.errorCode == null) {
            try {
                waitForIo();
            } catch (Throwable th) {
                this.readTimeout.exitAndThrowIfTimedOut();
                throw th;
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        list = this.responseHeaders;
        if (list == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("stream was reset: ");
            sb.append(this.errorCode);
            throw new IOException(sb.toString());
        }
        return list;
    }

    public WG getSink() {
        synchronized (this) {
            if (this.responseHeaders == null && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode == null) {
            FramedDataSource framedDataSource = this.source;
            if (framedDataSource.finished || framedDataSource.closed) {
                FramedDataSink framedDataSink = this.sink;
                if ((framedDataSink.finished || framedDataSink.closed) && this.responseHeaders != null) {
                }
            }
            return true;
        }
        return false;
    }

    public void receiveFin() {
        boolean isOpen;
        synchronized (this) {
            this.source.finished = true;
            isOpen = isOpen();
            notifyAll();
        }
        if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    public void receiveHeaders(List<Header> list, HeadersMode headersMode) {
        ErrorCode errorCode2 = null;
        boolean z = true;
        synchronized (this) {
            List<Header> list2 = this.responseHeaders;
            if (list2 == null) {
                if (headersMode.failIfHeadersAbsent()) {
                    errorCode2 = ErrorCode.PROTOCOL_ERROR;
                } else {
                    this.responseHeaders = list;
                    z = isOpen();
                    notifyAll();
                }
            } else if (headersMode.failIfHeadersPresent()) {
                errorCode2 = ErrorCode.STREAM_IN_USE;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list2);
                arrayList.addAll(list);
                this.responseHeaders = arrayList;
            }
        }
        if (errorCode2 != null) {
            closeLater(errorCode2);
        } else if (!z) {
            this.connection.removeStream(this.id);
        }
    }

    public synchronized void receiveRstStream(ErrorCode errorCode2) {
        if (this.errorCode == null) {
            this.errorCode = errorCode2;
            notifyAll();
        }
    }

    public void reply(List<Header> list, boolean z) throws IOException {
        boolean z2 = false;
        synchronized (this) {
            if (list == null) {
                throw new NullPointerException("responseHeaders == null");
            } else if (this.responseHeaders == null) {
                this.responseHeaders = list;
                if (!z) {
                    this.sink.finished = true;
                    z2 = true;
                }
            } else {
                throw new IllegalStateException("reply already sent");
            }
        }
        this.connection.writeSynReply(this.id, z2, list);
        if (z2) {
            this.connection.flush();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void checkOutNotClosed() throws IOException {
        String str;
        FramedDataSink framedDataSink = this.sink;
        if (framedDataSink.closed) {
            str = "stream closed";
        } else if (!framedDataSink.finished) {
            ErrorCode errorCode2 = this.errorCode;
            if (errorCode2 != null) {
                StringBuilder sb = new StringBuilder("stream was reset: ");
                sb.append(errorCode2);
                str = sb.toString();
            } else {
                return;
            }
        } else {
            str = "stream finished";
        }
        throw new IOException(str);
    }

    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public boolean isLocallyInitiated() {
        boolean z = false;
        if ((this.id & 1) == 1) {
            z = true;
        }
        if (this.connection.client != z) {
            return false;
        }
        return true;
    }

    public void receiveData(Dp dp, int i) throws IOException {
        this.source.receive(dp, (long) i);
    }

    public FramedStream(int i, FramedConnection framedConnection, boolean z, boolean z2, List<Header> list) {
        String str;
        if (framedConnection == null) {
            str = "connection == null";
        } else if (list != null) {
            this.id = i;
            this.connection = framedConnection;
            this.bytesLeftInWriteWindow = (long) framedConnection.peerSettings.getInitialWindowSize(65536);
            FramedDataSource framedDataSource = new FramedDataSource((long) framedConnection.okHttpSettings.getInitialWindowSize(65536));
            this.source = framedDataSource;
            FramedDataSink framedDataSink = new FramedDataSink();
            this.sink = framedDataSink;
            framedDataSource.finished = z2;
            framedDataSink.finished = z;
            this.requestHeaders = list;
            return;
        } else {
            str = "requestHeaders == null";
        }
        throw new NullPointerException(str);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            throw new InterruptedIOException();
        }
    }

    public void close(ErrorCode errorCode2) throws IOException {
        if (closeInternal(errorCode2)) {
            this.connection.writeSynReset(this.id, errorCode2);
        }
    }

    public void closeLater(ErrorCode errorCode2) {
        if (closeInternal(errorCode2)) {
            this.connection.writeSynResetLater(this.id, errorCode2);
        }
    }

    public FramedConnection getConnection() {
        return this.connection;
    }

    public int getId() {
        return this.id;
    }

    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    public WF getSource() {
        return this.source;
    }

    public WE readTimeout() {
        return this.readTimeout;
    }

    public WE writeTimeout() {
        return this.writeTimeout;
    }
}
