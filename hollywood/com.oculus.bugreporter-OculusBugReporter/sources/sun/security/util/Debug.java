package sun.security.util;

import android.icu.impl.number.Padder;
import android.icu.text.PluralRules;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Debug {
    private static String args;
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();
    private String prefix;

    public static Debug getInstance(String option) {
        return getInstance(option, option);
    }

    public static Debug getInstance(String option, String prefix2) {
        if (!isOn(option)) {
            return null;
        }
        Debug d = new Debug();
        d.prefix = prefix2;
        return d;
    }

    public static boolean isOn(String option) {
        String str = args;
        if (str == null) {
            return false;
        }
        if (str.indexOf("all") != -1) {
            return true;
        }
        if (args.indexOf(option) != -1) {
            return true;
        }
        return false;
    }

    public void println(String message) {
        PrintStream printStream = System.err;
        printStream.println(this.prefix + PluralRules.KEYWORD_RULE_SEPARATOR + message);
    }

    public void println() {
        PrintStream printStream = System.err;
        printStream.println(this.prefix + ":");
    }

    public static String toHexString(BigInteger b) {
        String hexValue = b.toString(16);
        StringBuffer buf = new StringBuffer(hexValue.length() * 2);
        if (hexValue.startsWith("-")) {
            buf.append("   -");
            hexValue = hexValue.substring(1);
        } else {
            buf.append("    ");
        }
        if (hexValue.length() % 2 != 0) {
            hexValue = AndroidHardcodedSystemProperties.JAVA_VERSION + hexValue;
        }
        int i = 0;
        while (i < hexValue.length()) {
            buf.append(hexValue.substring(i, i + 2));
            i += 2;
            if (i != hexValue.length()) {
                if (i % 64 == 0) {
                    buf.append("\n    ");
                } else if (i % 8 == 0) {
                    buf.append(Padder.FALLBACK_PADDING_STRING);
                }
            }
        }
        return buf.toString();
    }

    private static String marshal(String args2) {
        if (args2 == null) {
            return null;
        }
        StringBuffer target = new StringBuffer();
        StringBuffer source = new StringBuffer(args2);
        Matcher matcher = Pattern.compile("[Pp][Ee][Rr][Mm][Ii][Ss][Ss][Ii][Oo][Nn]=" + "[a-zA-Z_$][a-zA-Z0-9_$]*([.][a-zA-Z_$][a-zA-Z0-9_$]*)*").matcher(source);
        StringBuffer left = new StringBuffer();
        while (matcher.find()) {
            target.append(matcher.group().replaceFirst("[Pp][Ee][Rr][Mm][Ii][Ss][Ss][Ii][Oo][Nn]=", "permission="));
            target.append("  ");
            matcher.appendReplacement(left, "");
        }
        matcher.appendTail(left);
        Matcher matcher2 = Pattern.compile("[Cc][Oo][Dd][Ee][Bb][Aa][Ss][Ee]=" + "[^, ;]*").matcher(left);
        StringBuffer left2 = new StringBuffer();
        while (matcher2.find()) {
            target.append(matcher2.group().replaceFirst("[Cc][Oo][Dd][Ee][Bb][Aa][Ss][Ee]=", "codebase="));
            target.append("  ");
            matcher2.appendReplacement(left2, "");
        }
        matcher2.appendTail(left2);
        target.append(left2.toString().toLowerCase(Locale.ENGLISH));
        return target.toString();
    }

    public static String toString(byte[] b) {
        if (b == null) {
            return "(null)";
        }
        StringBuilder sb = new StringBuilder(b.length * 3);
        for (int i = 0; i < b.length; i++) {
            int k = b[i] & 255;
            if (i != 0) {
                sb.append(':');
            }
            sb.append(hexDigits[k >>> 4]);
            sb.append(hexDigits[k & 15]);
        }
        return sb.toString();
    }
}
