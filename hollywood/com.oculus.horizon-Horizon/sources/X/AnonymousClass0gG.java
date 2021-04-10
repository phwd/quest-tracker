package X;

/* renamed from: X.0gG  reason: invalid class name */
public class AnonymousClass0gG extends AnonymousClass0lG {
    public static final AnonymousClass0gG A00 = new AnonymousClass0gG();

    public static AnonymousClass08T A00(String str, AbstractC04000gb r11, int i) {
        return new AnonymousClass08T(str, r11, null, null, null, null, i, null, true);
    }

    @Override // X.AnonymousClass0lG
    public final Object A0C(AbstractC04020gg r9, Object[] objArr) {
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
        return new AnonymousClass0jc(obj, longValue, longValue2, intValue, intValue2);
    }

    @Override // X.AnonymousClass0lG
    public final boolean A0I() {
        return true;
    }

    @Override // X.AnonymousClass0lG
    public final String A0D() {
        return AnonymousClass0jc.class.getName();
    }

    @Override // X.AnonymousClass0lG
    public final AnonymousClass0HD[] A0M(AnonymousClass08X r7) {
        AbstractC04000gb A03 = r7.A03(Integer.TYPE);
        AbstractC04000gb A032 = r7.A03(Long.TYPE);
        return new AnonymousClass08T[]{A00("sourceRef", r7.A03(Object.class), 0), A00("byteOffset", A032, 1), A00("charOffset", A032, 2), A00("lineNr", A03, 3), A00("columnNr", A03, 4)};
    }
}
