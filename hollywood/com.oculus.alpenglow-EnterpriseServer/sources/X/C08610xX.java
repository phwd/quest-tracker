package X;

import android.content.Context;
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
/* renamed from: X.0xX  reason: invalid class name and case insensitive filesystem */
public final class C08610xX {
    @GuardedBy("member reference guarded by this")
    public C08620xY A00;
    @GuardedBy("member reference guarded by this")
    public C09370zJ A01;
    @Nullable
    public String A02;
    public InetAddress A03;
    public InetAddress A04;
    @GuardedBy("member reference guarded by this")
    public Socket A05;
    public final Context A06;
    public final RealtimeSinceBootClock A07;
    public final AnonymousClass0x2 A08;
    public final C07520vQ A09;
    public final AbstractC08480xJ A0A;
    public final AnonymousClass0x9 A0B;
    public final AnonymousClass0vc A0C;
    public final ScheduledExecutorService A0D;
    public final C07640vh A0E;
    public volatile AnonymousClass104 A0F;
    public volatile C08530xO A0G;
    @GuardedBy("this")
    public volatile boolean A0H = false;

    public static int A01(DataOutputStream dataOutputStream, int i) throws IOException {
        int i2 = 0;
        do {
            int i3 = i % 128;
            i >>= 7;
            if (i > 0) {
                i3 |= 128;
            }
            dataOutputStream.writeByte(i3);
            i2++;
        } while (i > 0);
        return i2;
    }

