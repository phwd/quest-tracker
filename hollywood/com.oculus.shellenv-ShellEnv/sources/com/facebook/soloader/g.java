package com.facebook.soloader;

import android.os.Trace;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileChannel;

public class g implements com.facebook.soloader.a.b {

    /* access modifiers changed from: package-private */
    public static class a extends RuntimeException {
        a(String str) {
            super(str);
        }
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    public static final class b extends Enum {
        public static final b a = new b("X86", 1, "x86");
        public static final b b = new b("ARM", 2, "armeabi-v7a");
        public static final b c = new b("X86_64", 3, "x86_64");
        public static final b d = new b("AARCH64", 4, "arm64-v8a");
        private static b e = new b("NOT_SO", 0, "not_so");
        private static b f = new b("OTHERS", 5, "others");
        private final String g;

        static {
            b[] bVarArr = {e, a, b, c, d, f};
        }

        private b(String str, int i, String str2) {
            this.g = str2;
        }

        public final String toString() {
            return this.g;
        }
    }

    private static String a(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short e = e(fileChannel, byteBuffer, j);
            if (e == 0) {
                return sb.toString();
            }
            sb.append((char) e);
            j = j2;
        }
    }

    public static void a(String str, String str2, String str3) {
        String str4 = str + str2 + str3;
        if (str4.length() > 127 && str2 != null) {
            int length = (127 - str.length()) - str3.length();
            str4 = str + str2.substring(0, length) + str3;
        }
        Trace.beginSection(str4);
    }

    private static void a(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) {
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
        throw new a("ELF file truncated");
    }

    public static String[] a(File file) {
        int i = 0;
        while (true) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return a(fileInputStream.getChannel());
            } catch (ClosedByInterruptException e) {
                i++;
                if (i <= 3) {
                    Thread.interrupted();
                    Log.e("MinElf", "retrying extract_DT_NEEDED due to ClosedByInterruptException", e);
                } else {
                    throw e;
                }
            } finally {
                fileInputStream.close();
            }
        }
    }

    private static String[] a(FileChannel fileChannel) {
        long j;
        long j2;
        long j3;
        long j4;
        long c;
        long j5;
        long j6;
        long j7;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        long c2 = c(fileChannel, allocate, 0);
        if (c2 == 1179403647) {
            boolean z = true;
            if (e(fileChannel, allocate, 4) != 1) {
                z = false;
            }
            if (e(fileChannel, allocate, 5) == 2) {
                allocate.order(ByteOrder.BIG_ENDIAN);
            }
            long c3 = z ? c(fileChannel, allocate, 28) : b(fileChannel, allocate, 32);
            long d = z ? (long) d(fileChannel, allocate, 44) : (long) d(fileChannel, allocate, 56);
            int d2 = d(fileChannel, allocate, z ? 42 : 54);
            if (d == 65535) {
                long c4 = z ? c(fileChannel, allocate, 32) : b(fileChannel, allocate, 40);
                d = c(fileChannel, allocate, z ? c4 + 28 : c4 + 44);
            }
            long j8 = c3;
            long j9 = 0;
            while (true) {
                j = 1;
                if (j9 >= d) {
                    j2 = 0;
                    break;
                } else if (c(fileChannel, allocate, j8) == 2) {
                    j2 = z ? c(fileChannel, allocate, j8 + 4) : b(fileChannel, allocate, j8 + 8);
                } else {
                    j8 += (long) d2;
                    j9++;
                }
            }
            if (j2 != 0) {
                long j10 = j2;
                int i = 0;
                long j11 = 0;
                while (true) {
                    long c5 = z ? c(fileChannel, allocate, j10) : b(fileChannel, allocate, j10);
                    if (c5 == j) {
                        if (i != Integer.MAX_VALUE) {
                            i++;
                        } else {
                            throw new a("malformed DT_NEEDED section");
                        }
                    } else if (c5 == 5) {
                        j11 = z ? c(fileChannel, allocate, j10 + 4) : b(fileChannel, allocate, j10 + 8);
                    }
                    j10 += z ? 8 : 16;
                    if (c5 != 0) {
                        j = 1;
                    } else if (j11 != 0) {
                        long j12 = c3;
                        int i2 = 0;
                        while (true) {
                            if (((long) i2) >= d) {
                                j3 = j2;
                                j4 = 0;
                                break;
                            }
                            if (c(fileChannel, allocate, j12) == 1) {
                                if (z) {
                                    j5 = d;
                                    j6 = c(fileChannel, allocate, j12 + 8);
                                } else {
                                    j5 = d;
                                    j6 = b(fileChannel, allocate, j12 + 16);
                                }
                                if (z) {
                                    j3 = j2;
                                    j7 = c(fileChannel, allocate, j12 + 20);
                                } else {
                                    j3 = j2;
                                    j7 = b(fileChannel, allocate, j12 + 40);
                                }
                                if (j6 <= j11 && j11 < j7 + j6) {
                                    j4 = (z ? c(fileChannel, allocate, j12 + 4) : b(fileChannel, allocate, j12 + 8)) + (j11 - j6);
                                }
                            } else {
                                j5 = d;
                                j3 = j2;
                            }
                            j12 += (long) d2;
                            i2++;
                            j2 = j3;
                            d = j5;
                        }
                        if (j4 != 0) {
                            String[] strArr = new String[i];
                            long j13 = j3;
                            int i3 = 0;
                            do {
                                c = z ? c(fileChannel, allocate, j13) : b(fileChannel, allocate, j13);
                                if (c == 1) {
                                    strArr[i3] = a(fileChannel, allocate, (z ? c(fileChannel, allocate, j13 + 4) : b(fileChannel, allocate, j13 + 8)) + j4);
                                    if (i3 != Integer.MAX_VALUE) {
                                        i3++;
                                    } else {
                                        throw new a("malformed DT_NEEDED section");
                                    }
                                }
                                j13 += z ? 8 : 16;
                            } while (c != 0);
                            if (i3 == strArr.length) {
                                return strArr;
                            }
                            throw new a("malformed DT_NEEDED section");
                        }
                        throw new a("did not find file offset of DT_STRTAB table");
                    } else {
                        throw new a("Dynamic section string-table not found");
                    }
                }
            } else {
                throw new a("ELF file does not contain dynamic linking information");
            }
        } else {
            throw new a("file is not ELF: 0x" + Long.toHexString(c2));
        }
    }

    private static long b(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static long c(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 4, j);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    private static int d(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & 65535;
    }

    private static short e(FileChannel fileChannel, ByteBuffer byteBuffer, long j) {
        a(fileChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    @Override // com.facebook.soloader.a.b
    public boolean a(String str, int i) {
        return i.a(str, ((i & 1) != 0 ? 16 : 0) | 0);
    }
}
