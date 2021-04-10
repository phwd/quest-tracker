package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DA {
    public final List A00;
    public final List A01;
    public final List A02;
    public final List A03;
    public final List A04;
    public final List A05;
    public final List A06;
    public final List A07;
    public final boolean A08;
    public final boolean A09;

    public DA(D6[] d6Arr, D6[] d6Arr2, List list, List list2, List list3, List list4, List list5, List list6, boolean z, boolean z2) {
        this.A04 = Collections.unmodifiableList(Arrays.asList(d6Arr));
        this.A03 = Collections.unmodifiableList(Arrays.asList(d6Arr2));
        this.A02 = Collections.unmodifiableList(list);
        this.A00 = Collections.unmodifiableList(list2);
        this.A01 = Collections.unmodifiableList(list3);
        this.A06 = Collections.unmodifiableList(list4);
        this.A07 = Collections.unmodifiableList(list5);
        this.A05 = Collections.unmodifiableList(list6);
        this.A08 = z;
        this.A09 = z2;
    }
}
