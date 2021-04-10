package android.icu.text;

import android.icu.impl.Assert;
import android.icu.impl.ICUBinary;
import android.icu.impl.ICUResourceBundle;
import android.icu.util.UResourceBundle;
import java.nio.ByteBuffer;

final class DictionaryData {
    public static DictionaryMatcher loadDictionaryFor(String str) {
        ByteBuffer requiredData = ICUBinary.getRequiredData("brkitr/" + ((ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/brkitr")).getStringWithFallback("dictionaries/" + str));
        ICUBinary.readHeader(requiredData, 1147757428, null);
        int[] iArr = new int[8];
        boolean z = false;
        for (int i = 0; i < 8; i++) {
            iArr[i] = requiredData.getInt();
        }
        int i2 = iArr[0];
        Assert.assrt(i2 >= 32);
        if (i2 > 32) {
            ICUBinary.skipBytes(requiredData, i2 - 32);
        }
        int i3 = iArr[4] & 7;
        int i4 = iArr[3] - i2;
        if (i3 == 0) {
            int i5 = iArr[5];
            byte[] bArr = new byte[i4];
            requiredData.get(bArr);
            return new BytesDictionaryMatcher(bArr, i5);
        } else if (i3 != 1) {
            return null;
        } else {
            if (i4 % 2 == 0) {
                z = true;
            }
            Assert.assrt(z);
            return new CharsDictionaryMatcher(ICUBinary.getString(requiredData, i4 / 2, i4 & 1));
        }
    }
}
