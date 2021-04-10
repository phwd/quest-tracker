package X;

import android.content.res.AssetManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.localmedia.MediaProviderUtils;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

/* renamed from: X.1hO  reason: invalid class name */
public final class AnonymousClass1hO {
    public static SimpleDateFormat A0D;
    public static final byte[] A0E = {65, 83, 67, 73, 73, 0, 0, 0};
    public static final String[] A0F = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    public static final AnonymousClass1hT A0G = new AnonymousClass1hT("StripOffsets", 273, 3);
    public static final Charset A0H;
    public static final boolean A0I = Log.isLoggable("ExifInterface", 3);
    public static final byte[] A0J = {104, 101, 105, 99};
    public static final byte[] A0K = {109, 105, 102, 49};
    public static final byte[] A0L = {102, 116, 121, 112};
    public static final byte[] A0M;
    public static final byte[] A0N = {-1, -40, -1};
    public static final byte[] A0O = {79, 76, 89, 77, 80, 0};
    public static final byte[] A0P = {79, 76, 89, 77, 80, 85, 83, 0, 73, 73};
    public static final byte[] A0Q = {101, 88, 73, 102};
    public static final byte[] A0R = {73, 69, 78, 68};
    public static final byte[] A0S = {73, 72, 68, 82};
    public static final byte[] A0T = {-119, 80, 78, 71, 13, 10, 26, 10};
    public static final byte[] A0U = {69, 88, 73, 70};
    public static final byte[] A0V = {82, 73, 70, 70};
    public static final byte[] A0W = {87, 69, 66, 80};
    public static final int[] A0X = {8};
    public static final int[] A0Y = {8, 8, 8};
    public static final int[] A0Z = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    public static final AnonymousClass1hT[][] A0a;
    public static final AnonymousClass1hT A0b = new AnonymousClass1hT("JPEGInterchangeFormatLength", 514, 4);
    public static final AnonymousClass1hT A0c = new AnonymousClass1hT("JPEGInterchangeFormat", 513, 4);
    public static final HashMap<Integer, Integer> A0d = new HashMap<>();
    public static final HashSet<String> A0e = new HashSet<>(Arrays.asList("FNumber", "DigitalZoomRatio", "ExposureTime", "SubjectDistance", "GPSTimeStamp"));
    public static final List<Integer> A0f = Arrays.asList(2, 7, 4, 5);
    public static final List<Integer> A0g = Arrays.asList(1, 6, 3, 8);
    public static final Pattern A0h = Pattern.compile("^([0-9][0-9]):([0-9][0-9]):([0-9][0-9])$");
    public static final Pattern A0i = Pattern.compile(".*[1-9].*");
    public static final byte[] A0j = "http://ns.adobe.com/xap/1.0/\u0000".getBytes(A0H);
    public static final byte[] A0k = "ANIM".getBytes(Charset.defaultCharset());
    public static final byte[] A0l = "ANMF".getBytes(Charset.defaultCharset());
    public static final byte[] A0m = "VP8 ".getBytes(Charset.defaultCharset());
    public static final byte[] A0n = "VP8L".getBytes(Charset.defaultCharset());
    public static final byte[] A0o = "VP8X".getBytes(Charset.defaultCharset());
    public static final byte[] A0p = "XMP ".getBytes(Charset.defaultCharset());
    public static final AnonymousClass1hT[] A0q = {new AnonymousClass1hT("SubIFDPointer", 330, 4), new AnonymousClass1hT("ExifIFDPointer", 34665, 4), new AnonymousClass1hT("GPSInfoIFDPointer", 34853, 4), new AnonymousClass1hT("InteroperabilityIFDPointer", 40965, 4), new AnonymousClass1hT("CameraSettingsIFDPointer", 8224, 1), new AnonymousClass1hT("ImageProcessingIFDPointer", 8256, 1)};
    public static final AnonymousClass1hT[] A0r;
    public static final AnonymousClass1hT[] A0s;
    public static final AnonymousClass1hT[] A0t;
    public static final AnonymousClass1hT[] A0u;
    public static final AnonymousClass1hT[] A0v;
    public static final AnonymousClass1hT[] A0w;
    public static final AnonymousClass1hT[] A0x;
    public static final AnonymousClass1hT[] A0y;
    public static final AnonymousClass1hT[] A0z;
    public static final HashMap<Integer, AnonymousClass1hT>[] A10 = new HashMap[10];
    public static final HashMap<String, AnonymousClass1hT>[] A11 = new HashMap[10];
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public AssetManager.AssetInputStream A04;
    public FileDescriptor A05;
    public ByteOrder A06 = ByteOrder.BIG_ENDIAN;
    public Set<Integer> A07;
    public boolean A08;
    public byte[] A09;
    public int A0A;
    public int A0B;
    public final HashMap<String, AnonymousClass1hP>[] A0C;

