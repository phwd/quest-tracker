package java.io;

import java.util.Enumeration;
import java.util.Vector;

public class SequenceInputStream extends InputStream {
    Enumeration<? extends InputStream> e;
    InputStream in;

    public SequenceInputStream(Enumeration<? extends InputStream> e2) {
        this.e = e2;
        try {
            nextStream();
        } catch (IOException e3) {
            throw new Error("panic");
        }
    }

    public SequenceInputStream(InputStream s1, InputStream s2) {
        Vector<InputStream> v = new Vector<>(2);
        v.addElement(s1);
        v.addElement(s2);
        this.e = v.elements();
        try {
            nextStream();
        } catch (IOException e2) {
            throw new Error("panic");
        }
    }

    /* access modifiers changed from: package-private */
    public final void nextStream() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream != null) {
            inputStream.close();
        }
        if (this.e.hasMoreElements()) {
            this.in = (InputStream) this.e.nextElement();
            if (this.in == null) {
                throw new NullPointerException();
            }
            return;
        }
        this.in = null;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        InputStream inputStream = this.in;
        if (inputStream == null) {
            return 0;
        }
        return inputStream.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        while (true) {
            InputStream inputStream = this.in;
            if (inputStream == null) {
                return -1;
            }
            int c = inputStream.read();
            if (c != -1) {
                return c;
            }
            nextStream();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        if (this.in == null) {
            return -1;
        }
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        } else {
            do {
                int n = this.in.read(b, off, len);
                if (n > 0) {
                    return n;
                }
                nextStream();
            } while (this.in != null);
            return -1;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        do {
            nextStream();
        } while (this.in != null);
    }
}
