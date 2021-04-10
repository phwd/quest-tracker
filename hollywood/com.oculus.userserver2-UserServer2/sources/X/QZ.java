package X;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
public abstract class QZ {
    public String toString() {
        Object obj;
        if (!(this instanceof MJ)) {
            NA na = (NA) this;
            if (!(na instanceof AbstractC00189h)) {
                obj = na.A00();
            } else {
                obj = ((C00050s) ((AnonymousClass6q) ((AbstractC00189h) na))).A00;
            }
        } else {
            MJ mj = (MJ) this;
            if (!(mj instanceof AnonymousClass9G)) {
                obj = mj.A00();
            } else {
                obj = ((AnonymousClass6d) ((AnonymousClass9G) mj)).A00;
            }
        }
        return obj.toString();
    }
}
