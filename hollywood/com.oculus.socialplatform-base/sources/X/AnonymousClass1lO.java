package X;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.1lO  reason: invalid class name */
public class AnonymousClass1lO implements AnonymousClass0H5 {
    public final /* synthetic */ C09901kJ A00;
    public final /* synthetic */ AnonymousClass0PZ A01;

    public AnonymousClass1lO(C09901kJ r1, AnonymousClass0PZ r2) {
        this.A00 = r1;
        this.A01 = r2;
    }

    @Override // X.AnonymousClass0H5
    public final void ABB(OutputStream outputStream) throws IOException {
        this.A00.A02.A00(this.A01.A0A(), outputStream);
    }
}
