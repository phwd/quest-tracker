package java.util.prefs;

import android.icu.impl.PatternTokenizer;
import android.icu.impl.UCharacterProperty;
import android.icu.text.SymbolTable;
import java.util.Arrays;
import java.util.Random;

class Base64 {
    private static final byte[] altBase64ToInt = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, -1, 62, 9, 10, 11, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 12, 13, 14, -1, 15, 63, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17, -1, 18, 19, 21, 20, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 22, 23, 24, 25};
    private static final byte[] base64ToInt = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};
    private static final char[] intToAltBase64 = {'!', '\"', '#', SymbolTable.SYMBOL_REF, '%', '&', PatternTokenizer.SINGLE_QUOTE, '(', ')', ',', '-', '.', ':', ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', UCharacterProperty.LATIN_SMALL_LETTER_I_, 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?'};
    private static final char[] intToBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', UCharacterProperty.LATIN_SMALL_LETTER_I_, 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    Base64() {
    }

    static String byteArrayToBase64(byte[] a) {
        return byteArrayToBase64(a, false);
    }

    static String byteArrayToAltBase64(byte[] a) {
        return byteArrayToBase64(a, true);
    }

    private static String byteArrayToBase64(byte[] a, boolean alternate) {
        int aLen = a.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - (numFullGroups * 3);
        StringBuffer result = new StringBuffer(((aLen + 2) / 3) * 4);
        char[] intToAlpha = alternate ? intToAltBase64 : intToBase64;
        int inCursor = 0;
        int i = 0;
        while (i < numFullGroups) {
            int inCursor2 = inCursor + 1;
            int byte0 = a[inCursor] & 255;
            int inCursor3 = inCursor2 + 1;
            int byte1 = a[inCursor2] & 255;
            int inCursor4 = inCursor3 + 1;
            int byte2 = a[inCursor3] & 255;
            result.append(intToAlpha[byte0 >> 2]);
            result.append(intToAlpha[((byte0 << 4) & 63) | (byte1 >> 4)]);
            result.append(intToAlpha[((byte1 << 2) & 63) | (byte2 >> 6)]);
            result.append(intToAlpha[byte2 & 63]);
            i++;
            inCursor = inCursor4;
        }
        if (numBytesInPartialGroup != 0) {
            int inCursor5 = inCursor + 1;
            int byte02 = a[inCursor] & 255;
            result.append(intToAlpha[byte02 >> 2]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[(byte02 << 4) & 63]);
                result.append("==");
            } else {
                int i2 = inCursor5 + 1;
                int byte12 = a[inCursor5] & 255;
                result.append(intToAlpha[((byte02 << 4) & 63) | (byte12 >> 4)]);
                result.append(intToAlpha[(byte12 << 2) & 63]);
                result.append('=');
            }
        }
        return result.toString();
    }

    static byte[] base64ToByteArray(String s) {
        return base64ToByteArray(s, false);
    }

    static byte[] altBase64ToByteArray(String s) {
        return base64ToByteArray(s, true);
    }

    private static byte[] base64ToByteArray(String s, boolean alternate) {
        byte[] alphaToInt = alternate ? altBase64ToInt : base64ToInt;
        int sLen = s.length();
        int numGroups = sLen / 4;
        if (numGroups * 4 == sLen) {
            int missingBytesInLastGroup = 0;
            int numFullGroups = numGroups;
            if (sLen != 0) {
                if (s.charAt(sLen - 1) == '=') {
                    missingBytesInLastGroup = 0 + 1;
                    numFullGroups--;
                }
                if (s.charAt(sLen - 2) == '=') {
                    missingBytesInLastGroup++;
                }
            }
            byte[] result = new byte[((numGroups * 3) - missingBytesInLastGroup)];
            int inCursor = 0;
            int outCursor = 0;
            int i = 0;
            while (i < numFullGroups) {
                int inCursor2 = inCursor + 1;
                int ch0 = base64toInt(s.charAt(inCursor), alphaToInt);
                int inCursor3 = inCursor2 + 1;
                int ch1 = base64toInt(s.charAt(inCursor2), alphaToInt);
                int inCursor4 = inCursor3 + 1;
                int ch2 = base64toInt(s.charAt(inCursor3), alphaToInt);
                int inCursor5 = inCursor4 + 1;
                int ch3 = base64toInt(s.charAt(inCursor4), alphaToInt);
                int outCursor2 = outCursor + 1;
                result[outCursor] = (byte) ((ch0 << 2) | (ch1 >> 4));
                int outCursor3 = outCursor2 + 1;
                result[outCursor2] = (byte) ((ch1 << 4) | (ch2 >> 2));
                result[outCursor3] = (byte) ((ch2 << 6) | ch3);
                i++;
                inCursor = inCursor5;
                outCursor = outCursor3 + 1;
            }
            if (missingBytesInLastGroup != 0) {
                int inCursor6 = inCursor + 1;
                int ch02 = base64toInt(s.charAt(inCursor), alphaToInt);
                int inCursor7 = inCursor6 + 1;
                int ch12 = base64toInt(s.charAt(inCursor6), alphaToInt);
                int outCursor4 = outCursor + 1;
                result[outCursor] = (byte) ((ch02 << 2) | (ch12 >> 4));
                if (missingBytesInLastGroup == 1) {
                    int i2 = inCursor7 + 1;
                    int i3 = outCursor4 + 1;
                    result[outCursor4] = (byte) ((ch12 << 4) | (base64toInt(s.charAt(inCursor7), alphaToInt) >> 2));
                }
            }
            return result;
        }
        throw new IllegalArgumentException("String length must be a multiple of four.");
    }

    private static int base64toInt(char c, byte[] alphaToInt) {
        byte b = alphaToInt[c];
        if (b >= 0) {
            return b;
        }
        throw new IllegalArgumentException("Illegal character " + c);
    }

    public static void main(String[] args) {
        int numRuns = Integer.parseInt(args[0]);
        int numBytes = Integer.parseInt(args[1]);
        Random rnd = new Random();
        for (int i = 0; i < numRuns; i++) {
            for (int j = 0; j < numBytes; j++) {
                byte[] arr = new byte[j];
                for (int k = 0; k < j; k++) {
                    arr[k] = (byte) rnd.nextInt();
                }
                if (!Arrays.equals(arr, base64ToByteArray(byteArrayToBase64(arr)))) {
                    System.out.println("Dismal failure!");
                }
                if (!Arrays.equals(arr, altBase64ToByteArray(byteArrayToAltBase64(arr)))) {
                    System.out.println("Alternate dismal failure!");
                }
            }
        }
    }
}
