package X;

import java.io.IOException;

/* renamed from: X.0WU  reason: invalid class name */
public class AnonymousClass0WU extends AnonymousClass0Bd<Class> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AnonymousClass0Bd
    public final Class A02(AnonymousClass0Fo r3) throws IOException {
        throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.0GL, java.lang.Object] */
    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r4, Class cls) throws IOException {
        throw new UnsupportedOperationException(AnonymousClass006.A07("Attempted to serialize java.lang.Class: ", cls.getName(), ". Forgot to register a type adapter?"));
    }
}
