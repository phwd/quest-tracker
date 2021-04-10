package android.icu.impl;

import android.icu.lang.UCharacter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TextTrieMap {
    boolean _ignoreCase;
    private Node _root = new Node();

    public static class Output {
        public int matchLength;
        public boolean partialMatch;
    }

    public interface ResultHandler {
        boolean handlePrefixMatch(int i, Iterator it);
    }

    public TextTrieMap(boolean z) {
        this._ignoreCase = z;
    }

    public TextTrieMap put(CharSequence charSequence, Object obj) {
        this._root.add(new CharIterator(charSequence, 0, this._ignoreCase), obj);
        return this;
    }

    public Iterator get(CharSequence charSequence, int i, Output output) {
        LongestMatchHandler longestMatchHandler = new LongestMatchHandler();
        find(charSequence, i, longestMatchHandler, output);
        if (output != null) {
            output.matchLength = longestMatchHandler.getMatchLength();
        }
        return longestMatchHandler.getMatches();
    }

    public void find(CharSequence charSequence, int i, ResultHandler resultHandler) {
        find(charSequence, i, resultHandler, (Output) null);
    }

    private void find(CharSequence charSequence, int i, ResultHandler resultHandler, Output output) {
        find(this._root, new CharIterator(charSequence, i, this._ignoreCase), resultHandler, output);
    }

    private synchronized void find(Node node, CharIterator charIterator, ResultHandler resultHandler, Output output) {
        Iterator values = node.values();
        if (values == null || resultHandler.handlePrefixMatch(charIterator.processedLength(), values)) {
            Node findMatch = node.findMatch(charIterator, output);
            if (findMatch != null) {
                find(findMatch, charIterator, resultHandler, output);
            }
        }
    }

    public static class CharIterator implements Iterator {
        private boolean _ignoreCase;
        private int _nextIdx;
        private Character _remainingChar;
        private int _startIdx;
        private CharSequence _text;

        CharIterator(CharSequence charSequence, int i, boolean z) {
            this._text = charSequence;
            this._startIdx = i;
            this._nextIdx = i;
            this._ignoreCase = z;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return (this._nextIdx == this._text.length() && this._remainingChar == null) ? false : true;
        }

        @Override // java.util.Iterator
        public Character next() {
            if (this._nextIdx == this._text.length() && this._remainingChar == null) {
                return null;
            }
            Character ch = this._remainingChar;
            if (ch != null) {
                this._remainingChar = null;
                return ch;
            } else if (this._ignoreCase) {
                int foldCase = UCharacter.foldCase(Character.codePointAt(this._text, this._nextIdx), true);
                this._nextIdx += Character.charCount(foldCase);
                char[] chars = Character.toChars(foldCase);
                Character valueOf = Character.valueOf(chars[0]);
                if (chars.length == 2) {
                    this._remainingChar = Character.valueOf(chars[1]);
                }
                return valueOf;
            } else {
                Character valueOf2 = Character.valueOf(this._text.charAt(this._nextIdx));
                this._nextIdx++;
                return valueOf2;
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove() not supproted");
        }

        public int processedLength() {
            if (this._remainingChar == null) {
                return this._nextIdx - this._startIdx;
            }
            throw new IllegalStateException("In the middle of surrogate pair");
        }
    }

    private static class LongestMatchHandler implements ResultHandler {
        private int length;
        private Iterator matches;

        private LongestMatchHandler() {
            this.matches = null;
            this.length = 0;
        }

        @Override // android.icu.impl.TextTrieMap.ResultHandler
        public boolean handlePrefixMatch(int i, Iterator it) {
            if (i <= this.length) {
                return true;
            }
            this.length = i;
            this.matches = it;
            return true;
        }

        public Iterator getMatches() {
            return this.matches;
        }

        public int getMatchLength() {
            return this.length;
        }
    }

    /* access modifiers changed from: private */
    public class Node {
        private List _children;
        private char[] _text;
        private List _values;

        private Node() {
        }

        private Node(char[] cArr, List list, List list2) {
            this._text = cArr;
            this._values = list;
            this._children = list2;
        }

        public Iterator values() {
            List list = this._values;
            if (list == null) {
                return null;
            }
            return list.iterator();
        }

        public void add(CharIterator charIterator, Object obj) {
            StringBuilder sb = new StringBuilder();
            while (charIterator.hasNext()) {
                sb.append(charIterator.next());
            }
            add(TextTrieMap.toCharArray(sb), 0, obj);
        }

        public Node findMatch(CharIterator charIterator, Output output) {
            if (this._children == null) {
                return null;
            }
            if (!charIterator.hasNext()) {
                if (output != null) {
                    output.partialMatch = true;
                }
                return null;
            }
            Character next = charIterator.next();
            for (Node node : this._children) {
                if (next.charValue() < node._text[0]) {
                    return null;
                }
                if (next.charValue() == node._text[0]) {
                    if (node.matchFollowing(charIterator, output)) {
                        return node;
                    }
                    return null;
                }
            }
            return null;
        }

        private void add(char[] cArr, int i, Object obj) {
            Node node;
            char[] cArr2;
            if (cArr.length == i) {
                this._values = addValue(this._values, obj);
                return;
            }
            List list = this._children;
            if (list == null) {
                this._children = new LinkedList();
                this._children.add(new Node(TextTrieMap.subArray(cArr, i), addValue(null, obj), null));
                return;
            }
            ListIterator listIterator = list.listIterator();
            do {
                if (listIterator.hasNext()) {
                    node = (Node) listIterator.next();
                    char c = cArr[i];
                    cArr2 = node._text;
                    if (c < cArr2[0]) {
                        listIterator.previous();
                    }
                }
                listIterator.add(new Node(TextTrieMap.subArray(cArr, i), addValue(null, obj), null));
                return;
            } while (cArr[i] != cArr2[0]);
            int lenMatches = node.lenMatches(cArr, i);
            if (lenMatches == node._text.length) {
                node.add(cArr, i + lenMatches, obj);
                return;
            }
            node.split(lenMatches);
            node.add(cArr, i + lenMatches, obj);
        }

        private boolean matchFollowing(CharIterator charIterator, Output output) {
            for (int i = 1; i < this._text.length; i++) {
                if (!charIterator.hasNext()) {
                    if (output == null) {
                        return false;
                    }
                    output.partialMatch = true;
                    return false;
                } else if (charIterator.next().charValue() != this._text[i]) {
                    return false;
                }
            }
            return true;
        }

        private int lenMatches(char[] cArr, int i) {
            int length = cArr.length - i;
            char[] cArr2 = this._text;
            if (cArr2.length < length) {
                length = cArr2.length;
            }
            int i2 = 0;
            while (i2 < length && this._text[i2] == cArr[i + i2]) {
                i2++;
            }
            return i2;
        }

        private void split(int i) {
            char[] subArray = TextTrieMap.subArray(this._text, i);
            this._text = TextTrieMap.subArray(this._text, 0, i);
            Node node = new Node(subArray, this._values, this._children);
            this._values = null;
            this._children = new LinkedList();
            this._children.add(node);
        }

        private List addValue(List list, Object obj) {
            if (list == null) {
                list = new LinkedList();
            }
            list.add(obj);
            return list;
        }
    }

    /* access modifiers changed from: private */
    public static char[] toCharArray(CharSequence charSequence) {
        char[] cArr = new char[charSequence.length()];
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = charSequence.charAt(i);
        }
        return cArr;
    }

    /* access modifiers changed from: private */
    public static char[] subArray(char[] cArr, int i) {
        if (i == 0) {
            return cArr;
        }
        char[] cArr2 = new char[(cArr.length - i)];
        System.arraycopy((Object) cArr, i, (Object) cArr2, 0, cArr2.length);
        return cArr2;
    }

    /* access modifiers changed from: private */
    public static char[] subArray(char[] cArr, int i, int i2) {
        if (i == 0 && i2 == cArr.length) {
            return cArr;
        }
        int i3 = i2 - i;
        char[] cArr2 = new char[i3];
        System.arraycopy((Object) cArr, i, (Object) cArr2, 0, i3);
        return cArr2;
    }
}
