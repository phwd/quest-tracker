package X;

/* renamed from: X.0ht  reason: invalid class name and case insensitive filesystem */
public class C02050ht extends AbstractC04300pk {
    public static final C02050ht A00 = new C02050ht();

    public static AnonymousClass0HD A00(String str, AbstractC02190iF r11, int i) {
        return new AnonymousClass0HD(str, r11, null, null, null, null, i, null, true);
    }

    @Override // X.AbstractC04300pk
    public final Object A0C(AbstractC02210iH r9, Object[] objArr) {
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
        return new AnonymousClass0o8(obj, longValue, longValue2, intValue, intValue2);
    }

    @Override // X.AbstractC04300pk
    public final boolean A0I() {
        return true;
    }

    @Override // X.AbstractC04300pk
    public final String A0D() {
        return AnonymousClass0o8.class.getName();
    }

    @Override // X.AbstractC04300pk
    public final AbstractC01170Rz[] A0M(AnonymousClass0HU r8) {
        AbstractC02190iF A03 = r8.A03(Integer.TYPE);
        AbstractC02190iF A032 = r8.A03(Long.TYPE);
        return new AnonymousClass0HD[]{A00("sourceRef", r8.A03(Object.class), 0), A00("byteOffset", A032, 1), A00("charOffset", A032, 2), A00("lineNr", A03, 3), A00("columnNr", A03, 4)};
    }
}
