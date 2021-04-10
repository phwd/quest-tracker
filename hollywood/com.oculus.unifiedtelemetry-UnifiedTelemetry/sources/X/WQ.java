package X;

public class WQ extends AbstractC0262Ym {
    public static final WQ A00 = new WQ();

    public static AnonymousClass8I A00(String str, AbstractC0224Wl wl, int i) {
        return new AnonymousClass8I(str, wl, null, null, null, null, i, null, true);
    }

    @Override // X.AbstractC0262Ym
    public final Object A0C(AbstractC0226Wn wn, Object[] objArr) {
        long longValue;
        long longValue2;
        int intValue;
        int intValue2;
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        if (obj2 == null) {
            longValue = 0;
        } else {
            longValue = ((Number) obj2).longValue();
        }
        Object obj3 = objArr[2];
        if (obj3 == null) {
            longValue2 = 0;
        } else {
            longValue2 = ((Number) obj3).longValue();
        }
        Object obj4 = objArr[3];
        if (obj4 == null) {
            intValue = 0;
        } else {
            intValue = ((Number) obj4).intValue();
        }
        Object obj5 = objArr[4];
        if (obj5 == null) {
            intValue2 = 0;
        } else {
            intValue2 = ((Number) obj5).intValue();
        }
        return new pw(obj, longValue, longValue2, intValue, intValue2);
    }

    @Override // X.AbstractC0262Ym
    public final boolean A0I() {
        return true;
    }

    @Override // X.AbstractC0262Ym
    public final String A0D() {
        return pw.class.getName();
    }

    @Override // X.AbstractC0262Ym
    public final AbstractC0073Cr[] A0M(AnonymousClass8M r8) {
        AbstractC0224Wl A03 = r8.A03(Integer.TYPE);
        AbstractC0224Wl A032 = r8.A03(Long.TYPE);
        return new AnonymousClass8I[]{A00("sourceRef", r8.A03(Object.class), 0), A00("byteOffset", A032, 1), A00("charOffset", A032, 2), A00("lineNr", A03, 3), A00("columnNr", A03, 4)};
    }
}
