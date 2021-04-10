package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.oculus.localmedia.MediaProviderUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* renamed from: X.1gR  reason: invalid class name */
public final class AnonymousClass1gR implements AbstractC08251eH {
    public static final byte[] A00 = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));
    public static final int[] A01 = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    @NonNull
    public static ImageHeaderParser$ImageType A00(AnonymousClass1gm r5) throws IOException {
        try {
            int A5A = r5.A5A();
            if (A5A == 65496) {
                return ImageHeaderParser$ImageType.JPEG;
            }
            int A5B = (A5A << 8) | r5.A5B();
            if (A5B == 4671814) {
                return ImageHeaderParser$ImageType.GIF;
            }
            int A5B2 = (A5B << 8) | r5.A5B();
            if (A5B2 == -1991225785) {
                r5.skip(21);
                try {
                    if (r5.A5B() >= 3) {
                        return ImageHeaderParser$ImageType.PNG_A;
                    }
                    return ImageHeaderParser$ImageType.PNG;
                } catch (AnonymousClass1hG unused) {
                    return ImageHeaderParser$ImageType.PNG;
                }
            } else if (A5B2 != 1380533830) {
                return ImageHeaderParser$ImageType.UNKNOWN;
            } else {
                r5.skip(4);
                if (((r5.A5A() << 16) | r5.A5A()) != 1464156752) {
                    return ImageHeaderParser$ImageType.UNKNOWN;
                }
                int A5A2 = (r5.A5A() << 16) | r5.A5A();
                if ((A5A2 & -256) != 1448097792) {
                    return ImageHeaderParser$ImageType.UNKNOWN;
                }
                int i = A5A2 & MediaProviderUtils.JPEG_HEADER;
                if (i == 88) {
                    r5.skip(4);
                    if ((r5.A5B() & 16) != 0) {
                        return ImageHeaderParser$ImageType.WEBP_A;
                    }
                    return ImageHeaderParser$ImageType.WEBP;
                } else if (i != 76) {
                    return ImageHeaderParser$ImageType.WEBP;
                } else {
                    r5.skip(4);
                    if ((r5.A5B() & 8) != 0) {
                        return ImageHeaderParser$ImageType.WEBP_A;
                    }
                    return ImageHeaderParser$ImageType.WEBP;
                }
            }
        } catch (AnonymousClass1hG unused2) {
            return ImageHeaderParser$ImageType.UNKNOWN;
        }
    }

    @Override // X.AbstractC08251eH
    public final int A4a(@NonNull InputStream inputStream, @NonNull AnonymousClass1hX r14) throws IOException {
        short A5B;
        short s;
        byte[] bArr;
        int length;
        ByteOrder byteOrder;
        int i;
        short A002;
        int i2;
        int i3;
        int i4;
        AnonymousClass1S2.A00(inputStream);
        C09091gc r6 = new C09091gc(inputStream);
        AnonymousClass1S2.A00(r14);
        try {
            int A5A = r6.A5A();
            if ((A5A & 65496) != 65496 && A5A != 19789 && A5A != 18761) {
                return -1;
            }
            while (r6.A5B() == 255 && (A5B = r6.A5B()) != 218 && A5B != 217) {
                int A5A2 = r6.A5A() - 2;
                if (A5B != 225) {
                    long j = (long) A5A2;
                    if (r6.skip(j) != j) {
                        return -1;
                    }
                } else if (A5A2 == -1) {
                    return -1;
                } else {
                    byte[] bArr2 = (byte[]) r14.A04(A5A2, byte[].class);
                    try {
                        if (r6.A8r(bArr2, A5A2) == A5A2 && bArr2 != null && A5A2 > (length = (bArr = A00).length)) {
                            int i5 = 0;
                            while (true) {
                                if (i5 < length) {
                                    if (bArr2[i5] != bArr[i5]) {
                                        break;
                                    }
                                    i5++;
                                } else {
                                    AnonymousClass1gg r9 = new AnonymousClass1gg(bArr2, A5A2);
                                    short A003 = r9.A00(6);
                                    if (A003 == 18761) {
                                        byteOrder = ByteOrder.LITTLE_ENDIAN;
                                    } else if (A003 != 19789) {
                                        byteOrder = ByteOrder.BIG_ENDIAN;
                                    } else {
                                        byteOrder = ByteOrder.BIG_ENDIAN;
                                    }
                                    ByteBuffer byteBuffer = r9.A00;
                                    byteBuffer.order(byteOrder);
                                    if (byteBuffer.remaining() - 10 >= 4) {
                                        i = byteBuffer.getInt(10);
                                    } else {
                                        i = -1;
                                    }
                                    int i6 = i + 6;
                                    short A004 = r9.A00(i6);
                                    int i7 = 0;
                                    while (true) {
                                        if (i7 >= A004) {
                                            break;
                                        }
                                        int i8 = i6 + 2 + (i7 * 12);
                                        if (r9.A00(i8) == 274 && (A002 = r9.A00(i8 + 2)) >= 1 && A002 <= 12) {
                                            int i9 = i8 + 4;
                                            if (byteBuffer.remaining() - i9 >= 4 && (i2 = byteBuffer.getInt(i9)) >= 0 && (i3 = i2 + A01[A002]) <= 4 && (i4 = i8 + 8) >= 0 && i4 <= byteBuffer.remaining() && i3 >= 0 && i3 + i4 <= byteBuffer.remaining()) {
                                                s = r9.A00(i4);
                                                break;
                                            }
                                        }
                                        i7++;
                                    }
                                }
                            }
                        }
                        s = -1;
                        return s;
                    } finally {
                        r14.A05(bArr2);
                    }
                }
            }
            return -1;
        } catch (AnonymousClass1hG unused) {
            return -1;
        }
    }

    @Override // X.AbstractC08251eH
    @NonNull
    public final ImageHeaderParser$ImageType A57(@NonNull InputStream inputStream) throws IOException {
        AnonymousClass1S2.A00(inputStream);
        return A00(new C09091gc(inputStream));
    }

    @Override // X.AbstractC08251eH
    @NonNull
    public final ImageHeaderParser$ImageType A58(@NonNull ByteBuffer byteBuffer) throws IOException {
        AnonymousClass1S2.A00(byteBuffer);
        return A00(new AnonymousClass1gS(byteBuffer));
    }
}
