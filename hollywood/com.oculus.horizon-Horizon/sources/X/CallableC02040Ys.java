package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.Callable;

/* renamed from: X.0Ys  reason: invalid class name and case insensitive filesystem */
public class CallableC02040Ys implements Callable<AnonymousClass0ZS> {
    public final /* synthetic */ AbstractC02050Yt A00;
    public final /* synthetic */ String A01;

    public CallableC02040Ys(AbstractC02050Yt r1, String str) {
        this.A00 = r1;
        this.A01 = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final AnonymousClass0ZS call() throws Exception {
        AnonymousClass0XN r1;
        int i;
        String str = this.A01;
        AbstractC02050Yt r6 = this.A00;
        try {
            AnonymousClass0ZS r8 = new AnonymousClass0ZS(str, Arrays.asList(InetAddress.getAllByName(str)), 0, 0);
            synchronized (r6) {
                AnonymousClass0ZR r7 = r6.A00;
                TreeSet<AnonymousClass0ZS> A012 = r7.A01();
                if (A012.isEmpty()) {
                    i = 0;
                } else {
                    i = A012.first().A01 + 1;
                }
                AnonymousClass0ZS A002 = r7.A00(r8);
                if (A002 == null) {
                    r7.A03(new AnonymousClass0ZS(r8.A02, r8.A00(), i, 0));
                } else {
                    r7.A04(A002, new AnonymousClass0ZS(r8.A02, r8.A00(), i, A002.A00));
                }
                r7.A02();
            }
            return r8;
        } catch (UnknownHostException unused) {
            r1 = AnonymousClass0XN.UnknownHost;
            throw new AnonymousClass0XO(r1);
        } catch (SecurityException unused2) {
            r1 = AnonymousClass0XN.SecurityException;
            throw new AnonymousClass0XO(r1);
        }
    }
}
