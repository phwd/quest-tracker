package X;

import android.content.Context;
import com.facebook.FacebookSdk;
import com.facebook.rti.common.time.RealtimeSinceBootClock;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.zip.Deflater;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
/* renamed from: X.0m2  reason: invalid class name and case insensitive filesystem */
public final class C05890m2 {
    @GuardedBy("member reference guarded by this")
    public C02240a0 A00;
    @GuardedBy("member reference guarded by this")
    public C02250a2 A01;
    @Nullable
    public String A02;
    public InetAddress A03;
    public InetAddress A04;
    @GuardedBy("member reference guarded by this")
    public Socket A05;
    public final Context A06;
    public final RealtimeSinceBootClock A07;
    public final AnonymousClass0Wo A08;
    public final C01760Xc A09;
    public final AbstractC02050Yt A0A;
    public final AnonymousClass0ZM A0B;
    public final C05220kZ A0C;
    public final ScheduledExecutorService A0D;
    public final AnonymousClass0nN A0E;
    public volatile C05880m1 A0F;
    public volatile C05870m0 A0G;
    @GuardedBy("this")
    public volatile boolean A0H = false;

    public static int A01(DataOutputStream dataOutputStream, int i) throws IOException {
        int i2 = 0;
        do {
            int i3 = i % FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE;
            i >>= 7;
            if (i > 0) {
                i3 |= FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE;
            }
            dataOutputStream.writeByte(i3);
            i2++;
        } while (i > 0);
        return i2;
    }

    public static int A00(C02080Zc r2) {
        int i = (r2.A03.mValue << 4) | 0;
        if (r2.A04) {
            i |= 8;
        }
        int i2 = i | (r2.A02 << 1);
        if (r2.A01) {
            return i2 | 1;
        }
        return i2;
    }

