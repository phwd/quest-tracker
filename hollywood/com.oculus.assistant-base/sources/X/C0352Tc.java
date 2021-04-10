package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.lang.reflect.Method;

/* renamed from: X.Tc  reason: case insensitive filesystem */
public final class C0352Tc extends AbstractC1034r7 {
    public static final long serialVersionUID = 1;
    public final transient Method A00;
    public final AnonymousClass0q _annotated;

    public Object readResolve() {
        return new C0352Tc(this, this._annotated.A00);
    }

    public C0352Tc(C0352Tc tc, JsonDeserializer jsonDeserializer) {
        super(tc, jsonDeserializer);
        this._annotated = tc._annotated;
        this.A00 = tc.A00;
    }

    public C0352Tc(C0352Tc tc, String str) {
        super(tc, str);
        this._annotated = tc._annotated;
        this.A00 = tc.A00;
    }

    public C0352Tc(C0352Tc tc, Method method) {
        super(tc);
        this._annotated = tc._annotated;
        this.A00 = method;
    }

    public C0352Tc(PE pe, AbstractC1024qt qtVar, PR pr, Q0 q0, AnonymousClass0q r6) {
        super(pe, qtVar, pr, q0);
        this._annotated = r6;
        this.A00 = r6.A00;
    }
}
