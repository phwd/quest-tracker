package X;

import java.io.IOException;

public class Sm extends AbstractC0131Ob<Class> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final Class A02(lk lkVar) throws IOException {
        throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Class cls) throws IOException {
        throw new UnsupportedOperationException(AnonymousClass06.A05("Attempted to serialize java.lang.Class: ", cls.getName(), ". Forgot to register a type adapter?"));
    }
}
