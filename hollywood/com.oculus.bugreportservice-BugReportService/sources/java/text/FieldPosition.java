package java.text;

import java.text.Format;

public class FieldPosition {
    private Format.Field attribute;
    int beginIndex;
    int endIndex;
    int field;

    public FieldPosition(int i) {
        this.field = 0;
        this.endIndex = 0;
        this.beginIndex = 0;
        this.field = i;
    }

    public FieldPosition(Format.Field field2) {
        this(field2, -1);
    }

    public FieldPosition(Format.Field field2, int i) {
        this.field = 0;
        this.endIndex = 0;
        this.beginIndex = 0;
        this.attribute = field2;
        this.field = i;
    }

    public Format.Field getFieldAttribute() {
        return this.attribute;
    }

    public int getField() {
        return this.field;
    }

    public int getBeginIndex() {
        return this.beginIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public void setBeginIndex(int i) {
        this.beginIndex = i;
    }

    public void setEndIndex(int i) {
        this.endIndex = i;
    }

    /* access modifiers changed from: package-private */
    public Format.FieldDelegate getFieldDelegate() {
        return new Delegate();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FieldPosition)) {
            return false;
        }
        FieldPosition fieldPosition = (FieldPosition) obj;
        Format.Field field2 = this.attribute;
        if (field2 == null) {
            if (fieldPosition.attribute != null) {
                return false;
            }
        } else if (!field2.equals(fieldPosition.attribute)) {
            return false;
        }
        if (this.beginIndex == fieldPosition.beginIndex && this.endIndex == fieldPosition.endIndex && this.field == fieldPosition.field) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.endIndex | (this.field << 24) | (this.beginIndex << 16);
    }

    public String toString() {
        return getClass().getName() + "[field=" + this.field + ",attribute=" + this.attribute + ",beginIndex=" + this.beginIndex + ",endIndex=" + this.endIndex + ']';
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean matchesField(Format.Field field2, int i) {
        Format.Field field3 = this.attribute;
        if (field3 != null) {
            return field3.equals(field2);
        }
        return i == this.field;
    }

    private class Delegate implements Format.FieldDelegate {
        private boolean encounteredField;

        private Delegate() {
        }

        @Override // java.text.Format.FieldDelegate
        public void formatted(int i, Format.Field field, Object obj, int i2, int i3, StringBuffer stringBuffer) {
            if (!this.encounteredField && FieldPosition.this.matchesField(field, i)) {
                FieldPosition.this.setBeginIndex(i2);
                FieldPosition.this.setEndIndex(i3);
                this.encounteredField = i2 != i3;
            }
        }
    }
}
