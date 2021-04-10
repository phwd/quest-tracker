package java.text;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MessageFormat extends Format {
    private static final int[] DATE_TIME_MODIFIERS = {2, 3, 2, 1, 0};
    private static final String[] DATE_TIME_MODIFIER_KEYWORDS = {"", "short", "medium", "long", "full"};
    private static final String[] NUMBER_MODIFIER_KEYWORDS = {"", "currency", "percent", "integer"};
    private static final String[] TYPE_KEYWORDS = {"", "number", "date", "time", "choice"};
    private static final long serialVersionUID = 6479157306784022952L;
    private int[] argumentNumbers;
    private Format[] formats;
    private Locale locale;
    private int maxOffset;
    private int[] offsets;
    private String pattern;

    public MessageFormat(String str) {
        this.pattern = "";
        this.formats = new Format[10];
        this.offsets = new int[10];
        this.argumentNumbers = new int[10];
        this.maxOffset = -1;
        this.locale = Locale.getDefault(Locale.Category.FORMAT);
        applyPattern(str);
    }

    public MessageFormat(String str, Locale locale2) {
        this.pattern = "";
        this.formats = new Format[10];
        this.offsets = new int[10];
        this.argumentNumbers = new int[10];
        this.maxOffset = -1;
        this.locale = locale2;
        applyPattern(str);
    }

    public void applyPattern(String str) {
        StringBuilder[] sbArr = new StringBuilder[4];
        sbArr[0] = new StringBuilder();
        this.maxOffset = -1;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (i3 == 0) {
                if (charAt == '\'') {
                    int i5 = i + 1;
                    if (i5 >= str.length() || str.charAt(i5) != '\'') {
                        z = !z;
                    } else {
                        sbArr[i3].append(charAt);
                        i = i5;
                    }
                } else if (charAt != '{' || z) {
                    sbArr[i3].append(charAt);
                } else {
                    if (sbArr[1] == null) {
                        sbArr[1] = new StringBuilder();
                    }
                    i3 = 1;
                }
            } else if (z) {
                sbArr[i3].append(charAt);
                if (charAt == '\'') {
                    z = false;
                }
            } else if (charAt != ' ') {
                if (charAt == '\'') {
                    z = true;
                } else if (charAt != ',') {
                    if (charAt == '{') {
                        i2++;
                        sbArr[i3].append(charAt);
                    } else if (charAt == '}') {
                        if (i2 == 0) {
                            makeFormat(i, i4, sbArr);
                            i4++;
                            sbArr[1] = null;
                            sbArr[2] = null;
                            sbArr[3] = null;
                            i3 = 0;
                        } else {
                            i2--;
                            sbArr[i3].append(charAt);
                        }
                    }
                } else if (i3 < 3) {
                    i3++;
                    if (sbArr[i3] == null) {
                        sbArr[i3] = new StringBuilder();
                    }
                } else {
                    sbArr[i3].append(charAt);
                }
                sbArr[i3].append(charAt);
            } else if (i3 != 2 || sbArr[2].length() > 0) {
                sbArr[i3].append(charAt);
            }
            i++;
        }
        if (i2 != 0 || i3 == 0) {
            this.pattern = sbArr[0].toString();
        } else {
            this.maxOffset = -1;
            throw new IllegalArgumentException("Unmatched braces in the pattern.");
        }
    }

    public static String format(String str, Object... objArr) {
        return new MessageFormat(str).format(objArr);
    }

    @Override // java.text.Format
    public final StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        subformat((Object[]) obj, stringBuffer, fieldPosition, null);
        return stringBuffer;
    }

    @Override // java.text.Format
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        if (obj != null) {
            subformat((Object[]) obj, stringBuffer, null, arrayList);
            if (arrayList.size() == 0) {
                return createAttributedCharacterIterator("");
            }
            return createAttributedCharacterIterator((AttributedCharacterIterator[]) arrayList.toArray(new AttributedCharacterIterator[arrayList.size()]));
        }
        throw new NullPointerException("formatToCharacterIterator must be passed non-null object");
    }

    @Override // java.text.Format
    public Object clone() {
        MessageFormat messageFormat = (MessageFormat) super.clone();
        messageFormat.formats = (Format[]) this.formats.clone();
        int i = 0;
        while (true) {
            Format[] formatArr = this.formats;
            if (i < formatArr.length) {
                if (formatArr[i] != null) {
                    messageFormat.formats[i] = (Format) formatArr[i].clone();
                }
                i++;
            } else {
                messageFormat.offsets = (int[]) this.offsets.clone();
                messageFormat.argumentNumbers = (int[]) this.argumentNumbers.clone();
                return messageFormat;
            }
        }
    }

    public boolean equals(Object obj) {
        Locale locale2;
        if (this == obj) {
            return true;
        }
        if (obj == null || MessageFormat.class != obj.getClass()) {
            return false;
        }
        MessageFormat messageFormat = (MessageFormat) obj;
        return this.maxOffset == messageFormat.maxOffset && this.pattern.equals(messageFormat.pattern) && (((locale2 = this.locale) != null && locale2.equals(messageFormat.locale)) || (this.locale == null && messageFormat.locale == null)) && Arrays.equals(this.offsets, messageFormat.offsets) && Arrays.equals(this.argumentNumbers, messageFormat.argumentNumbers) && Arrays.equals(this.formats, messageFormat.formats);
    }

    public int hashCode() {
        return this.pattern.hashCode();
    }

    public static class Field extends Format.Field {
        public static final Field ARGUMENT = new Field("message argument field");
        private static final long serialVersionUID = 7899943957617360810L;

        protected Field(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        @Override // java.text.AttributedCharacterIterator.Attribute
        public Object readResolve() {
            if (Field.class == Field.class) {
                return ARGUMENT;
            }
            throw new InvalidObjectException("subclass didn't correctly implement readResolve");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.StringBuffer subformat(java.lang.Object[] r11, java.lang.StringBuffer r12, java.text.FieldPosition r13, java.util.List r14) {
        /*
        // Method dump skipped, instructions count: 306
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.MessageFormat.subformat(java.lang.Object[], java.lang.StringBuffer, java.text.FieldPosition, java.util.List):java.lang.StringBuffer");
    }

    private void append(StringBuffer stringBuffer, CharacterIterator characterIterator) {
        if (characterIterator.first() != 65535) {
            stringBuffer.append(characterIterator.first());
            while (true) {
                char next = characterIterator.next();
                if (next != 65535) {
                    stringBuffer.append(next);
                } else {
                    return;
                }
            }
        }
    }

    private void makeFormat(int i, int i2, StringBuilder[] sbArr) {
        int findKeyword;
        Format decimalFormat;
        String[] strArr = new String[sbArr.length];
        for (int i3 = 0; i3 < sbArr.length; i3++) {
            StringBuilder sb = sbArr[i3];
            strArr[i3] = sb != null ? sb.toString() : "";
        }
        try {
            int parseInt = Integer.parseInt(strArr[1]);
            if (parseInt >= 0) {
                Format[] formatArr = this.formats;
                if (i2 >= formatArr.length) {
                    int length = formatArr.length * 2;
                    Format[] formatArr2 = new Format[length];
                    int[] iArr = new int[length];
                    int[] iArr2 = new int[length];
                    System.arraycopy(formatArr, 0, formatArr2, 0, this.maxOffset + 1);
                    System.arraycopy(this.offsets, 0, iArr, 0, this.maxOffset + 1);
                    System.arraycopy(this.argumentNumbers, 0, iArr2, 0, this.maxOffset + 1);
                    this.formats = formatArr2;
                    this.offsets = iArr;
                    this.argumentNumbers = iArr2;
                }
                int i4 = this.maxOffset;
                this.maxOffset = i2;
                this.offsets[i2] = strArr[0].length();
                this.argumentNumbers[i2] = parseInt;
                Format format = null;
                if (!(strArr[2].length() == 0 || (findKeyword = findKeyword(strArr[2], TYPE_KEYWORDS)) == 0)) {
                    if (findKeyword == 1) {
                        int findKeyword2 = findKeyword(strArr[3], NUMBER_MODIFIER_KEYWORDS);
                        if (findKeyword2 == 0) {
                            format = NumberFormat.getInstance(this.locale);
                        } else if (findKeyword2 == 1) {
                            format = NumberFormat.getCurrencyInstance(this.locale);
                        } else if (findKeyword2 == 2) {
                            format = NumberFormat.getPercentInstance(this.locale);
                        } else if (findKeyword2 != 3) {
                            try {
                                decimalFormat = new DecimalFormat(strArr[3], DecimalFormatSymbols.getInstance(this.locale));
                            } catch (IllegalArgumentException e) {
                                this.maxOffset = i4;
                                throw e;
                            }
                        } else {
                            format = NumberFormat.getIntegerInstance(this.locale);
                        }
                    } else if (findKeyword == 2 || findKeyword == 3) {
                        int findKeyword3 = findKeyword(strArr[3], DATE_TIME_MODIFIER_KEYWORDS);
                        if (findKeyword3 < 0 || findKeyword3 >= DATE_TIME_MODIFIER_KEYWORDS.length) {
                            try {
                                decimalFormat = new SimpleDateFormat(strArr[3], this.locale);
                            } catch (IllegalArgumentException e2) {
                                this.maxOffset = i4;
                                throw e2;
                            }
                        } else {
                            format = findKeyword == 2 ? DateFormat.getDateInstance(DATE_TIME_MODIFIERS[findKeyword3], this.locale) : DateFormat.getTimeInstance(DATE_TIME_MODIFIERS[findKeyword3], this.locale);
                        }
                    } else if (findKeyword == 4) {
                        try {
                            decimalFormat = new ChoiceFormat(strArr[3]);
                        } catch (Exception e3) {
                            this.maxOffset = i4;
                            throw new IllegalArgumentException("Choice Pattern incorrect: " + strArr[3], e3);
                        }
                    } else {
                        this.maxOffset = i4;
                        throw new IllegalArgumentException("unknown format type: " + strArr[2]);
                    }
                    format = decimalFormat;
                }
                this.formats[i2] = format;
                return;
            }
            throw new IllegalArgumentException("negative argument number: " + parseInt);
        } catch (NumberFormatException e4) {
            throw new IllegalArgumentException("can't parse argument number: " + strArr[1], e4);
        }
    }

    private static final int findKeyword(String str, String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (str.equals(strArr[i])) {
                return i;
            }
        }
        String lowerCase = str.trim().toLowerCase(Locale.ROOT);
        if (lowerCase == str) {
            return -1;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (lowerCase.equals(strArr[i2])) {
                return i2;
            }
        }
        return -1;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        throw null;
    }
}
