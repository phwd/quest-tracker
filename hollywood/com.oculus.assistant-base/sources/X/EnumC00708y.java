package X;

/* renamed from: X.8y  reason: invalid class name and case insensitive filesystem */
public enum EnumC00708y {
    UNSUPPORTED,
    OCULUS_BROWSER(0),
    OCULUS_STORE(1),
    OCULUS_SOCIAL(2);
    
    public final Integer modelValue;

    public int getModelValue() {
        Integer num = this.modelValue;
        if (num != null) {
            return num.intValue();
        }
        throw new NoSuchFieldException("Only certain surface types are currently supported as input to the model.");
    }

    /* access modifiers changed from: public */
    EnumC00708y() {
        this.modelValue = null;
    }

    /* access modifiers changed from: public */
    EnumC00708y(int i) {
        this.modelValue = Integer.valueOf(i);
    }
}