    public static int A00(AnonymousClass0z5 r2) {
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
            AnonymousClass0NK.A0B("ZlibCompressionUtil", e, "got io exception closing ByteArrayOutputStream");
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

    public C08610xX(C07520vQ r2, AnonymousClass0x2 r3, AnonymousClass0x9 r4, RealtimeSinceBootClock realtimeSinceBootClock, C07640vh r6, ScheduledExecutorService scheduledExecutorService, AbstractC08480xJ r8, AnonymousClass0vc r9, Context context, @Nullable String str) {
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

    public static void A02(C08610xX r18, C09370zJ r19, AnonymousClass0yD r20) throws IOException {
        AbstractC09150yk r0;
        String str;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String name;
        if (r19 != null) {
            boolean z = r20 instanceof AnonymousClass0yP;
            if (z) {
                r0 = AbstractC09150yk.A00(((AnonymousClass0yP) r20).A02().A01);
            } else {
                r0 = C09340zG.A00;
            }
            if (r0.A02()) {
                str = C09070yc.A00((String) r0.A01());
                if (str == null) {
                    str = (String) r0.A01();
                }
            } else {
                str = "";
            }
            try {
                synchronized (r19) {
                    AnonymousClass0z5 r3 = r20.A00;
                    EnumC08830xt r02 = r3.A03;
                    int i6 = 0;
                    switch (r02.ordinal()) {
                        case 0:
                            if (r20 instanceof AnonymousClass0yJ) {
                                AnonymousClass0yJ r1 = (AnonymousClass0yJ) r20;
                                r1.A03();
                                DataOutputStream dataOutputStream = r19.A00;
                                AnonymousClass0z5 r03 = r1.A00;
                                C09200yq A032 = r1.A03();
                                C08840xu A022 = r1.A02();
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
                                C08690xf r9 = new C08690xf(byteArrayOutputStream);
                                C08650xb r13 = A022.A02;
                                C08630xZ r14 = new C08630xZ(AnonymousClass007.A00);
                                C09290zB<Long> r12 = C08640xa.A0P;
                                Long l = r13.A0B;
                                Map<C09290zB<?>, Object> map = r14.A01;
                                map.put(r12, l);
                                map.put(C08640xa.A0O, r13.A0I);
                                map.put(C08640xa.A01, r13.A07);
                                map.put(C08640xa.A09, r13.A09);
                                map.put(C08640xa.A0L, Integer.valueOf(r13.A00));
                                map.put(C08640xa.A0J, r13.A03);
                                map.put(C08640xa.A0G, r13.A01);
                                map.put(C08640xa.A07, r13.A0G);
                                map.put(C08640xa.A0E, r13.A02);
                                map.put(C08640xa.A0I, r13.A06);
                                map.put(C08640xa.A0H, r13.A05);
                                map.put(C08640xa.A03, r13.A0A);
                                map.put(C08640xa.A02, null);
                                ArrayList arrayList = new ArrayList();
                                for (String str2 : r13.A0J) {
                                    Integer num = C09070yc.A01.get(str2);
                                    if (num != null) {
                                        arrayList.add(num);
                                    }
                                }
                                map.put(C08640xa.A0N, arrayList);
                                map.put(C08640xa.A05, r13.A0D);
                                C09290zB<Long> r04 = C08640xa.A00;
                                String str3 = r13.A0C;
                                map.put(r04, str3 == null ? null : Long.valueOf(Long.parseLong(str3)));
                                map.put(C08640xa.A0K, null);
                                map.put(C08640xa.A06, null);
                                map.put(C08640xa.A0M, r13.A0F);
                                map.put(C08640xa.A08, r13.A0H);
                                map.put(C08640xa.A04, r13.A04);
                                map.put(C08640xa.A0F, r13.A08);
                                C08630xZ r6 = new C08630xZ(AnonymousClass007.A0E);
                                C09290zB<String> r15 = C08640xa.A0U;
                                String str4 = A022.A03;
                                Map<C09290zB<?>, Object> map2 = r6.A01;
                                map2.put(r15, str4);
                                map2.put(C08640xa.A0c, A022.A06);
                                map2.put(C08640xa.A0b, A022.A05);
                                map2.put(C08640xa.A0V, r14);
                                map2.put(C08640xa.A0Y, A022.A04);
                                map2.put(C08640xa.A0X, null);
                                map2.put(C08640xa.A0Z, null);
                                map2.put(C08640xa.A0W, null);
                                map2.put(C08640xa.A0d, null);
                                map2.put(C08640xa.A0T, r13.A0K);
                                map2.put(C08640xa.A0a, A022.A01);
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
                                    i7 = 128;
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
                                throw new AssertionError("Unexpected type: " + r20);
                            }
                        case 1:
                            if (r20 instanceof C08970yE) {
                                C08970yE r32 = (C08970yE) r20;
                                r32.A03();
                                i5 = 4;
                                boolean z2 = true;
                                if (r32.A03().A00 == 0) {
                                    boolean z3 = false;
                                    if (r32.A02() != null) {
                                        z3 = true;
                                    }
                                    C08170wh.A01(z3);
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
                                    C08170wh.A01(z2);
                                    r19.A00.writeByte(A00(r32.A00));
                                    r19.A00.writeByte(2);
                                    r19.A00.writeByte(0);
                                    r19.A00.writeByte(r32.A03().A00);
                                    r19.A00.flush();
                                }
                                i6 = i5;
                                break;
                            } else {
                                throw new AssertionError("Unexpected type: " + r20);
                            }
                        case 2:
                            if (z) {
                                AnonymousClass0yP r05 = (AnonymousClass0yP) r20;
                                r05.A02();
                                r05.A02();
                                AnonymousClass0z5 r142 = r05.A00;
                                AnonymousClass0z0 A023 = r05.A02();
                                byte[] A034 = r05.A03();
                                int i9 = r19.A01;
                                int i10 = 2;
                                if (i9 != 0) {
                                    byte[] A043 = A04(A034);
                                    if (2 != i9 || A043.length <= A034.length) {
                                        A034 = A043;
                                    } else {
                                        r142.A01 = true;
                                    }
                                }
                                byte[] A035 = A03(A023.A01);
                                int length3 = A035.length;
                                int i11 = length3 + 2;
                                int i12 = r142.A02;
                                if (i12 <= 0) {
                                    i10 = 0;
                                }
                                int i13 = i11 + i10;
                                int length4 = A034.length;
                                int i14 = i13 + length4;
                                r19.A00.writeByte(A00(r142));
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
                                throw new AssertionError("Unexpected type: " + r20);
                            }
                        case 3:
                            if (r20 instanceof AnonymousClass0yW) {
                                AnonymousClass0yW r16 = (AnonymousClass0yW) r20;
                                r16.A02();
                                AnonymousClass0z5 r06 = r16.A00;
                                C09590zi A024 = r16.A02();
                                r19.A00.writeByte(A00(r06));
                                int A014 = A01(r19.A00, 2) + 1;
                                r19.A00.writeShort(A024.A00);
                                i = A014 + 2;
                                r19.A00.flush();
                                i6 = i;
                                break;
                            } else {
                                throw new AssertionError("Unexpected type: " + r20);
                            }
                        case 4:
                        case 5:
                        case 6:
                        default:
                            AnonymousClass0NK.A0D("MessageEncoder", new IllegalArgumentException("Unknown message type: " + r02), "send/unexpected; type=%s", r02);
                            break;
                        case 7:
                            if (r20 instanceof C09030yN) {
                                C09030yN r07 = (C09030yN) r20;
                                r07.A03();
                                AnonymousClass0z5 r8 = r07.A00;
                                C09590zi A025 = r07.A02();
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
                                throw new AssertionError("Unexpected type: " + r20);
                            }
                        case 8:
                            if (r20 instanceof C09040yO) {
                                C09040yO r33 = (C09040yO) r20;
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
                                throw new AssertionError("Unexpected type: " + r20);
                            }
                        case 9:
                            if (r20 instanceof C09020yM) {
                                C09020yM r08 = (C09020yM) r20;
                                r08.A03();
                                AnonymousClass0z5 r10 = r08.A00;
                                C09590zi A026 = r08.A02();
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
                                throw new AssertionError("Unexpected type: " + r20);
                            }
                        case 10:
                            if (r20 instanceof C09090ye) {
                                C09090ye r62 = (C09090ye) r20;
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
                                throw new AssertionError("Unexpected type: " + r20);
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
                        ((AnonymousClass0yP) r20).A02();
                    }
                    AnonymousClass104 r11 = r19.A02;
                    if (i6 >= 0) {
                        C08300wz r34 = r11.A00;
                        ((AbstractC07790w1) r34.A09.A06(C09510za.class)).A03((long) i6, r34.A0Z, "m", "s", "b");
                    }
                    C08300wz r35 = r11.A00;
                    ((AbstractC07790w1) r35.A09.A06(C09510za.class)).A03(1, r35.A0Z, "m", "s", "c");
                    AnonymousClass0z8.A02.A00(i6, true);
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
