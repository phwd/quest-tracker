package X;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0WJ  reason: invalid class name */
public class AnonymousClass0WJ extends AnonymousClass0Bd<AnonymousClass0AU> {
    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final void A03(AnonymousClass0GL r4, AnonymousClass0AU r5) throws IOException {
        String str;
        String str2;
        if (r5 == null || (r5 instanceof AnonymousClass0XT)) {
            r4.A0A();
        } else if (r5 instanceof AnonymousClass0XR) {
            AnonymousClass0XR r52 = (AnonymousClass0XR) r5;
            Object obj = r52.A00;
            boolean z = obj instanceof Number;
            if (z) {
                r4.A0C(r52.A02());
            } else if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                AnonymousClass0GL.A04(r4);
                AnonymousClass0GL.A03(r4);
                Writer writer = r4.A08;
                if (booleanValue) {
                    str2 = "true";
                } else {
                    str2 = "false";
                }
                writer.write(str2);
            } else {
                if (z) {
                    str = r52.A02().toString();
                } else {
                    str = (String) obj;
                }
                r4.A0E(str);
            }
        } else if (r5 instanceof AnonymousClass0XV) {
            r4.A06();
            Iterator<AnonymousClass0AU> it = ((AnonymousClass0XV) r5).iterator();
            while (it.hasNext()) {
                A03(r4, it.next());
            }
            r4.A08();
        } else if (r5 instanceof AnonymousClass0XS) {
            r4.A07();
            for (Map.Entry<String, AnonymousClass0AU> entry : ((AnonymousClass0XS) r5).A00.entrySet()) {
                r4.A0D(entry.getKey());
                A03(r4, entry.getValue());
            }
            r4.A09();
        } else {
            throw new IllegalArgumentException("Couldn't write " + r5.getClass());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AnonymousClass0AU A02(AnonymousClass0Fo r5) throws IOException {
        switch (r5.A0D().intValue()) {
            case 0:
                AnonymousClass0XV r2 = new AnonymousClass0XV();
                r5.A0H();
                while (r5.A0N()) {
                    r2.A00.add(A02(r5));
                }
                r5.A0J();
                return r2;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException();
            case 2:
                AnonymousClass0XS r3 = new AnonymousClass0XS();
                r5.A0I();
                while (r5.A0N()) {
                    r3.A00.put(r5.A0E(), A02(r5));
                }
                r5.A0K();
                return r3;
            case 5:
                return new AnonymousClass0XR(r5.A0F());
            case 6:
                return new AnonymousClass0XR(new AnonymousClass0DB(r5.A0F()));
            case 7:
                return new AnonymousClass0XR(Boolean.valueOf(r5.A0O()));
            case 8:
                r5.A0L();
                return AnonymousClass0XT.A00;
        }
    }
}
