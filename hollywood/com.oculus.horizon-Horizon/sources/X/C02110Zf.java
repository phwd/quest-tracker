package X;

import java.util.HashMap;

/* renamed from: X.0Zf  reason: invalid class name and case insensitive filesystem */
public class C02110Zf extends HashMap<Integer, EnumC02120Zg> {
    public C02110Zf() {
        EnumC02120Zg[] values = EnumC02120Zg.values();
        for (EnumC02120Zg r1 : values) {
            put(Integer.valueOf(r1.mValue), r1);
        }
    }
}
