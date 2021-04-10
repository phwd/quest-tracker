package X;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.Callable;

/* renamed from: X.0xL  reason: invalid class name and case insensitive filesystem */
public class CallableC08500xL implements Callable<C08440xF> {
    public final /* synthetic */ AbstractC08480xJ A00;
    public final /* synthetic */ String A01;

    public CallableC08500xL(AbstractC08480xJ r1, String str) {
        this.A00 = r1;
        this.A01 = str;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final C08440xF call() throws Exception {
        EnumC09190yp r1;
        int i;
        String str = this.A01;
        AbstractC08480xJ r6 = this.A00;
        try {
            C08440xF r8 = new C08440xF(str, Arrays.asList(InetAddress.getAllByName(str)), 0, 0);
            synchronized (r6) {
                C08410xC r7 = r6.A00;
                TreeSet<C08440xF> A012 = r7.A01();
                if (A012.isEmpty()) {
                    i = 0;
                } else {
                    i = A012.first().A01 + 1;
                }
                C08440xF A002 = r7.A00(r8);
                if (A002 == null) {
                    r7.A03(new C08440xF(r8.A02, r8.A00(), i, 0));
                } else {
                    r7.A04(A002, new C08440xF(r8.A02, r8.A00(), i, A002.A00));
                }
                r7.A02();
            }
            return r8;
        } catch (UnknownHostException unused) {
            r1 = EnumC09190yp.UnknownHost;
            throw new C09260yx(r1);
        } catch (SecurityException unused2) {
            r1 = EnumC09190yp.SecurityException;
            throw new C09260yx(r1);
        }
    }
}
