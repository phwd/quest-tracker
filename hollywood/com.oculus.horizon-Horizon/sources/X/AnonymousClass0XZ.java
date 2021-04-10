package X;

import java.net.Socket;
import java.util.concurrent.Callable;

/* renamed from: X.0XZ  reason: invalid class name */
public class AnonymousClass0XZ implements Callable<Socket> {
    public final /* synthetic */ int A00;
    public final /* synthetic */ AbstractC01750Xb A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ Socket A03;

    public AnonymousClass0XZ(AbstractC01750Xb r1, Socket socket, String str, int i) {
        this.A01 = r1;
        this.A03 = socket;
        this.A02 = str;
        this.A00 = i;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Socket call() throws Exception {
        return this.A01.A01(this.A03, this.A02, this.A00);
    }
}
