package X;

import java.net.Socket;
import java.util.concurrent.Callable;

/* renamed from: X.0Z3  reason: invalid class name */
public class AnonymousClass0Z3 implements Callable<Void> {
    public final /* synthetic */ AnonymousClass0Z4 A00;
    public final /* synthetic */ Socket A01;
    public final /* synthetic */ Socket A02;

    public AnonymousClass0Z3(AnonymousClass0Z4 r1, Socket socket, Socket socket2) {
        this.A00 = r1;
        this.A01 = socket;
        this.A02 = socket2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Void call() throws Exception {
        AnonymousClass0Z4 r3 = this.A00;
        AnonymousClass0Z4.A00(r3, this.A01, r3.A02, this.A02);
        return null;
    }
}
