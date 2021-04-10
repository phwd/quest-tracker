package android.icu.impl.number;

import android.icu.impl.UCharacterProperty;
import android.icu.text.NumberFormat;
import android.icu.text.SymbolTable;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.FieldPosition;
import java.text.Format;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberStringBuilder implements CharSequence {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final NumberStringBuilder EMPTY = new NumberStringBuilder();
    private static final Map<NumberFormat.Field, Character> fieldToDebugChar = new HashMap();
    private char[] chars;
    private NumberFormat.Field[] fields;
    private int length;
    private int zero;

    static {
        fieldToDebugChar.put(NumberFormat.Field.SIGN, '-');
        fieldToDebugChar.put(NumberFormat.Field.INTEGER, Character.valueOf(UCharacterProperty.LATIN_SMALL_LETTER_I_));
        fieldToDebugChar.put(NumberFormat.Field.FRACTION, 'f');
        fieldToDebugChar.put(NumberFormat.Field.EXPONENT, 'e');
        fieldToDebugChar.put(NumberFormat.Field.EXPONENT_SIGN, '+');
        fieldToDebugChar.put(NumberFormat.Field.EXPONENT_SYMBOL, 'E');
        fieldToDebugChar.put(NumberFormat.Field.DECIMAL_SEPARATOR, '.');
        fieldToDebugChar.put(NumberFormat.Field.GROUPING_SEPARATOR, ',');
        fieldToDebugChar.put(NumberFormat.Field.PERCENT, '%');
        fieldToDebugChar.put(NumberFormat.Field.PERMILLE, (char) 8240);
        fieldToDebugChar.put(NumberFormat.Field.CURRENCY, Character.valueOf(SymbolTable.SYMBOL_REF));
    }

    public NumberStringBuilder() {
        this(40);
    }

    public NumberStringBuilder(int capacity) {
        this.chars = new char[capacity];
        this.fields = new NumberFormat.Field[capacity];
        this.zero = capacity / 2;
        this.length = 0;
    }

    public NumberStringBuilder(NumberStringBuilder source) {
        copyFrom(source);
    }

    public void copyFrom(NumberStringBuilder source) {
        char[] cArr = source.chars;
        this.chars = Arrays.copyOf(cArr, cArr.length);
        NumberFormat.Field[] fieldArr = source.fields;
        this.fields = (NumberFormat.Field[]) Arrays.copyOf(fieldArr, fieldArr.length);
        this.zero = source.zero;
        this.length = source.length;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    public int codePointCount() {
        return Character.codePointCount(this, 0, length());
    }

    @Override // java.lang.CharSequence
    public char charAt(int index) {
        return this.chars[this.zero + index];
    }

    public NumberFormat.Field fieldAt(int index) {
        return this.fields[this.zero + index];
    }

    public int getFirstCodePoint() {
        int i = this.length;
        if (i == 0) {
            return -1;
        }
        char[] cArr = this.chars;
        int i2 = this.zero;
        return Character.codePointAt(cArr, i2, i + i2);
    }

    public int getLastCodePoint() {
        int i = this.length;
        if (i == 0) {
            return -1;
        }
        char[] cArr = this.chars;
        int i2 = this.zero;
        return Character.codePointBefore(cArr, i + i2, i2);
    }

    public int codePointAt(int index) {
        char[] cArr = this.chars;
        int i = this.zero;
        return Character.codePointAt(cArr, i + index, i + this.length);
    }

    public int codePointBefore(int index) {
        char[] cArr = this.chars;
        int i = this.zero;
        return Character.codePointBefore(cArr, i + index, i);
    }

    public NumberStringBuilder clear() {
        this.zero = getCapacity() / 2;
        this.length = 0;
        return this;
    }

    public int appendCodePoint(int codePoint, NumberFormat.Field field) {
        return insertCodePoint(this.length, codePoint, field);
    }

    public int insertCodePoint(int index, int codePoint, NumberFormat.Field field) {
        int count = Character.charCount(codePoint);
        int position = prepareForInsert(index, count);
        Character.toChars(codePoint, this.chars, position);
        NumberFormat.Field[] fieldArr = this.fields;
        fieldArr[position] = field;
        if (count == 2) {
            fieldArr[position + 1] = field;
        }
        return count;
    }

    public int append(CharSequence sequence, NumberFormat.Field field) {
        return insert(this.length, sequence, field);
    }

    public int insert(int index, CharSequence sequence, NumberFormat.Field field) {
        if (sequence.length() == 0) {
            return 0;
        }
        if (sequence.length() == 1) {
            return insertCodePoint(index, sequence.charAt(0), field);
        }
        return insert(index, sequence, 0, sequence.length(), field);
    }

    public int insert(int index, CharSequence sequence, int start, int end, NumberFormat.Field field) {
        int count = end - start;
        int position = prepareForInsert(index, count);
        for (int i = 0; i < count; i++) {
            this.chars[position + i] = sequence.charAt(start + i);
            this.fields[position + i] = field;
        }
        return count;
    }

    public int splice(int startThis, int endThis, CharSequence sequence, int startOther, int endOther, NumberFormat.Field field) {
        int position;
        int otherLength = endOther - startOther;
        int count = otherLength - (endThis - startThis);
        if (count > 0) {
            position = prepareForInsert(startThis, count);
        } else {
            position = remove(startThis, -count);
        }
        for (int i = 0; i < otherLength; i++) {
            this.chars[position + i] = sequence.charAt(startOther + i);
            this.fields[position + i] = field;
        }
        return count;
    }

    public int append(char[] chars2, NumberFormat.Field[] fields2) {
        return insert(this.length, chars2, fields2);
    }

    public int insert(int index, char[] chars2, NumberFormat.Field[] fields2) {
        int count = chars2.length;
        if (count == 0) {
            return 0;
        }
        int position = prepareForInsert(index, count);
        for (int i = 0; i < count; i++) {
            this.chars[position + i] = chars2[i];
            this.fields[position + i] = fields2 == null ? null : fields2[i];
        }
        return count;
    }

    public int append(NumberStringBuilder other) {
        return insert(this.length, other);
    }

    public int insert(int index, NumberStringBuilder other) {
        if (this != other) {
            int count = other.length;
            if (count == 0) {
                return 0;
            }
            int position = prepareForInsert(index, count);
            for (int i = 0; i < count; i++) {
                this.chars[position + i] = other.charAt(i);
                this.fields[position + i] = other.fieldAt(i);
            }
            return count;
        }
        throw new IllegalArgumentException("Cannot call insert/append on myself");
    }

    private int prepareForInsert(int index, int count) {
        if (index == 0) {
            int i = this.zero;
            if (i - count >= 0) {
                this.zero = i - count;
                this.length += count;
                return this.zero;
            }
        }
        int i2 = this.length;
        if (index != i2 || this.zero + i2 + count >= getCapacity()) {
            return prepareForInsertHelper(index, count);
        }
        this.length += count;
        return (this.zero + this.length) - count;
    }

    private int prepareForInsertHelper(int index, int count) {
        int oldCapacity = getCapacity();
        int oldZero = this.zero;
        char[] oldChars = this.chars;
        NumberFormat.Field[] oldFields = this.fields;
        int i = this.length;
        if (i + count > oldCapacity) {
            int newCapacity = (i + count) * 2;
            int newZero = (newCapacity / 2) - ((i + count) / 2);
            char[] newChars = new char[newCapacity];
            NumberFormat.Field[] newFields = new NumberFormat.Field[newCapacity];
            System.arraycopy((Object) oldChars, oldZero, (Object) newChars, newZero, index);
            System.arraycopy((Object) oldChars, oldZero + index, (Object) newChars, newZero + index + count, this.length - index);
            System.arraycopy(oldFields, oldZero, newFields, newZero, index);
            System.arraycopy(oldFields, oldZero + index, newFields, newZero + index + count, this.length - index);
            this.chars = newChars;
            this.fields = newFields;
            this.zero = newZero;
            this.length += count;
        } else {
            int newZero2 = (oldCapacity / 2) - ((i + count) / 2);
            System.arraycopy((Object) oldChars, oldZero, (Object) oldChars, newZero2, i);
            System.arraycopy((Object) oldChars, newZero2 + index, (Object) oldChars, newZero2 + index + count, this.length - index);
            System.arraycopy(oldFields, oldZero, oldFields, newZero2, this.length);
            System.arraycopy(oldFields, newZero2 + index, oldFields, newZero2 + index + count, this.length - index);
            this.zero = newZero2;
            this.length += count;
        }
        return this.zero + index;
    }

    private int remove(int index, int count) {
        int position = this.zero + index;
        char[] cArr = this.chars;
        System.arraycopy((Object) cArr, position + count, (Object) cArr, position, (this.length - index) - count);
        NumberFormat.Field[] fieldArr = this.fields;
        System.arraycopy(fieldArr, position + count, fieldArr, position, (this.length - index) - count);
        this.length -= count;
        return position;
    }

    private int getCapacity() {
        return this.chars.length;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || end > this.length || end < start) {
            throw new IndexOutOfBoundsException();
        }
        NumberStringBuilder other = new NumberStringBuilder(this);
        other.zero = this.zero + start;
        other.length = end - start;
        return other;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.chars, this.zero, this.length);
    }

    public String toDebugString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<NumberStringBuilder [");
        sb.append(toString());
        sb.append("] [");
        for (int i = this.zero; i < this.zero + this.length; i++) {
            NumberFormat.Field[] fieldArr = this.fields;
            if (fieldArr[i] == null) {
                sb.append('n');
            } else {
                sb.append((Object) fieldToDebugChar.get(fieldArr[i]));
            }
        }
        sb.append("]>");
        return sb.toString();
    }

    public char[] toCharArray() {
        char[] cArr = this.chars;
        int i = this.zero;
        return Arrays.copyOfRange(cArr, i, this.length + i);
    }

    public NumberFormat.Field[] toFieldArray() {
        NumberFormat.Field[] fieldArr = this.fields;
        int i = this.zero;
        return (NumberFormat.Field[]) Arrays.copyOfRange(fieldArr, i, this.length + i);
    }

    public boolean contentEquals(char[] chars2, NumberFormat.Field[] fields2) {
        int length2 = chars2.length;
        int i = this.length;
        if (!(length2 == i && fields2.length == i)) {
            return false;
        }
        for (int i2 = 0; i2 < this.length; i2++) {
            char[] cArr = this.chars;
            int i3 = this.zero;
            if (!(cArr[i3 + i2] == chars2[i2] && this.fields[i3 + i2] == fields2[i2])) {
                return false;
            }
        }
        return true;
    }

    public boolean contentEquals(NumberStringBuilder other) {
        if (this.length != other.length) {
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            if (!(charAt(i) == other.charAt(i) && fieldAt(i) == other.fieldAt(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        throw new UnsupportedOperationException("Don't call #hashCode() or #equals() on a mutable.");
    }

    public boolean equals(Object other) {
        throw new UnsupportedOperationException("Don't call #hashCode() or #equals() on a mutable.");
    }

    public boolean nextFieldPosition(FieldPosition fp) {
        Format.Field rawField = fp.getFieldAttribute();
        if (rawField == null) {
            if (fp.getField() == 0) {
                rawField = NumberFormat.Field.INTEGER;
            } else if (fp.getField() != 1) {
                return false;
            } else {
                rawField = NumberFormat.Field.FRACTION;
            }
        }
        if (rawField instanceof NumberFormat.Field) {
            NumberFormat.Field field = (NumberFormat.Field) rawField;
            boolean seenStart = false;
            int fractionStart = -1;
            int i = this.zero + fp.getEndIndex();
            while (true) {
                int i2 = this.zero;
                int i3 = this.length;
                if (i > i2 + i3) {
                    break;
                }
                NumberFormat.Field _field = i < i2 + i3 ? this.fields[i] : null;
                if (!seenStart || field == _field) {
                    if (!seenStart && field == _field) {
                        fp.setBeginIndex(i - this.zero);
                        seenStart = true;
                    }
                    if (_field == NumberFormat.Field.INTEGER || _field == NumberFormat.Field.DECIMAL_SEPARATOR) {
                        fractionStart = (i - this.zero) + 1;
                    }
                } else if (field != NumberFormat.Field.INTEGER || _field != NumberFormat.Field.GROUPING_SEPARATOR) {
                    fp.setEndIndex(i - this.zero);
                }
                i++;
            }
            if (field == NumberFormat.Field.FRACTION && !seenStart && fractionStart != -1) {
                fp.setBeginIndex(fractionStart);
                fp.setEndIndex(fractionStart);
            }
            return seenStart;
        }
        throw new IllegalArgumentException("You must pass an instance of android.icu.text.NumberFormat.Field as your FieldPosition attribute.  You passed: " + rawField.getClass().toString());
    }

    public AttributedCharacterIterator toCharacterIterator() {
        int i;
        AttributedString as = new AttributedString(toString());
        NumberFormat.Field current = null;
        int currentStart = -1;
        int i2 = 0;
        while (true) {
            i = this.length;
            if (i2 >= i) {
                break;
            }
            NumberFormat.Field field = this.fields[this.zero + i2];
            if (current == NumberFormat.Field.INTEGER && field == NumberFormat.Field.GROUPING_SEPARATOR) {
                as.addAttribute(NumberFormat.Field.GROUPING_SEPARATOR, NumberFormat.Field.GROUPING_SEPARATOR, i2, i2 + 1);
            } else if (current != field) {
                if (current != null) {
                    as.addAttribute(current, current, currentStart, i2);
                }
                current = field;
                currentStart = i2;
            }
            i2++;
        }
        if (current != null) {
            as.addAttribute(current, current, currentStart, i);
        }
        return as.getIterator();
    }
}
