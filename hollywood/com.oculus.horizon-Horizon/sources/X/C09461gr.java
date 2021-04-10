package X;

import java.io.Serializable;
import org.json.JSONException;

/* renamed from: X.1gr  reason: invalid class name and case insensitive filesystem */
public class C09461gr implements Serializable {
    public static final long serialVersionUID = -2488473066578201069L;
    public final boolean isImplicit;
    public final String jsonString;

    private Object readResolve() throws JSONException {
        return new C09401dq(this.jsonString, this.isImplicit);
    }

    public C09461gr(String str, boolean z) {
        this.jsonString = str;
        this.isImplicit = z;
    }
}
