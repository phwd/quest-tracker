package X;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.net.ssl.SSLSession;

/* renamed from: X.176  reason: invalid class name */
public class AnonymousClass176 implements Enumeration<byte[]> {
    public SSLSession A00;
    public final /* synthetic */ AnonymousClass16J A01;
    public final /* synthetic */ Iterator A02;

    public AnonymousClass176(AnonymousClass16J r1, Iterator it) {
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
