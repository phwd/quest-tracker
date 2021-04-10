package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Sn  reason: invalid class name */
public enum AnonymousClass0Sn {
    AUTO_EXPOSURE("auto"),
    MANUAL_EXPOSURE("man");
    
    public String mValue;

    public String getValue() {
        return this.mValue;
    }

    /* access modifiers changed from: public */
    AnonymousClass0Sn(String str) {
        this.mValue = str;
    }

    public String toString() {
        return getValue();
    }
}
