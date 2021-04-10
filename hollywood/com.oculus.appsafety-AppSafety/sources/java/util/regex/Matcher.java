package java.util.regex;

import android.icu.impl.PatternTokenizer;
import libcore.util.NativeAllocationRegistry;

public final class Matcher implements MatchResult {
    private static final NativeAllocationRegistry registry = NativeAllocationRegistry.createMalloced(Matcher.class.getClassLoader(), getNativeFinalizer());
    private long address;
    boolean anchoringBounds = true;
    int appendPos = 0;
    int from;
    int[] groups;
    private boolean matchFound;
    private Runnable nativeFinalizer;
    private CharSequence originalInput;
    private Pattern parentPattern;
    String text;
    int to;
    boolean transparentBounds = false;

    private static native boolean findImpl(long j, int i, int[] iArr);

    private static native boolean findNextImpl(long j, int[] iArr);

    private static native int getMatchedGroupIndex0(long j, String str);

    private static native long getNativeFinalizer();

    private static native int groupCountImpl(long j);

    private static native boolean hitEndImpl(long j);

    private static native boolean lookingAtImpl(long j, int[] iArr);

    private static native boolean matchesImpl(long j, int[] iArr);

    private static native long openImpl(long j);

    private static native boolean requireEndImpl(long j);

    private static native void setInputImpl(long j, String str, int i, int i2);

    private static native void useAnchoringBoundsImpl(long j, boolean z);

    private static native void useTransparentBoundsImpl(long j, boolean z);

    Matcher(Pattern parent, CharSequence text2) {
        usePattern(parent);
        reset(text2);
    }

    public Pattern pattern() {
        return this.parentPattern;
    }

    public MatchResult toMatchResult() {
        ensureMatch();
        return new OffsetBasedMatchResult(this.text, this.groups);
    }

    public Matcher usePattern(Pattern newPattern) {
        if (newPattern != null) {
            this.parentPattern = newPattern;
            synchronized (this) {
                if (this.nativeFinalizer != null) {
                    this.nativeFinalizer.run();
                    this.address = 0;
                    this.nativeFinalizer = null;
                }
                this.address = openImpl(this.parentPattern.address);
                this.nativeFinalizer = registry.registerNativeAllocation(this, this.address);
            }
            if (this.text != null) {
                resetForInput();
            }
            this.groups = new int[((groupCount() + 1) * 2)];
            this.matchFound = false;
            return this;
        }
        throw new IllegalArgumentException("Pattern cannot be null");
    }

    public Matcher reset() {
        CharSequence charSequence = this.originalInput;
        return reset(charSequence, 0, charSequence.length());
    }

    public Matcher reset(CharSequence input) {
        return reset(input, 0, input.length());
    }

    @Override // java.util.regex.MatchResult
    public int start() {
        return start(0);
    }

    @Override // java.util.regex.MatchResult
    public int start(int group) {
        ensureMatch();
        if (group >= 0 && group <= groupCount()) {
            return this.groups[group * 2];
        }
        throw new IndexOutOfBoundsException("No group " + group);
    }

    public int start(String name) {
        return this.groups[getMatchedGroupIndex(name) * 2];
    }

    @Override // java.util.regex.MatchResult
    public int end() {
        return end(0);
    }

    @Override // java.util.regex.MatchResult
    public int end(int group) {
        ensureMatch();
        if (group >= 0 && group <= groupCount()) {
            return this.groups[(group * 2) + 1];
        }
        throw new IndexOutOfBoundsException("No group " + group);
    }

    public int end(String name) {
        return this.groups[(getMatchedGroupIndex(name) * 2) + 1];
    }

    @Override // java.util.regex.MatchResult
    public String group() {
        return group(0);
    }

    @Override // java.util.regex.MatchResult
    public String group(int group) {
        ensureMatch();
        if (group < 0 || group > groupCount()) {
            throw new IndexOutOfBoundsException("No group " + group);
        }
        int[] iArr = this.groups;
        if (iArr[group * 2] == -1 || iArr[(group * 2) + 1] == -1) {
            return null;
        }
        return getSubSequence(iArr[group * 2], iArr[(group * 2) + 1]).toString();
    }

