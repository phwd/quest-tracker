package java.util.jar;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Manifest implements Cloneable {
    private Attributes attr = new Attributes();
    private Map entries = new HashMap();

    private int toLower(int i) {
        return (i < 65 || i > 90) ? i : (i - 65) + 97;
    }

    public Manifest() {
    }

    public Manifest(InputStream inputStream) {
        read(inputStream);
    }

    public Manifest(Manifest manifest) {
        this.attr.putAll(manifest.getMainAttributes());
        this.entries.putAll(manifest.getEntries());
    }

    public Attributes getMainAttributes() {
        return this.attr;
    }

    public Map getEntries() {
        return this.entries;
    }

    public Attributes getAttributes(String str) {
        return (Attributes) getEntries().get(str);
    }

    public void read(InputStream inputStream) {
        FastInputStream fastInputStream = new FastInputStream(inputStream);
        byte[] bArr = new byte[512];
        this.attr.read(fastInputStream, bArr);
        int i = 2;
        String str = null;
        byte[] bArr2 = null;
        boolean z = true;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int readLine = fastInputStream.readLine(bArr);
            if (readLine != -1) {
                int i4 = readLine - 1;
                if (bArr[i4] == 10) {
                    if (i4 > 0 && bArr[i4 - 1] == 13) {
                        i4--;
                    }
                    if (i4 != 0 || !z) {
                        if (str == null) {
                            str = parseName(bArr, i4);
                            if (str == null) {
                                throw new IOException("invalid manifest format");
                            } else if (fastInputStream.peek() == 32) {
                                int i5 = i4 - 6;
                                bArr2 = new byte[i5];
                                System.arraycopy(bArr, 6, bArr2, 0, i5);
                                z = false;
                            } else {
                                Attributes attributes = getAttributes(str);
                                if (attributes == null) {
                                    attributes = new Attributes(i);
                                    this.entries.put(str, attributes);
                                }
                                attributes.read(fastInputStream, bArr);
                                i2++;
                                i3 += attributes.size();
                                i = Math.max(2, i3 / i2);
                                str = null;
                                z = true;
                            }
                        } else {
                            byte[] bArr3 = new byte[((bArr2.length + i4) - 1)];
                            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                            System.arraycopy(bArr, 1, bArr3, bArr2.length, i4 - 1);
                            if (fastInputStream.peek() == 32) {
                                z = false;
                                bArr2 = bArr3;
                            } else {
                                new String(bArr3, 0, bArr3.length, "UTF8");
                                throw null;
                            }
                        }
                    }
                } else {
                    throw new IOException("manifest line too long");
                }
            } else {
                return;
            }
        }
    }

    private String parseName(byte[] bArr, int i) {
        if (toLower(bArr[0]) == 110 && toLower(bArr[1]) == 97 && toLower(bArr[2]) == 109 && toLower(bArr[3]) == 101 && bArr[4] == 58 && bArr[5] == 32) {
            try {
                new String(bArr, 6, i - 6, "UTF8");
                throw null;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Manifest)) {
            return false;
        }
        Manifest manifest = (Manifest) obj;
        if (!this.attr.equals(manifest.getMainAttributes()) || !this.entries.equals(manifest.getEntries())) {
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

        FastInputStream(InputStream inputStream) {
            this(inputStream, 8192);
        }

        FastInputStream(InputStream inputStream, int i) {
            super(inputStream);
            this.count = 0;
            this.pos = 0;
            this.buf = new byte[i];
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() {
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
        public int read(byte[] bArr, int i, int i2) {
            int i3 = this.count - this.pos;
            if (i3 <= 0) {
                if (i2 >= this.buf.length) {
                    return this.in.read(bArr, i, i2);
                }
                fill();
                i3 = this.count - this.pos;
                if (i3 <= 0) {
                    return -1;
                }
            }
            if (i2 > i3) {
                i2 = i3;
            }
            System.arraycopy(this.buf, this.pos, bArr, i, i2);
            this.pos += i2;
            return i2;
        }

        public int readLine(byte[] bArr, int i, int i2) {
            byte[] bArr2 = this.buf;
            int i3 = 0;
            while (i3 < i2) {
                int i4 = this.count - this.pos;
                if (i4 <= 0) {
                    fill();
                    i4 = this.count - this.pos;
                    if (i4 <= 0) {
                        return -1;
                    }
                }
                int i5 = i2 - i3;
                if (i5 <= i4) {
                    i4 = i5;
                }
                int i6 = this.pos;
                int i7 = i4 + i6;
                while (true) {
                    if (i6 >= i7) {
                        break;
                    }
                    int i8 = i6 + 1;
                    if (bArr2[i6] == 10) {
                        i6 = i8;
                        break;
                    }
                    i6 = i8;
                }
                int i9 = this.pos;
                int i10 = i6 - i9;
                System.arraycopy(bArr2, i9, bArr, i, i10);
                i += i10;
                i3 += i10;
                this.pos = i6;
                if (bArr2[i6 - 1] == 10) {
                    break;
                }
            }
            return i3;
        }

        public byte peek() {
            if (this.pos == this.count) {
                fill();
            }
            int i = this.pos;
            if (i == this.count) {
                return -1;
            }
            return this.buf[i];
        }

        public int readLine(byte[] bArr) {
            return readLine(bArr, 0, bArr.length);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) {
            if (j <= 0) {
                return 0;
            }
            long j2 = (long) (this.count - this.pos);
            if (j2 <= 0) {
                return this.in.skip(j);
            }
            if (j > j2) {
                j = j2;
            }
            this.pos = (int) (((long) this.pos) + j);
            return j;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int available() {
            return (this.count - this.pos) + this.in.available();
        }

        @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() {
            if (this.in != null) {
                this.in.close();
                this.in = null;
                this.buf = null;
            }
        }

        private void fill() {
            this.pos = 0;
            this.count = 0;
            InputStream inputStream = this.in;
            byte[] bArr = this.buf;
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read > 0) {
                this.count = read;
            }
        }
    }
}
