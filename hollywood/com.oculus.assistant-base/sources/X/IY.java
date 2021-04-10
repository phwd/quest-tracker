package X;

import com.facebook.quicklog.PointEditor;

public abstract class IY {
    public final PointEditor A02(String str) {
        if (this instanceof C0918ot) {
            return (C0918ot) this;
        }
        C0917os osVar = (C0917os) this;
        osVar.A04 = str;
        osVar.A02 = -1;
        osVar.A01 = 0;
        return osVar;
    }

    public final void markerEditingCompleted() {
        if (!(this instanceof C0918ot)) {
            C0917os osVar = (C0917os) this;
            if (osVar.A04 != null) {
                osVar.pointEditingCompleted();
            }
        }
    }
}