    public static String A02(byte[] bArr) {
        int length = bArr.length;
        StringBuilder sb = new StringBuilder(length << 1);
        for (byte b : bArr) {
            sb.append(String.format("%02x", Byte.valueOf(b)));
        }
        return sb.toString();
    }

    private void A05() {
        int i = 0;
        while (true) {
            HashMap<String, AnonymousClass1hP>[] hashMapArr = this.A0C;
            if (i < hashMapArr.length) {
                HashMap<String, AnonymousClass1hP> hashMap = hashMapArr[i];
                hashMap.size();
                for (Map.Entry<String, AnonymousClass1hP> entry : hashMap.entrySet()) {
                    entry.getKey();
                    entry.getValue().A05(this.A06);
                }
                i++;
            } else {
                return;
            }
        }
    }

    private void A06() throws IOException {
        A07(0, 5);
        A07(0, 4);
        A07(5, 4);
        HashMap[] hashMapArr = this.A0C;
        Object obj = hashMapArr[1].get("PixelXDimension");
        Object obj2 = hashMapArr[1].get("PixelYDimension");
        if (!(obj == null || obj2 == null)) {
            hashMapArr[0].put("ImageWidth", obj);
            hashMapArr[0].put("ImageLength", obj2);
        }
        if (hashMapArr[4].isEmpty() && A0E(hashMapArr[5])) {
            hashMapArr[4] = hashMapArr[5];
            hashMapArr[5] = new HashMap();
        }
        A0E(hashMapArr[4]);
    }

