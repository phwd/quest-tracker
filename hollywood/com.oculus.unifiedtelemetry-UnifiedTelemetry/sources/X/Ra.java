package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public enum Ra {
    AUTO_EXPOSURE("auto"),
    MANUAL_EXPOSURE("man");
    
    public String mValue;

    /* access modifiers changed from: public */
    Ra(String str) {
        this.mValue = str;
    }

    public String toString() {
        return getValue();
    }

    public String getValue() {
        return this.mValue;
    }
}
