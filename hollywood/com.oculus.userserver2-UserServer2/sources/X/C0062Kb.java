package X;

import java.util.Calendar;
import java.util.GregorianCalendar;

/* renamed from: X.Kb  reason: case insensitive filesystem */
public class C0062Kb implements AbstractC0237hg {
    public final /* synthetic */ hh A00;
    public final /* synthetic */ Class A01 = Calendar.class;
    public final /* synthetic */ Class A02 = GregorianCalendar.class;

    public C0062Kb(hh hhVar) {
        this.A00 = hhVar;
    }

    @Override // X.AbstractC0237hg
    public final <T> hh<T> A1F(C0246hr hrVar, h6<T> h6Var) {
        Class<? super T> cls = h6Var.rawType;
        if (cls == this.A01 || cls == this.A02) {
            return this.A00;
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Factory[type=");
        sb.append(this.A01.getName());
        sb.append("+");
        sb.append(this.A02.getName());
        sb.append(",adapter=");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }
}
