package okio;

import X.AnonymousClass006;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class Okio {
    public static final Logger logger = Logger.getLogger(Okio.class.getName());

    public static Sink appendingSink(File file) throws FileNotFoundException {
        if (file != null) {
            return sink(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink blackhole() {
        return new Sink() {
            /* class okio.Okio.AnonymousClass3 */

            @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
            public void close() throws IOException {
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return Timeout.NONE;
            }

            @Override // okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                buffer.skip(j);
            }
        };
    }

    public static AsyncTimeout timeout(final Socket socket) {
        return new AsyncTimeout() {
            /* class okio.Okio.AnonymousClass4 */

            @Override // okio.AsyncTimeout
            public IOException newTimeoutException(@Nullable IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            @Override // okio.AsyncTimeout
            public void timedOut() {
                try {
                    socket.close();
                } catch (Exception e) {
                    Logger logger = Okio.logger;
                    Level level = Level.WARNING;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to close timed out socket ");
                    sb.append(socket);
                    logger.log(level, sb.toString(), (Throwable) e);
                } catch (AssertionError e2) {
                    if (Okio.isAndroidGetsocknameError(e2)) {
                        Logger logger2 = Okio.logger;
                        Level level2 = Level.WARNING;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Failed to close timed out socket ");
                        sb2.append(socket);
                        logger2.log(level2, sb2.toString(), (Throwable) e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        if (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) {
            return false;
        }
        return true;
    }

    public static BufferedSink buffer(Sink sink) {
        return new RealBufferedSink(sink);
    }

    public static BufferedSource buffer(Source source) {
        return new RealBufferedSource(source);
    }

    public static Sink sink(File file) throws FileNotFoundException {
        if (file != null) {
            return sink(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static Sink sink(OutputStream outputStream) {
        return sink(outputStream, new Timeout());
    }

    public static Sink sink(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (timeout != null) {
            return new Sink() {
                /* class okio.Okio.AnonymousClass1 */

                @Override // okio.Sink
                public void write(Buffer buffer, long j) throws IOException {
                    long j2 = j;
                    Util.checkOffsetAndCount(buffer.size, 0, j2);
                    while (j2 > 0) {
                        Timeout.this.throwIfReached();
                        Segment segment = buffer.head;
                        int i = segment.limit;
                        int i2 = segment.pos;
                        int min = (int) Math.min(j2, (long) (i - i2));
                        outputStream.write(segment.data, i2, min);
                        int i3 = segment.pos + min;
                        segment.pos = i3;
                        long j3 = (long) min;
                        j2 -= j3;
                        buffer.size -= j3;
                        if (i3 == segment.limit) {
                            buffer.head = segment.pop();
                            SegmentPool.recycle(segment);
                        }
                    }
                }

                @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
                public void close() throws IOException {
                    outputStream.close();
                }

                @Override // okio.Sink, java.io.Flushable
                public void flush() throws IOException {
                    outputStream.flush();
                }

                public String toString() {
                    StringBuilder sb = new StringBuilder("sink(");
                    sb.append(outputStream);
                    sb.append(")");
                    return sb.toString();
                }

                @Override // okio.Sink
                public Timeout timeout() {
                    return Timeout.this;
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static Sink sink(final Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getOutputStream() != null) {
            AnonymousClass4 r2 = new AsyncTimeout() {
                /* class okio.Okio.AnonymousClass4 */

                @Override // okio.AsyncTimeout
                public IOException newTimeoutException(@Nullable IOException iOException) {
                    SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                    if (iOException != null) {
                        socketTimeoutException.initCause(iOException);
                    }
                    return socketTimeoutException;
                }

                @Override // okio.AsyncTimeout
                public void timedOut() {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        Logger logger = Okio.logger;
                        Level level = Level.WARNING;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to close timed out socket ");
                        sb.append(socket);
                        logger.log(level, sb.toString(), (Throwable) e);
                    } catch (AssertionError e2) {
                        if (Okio.isAndroidGetsocknameError(e2)) {
                            Logger logger2 = Okio.logger;
                            Level level2 = Level.WARNING;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Failed to close timed out socket ");
                            sb2.append(socket);
                            logger2.log(level2, sb2.toString(), (Throwable) e2);
                            return;
                        }
                        throw e2;
                    }
                }
            };
            return 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: RETURN  
                  (wrap: okio.AsyncTimeout$1 : 0x0017: CONSTRUCTOR  (r0v4 okio.AsyncTimeout$1) = 
                  (r2v0 'r2' okio.Okio$4)
                  (wrap: okio.Sink : 0x0011: INVOKE  (r1v2 okio.Sink) = 
                  (wrap: java.io.OutputStream : 0x000d: INVOKE  (r0v3 java.io.OutputStream) = (r3v0 'socket' java.net.Socket) type: VIRTUAL call: java.net.Socket.getOutputStream():java.io.OutputStream)
                  (r2v0 'r2' okio.Okio$4)
                 type: STATIC call: okio.Okio.sink(java.io.OutputStream, okio.Timeout):okio.Sink)
                 call: okio.AsyncTimeout.1.<init>(okio.AsyncTimeout, okio.Sink):void type: CONSTRUCTOR)
                 in method: okio.Okio.sink(java.net.Socket):okio.Sink, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:176)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:153)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: CONSTRUCTOR  (r0v4 okio.AsyncTimeout$1) = 
                  (r2v0 'r2' okio.Okio$4)
                  (wrap: okio.Sink : 0x0011: INVOKE  (r1v2 okio.Sink) = 
                  (wrap: java.io.OutputStream : 0x000d: INVOKE  (r0v3 java.io.OutputStream) = (r3v0 'socket' java.net.Socket) type: VIRTUAL call: java.net.Socket.getOutputStream():java.io.OutputStream)
                  (r2v0 'r2' okio.Okio$4)
                 type: STATIC call: okio.Okio.sink(java.io.OutputStream, okio.Timeout):okio.Sink)
                 call: okio.AsyncTimeout.1.<init>(okio.AsyncTimeout, okio.Sink):void type: CONSTRUCTOR in method: okio.Okio.sink(java.net.Socket):okio.Sink, file: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                	... 21 more
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: okio.AsyncTimeout, state: GENERATED_AND_UNLOADED
                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                	... 25 more
                */
            /*
                if (r3 == 0) goto L_0x0023
                java.io.OutputStream r0 = r3.getOutputStream()
                if (r0 == 0) goto L_0x001b
                okio.Okio$4 r2 = new okio.Okio$4
                r2.<init>(r3)
                java.io.OutputStream r0 = r3.getOutputStream()
                okio.Sink r1 = sink(r0, r2)
                okio.AsyncTimeout$1 r0 = new okio.AsyncTimeout$1
                r0.<init>(r1)
                return r0
            L_0x001b:
                java.lang.String r1 = "socket's output stream == null"
                java.io.IOException r0 = new java.io.IOException
                r0.<init>(r1)
                throw r0
            L_0x0023:
                java.lang.String r1 = "socket == null"
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.Okio.sink(java.net.Socket):okio.Sink");
        }

        @IgnoreJRERequirement
        public static Sink sink(Path path, OpenOption... openOptionArr) throws IOException {
            if (path != null) {
                return sink(Files.newOutputStream(path, openOptionArr));
            }
            throw new IllegalArgumentException("path == null");
        }

        public static Source source(File file) throws FileNotFoundException {
            if (file != null) {
                return source(new FileInputStream(file));
            }
            throw new IllegalArgumentException("file == null");
        }

        public static Source source(InputStream inputStream) {
            return source(inputStream, new Timeout());
        }

        public static Source source(final InputStream inputStream, final Timeout timeout) {
            if (inputStream == null) {
                throw new IllegalArgumentException("in == null");
            } else if (timeout != null) {
                return new Source() {
                    /* class okio.Okio.AnonymousClass2 */

                    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        inputStream.close();
                    }

                    @Override // okio.Source
                    public long read(Buffer buffer, long j) throws IOException {
                        if (j < 0) {
                            throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
                        } else if (j == 0) {
                            return 0;
                        } else {
                            try {
                                Timeout.this.throwIfReached();
                                Segment writableSegment = buffer.writableSegment(1);
                                int i = writableSegment.limit;
                                int read = inputStream.read(writableSegment.data, i, (int) Math.min(j, (long) (8192 - i)));
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

                    public String toString() {
                        StringBuilder sb = new StringBuilder("source(");
                        sb.append(inputStream);
                        sb.append(")");
                        return sb.toString();
                    }

                    @Override // okio.Source
                    public Timeout timeout() {
                        return Timeout.this;
                    }
                };
            } else {
                throw new IllegalArgumentException("timeout == null");
            }
        }

        public static Source source(final Socket socket) throws IOException {
            if (socket == null) {
                throw new IllegalArgumentException("socket == null");
            } else if (socket.getInputStream() != null) {
                AnonymousClass4 r2 = new AsyncTimeout() {
                    /* class okio.Okio.AnonymousClass4 */

                    @Override // okio.AsyncTimeout
                    public IOException newTimeoutException(@Nullable IOException iOException) {
                        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                        if (iOException != null) {
                            socketTimeoutException.initCause(iOException);
                        }
                        return socketTimeoutException;
                    }

                    @Override // okio.AsyncTimeout
                    public void timedOut() {
                        try {
                            socket.close();
                        } catch (Exception e) {
                            Logger logger = Okio.logger;
                            Level level = Level.WARNING;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Failed to close timed out socket ");
                            sb.append(socket);
                            logger.log(level, sb.toString(), (Throwable) e);
                        } catch (AssertionError e2) {
                            if (Okio.isAndroidGetsocknameError(e2)) {
                                Logger logger2 = Okio.logger;
                                Level level2 = Level.WARNING;
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Failed to close timed out socket ");
                                sb2.append(socket);
                                logger2.log(level2, sb2.toString(), (Throwable) e2);
                                return;
                            }
                            throw e2;
                        }
                    }
                };
                return 
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: RETURN  
                      (wrap: okio.AsyncTimeout$2 : 0x0017: CONSTRUCTOR  (r0v4 okio.AsyncTimeout$2) = 
                      (r2v0 'r2' okio.Okio$4)
                      (wrap: okio.Source : 0x0011: INVOKE  (r1v2 okio.Source) = 
                      (wrap: java.io.InputStream : 0x000d: INVOKE  (r0v3 java.io.InputStream) = (r3v0 'socket' java.net.Socket) type: VIRTUAL call: java.net.Socket.getInputStream():java.io.InputStream)
                      (r2v0 'r2' okio.Okio$4)
                     type: STATIC call: okio.Okio.source(java.io.InputStream, okio.Timeout):okio.Source)
                     call: okio.AsyncTimeout.2.<init>(okio.AsyncTimeout, okio.Source):void type: CONSTRUCTOR)
                     in method: okio.Okio.source(java.net.Socket):okio.Source, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:99)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:143)
                    	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:176)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:153)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
                    	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.util.ArrayList.forEach(ArrayList.java:1259)
                    	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0017: CONSTRUCTOR  (r0v4 okio.AsyncTimeout$2) = 
                      (r2v0 'r2' okio.Okio$4)
                      (wrap: okio.Source : 0x0011: INVOKE  (r1v2 okio.Source) = 
                      (wrap: java.io.InputStream : 0x000d: INVOKE  (r0v3 java.io.InputStream) = (r3v0 'socket' java.net.Socket) type: VIRTUAL call: java.net.Socket.getInputStream():java.io.InputStream)
                      (r2v0 'r2' okio.Okio$4)
                     type: STATIC call: okio.Okio.source(java.io.InputStream, okio.Timeout):okio.Source)
                     call: okio.AsyncTimeout.2.<init>(okio.AsyncTimeout, okio.Source):void type: CONSTRUCTOR in method: okio.Okio.source(java.net.Socket):okio.Source, file: classes.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:255)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
                    	... 21 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: okio.AsyncTimeout, state: GENERATED_AND_UNLOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:215)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:630)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:363)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
                    	... 25 more
                    */
                /*
                    if (r3 == 0) goto L_0x0023
                    java.io.InputStream r0 = r3.getInputStream()
                    if (r0 == 0) goto L_0x001b
                    okio.Okio$4 r2 = new okio.Okio$4
                    r2.<init>(r3)
                    java.io.InputStream r0 = r3.getInputStream()
                    okio.Source r1 = source(r0, r2)
                    okio.AsyncTimeout$2 r0 = new okio.AsyncTimeout$2
                    r0.<init>(r1)
                    return r0
                L_0x001b:
                    java.lang.String r1 = "socket's input stream == null"
                    java.io.IOException r0 = new java.io.IOException
                    r0.<init>(r1)
                    throw r0
                L_0x0023:
                    java.lang.String r1 = "socket == null"
                    java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                    r0.<init>(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: okio.Okio.source(java.net.Socket):okio.Source");
            }

            @IgnoreJRERequirement
            public static Source source(Path path, OpenOption... openOptionArr) throws IOException {
                if (path != null) {
                    return source(Files.newInputStream(path, openOptionArr));
                }
                throw new IllegalArgumentException("path == null");
            }
        }
