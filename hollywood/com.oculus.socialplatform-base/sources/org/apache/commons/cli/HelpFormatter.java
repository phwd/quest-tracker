package org.apache.commons.cli;

import X.AnonymousClass006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class HelpFormatter {
    public static final String DEFAULT_ARG_NAME = "arg";
    public static final int DEFAULT_DESC_PAD = 3;
    public static final int DEFAULT_LEFT_PAD = 1;
    public static final String DEFAULT_LONG_OPT_PREFIX = "--";
    public static final String DEFAULT_LONG_OPT_SEPARATOR = " ";
    public static final String DEFAULT_OPT_PREFIX = "-";
    public static final String DEFAULT_SYNTAX_PREFIX = "usage: ";
    public static final int DEFAULT_WIDTH = 74;
    @Deprecated
    public String defaultArgName = DEFAULT_ARG_NAME;
    @Deprecated
    public int defaultDescPad = 3;
    @Deprecated
    public int defaultLeftPad = 1;
    @Deprecated
    public String defaultLongOptPrefix = DEFAULT_LONG_OPT_PREFIX;
    @Deprecated
    public String defaultNewLine = System.getProperty("line.separator");
    @Deprecated
    public String defaultOptPrefix = DEFAULT_OPT_PREFIX;
    @Deprecated
    public String defaultSyntaxPrefix = DEFAULT_SYNTAX_PREFIX;
    @Deprecated
    public int defaultWidth = 74;
    public String longOptSeparator = DEFAULT_LONG_OPT_SEPARATOR;
    public Comparator<Option> optionComparator = new OptionComparator();

    public StringBuffer renderWrappedText(StringBuffer stringBuffer, int i, int i2, String str) {
        int findWrapPos = findWrapPos(str, i, 0);
        if (findWrapPos == -1) {
            stringBuffer.append(rtrim(str));
            return stringBuffer;
        }
        stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
        stringBuffer.append(this.defaultNewLine);
        if (i2 >= i) {
            i2 = 1;
        }
        String createPadding = createPadding(i2);
        while (true) {
            str = AnonymousClass006.A07(createPadding, str.substring(findWrapPos).trim());
            findWrapPos = findWrapPos(str, i, 0);
            if (findWrapPos == -1) {
                stringBuffer.append(str);
                return stringBuffer;
            }
            if (str.length() > i && findWrapPos == i2 - 1) {
                findWrapPos = i;
            }
            stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
            stringBuffer.append(this.defaultNewLine);
        }
    }

    private void appendOption(StringBuffer stringBuffer, Option option, boolean z) {
        String str;
        String str2;
        String str3;
        if (!z) {
            stringBuffer.append("[");
        }
        if (option.opt != null) {
            stringBuffer.append(DEFAULT_OPT_PREFIX);
            str = option.opt;
        } else {
            stringBuffer.append(DEFAULT_LONG_OPT_PREFIX);
            str = option.longOpt;
        }
        stringBuffer.append(str);
        if (option.hasArg() && ((str2 = option.argName) == null || str2.length() != 0)) {
            if (option.opt == null) {
                str3 = this.longOptSeparator;
            } else {
                str3 = DEFAULT_LONG_OPT_SEPARATOR;
            }
            stringBuffer.append(str3);
            stringBuffer.append("<");
            String str4 = option.argName;
            if (str4 == null) {
                str4 = this.defaultArgName;
            }
            stringBuffer.append(str4);
            stringBuffer.append(">");
        }
        if (!z) {
            stringBuffer.append("]");
        }
    }

    private void appendOptionGroup(StringBuffer stringBuffer, OptionGroup optionGroup) {
        if (!optionGroup.required) {
            stringBuffer.append("[");
        }
        ArrayList arrayList = new ArrayList(optionGroup.optionMap.values());
        Comparator<Option> comparator = this.optionComparator;
        if (comparator != null) {
            Collections.sort(arrayList, comparator);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            appendOption(stringBuffer, (Option) it.next(), true);
            if (it.hasNext()) {
                stringBuffer.append(" | ");
            }
        }
        if (!optionGroup.required) {
            stringBuffer.append("]");
        }
    }

    private Appendable renderWrappedTextBlock(StringBuffer stringBuffer, int i, int i2, String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
            boolean z = true;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (!z) {
                    stringBuffer.append(this.defaultNewLine);
                } else {
                    z = false;
                }
                renderWrappedText(stringBuffer, i, i2, readLine);
            }
        } catch (IOException unused) {
        }
        return stringBuffer;
    }

    public String createPadding(int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, ' ');
        return new String(cArr);
    }

    public int findWrapPos(String str, int i, int i2) {
        int indexOf = str.indexOf(10, i2);
        if ((indexOf != -1 && indexOf <= i) || ((indexOf = str.indexOf(9, i2)) != -1 && indexOf <= i)) {
            return indexOf + 1;
        }
        int i3 = i + i2;
        int length = str.length();
        if (i3 >= length) {
            return -1;
        }
        int i4 = i3;
        while (i4 >= i2) {
            char charAt = str.charAt(i4);
            if (charAt == ' ' || charAt == '\n' || charAt == '\r') {
                break;
            }
            i4--;
        }
        if (i4 > i2) {
            return i4;
        }
        if (i3 == length) {
            return -1;
        }
        return i3;
    }

    public String getArgName() {
        return this.defaultArgName;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public String getLongOptPrefix() {
        return this.defaultLongOptPrefix;
    }

    public String getLongOptSeparator() {
        return this.longOptSeparator;
    }

    public String getNewLine() {
        return this.defaultNewLine;
    }

    public String getOptPrefix() {
        return this.defaultOptPrefix;
    }

    public Comparator<Option> getOptionComparator() {
        return this.optionComparator;
    }

    public String getSyntaxPrefix() {
        return this.defaultSyntaxPrefix;
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public void printOptions(PrintWriter printWriter, int i, Options options, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        renderOptions(stringBuffer, i, options, i2, i3);
        printWriter.println(stringBuffer.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0022 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.StringBuffer renderOptions(java.lang.StringBuffer r13, int r14, org.apache.commons.cli.Options r15, int r16, int r17) {
        /*
        // Method dump skipped, instructions count: 251
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.cli.HelpFormatter.renderOptions(java.lang.StringBuffer, int, org.apache.commons.cli.Options, int, int):java.lang.StringBuffer");
    }

    public String rtrim(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }

    public void setArgName(String str) {
        this.defaultArgName = str;
    }

    public void setDescPadding(int i) {
        this.defaultDescPad = i;
    }

    public void setLeftPadding(int i) {
        this.defaultLeftPad = i;
    }

    public void setLongOptPrefix(String str) {
        this.defaultLongOptPrefix = str;
    }

    public void setLongOptSeparator(String str) {
        this.longOptSeparator = str;
    }

    public void setNewLine(String str) {
        this.defaultNewLine = str;
    }

    public void setOptPrefix(String str) {
        this.defaultOptPrefix = str;
    }

    public void setOptionComparator(Comparator<Option> comparator) {
        this.optionComparator = comparator;
    }

    public void setSyntaxPrefix(String str) {
        this.defaultSyntaxPrefix = str;
    }

    public void setWidth(int i) {
        this.defaultWidth = i;
    }

    public void printHelp(int i, String str, String str2, Options options, String str3) {
        printHelp(i, str, str2, options, str3, false);
    }

    public void printHelp(int i, String str, String str2, Options options, String str3, boolean z) {
        PrintWriter printWriter = new PrintWriter(System.out);
        printHelp(printWriter, i, str, str2, options, this.defaultLeftPad, this.defaultDescPad, str3, z);
        printWriter.flush();
    }

    public void printHelp(PrintWriter printWriter, int i, String str, String str2, Options options, int i2, int i3, String str3) {
        printHelp(printWriter, i, str, str2, options, i2, i3, str3, false);
    }

    public void printHelp(PrintWriter printWriter, int i, String str, String str2, Options options, int i2, int i3, String str3, boolean z) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("cmdLineSyntax not provided");
        }
        if (z) {
            printUsage(printWriter, i, str, options);
        } else {
            printUsage(printWriter, i, str);
        }
        if (str2 != null && str2.trim().length() > 0) {
            printWrapped(printWriter, i, str2);
        }
        printOptions(printWriter, i, options, i2, i3);
        if (str3 != null && str3.trim().length() > 0) {
            printWrapped(printWriter, i, str3);
        }
    }

    public void printHelp(String str, String str2, Options options, String str3) {
        printHelp(str, str2, options, str3, false);
    }

    public void printHelp(String str, String str2, Options options, String str3, boolean z) {
        printHelp(this.defaultWidth, str, str2, options, str3, z);
    }

    public void printHelp(String str, Options options) {
        printHelp(this.defaultWidth, str, null, options, null, false);
    }

    public void printHelp(String str, Options options, boolean z) {
        printHelp(this.defaultWidth, str, null, options, null, z);
    }

    public void printUsage(PrintWriter printWriter, int i, String str) {
        int indexOf = str.indexOf(32) + 1;
        String str2 = this.defaultSyntaxPrefix;
        printWrapped(printWriter, i, str2.length() + indexOf, AnonymousClass006.A07(str2, str));
    }

    public void printUsage(PrintWriter printWriter, int i, String str, Options options) {
        StringBuffer stringBuffer = new StringBuffer(this.defaultSyntaxPrefix);
        stringBuffer.append(str);
        stringBuffer.append(DEFAULT_LONG_OPT_SEPARATOR);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(options.getOptions());
        Comparator<Option> comparator = this.optionComparator;
        if (comparator != null) {
            Collections.sort(arrayList2, comparator);
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            OptionGroup optionGroup = options.getOptionGroup(option);
            if (optionGroup == null) {
                appendOption(stringBuffer, option, option.required);
            } else if (!arrayList.contains(optionGroup)) {
                arrayList.add(optionGroup);
                appendOptionGroup(stringBuffer, optionGroup);
            }
            if (it.hasNext()) {
                stringBuffer.append(DEFAULT_LONG_OPT_SEPARATOR);
            }
        }
        printWrapped(printWriter, i, stringBuffer.toString().indexOf(32) + 1, stringBuffer.toString());
    }

    public void printWrapped(PrintWriter printWriter, int i, int i2, String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        renderWrappedTextBlock(stringBuffer, i, i2, str);
        printWriter.println(stringBuffer.toString());
    }

    public void printWrapped(PrintWriter printWriter, int i, String str) {
        printWrapped(printWriter, i, 0, str);
    }

    public static class OptionComparator implements Comparator<Option>, Serializable {
        public static final long serialVersionUID = 5305467873966684014L;

        public OptionComparator() {
        }

        public /* synthetic */ OptionComparator(AnonymousClass1 r1) {
        }

        public int compare(Option option, Option option2) {
            return option.getKey().compareToIgnoreCase(option2.getKey());
        }
    }
}
