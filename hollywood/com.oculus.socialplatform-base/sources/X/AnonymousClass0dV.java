package X;

import java.io.IOException;

/* renamed from: X.0dV  reason: invalid class name */
public class AnonymousClass0dV extends AnonymousClass13Y<Class> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass13Y
    public final Class A02(AnonymousClass14I r3) throws IOException {
        throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.14L, java.lang.Object] */
    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r4, Class cls) throws IOException {
        throw new UnsupportedOperationException(AnonymousClass006.A09("Attempted to serialize java.lang.Class: ", cls.getName(), ". Forgot to register a type adapter?"));
    }
}
