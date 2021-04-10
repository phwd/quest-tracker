package java.io;

import android.icu.lang.UCharacter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BufferedReader extends Reader {
    private static final int INVALIDATED = -2;
    private static final int UNMARKED = -1;
    private static int defaultCharBufferSize = 8192;
    private static int defaultExpectedLineLength = 80;
    private char[] cb;
    private Reader in;
    private int markedChar;
    private boolean markedSkipLF;
    private int nChars;
    private int nextChar;
    private int readAheadLimit;
    private boolean skipLF;

    public BufferedReader(Reader in2, int sz) {
        super(in2);
        this.markedChar = -1;
        this.readAheadLimit = 0;
        this.skipLF = false;
        this.markedSkipLF = false;
        if (sz > 0) {
            this.in = in2;
            this.cb = new char[sz];
            this.nChars = 0;
            this.nextChar = 0;
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    public BufferedReader(Reader in2) {
        this(in2, defaultCharBufferSize);
    }

    private void ensureOpen() throws IOException {
        if (this.in == null) {
            throw new IOException("Stream closed");
        }
    }

    private void fill() throws IOException {
        int dst;
        int n;
        int i = this.markedChar;
        if (i <= -1) {
            dst = 0;
        } else {
            int delta = this.nextChar - i;
            int i2 = this.readAheadLimit;
            if (delta >= i2) {
                this.markedChar = -2;
                this.readAheadLimit = 0;
                dst = 0;
            } else {
                char[] cArr = this.cb;
                if (i2 <= cArr.length) {
                    System.arraycopy((Object) cArr, i, (Object) cArr, 0, delta);
                    this.markedChar = 0;
                    dst = delta;
                } else {
                    int nlength = cArr.length * 2;
                    if (nlength > i2) {
                        nlength = this.readAheadLimit;
                    }
                    char[] ncb = new char[nlength];
                    System.arraycopy((Object) this.cb, this.markedChar, (Object) ncb, 0, delta);
                    this.cb = ncb;
                    this.markedChar = 0;
                    dst = delta;
                }
                this.nChars = delta;
                this.nextChar = delta;
            }
        }
        do {
            Reader reader = this.in;
            char[] cArr2 = this.cb;
            n = reader.read(cArr2, dst, cArr2.length - dst);
        } while (n == 0);
        if (n > 0) {
            this.nChars = dst + n;
            this.nextChar = dst;
        }
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            while (true) {
                if (this.nextChar >= this.nChars) {
                    fill();
                    if (this.nextChar >= this.nChars) {
                        return -1;
                    }
                }
                if (!this.skipLF) {
                    break;
                }
                this.skipLF = false;
                if (this.cb[this.nextChar] != '\n') {
                    break;
                }
                this.nextChar++;
            }
            char[] cArr = this.cb;
            int i = this.nextChar;
            this.nextChar = i + 1;
            return cArr[i];
        }
    }

    private int read1(char[] cbuf, int off, int len) throws IOException {
        if (this.nextChar >= this.nChars) {
            if (len >= this.cb.length && this.markedChar <= -1 && !this.skipLF) {
                return this.in.read(cbuf, off, len);
            }
            fill();
        }
        int i = this.nextChar;
        int i2 = this.nChars;
        if (i >= i2) {
            return -1;
        }
        if (this.skipLF) {
            this.skipLF = false;
            if (this.cb[i] == '\n') {
                this.nextChar = i + 1;
                if (this.nextChar >= i2) {
                    fill();
                }
                if (this.nextChar >= this.nChars) {
                    return -1;
                }
            }
        }
        int n = Math.min(len, this.nChars - this.nextChar);
        System.arraycopy((Object) this.cb, this.nextChar, (Object) cbuf, off, n);
        this.nextChar += n;
        return n;
    }

    @Override // java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        synchronized (this.lock) {
            ensureOpen();
            if (off < 0 || off > cbuf.length || len < 0 || off + len > cbuf.length || off + len < 0) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return 0;
            } else {
                int n = read1(cbuf, off, len);
                if (n <= 0) {
                    return n;
                }
                while (true) {
                    if (n >= len || !this.in.ready()) {
                        break;
                    }
                    int n1 = read1(cbuf, off + n, len - n);
                    if (n1 <= 0) {
                        break;
                    }
                    n += n1;
                }
                return n;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005e, code lost:
        r5 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0019  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0022 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String readLine(boolean r13) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.BufferedReader.readLine(boolean):java.lang.String");
    }

    public String readLine() throws IOException {
        return readLine(false);
    }

    @Override // java.io.Reader
    public long skip(long n) throws IOException {
        long j;
        if (n >= 0) {
            synchronized (this.lock) {
                ensureOpen();
                long r = n;
                while (true) {
                    if (r <= 0) {
                        break;
                    }
                    if (this.nextChar >= this.nChars) {
                        fill();
                    }
                    if (this.nextChar >= this.nChars) {
                        break;
                    }
                    if (this.skipLF) {
                        this.skipLF = false;
                        if (this.cb[this.nextChar] == '\n') {
                            this.nextChar++;
                        }
                    }
                    long d = (long) (this.nChars - this.nextChar);
                    if (r <= d) {
                        this.nextChar = (int) (((long) this.nextChar) + r);
                        r = 0;
                        break;
                    }
                    r -= d;
                    this.nextChar = this.nChars;
                }
                j = n - r;
            }
            return j;
        }
        throw new IllegalArgumentException("skip value is negative");
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        boolean z;
        synchronized (this.lock) {
            ensureOpen();
            z = false;
            if (this.skipLF) {
                if (this.nextChar >= this.nChars && this.in.ready()) {
                    fill();
                }
                if (this.nextChar < this.nChars) {
                    if (this.cb[this.nextChar] == '\n') {
                        this.nextChar++;
                    }
                    this.skipLF = false;
                }
            }
            if (this.nextChar < this.nChars || this.in.ready()) {
                z = true;
            }
        }
        return z;
    }

    @Override // java.io.Reader
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.Reader
    public void mark(int readAheadLimit2) throws IOException {
        if (readAheadLimit2 >= 0) {
            synchronized (this.lock) {
                ensureOpen();
                this.readAheadLimit = readAheadLimit2;
                this.markedChar = this.nextChar;
                this.markedSkipLF = this.skipLF;
            }
            return;
        }
        throw new IllegalArgumentException("Read-ahead limit < 0");
    }

    @Override // java.io.Reader
    public void reset() throws IOException {
        String str;
        synchronized (this.lock) {
            ensureOpen();
            if (this.markedChar < 0) {
                if (this.markedChar == -2) {
                    str = "Mark invalid";
                } else {
                    str = "Stream not marked";
                }
                throw new IOException(str);
            }
            this.nextChar = this.markedChar;
            this.skipLF = this.markedSkipLF;
        }
    }

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.lock) {
            if (this.in != null) {
                try {
                    this.in.close();
                } finally {
                    this.in = null;
                    this.cb = null;
                }
            }
        }
    }

    public Stream<String> lines() {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<String>() {
            /* class java.io.BufferedReader.AnonymousClass1 */
            String nextLine = null;

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextLine != null) {
                    return true;
                }
                try {
                    this.nextLine = BufferedReader.this.readLine();
                    if (this.nextLine != null) {
                        return true;
                    }
                    return false;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }

            @Override // java.util.Iterator
            public String next() {
                if (this.nextLine != null || hasNext()) {
                    String line = this.nextLine;
                    this.nextLine = null;
                    return line;
                }
                throw new NoSuchElementException();
            }
        }, (int) UCharacter.UnicodeBlock.TANGUT_ID), false);
    }
}
