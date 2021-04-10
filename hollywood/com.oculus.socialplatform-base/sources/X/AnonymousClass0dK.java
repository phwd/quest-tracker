package X;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0dK  reason: invalid class name */
public class AnonymousClass0dK extends AnonymousClass13Y<AnonymousClass13R> {
    /* access modifiers changed from: private */
    /* renamed from: A01 */
    public final void A03(AnonymousClass14L r4, AnonymousClass13R r5) throws IOException {
        String str;
        if (r5 == null || (r5 instanceof AnonymousClass0eU)) {
            r4.A09();
        } else if (r5 instanceof AnonymousClass0eS) {
            AnonymousClass0eS r52 = (AnonymousClass0eS) r5;
            Object obj = r52.A00;
            if (obj instanceof Number) {
                r4.A0B(r52.A02());
            } else if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                AnonymousClass14L.A03(r4);
                AnonymousClass14L.A02(r4);
                Writer writer = r4.A07;
                if (booleanValue) {
                    str = "true";
                } else {
                    str = "false";
                }
                writer.write(str);
            } else {
                r4.A0D(r52.A03());
            }
        } else if (r5 instanceof AnonymousClass0eW) {
            r4.A05();
            Iterator<AnonymousClass13R> it = ((AnonymousClass0eW) r5).iterator();
            while (it.hasNext()) {
                A03(r4, it.next());
            }
            r4.A07();
        } else if (r5 instanceof AnonymousClass0eT) {
            r4.A06();
            for (Map.Entry<String, AnonymousClass13R> entry : ((AnonymousClass0eT) r5).A00.entrySet()) {
                r4.A0C(entry.getKey());
                A03(r4, entry.getValue());
            }
            r4.A08();
        } else {
            StringBuilder sb = new StringBuilder("Couldn't write ");
            sb.append(r5.getClass());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AnonymousClass13R A02(AnonymousClass14I r5) throws IOException {
        switch (r5.A0G().intValue()) {
            case 0:
                AnonymousClass0eW r2 = new AnonymousClass0eW();
                r5.A0L();
                while (r5.A0R()) {
                    r2.A00.add(A02(r5));
                }
                r5.A0N();
                return r2;
            case 1:
            case 3:
            case 4:
            default:
                throw new IllegalArgumentException();
            case 2:
                AnonymousClass0eT r3 = new AnonymousClass0eT();
                r5.A0M();
                while (r5.A0R()) {
                    r3.A00.put(r5.A0I(), A02(r5));
                }
                r5.A0O();
                return r3;
            case 5:
                return new AnonymousClass0eS(r5.A0J());
            case 6:
                return new AnonymousClass0eS(new C057713o(r5.A0J()));
            case 7:
                return new AnonymousClass0eS(Boolean.valueOf(r5.A0S()));
            case 8:
                r5.A0P();
                return AnonymousClass0eU.A00;
        }
    }
}
