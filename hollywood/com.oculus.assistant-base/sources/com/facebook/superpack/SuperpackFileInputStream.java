package com.facebook.superpack;

import com.facebook.acra.util.HttpRequest;
import java.io.InputStream;

public class SuperpackFileInputStream extends InputStream {
    public int A00;
    public int A01;
    public int A02;
    public Boolean A03;
    public byte[] A04;
    public final SuperpackFile A05;

    public final synchronized void mark(int i) {
        this.A01 = this.A02;
    }

    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public final synchronized void reset() {
        this.A02 = this.A01;
    }

    @Override // java.io.InputStream
    public final synchronized long skip(long j) {
        if (j < 0) {
            return 0;
        }
        int i = this.A02;
        long j2 = (long) i;
        int i2 = this.A00;
        if (j2 + j > ((long) i2)) {
            j = (long) (i2 - i);
        }
        this.A02 = (int) (j2 + j);
        return j;
    }

    public static SuperpackFileInputStream createFromSingletonArchiveInputStream(InputStream inputStream, String str) {
        if (inputStream != null) {
            SuperpackArchive superpackArchive = new SuperpackArchive(SuperpackArchive.readNative(inputStream, str), null);
            try {
                if (superpackArchive.hasNext()) {
                    SuperpackFile next = superpackArchive.next();
                    if (!superpackArchive.hasNext()) {
                        SuperpackFileInputStream superpackFileInputStream = new SuperpackFileInputStream(next, true);
                        superpackArchive.close();
                        return superpackFileInputStream;
                    }
                    throw new IllegalArgumentException();
                }
                throw new IllegalArgumentException();
            } catch (Throwable unused) {
            }
        } else {
            throw null;
        }
        throw th;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
        if (this.A03.booleanValue()) {
            this.A05.close();
        }
    }

    public SuperpackFileInputStream(SuperpackFile superpackFile) {
        int i;
        if (superpackFile != null) {
            this.A05 = superpackFile;
            this.A02 = 0;
            synchronized (superpackFile) {
                if (superpackFile.mPtr != 0) {
                    i = superpackFile.mLength;
                } else {
                    throw new IllegalStateException();
                }
            }
            this.A00 = i;
            this.A01 = 0;
            this.A04 = null;
            this.A03 = false;
            return;
        }
        throw null;
    }

    public SuperpackFileInputStream(SuperpackFile superpackFile, Boolean bool) {
        this(superpackFile);
        this.A03 = bool;
    }

    @Override // java.io.InputStream
    public final synchronized int read() {
        int i;
        byte[] bArr = this.A04;
        if (bArr == null) {
            bArr = new byte[1];
            this.A04 = bArr;
        }
        int read = read(bArr);
        i = -1;
        if (read != -1) {
            if (read == 1) {
                byte b = this.A04[0];
                i = b;
                if (b < 0) {
                    i = b + HttpRequest.CHAR_ARRAY_BUFFER_SIZE;
                }
            } else {
                throw new IllegalStateException("Unexpected number of bytes read");
            }
        }
        return i == 1 ? 1 : 0;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public final synchronized int read(byte[] bArr, int i, int i2) {
        int i3 = i2;
        synchronized (this) {
            if (bArr != null) {
                if (i >= 0 && i2 >= 0) {
                    int i4 = i2 + i;
                    int length = bArr.length;
                    if (i4 <= length) {
                        int i5 = this.A02;
                        int i6 = this.A00;
                        if (i5 == i6) {
                            return -1;
                        }
                        if (i5 + i2 > i6) {
                            i3 = i6 - i5;
                        }
                        SuperpackFile superpackFile = this.A05;
                        synchronized (superpackFile) {
                            long j = superpackFile.mPtr;
                            if (j == 0) {
                                throw new IllegalStateException();
                            } else if (i5 < 0 || i3 < 0) {
                                throw new IndexOutOfBoundsException();
                            } else if (i + i3 > length) {
                                throw new IndexOutOfBoundsException();
                            } else if (i5 + i3 <= superpackFile.mLength) {
                                SuperpackFile.readBytesNative(j, i5, i3, bArr, i);
                            } else {
                                throw new IndexOutOfBoundsException();
                            }
                        }
                        this.A02 += i3;
                        return i3;
                    }
                }
                throw new IndexOutOfBoundsException();
            }
            throw new NullPointerException();
        }
    }
}
