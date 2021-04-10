package X;

import java.io.IOException;

/* renamed from: X.Sl  reason: case insensitive filesystem */
public class C0151Sl extends AbstractC0131Ob<StringBuffer> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [X.mm, java.lang.Object] */
    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, StringBuffer stringBuffer) throws IOException {
        String obj;
        if (stringBuffer == null) {
            obj = null;
        } else {
            obj = stringBuffer.toString();
        }
        mmVar.A0G(obj);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // X.AbstractC0131Ob
    public final StringBuffer A02(lk lkVar) throws IOException {
        if (lkVar.A0G() != AnonymousClass07.A08) {
            return new StringBuffer(lkVar.A0J());
        }
        lkVar.A0P();
        return null;
    }
}
