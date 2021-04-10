package X;

import com.fasterxml.jackson.databind.JsonDeserializer;

/* renamed from: X.Ui  reason: case insensitive filesystem */
public final class C0372Ui extends AbstractC1034r7 {
    public static final long serialVersionUID = 1;
    public final Ss _annotated;
    public final int _creatorIndex;
    public final Object _injectableValueId;

    @Override // X.AbstractC1034r7
    public final String toString() {
        StringBuilder sb = new StringBuilder("[creator property, name '");
        sb.append(this._propName);
        sb.append("'; inject id '");
        sb.append(this._injectableValueId);
        sb.append("']");
        return sb.toString();
    }

    public C0372Ui(C0372Ui ui, JsonDeserializer jsonDeserializer) {
        super(ui, jsonDeserializer);
        this._annotated = ui._annotated;
        this._creatorIndex = ui._creatorIndex;
        this._injectableValueId = ui._injectableValueId;
    }

    public C0372Ui(C0372Ui ui, String str) {
        super(ui, str);
        this._annotated = ui._annotated;
        this._creatorIndex = ui._creatorIndex;
        this._injectableValueId = ui._injectableValueId;
    }

    public C0372Ui(String str, AbstractC1024qt qtVar, OE oe, PR pr, Q0 q0, Ss ss, int i, Object obj, boolean z) {
        super(str, qtVar, oe, pr, q0, z);
        this._annotated = ss;
        this._creatorIndex = i;
        this._injectableValueId = obj;
    }
}
