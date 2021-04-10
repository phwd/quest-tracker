package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.lang.reflect.Field;

/* renamed from: X.Uc  reason: case insensitive filesystem */
public final class C0367Uc extends AbstractC1034r7 {
    public static final long serialVersionUID = 1;
    public final transient Field A00;
    public final St _annotated;

    public Object readResolve() {
        return new C0367Uc(this, this._annotated.A00);
    }

    public C0367Uc(C0367Uc uc, JsonDeserializer jsonDeserializer) {
        super(uc, jsonDeserializer);
        this._annotated = uc._annotated;
        this.A00 = uc.A00;
    }

    public C0367Uc(C0367Uc uc, String str) {
        super(uc, str);
        this._annotated = uc._annotated;
        this.A00 = uc.A00;
    }

    public C0367Uc(C0367Uc uc, Field field) {
        super(uc);
        this._annotated = uc._annotated;
        if (field != null) {
            this.A00 = field;
            return;
        }
        throw new IllegalArgumentException(AnonymousClass08.A07("No Field passed for property '", uc._propName, "' (class ", uc.A2e().A0P().getName(), ")"));
    }

    public C0367Uc(PE pe, AbstractC1024qt qtVar, PR pr, Q0 q0, St st) {
        super(pe, qtVar, pr, q0);
        this._annotated = st;
        this.A00 = st.A00;
    }
}
