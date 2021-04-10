package android.icu.impl;

import android.icu.lang.UCharacter;
import android.icu.text.UnicodeSet;
import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;

public final class UCharacterName {
    static final int EXTENDED_CATEGORY_ = 33;
    private static final String FILE_NAME_ = "unames.icu";
    private static final int GROUP_MASK_ = 31;
    private static final int GROUP_SHIFT_ = 5;
    public static final UCharacterName INSTANCE;
    private static final int LEAD_SURROGATE_ = 31;
    public static final int LINES_PER_GROUP_ = 32;
    private static final int NON_CHARACTER_ = 30;
    private static final int OFFSET_HIGH_OFFSET_ = 1;
    private static final int OFFSET_LOW_OFFSET_ = 2;
    private static final int SINGLE_NIBBLE_MAX_ = 11;
    private static final int TRAIL_SURROGATE_ = 32;
    private static final String[] TYPE_NAMES_ = {"unassigned", "uppercase letter", "lowercase letter", "titlecase letter", "modifier letter", "other letter", "non spacing mark", "enclosing mark", "combining spacing mark", "decimal digit number", "letter number", "other number", "space separator", "line separator", "paragraph separator", "control", "format", "private use area", "surrogate", "dash punctuation", "start punctuation", "end punctuation", "connector punctuation", "other punctuation", "math symbol", "currency symbol", "modifier symbol", "other symbol", "initial punctuation", "final punctuation", "noncharacter", "lead surrogate", "trail surrogate"};
    private static final String UNKNOWN_TYPE_NAME_ = "unknown";
    private int[] m_ISOCommentSet_ = new int[8];
    private AlgorithmName[] m_algorithm_;
    public int m_groupcount_ = 0;
    private char[] m_groupinfo_;
    private char[] m_grouplengths_ = new char[33];
    private char[] m_groupoffsets_ = new char[33];
    int m_groupsize_ = 0;
    private byte[] m_groupstring_;
    private int m_maxISOCommentLength_;
    private int m_maxNameLength_;
    private int[] m_nameSet_ = new int[8];
    private byte[] m_tokenstring_;
    private char[] m_tokentable_;
    private int[] m_utilIntBuffer_ = new int[2];
    private StringBuffer m_utilStringBuffer_ = new StringBuffer();

    static {
        try {
            INSTANCE = new UCharacterName();
        } catch (IOException e) {
            throw new MissingResourceException("Could not construct UCharacterName. Missing unames.icu", "", "");
        }
    }

    public String getName(int ch, int choice) {
        if (ch < 0 || ch > 1114111 || choice > 4) {
            return null;
        }
        String result = getAlgName(ch, choice);
        if (result != null && result.length() != 0) {
            return result;
        }
        if (choice == 2) {
            return getExtendedName(ch);
        }
        return getGroupName(ch, choice);
    }

    public int getCharFromName(int choice, String name) {
        if (choice >= 4 || name == null || name.length() == 0) {
            return -1;
        }
        int result = getExtendedChar(name.toLowerCase(Locale.ENGLISH), choice);
        if (result >= -1) {
            return result;
        }
        String upperCaseName = name.toUpperCase(Locale.ENGLISH);
        if (choice == 0 || choice == 2) {
            int count = 0;
            AlgorithmName[] algorithmNameArr = this.m_algorithm_;
            if (algorithmNameArr != null) {
                count = algorithmNameArr.length;
            }
            for (int count2 = count - 1; count2 >= 0; count2--) {
                int result2 = this.m_algorithm_[count2].getChar(upperCaseName);
                if (result2 >= 0) {
                    return result2;
                }
            }
        }
        if (choice != 2) {
            return getGroupChar(upperCaseName, choice);
        }
        int result3 = getGroupChar(upperCaseName, 0);
        if (result3 == -1) {
            return getGroupChar(upperCaseName, 3);
        }
        return result3;
    }

