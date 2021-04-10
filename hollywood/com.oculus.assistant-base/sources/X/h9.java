package X;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public final class h9 {
    public AnonymousClass98 A00 = new AnonymousClass98(32, null);
    public int A01 = 0;

    public final List A00(String str) {
        AnonymousClass98 r1 = this.A00;
        if (r1 != null && str != null && !str.isEmpty()) {
            String lowerCase = str.trim().toLowerCase(Locale.US);
            int length = lowerCase.length();
            int i = 0;
            loop0:
            while (true) {
                if (i < length) {
                    int codePointAt = lowerCase.codePointAt(i);
                    LinkedList linkedList = r1.A02;
                    if (linkedList == null) {
                        break;
                    }
                    Iterator it = linkedList.iterator();
                    while (it.hasNext()) {
                        r1 = (AnonymousClass98) it.next();
                        if (r1.A04 == codePointAt) {
                            i += Character.charCount(codePointAt);
                        }
                    }
                    break loop0;
                } else if (r1 != null) {
                    ArrayList arrayList = new ArrayList();
                    LinkedList linkedList2 = new LinkedList();
                    linkedList2.addLast(r1);
                    while (!linkedList2.isEmpty()) {
                        Iterator it2 = ((AnonymousClass98) linkedList2.removeFirst()).A02.iterator();
                        while (it2.hasNext()) {
                            AnonymousClass98 r3 = (AnonymousClass98) it2.next();
                            if (r3 != null) {
                                if (r3.A01) {
                                    arrayList.add(new AbstractMap.SimpleEntry(r3.toString(), Integer.valueOf(r3.A00)));
                                }
                                linkedList2.addLast(r3);
                            }
                        }
                    }
                    return arrayList;
                }
            }
        }
        return Collections.emptyList();
    }

    public final void A01(String str, Object obj) {
        AnonymousClass98 r1;
        Number number = (Number) obj;
        String lowerCase = str.trim().toLowerCase(Locale.US);
        int length = lowerCase.length();
        AnonymousClass98 r5 = this.A00;
        int i = 0;
        while (i < length) {
            int codePointAt = lowerCase.codePointAt(i);
            LinkedList linkedList = r5.A02;
            if (linkedList != null) {
                Iterator it = linkedList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    r1 = (AnonymousClass98) it.next();
                    if (r1.A04 == codePointAt) {
                        break;
                    }
                }
                r5 = r1;
                i += Character.charCount(codePointAt);
            }
            r1 = new AnonymousClass98(codePointAt, r5);
            r5.A02.add(r1);
            r5 = r1;
            i += Character.charCount(codePointAt);
        }
        if (!r5.A01) {
            this.A01++;
            r5.A01 = true;
        }
        if (number != null) {
            r5.A00 = number.intValue();
        }
    }
}
