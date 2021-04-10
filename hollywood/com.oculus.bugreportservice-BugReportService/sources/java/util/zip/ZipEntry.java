package java.util.zip;

import java.nio.file.attribute.FileTime;
import java.util.Objects;

public class ZipEntry implements ZipConstants, Cloneable {
    FileTime atime;
    String comment;
    long crc = -1;
    long csize = -1;
    FileTime ctime;
    long dataOffset;
    byte[] extra;
    int flag = 0;
    int method = -1;
    FileTime mtime;
    String name;
    long size = -1;
    long xdostime = -1;

    public ZipEntry(ZipEntry zipEntry) {
        Objects.requireNonNull(zipEntry, "entry");
        this.name = zipEntry.name;
        this.xdostime = zipEntry.xdostime;
        this.mtime = zipEntry.mtime;
        this.atime = zipEntry.atime;
        this.ctime = zipEntry.ctime;
        this.crc = zipEntry.crc;
        this.size = zipEntry.size;
        this.csize = zipEntry.csize;
        this.method = zipEntry.method;
        this.flag = zipEntry.flag;
        this.extra = zipEntry.extra;
        this.comment = zipEntry.comment;
        this.dataOffset = zipEntry.dataOffset;
    }

    ZipEntry() {
    }

    public String getName() {
        return this.name;
    }

    public long getTime() {
        FileTime fileTime = this.mtime;
        if (fileTime != null) {
            return fileTime.toMillis();
        }
        long j = this.xdostime;
        if (j != -1) {
            return ZipUtils.extendedDosToJavaTime(j);
        }
        return -1;
    }

    public long getSize() {
        return this.size;
    }

    /* access modifiers changed from: package-private */
    public void setExtra0(byte[] bArr, boolean z) {
        int i;
        if (bArr != null) {
            if (bArr.length <= 65535) {
                int i2 = 0;
                int length = bArr.length;
                while (true) {
                    int i3 = i2 + 4;
                    if (i3 >= length) {
                        break;
                    }
                    int r3 = ZipUtils.get16(bArr, i2);
                    int r0 = ZipUtils.get16(bArr, i2 + 2);
                    int i4 = i3 + r0;
                    if (i4 > length) {
                        break;
                    }
                    int i5 = 1;
                    if (r3 != 1) {
                        if (r3 != 10) {
                            if (r3 == 21589) {
                                int unsignedInt = Byte.toUnsignedInt(bArr[i3]);
                                if ((unsignedInt & 1) != 0 && 5 <= r0) {
                                    this.mtime = ZipUtils.unixTimeToFileTime(ZipUtils.get32(bArr, i3 + 1));
                                    i5 = 5;
                                }
                                if ((unsignedInt & 2) != 0 && (i = i5 + 4) <= r0) {
                                    this.atime = ZipUtils.unixTimeToFileTime(ZipUtils.get32(bArr, i5 + i3));
                                    i5 = i;
                                }
                                if ((unsignedInt & 4) != 0 && i5 + 4 <= r0) {
                                    this.ctime = ZipUtils.unixTimeToFileTime(ZipUtils.get32(bArr, i3 + i5));
                                }
                            }
                        } else if (r0 >= 32) {
                            int i6 = i3 + 4;
                            if (ZipUtils.get16(bArr, i6) == 1 && ZipUtils.get16(bArr, i6 + 2) == 24) {
                                this.mtime = ZipUtils.winTimeToFileTime(ZipUtils.get64(bArr, i6 + 4));
                                this.atime = ZipUtils.winTimeToFileTime(ZipUtils.get64(bArr, i6 + 12));
                                this.ctime = ZipUtils.winTimeToFileTime(ZipUtils.get64(bArr, i6 + 20));
                            }
                        }
                    } else if (z && r0 >= 16) {
                        this.size = ZipUtils.get64(bArr, i3);
                        this.csize = ZipUtils.get64(bArr, i3 + 8);
                    }
                    i2 = i4;
                }
            } else {
                throw new IllegalArgumentException("invalid extra field length");
            }
        }
        this.extra = bArr;
    }

    public boolean isDirectory() {
        return this.name.endsWith("/");
    }

    public String toString() {
        return getName();
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public Object clone() {
        try {
            ZipEntry zipEntry = (ZipEntry) super.clone();
            zipEntry.extra = this.extra == null ? null : (byte[]) this.extra.clone();
            return zipEntry;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
