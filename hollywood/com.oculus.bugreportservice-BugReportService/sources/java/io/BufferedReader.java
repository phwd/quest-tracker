package java.io;

public class BufferedReader extends Reader {
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

    public BufferedReader(Reader reader, int i) {
        super(reader);
        this.markedChar = -1;
        this.readAheadLimit = 0;
        this.skipLF = false;
        this.markedSkipLF = false;
        if (i > 0) {
            this.in = reader;
            this.cb = new char[i];
            this.nChars = 0;
            this.nextChar = 0;
            return;
        }
        throw new IllegalArgumentException("Buffer size <= 0");
    }

    public BufferedReader(Reader reader) {
        this(reader, defaultCharBufferSize);
    }

    private void ensureOpen() {
        if (this.in == null) {
            throw new IOException("Stream closed");
        }
    }

    private void fill() {
        int read;
        int i = this.markedChar;
        int i2 = 0;
        if (i > -1) {
            int i3 = this.nextChar - i;
            int i4 = this.readAheadLimit;
            if (i3 >= i4) {
                this.markedChar = -2;
                this.readAheadLimit = 0;
            } else {
                char[] cArr = this.cb;
                if (i4 <= cArr.length) {
                    System.arraycopy((Object) cArr, i, (Object) cArr, 0, i3);
                    this.markedChar = 0;
                } else {
                    int length = cArr.length * 2;
                    if (length > i4) {
                        length = i4;
                    }
                    char[] cArr2 = new char[length];
                    System.arraycopy((Object) this.cb, this.markedChar, (Object) cArr2, 0, i3);
                    this.cb = cArr2;
                    this.markedChar = 0;
                }
                this.nChars = i3;
                this.nextChar = i3;
                i2 = i3;
            }
        }
        do {
            Reader reader = this.in;
            char[] cArr3 = this.cb;
            read = reader.read(cArr3, i2, cArr3.length - i2);
        } while (read == 0);
        if (read > 0) {
            this.nChars = read + i2;
            this.nextChar = i2;
        }
    }

    private int read1(char[] cArr, int i, int i2) {
        if (this.nextChar >= this.nChars) {
            if (i2 >= this.cb.length && this.markedChar <= -1 && !this.skipLF) {
                return this.in.read(cArr, i, i2);
            }
            fill();
        }
        int i3 = this.nextChar;
        int i4 = this.nChars;
        if (i3 >= i4) {
            return -1;
        }
        if (this.skipLF) {
            this.skipLF = false;
            if (this.cb[i3] == '\n') {
                this.nextChar = i3 + 1;
                if (this.nextChar >= i4) {
                    fill();
                }
                if (this.nextChar >= this.nChars) {
                    return -1;
                }
            }
        }
        int min = Math.min(i2, this.nChars - this.nextChar);
        System.arraycopy((Object) this.cb, this.nextChar, (Object) cArr, i, min);
        this.nextChar += min;
        return min;
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) {
        int i3;
        synchronized (this.lock) {
            ensureOpen();
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 == 0) {
                return 0;
            } else {
                int read1 = read1(cArr, i, i2);
                if (read1 <= 0) {
                    return read1;
                }
                while (true) {
                    if (read1 >= i2 || !this.in.ready()) {
                        break;
                    }
                    int read12 = read1(cArr, i + read1, i2 - read1);
                    if (read12 <= 0) {
                        break;
                    }
                    read1 += read12;
                }
                return read1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005b, code lost:
        r5 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0023 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String readLine(boolean r10) {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.BufferedReader.readLine(boolean):java.lang.String");
    }

    public String readLine() {
        return readLine(false);
    }

    @Override // java.io.Reader
    public boolean ready() {
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

    @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
    public void close() {
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
}
