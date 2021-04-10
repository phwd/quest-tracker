package X;

import com.google.gson.internal.bind.TypeAdapters$26;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: X.0dO  reason: invalid class name */
public class AnonymousClass0dO extends AnonymousClass13Y<Timestamp> {
    public final /* synthetic */ AnonymousClass13Y A00;
    public final /* synthetic */ TypeAdapters$26 A01;

    public AnonymousClass0dO(TypeAdapters$26 typeAdapters$26, AnonymousClass13Y r2) {
        this.A01 = typeAdapters$26;
        this.A00 = r2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Timestamp A02(AnonymousClass14I r4) throws IOException {
        Date date = (Date) this.A00.A02(r4);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r2, Timestamp timestamp) throws IOException {
        this.A00.A03(r2, timestamp);
    }
}
