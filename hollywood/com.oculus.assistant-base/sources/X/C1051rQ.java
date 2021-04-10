package X;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: X.rQ  reason: case insensitive filesystem */
public final class C1051rQ implements PM {
    public final /* synthetic */ C1052rR A00;

    public C1051rQ(C1052rR rRVar) {
        this.A00 = rRVar;
    }

    @Override // X.PM
    public final Object A5f(AbstractC1044rJ rJVar) {
        JsonProperty jsonProperty;
        if (!(this.A00.A07 instanceof Rw) || (jsonProperty = (JsonProperty) rJVar.A0L(JsonProperty.class)) == null) {
            return null;
        }
        return Boolean.valueOf(jsonProperty.required());
    }
}
