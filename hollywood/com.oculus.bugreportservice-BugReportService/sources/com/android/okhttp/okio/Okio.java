package com.android.okhttp.okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Okio {
    private static final Logger logger = Logger.getLogger(Okio.class.getName());

    private Okio() {
    }

    public static BufferedSource buffer(Source source) {
        if (source != null) {
            return new RealBufferedSource(source);
        }
        throw new IllegalArgumentException("source == null");
    }

    public static BufferedSink buffer(Sink sink) {
        if (sink != null) {
            return new RealBufferedSink(sink);
        }
        throw new IllegalArgumentException("sink == null");
    }

    public static Sink sink(OutputStream outputStream) {
        return sink(outputStream, new Timeout());
    }

    private static Sink sink(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (timeout != null) {
            return new Sink() {
                /* class com.android.okhttp.okio.Okio.AnonymousClass1 */

                @Override // com.android.okhttp.okio.Sink
                public void write(Buffer buffer, long j) {
                    Util.checkOffsetAndCount(buffer.size, 0, j);
                    while (j > 0) {
                        Timeout.this.throwIfReached();
                        Segment segment = buffer.head;
                        int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
                        outputStream.write(segment.data, segment.pos, min);
                        segment.pos += min;
                        long j2 = (long) min;
                        j -= j2;
                        buffer.size -= j2;
                        if (segment.pos == segment.limit) {
                            buffer.head = segment.pop();
                            SegmentPool.recycle(segment);
                        }
                    }
                }

                @Override // com.android.okhttp.okio.Sink
                public void flush() {
                    outputStream.flush();
                }

                @Override // com.android.okhttp.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                    outputStream.close();
                }

                @Override // com.android.okhttp.okio.Sink
                public Timeout timeout() {
                    return Timeout.this;
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static Sink sink(Socket socket) {
        if (socket != null) {
            AsyncTimeout timeout = timeout(socket);
            return timeout.sink(sink(socket.getOutputStream(), timeout));
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static Source source(InputStream inputStream) {
        return source(inputStream, new Timeout());
    }

    private static Source source(final InputStream inputStream, final Timeout timeout) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (timeout != null) {
            return new Source() {
                /* class com.android.okhttp.okio.Okio.AnonymousClass2 */

                @Override // com.android.okhttp.okio.Source
                public long read(Buffer buffer, long j) {
                    int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                    if (i < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (i == 0) {
                        return 0;
                    } else {
                        try {
                            Timeout.this.throwIfReached();
                            Segment writableSegment = buffer.writableSegment(1);
                            int read = inputStream.read(writableSegment.data, writableSegment.limit, (int) Math.min(j, (long) (8192 - writableSegment.limit)));
                            if (read == -1) {
                                return -1;
                            }
                            writableSegment.limit += read;
                            long j2 = (long) read;
                            buffer.size += j2;
                            return j2;
                        } catch (AssertionError e) {
                            if (Okio.isAndroidGetsocknameError(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                @Override // java.io.Closeable, com.android.okhttp.okio.Source, java.lang.AutoCloseable
                public void close() {
                    inputStream.close();
                }

                @Override // com.android.okhttp.okio.Source
                public Timeout timeout() {
                    return Timeout.this;
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static Source source(File file) {
        if (file != null) {
            return source(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Source source(Socket socket) {
        if (socket != null) {
            AsyncTimeout timeout = timeout(socket);
            return timeout.source(source(socket.getInputStream(), timeout));
        }
        throw new IllegalArgumentException("socket == null");
    }

    private static AsyncTimeout timeout(final Socket socket) {
        return new AsyncTimeout() {
            /* class com.android.okhttp.okio.Okio.AnonymousClass3 */

            /* access modifiers changed from: protected */
            @Override // com.android.okhttp.okio.AsyncTimeout
            public IOException newTimeoutException(IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            /* access modifiers changed from: protected */
            @Override // com.android.okhttp.okio.AsyncTimeout
            public void timedOut() {
                try {
                    Socket.this.close();
                } catch (Exception e) {
                    Logger logger = Okio.logger;
                    Level level = Level.WARNING;
                    logger.log(level, "Failed to close timed out socket " + Socket.this, e);
                } catch (AssertionError e2) {
                    if (Okio.isAndroidGetsocknameError(e2)) {
                        Logger logger2 = Okio.logger;
                        Level level2 = Level.WARNING;
                        logger2.log(level2, "Failed to close timed out socket " + Socket.this, e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