    public String group(String name) {
        int group = getMatchedGroupIndex(name);
        int[] iArr = this.groups;
        if (iArr[group * 2] == -1 || iArr[(group * 2) + 1] == -1) {
            return null;
        }
        return getSubSequence(iArr[group * 2], iArr[(group * 2) + 1]).toString();
    }

    @Override // java.util.regex.MatchResult
    public int groupCount() {
        int groupCountImpl;
        synchronized (this) {
            groupCountImpl = groupCountImpl(this.address);
        }
        return groupCountImpl;
    }

    public boolean matches() {
        synchronized (this) {
            this.matchFound = matchesImpl(this.address, this.groups);
        }
        return this.matchFound;
    }

    public boolean find() {
        synchronized (this) {
            this.matchFound = findNextImpl(this.address, this.groups);
        }
        return this.matchFound;
    }

    public boolean find(int start) {
        int limit = getTextLength();
        if (start < 0 || start > limit) {
            throw new IndexOutOfBoundsException("Illegal start index");
        }
        reset();
        synchronized (this) {
            this.matchFound = findImpl(this.address, start, this.groups);
        }
        return this.matchFound;
    }

    public boolean lookingAt() {
        synchronized (this) {
            this.matchFound = lookingAtImpl(this.address, this.groups);
        }
        return this.matchFound;
    }

    public static String quoteReplacement(String s) {
        if (s.indexOf(92) == -1 && s.indexOf(36) == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' || c == '$') {
                sb.append(PatternTokenizer.BACK_SLASH);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public Matcher appendReplacement(StringBuffer sb, String replacement) {
        sb.append(this.text.substring(this.appendPos, start()));
        appendEvaluated(sb, replacement);
        this.appendPos = end();
        return this;
    }

    private void appendEvaluated(StringBuffer buffer, String s) {
        boolean escape = false;
        boolean dollar = false;
        boolean escapeNamedGroup = false;
        int escapeNamedGroupStart = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '\\' && !escape) {
                escape = true;
            } else if (c == '$' && !escape) {
                dollar = true;
            } else if (c >= '0' && c <= '9' && dollar) {
                buffer.append(group(c - '0'));
                dollar = false;
            } else if (c == '{' && dollar) {
                escapeNamedGroup = true;
                escapeNamedGroupStart = i;
            } else if (c == '}' && dollar && escapeNamedGroup) {
                buffer.append(group(s.substring(escapeNamedGroupStart + 1, i)));
                dollar = false;
                escapeNamedGroup = false;
            } else if (c == '}' || !dollar || !escapeNamedGroup) {
                buffer.append(c);
                dollar = false;
                escape = false;
                escapeNamedGroup = false;
            }
        }
        if (escape) {
            throw new IllegalArgumentException("character to be escaped is missing");
        } else if (dollar) {
            throw new IllegalArgumentException("Illegal group reference: group index is missing");
        } else if (escapeNamedGroup) {
            throw new IllegalArgumentException("Missing ending brace '}' from replacement string");
        }
    }

    public StringBuffer appendTail(StringBuffer sb) {
        int i = this.appendPos;
        int i2 = this.to;
        if (i < i2) {
            sb.append(this.text.substring(i, i2));
        }
        return sb;
    }

    public String replaceAll(String replacement) {
        reset();
        if (!find()) {
            return this.text.toString();
        }
        StringBuffer sb = new StringBuffer();
        do {
            appendReplacement(sb, replacement);
        } while (find());
        appendTail(sb);
        return sb.toString();
    }

    public String replaceFirst(String replacement) {
        if (replacement != null) {
            reset();
            if (!find()) {
                return this.text.toString();
            }
            StringBuffer sb = new StringBuffer();
            appendReplacement(sb, replacement);
            appendTail(sb);
            return sb.toString();
        }
        throw new NullPointerException("replacement");
    }

