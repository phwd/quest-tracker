package X;

/* renamed from: X.0yz  reason: invalid class name and case insensitive filesystem */
public enum EnumC09280yz {
    PROVIDED_TOKEN_BINDING(0),
    REFERRED_TOKEN_BINDING(1);
    
    public int value;

    public byte getEncoded() {
        return (byte) this.value;
    }

    /* access modifiers changed from: public */
    EnumC09280yz(int i) {
        this.value = i;
    }
}
