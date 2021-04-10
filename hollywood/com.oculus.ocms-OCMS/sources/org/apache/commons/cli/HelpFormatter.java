package org.apache.commons.cli;

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
import java.util.List;

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
    private String longOptSeparator = " ";
    protected Comparator<Option> optionComparator = new OptionComparator();

    public void setWidth(int i) {
        this.defaultWidth = i;
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public void setLeftPadding(int i) {
        this.defaultLeftPad = i;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public void setDescPadding(int i) {
        this.defaultDescPad = i;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public void setSyntaxPrefix(String str) {
        this.defaultSyntaxPrefix = str;
    }

    public String getSyntaxPrefix() {
        return this.defaultSyntaxPrefix;
    }

    public void setNewLine(String str) {
        this.defaultNewLine = str;
    }

    public String getNewLine() {
        return this.defaultNewLine;
    }

    public void setOptPrefix(String str) {
        this.defaultOptPrefix = str;
    }

    public String getOptPrefix() {
        return this.defaultOptPrefix;
    }

    public void setLongOptPrefix(String str) {
        this.defaultLongOptPrefix = str;
    }

    public String getLongOptPrefix() {
        return this.defaultLongOptPrefix;
    }

    public void setLongOptSeparator(String str) {
        this.longOptSeparator = str;
    }

    public String getLongOptSeparator() {
        return this.longOptSeparator;
    }

    public void setArgName(String str) {
        this.defaultArgName = str;
    }

    public String getArgName() {
        return this.defaultArgName;
    }

    public Comparator<Option> getOptionComparator() {
        return this.optionComparator;
    }

    public void setOptionComparator(Comparator<Option> comparator) {
        this.optionComparator = comparator;
    }

    public void printHelp(String str, Options options) {
        printHelp(getWidth(), str, null, options, null, false);
    }

    public void printHelp(String str, Options options, boolean z) {
        printHelp(getWidth(), str, null, options, null, z);
    }

    public void printHelp(String str, String str2, Options options, String str3) {
        printHelp(str, str2, options, str3, false);
    }

    public void printHelp(String str, String str2, Options options, String str3, boolean z) {
        printHelp(getWidth(), str, str2, options, str3, z);
    }

    public void printHelp(int i, String str, String str2, Options options, String str3) {
        printHelp(i, str, str2, options, str3, false);
    }

    public void printHelp(int i, String str, String str2, Options options, String str3, boolean z) {
        PrintWriter printWriter = new PrintWriter(System.out);
        printHelp(printWriter, i, str, str2, options, getLeftPadding(), getDescPadding(), str3, z);
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

    public void printUsage(PrintWriter printWriter, int i, String str, Options options) {
        StringBuffer stringBuffer = new StringBuffer(getSyntaxPrefix());
        stringBuffer.append(str);
        stringBuffer.append(" ");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(options.getOptions());
        if (getOptionComparator() != null) {
            Collections.sort(arrayList2, getOptionComparator());
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Option option = (Option) it.next();
            OptionGroup optionGroup = options.getOptionGroup(option);
            if (optionGroup == null) {
                appendOption(stringBuffer, option, option.isRequired());
            } else if (!arrayList.contains(optionGroup)) {
                arrayList.add(optionGroup);
                appendOptionGroup(stringBuffer, optionGroup);
            }
            if (it.hasNext()) {
                stringBuffer.append(" ");
            }
        }
        printWrapped(printWriter, i, stringBuffer.toString().indexOf(32) + 1, stringBuffer.toString());
    }

    private void appendOptionGroup(StringBuffer stringBuffer, OptionGroup optionGroup) {
        if (!optionGroup.isRequired()) {
            stringBuffer.append("[");
        }
        ArrayList arrayList = new ArrayList(optionGroup.getOptions());
        if (getOptionComparator() != null) {
            Collections.sort(arrayList, getOptionComparator());
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            appendOption(stringBuffer, (Option) it.next(), true);
            if (it.hasNext()) {
                stringBuffer.append(" | ");
            }
        }
        if (!optionGroup.isRequired()) {
            stringBuffer.append("]");
        }
    }

    private void appendOption(StringBuffer stringBuffer, Option option, boolean z) {
        if (!z) {
            stringBuffer.append("[");
        }
        if (option.getOpt() != null) {
            stringBuffer.append(DEFAULT_OPT_PREFIX);
            stringBuffer.append(option.getOpt());
        } else {
            stringBuffer.append(DEFAULT_LONG_OPT_PREFIX);
            stringBuffer.append(option.getLongOpt());
        }
        if (option.hasArg() && (option.getArgName() == null || option.getArgName().length() != 0)) {
            stringBuffer.append(option.getOpt() == null ? this.longOptSeparator : " ");
            stringBuffer.append("<");
            stringBuffer.append(option.getArgName() != null ? option.getArgName() : getArgName());
            stringBuffer.append(">");
        }
        if (!z) {
            stringBuffer.append("]");
        }
    }

    public void printUsage(PrintWriter printWriter, int i, String str) {
        int length = getSyntaxPrefix().length() + str.indexOf(32) + 1;
        printWrapped(printWriter, i, length, getSyntaxPrefix() + str);
    }

    public void printOptions(PrintWriter printWriter, int i, Options options, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        renderOptions(stringBuffer, i, options, i2, i3);
        printWriter.println(stringBuffer.toString());
    }

    public void printWrapped(PrintWriter printWriter, int i, String str) {
        printWrapped(printWriter, i, 0, str);
    }

    public void printWrapped(PrintWriter printWriter, int i, int i2, String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        renderWrappedTextBlock(stringBuffer, i, i2, str);
        printWriter.println(stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public StringBuffer renderOptions(StringBuffer stringBuffer, int i, Options options, int i2, int i3) {
        String createPadding = createPadding(i2);
        String createPadding2 = createPadding(i3);
        ArrayList arrayList = new ArrayList();
        List<Option> helpOptions = options.helpOptions();
        if (getOptionComparator() != null) {
            Collections.sort(helpOptions, getOptionComparator());
        }
        int i4 = 0;
        int i5 = 0;
        for (Option option : helpOptions) {
            StringBuffer stringBuffer2 = new StringBuffer();
            if (option.getOpt() == null) {
                stringBuffer2.append(createPadding);
                stringBuffer2.append("   ");
                stringBuffer2.append(getLongOptPrefix());
                stringBuffer2.append(option.getLongOpt());
            } else {
                stringBuffer2.append(createPadding);
                stringBuffer2.append(getOptPrefix());
                stringBuffer2.append(option.getOpt());
                if (option.hasLongOpt()) {
                    stringBuffer2.append(',');
                    stringBuffer2.append(getLongOptPrefix());
                    stringBuffer2.append(option.getLongOpt());
                }
            }
            if (option.hasArg()) {
                String argName = option.getArgName();
                if (argName == null || argName.length() != 0) {
                    stringBuffer2.append(option.hasLongOpt() ? this.longOptSeparator : " ");
                    stringBuffer2.append("<");
                    stringBuffer2.append(argName != null ? option.getArgName() : getArgName());
                    stringBuffer2.append(">");
                } else {
                    stringBuffer2.append(' ');
                }
            }
            arrayList.add(stringBuffer2);
            if (stringBuffer2.length() > i5) {
                i5 = stringBuffer2.length();
            }
        }
        Iterator<Option> it = helpOptions.iterator();
        while (it.hasNext()) {
            Option next = it.next();
            int i6 = i4 + 1;
            StringBuilder sb = new StringBuilder(((StringBuffer) arrayList.get(i4)).toString());
            if (sb.length() < i5) {
                sb.append(createPadding(i5 - sb.length()));
            }
            sb.append(createPadding2);
            int i7 = i5 + i3;
            if (next.getDescription() != null) {
                sb.append(next.getDescription());
            }
            renderWrappedText(stringBuffer, i, i7, sb.toString());
            if (it.hasNext()) {
                stringBuffer.append(getNewLine());
            }
            i4 = i6;
        }
        return stringBuffer;
    }

    /* access modifiers changed from: protected */
    public StringBuffer renderWrappedText(StringBuffer stringBuffer, int i, int i2, String str) {
        int findWrapPos = findWrapPos(str, i, 0);
        if (findWrapPos == -1) {
            stringBuffer.append(rtrim(str));
            return stringBuffer;
        }
        stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
        stringBuffer.append(getNewLine());
        if (i2 >= i) {
            i2 = 1;
        }
        String createPadding = createPadding(i2);
        while (true) {
            str = createPadding + str.substring(findWrapPos).trim();
            findWrapPos = findWrapPos(str, i, 0);
            if (findWrapPos == -1) {
                stringBuffer.append(str);
                return stringBuffer;
            }
            if (str.length() > i && findWrapPos == i2 - 1) {
                findWrapPos = i;
            }
            stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
            stringBuffer.append(getNewLine());
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
                    stringBuffer.append(getNewLine());
                } else {
                    z = false;
                }
                renderWrappedText(stringBuffer, i, i2, readLine);
            }
        } catch (IOException unused) {
        }
        return stringBuffer;
    }

    /* access modifiers changed from: protected */
    public int findWrapPos(String str, int i, int i2) {
        int indexOf = str.indexOf(10, i2);
        if (indexOf != -1 && indexOf <= i) {
            return indexOf + 1;
        }
        int indexOf2 = str.indexOf(9, i2);
        if (indexOf2 != -1 && indexOf2 <= i) {
            return indexOf2 + 1;
        }
        int i3 = i + i2;
        if (i3 >= str.length()) {
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
        if (i3 == str.length()) {
            return -1;
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public String createPadding(int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, ' ');
        return new String(cArr);
    }

    /* access modifiers changed from: protected */
    public String rtrim(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }

    private static class OptionComparator implements Comparator<Option>, Serializable {
        private static final long serialVersionUID = 5305467873966684014L;

        private OptionComparator() {
        }

        public int compare(Option option, Option option2) {
            return option.getKey().compareToIgnoreCase(option2.getKey());
        }
    }
}
