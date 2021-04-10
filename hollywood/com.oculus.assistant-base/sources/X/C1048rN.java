package X;

import com.fasterxml.jackson.annotation.JsonView;

/* renamed from: X.rN  reason: case insensitive filesystem */
public final class C1048rN implements PM {
    public final /* synthetic */ C1052rR A00;

    public C1048rN(C1052rR rRVar) {
        this.A00 = rRVar;
    }

    @Override // X.PM
    public final Object A5f(AbstractC1044rJ rJVar) {
        JsonView jsonView;
        if (!(this.A00.A07 instanceof Rw) || (jsonView = (JsonView) rJVar.A0L(JsonView.class)) == null) {
            return null;
        }
        return jsonView.value();
    }
}
