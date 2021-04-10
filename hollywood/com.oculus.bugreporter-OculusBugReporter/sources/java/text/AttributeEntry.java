package java.text;

import java.text.AttributedCharacterIterator;
import java.util.Map;

/* compiled from: AttributedString */
class AttributeEntry implements Map.Entry<AttributedCharacterIterator.Attribute, Object> {
    private AttributedCharacterIterator.Attribute key;
    private Object value;

    AttributeEntry(AttributedCharacterIterator.Attribute key2, Object value2) {
        this.key = key2;
        this.value = value2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0024 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    @Override // java.util.Map.Entry
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof java.text.AttributeEntry
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r0 = r5
            java.text.AttributeEntry r0 = (java.text.AttributeEntry) r0
            java.text.AttributedCharacterIterator$Attribute r2 = r0.key
            java.text.AttributedCharacterIterator$Attribute r3 = r4.key
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0026
            java.lang.Object r2 = r4.value
            if (r2 != 0) goto L_0x001c
            java.lang.Object r2 = r0.value
            if (r2 != 0) goto L_0x0026
            goto L_0x0024
        L_0x001c:
            java.lang.Object r3 = r0.value
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0026
        L_0x0024:
            r1 = 1
            goto L_0x0027
        L_0x0026:
        L_0x0027:
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
    public Object setValue(Object newValue) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        int hashCode = this.key.hashCode();
        Object obj = this.value;
        return hashCode ^ (obj == null ? 0 : obj.hashCode());
    }

    public String toString() {
        return this.key.toString() + "=" + this.value.toString();
    }
}
