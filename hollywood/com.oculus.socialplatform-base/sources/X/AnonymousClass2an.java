package X;

import com.facebook.acra.CrashTimeDataCollector;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.2an  reason: invalid class name */
public class AnonymousClass2an implements AnonymousClass2bJ {
    public int A00 = 1;
    public AnonymousClass2b1 A01 = null;
    public AbstractC14992aj A02;
    public int A03;
    public int A04;
    public AnonymousClass2bJ A05 = null;
    public Integer A06 = AnonymousClass007.A00;
    public List<AnonymousClass2bJ> A07 = new ArrayList();
    public List<AnonymousClass2an> A08 = new ArrayList();
    public boolean A09 = false;
    public boolean A0A = false;
    public boolean A0B = false;

    public final void A00() {
        this.A08.clear();
        this.A07.clear();
        this.A0B = false;
        this.A04 = 0;
        this.A0A = false;
        this.A09 = false;
    }

    public final void A01(int i) {
        if (!this.A0B) {
            this.A0B = true;
            this.A04 = i;
            for (AnonymousClass2bJ r0 : this.A07) {
                r0.AAs(r0);
            }
        }
    }

    public final void A02(AnonymousClass2bJ r2) {
        this.A07.add(r2);
        if (this.A0B) {
            r2.AAs(r2);
        }
    }

    @Override // X.AnonymousClass2bJ
    public final void AAs(AnonymousClass2bJ r7) {
        for (AnonymousClass2an r0 : this.A08) {
            if (!r0.A0B) {
                return;
            }
        }
        this.A0A = true;
        AnonymousClass2bJ r02 = this.A05;
        if (r02 != null) {
            r02.AAs(this);
        }
        if (this.A09) {
            this.A02.AAs(this);
            return;
        }
        AnonymousClass2an r4 = null;
        int i = 0;
        for (AnonymousClass2an r1 : this.A08) {
            if (!(r1 instanceof AnonymousClass2b1)) {
                i++;
                r4 = r1;
            }
        }
        if (r4 != null && i == 1 && r4.A0B) {
            AnonymousClass2b1 r2 = this.A01;
            if (r2 != null) {
                if (r2.A0B) {
                    this.A03 = this.A00 * r2.A04;
                } else {
                    return;
                }
            }
            A01(r4.A04 + this.A03);
        }
        AnonymousClass2bJ r03 = this.A05;
        if (r03 != null) {
            r03.AAs(this);
        }
    }

    public final String toString() {
        String str;
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append(this.A02.A02.A0j);
        sb.append(":");
        Integer num = this.A06;
        if (num != null) {
            switch (num.intValue()) {
                case 1:
                    str = "HORIZONTAL_DIMENSION";
                    break;
                case 2:
                    str = "VERTICAL_DIMENSION";
                    break;
                case 3:
                    str = "LEFT";
                    break;
                case 4:
                    str = "RIGHT";
                    break;
                case 5:
                    str = "TOP";
                    break;
                case 6:
                    str = "BOTTOM";
                    break;
                case 7:
                    str = "BASELINE";
                    break;
                default:
                    str = CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
                    break;
            }
        } else {
            str = "null";
        }
        sb.append(str);
        sb.append("(");
        if (this.A0B) {
            obj = Integer.valueOf(this.A04);
        } else {
            obj = "unresolved";
        }
        sb.append(obj);
        sb.append(") <t=");
        sb.append(this.A08.size());
        sb.append(":d=");
        sb.append(this.A07.size());
        sb.append(">");
        return sb.toString();
    }

    public AnonymousClass2an(AbstractC14992aj r4) {
        this.A02 = r4;
    }
}
