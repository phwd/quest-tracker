package android.icu.impl;

import android.icu.lang.UCharacter;
import android.icu.text.UnicodeSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TextTrieMap<V> {
    boolean _ignoreCase;
    private TextTrieMap<V>.Node _root = new Node();

    public static class Output {
        public int matchLength;
        public boolean partialMatch;
    }

    public interface ResultHandler<V> {
        boolean handlePrefixMatch(int i, Iterator<V> it);
    }

    public TextTrieMap(boolean ignoreCase) {
        this._ignoreCase = ignoreCase;
    }

    public TextTrieMap<V> put(CharSequence text, V val) {
        this._root.add(new CharIterator(text, 0, this._ignoreCase), val);
        return this;
    }

    public Iterator<V> get(String text) {
        return get(text, 0);
    }

    public Iterator<V> get(CharSequence text, int start) {
        return get(text, start, null);
    }

    public Iterator<V> get(CharSequence text, int start, Output output) {
        LongestMatchHandler<V> handler = new LongestMatchHandler<>();
        find(text, start, handler, output);
        if (output != null) {
            output.matchLength = handler.getMatchLength();
        }
        return handler.getMatches();
    }

    public void find(CharSequence text, ResultHandler<V> handler) {
        find(text, 0, handler, (Output) null);
    }

    public void find(CharSequence text, int offset, ResultHandler<V> handler) {
        find(text, offset, handler, (Output) null);
    }

    private void find(CharSequence text, int offset, ResultHandler<V> handler, Output output) {
        find(this._root, new CharIterator(text, offset, this._ignoreCase), handler, output);
    }

    private synchronized void find(TextTrieMap<V>.Node node, CharIterator chitr, ResultHandler<V> handler, Output output) {
        Iterator<V> values = node.values();
        if (values == null || handler.handlePrefixMatch(chitr.processedLength(), values)) {
            TextTrieMap<V>.Node nextMatch = node.findMatch(chitr, output);
            if (nextMatch != null) {
                find(nextMatch, chitr, handler, output);
            }
        }
    }

    public void putLeadCodePoints(UnicodeSet output) {
        this._root.putLeadCodePoints(output);
    }

    public static class CharIterator implements Iterator<Character> {
        private boolean _ignoreCase;
        private int _nextIdx;
        private Character _remainingChar;
        private int _startIdx;
        private CharSequence _text;

        CharIterator(CharSequence text, int offset, boolean ignoreCase) {
            this._text = text;
            this._startIdx = offset;
            this._nextIdx = offset;
            this._ignoreCase = ignoreCase;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this._nextIdx == this._text.length() && this._remainingChar == null) {
                return false;
            }
            return true;
        }

        @Override // java.util.Iterator
        public Character next() {
            if (this._nextIdx == this._text.length() && this._remainingChar == null) {
                return null;
            }
            if (this._remainingChar != null) {
                Character next = this._remainingChar;
                this._remainingChar = null;
                return next;
            } else if (this._ignoreCase) {
                int cp = UCharacter.foldCase(Character.codePointAt(this._text, this._nextIdx), true);
                this._nextIdx += Character.charCount(cp);
                char[] chars = Character.toChars(cp);
                Character next2 = Character.valueOf(chars[0]);
                if (chars.length == 2) {
                    this._remainingChar = Character.valueOf(chars[1]);
                }
                return next2;
            } else {
                Character next3 = Character.valueOf(this._text.charAt(this._nextIdx));
                this._nextIdx++;
                return next3;
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove() not supproted");
        }

        public int nextIndex() {
            return this._nextIdx;
        }

        public int processedLength() {
            if (this._remainingChar == null) {
                return this._nextIdx - this._startIdx;
            }
            throw new IllegalStateException("In the middle of surrogate pair");
        }
    }

    /* access modifiers changed from: private */
    public static class LongestMatchHandler<V> implements ResultHandler<V> {
        private int length;
        private Iterator<V> matches;

        private LongestMatchHandler() {
            this.matches = null;
            this.length = 0;
        }

        @Override // android.icu.impl.TextTrieMap.ResultHandler
        public boolean handlePrefixMatch(int matchLength, Iterator<V> values) {
            if (matchLength <= this.length) {
                return true;
            }
            this.length = matchLength;
            this.matches = values;
            return true;
        }

        public Iterator<V> getMatches() {
            return this.matches;
        }

        public int getMatchLength() {
            return this.length;
        }
    }

    /* access modifiers changed from: private */
    public class Node {
        private List<TextTrieMap<V>.Node> _children;
        private char[] _text;
        private List<V> _values;

        private Node() {
        }

        private Node(char[] text, List<V> values, List<TextTrieMap<V>.Node> children) {
            this._text = text;
            this._values = values;
            this._children = children;
        }

        public int charCount() {
            char[] cArr = this._text;
            if (cArr == null) {
                return 0;
            }
            return cArr.length;
        }

        public Iterator<V> values() {
            List<V> list = this._values;
            if (list == null) {
                return null;
            }
            return list.iterator();
        }

        public void add(CharIterator chitr, V value) {
            StringBuilder buf = new StringBuilder();
            while (chitr.hasNext()) {
                buf.append((Object) chitr.next());
            }
            add(TextTrieMap.toCharArray(buf), 0, value);
        }

        public TextTrieMap<V>.Node findMatch(CharIterator chitr, Output output) {
            if (this._children == null) {
                return null;
            }
            if (!chitr.hasNext()) {
                if (output != null) {
                    output.partialMatch = true;
                }
                return null;
            }
            Character ch = chitr.next();
            for (TextTrieMap<V>.Node child : this._children) {
                if (ch.charValue() < child._text[0]) {
                    return null;
                }
                if (ch.charValue() == child._text[0]) {
                    if (child.matchFollowing(chitr, output)) {
                        return child;
                    }
                    return null;
                }
            }
            return null;
        }

        public void putLeadCodePoints(UnicodeSet output) {
            List<TextTrieMap<V>.Node> list = this._children;
            if (list != null) {
                for (TextTrieMap<V>.Node child : list) {
                    char c0 = child._text[0];
                    if (!UCharacter.isHighSurrogate(c0)) {
                        output.add(c0);
                    } else if (child.charCount() >= 2) {
                        output.add(Character.codePointAt(child._text, 0));
                    } else {
                        List<TextTrieMap<V>.Node> list2 = child._children;
                        if (list2 != null) {
                            for (TextTrieMap<V>.Node grandchild : list2) {
                                output.add(Character.toCodePoint(c0, grandchild._text[0]));
                            }
                        }
                    }
                }
            }
        }

        private void add(char[] text, int offset, V value) {
            TextTrieMap<V>.Node next;
            char[] cArr;
            if (text.length == offset) {
                this._values = addValue(this._values, value);
                return;
            }
            List<TextTrieMap<V>.Node> list = this._children;
            if (list == null) {
                this._children = new LinkedList();
                this._children.add(new Node(TextTrieMap.subArray(text, offset), addValue(null, value), null));
                return;
            }
            ListIterator<TextTrieMap<V>.Node> litr = list.listIterator();
            do {
                if (litr.hasNext()) {
                    next = litr.next();
                    char c = text[offset];
                    cArr = next._text;
                    if (c < cArr[0]) {
                        litr.previous();
                    }
                }
                litr.add(new Node(TextTrieMap.subArray(text, offset), addValue(null, value), null));
                return;
            } while (text[offset] != cArr[0]);
            int matchLen = next.lenMatches(text, offset);
            if (matchLen == next._text.length) {
                next.add(text, offset + matchLen, value);
                return;
            }
            next.split(matchLen);
            next.add(text, offset + matchLen, value);
        }

        private boolean matchFollowing(CharIterator chitr, Output output) {
            for (int idx = 1; idx < this._text.length; idx++) {
                if (!chitr.hasNext()) {
                    if (output != null) {
                        output.partialMatch = true;
                    }
                    return false;
                } else if (chitr.next().charValue() != this._text[idx]) {
                    return false;
                }
            }
            return true;
        }

        private int lenMatches(char[] text, int offset) {
            int textLen = text.length - offset;
            char[] cArr = this._text;
            int limit = cArr.length < textLen ? cArr.length : textLen;
            int len = 0;
            while (len < limit && this._text[len] == text[offset + len]) {
                len++;
            }
            return len;
        }

        private void split(int offset) {
            char[] childText = TextTrieMap.subArray(this._text, offset);
            this._text = TextTrieMap.subArray(this._text, 0, offset);
            TextTrieMap<V>.Node child = new Node(childText, this._values, this._children);
            this._values = null;
            this._children = new LinkedList();
            this._children.add(child);
        }

        private List<V> addValue(List<V> list, V value) {
            if (list == null) {
                list = new LinkedList();
            }
            list.add(value);
            return list;
        }
    }

    /* access modifiers changed from: private */
    public static char[] toCharArray(CharSequence text) {
        char[] array = new char[text.length()];
        for (int i = 0; i < array.length; i++) {
            array[i] = text.charAt(i);
        }
        return array;
    }

    /* access modifiers changed from: private */
    public static char[] subArray(char[] array, int start) {
        if (start == 0) {
            return array;
        }
        char[] sub = new char[(array.length - start)];
        System.arraycopy((Object) array, start, (Object) sub, 0, sub.length);
        return sub;
    }

    /* access modifiers changed from: private */
    public static char[] subArray(char[] array, int start, int limit) {
        if (start == 0 && limit == array.length) {
            return array;
        }
        char[] sub = new char[(limit - start)];
        System.arraycopy((Object) array, start, (Object) sub, 0, limit - start);
        return sub;
    }
}