    public static byte[] A03(String str) {
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] A04(byte[] bArr) {
        Deflater deflater = new Deflater(9);
        deflater.setInput(bArr);
        deflater.finish();
        int length = bArr.length + 32;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        byte[] bArr2 = new byte[length];
        while (!deflater.finished()) {
            byteArrayOutputStream.write(bArr2, 0, deflater.deflate(bArr2));
        }
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            AnonymousClass0NO.A0J("ZlibCompressionUtil", e, "got io exception closing ByteArrayOutputStream");
        }
        deflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    public final void A05() {
        Socket socket = this.A05;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
        }
        synchronized (this) {
            this.A05 = null;
            this.A02 = null;
            this.A00 = null;
            this.A01 = null;
            this.A0G.A00.A0X = AnonymousClass007.A0D;
        }
        this.A0G.A00();
    }

    public C05890m2(C01760Xc r2, AnonymousClass0Wo r3, AnonymousClass0ZM r4, RealtimeSinceBootClock realtimeSinceBootClock, AnonymousClass0nN r6, ScheduledExecutorService scheduledExecutorService, AbstractC02050Yt r8, C05220kZ r9, Context context, @Nullable String str) {
        this.A09 = r2;
        this.A08 = r3;
        this.A0B = r4;
        this.A07 = realtimeSinceBootClock;
        this.A0E = r6;
        this.A0D = scheduledExecutorService;
        this.A0A = r8;
        this.A0C = r9;
        this.A06 = context;
    }

    public static void A02(C05890m2 r18, C02250a2 r19, C02150Zl r20) throws IOException {
        AnonymousClass0W8 r0;
        String str;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String name;
        if (r19 != null) {
            boolean z = r20 instanceof C05440l8;
            if (z) {
                r0 = AnonymousClass0W8.A00(((C05440l8) r20).A02().A01);
            } else {
                r0 = C06530na.A00;
            }
            if (r0.A02()) {
                str = AnonymousClass0WL.A00((String) r0.A01());
                if (str == null) {
                    str = (String) r0.A01();
                }
            } else {
                str = "";
            }
            try {
                synchronized (r19) {
                    C02080Zc r3 = r20.A00;
                    EnumC02120Zg r02 = r3.A03;
                    int i6 = 0;
                    switch (r02.ordinal()) {
                        case 0:
                            if (r20 instanceof C05810lt) {
                                C05810lt r1 = (C05810lt) r20;
                                r1.A03();
                                DataOutputStream dataOutputStream = r19.A00;
                                C02080Zc r03 = r1.A00;
                                AnonymousClass0ZZ A032 = r1.A03();
                                AnonymousClass0ZX A022 = r1.A02();
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
                                AnonymousClass0WN r9 = new AnonymousClass0WN(byteArrayOutputStream);
                                AnonymousClass0ZY r13 = A022.A02;
                                C02140Zk r14 = new C02140Zk(AnonymousClass007.A00);
                                AnonymousClass0WP<Long> r12 = C02130Zi.A0P;
                                Long l = r13.A0B;
                                Map<AnonymousClass0WP<?>, Object> map = r14.A01;
                                map.put(r12, l);
                                map.put(C02130Zi.A0O, r13.A0I);
                                map.put(C02130Zi.A01, r13.A07);
                                map.put(C02130Zi.A09, r13.A09);
                                map.put(C02130Zi.A0L, Integer.valueOf(r13.A00));
                                map.put(C02130Zi.A0J, r13.A03);
                                map.put(C02130Zi.A0G, r13.A01);
                                map.put(C02130Zi.A07, r13.A0G);
                                map.put(C02130Zi.A0E, r13.A02);
                                map.put(C02130Zi.A0I, r13.A06);
                                map.put(C02130Zi.A0H, r13.A05);
                                map.put(C02130Zi.A03, r13.A0A);
                                map.put(C02130Zi.A02, null);
                                ArrayList arrayList = new ArrayList();
                                for (String str2 : r13.A0J) {
                                    Integer num = AnonymousClass0WL.A01.get(str2);
                                    if (num != null) {
                                        arrayList.add(num);
                                    }
                                }
                                map.put(C02130Zi.A0N, arrayList);
                                map.put(C02130Zi.A05, r13.A0D);
                                AnonymousClass0WP<Long> r04 = C02130Zi.A00;
                                String str3 = r13.A0C;
                                map.put(r04, str3 == null ? null : Long.valueOf(Long.parseLong(str3)));
                                map.put(C02130Zi.A0K, null);
                                map.put(C02130Zi.A06, null);
                                map.put(C02130Zi.A0M, r13.A0F);
                                map.put(C02130Zi.A08, r13.A0H);
                                map.put(C02130Zi.A04, r13.A04);
                                map.put(C02130Zi.A0F, r13.A08);
                                C02140Zk r6 = new C02140Zk(AnonymousClass007.A0E);
                                AnonymousClass0WP<String> r15 = C02130Zi.A0U;
                                String str4 = A022.A03;
                                Map<AnonymousClass0WP<?>, Object> map2 = r6.A01;
                                map2.put(r15, str4);
                                map2.put(C02130Zi.A0c, A022.A06);
                                map2.put(C02130Zi.A0b, A022.A05);
                                map2.put(C02130Zi.A0V, r14);
                                map2.put(C02130Zi.A0Y, A022.A04);
                                map2.put(C02130Zi.A0X, null);
                                map2.put(C02130Zi.A0Z, null);
                                map2.put(C02130Zi.A0W, null);
                                map2.put(C02130Zi.A0d, null);
                                map2.put(C02130Zi.A0T, r13.A0K);
                                map2.put(C02130Zi.A0a, A022.A01);
                                r6.A01(r9);
                                byte[] A042 = A04(byteArrayOutputStream.toByteArray());
                                int length = A042.length;
                                i3 = length + 12;
                                dataOutputStream.writeByte(A00(r03));
                                i2 = A01(dataOutputStream, i3) + 1;
                                dataOutputStream.writeByte(0);
                                dataOutputStream.writeByte(6);
                                dataOutputStream.writeByte(77);
                                dataOutputStream.writeByte(81);
                                dataOutputStream.writeByte(84);
                                dataOutputStream.writeByte(84);
                                dataOutputStream.writeByte(111);
                                dataOutputStream.writeByte(84);
                                dataOutputStream.write(A032.A01);
                                int i7 = 0;
                                if (A032.A05) {
                                    i7 = FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE;
                                }
                                if (A032.A04) {
                                    i7 |= 64;
                                }
                                if (A032.A07) {
                                    i7 |= 32;
                                }
                                int i8 = i7 | ((A032.A02 & 3) << 3);
                                if (A032.A06) {
                                    i8 |= 4;
                                }
                                if (A032.A03) {
                                    i8 |= 2;
                                }
                                dataOutputStream.write(i8);
                                dataOutputStream.writeShort(A032.A00);
                                dataOutputStream.write(A042, 0, length);
                                dataOutputStream.flush();
                                i = i2 + i3;
                                i6 = i;
                                break;
                            } else {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Unexpected type: ");
                                sb.append(r20);
                                throw new AssertionError(sb.toString());
                            }
                        case 1:
                            if (r20 instanceof C05830lv) {
                                C05830lv r32 = (C05830lv) r20;
                                r32.A03();
                                i5 = 4;
                                boolean z2 = true;
                                if (r32.A03().A00 == 0) {
                                    boolean z3 = false;
                                    if (r32.A02() != null) {
                                        z3 = true;
                                    }
                                    AnonymousClass0W9.A01(z3);
                                    byte[] A033 = A03(r32.A02().toString());
                                    r19.A00.writeByte(A00(r32.A00));
                                    DataOutputStream dataOutputStream2 = r19.A00;
                                    int length2 = A033.length;
                                    int A012 = 1 + A01(dataOutputStream2, length2 + 4);
                                    r19.A00.writeByte(0);
                                    r19.A00.writeByte(r32.A03().A00);
                                    r19.A00.writeShort(length2);
                                    r19.A00.write(A033);
                                    i5 = A012 + 4 + length2;
                                    r19.A00.flush();
                                } else {
                                    if (r32.A02() != null) {
                                        z2 = false;
                                    }
                                    AnonymousClass0W9.A01(z2);
                                    r19.A00.writeByte(A00(r32.A00));
                                    r19.A00.writeByte(2);
                                    r19.A00.writeByte(0);
                                    r19.A00.writeByte(r32.A03().A00);
                                    r19.A00.flush();
                                }
                                i6 = i5;
                                break;
                            } else {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Unexpected type: ");
                                sb2.append(r20);
                                throw new AssertionError(sb2.toString());
                            }
                        case 2:
                            if (z) {
                                C05440l8 r16 = (C05440l8) r20;
                                r16.A02();
                                r16.A02();
                                C02080Zc r05 = r16.A00;
                                C02180Zo A023 = r16.A02();
                                byte[] A034 = r16.A03();
                                int i9 = r19.A01;
                                int i10 = 2;
                                if (i9 != 0) {
                                    byte[] A043 = A04(A034);
                                    if (2 != i9 || A043.length <= A034.length) {
                                        A034 = A043;
                                    } else {
                                        r05.A01 = true;
                                    }
                                }
                                byte[] A035 = A03(A023.A01);
                                int length3 = A035.length;
                                int i11 = length3 + 2;
                                int i12 = r05.A02;
                                if (i12 <= 0) {
                                    i10 = 0;
                                }
                                int i13 = i11 + i10;
                                int length4 = A034.length;
                                int i14 = i13 + length4;
                                r19.A00.writeByte(A00(r05));
                                int A013 = 1 + A01(r19.A00, i14);
                                r19.A00.writeShort(length3);
                                r19.A00.write(A035, 0, length3);
                                if (i12 > 0) {
                                    r19.A00.writeShort(A023.A00);
                                }
                                r19.A00.write(A034, 0, length4);
                                r19.A00.flush();
                                i4 = A013 + i14;
                                i6 = i4;
                                break;
                            } else {
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append("Unexpected type: ");
                                sb3.append(r20);
                                throw new AssertionError(sb3.toString());
                            }
                            break;
                        case 3:
                            if (r20 instanceof C05750ln) {
                                C05750ln r17 = (C05750ln) r20;
                                r17.A02();
                                C02080Zc r06 = r17.A00;
                                C02100Ze A024 = r17.A02();
                                r19.A00.writeByte(A00(r06));
                                int A014 = A01(r19.A00, 2) + 1;
                                r19.A00.writeShort(A024.A00);
                                i = A014 + 2;
                                r19.A00.flush();
                                i6 = i;
                                break;
                            } else {
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append("Unexpected type: ");
                                sb4.append(r20);
                                throw new AssertionError(sb4.toString());
                            }
                        case 4:
                        case 5:
                        case 6:
                        default:
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Unknown message type: ");
                            sb5.append(r02);
                            AnonymousClass0NO.A0L("MessageEncoder", new IllegalArgumentException(sb5.toString()), "send/unexpected; type=%s", r02);
                            break;
                        case 7:
                            if (r20 instanceof C05300ki) {
                                C05300ki r07 = (C05300ki) r20;
                                r07.A03();
                                C02080Zc r8 = r07.A00;
                                C02100Ze A025 = r07.A02();
                                List<SubscribeTopic> list = r07.A03().A00;
                                int i15 = 0;
                                for (SubscribeTopic subscribeTopic : list) {
                                    i15 = i15 + A03(subscribeTopic.A01).length + 2 + 1;
                                }
                                int i16 = 2 + i15;
                                r19.A00.writeByte(A00(r8));
                                int A015 = 1 + A01(r19.A00, i16);
                                r19.A00.writeShort(A025.A00);
                                for (SubscribeTopic subscribeTopic2 : list) {
                                    byte[] A036 = A03(subscribeTopic2.A01);
                                    DataOutputStream dataOutputStream3 = r19.A00;
                                    int length5 = A036.length;
                                    dataOutputStream3.writeShort(length5);
                                    r19.A00.write(A036, 0, length5);
                                    r19.A00.write(subscribeTopic2.A00);
                                }
                                r19.A00.flush();
                                i6 = A015 + i16;
                                break;
                            } else {
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append("Unexpected type: ");
                                sb6.append(r20);
                                throw new AssertionError(sb6.toString());
                            }
                        case 8:
                            if (r20 instanceof AnonymousClass0l7) {
                                AnonymousClass0l7 r33 = (AnonymousClass0l7) r20;
                                r33.A02();
                                int size = r33.A03().A00.size() + 2;
                                r19.A00.writeByte(A00(r33.A00));
                                int A016 = A01(r19.A00, size) + 1;
                                r19.A00.writeShort(r33.A02().A00);
                                for (Integer num2 : r33.A03().A00) {
                                    r19.A00.writeByte(num2.intValue());
                                }
                                r19.A00.flush();
                                i5 = A016 + size;
                                i6 = i5;
                                break;
                            } else {
                                StringBuilder sb7 = new StringBuilder();
                                sb7.append("Unexpected type: ");
                                sb7.append(r20);
                                throw new AssertionError(sb7.toString());
                            }
                        case 9:
                            if (r20 instanceof C05260ke) {
                                C05260ke r08 = (C05260ke) r20;
                                r08.A03();
                                C02080Zc r10 = r08.A00;
                                C02100Ze A026 = r08.A02();
                                List<String> list2 = r08.A03().A00;
                                int i17 = 0;
                                for (String str5 : list2) {
                                    i17 += A03(str5).length + 2;
                                }
                                int i18 = 2 + i17;
                                r19.A00.writeByte(A00(r10));
                                int A017 = 1 + A01(r19.A00, i18);
                                r19.A00.writeShort(A026.A00);
                                for (String str6 : list2) {
                                    byte[] A037 = A03(str6);
                                    DataOutputStream dataOutputStream4 = r19.A00;
                                    int length6 = A037.length;
                                    dataOutputStream4.writeShort(length6);
                                    r19.A00.write(A037, 0, length6);
                                }
                                r19.A00.flush();
                                i4 = A017 + i18;
                                i6 = i4;
                                break;
                            } else {
                                StringBuilder sb8 = new StringBuilder();
                                sb8.append("Unexpected type: ");
                                sb8.append(r20);
                                throw new AssertionError(sb8.toString());
                            }
                        case 10:
                            if (r20 instanceof C05270kf) {
                                C05270kf r62 = (C05270kf) r20;
                                r62.A02();
                                r19.A00.writeByte(A00(r62.A00));
                                i3 = 2;
                                i2 = A01(r19.A00, 2) + 1;
                                r19.A00.writeShort(r62.A02().A00);
                                r19.A00.flush();
                                i = i2 + i3;
                                i6 = i;
                                break;
                            } else {
                                StringBuilder sb9 = new StringBuilder();
                                sb9.append("Unexpected type: ");
                                sb9.append(r20);
                                throw new AssertionError(sb9.toString());
                            }
                        case 11:
                            r19.A00.writeByte(A00(r3));
                            r19.A00.writeByte(0);
                            r19.A00.flush();
                            break;
                        case 12:
                            r19.A00.writeByte(A00(r3));
                            r19.A00.writeByte(0);
                            r19.A00.flush();
                            break;
                    }
                    name = r02.name();
                    if (z) {
                        ((C05440l8) r20).A02();
                    }
                    C05880m1 r102 = r19.A02;
                    if (i6 >= 0) {
                        AnonymousClass0ZF r11 = r102.A00;
                        ((AnonymousClass0nI) r11.A09.A06(AnonymousClass0Ic.class)).A03((long) i6, r11.A0Z, "m", "s", "b");
                    }
                    AnonymousClass0ZF r5 = r102.A00;
                    ((AnonymousClass0nI) r5.A09.A06(AnonymousClass0Ic.class)).A03(1, r5.A0Z, "m", "s", "c");
                    C02580am.A02.A00(i6, true);
                }
                r18.A0G.A02(name, str);
            } catch (IOException e) {
                r18.A0G.A02(AnonymousClass006.A05(r20.A00.A03.name(), "-failed"), str);
                throw e;
            }
        } else {
            throw new IOException("No message encoder");
        }
    }
}
