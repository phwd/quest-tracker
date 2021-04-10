package android.icu.text;

import android.icu.impl.PatternProps;
import android.icu.impl.Utility;
import android.icu.util.CaseInsensitiveString;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class TransliteratorIDParser {
    private static final Map SPECIAL_INVERSES = Collections.synchronizedMap(new HashMap());

    /* access modifiers changed from: private */
    public static class Specs {
        public String filter;
        public boolean sawSource;
        public String source;
        public String target;
        public String variant;

        Specs(String str, String str2, String str3, boolean z, String str4) {
            this.source = str;
            this.target = str2;
            this.variant = str3;
            this.sawSource = z;
            this.filter = str4;
        }
    }

    /* access modifiers changed from: package-private */
    public static class SingleID {
        public String basicID;
        public String canonID;
        public String filter;

        SingleID(String str, String str2, String str3) {
            this.canonID = str;
            this.basicID = str2;
            this.filter = str3;
        }

        SingleID(String str, String str2) {
            this(str, str2, null);
        }

        /* access modifiers changed from: package-private */
        public Transliterator getInstance() {
            Transliterator transliterator;
            String str;
            String str2 = this.basicID;
            if (str2 == null || str2.length() == 0) {
                transliterator = Transliterator.getBasicInstance("Any-Null", this.canonID);
            } else {
                transliterator = Transliterator.getBasicInstance(this.basicID, this.canonID);
            }
            if (!(transliterator == null || (str = this.filter) == null)) {
                transliterator.setFilter(new UnicodeSet(str));
            }
            return transliterator;
        }
    }

    public static SingleID parseFilterID(String str, int[] iArr) {
        int i = iArr[0];
        Specs parseFilterID = parseFilterID(str, iArr, true);
        if (parseFilterID == null) {
            iArr[0] = i;
            return null;
        }
        SingleID specsToID = specsToID(parseFilterID, 0);
        specsToID.filter = parseFilterID.filter;
        return specsToID;
    }

    public static SingleID parseSingleID(String str, int[] iArr, int i) {
        boolean z;
        SingleID singleID;
        int i2 = iArr[0];
        Specs specs = null;
        Specs specs2 = null;
        int i3 = 1;
        while (true) {
            if (i3 > 2) {
                z = false;
                break;
            } else if (i3 == 2 && (specs2 = parseFilterID(str, iArr, true)) == null) {
                iArr[0] = i2;
                return null;
            } else if (!Utility.parseChar(str, iArr, '(')) {
                i3++;
            } else if (!Utility.parseChar(str, iArr, ')')) {
                Specs parseFilterID = parseFilterID(str, iArr, true);
                if (parseFilterID == null || !Utility.parseChar(str, iArr, ')')) {
                    iArr[0] = i2;
                    return null;
                }
                z = true;
                specs = parseFilterID;
            } else {
                z = true;
            }
        }
        if (!z) {
            if (i == 0) {
                singleID = specsToID(specs2, 0);
            } else {
                singleID = specsToSpecialInverse(specs2);
                if (singleID == null) {
                    singleID = specsToID(specs2, 1);
                }
            }
            singleID.filter = specs2.filter;
        } else if (i == 0) {
            singleID = specsToID(specs2, 0);
            singleID.canonID += '(' + specsToID(specs, 0).canonID + ')';
            if (specs2 != null) {
                singleID.filter = specs2.filter;
            }
        } else {
            singleID = specsToID(specs, 0);
            singleID.canonID += '(' + specsToID(specs2, 0).canonID + ')';
            if (specs != null) {
                singleID.filter = specs.filter;
            }
        }
        return singleID;
    }

    public static UnicodeSet parseGlobalFilter(String str, int[] iArr, int i, int[] iArr2, StringBuffer stringBuffer) {
        int i2 = iArr[0];
        if (iArr2[0] == -1) {
            iArr2[0] = Utility.parseChar(str, iArr, '(') ? 1 : 0;
        } else if (iArr2[0] == 1 && !Utility.parseChar(str, iArr, '(')) {
            iArr[0] = i2;
            return null;
        }
        iArr[0] = PatternProps.skipWhiteSpace(str, iArr[0]);
        if (!UnicodeSet.resemblesPattern(str, iArr[0])) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(iArr[0]);
        try {
            UnicodeSet unicodeSet = new UnicodeSet(str, parsePosition, null);
            String substring = str.substring(iArr[0], parsePosition.getIndex());
            iArr[0] = parsePosition.getIndex();
            if (iArr2[0] != 1 || Utility.parseChar(str, iArr, ')')) {
                if (stringBuffer != null) {
                    if (i == 0) {
                        if (iArr2[0] == 1) {
                            substring = String.valueOf('(') + substring + ')';
                        }
                        stringBuffer.append(substring + ';');
                    } else {
                        if (iArr2[0] == 0) {
                            substring = String.valueOf('(') + substring + ')';
                        }
                        stringBuffer.insert(0, substring + ';');
                    }
                }
                return unicodeSet;
            }
            iArr[0] = i2;
            return null;
        } catch (IllegalArgumentException unused) {
            iArr[0] = i2;
            return null;
        }
    }

    public static boolean parseCompoundID(String str, int i, StringBuffer stringBuffer, List list, UnicodeSet[] unicodeSetArr) {
        boolean z;
        int[] iArr = {0};
        list.clear();
        unicodeSetArr[0] = null;
        stringBuffer.setLength(0);
        int[] iArr2 = {0};
        UnicodeSet parseGlobalFilter = parseGlobalFilter(str, iArr, i, iArr2, stringBuffer);
        if (parseGlobalFilter != null) {
            if (!Utility.parseChar(str, iArr, ';')) {
                stringBuffer.setLength(0);
                iArr[0] = 0;
            }
            if (i == 0) {
                unicodeSetArr[0] = parseGlobalFilter;
            }
        }
        while (true) {
            SingleID parseSingleID = parseSingleID(str, iArr, i);
            if (parseSingleID == null) {
                z = true;
                break;
            }
            if (i == 0) {
                list.add(parseSingleID);
            } else {
                list.add(0, parseSingleID);
            }
            if (!Utility.parseChar(str, iArr, ';')) {
                z = false;
                break;
            }
        }
        if (list.size() == 0) {
            return false;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            stringBuffer.append(((SingleID) list.get(i2)).canonID);
            if (i2 != list.size() - 1) {
                stringBuffer.append(';');
            }
        }
        if (z) {
            iArr2[0] = 1;
            UnicodeSet parseGlobalFilter2 = parseGlobalFilter(str, iArr, i, iArr2, stringBuffer);
            if (parseGlobalFilter2 != null) {
                Utility.parseChar(str, iArr, ';');
                if (i == 1) {
                    unicodeSetArr[0] = parseGlobalFilter2;
                }
            }
        }
        iArr[0] = PatternProps.skipWhiteSpace(str, iArr[0]);
        return iArr[0] == str.length();
    }

    static List instantiateList(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SingleID singleID = (SingleID) it.next();
            if (singleID.basicID.length() != 0) {
                Transliterator instance = singleID.getInstance();
                if (instance != null) {
                    arrayList.add(instance);
                } else {
                    throw new IllegalArgumentException("Illegal ID " + singleID.canonID);
                }
            }
        }
        if (arrayList.size() == 0) {
            Transliterator basicInstance = Transliterator.getBasicInstance("Any-Null", null);
            if (basicInstance != null) {
                arrayList.add(basicInstance);
            } else {
                throw new IllegalArgumentException("Internal error; cannot instantiate Any-Null");
            }
        }
        return arrayList;
    }

    public static String[] IDtoSTV(String str) {
        String str2;
        boolean z;
        String str3;
        int indexOf = str.indexOf(45);
        int indexOf2 = str.indexOf(47);
        if (indexOf2 < 0) {
            indexOf2 = str.length();
        }
        String str4 = "Any";
        if (indexOf < 0) {
            str3 = str.substring(0, indexOf2);
            str2 = str.substring(indexOf2);
            z = false;
        } else if (indexOf < indexOf2) {
            if (indexOf > 0) {
                str4 = str.substring(0, indexOf);
                z = true;
            } else {
                z = false;
            }
            str3 = str.substring(indexOf + 1, indexOf2);
            str2 = str.substring(indexOf2);
        } else {
            if (indexOf2 > 0) {
                str4 = str.substring(0, indexOf2);
                z = true;
            } else {
                z = false;
            }
            int i = indexOf + 1;
            String substring = str.substring(indexOf2, indexOf);
            str3 = str.substring(i);
            str2 = substring;
        }
        if (str2.length() > 0) {
            str2 = str2.substring(1);
        }
        String[] strArr = new String[4];
        strArr[0] = str4;
        strArr[1] = str3;
        strArr[2] = str2;
        strArr[3] = z ? "" : null;
        return strArr;
    }

    public static String STVtoID(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(str);
        if (sb.length() == 0) {
            sb.append("Any");
        }
        sb.append('-');
        sb.append(str2);
        if (!(str3 == null || str3.length() == 0)) {
            sb.append('/');
            sb.append(str3);
        }
        return sb.toString();
    }

    public static void registerSpecialInverse(String str, String str2, boolean z) {
        SPECIAL_INVERSES.put(new CaseInsensitiveString(str), str2);
        if (z && !str.equalsIgnoreCase(str2)) {
            SPECIAL_INVERSES.put(new CaseInsensitiveString(str2), str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.icu.text.TransliteratorIDParser.Specs parseFilterID(java.lang.String r13, int[] r14, boolean r15) {
        /*
        // Method dump skipped, instructions count: 159
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.TransliteratorIDParser.parseFilterID(java.lang.String, int[], boolean):android.icu.text.TransliteratorIDParser$Specs");
    }

    private static SingleID specsToID(Specs specs, int i) {
        String str;
        String str2 = "";
        if (specs != null) {
            StringBuilder sb = new StringBuilder();
            if (i == 0) {
                if (specs.sawSource) {
                    sb.append(specs.source);
                    sb.append('-');
                } else {
                    str2 = specs.source + '-';
                }
                sb.append(specs.target);
            } else {
                sb.append(specs.target);
                sb.append('-');
                sb.append(specs.source);
            }
            if (specs.variant != null) {
                sb.append('/');
                sb.append(specs.variant);
            }
            str2 = str2 + sb.toString();
            String str3 = specs.filter;
            if (str3 != null) {
                sb.insert(0, str3);
            }
            str = sb.toString();
        } else {
            str = str2;
        }
        return new SingleID(str, str2);
    }

    private static SingleID specsToSpecialInverse(Specs specs) {
        String str;
        if (!specs.source.equalsIgnoreCase("Any") || (str = (String) SPECIAL_INVERSES.get(new CaseInsensitiveString(specs.target))) == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String str2 = specs.filter;
        if (str2 != null) {
            sb.append(str2);
        }
        if (specs.sawSource) {
            sb.append("Any");
            sb.append('-');
        }
        sb.append(str);
        String str3 = "Any-" + str;
        if (specs.variant != null) {
            sb.append('/');
            sb.append(specs.variant);
            str3 = str3 + '/' + specs.variant;
        }
        return new SingleID(sb.toString(), str3);
    }
}
