package java.util.regex;

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

    private static native boolean findNextImpl(long j, int[] iArr);

    private static native int getMatchedGroupIndex0(long j, String str);

    private static native long getNativeFinalizer();

    private static native int groupCountImpl(long j);

    private static native boolean lookingAtImpl(long j, int[] iArr);

    private static native boolean matchesImpl(long j, int[] iArr);

    private static native long openImpl(long j);

    private static native void setInputImpl(long j, String str, int i, int i2);

    private static native void useAnchoringBoundsImpl(long j, boolean z);

    private static native void useTransparentBoundsImpl(long j, boolean z);

    Matcher(Pattern pattern, CharSequence charSequence) {
        usePattern(pattern);
        reset(charSequence);
    }

    public Pattern pattern() {
        return this.parentPattern;
    }

    public Matcher usePattern(Pattern pattern) {
        if (pattern != null) {
            this.parentPattern = pattern;
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
        reset(charSequence, 0, charSequence.length());
        return this;
    }

    public Matcher reset(CharSequence charSequence) {
        reset(charSequence, 0, charSequence.length());
        return this;
    }

    public int start() {
        return start(0);
    }

    public int start(int i) {
        ensureMatch();
        if (i >= 0 && i <= groupCount()) {
            return this.groups[i * 2];
        }
        throw new IndexOutOfBoundsException("No group " + i);
    }

    public int end() {
        return end(0);
    }

    public int end(int i) {
        ensureMatch();
        if (i >= 0 && i <= groupCount()) {
            return this.groups[(i * 2) + 1];
        }
        throw new IndexOutOfBoundsException("No group " + i);
    }

    public String group() {
        return group(0);
    }

    public String group(int i) {
        ensureMatch();
        if (i < 0 || i > groupCount()) {
            throw new IndexOutOfBoundsException("No group " + i);
        }
        int[] iArr = this.groups;
        int i2 = i * 2;
        if (iArr[i2] == -1) {
            return null;
        }
        int i3 = i2 + 1;
        if (iArr[i3] == -1) {
            return null;
        }
        return getSubSequence(iArr[i2], iArr[i3]).toString();
    }

    public String group(String str) {
        int matchedGroupIndex = getMatchedGroupIndex(str);
        int[] iArr = this.groups;
        int i = matchedGroupIndex * 2;
        if (iArr[i] == -1) {
            return null;
        }
        int i2 = i + 1;
        if (iArr[i2] == -1) {
            return null;
        }
        return getSubSequence(iArr[i], iArr[i2]).toString();
    }

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

    public boolean lookingAt() {
        synchronized (this) {
            this.matchFound = lookingAtImpl(this.address, this.groups);
        }
        return this.matchFound;
    }

    public Matcher appendReplacement(StringBuffer stringBuffer, String str) {
        stringBuffer.append(this.text.substring(this.appendPos, start()));
        appendEvaluated(stringBuffer, str);
        this.appendPos = end();
        return this;
    }

    private void appendEvaluated(StringBuffer stringBuffer, String str) {
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i = -1;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\\' && !z) {
                z = true;
            } else if (charAt == '$' && !z) {
                z2 = true;
            } else if (charAt >= '0' && charAt <= '9' && z2) {
                stringBuffer.append(group(charAt - '0'));
                z2 = false;
            } else if (charAt != '{' || !z2) {
                if (charAt == '}' && z2 && z3) {
                    stringBuffer.append(group(str.substring(i + 1, i2)));
                    z2 = false;
                } else if (charAt == '}' || !z2 || !z3) {
                    stringBuffer.append(charAt);
                    z = false;
                    z2 = false;
                }
                z3 = z2;
            } else {
                i = i2;
                z3 = true;
            }
        }
        if (z) {
            throw new IllegalArgumentException("character to be escaped is missing");
        } else if (z2) {
            throw new IllegalArgumentException("Illegal group reference: group index is missing");
        } else if (z3) {
            throw new IllegalArgumentException("Missing ending brace '}' from replacement string");
        }
    }

    public StringBuffer appendTail(StringBuffer stringBuffer) {
        int i = this.appendPos;
        int i2 = this.to;
        if (i < i2) {
            stringBuffer.append(this.text.substring(i, i2));
        }
        return stringBuffer;
    }

    public String replaceAll(String str) {
        reset();
        if (!find()) {
            return this.text.toString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        do {
            appendReplacement(stringBuffer, str);
        } while (find());
        appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public Matcher region(int i, int i2) {
        reset(this.originalInput, i, i2);
        return this;
    }

    public int regionStart() {
        return this.from;
    }

    public int regionEnd() {
        return this.to;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("java.util.regex.Matcher");
        sb.append("[pattern=" + pattern());
        sb.append(" region=");
        sb.append(regionStart() + "," + regionEnd());
        sb.append(" lastmatch=");
        if (this.matchFound && group() != null) {
            sb.append(group());
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public CharSequence getSubSequence(int i, int i2) {
        return this.text.subSequence(i, i2);
    }

    private Matcher reset(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            throw new IllegalArgumentException("input == null");
        } else if (i < 0 || i2 < 0 || i > charSequence.length() || i2 > charSequence.length() || i > i2) {
            throw new IndexOutOfBoundsException();
        } else {
            this.originalInput = charSequence;
            this.text = charSequence.toString();
            this.from = i;
            this.to = i2;
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

    private int getMatchedGroupIndex(String str) {
        ensureMatch();
        int matchedGroupIndex0 = getMatchedGroupIndex0(this.parentPattern.address, str);
        if (matchedGroupIndex0 >= 0) {
            return matchedGroupIndex0;
        }
        throw new IllegalArgumentException("No capturing group in the pattern with the name " + str);
    }
}
