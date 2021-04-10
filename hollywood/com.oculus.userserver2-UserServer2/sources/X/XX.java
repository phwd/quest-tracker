package X;

import java.util.ArrayList;
import java.util.List;

public final class XX {
    public final List<String> A00 = new ArrayList(20);

    public final void A01(String str) {
        int i = 0;
        while (true) {
            List<String> list = this.A00;
            if (i < list.size()) {
                if (str.equalsIgnoreCase(list.get(i))) {
                    list.remove(i);
                    list.remove(i);
                    i -= 2;
                }
                i += 2;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005d, code lost:
        r1 = java.lang.String.format(java.util.Locale.US, r1, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(java.lang.String r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 113
        */
        throw new UnsupportedOperationException("Method not decompiled: X.XX.A00(java.lang.String, java.lang.String):void");
    }

    public final void A02(String str, String str2) {
        List<String> list = this.A00;
        list.add(str);
        list.add(str2.trim());
    }
}
