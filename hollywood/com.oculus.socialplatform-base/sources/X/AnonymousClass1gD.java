package X;

import android.graphics.Bitmap;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.adobe.xmp.impl.Base64;
import com.oculus.localmedia.MediaProviderUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* renamed from: X.1gD  reason: invalid class name */
public final class AnonymousClass1gD {
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    @NonNull
    public Bitmap.Config A05 = Bitmap.Config.ARGB_8888;
    public Bitmap A06;
    public AnonymousClass1gT A07;
    @Nullable
    public Boolean A08;
    public ByteBuffer A09;
    public boolean A0A;
    public byte[] A0B;
    public byte[] A0C;
    public byte[] A0D;
    public byte[] A0E;
    @ColorInt
    public int[] A0F;
    @ColorInt
    public int[] A0G;
    public short[] A0H;
    public final AnonymousClass1hA A0I;
    @ColorInt
    public final int[] A0J = new int[256];

    private Bitmap A00() {
        Bitmap.Config config;
        Boolean bool = this.A08;
        if (bool == null || bool.booleanValue()) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = this.A05;
        }
        AnonymousClass1hA r0 = this.A0I;
        Bitmap A3n = r0.A00.A3n(this.A01, this.A00, config);
        A3n.setHasAlpha(true);
        return A3n;
    }

    @Nullable
    public final synchronized Bitmap A01() {
        int i;
        boolean z;
        int i2;
        int i3;
        boolean z2;
        byte[] bArr;
        int i4;
        Bitmap bitmap;
        byte[] bArr2;
        if (this.A07.A02 <= 0 || this.A02 < 0) {
            this.A04 = 1;
        }
        int i5 = this.A04;
        if (!(i5 == 1 || i5 == 2)) {
            this.A04 = 0;
            if (this.A0B == null) {
                AnonymousClass1hX r4 = this.A0I.A01;
                if (r4 == null) {
                    bArr2 = new byte[MediaProviderUtils.JPEG_HEADER];
                } else {
                    bArr2 = (byte[]) r4.A04(MediaProviderUtils.JPEG_HEADER, byte[].class);
                }
                this.A0B = bArr2;
            }
            C09101gd r0 = this.A07.A0A.get(this.A02);
            int i6 = this.A02 - 1;
            C09101gd r42 = i6 >= 0 ? this.A07.A0A.get(i6) : null;
            int[] iArr = r0.A0A;
            if (iArr == null) {
                iArr = this.A07.A09;
            }
            this.A0F = iArr;
            if (iArr == null) {
                this.A04 = 1;
            } else {
                if (r0.A09) {
                    int[] iArr2 = this.A0J;
                    System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                    this.A0F = iArr2;
                    iArr2[r0.A07] = 0;
                    if (r0.A02 == 2 && this.A02 == 0) {
                        this.A08 = true;
                    }
                }
                int[] iArr3 = this.A0G;
                int i7 = 0;
                if (r42 == null) {
                    Bitmap bitmap2 = this.A06;
                    if (bitmap2 != null) {
                        this.A0I.A00.A8l(bitmap2);
                    }
                    this.A06 = null;
                    Arrays.fill(iArr3, 0);
                }
                if (r42 != null && r42.A02 == 3 && this.A06 == null) {
                    Arrays.fill(iArr3, 0);
                }
                if (r42 != null && (i4 = r42.A02) > 0) {
                    if (i4 == 2) {
                        if (!r0.A09) {
                            AnonymousClass1gT r5 = this.A07;
                            int i8 = r5.A00;
                            if (r0.A0A == null || r5.A01 != r0.A07) {
                                i7 = i8;
                            }
                        }
                        int i9 = r42.A03;
                        int i10 = this.A03;
                        int i11 = i9 / i10;
                        int i12 = r42.A06 / i10;
                        int i13 = r42.A04 / i10;
                        int i14 = r42.A05 / i10;
                        int i15 = this.A01;
                        int i16 = (i12 * i15) + i14;
                        int i17 = (i11 * i15) + i16;
                        while (i16 < i17) {
                            int i18 = i16 + i13;
                            for (int i19 = i16; i19 < i18; i19++) {
                                iArr3[i19] = i7;
                            }
                            i16 += i15;
                        }
                    } else if (i4 == 3 && (bitmap = this.A06) != null) {
                        int i20 = this.A01;
                        bitmap.getPixels(iArr3, 0, i20, 0, 0, i20, this.A00);
                    }
                }
                this.A09.position(r0.A00);
                int i21 = r0.A03 * r0.A04;
                byte[] bArr3 = this.A0C;
                byte[] bArr4 = bArr3;
                if (bArr3 == null || bArr3.length < i21) {
                    AnonymousClass1hX r52 = this.A0I.A01;
                    if (r52 == null) {
                        bArr = new byte[i21];
                        bArr4 = bArr;
                    } else {
                        bArr = (byte[]) r52.A04(i21, byte[].class);
                        bArr4 = bArr;
                    }
                    this.A0C = bArr;
                }
                short[] sArr = this.A0H;
                if (sArr == null) {
                    sArr = new short[4096];
                    this.A0H = sArr;
                }
                byte[] bArr5 = this.A0E;
                if (bArr5 == null) {
                    bArr5 = new byte[4096];
                    this.A0E = bArr5;
                }
                byte[] bArr6 = this.A0D;
                if (bArr6 == null) {
                    bArr6 = new byte[4097];
                    this.A0D = bArr6;
                }
                int i22 = this.A09.get() & Base64.INVALID;
                int i23 = 1 << i22;
                int i24 = i23 + 1;
                int i25 = i23 + 2;
                int i26 = i22 + 1;
                int i27 = (1 << i26) - 1;
                int i28 = 0;
                for (int i29 = 0; i29 < i23; i29++) {
                    sArr[i29] = 0;
                    bArr5[i29] = (byte) i29;
                }
                byte[] bArr7 = this.A0B;
                int i30 = i26;
                int i31 = i25;
                int i32 = i27;
                int i33 = 0;
                int i34 = 0;
                int i35 = 0;
                int i36 = 0;
                int i37 = 0;
                short s = -1;
                int i38 = 0;
                int i39 = 0;
                while (true) {
                    if (i28 >= i21) {
                        break;
                    }
                    if (i33 == 0) {
                        i33 = this.A09.get() & Base64.INVALID;
                        if (i33 > 0) {
                            ByteBuffer byteBuffer = this.A09;
                            byteBuffer.get(this.A0B, 0, Math.min(i33, byteBuffer.remaining()));
                        }
                        if (i33 <= 0) {
                            this.A04 = 3;
                            break;
                        }
                        i36 = 0;
                    }
                    i35 += (bArr7[i36] & Base64.INVALID) << i34;
                    i34 += 8;
                    i36++;
                    i33--;
                    while (i34 >= i30) {
                        int i40 = i35 & i32;
                        i35 >>= i30;
                        i34 -= i30;
                        if (i40 == i23) {
                            i30 = i26;
                            i31 = i25;
                            i32 = i27;
                            s = -1;
                        } else if (i40 == i24) {
                            break;
                        } else if (s == -1) {
                            bArr4[i37] = bArr5[i40];
                            i37++;
                            i28++;
                            s = i40;
                            i38 = i40;
                        } else {
                            short s2 = i40;
                            if (i40 >= i31) {
                                bArr6[i39] = (byte) i38;
                                i39++;
                                s2 = s;
                            }
                            while (s2 >= i23) {
                                bArr6[i39] = bArr5[s2];
                                i39++;
                                s2 = sArr[s2];
                            }
                            i38 = bArr5[s2] & Base64.INVALID;
                            byte b = (byte) i38;
                            bArr4[i37] = b;
                            while (true) {
                                i37++;
                                i28++;
                                if (i39 <= 0) {
                                    break;
                                }
                                i39--;
                                bArr4[i37] = bArr6[i39];
                            }
                            if (i31 < 4096) {
                                sArr[i31] = (short) s;
                                bArr5[i31] = b;
                                i31++;
                                if ((i31 & i32) == 0 && i31 < 4096) {
                                    i30++;
                                    i32 += i31;
                                }
                            }
                            s = i40;
                        }
                    }
                }
                Arrays.fill(bArr4, i37, i21, (byte) 0);
                boolean z3 = r0.A08;
                if (z3 || this.A03 != 1) {
                    int[] iArr4 = this.A0G;
                    int i41 = r0.A03;
                    int i42 = this.A03;
                    int i43 = i41 / i42;
                    int i44 = r0.A06 / i42;
                    int i45 = r0.A04;
                    int i46 = i45 / i42;
                    int i47 = r0.A05 / i42;
                    boolean z4 = false;
                    if (this.A02 == 0) {
                        z4 = true;
                    }
                    int i48 = this.A01;
                    int i49 = this.A00;
                    byte[] bArr8 = this.A0C;
                    int[] iArr5 = this.A0F;
                    Boolean bool = this.A08;
                    Boolean bool2 = bool;
                    int i50 = 0;
                    int i51 = 0;
                    int i52 = 1;
                    int i53 = 8;
                    while (i50 < i43) {
                        if (z3) {
                            if (i51 >= i43) {
                                i52++;
                                if (i52 == 2) {
                                    i51 = 4;
                                } else if (i52 == 3) {
                                    i51 = 2;
                                    i53 = 4;
                                } else if (i52 == 4) {
                                    i51 = 1;
                                    i53 = 2;
                                }
                            }
                            i2 = i51 + i53;
                        } else {
                            i2 = i51;
                            i51 = i50;
                        }
                        int i54 = i51 + i44;
                        boolean z5 = false;
                        if (i42 == 1) {
                            z5 = true;
                        }
                        if (i54 < i49) {
                            int i55 = i54 * i48;
                            int i56 = i55 + i47;
                            int i57 = i56 + i46;
                            int i58 = i55 + i48;
                            if (i58 < i57) {
                                i57 = i58;
                            }
                            int i59 = i50 * i42 * i45;
                            if (!z5) {
                                int i60 = ((i57 - i56) * i42) + i59;
                                while (i56 < i57) {
                                    int i61 = i59;
                                    int i62 = 0;
                                    int i63 = 0;
                                    int i64 = 0;
                                    int i65 = 0;
                                    int i66 = 0;
                                    while (i61 < i42 + i59 && i61 < bArr8.length && i61 < i60) {
                                        int i67 = iArr5[bArr8[i61] & Base64.INVALID];
                                        if (i67 != 0) {
                                            i62 += (i67 >> 24) & MediaProviderUtils.JPEG_HEADER;
                                            i63 += (i67 >> 16) & MediaProviderUtils.JPEG_HEADER;
                                            i64 += (i67 >> 8) & MediaProviderUtils.JPEG_HEADER;
                                            i65 += i67 & MediaProviderUtils.JPEG_HEADER;
                                            i66++;
                                        }
                                        i61++;
                                    }
                                    int i68 = i59 + i45;
                                    int i69 = i68;
                                    while (i69 < i42 + i68 && i69 < bArr8.length && i69 < i60) {
                                        int i70 = iArr5[bArr8[i69] & Base64.INVALID];
                                        if (i70 != 0) {
                                            i62 += (i70 >> 24) & MediaProviderUtils.JPEG_HEADER;
                                            i63 += (i70 >> 16) & MediaProviderUtils.JPEG_HEADER;
                                            i64 += (i70 >> 8) & MediaProviderUtils.JPEG_HEADER;
                                            i65 += i70 & MediaProviderUtils.JPEG_HEADER;
                                            i66++;
                                        }
                                        i69++;
                                    }
                                    if (i66 == 0) {
                                        i3 = 0;
                                    } else {
                                        i3 = ((i62 / i66) << 24) | ((i63 / i66) << 16) | ((i64 / i66) << 8) | (i65 / i66);
                                    }
                                    if (i3 != 0) {
                                        iArr4[i56] = i3;
                                    } else if (z4 && bool2 == null) {
                                        bool2 = true;
                                    }
                                    i59 += i42;
                                    i56++;
                                }
                            } else {
                                while (i56 < i57) {
                                    int i71 = iArr5[bArr8[i59] & Base64.INVALID];
                                    if (i71 != 0) {
                                        iArr4[i56] = i71;
                                    } else if (z4 && bool2 == null) {
                                        bool2 = true;
                                    }
                                    i59 += i42;
                                    i56++;
                                }
                            }
                        }
                        i50++;
                        i51 = i2;
                    }
                    if (bool == null) {
                        if (bool2 == null) {
                            z = false;
                        } else {
                            z = bool2.booleanValue();
                        }
                        this.A08 = Boolean.valueOf(z);
                    }
                } else {
                    int[] iArr6 = this.A0G;
                    int i72 = r0.A03;
                    int i73 = r0.A06;
                    int i74 = r0.A04;
                    int i75 = r0.A05;
                    boolean z6 = false;
                    if (this.A02 == 0) {
                        z6 = true;
                    }
                    int i76 = this.A01;
                    byte[] bArr9 = this.A0C;
                    int[] iArr7 = this.A0F;
                    byte b2 = -1;
                    for (int i77 = 0; i77 < i72; i77++) {
                        int i78 = (i77 + i73) * i76;
                        int i79 = i78 + i75;
                        int i80 = i79 + i74;
                        int i81 = i78 + i76;
                        if (i81 < i80) {
                            i80 = i81;
                        }
                        int i82 = i74 * i77;
                        while (i79 < i80) {
                            byte b3 = bArr9[i82];
                            int i83 = b3 & Base64.INVALID;
                            if (i83 != b2) {
                                int i84 = iArr7[i83];
                                if (i84 != 0) {
                                    iArr6[i79] = i84;
                                } else {
                                    b2 = b3;
                                }
                            }
                            i82++;
                            i79++;
                        }
                    }
                    Boolean bool3 = this.A08;
                    if ((bool3 == null || !bool3.booleanValue()) && (bool3 != null || !z6 || b2 == -1)) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    this.A08 = Boolean.valueOf(z2);
                }
                if (this.A0A && ((i = r0.A02) == 0 || i == 1)) {
                    Bitmap bitmap3 = this.A06;
                    if (bitmap3 == null) {
                        bitmap3 = A00();
                        this.A06 = bitmap3;
                    }
                    int i85 = this.A01;
                    bitmap3.setPixels(iArr3, 0, i85, 0, 0, i85, this.A00);
                }
                Bitmap A002 = A00();
                int i86 = this.A01;
                A002.setPixels(iArr3, 0, i86, 0, 0, i86, this.A00);
                return A002;
            }
        }
        return null;
    }

    public AnonymousClass1gD(@NonNull AnonymousClass1hA r6, AnonymousClass1gT r7, ByteBuffer byteBuffer, int i) {
        byte[] bArr;
        int[] iArr;
        this.A0I = r6;
        this.A07 = new AnonymousClass1gT();
        synchronized (this) {
            if (i > 0) {
                int highestOneBit = Integer.highestOneBit(i);
                this.A04 = 0;
                this.A07 = r7;
                this.A02 = -1;
                ByteBuffer asReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
                this.A09 = asReadOnlyBuffer;
                asReadOnlyBuffer.position(0);
                this.A09.order(ByteOrder.LITTLE_ENDIAN);
                this.A0A = false;
                Iterator<C09101gd> it = r7.A0A.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().A02 == 3) {
                            this.A0A = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                this.A03 = highestOneBit;
                int i2 = r7.A06;
                this.A01 = i2 / highestOneBit;
                int i3 = r7.A04;
                this.A00 = i3 / highestOneBit;
                AnonymousClass1hA r3 = this.A0I;
                int i4 = i2 * i3;
                AnonymousClass1hX r1 = r3.A01;
                if (r1 == null) {
                    bArr = new byte[i4];
                } else {
                    bArr = (byte[]) r1.A04(i4, byte[].class);
                }
                this.A0C = bArr;
                int i5 = this.A01 * this.A00;
                AnonymousClass1hX r12 = r3.A01;
                if (r12 == null) {
                    iArr = new int[i5];
                } else {
                    iArr = (int[]) r12.A04(i5, int[].class);
                }
                this.A0G = iArr;
            } else {
                throw new IllegalArgumentException(AnonymousClass006.A03("Sample size must be >=0, not: ", i));
            }
        }
    }
}
