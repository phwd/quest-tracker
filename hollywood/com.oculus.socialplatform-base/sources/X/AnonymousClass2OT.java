package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.2OT  reason: invalid class name */
public class AnonymousClass2OT {
    public final int A00;
    public final int A01;
    public final AnonymousClass2OU A02;
    public final List<AnonymousClass2Oa> A03;
    public final boolean A04 = true;
    public final int[] A05;
    public final int[] A06;

    /* JADX WARN: Incorrect args count in method signature: (LX/2OU;Ljava/util/List<LX/2Oa;>;[I[IZ)V */
    public AnonymousClass2OT(AnonymousClass2OU r12, List list, int[] iArr, int[] iArr2) {
        int i;
        AnonymousClass2Oa r1;
        this.A03 = list;
        this.A06 = iArr;
        this.A05 = iArr2;
        Arrays.fill(iArr, 0);
        Arrays.fill(this.A05, 0);
        this.A02 = r12;
        this.A01 = r12.getOldListSize();
        this.A00 = r12.getNewListSize();
        List<AnonymousClass2Oa> list2 = this.A03;
        if (list2.isEmpty() || (r1 = list2.get(0)) == null || r1.A01 != 0 || r1.A02 != 0) {
            list2.add(0, new AnonymousClass2Oa(0, 0, 0));
        }
        list2.add(new AnonymousClass2Oa(this.A01, this.A00, 0));
        for (AnonymousClass2Oa r6 : this.A03) {
            for (int i2 = 0; i2 < r6.A00; i2++) {
                int i3 = r6.A01 + i2;
                int i4 = r6.A02 + i2;
                int i5 = 2;
                if (this.A02.areContentsTheSame(i3, i4)) {
                    i5 = 1;
                }
                this.A06[i3] = (i4 << 4) | i5;
                this.A05[i4] = (i3 << 4) | i5;
            }
        }
        if (this.A04) {
            int i6 = 0;
            for (AnonymousClass2Oa r62 : this.A03) {
                while (true) {
                    i = r62.A01;
                    if (i6 >= i) {
                        break;
                    }
                    if (this.A06[i6] == 0) {
                        List<AnonymousClass2Oa> list3 = this.A03;
                        int size = list3.size();
                        int i7 = 0;
                        int i8 = 0;
                        while (true) {
                            if (i7 >= size) {
                                break;
                            }
                            AnonymousClass2Oa r3 = list3.get(i7);
                            while (i8 < r3.A02) {
                                int[] iArr3 = this.A05;
                                if (iArr3[i8] == 0) {
                                    AnonymousClass2OU r2 = this.A02;
                                    if (r2.areItemsTheSame(i6, i8)) {
                                        int i9 = r2.areContentsTheSame(i6, i8) ? 8 : 4;
                                        this.A06[i6] = (i8 << 4) | i9;
                                        iArr3[i8] = (i6 << 4) | i9;
                                    }
                                }
                                i8++;
                            }
                            i8 = r3.A02 + r3.A00;
                            i7++;
                        }
                    }
                    i6++;
                }
                i6 = i + r62.A00;
            }
        }
    }

    public final void A01(@NonNull AnonymousClass2OR r15) {
        AnonymousClass2OS r152;
        int i;
        if (r15 instanceof AnonymousClass2OS) {
            r152 = (AnonymousClass2OS) r15;
        } else {
            r152 = new AnonymousClass2OS(r15);
        }
        int i2 = this.A01;
        ArrayDeque arrayDeque = new ArrayDeque();
        int i3 = i2;
        int i4 = this.A00;
        List<AnonymousClass2Oa> list = this.A03;
        for (int size = list.size() - 1; size >= 0; size--) {
            AnonymousClass2Oa r5 = list.get(size);
            int i5 = r5.A01;
            int i6 = r5.A00;
            int i7 = i5 + i6;
            int i8 = r5.A02 + i6;
            while (true) {
                if (i3 <= i7) {
                    break;
                }
                i3--;
                int i9 = this.A06[i3];
                if ((i9 & 12) != 0) {
                    int i10 = i9 >> 4;
                    AnonymousClass2Ob A002 = A00(arrayDeque, i10, false);
                    if (A002 != null) {
                        int i11 = (i2 - A002.A00) - 1;
                        r152.A7L(i3, i11);
                        if ((i9 & 4) != 0) {
                            r152.A6p(i11, 1, this.A02.getChangePayload(i3, i10));
                        }
                    } else {
                        arrayDeque.add(new AnonymousClass2Ob(i3, (i2 - i3) - 1, true));
                    }
                } else {
                    r152.A7k(i3, 1);
                    i2--;
                }
            }
            while (i4 > i8) {
                i4--;
                int i12 = this.A05[i4];
                if ((i12 & 12) != 0) {
                    int i13 = i12 >> 4;
                    AnonymousClass2Ob A003 = A00(arrayDeque, i13, true);
                    if (A003 == null) {
                        arrayDeque.add(new AnonymousClass2Ob(i4, i2 - i3, false));
                    } else {
                        r152.A7L((i2 - A003.A00) - 1, i3);
                        if ((i12 & 4) != 0) {
                            r152.A6p(i3, 1, this.A02.getChangePayload(i13, i4));
                        }
                    }
                } else {
                    r152.A7A(i3, 1);
                    i2++;
                }
            }
            int i14 = r5.A01;
            int i15 = r5.A02;
            for (i = 0; i < r5.A00; i++) {
                if ((this.A06[i14] & 15) == 2) {
                    r152.A6p(i14, 1, this.A02.getChangePayload(i14, i15));
                }
                i14++;
                i15++;
            }
            i3 = r5.A01;
            i4 = r5.A02;
        }
        r152.A00();
    }

    @Nullable
    public static AnonymousClass2Ob A00(Collection<AnonymousClass2Ob> collection, int i, boolean z) {
        AnonymousClass2Ob r2;
        int i2;
        Iterator<AnonymousClass2Ob> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                r2 = null;
                break;
            }
            r2 = it.next();
            if (r2.A01 == i && r2.A02 == z) {
                it.remove();
                break;
            }
        }
        while (it.hasNext()) {
            AnonymousClass2Ob next = it.next();
            int i3 = next.A00;
            if (z) {
                i2 = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
            next.A00 = i2;
        }
        return r2;
    }
}
