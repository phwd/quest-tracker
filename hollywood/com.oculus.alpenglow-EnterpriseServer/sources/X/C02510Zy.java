package X;

/* renamed from: X.0Zy  reason: invalid class name and case insensitive filesystem */
public class C02510Zy extends AnonymousClass0nB {
    public static final C02510Zy A00 = new C02510Zy();

    public static AnonymousClass0F3 A00(String str, AnonymousClass0aI r11, int i) {
        return new AnonymousClass0F3(str, r11, null, null, null, null, i, null, true);
    }

    @Override // X.AnonymousClass0nB
    public final Object A0C(AbstractC02570aK r9, Object[] objArr) {
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
        return new C05880lZ(obj, longValue, longValue2, intValue, intValue2);
    }

    @Override // X.AnonymousClass0nB
    public final boolean A0I() {
        return true;
    }

    @Override // X.AnonymousClass0nB
    public final String A0D() {
        return C05880lZ.class.getName();
    }

    @Override // X.AnonymousClass0nB
    public final AbstractC01680Ku[] A0M(C01260Fu r8) {
        AnonymousClass0aI A03 = r8.A03(Integer.TYPE);
        AnonymousClass0aI A032 = r8.A03(Long.TYPE);
        return new AnonymousClass0F3[]{A00("sourceRef", r8.A03(Object.class), 0), A00("byteOffset", A032, 1), A00("charOffset", A032, 2), A00("lineNr", A03, 3), A00("columnNr", A03, 4)};
    }
}
