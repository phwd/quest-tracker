package java.io;

import android.support.v4.media.session.PlaybackStateCompat;

public class LineNumberReader extends BufferedReader {
    private static final int maxSkipBufferSize = 8192;
    private int lineNumber = 0;
    private int markedLineNumber;
    private boolean markedSkipLF;
    private char[] skipBuffer = null;
    private boolean skipLF;

    public LineNumberReader(Reader in) {
        super(in);
    }

    public LineNumberReader(Reader in, int sz) {
        super(in, sz);
    }

    public void setLineNumber(int lineNumber2) {
        this.lineNumber = lineNumber2;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public int read() throws IOException {
        synchronized (this.lock) {
            int c = super.read();
            if (this.skipLF) {
                if (c == 10) {
                    c = super.read();
                }
                this.skipLF = false;
            }
            if (c != 10) {
                if (c != 13) {
                    return c;
                }
                this.skipLF = true;
            }
            this.lineNumber++;
            return 10;
        }
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public int read(char[] cbuf, int off, int len) throws IOException {
        int n;
        synchronized (this.lock) {
            n = super.read(cbuf, off, len);
            for (int i = off; i < off + n; i++) {
                char c = cbuf[i];
                if (this.skipLF) {
                    this.skipLF = false;
                    if (c == '\n') {
                    }
                }
                if (c != '\n') {
                    if (c == '\r') {
                        this.skipLF = true;
                    }
                }
                this.lineNumber++;
            }
        }
        return n;
    }

    @Override // java.io.BufferedReader
    public String readLine() throws IOException {
        String l;
        synchronized (this.lock) {
            l = super.readLine(this.skipLF);
            this.skipLF = false;
            if (l != null) {
                this.lineNumber++;
            }
        }
        return l;
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public long skip(long n) throws IOException {
        long j;
        if (n >= 0) {
            int nn = (int) Math.min(n, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            synchronized (this.lock) {
                if (this.skipBuffer == null || this.skipBuffer.length < nn) {
                    this.skipBuffer = new char[nn];
                }
                long r = n;
                while (true) {
                    if (r <= 0) {
                        break;
                    }
                    int nc = read(this.skipBuffer, 0, (int) Math.min(r, (long) nn));
                    if (nc == -1) {
                        break;
                    }
                    r -= (long) nc;
                }
                j = n - r;
            }
            return j;
        }
        throw new IllegalArgumentException("skip() value is negative");
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public void mark(int readAheadLimit) throws IOException {
        synchronized (this.lock) {
            super.mark(readAheadLimit);
            this.markedLineNumber = this.lineNumber;
            this.markedSkipLF = this.skipLF;
        }
    }

    @Override // java.io.BufferedReader, java.io.Reader
    public void reset() throws IOException {
        synchronized (this.lock) {
            super.reset();
            this.lineNumber = this.markedLineNumber;
            this.skipLF = this.markedSkipLF;
        }
    }
}
