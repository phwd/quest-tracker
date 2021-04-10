package X;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.net.ssl.SSLSession;

/* renamed from: X.1nq  reason: invalid class name */
public class AnonymousClass1nq implements Enumeration<byte[]> {
    public SSLSession A00;
    public final /* synthetic */ AnonymousClass1nm A01;
    public final /* synthetic */ Iterator A02;

    public AnonymousClass1nq(AnonymousClass1nm r1, Iterator it) {
        this.A01 = r1;
        this.A02 = it;
    }

    public final boolean hasMoreElements() {
        SSLSession sSLSession;
        if (this.A00 != null) {
            return true;
        }
        do {
            Iterator it = this.A02;
            if (it.hasNext()) {
                sSLSession = (SSLSession) it.next();
            } else {
                this.A00 = null;
                return false;
            }
        } while (!sSLSession.isValid());
        this.A00 = sSLSession;
        return true;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Enumeration
    public final byte[] nextElement() {
        if (hasMoreElements()) {
            byte[] id = this.A00.getId();
            this.A00 = null;
            return id;
        }
        throw new NoSuchElementException();
    }
}
