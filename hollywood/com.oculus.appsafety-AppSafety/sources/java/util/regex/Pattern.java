package java.util.regex;

import android.icu.lang.UCharacter;
import dalvik.system.VMRuntime;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import libcore.util.EmptyArray;
import libcore.util.NativeAllocationRegistry;

public final class Pattern implements Serializable {
    public static final int CANON_EQ = 128;
    public static final int CASE_INSENSITIVE = 2;
    public static final int COMMENTS = 4;
    public static final int DOTALL = 32;
    private static final String FASTSPLIT_METACHARACTERS = "\\?*+[](){}^$.|";
    public static final int LITERAL = 16;
    public static final int MULTILINE = 8;
    public static final int UNICODE_CASE = 64;
    public static final int UNICODE_CHARACTER_CLASS = 256;
    public static final int UNIX_LINES = 1;
    private static final NativeAllocationRegistry registry = NativeAllocationRegistry.createMalloced(Pattern.class.getClassLoader(), getNativeFinalizer());
    private static final long serialVersionUID = 5073258162644648461L;
    transient long address;
    private final int flags;
    private final String pattern;

    private static native long compileImpl(String str, int i);

    private static native long getNativeFinalizer();

    public static Pattern compile(String regex) {
        return new Pattern(regex, 0);
    }

    public static Pattern compile(String regex, int flags2) {
        return new Pattern(regex, flags2);
    }

    public String pattern() {
        return this.pattern;
    }

    public String toString() {
        return this.pattern;
    }

    public Matcher matcher(CharSequence input) {
        return new Matcher(this, input);
    }

    public int flags() {
        return this.flags;
    }

    public static boolean matches(String regex, CharSequence input) {
        return compile(regex).matcher(input).matches();
    }

    public String[] split(CharSequence input, int limit) {
        String[] fast = fastSplit(this.pattern, input.toString(), limit);
        if (fast != null) {
            return fast;
        }
        int index = 0;
        boolean matchLimited = limit > 0;
        ArrayList<String> matchList = new ArrayList<>();
        Matcher m = matcher(input);
        while (m.find()) {
            if (!matchLimited || matchList.size() < limit - 1) {
                if (index != 0 || index != m.start() || m.start() != m.end() || VMRuntime.getRuntime().getTargetSdkVersion() <= 28) {
                    matchList.add(input.subSequence(index, m.start()).toString());
                    index = m.end();
                }
            } else if (matchList.size() == limit - 1) {
                matchList.add(input.subSequence(index, input.length()).toString());
                index = m.end();
            }
        }
        if (index == 0) {
            return new String[]{input.toString()};
        }
        if (!matchLimited || matchList.size() < limit) {
            matchList.add(input.subSequence(index, input.length()).toString());
        }
        int resultSize = matchList.size();
        if (limit == 0) {
            while (resultSize > 0 && matchList.get(resultSize - 1).equals("")) {
                resultSize--;
            }
        }
        return (String[]) matchList.subList(0, resultSize).toArray(new String[resultSize]);
    }

    public static String[] fastSplit(String re, String input, int limit) {
        int end;
        int len = re.length();
        if (len == 0) {
            return null;
        }
        char ch = re.charAt(0);
        if (!(len == 1 && FASTSPLIT_METACHARACTERS.indexOf(ch) == -1)) {
            if (!(len == 2 && ch == '\\')) {
                return null;
            }
            ch = re.charAt(1);
            if (FASTSPLIT_METACHARACTERS.indexOf(ch) == -1) {
                return null;
            }
        }
        if (input.isEmpty()) {
            return new String[]{""};
        }
        int separatorCount = 0;
        int begin = 0;
        while (separatorCount + 1 != limit && (end = input.indexOf(ch, begin)) != -1) {
            separatorCount++;
            begin = end + 1;
        }
        int lastPartEnd = input.length();
        if (limit == 0 && begin == lastPartEnd) {
            if (separatorCount == lastPartEnd) {
                return EmptyArray.STRING;
            }
            do {
                begin--;
            } while (input.charAt(begin - 1) == ch);
            separatorCount -= input.length() - begin;
            lastPartEnd = begin;
        }
        String[] result = new String[(separatorCount + 1)];
        int begin2 = 0;
        for (int i = 0; i != separatorCount; i++) {
            int end2 = input.indexOf(ch, begin2);
            result[i] = input.substring(begin2, end2);
            begin2 = end2 + 1;
        }
        result[separatorCount] = input.substring(begin2, lastPartEnd);
        return result;
    }

