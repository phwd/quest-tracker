package com.facebook.soloader;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedByInterruptException;

public final class MinElf {

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

        public final String toString() {
            return this.value;
        }
    }

    static String[] extract_DT_NEEDED_with_retries(ElfFileChannel elfFileChannel) throws IOException {
        int i = 0;
        while (true) {
            try {
                return extract_DT_NEEDED_no_retries(elfFileChannel);
            } catch (ClosedByInterruptException e) {
                i++;
                if (i <= 4) {
                    Thread.interrupted();
                    Log.e("MinElf", "retrying extract_DT_NEEDED due to ClosedByInterruptException", e);
                    elfFileChannel.openChannel();
                } else {
                    throw e;
                }
            }
        }
    }

    static String[] extract_DT_NEEDED_no_retries(ElfByteChannel elfByteChannel) throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        long j10 = getu32(elfByteChannel, allocate, 0);
        if (j10 == 1179403647) {
            boolean z = true;
            if (getu8(elfByteChannel, allocate, 4) != 1) {
                z = false;
            }
            if (getu8(elfByteChannel, allocate, 5) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            long r15 = z ? getu32(elfByteChannel, allocate, 28) : get64(elfByteChannel, allocate, 32);
            long j11 = z ? (long) getu16(elfByteChannel, allocate, 44) : (long) getu16(elfByteChannel, allocate, 56);
            int i = getu16(elfByteChannel, allocate, z ? 42 : 54);
            if (j11 == 65535) {
                long r3 = z ? getu32(elfByteChannel, allocate, 32) : get64(elfByteChannel, allocate, 40);
                if (z) {
                    j9 = getu32(elfByteChannel, allocate, r3 + 28);
                } else {
                    j9 = getu32(elfByteChannel, allocate, r3 + 44);
                }
                j11 = j9;
            }
            long j12 = r15;
            long j13 = 0;
            while (true) {
                if (j13 >= j11) {
                    j = 0;
                    break;
                } else if (getu32(elfByteChannel, allocate, j12 + 0) != 2) {
                    j12 += (long) i;
                    j13++;
                } else if (z) {
                    j = getu32(elfByteChannel, allocate, j12 + 4);
                } else {
                    j = get64(elfByteChannel, allocate, j12 + 8);
                }
            }
            long j14 = 0;
            if (j != 0) {
                long j15 = j;
                long j16 = 0;
                int i2 = 0;
                while (true) {
                    long r7 = z ? getu32(elfByteChannel, allocate, j15 + j14) : get64(elfByteChannel, allocate, j15 + j14);
                    if (r7 == 1) {
                        j2 = j;
                        if (i2 != Integer.MAX_VALUE) {
                            i2++;
                        } else {
                            throw new ElfError("malformed DT_NEEDED section");
                        }
                    } else {
                        j2 = j;
                        if (r7 == 5) {
                            if (z) {
                                j8 = getu32(elfByteChannel, allocate, j15 + 4);
                            } else {
                                j8 = get64(elfByteChannel, allocate, j15 + 8);
                            }
                            j16 = j8;
                        }
                    }
                    long j17 = 16;
                    j15 += z ? 8 : 16;
                    j14 = 0;
                    if (r7 != 0) {
                        z = z;
                        j = j2;
                    } else if (j16 != 0) {
                        int i3 = 0;
                        while (true) {
                            if (((long) i3) >= j11) {
                                j3 = 0;
                                break;
                            }
                            if (getu32(elfByteChannel, allocate, r15 + j14) == 1) {
                                if (z) {
                                    j5 = getu32(elfByteChannel, allocate, r15 + 8);
                                } else {
                                    j5 = get64(elfByteChannel, allocate, r15 + j17);
                                }
                                if (z) {
                                    j4 = j11;
                                    j6 = getu32(elfByteChannel, allocate, r15 + 20);
                                } else {
                                    j4 = j11;
                                    j6 = get64(elfByteChannel, allocate, r15 + 40);
                                }
                                if (j5 <= j16 && j16 < j6 + j5) {
                                    if (z) {
                                        j7 = getu32(elfByteChannel, allocate, r15 + 4);
                                    } else {
                                        j7 = get64(elfByteChannel, allocate, r15 + 8);
                                    }
                                    j3 = j7 + (j16 - j5);
                                }
                            } else {
                                j4 = j11;
                            }
                            r15 += (long) i;
                            i3++;
                            j11 = j4;
                            j17 = 16;
                            j14 = 0;
                        }
                        long j18 = 0;
                        if (j3 != 0) {
                            String[] strArr = new String[i2];
                            int i4 = 0;
                            while (true) {
                                long j19 = j2 + j18;
                                long r9 = z ? getu32(elfByteChannel, allocate, j19) : get64(elfByteChannel, allocate, j19);
                                if (r9 == 1) {
                                    strArr[i4] = getSz(elfByteChannel, allocate, (z ? getu32(elfByteChannel, allocate, j2 + 4) : get64(elfByteChannel, allocate, j2 + 8)) + j3);
                                    if (i4 != Integer.MAX_VALUE) {
                                        i4++;
                                    } else {
                                        throw new ElfError("malformed DT_NEEDED section");
                                    }
                                }
                                j2 += z ? 8 : 16;
                                if (r9 != 0) {
                                    j18 = 0;
                                } else if (i4 == strArr.length) {
                                    return strArr;
                                } else {
                                    throw new ElfError("malformed DT_NEEDED section");
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
            throw new ElfError("file is not ELF: 0x" + Long.toHexString(j10));
        }
    }

    private static String getSz(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short u8Var = getu8(elfByteChannel, byteBuffer, j);
            if (u8Var == 0) {
                return sb.toString();
            }
            sb.append((char) u8Var);
            j = j2;
        }
    }

    private static void read(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = elfByteChannel.read(byteBuffer, j)) != -1) {
            j += (long) read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    private static long get64(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long getu32(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private static int getu16(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & 65535;
    }

    private static short getu8(ElfByteChannel elfByteChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(elfByteChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    /* access modifiers changed from: package-private */
    public static class ElfError extends RuntimeException {
        ElfError(String str) {
            super(str);
        }
    }
}
