package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: X.9A  reason: invalid class name */
public final class AnonymousClass9A {
    public double A00;
    public double A01;
    public String A02 = OacrConstants.AUTO_SPEECH_DOMAIN;
    public Map A03;

    public final void A00(List list, String str) {
        this.A02 = str;
        this.A03 = Collections.synchronizedMap(new HashMap());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            BreakIterator characterInstance = BreakIterator.getCharacterInstance();
            characterInstance.setText(str2);
            int last = characterInstance.last();
            double[][] dArr = (double[][]) Array.newInstance(double.class, 9, last + 1);
            int i = 0;
            do {
                dArr[i][0] = ((double) i) * this.A01;
                i++;
            } while (i <= 8);
            for (int i2 = 0; i2 <= last; i2++) {
                dArr[0][i2] = ((double) i2) * this.A00;
            }
            this.A03.put(str2, dArr);
        }
    }

    public AnonymousClass9A(double d, double d2) {
        this.A00 = d;
        this.A01 = d2;
        this.A03 = Collections.synchronizedMap(new HashMap());
    }
}
