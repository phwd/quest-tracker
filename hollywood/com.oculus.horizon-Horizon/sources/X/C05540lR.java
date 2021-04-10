package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import java.io.IOException;
import java.util.HashMap;

/* renamed from: X.0lR  reason: invalid class name and case insensitive filesystem */
public final class C05540lR {
    public final HashMap<String, AnonymousClass0HD> A00 = new HashMap<>();
    public final int A01;
    public final AnonymousClass0lG A02;
    public final AnonymousClass0HD[] A03;
    public final Object[] A04;

    public static C05540lR A00(AbstractC04020gg r7, AnonymousClass0lG r8, AnonymousClass0HD[] r9) throws C03990gZ {
        char c;
        int length = r9.length;
        AnonymousClass0HD[] r4 = new AnonymousClass0HD[length];
        Object[] objArr = null;
        for (int i = 0; i < length; i++) {
            AnonymousClass0HD r6 = r9[i];
            JsonDeserializer<Object> jsonDeserializer = r6._valueDeserializer;
            if (jsonDeserializer == null || jsonDeserializer == AnonymousClass0HD.A01) {
                r6 = r6.A02(r7.A05(r6._type, r6));
            }
            r4[i] = r6;
            JsonDeserializer<Object> jsonDeserializer2 = r6._valueDeserializer;
            if (jsonDeserializer2 == AnonymousClass0HD.A01 || jsonDeserializer2 == null || (c = jsonDeserializer2.A08()) == null) {
                if (!r6._type._class.isPrimitive()) {
                    continue;
                } else {
                    Class<?> cls = r6._type._class;
                    if (cls == Integer.TYPE) {
                        c = 0;
                    } else if (cls == Long.TYPE) {
                        c = 0L;
                    } else if (cls == Boolean.TYPE) {
                        c = Boolean.FALSE;
                    } else if (cls == Double.TYPE) {
                        c = Double.valueOf((double) OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY);
                    } else if (cls == Float.TYPE) {
                        c = Float.valueOf(0.0f);
                    } else if (cls == Byte.TYPE) {
                        c = (byte) 0;
                    } else if (cls == Short.TYPE) {
                        c = (short) 0;
                    } else if (cls == Character.TYPE) {
                        c = (char) 0;
                    } else {
                        throw new IllegalArgumentException(AnonymousClass006.A07("Class ", cls.getName(), " is not a primitive type"));
                    }
                    if (c == null) {
                    }
                }
            }
            if (objArr == null) {
                objArr = new Object[length];
            }
            objArr[i] = c;
        }
        return new C05540lR(r8, r4, objArr);
    }

    public final C05560lT A01(AbstractC04100gp r6, AbstractC04020gg r7, C05530lQ r8) {
        C05560lT r4 = new C05560lT(r6, r7, this.A01, r8);
        AnonymousClass0HD[] r3 = this.A03;
        if (r3 != null) {
            for (AnonymousClass0HD r0 : r3) {
                if (r0 != null) {
                    r4.A03.A0H(r0.A04());
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
        }
        return r4;
    }

    public final Object A02(AbstractC04020gg r7, C05560lT r8) throws IOException {
        Object obj;
        Object obj2;
        AnonymousClass0lG r5 = this.A02;
        Object[] objArr = this.A04;
        if (objArr != null) {
            Object[] objArr2 = r8.A05;
            int length = objArr2.length;
            for (int i = 0; i < length; i++) {
                if (objArr2[i] == null && (obj2 = objArr[i]) != null) {
                    objArr2[i] = obj2;
                }
            }
        }
        Object A0C = r5.A0C(r7, r8.A05);
        C05530lQ r3 = r8.A04;
        if (!(r3 == null || (obj = r8.A01) == null)) {
            r7.A0B(obj, r3.generator).A00(A0C);
            AnonymousClass0HD r2 = r3.idProperty;
            if (r2 != null) {
                A0C = r2.A07(A0C, r8.A01);
            }
        }
        for (AbstractC05550lS r0 = r8.A00; r0 != null; r0 = r0.A00) {
            r0.A00(A0C);
        }
        return A0C;
    }

    public C05540lR(AnonymousClass0lG r7, AnonymousClass0HD[] r8, Object[] objArr) {
        this.A02 = r7;
        int length = r8.length;
        this.A01 = length;
        AnonymousClass0HD[] r4 = null;
        for (int i = 0; i < length; i++) {
            AnonymousClass0HD r2 = r8[i];
            this.A00.put(r2._propName, r2);
            if (r2.A04() != null) {
                r4 = r4 == null ? new AnonymousClass0HD[length] : r4;
                r4[i] = r2;
            }
        }
        this.A04 = objArr;
        this.A03 = r4;
    }
}
