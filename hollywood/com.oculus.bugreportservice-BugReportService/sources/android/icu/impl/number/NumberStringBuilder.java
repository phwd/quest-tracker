package android.icu.impl.number;

import android.icu.text.NumberFormat;
import java.text.AttributedCharacterIterator;
import java.text.FieldPosition;
import java.text.Format;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberStringBuilder implements CharSequence {
    public static final NumberStringBuilder EMPTY = new NumberStringBuilder();
    private static final Map fieldToDebugChar = new HashMap();
    private char[] chars;
    private NumberFormat.Field[] fields;
    private int length;
    private int zero;

    static {
        fieldToDebugChar.put(NumberFormat.Field.SIGN, '-');
        fieldToDebugChar.put(NumberFormat.Field.INTEGER, 'i');
        fieldToDebugChar.put(NumberFormat.Field.FRACTION, 'f');
        fieldToDebugChar.put(NumberFormat.Field.EXPONENT, 'e');
        fieldToDebugChar.put(NumberFormat.Field.EXPONENT_SIGN, '+');
        fieldToDebugChar.put(NumberFormat.Field.EXPONENT_SYMBOL, 'E');
        fieldToDebugChar.put(NumberFormat.Field.DECIMAL_SEPARATOR, '.');
        fieldToDebugChar.put(NumberFormat.Field.GROUPING_SEPARATOR, ',');
        fieldToDebugChar.put(NumberFormat.Field.PERCENT, '%');
        fieldToDebugChar.put(NumberFormat.Field.PERMILLE, (char) 8240);
        fieldToDebugChar.put(NumberFormat.Field.CURRENCY, '$');
    }

    public NumberStringBuilder() {
        this(40);
    }

    public NumberStringBuilder(int i) {
        this.chars = new char[i];
        this.fields = new NumberFormat.Field[i];
        this.zero = i / 2;
        this.length = 0;
    }

    public NumberStringBuilder(NumberStringBuilder numberStringBuilder) {
        copyFrom(numberStringBuilder);
    }

    public void copyFrom(NumberStringBuilder numberStringBuilder) {
        char[] cArr = numberStringBuilder.chars;
        this.chars = Arrays.copyOf(cArr, cArr.length);
        NumberFormat.Field[] fieldArr = numberStringBuilder.fields;
        this.fields = (NumberFormat.Field[]) Arrays.copyOf(fieldArr, fieldArr.length);
        this.zero = numberStringBuilder.zero;
        this.length = numberStringBuilder.length;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    public int codePointCount() {
        return Character.codePointCount(this, 0, length());
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.chars[this.zero + i];
    }

    public NumberFormat.Field fieldAt(int i) {
        return this.fields[this.zero + i];
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

    public int codePointAt(int i) {
        char[] cArr = this.chars;
        int i2 = this.zero;
        return Character.codePointAt(cArr, i + i2, i2 + this.length);
    }

    public int codePointBefore(int i) {
        char[] cArr = this.chars;
        int i2 = this.zero;
        return Character.codePointBefore(cArr, i + i2, i2);
    }

    public NumberStringBuilder clear() {
        this.zero = getCapacity() / 2;
        this.length = 0;
        return this;
    }

    public int insertCodePoint(int i, int i2, NumberFormat.Field field) {
        int charCount = Character.charCount(i2);
        int prepareForInsert = prepareForInsert(i, charCount);
        Character.toChars(i2, this.chars, prepareForInsert);
        NumberFormat.Field[] fieldArr = this.fields;
        fieldArr[prepareForInsert] = field;
        if (charCount == 2) {
            fieldArr[prepareForInsert + 1] = field;
        }
        return charCount;
    }

    public int insert(int i, CharSequence charSequence, NumberFormat.Field field) {
        if (charSequence.length() == 0) {
            return 0;
        }
        if (charSequence.length() == 1) {
            return insertCodePoint(i, charSequence.charAt(0), field);
        }
        return insert(i, charSequence, 0, charSequence.length(), field);
    }

    public int insert(int i, CharSequence charSequence, int i2, int i3, NumberFormat.Field field) {
        int i4 = i3 - i2;
        int prepareForInsert = prepareForInsert(i, i4);
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = prepareForInsert + i5;
            this.chars[i6] = charSequence.charAt(i2 + i5);
            this.fields[i6] = field;
        }
        return i4;
    }

    public int splice(int i, int i2, CharSequence charSequence, int i3, int i4, NumberFormat.Field field) {
        int i5;
        int i6 = i4 - i3;
        int i7 = i6 - (i2 - i);
        if (i7 > 0) {
            i5 = prepareForInsert(i, i7);
        } else {
            i5 = remove(i, -i7);
        }
        for (int i8 = 0; i8 < i6; i8++) {
            int i9 = i5 + i8;
            this.chars[i9] = charSequence.charAt(i3 + i8);
            this.fields[i9] = field;
        }
        return i7;
    }

    public int insert(int i, char[] cArr, NumberFormat.Field[] fieldArr) {
        int length2 = cArr.length;
        if (length2 == 0) {
            return 0;
        }
        int prepareForInsert = prepareForInsert(i, length2);
        for (int i2 = 0; i2 < length2; i2++) {
            int i3 = prepareForInsert + i2;
            this.chars[i3] = cArr[i2];
            this.fields[i3] = fieldArr == null ? null : fieldArr[i2];
        }
        return length2;
    }

    private int prepareForInsert(int i, int i2) {
        if (i == 0) {
            int i3 = this.zero;
            if (i3 - i2 >= 0) {
                this.zero = i3 - i2;
                this.length += i2;
                return this.zero;
            }
        }
        int i4 = this.length;
        if (i != i4 || this.zero + i4 + i2 >= getCapacity()) {
            return prepareForInsertHelper(i, i2);
        }
        this.length += i2;
        return (this.zero + this.length) - i2;
    }

    private int prepareForInsertHelper(int i, int i2) {
        int capacity = getCapacity();
        int i3 = this.zero;
        char[] cArr = this.chars;
        NumberFormat.Field[] fieldArr = this.fields;
        int i4 = this.length;
        if (i4 + i2 > capacity) {
            int i5 = (i4 + i2) * 2;
            int i6 = (i5 / 2) - ((i4 + i2) / 2);
            char[] cArr2 = new char[i5];
            NumberFormat.Field[] fieldArr2 = new NumberFormat.Field[i5];
            System.arraycopy((Object) cArr, i3, (Object) cArr2, i6, i);
            int i7 = i3 + i;
            int i8 = i6 + i + i2;
            System.arraycopy((Object) cArr, i7, (Object) cArr2, i8, this.length - i);
            System.arraycopy(fieldArr, i3, fieldArr2, i6, i);
            System.arraycopy(fieldArr, i7, fieldArr2, i8, this.length - i);
            this.chars = cArr2;
            this.fields = fieldArr2;
            this.zero = i6;
            this.length += i2;
        } else {
            int i9 = (capacity / 2) - ((i4 + i2) / 2);
            System.arraycopy((Object) cArr, i3, (Object) cArr, i9, i4);
            int i10 = i9 + i;
            int i11 = i10 + i2;
            System.arraycopy((Object) cArr, i10, (Object) cArr, i11, this.length - i);
            System.arraycopy(fieldArr, i3, fieldArr, i9, this.length);
            System.arraycopy(fieldArr, i10, fieldArr, i11, this.length - i);
            this.zero = i9;
            this.length += i2;
        }
        return this.zero + i;
    }

    private int remove(int i, int i2) {
        int i3 = this.zero + i;
        char[] cArr = this.chars;
        int i4 = i3 + i2;
        System.arraycopy((Object) cArr, i4, (Object) cArr, i3, (this.length - i) - i2);
        NumberFormat.Field[] fieldArr = this.fields;
        System.arraycopy(fieldArr, i4, fieldArr, i3, (this.length - i) - i2);
        this.length -= i2;
        return i3;
    }

    private int getCapacity() {
        return this.chars.length;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        if (i < 0 || i2 > this.length || i2 < i) {
            throw new IndexOutOfBoundsException();
        }
        NumberStringBuilder numberStringBuilder = new NumberStringBuilder(this);
        numberStringBuilder.zero = this.zero + i;
        numberStringBuilder.length = i2 - i;
        return numberStringBuilder;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        new String(this.chars, this.zero, this.length);
        throw null;
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

    public int hashCode() {
        throw new UnsupportedOperationException("Don't call #hashCode() or #equals() on a mutable.");
    }

    public boolean equals(Object obj) {
        throw new UnsupportedOperationException("Don't call #hashCode() or #equals() on a mutable.");
    }

    public boolean nextFieldPosition(FieldPosition fieldPosition) {
        Format.Field fieldAttribute = fieldPosition.getFieldAttribute();
        boolean z = false;
        if (fieldAttribute == null) {
            if (fieldPosition.getField() == 0) {
                fieldAttribute = NumberFormat.Field.INTEGER;
            } else if (fieldPosition.getField() != 1) {
                return false;
            } else {
                fieldAttribute = NumberFormat.Field.FRACTION;
            }
        }
        if (fieldAttribute instanceof NumberFormat.Field) {
            NumberFormat.Field field = (NumberFormat.Field) fieldAttribute;
            int endIndex = this.zero + fieldPosition.getEndIndex();
            int i = -1;
            while (true) {
                int i2 = this.zero;
                int i3 = this.length;
                if (endIndex > i2 + i3) {
                    break;
                }
                NumberFormat.Field field2 = endIndex < i2 + i3 ? this.fields[endIndex] : null;
                if (!z || field == field2) {
                    if (!z && field == field2) {
                        fieldPosition.setBeginIndex(endIndex - this.zero);
                        z = true;
                    }
                    if (field2 == NumberFormat.Field.INTEGER || field2 == NumberFormat.Field.DECIMAL_SEPARATOR) {
                        i = (endIndex - this.zero) + 1;
                    }
                } else if (field != NumberFormat.Field.INTEGER || field2 != NumberFormat.Field.GROUPING_SEPARATOR) {
                    fieldPosition.setEndIndex(endIndex - this.zero);
                }
                endIndex++;
            }
            if (field == NumberFormat.Field.FRACTION && !z && i != -1) {
                fieldPosition.setBeginIndex(i);
                fieldPosition.setEndIndex(i);
            }
            return z;
        }
        throw new IllegalArgumentException("You must pass an instance of android.icu.text.NumberFormat.Field as your FieldPosition attribute.  You passed: " + fieldAttribute.getClass().toString());
    }

    public AttributedCharacterIterator toCharacterIterator() {
        toString();
        throw null;
    }
}
