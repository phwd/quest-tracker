package X;

import com.fasterxml.jackson.annotation.JsonTypeId;

/* renamed from: X.rP  reason: case insensitive filesystem */
public final class C1050rP implements PM {
    public final /* synthetic */ C1052rR A00;

    public C1050rP(C1052rR rRVar) {
        this.A00 = rRVar;
    }

    @Override // X.PM
    public final Object A5f(AbstractC1044rJ rJVar) {
        if (!(this.A00.A07 instanceof Rw)) {
            return null;
        }
        return Boolean.valueOf(rJVar.A0O(JsonTypeId.class));
    }
}
