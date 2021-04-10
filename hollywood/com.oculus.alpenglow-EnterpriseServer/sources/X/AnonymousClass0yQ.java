package X;

import java.net.Socket;
import java.util.concurrent.Callable;

/* renamed from: X.0yQ  reason: invalid class name */
public class AnonymousClass0yQ implements Callable<Void> {
    public final /* synthetic */ C08670xd A00;
    public final /* synthetic */ Socket A01;
    public final /* synthetic */ Socket A02;

    public AnonymousClass0yQ(C08670xd r1, Socket socket, Socket socket2) {
        this.A00 = r1;
        this.A01 = socket;
        this.A02 = socket2;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Void call() throws Exception {
        C08670xd r3 = this.A00;
        C08670xd.A00(r3, this.A01, r3.A02, this.A02);
        return null;
    }
}
