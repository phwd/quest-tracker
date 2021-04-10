package com.facebook.soloader;

import com.google.common.primitives.UnsignedBytes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;

public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;

    public enum ISA {
        NOT_SO("not_so"),
        X86("x86"),
        ARM("armeabi-v7a"),
        X86_64("x86_64"),
        AARCH64("arm64-v8a"),
        OTHERS("others");
        
        private final String value;

        private ISA(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    public static String[] extract_DT_NEEDED(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return extract_DT_NEEDED(fileInputStream.getChannel());
        } finally {
            fileInputStream.close();
        }
    }

    public static String[] extract_DT_NEEDED(FileChannel fileChannel) throws IOException {
        long j;
        boolean z;
        long j2;
        long j3;
        String str;
        long j4;
        String str2;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (getu32(fileChannel, allocate, 0) == 1179403647) {
            boolean z2 = true;
            if (getu8(fileChannel, allocate, 4) != 1) {
                z2 = false;
            }
            if (getu8(fileChannel, allocate, 5) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            long r15 = z2 ? getu32(fileChannel, allocate, 28) : get64(fileChannel, allocate, 32);
            long j12 = z2 ? (long) getu16(fileChannel, allocate, 44) : (long) getu16(fileChannel, allocate, 56);
            int i = getu16(fileChannel, allocate, z2 ? 42 : 54);
            if (j12 == 65535) {
                long r3 = z2 ? getu32(fileChannel, allocate, 32) : get64(fileChannel, allocate, 40);
                if (z2) {
                    j11 = getu32(fileChannel, allocate, r3 + 28);
                } else {
                    j11 = getu32(fileChannel, allocate, r3 + 44);
                }
                j12 = j11;
            }
            long j13 = r15;
            long j14 = 0;
            while (true) {
                if (j14 >= j12) {
                    j = 0;
                    break;
                }
                if (z2) {
                    j10 = getu32(fileChannel, allocate, j13 + 0);
                } else {
                    j10 = getu32(fileChannel, allocate, j13 + 0);
                }
                if (j10 != 2) {
                    j13 += (long) i;
                    j14++;
                } else if (z2) {
                    j = getu32(fileChannel, allocate, j13 + 4);
                } else {
                    j = get64(fileChannel, allocate, j13 + 8);
                }
            }
            long j15 = 0;
            if (j != 0) {
                long j16 = j;
                long j17 = 0;
                int i2 = 0;
                while (true) {
                    if (z2) {
                        z = z2;
                        j2 = getu32(fileChannel, allocate, j16 + j15);
                    } else {
                        z = z2;
                        j2 = get64(fileChannel, allocate, j16 + j15);
                    }
                    long j18 = j;
                    String str3 = "malformed DT_NEEDED section";
                    if (j2 == 1) {
                        if (i2 != Integer.MAX_VALUE) {
                            i2++;
                            j3 = j2;
                        } else {
                            throw new ElfError(str3);
                        }
                    } else if (j2 == 5) {
                        if (z) {
                            j3 = j2;
                            j9 = getu32(fileChannel, allocate, j16 + 4);
                        } else {
                            j3 = j2;
                            j9 = get64(fileChannel, allocate, j16 + 8);
                        }
                        j17 = j9;
                    } else {
                        j3 = j2;
                    }
                    long j19 = 16;
                    j16 += z ? 8 : 16;
                    j15 = 0;
                    if (j3 != 0) {
                        z2 = z;
                        j = j18;
                    } else if (j17 != 0) {
                        int i3 = 0;
                        while (true) {
                            if (((long) i3) >= j12) {
                                str = str3;
                                j4 = 0;
                                break;
                            }
                            if (z) {
                                j5 = getu32(fileChannel, allocate, r15 + j15);
                            } else {
                                j5 = getu32(fileChannel, allocate, r15 + j15);
                            }
                            if (j5 == 1) {
                                if (z) {
                                    j6 = getu32(fileChannel, allocate, r15 + 8);
                                } else {
                                    j6 = get64(fileChannel, allocate, r15 + j19);
                                }
                                if (z) {
                                    str = str3;
                                    j7 = getu32(fileChannel, allocate, r15 + 20);
                                } else {
                                    str = str3;
                                    j7 = get64(fileChannel, allocate, r15 + 40);
                                }
                                if (j6 <= j17 && j17 < j7 + j6) {
                                    if (z) {
                                        j8 = getu32(fileChannel, allocate, r15 + 4);
                                    } else {
                                        j8 = get64(fileChannel, allocate, r15 + 8);
                                    }
                                    j4 = j8 + (j17 - j6);
                                }
                            } else {
                                str = str3;
                            }
                            r15 += (long) i;
                            i3++;
                            str3 = str;
                            j19 = 16;
                            j15 = 0;
                        }
                        long j20 = 0;
                        if (j4 != 0) {
                            String[] strArr = new String[i2];
                            int i4 = 0;
                            while (true) {
                                long r8 = z ? getu32(fileChannel, allocate, j18 + j20) : get64(fileChannel, allocate, j18 + j20);
                                if (r8 == 1) {
                                    strArr[i4] = getSz(fileChannel, allocate, (z ? getu32(fileChannel, allocate, j18 + 4) : get64(fileChannel, allocate, j18 + 8)) + j4);
                                    if (i4 != Integer.MAX_VALUE) {
                                        i4++;
                                        str2 = str;
                                    } else {
                                        throw new ElfError(str);
                                    }
                                } else {
                                    str2 = str;
                                }
                                j18 += z ? 8 : 16;
                                if (r8 != 0) {
                                    str = str2;
                                    j20 = 0;
                                } else if (i4 == strArr.length) {
                                    return strArr;
                                } else {
                                    throw new ElfError(str2);
                                }
                            }
                        } else {
                            throw new ElfError("did not find file offset of DT_STRTAB table");
                        }
                    } else {
                        throw new ElfError("Dynamic section string-table not found");
                    }
                }
            } else {
                throw new ElfError("ELF file does not contain dynamic linking information");
            }
        } else {
            throw new ElfError("file is not ELF");
        }
    }

    private static String getSz(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short u8Var = getu8(fileChannel, byteBuffer, j);
            if (u8Var == 0) {
                return sb.toString();
            }
            sb.append((char) u8Var);
            j = j2;
        }
    }

    private static void read(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = fileChannel.read(byteBuffer, j)) != -1) {
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    private static long get64(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long getu32(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private static int getu16(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & 65535;
    }

    private static short getu8(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & UnsignedBytes.MAX_VALUE);
    }

    /* access modifiers changed from: private */
    public static class ElfError extends RuntimeException {
        ElfError(String str) {
            super(str);
        }
    }
}
