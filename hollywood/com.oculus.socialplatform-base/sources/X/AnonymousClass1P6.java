package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.Callable;

/* renamed from: X.1P6  reason: invalid class name */
public class AnonymousClass1P6 implements Callable<AnonymousClass1P5> {
    public final /* synthetic */ AnonymousClass1P7 A00;
    public final /* synthetic */ String A01;

    public AnonymousClass1P6(AnonymousClass1P7 r1, String str) {
        this.A00 = r1;
        this.A01 = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final AnonymousClass1P5 call() throws Exception {
        int i;
        String str = this.A01;
        AnonymousClass1P7 r6 = this.A00;
        try {
            AnonymousClass1P5 r8 = new AnonymousClass1P5(str, Arrays.asList(InetAddress.getAllByName(str)), 0, 0);
            synchronized (r6) {
                AnonymousClass1P9 r7 = r6.A00;
                TreeSet<AnonymousClass1P5> A012 = r7.A01();
                if (A012.isEmpty()) {
                    i = 0;
                } else {
                    i = A012.first().A01 + 1;
                }
                AnonymousClass1P5 A002 = r7.A00(r8);
                if (A002 == null) {
                    r7.A03(new AnonymousClass1P5(r8.A02, r8.A00(), i, 0));
                } else {
                    r7.A04(A002, new AnonymousClass1P5(r8.A02, r8.A00(), i, A002.A00));
                }
                r7.A02();
            }
            return r8;
        } catch (UnknownHostException unused) {
            throw new AnonymousClass1Jw(AnonymousClass1HJ.UnknownHost);
        } catch (SecurityException unused2) {
            throw new AnonymousClass1Jw(AnonymousClass1HJ.SecurityException);
        }
    }
}
