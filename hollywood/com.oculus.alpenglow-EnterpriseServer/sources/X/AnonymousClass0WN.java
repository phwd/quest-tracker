package X;

import com.google.gson.internal.bind.TypeAdapters$26;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: X.0WN  reason: invalid class name */
public class AnonymousClass0WN extends AnonymousClass0Bd<Timestamp> {
    public final /* synthetic */ AnonymousClass0Bd A00;
    public final /* synthetic */ TypeAdapters$26 A01;

    public AnonymousClass0WN(TypeAdapters$26 typeAdapters$26, AnonymousClass0Bd r2) {
        this.A01 = typeAdapters$26;
        this.A00 = r2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Timestamp A02(AnonymousClass0Fo r4) throws IOException {
        Date date = (Date) this.A00.A02(r4);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r2, Timestamp timestamp) throws IOException {
        this.A00.A03(r2, timestamp);
    }
}
