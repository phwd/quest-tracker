package java.text;

public class ParsePosition {
    int errorIndex = -1;
    int index = 0;

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public ParsePosition(int i) {
        this.index = i;
    }

    public void setErrorIndex(int i) {
        this.errorIndex = i;
    }

    public int getErrorIndex() {
        return this.errorIndex;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ParsePosition)) {
            return false;
        }
        ParsePosition parsePosition = (ParsePosition) obj;
        if (this.index == parsePosition.index && this.errorIndex == parsePosition.errorIndex) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.index | (this.errorIndex << 16);
    }

    public String toString() {
        return ParsePosition.class.getName() + "[index=" + this.index + ",errorIndex=" + this.errorIndex + ']';
    }
}
