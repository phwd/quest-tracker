package X;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: X.Sf  reason: case insensitive filesystem */
public class C0146Sf extends AbstractC0131Ob<Timestamp> {
    public final /* synthetic */ AbstractC0131Ob A00;
    public final /* synthetic */ Se A01;

    public C0146Sf(Se se, AbstractC0131Ob ob) {
        this.A01 = se;
        this.A00 = ob;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Timestamp A02(lk lkVar) throws IOException {
        Date date = (Date) this.A00.A02(lkVar);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Timestamp timestamp) throws IOException {
        this.A00.A03(mmVar, timestamp);
    }
}
