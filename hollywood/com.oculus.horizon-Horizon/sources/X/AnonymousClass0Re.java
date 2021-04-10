package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Re  reason: invalid class name */
public enum AnonymousClass0Re {
    AUTO_EXPOSURE("auto"),
    MANUAL_EXPOSURE("man");
    
    public String mValue;

    /* access modifiers changed from: public */
    AnonymousClass0Re(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public String toString() {
        return getValue();
    }
}
