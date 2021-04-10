package java.text;

import java.text.Format;

public class FieldPosition {
    private Format.Field attribute;
    int beginIndex;
    int endIndex;
    int field;

    public FieldPosition(int field2) {
        this.field = 0;
        this.endIndex = 0;
        this.beginIndex = 0;
        this.field = field2;
    }

    public FieldPosition(Format.Field attribute2) {
        this(attribute2, -1);
    }

    public FieldPosition(Format.Field attribute2, int fieldID) {
        this.field = 0;
        this.endIndex = 0;
        this.beginIndex = 0;
        this.attribute = attribute2;
        this.field = fieldID;
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

    public void setBeginIndex(int bi) {
        this.beginIndex = bi;
    }

    public void setEndIndex(int ei) {
        this.endIndex = ei;
    }

    /* access modifiers changed from: package-private */
    public Format.FieldDelegate getFieldDelegate() {
        return new Delegate();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FieldPosition)) {
            return false;
        }
        FieldPosition other = (FieldPosition) obj;
        Format.Field field2 = this.attribute;
        if (field2 == null) {
            if (other.attribute != null) {
                return false;
            }
        } else if (!field2.equals(other.attribute)) {
            return false;
        }
        if (this.beginIndex == other.beginIndex && this.endIndex == other.endIndex && this.field == other.field) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.field << 24) | (this.beginIndex << 16) | this.endIndex;
    }

    public String toString() {
        return getClass().getName() + "[field=" + this.field + ",attribute=" + ((Object) this.attribute) + ",beginIndex=" + this.beginIndex + ",endIndex=" + this.endIndex + ']';
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean matchesField(Format.Field attribute2) {
        Format.Field field2 = this.attribute;
        if (field2 != null) {
            return field2.equals(attribute2);
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean matchesField(Format.Field attribute2, int field2) {
        Format.Field field3 = this.attribute;
        if (field3 != null) {
            return field3.equals(attribute2);
        }
        return field2 == this.field;
    }

    private class Delegate implements Format.FieldDelegate {
        private boolean encounteredField;

        private Delegate() {
        }

        @Override // java.text.Format.FieldDelegate
        public void formatted(Format.Field attr, Object value, int start, int end, StringBuffer buffer) {
            if (!this.encounteredField && FieldPosition.this.matchesField(attr)) {
                FieldPosition.this.setBeginIndex(start);
                FieldPosition.this.setEndIndex(end);
                this.encounteredField = start != end;
            }
        }

        @Override // java.text.Format.FieldDelegate
        public void formatted(int fieldID, Format.Field attr, Object value, int start, int end, StringBuffer buffer) {
            if (!this.encounteredField && FieldPosition.this.matchesField(attr, fieldID)) {
                FieldPosition.this.setBeginIndex(start);
                FieldPosition.this.setEndIndex(end);
                this.encounteredField = start != end;
            }
        }
    }
}
