package X;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: X.1eM  reason: invalid class name and case insensitive filesystem */
public class C08301eM implements AbstractC08091dx<C08311eN> {
    public final /* synthetic */ C08291eL A00;

    public C08301eM(C08291eL r1) {
        this.A00 = r1;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC08091dx
    public final C08311eN A2N() {
        try {
            return new C08311eN(MessageDigest.getInstance("SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
