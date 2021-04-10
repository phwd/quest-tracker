package com.oculus.companion.server;

import android.content.Context;
import android.os.UserManager;

public class CompanionUtil {
    public static boolean isUserUnlocked(Context context) {
        return ((UserManager) context.getSystemService("user")).isUserUnlocked();
    }

    public static boolean isHollywoodDevice(Context context) {
        return context.getResources().getInteger(R.integer.adv_byte_0) == 32;
    }

    static byte[] hexToBytes(String str) {
        char[] charArray = str.toCharArray();
        int length = charArray.length / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int digit = Character.digit(charArray[i2 + 1], 16) | (Character.digit(charArray[i2], 16) << 4);
            if (digit > 127) {
                digit -= 256;
            }
            bArr[i] = (byte) digit;
        }
        return bArr;
    }
}
