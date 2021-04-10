package java.text;

import java.text.AttributedCharacterIterator;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class AttributedString {
    int runArraySize;
    Vector[] runAttributeValues;
    Vector[] runAttributes;
    int runCount;
    int[] runStarts;
    String text;

    AttributedString(AttributedCharacterIterator[] attributedCharacterIteratorArr) {
        if (attributedCharacterIteratorArr == null) {
            throw new NullPointerException("Iterators must not be null");
        } else if (attributedCharacterIteratorArr.length == 0) {
            this.text = "";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            for (AttributedCharacterIterator attributedCharacterIterator : attributedCharacterIteratorArr) {
                appendContents(stringBuffer, attributedCharacterIterator);
            }
            this.text = stringBuffer.toString();
            if (this.text.length() > 0) {
                Map map = null;
                int i2 = 0;
                while (i < attributedCharacterIteratorArr.length) {
                    AttributedCharacterIterator attributedCharacterIterator2 = attributedCharacterIteratorArr[i];
                    int beginIndex = attributedCharacterIterator2.getBeginIndex();
                    int endIndex = attributedCharacterIterator2.getEndIndex();
                    Map map2 = map;
                    int i3 = beginIndex;
                    while (i3 < endIndex) {
                        attributedCharacterIterator2.setIndex(i3);
                        Map attributes = attributedCharacterIterator2.getAttributes();
                        if (mapsDiffer(map2, attributes)) {
                            setAttributes(attributes, (i3 - beginIndex) + i2);
                        }
                        i3 = attributedCharacterIterator2.getRunLimit();
                        map2 = attributes;
                    }
                    i2 += endIndex - beginIndex;
                    i++;
                    map = map2;
                }
            }
        }
    }

    public AttributedString(String str) {
        if (str != null) {
            this.text = str;
            return;
        }
        throw new NullPointerException();
    }

    public AttributedString(AttributedCharacterIterator attributedCharacterIterator) {
        this(attributedCharacterIterator, attributedCharacterIterator.getBeginIndex(), attributedCharacterIterator.getEndIndex(), null);
    }

    public AttributedString(AttributedCharacterIterator attributedCharacterIterator, int i, int i2, AttributedCharacterIterator.Attribute[] attributeArr) {
        if (attributedCharacterIterator != null) {
            int beginIndex = attributedCharacterIterator.getBeginIndex();
            int endIndex = attributedCharacterIterator.getEndIndex();
            if (i < beginIndex || i2 > endIndex || i > i2) {
                throw new IllegalArgumentException("Invalid substring range");
            }
            StringBuffer stringBuffer = new StringBuffer();
            attributedCharacterIterator.setIndex(i);
            char current = attributedCharacterIterator.current();
            while (attributedCharacterIterator.getIndex() < i2) {
                stringBuffer.append(current);
                current = attributedCharacterIterator.next();
            }
            this.text = stringBuffer.toString();
            if (i != i2) {
                HashSet hashSet = new HashSet();
                if (attributeArr == null) {
                    hashSet.addAll(attributedCharacterIterator.getAllAttributeKeys());
                } else {
                    for (AttributedCharacterIterator.Attribute attribute : attributeArr) {
                        hashSet.add(attribute);
                    }
                    hashSet.retainAll(attributedCharacterIterator.getAllAttributeKeys());
                }
                if (!hashSet.isEmpty()) {
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        AttributedCharacterIterator.Attribute attribute2 = (AttributedCharacterIterator.Attribute) it.next();
                        attributedCharacterIterator.setIndex(beginIndex);
                        while (attributedCharacterIterator.getIndex() < i2) {
                            int runStart = attributedCharacterIterator.getRunStart(attribute2);
                            int runLimit = attributedCharacterIterator.getRunLimit(attribute2);
                            Object attribute3 = attributedCharacterIterator.getAttribute(attribute2);
                            if (attribute3 != null) {
                                if (runStart >= i2) {
                                    break;
                                } else if (runLimit > i) {
                                    runStart = runStart < i ? i : runStart;
                                    runLimit = runLimit > i2 ? i2 : runLimit;
                                    if (runStart != runLimit) {
                                        addAttribute(attribute2, attribute3, runStart - i, runLimit - i);
                                    }
                                }
                            }
                            attributedCharacterIterator.setIndex(runLimit);
                        }
                    }
                    return;
                }
                return;
            }
            return;
        }
        throw new NullPointerException();
    }

    public void addAttribute(AttributedCharacterIterator.Attribute attribute, Object obj) {
        if (attribute != null) {
            int length = length();
            if (length != 0) {
                addAttributeImpl(attribute, obj, 0, length);
                return;
            }
            throw new IllegalArgumentException("Can't add attribute to 0-length text");
        }
        throw new NullPointerException();
    }

    public void addAttribute(AttributedCharacterIterator.Attribute attribute, Object obj, int i, int i2) {
        if (attribute == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 > length() || i >= i2) {
            throw new IllegalArgumentException("Invalid substring range");
        } else {
            addAttributeImpl(attribute, obj, i, i2);
        }
    }

    private synchronized void addAttributeImpl(AttributedCharacterIterator.Attribute attribute, Object obj, int i, int i2) {
        if (this.runCount == 0) {
            createRunAttributeDataVectors();
        }
        addAttributeRunData(attribute, obj, ensureRunBreak(i), ensureRunBreak(i2));
    }

    private final void createRunAttributeDataVectors() {
        this.runStarts = new int[10];
        this.runAttributes = new Vector[10];
        this.runAttributeValues = new Vector[10];
        this.runArraySize = 10;
        this.runCount = 1;
    }

    private final int ensureRunBreak(int i) {
        return ensureRunBreak(i, true);
    }

    private final int ensureRunBreak(int i, boolean z) {
        Vector vector;
        if (i == length()) {
            return this.runCount;
        }
        int i2 = 0;
        while (i2 < this.runCount && this.runStarts[i2] < i) {
            i2++;
        }
        if (i2 < this.runCount && this.runStarts[i2] == i) {
            return i2;
        }
        int i3 = this.runCount;
        int i4 = this.runArraySize;
        if (i3 == i4) {
            int i5 = i4 + 10;
            int[] iArr = new int[i5];
            Vector[] vectorArr = new Vector[i5];
            Vector[] vectorArr2 = new Vector[i5];
            for (int i6 = 0; i6 < this.runArraySize; i6++) {
                iArr[i6] = this.runStarts[i6];
                vectorArr[i6] = this.runAttributes[i6];
                vectorArr2[i6] = this.runAttributeValues[i6];
            }
            this.runStarts = iArr;
            this.runAttributes = vectorArr;
            this.runAttributeValues = vectorArr2;
            this.runArraySize = i5;
        }
        Vector vector2 = null;
        if (z) {
            int i7 = i2 - 1;
            Vector vector3 = this.runAttributes[i7];
            Vector vector4 = this.runAttributeValues[i7];
            vector = vector3 != null ? new Vector(vector3) : null;
            if (vector4 != null) {
                vector2 = new Vector(vector4);
            }
        } else {
            vector = null;
        }
        this.runCount++;
        for (int i8 = this.runCount - 1; i8 > i2; i8--) {
            int[] iArr2 = this.runStarts;
            int i9 = i8 - 1;
            iArr2[i8] = iArr2[i9];
            Vector[] vectorArr3 = this.runAttributes;
            vectorArr3[i8] = vectorArr3[i9];
            Vector[] vectorArr4 = this.runAttributeValues;
            vectorArr4[i8] = vectorArr4[i9];
        }
        this.runStarts[i2] = i;
        this.runAttributes[i2] = vector;
        this.runAttributeValues[i2] = vector2;
        return i2;
    }

    private void addAttributeRunData(AttributedCharacterIterator.Attribute attribute, Object obj, int i, int i2) {
        int i3;
        while (i < i2) {
            Vector[] vectorArr = this.runAttributes;
            if (vectorArr[i] == null) {
                Vector vector = new Vector();
                Vector vector2 = new Vector();
                this.runAttributes[i] = vector;
                this.runAttributeValues[i] = vector2;
                i3 = -1;
            } else {
                i3 = vectorArr[i].indexOf(attribute);
            }
            if (i3 == -1) {
                int size = this.runAttributes[i].size();
                this.runAttributes[i].addElement(attribute);
                try {
                    this.runAttributeValues[i].addElement(obj);
                } catch (Exception unused) {
                    this.runAttributes[i].setSize(size);
                    this.runAttributeValues[i].setSize(size);
                }
            } else {
                this.runAttributeValues[i].set(i3, obj);
            }
            i++;
        }
    }

    public AttributedCharacterIterator getIterator() {
        return getIterator(null, 0, length());
    }

    public AttributedCharacterIterator getIterator(AttributedCharacterIterator.Attribute[] attributeArr, int i, int i2) {
        return new AttributedStringIterator(attributeArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    public int length() {
        return this.text.length();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private char charAt(int i) {
        return this.text.charAt(i);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized Object getAttribute(AttributedCharacterIterator.Attribute attribute, int i) {
        Vector vector = this.runAttributes[i];
        Vector vector2 = this.runAttributeValues[i];
        if (vector == null) {
            return null;
        }
        int indexOf = vector.indexOf(attribute);
        if (indexOf == -1) {
            return null;
        }
        return vector2.elementAt(indexOf);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object getAttributeCheckRange(AttributedCharacterIterator.Attribute attribute, int i, int i2, int i3) {
        return getAttribute(attribute, i);
    }

    /* access modifiers changed from: private */
    public static final boolean valuesMatch(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    private final void appendContents(StringBuffer stringBuffer, CharacterIterator characterIterator) {
        int endIndex = characterIterator.getEndIndex();
        for (int beginIndex = characterIterator.getBeginIndex(); beginIndex < endIndex; beginIndex++) {
            characterIterator.setIndex(beginIndex);
            stringBuffer.append(characterIterator.current());
        }
    }

    private void setAttributes(Map map, int i) {
        int size;
        if (this.runCount == 0) {
            createRunAttributeDataVectors();
        }
        int ensureRunBreak = ensureRunBreak(i, false);
        if (map != null && (size = map.size()) > 0) {
            Vector vector = new Vector(size);
            Vector vector2 = new Vector(size);
            for (Map.Entry entry : map.entrySet()) {
                vector.add((AttributedCharacterIterator.Attribute) entry.getKey());
                vector2.add(entry.getValue());
            }
            this.runAttributes[ensureRunBreak] = vector;
            this.runAttributeValues[ensureRunBreak] = vector2;
        }
    }

    private static boolean mapsDiffer(Map map, Map map2) {
        if (map == null) {
            return map2 != null && map2.size() > 0;
        }
        return !map.equals(map2);
    }

    /* access modifiers changed from: private */
    public final class AttributedStringIterator implements AttributedCharacterIterator {
        private int beginIndex;
        private int currentIndex;
        private int currentRunIndex;
        private int currentRunLimit;
        private int currentRunStart;
        private int endIndex;
        private AttributedCharacterIterator.Attribute[] relevantAttributes;

        AttributedStringIterator(AttributedCharacterIterator.Attribute[] attributeArr, int i, int i2) {
            if (i < 0 || i > i2 || i2 > AttributedString.this.length()) {
                throw new IllegalArgumentException("Invalid substring range");
            }
            this.beginIndex = i;
            this.endIndex = i2;
            this.currentIndex = i;
            updateRunInfo();
            if (attributeArr != null) {
                this.relevantAttributes = (AttributedCharacterIterator.Attribute[]) attributeArr.clone();
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AttributedStringIterator)) {
                return false;
            }
            AttributedStringIterator attributedStringIterator = (AttributedStringIterator) obj;
            return AttributedString.this == attributedStringIterator.getString() && this.currentIndex == attributedStringIterator.currentIndex && this.beginIndex == attributedStringIterator.beginIndex && this.endIndex == attributedStringIterator.endIndex;
        }

        public int hashCode() {
            return this.endIndex ^ ((AttributedString.this.text.hashCode() ^ this.currentIndex) ^ this.beginIndex);
        }

        @Override // java.text.CharacterIterator
        public Object clone() {
            try {
                return (AttributedStringIterator) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new InternalError(e);
            }
        }

        @Override // java.text.CharacterIterator
        public char first() {
            return internalSetIndex(this.beginIndex);
        }

        @Override // java.text.CharacterIterator
        public char current() {
            int i = this.currentIndex;
            if (i == this.endIndex) {
                return 65535;
            }
            return AttributedString.this.charAt(i);
        }

        @Override // java.text.CharacterIterator
        public char next() {
            int i = this.currentIndex;
            if (i < this.endIndex) {
                return internalSetIndex(i + 1);
            }
            return 65535;
        }

        @Override // java.text.CharacterIterator
        public char previous() {
            int i = this.currentIndex;
            if (i > this.beginIndex) {
                return internalSetIndex(i - 1);
            }
            return 65535;
        }

        @Override // java.text.CharacterIterator
        public char setIndex(int i) {
            if (i >= this.beginIndex && i <= this.endIndex) {
                return internalSetIndex(i);
            }
            throw new IllegalArgumentException("Invalid index");
        }

        @Override // java.text.CharacterIterator
        public int getBeginIndex() {
            return this.beginIndex;
        }

        @Override // java.text.CharacterIterator
        public int getEndIndex() {
            return this.endIndex;
        }

        @Override // java.text.CharacterIterator
        public int getIndex() {
            return this.currentIndex;
        }

        @Override // java.text.AttributedCharacterIterator
        public int getRunStart() {
            return this.currentRunStart;
        }

        @Override // java.text.AttributedCharacterIterator
        public int getRunStart(AttributedCharacterIterator.Attribute attribute) {
            if (this.currentRunStart == this.beginIndex || this.currentRunIndex == -1) {
                return this.currentRunStart;
            }
            Object attribute2 = getAttribute(attribute);
            int i = this.currentRunStart;
            int i2 = this.currentRunIndex;
            while (i > this.beginIndex && AttributedString.valuesMatch(attribute2, AttributedString.this.getAttribute(attribute, i2 - 1))) {
                i2--;
                i = AttributedString.this.runStarts[i2];
            }
            int i3 = this.beginIndex;
            return i < i3 ? i3 : i;
        }

        @Override // java.text.AttributedCharacterIterator
        public int getRunLimit() {
            return this.currentRunLimit;
        }

        @Override // java.text.AttributedCharacterIterator
        public int getRunLimit(AttributedCharacterIterator.Attribute attribute) {
            if (this.currentRunLimit == this.endIndex || this.currentRunIndex == -1) {
                return this.currentRunLimit;
            }
            Object attribute2 = getAttribute(attribute);
            int i = this.currentRunLimit;
            int i2 = this.currentRunIndex;
            while (i < this.endIndex) {
                i2++;
                if (!AttributedString.valuesMatch(attribute2, AttributedString.this.getAttribute(attribute, i2))) {
                    break;
                }
                AttributedString attributedString = AttributedString.this;
                i = i2 < attributedString.runCount + -1 ? attributedString.runStarts[i2 + 1] : this.endIndex;
            }
            int i3 = this.endIndex;
            return i > i3 ? i3 : i;
        }

        @Override // java.text.AttributedCharacterIterator
        public Map getAttributes() {
            int i;
            AttributedString attributedString = AttributedString.this;
            Vector[] vectorArr = attributedString.runAttributes;
            if (vectorArr == null || (i = this.currentRunIndex) == -1 || vectorArr[i] == null) {
                return new Hashtable();
            }
            return new AttributeMap(i, this.beginIndex, this.endIndex);
        }

        @Override // java.text.AttributedCharacterIterator
        public Set getAllAttributeKeys() {
            HashSet hashSet;
            Vector vector;
            AttributedString attributedString = AttributedString.this;
            if (attributedString.runAttributes == null) {
                return new HashSet();
            }
            synchronized (attributedString) {
                hashSet = new HashSet();
                for (int i = 0; i < AttributedString.this.runCount; i++) {
                    if (AttributedString.this.runStarts[i] < this.endIndex && ((i == AttributedString.this.runCount - 1 || AttributedString.this.runStarts[i + 1] > this.beginIndex) && (vector = AttributedString.this.runAttributes[i]) != null)) {
                        int size = vector.size();
                        while (true) {
                            int i2 = size - 1;
                            if (size <= 0) {
                                break;
                            }
                            hashSet.add((AttributedCharacterIterator.Attribute) vector.get(i2));
                            size = i2;
                        }
                    }
                }
            }
            return hashSet;
        }

        @Override // java.text.AttributedCharacterIterator
        public Object getAttribute(AttributedCharacterIterator.Attribute attribute) {
            int i = this.currentRunIndex;
            if (i < 0) {
                return null;
            }
            return AttributedString.this.getAttributeCheckRange(attribute, i, this.beginIndex, this.endIndex);
        }

        private AttributedString getString() {
            return AttributedString.this;
        }

        private char internalSetIndex(int i) {
            this.currentIndex = i;
            if (i < this.currentRunStart || i >= this.currentRunLimit) {
                updateRunInfo();
            }
            if (this.currentIndex == this.endIndex) {
                return 65535;
            }
            return AttributedString.this.charAt(i);
        }

        private void updateRunInfo() {
            int i = this.currentIndex;
            int i2 = this.endIndex;
            int i3 = -1;
            if (i == i2) {
                this.currentRunLimit = i2;
                this.currentRunStart = i2;
                this.currentRunIndex = -1;
                return;
            }
            synchronized (AttributedString.this) {
                while (i3 < AttributedString.this.runCount - 1) {
                    int i4 = i3 + 1;
                    if (AttributedString.this.runStarts[i4] > this.currentIndex) {
                        break;
                    }
                    i3 = i4;
                }
                this.currentRunIndex = i3;
                if (i3 >= 0) {
                    this.currentRunStart = AttributedString.this.runStarts[i3];
                    if (this.currentRunStart < this.beginIndex) {
                        this.currentRunStart = this.beginIndex;
                    }
                } else {
                    this.currentRunStart = this.beginIndex;
                }
                if (i3 < AttributedString.this.runCount - 1) {
                    this.currentRunLimit = AttributedString.this.runStarts[i3 + 1];
                    if (this.currentRunLimit > this.endIndex) {
                        this.currentRunLimit = this.endIndex;
                    }
                } else {
                    this.currentRunLimit = this.endIndex;
                }
            }
        }
    }

    private final class AttributeMap extends AbstractMap {
        int beginIndex;
        int endIndex;
        int runIndex;

        AttributeMap(int i, int i2, int i3) {
            this.runIndex = i;
            this.beginIndex = i2;
            this.endIndex = i3;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set entrySet() {
            HashSet hashSet = new HashSet();
            synchronized (AttributedString.this) {
                int size = AttributedString.this.runAttributes[this.runIndex].size();
                for (int i = 0; i < size; i++) {
                    hashSet.add(new AttributeEntry((AttributedCharacterIterator.Attribute) AttributedString.this.runAttributes[this.runIndex].get(i), AttributedString.this.runAttributeValues[this.runIndex].get(i)));
                }
            }
            return hashSet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Object get(Object obj) {
            return AttributedString.this.getAttributeCheckRange((AttributedCharacterIterator.Attribute) obj, this.runIndex, this.beginIndex, this.endIndex);
        }
    }
}
