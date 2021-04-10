package java.util.jar;

import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Manifest implements Cloneable {
    private Attributes attr = new Attributes();
    private Map<String, Attributes> entries = new HashMap();

    public Manifest() {
    }

    public Manifest(InputStream is) throws IOException {
        read(is);
    }

    public Manifest(Manifest man) {
        this.attr.putAll(man.getMainAttributes());
        this.entries.putAll(man.getEntries());
    }

    public Attributes getMainAttributes() {
        return this.attr;
    }

    public Map<String, Attributes> getEntries() {
        return this.entries;
    }

    public Attributes getAttributes(String name) {
        return getEntries().get(name);
    }

    public void clear() {
        this.attr.clear();
        this.entries.clear();
    }

    public void write(OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        this.attr.writeMain(dos);
        for (Map.Entry<String, Attributes> e : this.entries.entrySet()) {
            StringBuffer buffer = new StringBuffer("Name: ");
            String value = e.getKey();
            if (value != null) {
                byte[] vb = value.getBytes("UTF8");
                value = new String(vb, 0, 0, vb.length);
            }
            buffer.append(value);
            buffer.append("\r\n");
            make72Safe(buffer);
            dos.writeBytes(buffer.toString());
            e.getValue().write(dos);
        }
        dos.flush();
    }

    static void make72Safe(StringBuffer line) {
        int length = line.length();
        if (length > 72) {
            int index = 70;
            while (index < length - 2) {
                line.insert(index, "\r\n ");
                index += 72;
                length += 3;
            }
        }
    }

    public void read(InputStream is) throws IOException {
        FastInputStream fis = new FastInputStream(is);
        byte[] lbuf = new byte[512];
        this.attr.read(fis, lbuf);
        int ecount = 0;
        int acount = 0;
        int asize = 2;
        String name = null;
        boolean skipEmptyLines = true;
        byte[] lastline = null;
        while (true) {
            int len = fis.readLine(lbuf);
            if (len != -1) {
                int len2 = len - 1;
                if (lbuf[len2] == 10) {
                    if (len2 > 0 && lbuf[len2 - 1] == 13) {
                        len2--;
                    }
                    if (len2 != 0 || !skipEmptyLines) {
                        skipEmptyLines = false;
                        if (name == null) {
                            name = parseName(lbuf, len2);
                            if (name == null) {
                                throw new IOException("invalid manifest format");
                            } else if (fis.peek() == 32) {
                                lastline = new byte[(len2 - 6)];
                                System.arraycopy(lbuf, 6, lastline, 0, len2 - 6);
                            }
                        } else {
                            byte[] buf = new byte[((lastline.length + len2) - 1)];
                            System.arraycopy(lastline, 0, buf, 0, lastline.length);
                            System.arraycopy(lbuf, 1, buf, lastline.length, len2 - 1);
                            if (fis.peek() == 32) {
                                lastline = buf;
                            } else {
                                name = new String(buf, 0, buf.length, "UTF8");
                                lastline = null;
                            }
                        }
                        Attributes attr2 = getAttributes(name);
                        if (attr2 == null) {
                            attr2 = new Attributes(asize);
                            this.entries.put(name, attr2);
                        }
                        attr2.read(fis, lbuf);
                        ecount++;
                        acount += attr2.size();
                        asize = Math.max(2, acount / ecount);
                        name = null;
                        skipEmptyLines = true;
                    }
                } else {
                    throw new IOException("manifest line too long");
                }
            } else {
                return;
            }
        }
    }

    private String parseName(byte[] lbuf, int len) {
        if (toLower(lbuf[0]) != 110 || toLower(lbuf[1]) != 97 || toLower(lbuf[2]) != 109 || toLower(lbuf[3]) != 101 || lbuf[4] != 58 || lbuf[5] != 32) {
            return null;
        }
        try {
            return new String(lbuf, 6, len - 6, "UTF8");
        } catch (Exception e) {
            return null;
        }
    }

    private int toLower(int c) {
        return (c < 65 || c > 90) ? c : (c - 65) + 97;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Manifest)) {
            return false;
        }
        Manifest m = (Manifest) o;
        if (!this.attr.equals(m.getMainAttributes()) || !this.entries.equals(m.getEntries())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.attr.hashCode() + this.entries.hashCode();
    }

    public Object clone() {
        return new Manifest(this);
    }

    /* access modifiers changed from: package-private */
    public static class FastInputStream extends FilterInputStream {
        private byte[] buf;
        private int count;
        private int pos;

        FastInputStream(InputStream in) {
            this(in, 8192);
        }

        FastInputStream(InputStream in, int size) {
            super(in);
            this.count = 0;
            this.pos = 0;
            this.buf = new byte[size];
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            if (this.pos >= this.count) {
                fill();
                if (this.pos >= this.count) {
                    return -1;
                }
            }
            byte[] bArr = this.buf;
            int i = this.pos;
            this.pos = i + 1;
            return Byte.toUnsignedInt(bArr[i]);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            int avail = this.count - this.pos;
            if (avail <= 0) {
                if (len >= this.buf.length) {
                    return this.in.read(b, off, len);
                }
                fill();
                avail = this.count - this.pos;
                if (avail <= 0) {
                    return -1;
                }
            }
            if (len > avail) {
                len = avail;
            }
            System.arraycopy(this.buf, this.pos, b, off, len);
            this.pos += len;
            return len;
        }

        public int readLine(byte[] b, int off, int len) throws IOException {
            byte[] tbuf = this.buf;
            int total = 0;
            while (total < len) {
                int avail = this.count - this.pos;
                if (avail <= 0) {
                    fill();
                    avail = this.count - this.pos;
                    if (avail <= 0) {
                        return -1;
                    }
                }
                int n = len - total;
                if (n > avail) {
                    n = avail;
                }
                int tpos = this.pos;
                int maxpos = tpos + n;
                while (true) {
                    if (tpos >= maxpos) {
                        break;
                    }
                    int tpos2 = tpos + 1;
                    if (tbuf[tpos] == 10) {
                        tpos = tpos2;
                        break;
                    }
                    tpos = tpos2;
                }
                int tpos3 = this.pos;
                int n2 = tpos - tpos3;
                System.arraycopy(tbuf, tpos3, b, off, n2);
                off += n2;
                total += n2;
                this.pos = tpos;
                if (tbuf[tpos - 1] == 10) {
                    break;
                }
            }
            return total;
        }

        public byte peek() throws IOException {
            if (this.pos == this.count) {
                fill();
            }
            int i = this.pos;
            if (i == this.count) {
                return -1;
            }
            return this.buf[i];
        }

        public int readLine(byte[] b) throws IOException {
            return readLine(b, 0, b.length);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long n) throws IOException {
            if (n <= 0) {
                return 0;
            }
            long avail = (long) (this.count - this.pos);
            if (avail <= 0) {
                return this.in.skip(n);
            }
            if (n > avail) {
                n = avail;
            }
            this.pos = (int) (((long) this.pos) + n);
            return n;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() throws IOException {
            return (this.count - this.pos) + this.in.available();
        }

        @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            if (this.in != null) {
                this.in.close();
                this.in = null;
                this.buf = null;
            }
        }

        private void fill() throws IOException {
            this.pos = 0;
            this.count = 0;
            InputStream inputStream = this.in;
            byte[] bArr = this.buf;
            int n = inputStream.read(bArr, 0, bArr.length);
            if (n > 0) {
                this.count = n;
            }
        }
    }
}
