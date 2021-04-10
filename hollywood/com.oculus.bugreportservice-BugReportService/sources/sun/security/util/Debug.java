package sun.security.util;

import java.io.PrintStream;
import java.math.BigInteger;

public class Debug {
    private static String args;
    private static final char[] hexDigits = "0123456789abcdef".toCharArray();
    private String prefix;

    public static Debug getInstance(String str) {
        return getInstance(str, str);
    }

    public static Debug getInstance(String str, String str2) {
        if (!isOn(str)) {
            return null;
        }
        Debug debug = new Debug();
        debug.prefix = str2;
        return debug;
    }

    public static boolean isOn(String str) {
        String str2 = args;
        if (str2 == null) {
            return false;
        }
        if (str2.indexOf("all") != -1) {
            return true;
        }
        if (args.indexOf(str) != -1) {
            return true;
        }
        return false;
    }

    public void println(String str) {
        PrintStream printStream = System.err;
        printStream.println(this.prefix + ": " + str);
    }

    public void println() {
        PrintStream printStream = System.err;
        printStream.println(this.prefix + ":");
    }

    public static String toHexString(BigInteger bigInteger) {
        String bigInteger2 = bigInteger.toString(16);
        StringBuffer stringBuffer = new StringBuffer(bigInteger2.length() * 2);
        if (bigInteger2.startsWith("-")) {
            stringBuffer.append("   -");
            bigInteger2 = bigInteger2.substring(1);
        } else {
            stringBuffer.append("    ");
        }
        if (bigInteger2.length() % 2 != 0) {
            bigInteger2 = "0" + bigInteger2;
        }
        int i = 0;
        while (i < bigInteger2.length()) {
            int i2 = i + 2;
            stringBuffer.append(bigInteger2.substring(i, i2));
            if (i2 != bigInteger2.length()) {
                if (i2 % 64 == 0) {
                    stringBuffer.append("\n    ");
                } else if (i2 % 8 == 0) {
                    stringBuffer.append(" ");
                }
            }
            i = i2;
        }
        return stringBuffer.toString();
    }
}
