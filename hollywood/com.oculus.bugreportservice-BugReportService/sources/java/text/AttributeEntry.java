package java.text;

import java.text.AttributedCharacterIterator;
import java.util.Map;

/* compiled from: AttributedString */
class AttributeEntry implements Map.Entry {
    private AttributedCharacterIterator.Attribute key;
    private Object value;

    AttributeEntry(AttributedCharacterIterator.Attribute attribute, Object obj) {
        this.key = attribute;
        this.value = obj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0023 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof java.text.AttributeEntry
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.text.AttributeEntry r4 = (java.text.AttributeEntry) r4
            java.text.AttributedCharacterIterator$Attribute r0 = r4.key
            java.text.AttributedCharacterIterator$Attribute r2 = r3.key
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0024
            java.lang.Object r3 = r3.value
            if (r3 != 0) goto L_0x001b
            java.lang.Object r3 = r4.value
            if (r3 != 0) goto L_0x0024
            goto L_0x0023
        L_0x001b:
            java.lang.Object r4 = r4.value
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x0024
        L_0x0023:
            r1 = 1
        L_0x0024:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.AttributeEntry.equals(java.lang.Object):boolean");
    }

    @Override // java.util.Map.Entry
    public AttributedCharacterIterator.Attribute getKey() {
        return this.key;
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        int hashCode = this.key.hashCode();
        Object obj = this.value;
        return (obj == null ? 0 : obj.hashCode()) ^ hashCode;
    }

    public String toString() {
        return this.key.toString() + "=" + this.value.toString();
    }
}