    public String[] split(CharSequence input) {
        return split(input, 0);
    }

    public static String quote(String s) {
        if (s.indexOf("\\E") == -1) {
            return "\\Q" + s + "\\E";
        }
        StringBuilder sb = new StringBuilder(s.length() * 2);
        sb.append("\\Q");
        int current = 0;
        while (true) {
            int slashEIndex = s.indexOf("\\E", current);
            if (slashEIndex != -1) {
                sb.append(s.substring(current, slashEIndex));
                current = slashEIndex + 2;
                sb.append("\\E\\\\E\\Q");
            } else {
                sb.append(s.substring(current, s.length()));
                sb.append("\\E");
                return sb.toString();
            }
        }
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        compile();
    }

    private Pattern(String p, int f) {
        this.pattern = p;
        this.flags = f;
        if ((f & 128) != 0) {
            throw new UnsupportedOperationException("CANON_EQ flag not supported");
        } else if (((~127) & f) == 0) {
            compile();
        } else {
            throw new IllegalArgumentException("Unsupported flags: " + ((~127) & f));
        }
    }

    private void compile() throws PatternSyntaxException {
        String str = this.pattern;
        if (str != null) {
            String icuPattern = this.pattern;
            if ((this.flags & 16) != 0) {
                icuPattern = quote(str);
            }
            this.address = compileImpl(icuPattern, this.flags & 47);
            registry.registerNativeAllocation(this, this.address);
            return;
        }
        throw new NullPointerException("pattern == null");
    }

    public Predicate<String> asPredicate() {
        return new Predicate() {
            /* class java.util.regex.$$Lambda$Pattern$2enjXc1GhRFNtcTfhbg9qy2smk */

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Pattern.this.lambda$asPredicate$0$Pattern((String) obj);
            }
        };
    }

    public /* synthetic */ boolean lambda$asPredicate$0$Pattern(String s) {
        return matcher(s).find();
    }

    public Stream<String> splitAsStream(final CharSequence input) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new Iterator<String>() {
            /* class java.util.regex.Pattern.AnonymousClass1MatcherIterator */
            private int current;
            private int emptyElementCount;
            private final Matcher matcher;
            private String nextElement;

            /* JADX WARN: Incorrect args count in method signature: ()V */
            {
                this.matcher = Pattern.this.matcher(input);
            }

            @Override // java.util.Iterator
            public String next() {
                if (hasNext()) {
                    int i = this.emptyElementCount;
                    if (i == 0) {
                        String n = this.nextElement;
                        this.nextElement = null;
                        return n;
                    }
                    this.emptyElementCount = i - 1;
                    return "";
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextElement != null || this.emptyElementCount > 0) {
                    return true;
                }
                if (this.current == input.length()) {
                    return false;
                }
                while (this.matcher.find()) {
                    this.nextElement = input.subSequence(this.current, this.matcher.start()).toString();
                    this.current = this.matcher.end();
                    if (!this.nextElement.isEmpty()) {
                        return true;
                    }
                    if (this.current > 0) {
                        this.emptyElementCount++;
                    }
                }
                CharSequence charSequence = input;
                this.nextElement = charSequence.subSequence(this.current, charSequence.length()).toString();
                this.current = input.length();
                if (!this.nextElement.isEmpty()) {
                    return true;
                }
                this.emptyElementCount = 0;
                this.nextElement = null;
                return false;
            }
        }, (int) UCharacter.UnicodeBlock.TANGUT_ID), false);
    }
}