    public Matcher region(int start, int end) {
        return reset(this.originalInput, start, end);
    }

    public int regionStart() {
        return this.from;
    }

    public int regionEnd() {
        return this.to;
    }

    public boolean hasTransparentBounds() {
        return this.transparentBounds;
    }

    public Matcher useTransparentBounds(boolean b) {
        synchronized (this) {
            this.transparentBounds = b;
            useTransparentBoundsImpl(this.address, b);
        }
        return this;
    }

    public boolean hasAnchoringBounds() {
        return this.anchoringBounds;
    }

    public Matcher useAnchoringBounds(boolean b) {
        synchronized (this) {
            this.anchoringBounds = b;
            useAnchoringBoundsImpl(this.address, b);
        }
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.util.regex.Matcher");
        sb.append("[pattern=" + ((Object) pattern()));
        sb.append(" region=");
        sb.append(regionStart() + "," + regionEnd());
        sb.append(" lastmatch=");
        if (this.matchFound && group() != null) {
            sb.append(group());
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean hitEnd() {
        boolean hitEndImpl;
        synchronized (this) {
            hitEndImpl = hitEndImpl(this.address);
        }
        return hitEndImpl;
    }

    public boolean requireEnd() {
        boolean requireEndImpl;
        synchronized (this) {
            requireEndImpl = requireEndImpl(this.address);
        }
        return requireEndImpl;
    }

    /* access modifiers changed from: package-private */
    public int getTextLength() {
        return this.text.length();
    }

    /* access modifiers changed from: package-private */
    public CharSequence getSubSequence(int beginIndex, int endIndex) {
        return this.text.subSequence(beginIndex, endIndex);
    }

    private Matcher reset(CharSequence input, int start, int end) {
        if (input == null) {
            throw new IllegalArgumentException("input == null");
        } else if (start < 0 || end < 0 || start > input.length() || end > input.length() || start > end) {
            throw new IndexOutOfBoundsException();
        } else {
            this.originalInput = input;
            this.text = input.toString();
            this.from = start;
            this.to = end;
            resetForInput();
            this.matchFound = false;
            this.appendPos = 0;
            return this;
        }
    }

    private void resetForInput() {
        synchronized (this) {
            setInputImpl(this.address, this.text, this.from, this.to);
            useAnchoringBoundsImpl(this.address, this.anchoringBounds);
            useTransparentBoundsImpl(this.address, this.transparentBounds);
        }
    }

    private void ensureMatch() {
        if (!this.matchFound) {
            throw new IllegalStateException("No successful match so far");
        }
    }

    private int getMatchedGroupIndex(String name) {
        ensureMatch();
        int result = getMatchedGroupIndex0(this.parentPattern.address, name);
        if (result >= 0) {
            return result;
        }
        throw new IllegalArgumentException("No capturing group in the pattern with the name " + name);
    }

    static final class OffsetBasedMatchResult implements MatchResult {
        private final String input;
        private final int[] offsets;

        OffsetBasedMatchResult(String input2, int[] offsets2) {
            this.input = input2;
            this.offsets = (int[]) offsets2.clone();
        }

        @Override // java.util.regex.MatchResult
        public int start() {
            return start(0);
        }

        @Override // java.util.regex.MatchResult
        public int start(int group) {
            return this.offsets[group * 2];
        }

        @Override // java.util.regex.MatchResult
        public int end() {
            return end(0);
        }

        @Override // java.util.regex.MatchResult
        public int end(int group) {
            return this.offsets[(group * 2) + 1];
        }

        @Override // java.util.regex.MatchResult
        public String group() {
            return group(0);
        }

        @Override // java.util.regex.MatchResult
        public String group(int group) {
            int start = start(group);
            int end = end(group);
            if (start == -1 || end == -1) {
                return null;
            }
            return this.input.substring(start, end);
        }

        @Override // java.util.regex.MatchResult
        public int groupCount() {
            return (this.offsets.length / 2) - 1;
        }
    }
}
