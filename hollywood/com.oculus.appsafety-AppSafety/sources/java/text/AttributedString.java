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
    private static final int ARRAY_SIZE_INCREMENT = 10;
    int runArraySize;
    Vector<Object>[] runAttributeValues;
    Vector<AttributedCharacterIterator.Attribute>[] runAttributes;
    int runCount;
    int[] runStarts;
    String text;

    AttributedString(AttributedCharacterIterator[] iterators) {
        if (iterators == null) {
            throw new NullPointerException("Iterators must not be null");
        } else if (iterators.length == 0) {
            this.text = "";
        } else {
            StringBuffer buffer = new StringBuffer();
            for (AttributedCharacterIterator attributedCharacterIterator : iterators) {
                appendContents(buffer, attributedCharacterIterator);
            }
            this.text = buffer.toString();
            if (this.text.length() > 0) {
                int offset = 0;
                Map<AttributedCharacterIterator.Attribute, Object> last = null;
                for (AttributedCharacterIterator iterator : iterators) {
                    int start = iterator.getBeginIndex();
                    int end = iterator.getEndIndex();
                    for (int index = start; index < end; index = iterator.getRunLimit()) {
                        iterator.setIndex(index);
                        Map<AttributedCharacterIterator.Attribute, Object> attrs = iterator.getAttributes();
                        if (mapsDiffer(last, attrs)) {
                            setAttributes(attrs, (index - start) + offset);
                        }
                        last = attrs;
                    }
                    offset += end - start;
                }
            }
        }
    }

    public AttributedString(String text2) {
        if (text2 != null) {
            this.text = text2;
            return;
        }
        throw new NullPointerException();
    }

    public AttributedString(String text2, Map<? extends AttributedCharacterIterator.Attribute, ?> attributes) {
        if (text2 == null || attributes == null) {
            throw new NullPointerException();
        }
        this.text = text2;
        if (text2.length() != 0) {
            int attributeCount = attributes.size();
            if (attributeCount > 0) {
                createRunAttributeDataVectors();
                Vector<AttributedCharacterIterator.Attribute> newRunAttributes = new Vector<>(attributeCount);
                Vector<Object> newRunAttributeValues = new Vector<>(attributeCount);
                this.runAttributes[0] = newRunAttributes;
                this.runAttributeValues[0] = newRunAttributeValues;
                for (Map.Entry<? extends AttributedCharacterIterator.Attribute, ?> entry : attributes.entrySet()) {
                    newRunAttributes.addElement((AttributedCharacterIterator.Attribute) entry.getKey());
                    newRunAttributeValues.addElement(entry.getValue());
                }
            }
        } else if (!attributes.isEmpty()) {
            throw new IllegalArgumentException("Can't add attribute to 0-length text");
        }
    }

    public AttributedString(AttributedCharacterIterator text2) {
        this(text2, text2.getBeginIndex(), text2.getEndIndex(), null);
    }

    public AttributedString(AttributedCharacterIterator text2, int beginIndex, int endIndex) {
        this(text2, beginIndex, endIndex, null);
    }

    public AttributedString(AttributedCharacterIterator text2, int beginIndex, int endIndex, AttributedCharacterIterator.Attribute[] attributes) {
        if (text2 != null) {
            int textBeginIndex = text2.getBeginIndex();
            int textEndIndex = text2.getEndIndex();
            if (beginIndex < textBeginIndex || endIndex > textEndIndex || beginIndex > endIndex) {
                throw new IllegalArgumentException("Invalid substring range");
            }
            StringBuffer textBuffer = new StringBuffer();
            text2.setIndex(beginIndex);
            char c = text2.current();
            while (text2.getIndex() < endIndex) {
                textBuffer.append(c);
                c = text2.next();
            }
            this.text = textBuffer.toString();
            if (beginIndex != endIndex) {
                HashSet<AttributedCharacterIterator.Attribute> keys = new HashSet<>();
                if (attributes == null) {
                    keys.addAll(text2.getAllAttributeKeys());
                } else {
                    for (AttributedCharacterIterator.Attribute attribute : attributes) {
                        keys.add(attribute);
                    }
                    keys.retainAll(text2.getAllAttributeKeys());
                }
                if (!keys.isEmpty()) {
                    Iterator<AttributedCharacterIterator.Attribute> itr = keys.iterator();
                    while (itr.hasNext()) {
                        AttributedCharacterIterator.Attribute attributeKey = itr.next();
                        text2.setIndex(textBeginIndex);
                        while (text2.getIndex() < endIndex) {
                            int start = text2.getRunStart(attributeKey);
                            int limit = text2.getRunLimit(attributeKey);
                            Object value = text2.getAttribute(attributeKey);
                            if (value != null) {
                                if (value instanceof Annotation) {
                                    if (start >= beginIndex && limit <= endIndex) {
                                        addAttribute(attributeKey, value, start - beginIndex, limit - beginIndex);
                                    } else if (limit > endIndex) {
                                        break;
                                    }
                                } else if (start >= endIndex) {
                                    break;
                                } else if (limit > beginIndex) {
                                    start = start < beginIndex ? beginIndex : start;
                                    limit = limit > endIndex ? endIndex : limit;
                                    if (start != limit) {
                                        addAttribute(attributeKey, value, start - beginIndex, limit - beginIndex);
                                    }
                                }
                            }
                            text2.setIndex(limit);
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

    public void addAttribute(AttributedCharacterIterator.Attribute attribute, Object value) {
        if (attribute != null) {
            int len = length();
            if (len != 0) {
                addAttributeImpl(attribute, value, 0, len);
                return;
            }
            throw new IllegalArgumentException("Can't add attribute to 0-length text");
        }
        throw new NullPointerException();
    }

    public void addAttribute(AttributedCharacterIterator.Attribute attribute, Object value, int beginIndex, int endIndex) {
        if (attribute == null) {
            throw new NullPointerException();
        } else if (beginIndex < 0 || endIndex > length() || beginIndex >= endIndex) {
            throw new IllegalArgumentException("Invalid substring range");
        } else {
            addAttributeImpl(attribute, value, beginIndex, endIndex);
        }
    }

    public void addAttributes(Map<? extends AttributedCharacterIterator.Attribute, ?> attributes, int beginIndex, int endIndex) {
        if (attributes == null) {
            throw new NullPointerException();
        } else if (beginIndex < 0 || endIndex > length() || beginIndex > endIndex) {
            throw new IllegalArgumentException("Invalid substring range");
        } else if (beginIndex != endIndex) {
            if (this.runCount == 0) {
                createRunAttributeDataVectors();
            }
            int beginRunIndex = ensureRunBreak(beginIndex);
            int endRunIndex = ensureRunBreak(endIndex);
            for (Map.Entry<? extends AttributedCharacterIterator.Attribute, ?> entry : attributes.entrySet()) {
                addAttributeRunData((AttributedCharacterIterator.Attribute) entry.getKey(), entry.getValue(), beginRunIndex, endRunIndex);
            }
        } else if (!attributes.isEmpty()) {
            throw new IllegalArgumentException("Can't add attribute to 0-length text");
        }
    }

    private synchronized void addAttributeImpl(AttributedCharacterIterator.Attribute attribute, Object value, int beginIndex, int endIndex) {
        if (this.runCount == 0) {
            createRunAttributeDataVectors();
        }
        addAttributeRunData(attribute, value, ensureRunBreak(beginIndex), ensureRunBreak(endIndex));
    }

    private final void createRunAttributeDataVectors() {
        this.runStarts = new int[10];
        this.runAttributes = new Vector[10];
        this.runAttributeValues = new Vector[10];
        this.runArraySize = 10;
        this.runCount = 1;
    }

    private final int ensureRunBreak(int offset) {
        return ensureRunBreak(offset, true);
    }

    private final int ensureRunBreak(int offset, boolean copyAttrs) {
        if (offset == length()) {
            return this.runCount;
        }
        int runIndex = 0;
        while (runIndex < this.runCount && this.runStarts[runIndex] < offset) {
            runIndex++;
        }
        if (runIndex < this.runCount && this.runStarts[runIndex] == offset) {
            return runIndex;
        }
        int i = this.runCount;
        int i2 = this.runArraySize;
        if (i == i2) {
            int newArraySize = i2 + 10;
            int[] newRunStarts = new int[newArraySize];
            Vector<AttributedCharacterIterator.Attribute>[] newRunAttributes = new Vector[newArraySize];
            Vector<Object>[] newRunAttributeValues = new Vector[newArraySize];
            for (int i3 = 0; i3 < this.runArraySize; i3++) {
                newRunStarts[i3] = this.runStarts[i3];
                newRunAttributes[i3] = this.runAttributes[i3];
                newRunAttributeValues[i3] = this.runAttributeValues[i3];
            }
            this.runStarts = newRunStarts;
            this.runAttributes = newRunAttributes;
            this.runAttributeValues = newRunAttributeValues;
            this.runArraySize = newArraySize;
        }
        Vector<AttributedCharacterIterator.Attribute> newRunAttributes2 = null;
        Vector<Object> newRunAttributeValues2 = null;
        if (copyAttrs) {
            Vector<AttributedCharacterIterator.Attribute> oldRunAttributes = this.runAttributes[runIndex - 1];
            Vector<Object> oldRunAttributeValues = this.runAttributeValues[runIndex - 1];
            if (oldRunAttributes != null) {
                newRunAttributes2 = new Vector<>(oldRunAttributes);
            }
            if (oldRunAttributeValues != null) {
                newRunAttributeValues2 = new Vector<>(oldRunAttributeValues);
            }
        }
        this.runCount++;
        for (int i4 = this.runCount - 1; i4 > runIndex; i4--) {
            int[] iArr = this.runStarts;
            iArr[i4] = iArr[i4 - 1];
            Vector<AttributedCharacterIterator.Attribute>[] vectorArr = this.runAttributes;
            vectorArr[i4] = vectorArr[i4 - 1];
            Vector<Object>[] vectorArr2 = this.runAttributeValues;
            vectorArr2[i4] = vectorArr2[i4 - 1];
        }
        this.runStarts[runIndex] = offset;
        this.runAttributes[runIndex] = newRunAttributes2;
        this.runAttributeValues[runIndex] = newRunAttributeValues2;
        return runIndex;
    }

    private void addAttributeRunData(AttributedCharacterIterator.Attribute attribute, Object value, int beginRunIndex, int endRunIndex) {
        for (int i = beginRunIndex; i < endRunIndex; i++) {
            int keyValueIndex = -1;
            Vector<AttributedCharacterIterator.Attribute>[] vectorArr = this.runAttributes;
            if (vectorArr[i] == null) {
                Vector<AttributedCharacterIterator.Attribute> newRunAttributes = new Vector<>();
                Vector<Object> newRunAttributeValues = new Vector<>();
                this.runAttributes[i] = newRunAttributes;
                this.runAttributeValues[i] = newRunAttributeValues;
            } else {
                keyValueIndex = vectorArr[i].indexOf(attribute);
            }
            if (keyValueIndex == -1) {
                int oldSize = this.runAttributes[i].size();
                this.runAttributes[i].addElement(attribute);
                try {
                    this.runAttributeValues[i].addElement(value);
                } catch (Exception e) {
                    this.runAttributes[i].setSize(oldSize);
                    this.runAttributeValues[i].setSize(oldSize);
                }
            } else {
                this.runAttributeValues[i].set(keyValueIndex, value);
            }
        }
    }

    public AttributedCharacterIterator getIterator() {
        return getIterator(null, 0, length());
    }

    public AttributedCharacterIterator getIterator(AttributedCharacterIterator.Attribute[] attributes) {
        return getIterator(attributes, 0, length());
    }

    public AttributedCharacterIterator getIterator(AttributedCharacterIterator.Attribute[] attributes, int beginIndex, int endIndex) {
        return new AttributedStringIterator(attributes, beginIndex, endIndex);
    }

    /* access modifiers changed from: package-private */
    public int length() {
        return this.text.length();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private char charAt(int index) {
        return this.text.charAt(index);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized Object getAttribute(AttributedCharacterIterator.Attribute attribute, int runIndex) {
        Vector<AttributedCharacterIterator.Attribute> currentRunAttributes = this.runAttributes[runIndex];
        Vector<Object> currentRunAttributeValues = this.runAttributeValues[runIndex];
        if (currentRunAttributes == null) {
            return null;
        }
        int attributeIndex = currentRunAttributes.indexOf(attribute);
        if (attributeIndex == -1) {
            return null;
        }
        return currentRunAttributeValues.elementAt(attributeIndex);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object getAttributeCheckRange(AttributedCharacterIterator.Attribute attribute, int runIndex, int beginIndex, int endIndex) {
        Object value = getAttribute(attribute, runIndex);
        if (value instanceof Annotation) {
            if (beginIndex > 0) {
                int currIndex = runIndex;
                int runStart = this.runStarts[currIndex];
                while (runStart >= beginIndex && valuesMatch(value, getAttribute(attribute, currIndex - 1))) {
                    currIndex--;
                    runStart = this.runStarts[currIndex];
                }
                if (runStart < beginIndex) {
                    return null;
                }
            }
            int textLength = length();
            if (endIndex < textLength) {
                int currIndex2 = runIndex;
                int runLimit = currIndex2 < this.runCount + -1 ? this.runStarts[currIndex2 + 1] : textLength;
                while (runLimit <= endIndex && valuesMatch(value, getAttribute(attribute, currIndex2 + 1))) {
                    currIndex2++;
                    runLimit = currIndex2 < this.runCount + -1 ? this.runStarts[currIndex2 + 1] : textLength;
                }
                if (runLimit > endIndex) {
                    return null;
                }
            }
        }
        return value;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean attributeValuesMatch(Set<? extends AttributedCharacterIterator.Attribute> attributes, int runIndex1, int runIndex2) {
        for (AttributedCharacterIterator.Attribute key : attributes) {
            if (!valuesMatch(getAttribute(key, runIndex1), getAttribute(key, runIndex2))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static final boolean valuesMatch(Object value1, Object value2) {
        if (value1 == null) {
            return value2 == null;
        }
        return value1.equals(value2);
    }

    private final void appendContents(StringBuffer buf, CharacterIterator iterator) {
        int end = iterator.getEndIndex();
        for (int index = iterator.getBeginIndex(); index < end; index++) {
            iterator.setIndex(index);
            buf.append(iterator.current());
        }
    }

    private void setAttributes(Map<AttributedCharacterIterator.Attribute, Object> attrs, int offset) {
        int size;
        if (this.runCount == 0) {
            createRunAttributeDataVectors();
        }
        int index = ensureRunBreak(offset, false);
        if (attrs != null && (size = attrs.size()) > 0) {
            Vector<AttributedCharacterIterator.Attribute> runAttrs = new Vector<>(size);
            Vector<Object> runValues = new Vector<>(size);
            for (Map.Entry<AttributedCharacterIterator.Attribute, Object> entry : attrs.entrySet()) {
                runAttrs.add(entry.getKey());
                runValues.add(entry.getValue());
            }
            this.runAttributes[index] = runAttrs;
            this.runAttributeValues[index] = runValues;
        }
    }

    private static <K, V> boolean mapsDiffer(Map<K, V> last, Map<K, V> attrs) {
        if (last != null) {
            return true ^ last.equals(attrs);
        }
        if (attrs == null || attrs.size() <= 0) {
            return false;
        }
        return true;
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

        AttributedStringIterator(AttributedCharacterIterator.Attribute[] attributes, int beginIndex2, int endIndex2) {
            if (beginIndex2 < 0 || beginIndex2 > endIndex2 || endIndex2 > AttributedString.this.length()) {
                throw new IllegalArgumentException("Invalid substring range");
            }
            this.beginIndex = beginIndex2;
            this.endIndex = endIndex2;
            this.currentIndex = beginIndex2;
            updateRunInfo();
            if (attributes != null) {
                this.relevantAttributes = (AttributedCharacterIterator.Attribute[]) attributes.clone();
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AttributedStringIterator)) {
                return false;
            }
            AttributedStringIterator that = (AttributedStringIterator) obj;
            if (AttributedString.this == that.getString() && this.currentIndex == that.currentIndex && this.beginIndex == that.beginIndex && this.endIndex == that.endIndex) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((AttributedString.this.text.hashCode() ^ this.currentIndex) ^ this.beginIndex) ^ this.endIndex;
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
        public char last() {
            int i = this.endIndex;
            if (i == this.beginIndex) {
                return internalSetIndex(i);
            }
            return internalSetIndex(i - 1);
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
        public char setIndex(int position) {
            if (position >= this.beginIndex && position <= this.endIndex) {
                return internalSetIndex(position);
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
            Object value = getAttribute(attribute);
            int runStart = this.currentRunStart;
            int runIndex = this.currentRunIndex;
            while (runStart > this.beginIndex && AttributedString.valuesMatch(value, AttributedString.this.getAttribute(attribute, runIndex - 1))) {
                runIndex--;
                runStart = AttributedString.this.runStarts[runIndex];
            }
            if (runStart < this.beginIndex) {
                return this.beginIndex;
            }
            return runStart;
        }

        @Override // java.text.AttributedCharacterIterator
        public int getRunStart(Set<? extends AttributedCharacterIterator.Attribute> attributes) {
            if (this.currentRunStart == this.beginIndex || this.currentRunIndex == -1) {
                return this.currentRunStart;
            }
            int runStart = this.currentRunStart;
            int runIndex = this.currentRunIndex;
            while (runStart > this.beginIndex && AttributedString.this.attributeValuesMatch(attributes, this.currentRunIndex, runIndex - 1)) {
                runIndex--;
                runStart = AttributedString.this.runStarts[runIndex];
            }
            if (runStart < this.beginIndex) {
                return this.beginIndex;
            }
            return runStart;
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
            Object value = getAttribute(attribute);
            int runLimit = this.currentRunLimit;
            int runIndex = this.currentRunIndex;
            while (runLimit < this.endIndex && AttributedString.valuesMatch(value, AttributedString.this.getAttribute(attribute, runIndex + 1))) {
                runIndex++;
                runLimit = runIndex < AttributedString.this.runCount + -1 ? AttributedString.this.runStarts[runIndex + 1] : this.endIndex;
            }
            if (runLimit > this.endIndex) {
                return this.endIndex;
            }
            return runLimit;
        }

        @Override // java.text.AttributedCharacterIterator
        public int getRunLimit(Set<? extends AttributedCharacterIterator.Attribute> attributes) {
            if (this.currentRunLimit == this.endIndex || this.currentRunIndex == -1) {
                return this.currentRunLimit;
            }
            int runLimit = this.currentRunLimit;
            int runIndex = this.currentRunIndex;
            while (runLimit < this.endIndex && AttributedString.this.attributeValuesMatch(attributes, this.currentRunIndex, runIndex + 1)) {
                runIndex++;
                runLimit = runIndex < AttributedString.this.runCount + -1 ? AttributedString.this.runStarts[runIndex + 1] : this.endIndex;
            }
            if (runLimit > this.endIndex) {
                return this.endIndex;
            }
            return runLimit;
        }

        @Override // java.text.AttributedCharacterIterator
        public Map<AttributedCharacterIterator.Attribute, Object> getAttributes() {
            if (!(AttributedString.this.runAttributes == null || this.currentRunIndex == -1)) {
                Vector<AttributedCharacterIterator.Attribute>[] vectorArr = AttributedString.this.runAttributes;
                int i = this.currentRunIndex;
                if (vectorArr[i] != null) {
                    return new AttributeMap(i, this.beginIndex, this.endIndex);
                }
            }
            return new Hashtable();
        }

        @Override // java.text.AttributedCharacterIterator
        public Set<AttributedCharacterIterator.Attribute> getAllAttributeKeys() {
            Set<AttributedCharacterIterator.Attribute> keys;
            Vector<AttributedCharacterIterator.Attribute> currentRunAttributes;
            if (AttributedString.this.runAttributes == null) {
                return new HashSet();
            }
            synchronized (AttributedString.this) {
                keys = new HashSet<>();
                for (int i = 0; i < AttributedString.this.runCount; i++) {
                    if (AttributedString.this.runStarts[i] < this.endIndex && ((i == AttributedString.this.runCount - 1 || AttributedString.this.runStarts[i + 1] > this.beginIndex) && (currentRunAttributes = AttributedString.this.runAttributes[i]) != null)) {
                        int j = currentRunAttributes.size();
                        while (true) {
                            int j2 = j - 1;
                            if (j <= 0) {
                                break;
                            }
                            keys.add(currentRunAttributes.get(j2));
                            j = j2;
                        }
                    }
                }
            }
            return keys;
        }

        @Override // java.text.AttributedCharacterIterator
        public Object getAttribute(AttributedCharacterIterator.Attribute attribute) {
            int runIndex = this.currentRunIndex;
            if (runIndex < 0) {
                return null;
            }
            return AttributedString.this.getAttributeCheckRange(attribute, runIndex, this.beginIndex, this.endIndex);
        }

        private AttributedString getString() {
            return AttributedString.this;
        }

        private char internalSetIndex(int position) {
            this.currentIndex = position;
            if (position < this.currentRunStart || position >= this.currentRunLimit) {
                updateRunInfo();
            }
            if (this.currentIndex == this.endIndex) {
                return 65535;
            }
            return AttributedString.this.charAt(position);
        }

        private void updateRunInfo() {
            int i = this.currentIndex;
            int i2 = this.endIndex;
            if (i == i2) {
                this.currentRunLimit = i2;
                this.currentRunStart = i2;
                this.currentRunIndex = -1;
                return;
            }
            synchronized (AttributedString.this) {
                int runIndex = -1;
                while (runIndex < AttributedString.this.runCount - 1 && AttributedString.this.runStarts[runIndex + 1] <= this.currentIndex) {
                    runIndex++;
                }
                this.currentRunIndex = runIndex;
                if (runIndex >= 0) {
                    this.currentRunStart = AttributedString.this.runStarts[runIndex];
                    if (this.currentRunStart < this.beginIndex) {
                        this.currentRunStart = this.beginIndex;
                    }
                } else {
                    this.currentRunStart = this.beginIndex;
                }
                if (runIndex < AttributedString.this.runCount - 1) {
                    this.currentRunLimit = AttributedString.this.runStarts[runIndex + 1];
                    if (this.currentRunLimit > this.endIndex) {
                        this.currentRunLimit = this.endIndex;
                    }
                } else {
                    this.currentRunLimit = this.endIndex;
                }
            }
        }
    }

    private final class AttributeMap extends AbstractMap<AttributedCharacterIterator.Attribute, Object> {
        int beginIndex;
        int endIndex;
        int runIndex;

        AttributeMap(int runIndex2, int beginIndex2, int endIndex2) {
            this.runIndex = runIndex2;
            this.beginIndex = beginIndex2;
            this.endIndex = endIndex2;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<AttributedCharacterIterator.Attribute, Object>> entrySet() {
            HashSet<Map.Entry<AttributedCharacterIterator.Attribute, Object>> set = new HashSet<>();
            synchronized (AttributedString.this) {
                int size = AttributedString.this.runAttributes[this.runIndex].size();
                for (int i = 0; i < size; i++) {
                    AttributedCharacterIterator.Attribute key = AttributedString.this.runAttributes[this.runIndex].get(i);
                    Object value = AttributedString.this.runAttributeValues[this.runIndex].get(i);
                    if (!(value instanceof Annotation) || (value = AttributedString.this.getAttributeCheckRange(key, this.runIndex, this.beginIndex, this.endIndex)) != null) {
                        set.add(new AttributeEntry(key, value));
                    }
                }
            }
            return set;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Object get(Object key) {
            return AttributedString.this.getAttributeCheckRange((AttributedCharacterIterator.Attribute) key, this.runIndex, this.beginIndex, this.endIndex);
        }
    }
}
