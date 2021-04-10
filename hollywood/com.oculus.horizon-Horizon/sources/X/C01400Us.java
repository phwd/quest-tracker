package X;

import com.google.gson.internal.bind.TypeAdapters$26;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: X.0Us  reason: invalid class name and case insensitive filesystem */
public class C01400Us extends AnonymousClass0yl<Timestamp> {
    public final /* synthetic */ AnonymousClass0yl A00;
    public final /* synthetic */ TypeAdapters$26 A01;

    public C01400Us(TypeAdapters$26 typeAdapters$26, AnonymousClass0yl r2) {
        this.A01 = typeAdapters$26;
        this.A00 = r2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0yl
    public final Timestamp A02(C09120zR r4) throws IOException {
        Date date = (Date) this.A00.A02(r4);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0zU, java.lang.Object] */
    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r2, Timestamp timestamp) throws IOException {
        this.A00.A03(r2, timestamp);
    }
}