    public int getGroupLengths(int index, char[] offsets, char[] lengths) {
        char length = 65535;
        int index2 = index * this.m_groupsize_;
        char[] cArr = this.m_groupinfo_;
        int stringoffset = UCharacterUtility.toInt(cArr[index2 + 1], cArr[index2 + 2]);
        offsets[0] = 0;
        int i = 0;
        while (i < 32) {
            byte b = this.m_groupstring_[stringoffset];
            for (int shift = 4; shift >= 0; shift -= 4) {
                byte n = (byte) ((b >> shift) & 15);
                if (length != 65535 || n <= 11) {
                    if (length != 65535) {
                        lengths[i] = (char) ((length | n) + 12);
                    } else {
                        lengths[i] = (char) n;
                    }
                    if (i < 32) {
                        offsets[i + 1] = (char) (offsets[i] + lengths[i]);
                    }
                    length = 65535;
                    i++;
                } else {
                    length = (char) ((n - 12) << 4);
                }
            }
            stringoffset++;
        }
        return stringoffset;
    }

    public String getGroupName(int index, int length, int choice) {
        if (!(choice == 0 || choice == 2)) {
            char[] cArr = this.m_tokentable_;
            if (59 >= cArr.length || cArr[59] == 65535) {
                int fieldIndex = choice == 4 ? 2 : choice;
                do {
                    index += UCharacterUtility.skipByteSubString(this.m_groupstring_, index, length, (byte) 59);
                    length -= index - index;
                    fieldIndex--;
                } while (fieldIndex > 0);
            } else {
                length = 0;
            }
        }
        synchronized (this.m_utilStringBuffer_) {
            this.m_utilStringBuffer_.setLength(0);
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                byte b = this.m_groupstring_[index + i];
                i++;
                if (b < this.m_tokentable_.length) {
                    char token = this.m_tokentable_[b & 255];
                    if (token == 65534) {
                        token = this.m_tokentable_[(b << 8) | (this.m_groupstring_[index + i] & 255)];
                        i++;
                    }
                    if (token != 65535) {
                        UCharacterUtility.getNullTermByteSubString(this.m_utilStringBuffer_, this.m_tokenstring_, token);
                    } else if (b == 59) {
                        if (this.m_utilStringBuffer_.length() != 0 || choice != 2) {
                            break;
                        }
                    } else {
                        this.m_utilStringBuffer_.append((char) (b & 255));
                    }
                } else if (b == 59) {
                    break;
                } else {
                    this.m_utilStringBuffer_.append((int) b);
                }
            }
            if (this.m_utilStringBuffer_.length() <= 0) {
                return null;
            }
            return this.m_utilStringBuffer_.toString();
        }
    }

    public String getExtendedName(int ch) {
        String result = getName(ch, 0);
        if (result == null) {
            return getExtendedOr10Name(ch);
        }
        return result;
    }

    public int getGroup(int codepoint) {
        int endGroup = this.m_groupcount_;
        int msb = getCodepointMSB(codepoint);
        int result = 0;
        while (result < endGroup - 1) {
            int gindex = (result + endGroup) >> 1;
            if (msb < getGroupMSB(gindex)) {
                endGroup = gindex;
            } else {
                result = gindex;
            }
        }
        return result;
    }

    public String getExtendedOr10Name(int ch) {
        String result;
        String result2 = null;
        if (0 == 0) {
            int type = getType(ch);
            String[] strArr = TYPE_NAMES_;
            if (type >= strArr.length) {
                result = "unknown";
            } else {
                result = strArr[type];
            }
            synchronized (this.m_utilStringBuffer_) {
                this.m_utilStringBuffer_.setLength(0);
                this.m_utilStringBuffer_.append('<');
                this.m_utilStringBuffer_.append(result);
                this.m_utilStringBuffer_.append('-');
                String chStr = Integer.toHexString(ch).toUpperCase(Locale.ENGLISH);
                for (int zeros = 4 - chStr.length(); zeros > 0; zeros--) {
                    this.m_utilStringBuffer_.append('0');
                }
                this.m_utilStringBuffer_.append(chStr);
                this.m_utilStringBuffer_.append('>');
                result2 = this.m_utilStringBuffer_.toString();
            }
        }
        return result2;
    }

    public int getGroupMSB(int gindex) {
        if (gindex >= this.m_groupcount_) {
            return -1;
        }
        return this.m_groupinfo_[this.m_groupsize_ * gindex];
    }

    public static int getCodepointMSB(int codepoint) {
        return codepoint >> 5;
    }

    public static int getGroupLimit(int msb) {
        return (msb << 5) + 32;
    }

    public static int getGroupMin(int msb) {
        return msb << 5;
    }

    public static int getGroupOffset(int codepoint) {
        return codepoint & 31;
    }

    public static int getGroupMinFromCodepoint(int codepoint) {
        return codepoint & -32;
    }

    public int getAlgorithmLength() {
        return this.m_algorithm_.length;
    }

    public int getAlgorithmStart(int index) {
        return this.m_algorithm_[index].m_rangestart_;
    }

    public int getAlgorithmEnd(int index) {
        return this.m_algorithm_[index].m_rangeend_;
    }

    public String getAlgorithmName(int index, int codepoint) {
        String result;
        synchronized (this.m_utilStringBuffer_) {
            this.m_utilStringBuffer_.setLength(0);
            this.m_algorithm_[index].appendName(codepoint, this.m_utilStringBuffer_);
            result = this.m_utilStringBuffer_.toString();
        }
        return result;
    }

    public synchronized String getGroupName(int ch, int choice) {
        int msb = getCodepointMSB(ch);
        int group = getGroup(ch);
        if (msb != this.m_groupinfo_[this.m_groupsize_ * group]) {
            return null;
        }
        int offset = ch & 31;
        return getGroupName(this.m_groupoffsets_[offset] + getGroupLengths(group, this.m_groupoffsets_, this.m_grouplengths_), this.m_grouplengths_[offset], choice);
    }

    public int getMaxCharNameLength() {
        if (initNameSetsLengths()) {
            return this.m_maxNameLength_;
        }
        return 0;
    }

    public int getMaxISOCommentLength() {
        if (initNameSetsLengths()) {
            return this.m_maxISOCommentLength_;
        }
        return 0;
    }

    public void getCharNameCharacters(UnicodeSet set) {
        convert(this.m_nameSet_, set);
    }

    public void getISOCommentCharacters(UnicodeSet set) {
        convert(this.m_ISOCommentSet_, set);
    }

    /* access modifiers changed from: package-private */
    public static final class AlgorithmName {
        static final int TYPE_0_ = 0;
        static final int TYPE_1_ = 1;
        private char[] m_factor_;
        private byte[] m_factorstring_;
        private String m_prefix_;
        private int m_rangeend_;
        private int m_rangestart_;
        private byte m_type_;
        private int[] m_utilIntBuffer_ = new int[256];
        private StringBuffer m_utilStringBuffer_ = new StringBuffer();
        private byte m_variant_;

        AlgorithmName() {
        }

        /* access modifiers changed from: package-private */
        public boolean setInfo(int rangestart, int rangeend, byte type, byte variant) {
            if (rangestart < 0 || rangestart > rangeend || rangeend > 1114111) {
                return false;
            }
            if (type != 0 && type != 1) {
                return false;
            }
            this.m_rangestart_ = rangestart;
            this.m_rangeend_ = rangeend;
            this.m_type_ = type;
            this.m_variant_ = variant;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean setFactor(char[] factor) {
            if (factor.length != this.m_variant_) {
                return false;
            }
            this.m_factor_ = factor;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean setPrefix(String prefix) {
            if (prefix == null || prefix.length() <= 0) {
                return false;
            }
            this.m_prefix_ = prefix;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean setFactorString(byte[] string) {
            this.m_factorstring_ = string;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean contains(int ch) {
            return this.m_rangestart_ <= ch && ch <= this.m_rangeend_;
        }

        /* access modifiers changed from: package-private */
        public void appendName(int ch, StringBuffer str) {
            str.append(this.m_prefix_);
            byte b = this.m_type_;
            if (b == 0) {
                str.append(Utility.hex((long) ch, this.m_variant_));
            } else if (b == 1) {
                int offset = ch - this.m_rangestart_;
                int[] indexes = this.m_utilIntBuffer_;
                synchronized (this.m_utilIntBuffer_) {
                    for (int i = this.m_variant_ - 1; i > 0; i--) {
                        int factor = this.m_factor_[i] & 255;
                        indexes[i] = offset % factor;
                        offset /= factor;
                    }
                    indexes[0] = offset;
                    str.append(getFactorString(indexes, this.m_variant_));
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
            r1 = r1 + 1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getChar(java.lang.String r12) {
            /*
            // Method dump skipped, instructions count: 114
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.UCharacterName.AlgorithmName.getChar(java.lang.String):int");
        }

        /* access modifiers changed from: package-private */
        public int add(int[] set, int maxlength) {
            int length = UCharacterName.add(set, this.m_prefix_);
            byte b = this.m_type_;
            if (b == 0) {
                length += this.m_variant_;
            } else if (b == 1) {
                for (int i = this.m_variant_ - 1; i > 0; i--) {
                    int maxfactorlength = 0;
                    int count = 0;
                    int factor = this.m_factor_[i];
                    while (factor > 0) {
                        synchronized (this.m_utilStringBuffer_) {
                            this.m_utilStringBuffer_.setLength(0);
                            count = UCharacterUtility.getNullTermByteSubString(this.m_utilStringBuffer_, this.m_factorstring_, count);
                            UCharacterName.add(set, this.m_utilStringBuffer_);
                            if (this.m_utilStringBuffer_.length() > maxfactorlength) {
                                maxfactorlength = this.m_utilStringBuffer_.length();
                            }
                        }
                        factor = (factor == 1 ? 1 : 0) - 1;
                    }
                    length += maxfactorlength;
                }
            }
            if (length > maxlength) {
                return length;
            }
            return maxlength;
        }

        private String getFactorString(int[] index, int length) {
            String stringBuffer;
            int size = this.m_factor_.length;
            if (index == null || length != size) {
                return null;
            }
            synchronized (this.m_utilStringBuffer_) {
                this.m_utilStringBuffer_.setLength(0);
                int count = 0;
                int size2 = size - 1;
                for (int i = 0; i <= size2; i++) {
                    char c = this.m_factor_[i];
                    count = UCharacterUtility.getNullTermByteSubString(this.m_utilStringBuffer_, this.m_factorstring_, UCharacterUtility.skipNullTermByteSubString(this.m_factorstring_, count, index[i]));
                    if (i != size2) {
                        count = UCharacterUtility.skipNullTermByteSubString(this.m_factorstring_, count, (c - index[i]) - 1);
                    }
                }
                stringBuffer = this.m_utilStringBuffer_.toString();
            }
            return stringBuffer;
        }

        private boolean compareFactorString(int[] index, int length, String str, int offset) {
            int size = this.m_factor_.length;
            if (index == null || length != size) {
                return false;
            }
            int count = 0;
            int strcount = offset;
            int size2 = size - 1;
            for (int i = 0; i <= size2; i++) {
                char c = this.m_factor_[i];
                count = UCharacterUtility.skipNullTermByteSubString(this.m_factorstring_, count, index[i]);
                strcount = UCharacterUtility.compareNullTermByteSubString(str, this.m_factorstring_, strcount, count);
                if (strcount < 0) {
                    return false;
                }
                if (i != size2) {
                    count = UCharacterUtility.skipNullTermByteSubString(this.m_factorstring_, count, c - index[i]);
                }
            }
            if (strcount != str.length()) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean setToken(char[] token, byte[] tokenstring) {
        if (token == null || tokenstring == null || token.length <= 0 || tokenstring.length <= 0) {
            return false;
        }
        this.m_tokentable_ = token;
        this.m_tokenstring_ = tokenstring;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setAlgorithm(AlgorithmName[] alg) {
        if (alg == null || alg.length == 0) {
            return false;
        }
        this.m_algorithm_ = alg;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setGroupCountSize(int count, int size) {
        if (count <= 0 || size <= 0) {
            return false;
        }
        this.m_groupcount_ = count;
        this.m_groupsize_ = size;
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean setGroup(char[] group, byte[] groupstring) {
        if (group == null || groupstring == null || group.length <= 0 || groupstring.length <= 0) {
            return false;
        }
        this.m_groupinfo_ = group;
        this.m_groupstring_ = groupstring;
        return true;
    }

    private UCharacterName() throws IOException {
        new UCharacterNameReader(ICUBinary.getRequiredData(FILE_NAME_)).read(this);
    }

    private String getAlgName(int ch, int choice) {
        if (!(choice == 0 || choice == 2)) {
            return null;
        }
        synchronized (this.m_utilStringBuffer_) {
            this.m_utilStringBuffer_.setLength(0);
            for (int index = this.m_algorithm_.length - 1; index >= 0; index--) {
                if (this.m_algorithm_[index].contains(ch)) {
                    this.m_algorithm_[index].appendName(ch, this.m_utilStringBuffer_);
                    return this.m_utilStringBuffer_.toString();
                }
            }
            return null;
        }
    }

    private synchronized int getGroupChar(String name, int choice) {
        for (int i = 0; i < this.m_groupcount_; i++) {
            int result = getGroupChar(getGroupLengths(i, this.m_groupoffsets_, this.m_grouplengths_), this.m_grouplengths_, name, choice);
            if (result != -1) {
                return (this.m_groupinfo_[this.m_groupsize_ * i] << 5) | result;
            }
        }
        return -1;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x0023 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x0031 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r19v0, types: [char[]] */
    /* JADX WARN: Type inference failed for: r9v0, types: [char] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r9v2, types: [int] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getGroupChar(int r18, char[] r19, java.lang.String r20, int r21) {
        /*
        // Method dump skipped, instructions count: 150
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.UCharacterName.getGroupChar(int, char[], java.lang.String, int):int");
    }

    private static int getType(int ch) {
        if (UCharacterUtility.isNonCharacter(ch)) {
            return 30;
        }
        int result = UCharacter.getType(ch);
        if (result != 18) {
            return result;
        }
        if (ch <= 56319) {
            return 31;
        }
        return 32;
    }

    private static int getExtendedChar(String name, int choice) {
        int startIndex;
        if (name.charAt(0) != '<') {
            return -2;
        }
        if (choice == 2) {
            int endIndex = name.length() - 1;
            if (name.charAt(endIndex) == '>' && (startIndex = name.lastIndexOf(45)) >= 0) {
                int startIndex2 = startIndex + 1;
                try {
                    int result = Integer.parseInt(name.substring(startIndex2, endIndex), 16);
                    String type = name.substring(1, startIndex2 - 1);
                    int length = TYPE_NAMES_.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        } else if (type.compareTo(TYPE_NAMES_[i]) != 0) {
                            i++;
                        } else if (getType(result) == i) {
                            return result;
                        }
                    }
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }
        return -1;
    }

    private static void add(int[] set, char ch) {
        int i = ch >>> 5;
        set[i] = set[i] | (1 << (ch & 31));
    }

    private static boolean contains(int[] set, char ch) {
        return (set[ch >>> 5] & (1 << (ch & 31))) != 0;
    }

    /* access modifiers changed from: private */
    public static int add(int[] set, String str) {
        int result = str.length();
        for (int i = result - 1; i >= 0; i--) {
            add(set, str.charAt(i));
        }
        return result;
    }

    /* access modifiers changed from: private */
    public static int add(int[] set, StringBuffer str) {
        int result = str.length();
        for (int i = result - 1; i >= 0; i--) {
            add(set, str.charAt(i));
        }
        return result;
    }

    private int addAlgorithmName(int maxlength) {
        for (int i = this.m_algorithm_.length - 1; i >= 0; i--) {
            int result = this.m_algorithm_[i].add(this.m_nameSet_, maxlength);
            if (result > maxlength) {
                maxlength = result;
            }
        }
        return maxlength;
    }

    private int addExtendedName(int maxlength) {
        for (int i = TYPE_NAMES_.length - 1; i >= 0; i--) {
            int length = add(this.m_nameSet_, TYPE_NAMES_[i]) + 9;
            if (length > maxlength) {
                maxlength = length;
            }
        }
        return maxlength;
    }

    private int[] addGroupName(int offset, int length, byte[] tokenlength, int[] set) {
        int resultnlength = 0;
        int resultplength = 0;
        while (resultplength < length) {
            byte[] bArr = this.m_groupstring_;
            char b = (char) (bArr[offset + resultplength] & 255);
            resultplength++;
            if (b == ';') {
                break;
            }
            char[] cArr = this.m_tokentable_;
            if (b >= cArr.length) {
                add(set, b);
                resultnlength++;
            } else {
                char token = cArr[b & 255];
                if (token == 65534) {
                    b = (char) ((bArr[offset + resultplength] & 255) | (b << '\b'));
                    token = cArr[b];
                    resultplength++;
                }
                if (token == 65535) {
                    add(set, b);
                    resultnlength++;
                } else {
                    byte tlength = tokenlength[b];
                    if (tlength == 0) {
                        synchronized (this.m_utilStringBuffer_) {
                            this.m_utilStringBuffer_.setLength(0);
                            UCharacterUtility.getNullTermByteSubString(this.m_utilStringBuffer_, this.m_tokenstring_, token);
                            tlength = (byte) add(set, this.m_utilStringBuffer_);
                        }
                        tokenlength[b] = tlength;
                    }
                    resultnlength += tlength;
                }
            }
        }
        int[] iArr = this.m_utilIntBuffer_;
        iArr[0] = resultnlength;
        iArr[1] = resultplength;
        return iArr;
    }

    private void addGroupName(int maxlength) {
        int maxisolength = 0;
        char[] offsets = new char[34];
        char[] lengths = new char[34];
        byte[] tokenlengths = new byte[this.m_tokentable_.length];
        for (int i = 0; i < this.m_groupcount_; i++) {
            int offset = getGroupLengths(i, offsets, lengths);
            for (int linenumber = 0; linenumber < 32; linenumber++) {
                int lineoffset = offsets[linenumber] + offset;
                char c = lengths[linenumber];
                if (c != 0) {
                    int[] parsed = addGroupName(lineoffset, c, tokenlengths, this.m_nameSet_);
                    if (parsed[0] > maxlength) {
                        maxlength = parsed[0];
                    }
                    int lineoffset2 = lineoffset + parsed[1];
                    if (parsed[1] < c) {
                        int length = c - parsed[1];
                        int[] parsed2 = addGroupName(lineoffset2, length, tokenlengths, this.m_nameSet_);
                        if (parsed2[0] > maxlength) {
                            maxlength = parsed2[0];
                        }
                        int lineoffset3 = lineoffset2 + parsed2[1];
                        if (parsed2[1] < length) {
                            int length2 = length - parsed2[1];
                            if (addGroupName(lineoffset3, length2, tokenlengths, this.m_ISOCommentSet_)[1] > maxisolength) {
                                maxisolength = length2;
                            }
                        }
                    }
                }
            }
        }
        this.m_maxISOCommentLength_ = maxisolength;
        this.m_maxNameLength_ = maxlength;
    }

    private boolean initNameSetsLengths() {
        if (this.m_maxNameLength_ > 0) {
            return true;
        }
        for (int i = "0123456789ABCDEF<>-".length() - 1; i >= 0; i--) {
            add(this.m_nameSet_, "0123456789ABCDEF<>-".charAt(i));
        }
        this.m_maxNameLength_ = addAlgorithmName(0);
        this.m_maxNameLength_ = addExtendedName(this.m_maxNameLength_);
        addGroupName(this.m_maxNameLength_);
        return true;
    }

    private void convert(int[] set, UnicodeSet uset) {
        uset.clear();
        if (initNameSetsLengths()) {
            for (char c = 255; c > 0; c = (char) (c - 1)) {
                if (contains(set, c)) {
                    uset.add(c);
                }
            }
        }
    }
}
