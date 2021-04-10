package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Zk  reason: invalid class name and case insensitive filesystem */
public final class C02140Zk {
    public final Integer A00;
    public final Map<AnonymousClass0WP<?>, Object> A01 = new HashMap();

    public static <T> void A00(AnonymousClass0WN r3, AnonymousClass0WP<T> r4) throws IOException {
        byte b = r4.A00;
        short s = r4.A01;
        if (b == 2) {
            r3.A00 = Byte.valueOf(b);
            r3.A01 = Short.valueOf(s);
            return;
        }
        AnonymousClass0WN.A00(r3, b, s, (byte) -1);
    }

    public C02140Zk(Integer num) {
        this.A00 = num;
    }

    public final void A01(AnonymousClass0WN r8) throws IOException {
        Map<AnonymousClass0WP<?>, Object> map;
        AnonymousClass0WP<String> r1;
        Object obj;
        int i;
        switch (this.A00.intValue()) {
            case 0:
                r8.A02();
                map = this.A01;
                AnonymousClass0WP<Long> r12 = C02130Zi.A0P;
                if (map.containsKey(r12) && map.get(r12) != null) {
                    A00(r8, r12);
                    r8.A04(((Number) map.get(r12)).longValue());
                }
                AnonymousClass0WP<String> r13 = C02130Zi.A0O;
                if (map.containsKey(r13) && map.get(r13) != null) {
                    A00(r8, r13);
                    r8.A05((String) map.get(r13));
                }
                AnonymousClass0WP<Long> r14 = C02130Zi.A01;
                if (map.containsKey(r14) && map.get(r14) != null) {
                    A00(r8, r14);
                    r8.A04(((Number) map.get(r14)).longValue());
                }
                AnonymousClass0WP<Long> r15 = C02130Zi.A09;
                if (map.containsKey(r15) && map.get(r15) != null) {
                    A00(r8, r15);
                    r8.A04(((Number) map.get(r15)).longValue());
                }
                AnonymousClass0WP<Integer> r16 = C02130Zi.A0L;
                if (map.containsKey(r16) && map.get(r16) != null) {
                    A00(r8, r16);
                    int intValue = ((Number) map.get(r16)).intValue();
                    AnonymousClass0WN.A01(r8, (intValue >> 31) ^ (intValue << 1));
                }
                AnonymousClass0WP<Boolean> r17 = C02130Zi.A0J;
                if (map.containsKey(r17) && map.get(r17) != null) {
                    A00(r8, r17);
                    r8.A06(((Boolean) map.get(r17)).booleanValue());
                }
                AnonymousClass0WP<Boolean> r18 = C02130Zi.A0G;
                if (map.containsKey(r18) && map.get(r18) != null) {
                    A00(r8, r18);
                    r8.A06(((Boolean) map.get(r18)).booleanValue());
                }
                AnonymousClass0WP<String> r19 = C02130Zi.A07;
                if (map.containsKey(r19) && map.get(r19) != null) {
                    A00(r8, r19);
                    r8.A05((String) map.get(r19));
                }
                AnonymousClass0WP<Boolean> r110 = C02130Zi.A0E;
                if (map.containsKey(r110) && map.get(r110) != null) {
                    A00(r8, r110);
                    r8.A06(((Boolean) map.get(r110)).booleanValue());
                }
                AnonymousClass0WP<Integer> r111 = C02130Zi.A0I;
                if (map.containsKey(r111) && map.get(r111) != null) {
                    A00(r8, r111);
                    int intValue2 = ((Number) map.get(r111)).intValue();
                    AnonymousClass0WN.A01(r8, (intValue2 >> 31) ^ (intValue2 << 1));
                }
                AnonymousClass0WP<Integer> r112 = C02130Zi.A0H;
                if (map.containsKey(r112) && map.get(r112) != null) {
                    A00(r8, r112);
                    int intValue3 = ((Number) map.get(r112)).intValue();
                    AnonymousClass0WN.A01(r8, (intValue3 >> 31) ^ (intValue3 << 1));
                }
                AnonymousClass0WP<Long> r113 = C02130Zi.A03;
                if (map.containsKey(r113) && map.get(r113) != null) {
                    A00(r8, r113);
                    r8.A04(((Number) map.get(r113)).longValue());
                }
                AnonymousClass0WP<String> r114 = C02130Zi.A02;
                if (map.containsKey(r114) && map.get(r114) != null) {
                    A00(r8, r114);
                    r8.A05((String) map.get(r114));
                }
                AnonymousClass0WP<List<Integer>> r115 = C02130Zi.A0N;
                if (map.containsKey(r115) && map.get(r115) != null) {
                    A00(r8, r115);
                    List<Number> list = (List) map.get(r115);
                    r8.A03((byte) 8, list.size());
                    for (Number number : list) {
                        int intValue4 = number.intValue();
                        AnonymousClass0WN.A01(r8, (intValue4 >> 31) ^ (intValue4 << 1));
                    }
                }
                AnonymousClass0WP<String> r116 = C02130Zi.A05;
                if (map.containsKey(r116) && map.get(r116) != null) {
                    A00(r8, r116);
                    r8.A05((String) map.get(r116));
                }
                AnonymousClass0WP<Long> r117 = C02130Zi.A00;
                if (map.containsKey(r117) && map.get(r117) != null) {
                    A00(r8, r117);
                    r8.A04(((Number) map.get(r117)).longValue());
                }
                AnonymousClass0WP<Boolean> r118 = C02130Zi.A0K;
                if (map.containsKey(r118) && map.get(r118) != null) {
                    A00(r8, r118);
                    r8.A06(((Boolean) map.get(r118)).booleanValue());
                }
                AnonymousClass0WP<byte[]> r119 = C02130Zi.A06;
                if (map.containsKey(r119) && map.get(r119) != null) {
                    A00(r8, r119);
                    byte[] bArr = (byte[]) map.get(r119);
                    int length = bArr.length;
                    AnonymousClass0WN.A01(r8, length);
                    r8.A04.write(bArr, 0, length);
                }
                AnonymousClass0WP<String> r120 = C02130Zi.A0M;
                if (map.containsKey(r120) && map.get(r120) != null) {
                    A00(r8, r120);
                    r8.A05((String) map.get(r120));
                }
                AnonymousClass0WP<String> r121 = C02130Zi.A08;
                if (map.containsKey(r121) && map.get(r121) != null) {
                    A00(r8, r121);
                    r8.A05((String) map.get(r121));
                }
                AnonymousClass0WP<Long> r122 = C02130Zi.A0F;
                if (map.containsKey(r122) && map.get(r122) != null) {
                    A00(r8, r122);
                    r8.A04(((Number) map.get(r122)).longValue());
                }
                AnonymousClass0WP<Byte> r123 = C02130Zi.A04;
                if (map.containsKey(r123) && map.get(r123) != null) {
                    A00(r8, r123);
                    r8.A04.write(((Number) map.get(r123)).byteValue());
                }
                AnonymousClass0WP<Long> r124 = C02130Zi.A0A;
                if (map.containsKey(r124) && map.get(r124) != null) {
                    A00(r8, r124);
                    r8.A04(((Number) map.get(r124)).longValue());
                }
                AnonymousClass0WP<String> r125 = C02130Zi.A0B;
                if (map.containsKey(r125) && map.get(r125) != null) {
                    A00(r8, r125);
                    r8.A05((String) map.get(r125));
                }
                AnonymousClass0WP<String> r126 = C02130Zi.A0C;
                if (map.containsKey(r126) && map.get(r126) != null) {
                    A00(r8, r126);
                    r8.A05((String) map.get(r126));
                }
                r1 = C02130Zi.A0D;
                if (map.containsKey(r1) && map.get(r1) != null) {
                    A00(r8, r1);
                    r8.A05((String) map.get(r1));
                    break;
                }
            case 1:
                r8.A02();
                Map<AnonymousClass0WP<?>, Object> map2 = this.A01;
                AnonymousClass0WP<String> r127 = C02130Zi.A0o;
                if (map2.containsKey(r127) && map2.get(r127) != null) {
                    A00(r8, r127);
                    r8.A05((String) map2.get(r127));
                }
                AnonymousClass0WP<Long> r128 = C02130Zi.A0j;
                if (map2.containsKey(r128) && map2.get(r128) != null) {
                    A00(r8, r128);
                    r8.A04(((Number) map2.get(r128)).longValue());
                    AnonymousClass0WP<Integer> r129 = C02130Zi.A0k;
                    if (map2.containsKey(r129) && map2.get(r129) != null) {
                        A00(r8, r129);
                        int intValue5 = ((Number) map2.get(r129)).intValue();
                        AnonymousClass0WN.A01(r8, (intValue5 >> 31) ^ (intValue5 << 1));
                    }
                    AnonymousClass0WP<Integer> r130 = C02130Zi.A0e;
                    if (map2.containsKey(r130) && map2.get(r130) != null) {
                        A00(r8, r130);
                        int intValue6 = ((Number) map2.get(r130)).intValue();
                        AnonymousClass0WN.A01(r8, (intValue6 >> 31) ^ (intValue6 << 1));
                    }
                    AnonymousClass0WP<String> r131 = C02130Zi.A0h;
                    if (map2.containsKey(r131) && map2.get(r131) != null) {
                        A00(r8, r131);
                        r8.A05((String) map2.get(r131));
                    }
                    AnonymousClass0WP<String> r132 = C02130Zi.A0m;
                    if (map2.containsKey(r132) && map2.get(r132) != null) {
                        A00(r8, r132);
                        r8.A05((String) map2.get(r132));
                    }
                    AnonymousClass0WP<Integer> r133 = C02130Zi.A0n;
                    if (map2.containsKey(r133) && map2.get(r133) != null) {
                        A00(r8, r133);
                        int intValue7 = ((Number) map2.get(r133)).intValue();
                        AnonymousClass0WN.A01(r8, (intValue7 >> 31) ^ (intValue7 << 1));
                    }
                    AnonymousClass0WP<String> r134 = C02130Zi.A0f;
                    if (map2.containsKey(r134) && map2.get(r134) != null) {
                        A00(r8, r134);
                        r8.A05((String) map2.get(r134));
                    }
                    AnonymousClass0WP<String> r135 = C02130Zi.A0g;
                    if (map2.containsKey(r135) && map2.get(r135) != null) {
                        A00(r8, r135);
                        r8.A05((String) map2.get(r135));
                    }
                    AnonymousClass0WP<String> r136 = C02130Zi.A0l;
                    if (map2.containsKey(r136) && map2.get(r136) != null) {
                        A00(r8, r136);
                        r8.A05((String) map2.get(r136));
                    }
                    AnonymousClass0WP<Long> r137 = C02130Zi.A0i;
                    if (map2.containsKey(r137) && map2.get(r137) != null) {
                        A00(r8, r137);
                        r8.A04(((Number) map2.get(r137)).longValue());
                    }
                    AnonymousClass0WP<Long> r138 = C02130Zi.A0p;
                    if (map2.containsKey(r138) && map2.get(r138) != null) {
                        A00(r8, r138);
                        r8.A04(((Number) map2.get(r138)).longValue());
                        break;
                    }
                } else {
                    throw new IOException("Required field 'GetIrisDiffs.lastSeqId' was not present!");
                }
                break;
            case 2:
                r8.A02();
                map = this.A01;
                AnonymousClass0WP<String> r139 = C02130Zi.A0r;
                if (map.containsKey(r139) && map.get(r139) != null) {
                    A00(r8, r139);
                    r8.A05((String) map.get(r139));
                }
                AnonymousClass0WP<String> r140 = C02130Zi.A0q;
                if (map.containsKey(r140) && map.get(r140) != null) {
                    A00(r8, r140);
                    r8.A05((String) map.get(r140));
                }
                r1 = C02130Zi.A0s;
                A00(r8, r1);
                r8.A05((String) map.get(r1));
                break;
            case 3:
                r8.A02();
                Map<AnonymousClass0WP<?>, Object> map3 = this.A01;
                AnonymousClass0WP<String> r141 = C02130Zi.A0S;
                if (map3.containsKey(r141) && map3.get(r141) != null) {
                    A00(r8, r141);
                    r8.A05((String) map3.get(r141));
                }
                AnonymousClass0WP<Integer> r142 = C02130Zi.A0Q;
                if (map3.containsKey(r142) && map3.get(r142) != null) {
                    A00(r8, r142);
                    int intValue8 = ((Number) map3.get(r142)).intValue();
                    AnonymousClass0WN.A01(r8, (intValue8 >> 31) ^ (intValue8 << 1));
                }
                AnonymousClass0WP<byte[]> r143 = C02130Zi.A0R;
                if (map3.containsKey(r143) && map3.get(r143) != null) {
                    A00(r8, r143);
                    obj = map3.get(r143);
                    byte[] bArr2 = (byte[]) obj;
                    int length2 = bArr2.length;
                    AnonymousClass0WN.A01(r8, length2);
                    r8.A04.write(bArr2, 0, length2);
                    break;
                }
            case 4:
                r8.A02();
                Map<AnonymousClass0WP<?>, Object> map4 = this.A01;
                AnonymousClass0WP<String> r144 = C02130Zi.A0U;
                if (map4.containsKey(r144) && map4.get(r144) != null) {
                    A00(r8, r144);
                    r8.A05((String) map4.get(r144));
                }
                AnonymousClass0WP<String> r145 = C02130Zi.A0c;
                if (map4.containsKey(r145) && map4.get(r145) != null) {
                    A00(r8, r145);
                    r8.A05((String) map4.get(r145));
                }
                AnonymousClass0WP<String> r146 = C02130Zi.A0b;
                if (map4.containsKey(r146) && map4.get(r146) != null) {
                    A00(r8, r146);
                    r8.A05((String) map4.get(r146));
                }
                AnonymousClass0WP<C02140Zk> r147 = C02130Zi.A0V;
                if (map4.containsKey(r147) && map4.get(r147) != null) {
                    A00(r8, r147);
                    ((C02140Zk) map4.get(r147)).A01(r8);
                }
                AnonymousClass0WP<String> r148 = C02130Zi.A0Y;
                if (map4.containsKey(r148) && map4.get(r148) != null) {
                    A00(r8, r148);
                    r8.A05((String) map4.get(r148));
                }
                AnonymousClass0WP<List<byte[]>> r149 = C02130Zi.A0X;
                if (map4.containsKey(r149) && map4.get(r149) != null) {
                    A00(r8, r149);
                    List<byte[]> list2 = (List) map4.get(r149);
                    r8.A03((byte) 11, list2.size());
                    for (byte[] bArr3 : list2) {
                        int length3 = bArr3.length;
                        AnonymousClass0WN.A01(r8, length3);
                        r8.A04.write(bArr3, 0, length3);
                    }
                }
                AnonymousClass0WP<List<C02140Zk>> r150 = C02130Zi.A0Z;
                if (map4.containsKey(r150) && map4.get(r150) != null) {
                    A00(r8, r150);
                    List<C02140Zk> list3 = (List) map4.get(r150);
                    r8.A03((byte) 12, list3.size());
                    for (C02140Zk r0 : list3) {
                        r0.A01(r8);
                    }
                }
                AnonymousClass0WP<List<C02140Zk>> r151 = C02130Zi.A0W;
                if (map4.containsKey(r151) && map4.get(r151) != null) {
                    A00(r8, r151);
                    List<C02140Zk> list4 = (List) map4.get(r151);
                    r8.A03((byte) 12, list4.size());
                    for (C02140Zk r02 : list4) {
                        r02.A01(r8);
                    }
                }
                AnonymousClass0WP<String> r152 = C02130Zi.A0d;
                if (map4.containsKey(r152) && map4.get(r152) != null) {
                    A00(r8, r152);
                    r8.A05((String) map4.get(r152));
                }
                AnonymousClass0WP<Map<String, String>> r153 = C02130Zi.A0T;
                if (map4.containsKey(r153) && map4.get(r153) != null) {
                    A00(r8, r153);
                    Map map5 = (Map) map4.get(r153);
                    int size = map5.size();
                    if (size == 0) {
                        i = 0;
                    } else {
                        AnonymousClass0WN.A01(r8, size);
                        byte b = AnonymousClass0WN.A06[11];
                        i = (b << 4) | b;
                    }
                    r8.A04.write((byte) i);
                    for (Map.Entry entry : map5.entrySet()) {
                        r8.A05((String) entry.getKey());
                        r8.A05((String) entry.getValue());
                    }
                }
                AnonymousClass0WP<byte[]> r154 = C02130Zi.A0a;
                if (map4.containsKey(r154) && map4.get(r154) != null) {
                    A00(r8, r154);
                    obj = map4.get(r154);
                    byte[] bArr22 = (byte[]) obj;
                    int length22 = bArr22.length;
                    AnonymousClass0WN.A01(r8, length22);
                    r8.A04.write(bArr22, 0, length22);
                    break;
                }
            default:
                return;
        }
        r8.A04.write(0);
        List<Short> list5 = r8.A02;
        r8.A03 = list5.get(list5.size() - 1).shortValue();
        List<Short> list6 = r8.A02;
        r8.A03 = list6.remove(list6.size() - 1).shortValue();
    }
}