    @Nullable
    public static AnonymousClass1hP A00(@NonNull AnonymousClass1hO r2, String str) {
        if (str != null) {
            if ("ISOSpeedRatings".equals(str)) {
                str = "PhotographicSensitivity";
            }
            for (int i = 0; i < A0a.length; i++) {
                AnonymousClass1hP r0 = r2.A0C[i].get(str);
                if (r0 != null) {
                    return r0;
                }
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    @Nullable
    private final String A01(@NonNull String str) {
        double d;
        String A072;
        if (str != null) {
            AnonymousClass1hP A002 = A00(this, str);
            if (A002 != null) {
                if (!A0e.contains(str)) {
                    return A002.A05(this.A06);
                }
                if (str.equals("GPSTimeStamp")) {
                    int i = A002.A00;
                    if (i == 5 || i == 10) {
                        AnonymousClass1hS[] r6 = (AnonymousClass1hS[]) A002.A04(this.A06);
                        if (r6 == null || r6.length != 3) {
                            A072 = AnonymousClass006.A07("Invalid GPS Timestamp array. array=", Arrays.toString(r6));
                        } else {
                            AnonymousClass1hS r2 = r6[0];
                            Integer valueOf = Integer.valueOf((int) (((float) r2.A01) / ((float) r2.A00)));
                            AnonymousClass1hS r22 = r6[1];
                            Integer valueOf2 = Integer.valueOf((int) (((float) r22.A01) / ((float) r22.A00)));
                            AnonymousClass1hS r23 = r6[2];
                            return String.format("%02d:%02d:%02d", valueOf, valueOf2, Integer.valueOf((int) (((float) r23.A01) / ((float) r23.A00))));
                        }
                    } else {
                        A072 = AnonymousClass006.A03("GPS Timestamp format is not rational. format=", i);
                    }
                    Log.w("ExifInterface", A072);
                    return null;
                }
                try {
                    Object A042 = A002.A04(this.A06);
                    if (A042 != null) {
                        if (A042 instanceof String) {
                            d = Double.parseDouble((String) A042);
                        } else if (A042 instanceof long[]) {
                            long[] jArr = (long[]) A042;
                            if (jArr.length == 1) {
                                d = (double) jArr[0];
                            } else {
                                throw new NumberFormatException("There are more than one component");
                            }
                        } else if (A042 instanceof int[]) {
                            int[] iArr = (int[]) A042;
                            if (iArr.length == 1) {
                                d = (double) iArr[0];
                            } else {
                                throw new NumberFormatException("There are more than one component");
                            }
                        } else if (A042 instanceof double[]) {
                            double[] dArr = (double[]) A042;
                            if (dArr.length == 1) {
                                d = dArr[0];
                            } else {
                                throw new NumberFormatException("There are more than one component");
                            }
                        } else if (A042 instanceof AnonymousClass1hS[]) {
                            AnonymousClass1hS[] r4 = (AnonymousClass1hS[]) A042;
                            if (r4.length == 1) {
                                AnonymousClass1hS r24 = r4[0];
                                d = ((double) r24.A01) / ((double) r24.A00);
                            } else {
                                throw new NumberFormatException("There are more than one component");
                            }
                        } else {
                            throw new NumberFormatException("Couldn't find a double value");
                        }
                        return Double.toString(d);
                    }
                    throw new NumberFormatException("NULL can't be converted to a double value");
                } catch (NumberFormatException unused) {
                }
            }
            return null;
        }
        throw new NullPointerException("tag shouldn't be null");
    }

    private void A04() {
        String A012 = A01("DateTimeOriginal");
        if (A012 != null && A01("DateTime") == null) {
            HashMap<String, AnonymousClass1hP> hashMap = this.A0C[0];
            byte[] bytes = AnonymousClass006.A01(A012, 0).getBytes(A0H);
            hashMap.put("DateTime", new AnonymousClass1hP(2, bytes.length, bytes));
        }
        if (A01("ImageWidth") == null) {
            this.A0C[0].put("ImageWidth", AnonymousClass1hP.A01(0, this.A06));
        }
        if (A01("ImageLength") == null) {
            this.A0C[0].put("ImageLength", AnonymousClass1hP.A01(0, this.A06));
        }
        if (A01("Orientation") == null) {
            this.A0C[0].put("Orientation", AnonymousClass1hP.A01(0, this.A06));
        }
        if (A01("LightSource") == null) {
            this.A0C[1].put("LightSource", AnonymousClass1hP.A01(0, this.A06));
        }
    }

    private void A07(int i, int i2) throws IOException {
        HashMap<String, AnonymousClass1hP>[] hashMapArr = this.A0C;
        HashMap<String, AnonymousClass1hP> hashMap = hashMapArr[i];
        if (!hashMap.isEmpty() && !hashMapArr[i2].isEmpty()) {
            AnonymousClass1hP r6 = hashMap.get("ImageLength");
            AnonymousClass1hP r2 = hashMapArr[i].get("ImageWidth");
            AnonymousClass1hP r1 = hashMapArr[i2].get("ImageLength");
            AnonymousClass1hP r4 = hashMapArr[i2].get("ImageWidth");
            if (r6 != null && r2 != null && r1 != null && r4 != null) {
                int A032 = r6.A03(this.A06);
                int A033 = r2.A03(this.A06);
                int A034 = r1.A03(this.A06);
                int A035 = r4.A03(this.A06);
                if (A032 < A034 && A033 < A035) {
                    HashMap<String, AnonymousClass1hP> hashMap2 = hashMapArr[i];
                    hashMapArr[i] = hashMapArr[i2];
                    hashMapArr[i2] = hashMap2;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0213, code lost:
        if (r3 == 9) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0215, code lost:
        if (r1 == 9) goto L_0x0217;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0219, code lost:
        if (r7 == 8) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x021f, code lost:
        if (r3 == 12) goto L_0x0223;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0221, code lost:
        if (r1 == 12) goto L_0x0223;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0225, code lost:
        if (r7 == 11) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0229, code lost:
        if (r7 == 7) goto L_0x022b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006e, code lost:
        if (r1 != 4) goto L_0x0211;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0070, code lost:
        if (r7 != 3) goto L_0x0211;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01ac, code lost:
        if (((long) r28.A00) != r4) goto L_0x00ca;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0A(X.AnonymousClass1hQ r28, int r29) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 558
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1hO.A0A(X.1hQ, int):void");
    }

    private void A0B(AnonymousClass1hQ r12, int i) throws IOException {
        AnonymousClass1hP r1;
        StringBuilder sb;
        String str;
        AnonymousClass1hP A002;
        AnonymousClass1hP A003;
        HashMap<String, AnonymousClass1hP>[] hashMapArr = this.A0C;
        AnonymousClass1hP r10 = hashMapArr[i].get("DefaultCropSize");
        AnonymousClass1hP r6 = hashMapArr[i].get("SensorTopBorder");
        AnonymousClass1hP r7 = hashMapArr[i].get("SensorLeftBorder");
        AnonymousClass1hP r2 = hashMapArr[i].get("SensorBottomBorder");
        AnonymousClass1hP r13 = hashMapArr[i].get("SensorRightBorder");
        if (r10 != null) {
            if (r10.A00 == 5) {
                AnonymousClass1hS[] r22 = (AnonymousClass1hS[]) r10.A04(this.A06);
                if (r22 == null || r22.length != 2) {
                    sb = new StringBuilder();
                    sb.append("Invalid crop size values. cropSize=");
                    str = Arrays.toString(r22);
                    sb.append(str);
                    Log.w("ExifInterface", sb.toString());
                    return;
                }
                A002 = AnonymousClass1hP.A02(r22[0], this.A06);
                A003 = AnonymousClass1hP.A02(r22[1], this.A06);
            } else {
                int[] iArr = (int[]) r10.A04(this.A06);
                if (iArr == null || iArr.length != 2) {
                    sb = new StringBuilder();
                    sb.append("Invalid crop size values. cropSize=");
                    str = Arrays.toString(iArr);
                    sb.append(str);
                    Log.w("ExifInterface", sb.toString());
                    return;
                }
                A002 = AnonymousClass1hP.A00(iArr[0], this.A06);
                A003 = AnonymousClass1hP.A00(iArr[1], this.A06);
            }
            hashMapArr[i].put("ImageWidth", A002);
            hashMapArr[i].put("ImageLength", A003);
        } else if (r6 == null || r7 == null || r2 == null || r13 == null) {
            AnonymousClass1hP r14 = hashMapArr[i].get("ImageLength");
            AnonymousClass1hP r0 = hashMapArr[i].get("ImageWidth");
            if ((r14 == null || r0 == null) && (r1 = hashMapArr[i].get("JPEGInterchangeFormat")) != null) {
                A0C(r12, r1.A03(this.A06), i);
            }
        } else {
            int A032 = r6.A03(this.A06);
            int A033 = r2.A03(this.A06);
            int A034 = r13.A03(this.A06);
            int A035 = r7.A03(this.A06);
            if (A033 > A032 && A034 > A035) {
                AnonymousClass1hP A004 = AnonymousClass1hP.A00(A033 - A032, this.A06);
                AnonymousClass1hP A005 = AnonymousClass1hP.A00(A034 - A035, this.A06);
                hashMapArr[i].put("ImageLength", A004);
                hashMapArr[i].put("ImageWidth", A005);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0058 A[FALL_THROUGH] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0C(X.AnonymousClass1hQ r12, int r13, int r14) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 400
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1hO.A0C(X.1hQ, int, int):void");
    }

    private void A0D(byte[] bArr, int i) throws IOException {
        AnonymousClass1hQ r1 = new AnonymousClass1hQ(new ByteArrayInputStream(bArr));
        A09(r1, bArr.length);
        A0A(r1, i);
    }

    private boolean A0E(HashMap hashMap) throws IOException {
        AnonymousClass1hP r2 = (AnonymousClass1hP) hashMap.get("ImageLength");
        AnonymousClass1hP r1 = (AnonymousClass1hP) hashMap.get("ImageWidth");
        if (!(r2 == null || r1 == null)) {
            int A032 = r2.A03(this.A06);
            int A033 = r1.A03(this.A06);
            if (A032 > 512 || A033 > 512) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static ByteOrder A03(AnonymousClass1hQ r2) throws IOException {
        short readShort = r2.readShort();
        if (readShort == 18761) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        if (readShort == 19789) {
            return ByteOrder.BIG_ENDIAN;
        }
        throw new IOException(AnonymousClass006.A07("Invalid byte order: ", Integer.toHexString(readShort)));
    }

    private void A08(AnonymousClass1hQ r6) throws IOException {
        A09(r6, r6.available());
        A0A(r6, 0);
        A0B(r6, 0);
        A0B(r6, 5);
        A0B(r6, 4);
        A06();
        if (this.A01 == 8) {
            AbstractMap[] abstractMapArr = this.A0C;
            AnonymousClass1hP r0 = (AnonymousClass1hP) abstractMapArr[1].get("MakerNote");
            if (r0 != null) {
                AnonymousClass1hQ r2 = new AnonymousClass1hQ(new ByteArrayInputStream(r0.A02));
                r2.A02 = this.A06;
                r2.A00(6);
                A0A(r2, 9);
                Object obj = abstractMapArr[9].get("ColorSpace");
                if (obj != null) {
                    abstractMapArr[1].put("ColorSpace", obj);
                }
            }
        }
    }

    private void A09(AnonymousClass1hQ r4, int i) throws IOException {
        ByteOrder A032 = A03(r4);
        this.A06 = A032;
        r4.A02 = A032;
        int readUnsignedShort = r4.readUnsignedShort();
        int i2 = this.A01;
        if (i2 == 7 || i2 == 10 || readUnsignedShort == 42) {
            int readInt = r4.readInt();
            if (readInt < 8 || readInt >= i) {
                throw new IOException(AnonymousClass006.A03("Invalid first Ifd offset: ", readInt));
            }
            int i3 = readInt - 8;
            if (i3 > 0 && r4.skipBytes(i3) != i3) {
                throw new IOException(AnonymousClass006.A03("Couldn't jump to first Ifd: ", i3));
            }
            return;
        }
        throw new IOException(AnonymousClass006.A07("Invalid start code: ", Integer.toHexString(readUnsignedShort)));
    }

    static {
        AnonymousClass1hT[] r5 = new AnonymousClass1hT[42];
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("NewSubfileType", 254, 4), new AnonymousClass1hT("SubfileType", MediaProviderUtils.JPEG_HEADER, 4), new AnonymousClass1hT("ImageWidth", 256), new AnonymousClass1hT("ImageLength", 257), new AnonymousClass1hT("BitsPerSample", 258, 3), new AnonymousClass1hT("Compression", 259, 3), new AnonymousClass1hT("PhotometricInterpretation", 262, 3), new AnonymousClass1hT("ImageDescription", 270, 2), new AnonymousClass1hT("Make", 271, 2), new AnonymousClass1hT("Model", 272, 2), new AnonymousClass1hT("StripOffsets", 273), new AnonymousClass1hT("Orientation", 274, 3), new AnonymousClass1hT("SamplesPerPixel", 277, 3), new AnonymousClass1hT("RowsPerStrip", 278), new AnonymousClass1hT("StripByteCounts", 279), new AnonymousClass1hT("XResolution", 282, 5), new AnonymousClass1hT("YResolution", 283, 5), new AnonymousClass1hT("PlanarConfiguration", 284, 3), new AnonymousClass1hT("ResolutionUnit", 296, 3), new AnonymousClass1hT("TransferFunction", 301, 3), new AnonymousClass1hT("Software", 305, 2), new AnonymousClass1hT("DateTime", 306, 2), new AnonymousClass1hT("Artist", 315, 2), new AnonymousClass1hT("WhitePoint", 318, 5), new AnonymousClass1hT("PrimaryChromaticities", 319, 5), new AnonymousClass1hT("SubIFDPointer", 330, 4), new AnonymousClass1hT("JPEGInterchangeFormat", 513, 4)}, 0, r5, 0, 27);
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("JPEGInterchangeFormatLength", 514, 4), new AnonymousClass1hT("YCbCrCoefficients", 529, 5), new AnonymousClass1hT("YCbCrSubSampling", 530, 3), new AnonymousClass1hT("YCbCrPositioning", 531, 3), new AnonymousClass1hT("ReferenceBlackWhite", 532, 5), new AnonymousClass1hT("Copyright", 33432, 2), new AnonymousClass1hT("ExifIFDPointer", 34665, 4), new AnonymousClass1hT("GPSInfoIFDPointer", 34853, 4), new AnonymousClass1hT("SensorTopBorder", 4, 4), new AnonymousClass1hT("SensorLeftBorder", 5, 4), new AnonymousClass1hT("SensorBottomBorder", 6, 4), new AnonymousClass1hT("SensorRightBorder", 7, 4), new AnonymousClass1hT("ISO", 23, 3), new AnonymousClass1hT("JpgFromRaw", 46, 7), new AnonymousClass1hT("Xmp", 700, 1)}, 0, r5, 27, 15);
        A0v = r5;
        AnonymousClass1hT[] r1 = new AnonymousClass1hT[74];
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("ExposureTime", 33434, 5), new AnonymousClass1hT("FNumber", 33437, 5), new AnonymousClass1hT("ExposureProgram", 34850, 3), new AnonymousClass1hT("SpectralSensitivity", 34852, 2), new AnonymousClass1hT("PhotographicSensitivity", 34855, 3), new AnonymousClass1hT("OECF", 34856, 7), new AnonymousClass1hT("SensitivityType", 34864, 3), new AnonymousClass1hT("StandardOutputSensitivity", 34865, 4), new AnonymousClass1hT("RecommendedExposureIndex", 34866, 4), new AnonymousClass1hT("ISOSpeed", 34867, 4), new AnonymousClass1hT("ISOSpeedLatitudeyyy", 34868, 4), new AnonymousClass1hT("ISOSpeedLatitudezzz", 34869, 4), new AnonymousClass1hT("ExifVersion", 36864, 2), new AnonymousClass1hT("DateTimeOriginal", 36867, 2), new AnonymousClass1hT("DateTimeDigitized", 36868, 2), new AnonymousClass1hT("OffsetTime", 36880, 2), new AnonymousClass1hT("OffsetTimeOriginal", 36881, 2), new AnonymousClass1hT("OffsetTimeDigitized", 36882, 2), new AnonymousClass1hT("ComponentsConfiguration", 37121, 7), new AnonymousClass1hT("CompressedBitsPerPixel", 37122, 5), new AnonymousClass1hT("ShutterSpeedValue", 37377, 10), new AnonymousClass1hT("ApertureValue", 37378, 5), new AnonymousClass1hT("BrightnessValue", 37379, 10), new AnonymousClass1hT("ExposureBiasValue", 37380, 10), new AnonymousClass1hT("MaxApertureValue", 37381, 5), new AnonymousClass1hT("SubjectDistance", 37382, 5), new AnonymousClass1hT("MeteringMode", 37383, 3)}, 0, r1, 0, 27);
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("LightSource", 37384, 3), new AnonymousClass1hT("Flash", 37385, 3), new AnonymousClass1hT("FocalLength", 37386, 5), new AnonymousClass1hT("SubjectArea", 37396, 3), new AnonymousClass1hT("MakerNote", 37500, 7), new AnonymousClass1hT("UserComment", 37510, 7), new AnonymousClass1hT("SubSecTime", 37520, 2), new AnonymousClass1hT("SubSecTimeOriginal", 37521, 2), new AnonymousClass1hT("SubSecTimeDigitized", 37522, 2), new AnonymousClass1hT("FlashpixVersion", 40960, 7), new AnonymousClass1hT("ColorSpace", 40961, 3), new AnonymousClass1hT("PixelXDimension", 40962), new AnonymousClass1hT("PixelYDimension", 40963), new AnonymousClass1hT("RelatedSoundFile", 40964, 2), new AnonymousClass1hT("InteroperabilityIFDPointer", 40965, 4), new AnonymousClass1hT("FlashEnergy", 41483, 5), new AnonymousClass1hT("SpatialFrequencyResponse", 41484, 7), new AnonymousClass1hT("FocalPlaneXResolution", 41486, 5), new AnonymousClass1hT("FocalPlaneYResolution", 41487, 5), new AnonymousClass1hT("FocalPlaneResolutionUnit", 41488, 3), new AnonymousClass1hT("SubjectLocation", 41492, 3), new AnonymousClass1hT("ExposureIndex", 41493, 5), new AnonymousClass1hT("SensingMethod", 41495, 3), new AnonymousClass1hT("FileSource", 41728, 7), new AnonymousClass1hT("SceneType", 41729, 7), new AnonymousClass1hT("CFAPattern", 41730, 7), new AnonymousClass1hT("CustomRendered", 41985, 3)}, 0, r1, 27, 27);
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("ExposureMode", 41986, 3), new AnonymousClass1hT("WhiteBalance", 41987, 3), new AnonymousClass1hT("DigitalZoomRatio", 41988, 5), new AnonymousClass1hT("FocalLengthIn35mmFilm", 41989, 3), new AnonymousClass1hT("SceneCaptureType", 41990, 3), new AnonymousClass1hT("GainControl", 41991, 3), new AnonymousClass1hT("Contrast", 41992, 3), new AnonymousClass1hT("Saturation", 41993, 3), new AnonymousClass1hT("Sharpness", 41994, 3), new AnonymousClass1hT("DeviceSettingDescription", 41995, 7), new AnonymousClass1hT("SubjectDistanceRange", 41996, 3), new AnonymousClass1hT("ImageUniqueID", 42016, 2), new AnonymousClass1hT("CameraOwnerName", 42032, 2), new AnonymousClass1hT("BodySerialNumber", 42033, 2), new AnonymousClass1hT("LensSpecification", 42034, 5), new AnonymousClass1hT("LensMake", 42035, 2), new AnonymousClass1hT("LensModel", 42036, 2), new AnonymousClass1hT("Gamma", 42240, 5), new AnonymousClass1hT("DNGVersion", 50706, 1), new AnonymousClass1hT("DefaultCropSize", 50720)}, 0, r1, 54, 20);
        A0r = r1;
        AnonymousClass1hT[] r7 = new AnonymousClass1hT[32];
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("GPSVersionID", 0, 1), new AnonymousClass1hT("GPSLatitudeRef", 1, 2), new AnonymousClass1hT("GPSLatitude", 2, 5), new AnonymousClass1hT("GPSLongitudeRef", 3, 2), new AnonymousClass1hT("GPSLongitude", 4, 5), new AnonymousClass1hT("GPSAltitudeRef", 5, 1), new AnonymousClass1hT("GPSAltitude", 6, 5), new AnonymousClass1hT("GPSTimeStamp", 7, 5), new AnonymousClass1hT("GPSSatellites", 8, 2), new AnonymousClass1hT("GPSStatus", 9, 2), new AnonymousClass1hT("GPSMeasureMode", 10, 2), new AnonymousClass1hT("GPSDOP", 11, 5), new AnonymousClass1hT("GPSSpeedRef", 12, 2), new AnonymousClass1hT("GPSSpeed", 13, 5), new AnonymousClass1hT("GPSTrackRef", 14, 2), new AnonymousClass1hT("GPSTrack", 15, 5), new AnonymousClass1hT("GPSImgDirectionRef", 16, 2), new AnonymousClass1hT("GPSImgDirection", 17, 5), new AnonymousClass1hT("GPSMapDatum", 18, 2), new AnonymousClass1hT("GPSDestLatitudeRef", 19, 2), new AnonymousClass1hT("GPSDestLatitude", 20, 5), new AnonymousClass1hT("GPSDestLongitudeRef", 21, 2), new AnonymousClass1hT("GPSDestLongitude", 22, 5), new AnonymousClass1hT("GPSDestBearingRef", 23, 2), new AnonymousClass1hT("GPSDestBearing", 24, 5), new AnonymousClass1hT("GPSDestDistanceRef", 25, 2), new AnonymousClass1hT("GPSDestDistance", 26, 5)}, 0, r7, 0, 27);
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("GPSProcessingMethod", 27, 7), new AnonymousClass1hT("GPSAreaInformation", 28, 7), new AnonymousClass1hT("GPSDateStamp", 29, 2), new AnonymousClass1hT("GPSDifferential", 30, 3), new AnonymousClass1hT("GPSHPositioningError", 31, 5)}, 0, r7, 27, 5);
        A0s = r7;
        AnonymousClass1hT[] r69 = {new AnonymousClass1hT("InteroperabilityIndex", 1, 2)};
        A0t = r69;
        AnonymousClass1hT[] r10 = new AnonymousClass1hT[37];
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("NewSubfileType", 254, 4), new AnonymousClass1hT("SubfileType", MediaProviderUtils.JPEG_HEADER, 4), new AnonymousClass1hT("ThumbnailImageWidth", 256), new AnonymousClass1hT("ThumbnailImageLength", 257), new AnonymousClass1hT("BitsPerSample", 258, 3), new AnonymousClass1hT("Compression", 259, 3), new AnonymousClass1hT("PhotometricInterpretation", 262, 3), new AnonymousClass1hT("ImageDescription", 270, 2), new AnonymousClass1hT("Make", 271, 2), new AnonymousClass1hT("Model", 272, 2), new AnonymousClass1hT("StripOffsets", 273), new AnonymousClass1hT("ThumbnailOrientation", 274, 3), new AnonymousClass1hT("SamplesPerPixel", 277, 3), new AnonymousClass1hT("RowsPerStrip", 278), new AnonymousClass1hT("StripByteCounts", 279), new AnonymousClass1hT("XResolution", 282, 5), new AnonymousClass1hT("YResolution", 283, 5), new AnonymousClass1hT("PlanarConfiguration", 284, 3), new AnonymousClass1hT("ResolutionUnit", 296, 3), new AnonymousClass1hT("TransferFunction", 301, 3), new AnonymousClass1hT("Software", 305, 2), new AnonymousClass1hT("DateTime", 306, 2), new AnonymousClass1hT("Artist", 315, 2), new AnonymousClass1hT("WhitePoint", 318, 5), new AnonymousClass1hT("PrimaryChromaticities", 319, 5), new AnonymousClass1hT("SubIFDPointer", 330, 4), new AnonymousClass1hT("JPEGInterchangeFormat", 513, 4)}, 0, r10, 0, 27);
        System.arraycopy(new AnonymousClass1hT[]{new AnonymousClass1hT("JPEGInterchangeFormatLength", 514, 4), new AnonymousClass1hT("YCbCrCoefficients", 529, 5), new AnonymousClass1hT("YCbCrSubSampling", 530, 3), new AnonymousClass1hT("YCbCrPositioning", 531, 3), new AnonymousClass1hT("ReferenceBlackWhite", 532, 5), new AnonymousClass1hT("Copyright", 33432, 2), new AnonymousClass1hT("ExifIFDPointer", 34665, 4), new AnonymousClass1hT("GPSInfoIFDPointer", 34853, 4), new AnonymousClass1hT("DNGVersion", 50706, 1), new AnonymousClass1hT("DefaultCropSize", 50720)}, 0, r10, 27, 10);
        A0u = r10;
        AnonymousClass1hT[] r72 = {new AnonymousClass1hT("ThumbnailImage", 256, 7), new AnonymousClass1hT("CameraSettingsIFDPointer", 8224, 4), new AnonymousClass1hT("ImageProcessingIFDPointer", 8256, 4)};
        A0y = r72;
        AnonymousClass1hT[] r73 = {new AnonymousClass1hT("PreviewImageStart", 257, 4), new AnonymousClass1hT("PreviewImageLength", 258, 4)};
        A0w = r73;
        AnonymousClass1hT[] r74 = {new AnonymousClass1hT("AspectFrame", 4371, 3)};
        A0x = r74;
        AnonymousClass1hT[] r75 = {new AnonymousClass1hT("ColorSpace", 55, 3)};
        A0z = r75;
        A0a = new AnonymousClass1hT[][]{r5, r1, r7, r69, r10, r5, r72, r73, r74, r75};
        Charset forName = Charset.forName("US-ASCII");
        A0H = forName;
        A0M = "Exif\u0000\u0000".getBytes(forName);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        A0D = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        int i = 0;
        while (true) {
            AnonymousClass1hT[][] r4 = A0a;
            if (i < r4.length) {
                A10[i] = new HashMap<>();
                A11[i] = new HashMap<>();
                AnonymousClass1hT[] r8 = r4[i];
                for (AnonymousClass1hT r42 : r8) {
                    A10[i].put(Integer.valueOf(r42.A00), r42);
                    A11[i].put(r42.A03, r42);
                }
                i++;
            } else {
                HashMap<Integer, Integer> hashMap = A0d;
                hashMap.put(Integer.valueOf(A0q[0].A00), 5);
                hashMap.put(Integer.valueOf(A0q[1].A00), 1);
                hashMap.put(Integer.valueOf(A0q[2].A00), 2);
                AnonymousClass1hT[] r3 = A0q;
                hashMap.put(Integer.valueOf(r3[3].A00), 3);
                hashMap.put(Integer.valueOf(r3[4].A00), 7);
                hashMap.put(Integer.valueOf(r3[5].A00), 8);
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0546, code lost:
        if (r1 != 7) goto L_0x0548;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01cc  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01df  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x02b3  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0321  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0352  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x03d7 A[Catch:{ all -> 0x0677 }] */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x040b A[Catch:{ all -> 0x0677 }] */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0434  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x04a1  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x051e  */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x066f  */
    /* JADX WARNING: Removed duplicated region for block: B:380:0x01af A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:416:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b A[SYNTHETIC, Splitter:B:8:0x002b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass1hO(@androidx.annotation.NonNull java.io.InputStream r20) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1846
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1hO.<init>(java.io.InputStream):void");
    }
}
