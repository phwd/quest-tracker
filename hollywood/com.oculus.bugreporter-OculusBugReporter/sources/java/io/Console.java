package java.io;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Formatter;
import sun.nio.cs.StreamDecoder;
import sun.nio.cs.StreamEncoder;

public final class Console implements Flushable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Console cons;
    private static boolean echoOff;
    private Charset cs;
    private Formatter formatter;
    private Writer out;
    private PrintWriter pw;
    private char[] rcb;
    private Object readLock;
    private Reader reader;
    private Object writeLock;

    private static native boolean echo(boolean z) throws IOException;

    private static native String encoding();

    private static native boolean istty();

    public PrintWriter writer() {
        return this.pw;
    }

    public Reader reader() {
        return this.reader;
    }

    public Console format(String fmt, Object... args) {
        this.formatter.format(fmt, args).flush();
        return this;
    }

    public Console printf(String format, Object... args) {
        return format(format, args);
    }

    public String readLine(String fmt, Object... args) {
        String line = null;
        synchronized (this.writeLock) {
            synchronized (this.readLock) {
                if (fmt.length() != 0) {
                    this.pw.format(fmt, args);
                }
                try {
                    char[] ca = readline(false);
                    if (ca != null) {
                        line = new String(ca);
                    }
                } catch (IOException x) {
                    throw new IOError(x);
                }
            }
        }
        return line;
    }

    public String readLine() {
        return readLine("", new Object[0]);
    }

    public char[] readPassword(String fmt, Object... args) {
        char[] passwd;
        synchronized (this.writeLock) {
            synchronized (this.readLock) {
                try {
                    echoOff = echo(false);
                    IOError ioe = null;
                    try {
                        if (fmt.length() != 0) {
                            this.pw.format(fmt, args);
                        }
                        passwd = readline(true);
                        try {
                            echoOff = echo(true);
                        } catch (IOException x) {
                            if (0 == 0) {
                                ioe = new IOError(x);
                            } else {
                                ioe.addSuppressed(x);
                            }
                        }
                        if (ioe == null) {
                            this.pw.println();
                        }
                    } catch (IOException x2) {
                        ioe = new IOError(x2);
                        try {
                            echoOff = echo(true);
                        } catch (IOException x3) {
                            ioe.addSuppressed(x3);
                        }
                    } catch (Throwable th) {
                        try {
                            echoOff = echo(true);
                        } catch (IOException x4) {
                            if (0 == 0) {
                                ioe = new IOError(x4);
                            } else {
                                ioe.addSuppressed(x4);
                            }
                        }
                        if (ioe == null) {
                            throw th;
                        }
                    }
                    throw ioe;
                } catch (IOException x5) {
                    throw new IOError(x5);
                }
            }
        }
        return passwd;
    }

    public char[] readPassword() {
        return readPassword("", new Object[0]);
    }

    @Override // java.io.Flushable
    public void flush() {
        this.pw.flush();
    }

    private char[] readline(boolean zeroOut) throws IOException {
        Reader reader2 = this.reader;
        char[] cArr = this.rcb;
        int len = reader2.read(cArr, 0, cArr.length);
        if (len < 0) {
            return null;
        }
        char[] cArr2 = this.rcb;
        if (cArr2[len - 1] == '\r') {
            len--;
        } else if (cArr2[len - 1] == '\n' && len - 1 > 0 && cArr2[len - 1] == '\r') {
            len--;
        }
        char[] b = new char[len];
        if (len > 0) {
            System.arraycopy((Object) this.rcb, 0, (Object) b, 0, len);
            if (zeroOut) {
                Arrays.fill(this.rcb, 0, len, ' ');
            }
        }
        return b;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private char[] grow() {
        char[] cArr = this.rcb;
        char[] t = new char[(cArr.length * 2)];
        System.arraycopy((Object) cArr, 0, (Object) t, 0, cArr.length);
        this.rcb = t;
        return this.rcb;
    }

    class LineReader extends Reader {
        private char[] cb = new char[1024];
        private Reader in;
        boolean leftoverLF = false;
        private int nChars = 0;
        private int nextChar = 0;

        LineReader(Reader in2) {
            this.in = in2;
        }

        @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Reader
        public boolean ready() throws IOException {
            return this.in.ready();
        }

        @Override // java.io.Reader
        public int read(char[] cbuf, int offset, int length) throws IOException {
            Throwable th;
            int off;
            int n;
            int off2 = offset;
            int end = offset + length;
            if (offset < 0 || offset > cbuf.length || length < 0 || end < 0 || end > cbuf.length) {
                throw new IndexOutOfBoundsException();
            }
            synchronized (Console.this.readLock) {
                boolean eof = false;
                do {
                    try {
                        if (this.nextChar >= this.nChars) {
                            do {
                                n = this.in.read(this.cb, 0, this.cb.length);
                            } while (n == 0);
                            if (n > 0) {
                                this.nChars = n;
                                this.nextChar = 0;
                                if (!(n >= this.cb.length || this.cb[n - 1] == '\n' || this.cb[n - 1] == '\r')) {
                                    eof = true;
                                }
                            } else if (off2 - offset == 0) {
                                return -1;
                            } else {
                                return off2 - offset;
                            }
                        }
                        if (this.leftoverLF && cbuf == Console.this.rcb && this.cb[this.nextChar] == '\n') {
                            this.nextChar++;
                        }
                        this.leftoverLF = false;
                        while (this.nextChar < this.nChars) {
                            int off3 = off2 + 1;
                            try {
                                char c = this.cb[this.nextChar];
                                cbuf[off2] = c;
                                char[] cArr = this.cb;
                                int i = this.nextChar;
                                this.nextChar = i + 1;
                                cArr[i] = 0;
                                if (c == '\n') {
                                    return off3 - offset;
                                } else if (c == '\r') {
                                    if (off3 == end) {
                                        if (cbuf == Console.this.rcb) {
                                            cbuf = Console.this.grow();
                                            int length2 = cbuf.length;
                                        } else {
                                            this.leftoverLF = true;
                                            return off3 - offset;
                                        }
                                    }
                                    if (this.nextChar == this.nChars && this.in.ready()) {
                                        this.nChars = this.in.read(this.cb, 0, this.cb.length);
                                        this.nextChar = 0;
                                    }
                                    if (this.nextChar >= this.nChars || this.cb[this.nextChar] != '\n') {
                                        off = off3;
                                    } else {
                                        off = off3 + 1;
                                        cbuf[off3] = '\n';
                                        this.nextChar++;
                                    }
                                    return off - offset;
                                } else if (off3 != end) {
                                    off2 = off3;
                                } else if (cbuf == Console.this.rcb) {
                                    cbuf = Console.this.grow();
                                    end = cbuf.length;
                                    off2 = off3;
                                } else {
                                    return off3 - offset;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                } while (!eof);
                return off2 - offset;
            }
        }
    }

    public static Console console() {
        if (!istty()) {
            return null;
        }
        if (cons == null) {
            cons = new Console();
        }
        return cons;
    }

    private Console() {
        this(new FileInputStream(FileDescriptor.in), new FileOutputStream(FileDescriptor.out));
    }

    private Console(InputStream inStream, OutputStream outStream) {
        this.readLock = new Object();
        this.writeLock = new Object();
        String csname = encoding();
        if (csname != null) {
            try {
                this.cs = Charset.forName(csname);
            } catch (Exception e) {
            }
        }
        if (this.cs == null) {
            this.cs = Charset.defaultCharset();
        }
        this.out = StreamEncoder.forOutputStreamWriter(outStream, this.writeLock, this.cs);
        this.pw = new PrintWriter(this.out, true) {
            /* class java.io.Console.AnonymousClass1 */

            @Override // java.io.PrintWriter, java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
            public void close() {
            }
        };
        this.formatter = new Formatter(this.out);
        this.reader = new LineReader(StreamDecoder.forInputStreamReader(inStream, this.readLock, this.cs));
        this.rcb = new char[1024];
    }
}
