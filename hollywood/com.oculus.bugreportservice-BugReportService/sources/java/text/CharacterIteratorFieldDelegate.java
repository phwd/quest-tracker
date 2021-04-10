package java.text;

import java.text.Format;
import java.util.ArrayList;

class CharacterIteratorFieldDelegate implements Format.FieldDelegate {
    private ArrayList attributedStrings = new ArrayList();
    private int size;

    CharacterIteratorFieldDelegate() {
    }

    public void formatted(Format.Field field, Object obj, int i, int i2, StringBuffer stringBuffer) {
        if (i != i2) {
            int i3 = this.size;
            if (i < i3) {
                int size2 = this.attributedStrings.size() - 1;
                while (i < i3) {
                    int i4 = size2 - 1;
                    AttributedString attributedString = (AttributedString) this.attributedStrings.get(size2);
                    i3 -= attributedString.length();
                    int max = Math.max(0, i - i3);
                    attributedString.addAttribute(field, obj, max, Math.min(i2 - i, attributedString.length() - max) + max);
                    size2 = i4;
                }
            }
            int i5 = this.size;
            if (i5 < i) {
                this.attributedStrings.add(new AttributedString(stringBuffer.substring(i5, i)));
                this.size = i;
            }
            int i6 = this.size;
            if (i6 < i2) {
                AttributedString attributedString2 = new AttributedString(stringBuffer.substring(Math.max(i, i6), i2));
                attributedString2.addAttribute(field, obj);
                this.attributedStrings.add(attributedString2);
                this.size = i2;
            }
        }
    }

    @Override // java.text.Format.FieldDelegate
    public void formatted(int i, Format.Field field, Object obj, int i2, int i3, StringBuffer stringBuffer) {
        formatted(field, obj, i2, i3, stringBuffer);
    }

    public AttributedCharacterIterator getIterator(String str) {
        int length = str.length();
        int i = this.size;
        if (length > i) {
            this.attributedStrings.add(new AttributedString(str.substring(i)));
            this.size = str.length();
        }
        int size2 = this.attributedStrings.size();
        AttributedCharacterIterator[] attributedCharacterIteratorArr = new AttributedCharacterIterator[size2];
        for (int i2 = 0; i2 < size2; i2++) {
            attributedCharacterIteratorArr[i2] = ((AttributedString) this.attributedStrings.get(i2)).getIterator();
        }
        return new AttributedString(attributedCharacterIteratorArr).getIterator();
    }
}
