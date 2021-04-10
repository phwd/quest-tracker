package java.util.regex;

import dalvik.system.VMRuntime;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import libcore.util.EmptyArray;
import libcore.util.NativeAllocationRegistry;

public final class Pattern implements Serializable {
    private static final NativeAllocationRegistry registry = NativeAllocationRegistry.createMalloced(Pattern.class.getClassLoader(), getNativeFinalizer());
    private static final long serialVersionUID = 5073258162644648461L;
    transient long address;
    private final int flags;
    private final String pattern;

    private static native long compileImpl(String str, int i);

    private static native long getNativeFinalizer();

    public static Pattern compile(String str) {
        return new Pattern(str, 0);
    }

    public static Pattern compile(String str, int i) {
        return new Pattern(str, i);
    }

    public String toString() {
        return this.pattern;
    }

    public Matcher matcher(CharSequence charSequence) {
        return new Matcher(this, charSequence);
    }

    public static boolean matches(String str, CharSequence charSequence) {
        return compile(str).matcher(charSequence).matches();
    }

    public String[] split(CharSequence charSequence, int i) {
        int i2;
        String[] fastSplit = fastSplit(this.pattern, charSequence.toString(), i);
        if (fastSplit != null) {
            return fastSplit;
        }
        boolean z = i > 0;
        ArrayList arrayList = new ArrayList();
        Matcher matcher = matcher(charSequence);
        int i3 = 0;
        while (matcher.find()) {
            if (!z || arrayList.size() < i - 1) {
                if (i3 != 0 || i3 != matcher.start() || matcher.start() != matcher.end() || VMRuntime.getRuntime().getTargetSdkVersion() <= 28) {
                    arrayList.add(charSequence.subSequence(i3, matcher.start()).toString());
                    i3 = matcher.end();
                }
            } else if (arrayList.size() == i2) {
                arrayList.add(charSequence.subSequence(i3, charSequence.length()).toString());
                i3 = matcher.end();
            }
        }
        if (i3 == 0) {
            return new String[]{charSequence.toString()};
        }
        if (!z || arrayList.size() < i) {
            arrayList.add(charSequence.subSequence(i3, charSequence.length()).toString());
        }
        int size = arrayList.size();
        if (i == 0) {
            while (size > 0 && ((String) arrayList.get(size - 1)).equals("")) {
                size--;
            }
        }
        return (String[]) arrayList.subList(0, size).toArray(new String[size]);
    }

    public static String[] fastSplit(String str, String str2, int i) {
        int indexOf;
        int length = str.length();
        if (length == 0) {
            return null;
        }
        char charAt = str.charAt(0);
        if (!(length == 1 && "\\?*+[](){}^$.|".indexOf(charAt) == -1)) {
            if (!(length == 2 && charAt == '\\')) {
                return null;
            }
            charAt = str.charAt(1);
            if ("\\?*+[](){}^$.|".indexOf(charAt) == -1) {
                return null;
            }
        }
        if (str2.isEmpty()) {
            return new String[]{""};
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 1;
            if (i4 == i || (indexOf = str2.indexOf(charAt, i3)) == -1) {
                int length2 = str2.length();
            } else {
                i3 = indexOf + 1;
                i2 = i4;
            }
        }
        int length22 = str2.length();
        if (i != 0 || i3 != length22) {
            i3 = length22;
        } else if (i2 == length22) {
            return EmptyArray.STRING;
        } else {
            do {
                i3--;
            } while (str2.charAt(i3 - 1) == charAt);
            i2 -= str2.length() - i3;
        }
        String[] strArr = new String[(i2 + 1)];
        int i5 = 0;
        for (int i6 = 0; i6 != i2; i6++) {
            int indexOf2 = str2.indexOf(charAt, i5);
            strArr[i6] = str2.substring(i5, indexOf2);
            i5 = indexOf2 + 1;
        }
        strArr[i2] = str2.substring(i5, i3);
        return strArr;
    }

    public String[] split(CharSequence charSequence) {
        return split(charSequence, 0);
    }

    public static String quote(String str) {
        if (str.indexOf("\\E") == -1) {
            return "\\Q" + str + "\\E";
        }
        StringBuilder sb = new StringBuilder(str.length() * 2);
        sb.append("\\Q");
        int i = 0;
        while (true) {
            int indexOf = str.indexOf("\\E", i);
            if (indexOf != -1) {
                sb.append(str.substring(i, indexOf));
                i = indexOf + 2;
                sb.append("\\E\\\\E\\Q");
            } else {
                sb.append(str.substring(i, str.length()));
                sb.append("\\E");
                return sb.toString();
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }

    private Pattern(String str, int i) {
        this.pattern = str;
        this.flags = i;
        if ((i & 128) == 0) {
            int i2 = i & -128;
            if (i2 == 0) {
                compile();
                return;
            }
            throw new IllegalArgumentException("Unsupported flags: " + i2);
        }
        throw new UnsupportedOperationException("CANON_EQ flag not supported");
    }

    private void compile() {
        String str = this.pattern;
        if (str != null) {
            if ((this.flags & 16) != 0) {
                str = quote(str);
            }
            this.address = compileImpl(str, this.flags & 47);
            registry.registerNativeAllocation(this, this.address);
            return;
        }
        throw new NullPointerException("pattern == null");
    }
}
