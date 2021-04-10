package X;

import io.reactivex.annotations.NonNull;
import java.util.ArrayList;

/* renamed from: X.1xU  reason: invalid class name */
public final class AnonymousClass1xU implements AbstractC12271xB, AbstractC12231x7 {
    public AnonymousClass1xV<AbstractC12271xB> A00;
    public volatile boolean A01;

    public static final void A00(AnonymousClass1xV<AbstractC12271xB> r7) {
        if (r7 != null) {
            ArrayList arrayList = null;
            T[] tArr = r7.A03;
            for (T t : tArr) {
                if (t instanceof AbstractC12271xB) {
                    try {
                        t.dispose();
                    } catch (Throwable th) {
                        C12261xA.A00(th);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(th);
                    }
                }
            }
            if (arrayList == null) {
                return;
            }
            if (arrayList.size() == 1) {
                throw C12301xE.A00((Throwable) arrayList.get(0));
            }
            throw new AnonymousClass1Ox(arrayList);
        }
    }

    public final void A01() {
        if (!this.A01) {
            synchronized (this) {
                if (!this.A01) {
                    AnonymousClass1xV<AbstractC12271xB> r1 = this.A00;
                    this.A00 = null;
                    A00(r1);
                }
            }
        }
    }

    @Override // X.AbstractC12231x7
    public final boolean A1D(@NonNull AbstractC12271xB r11) {
        AnonymousClass219.A01(r11, "d is null");
        if (!this.A01) {
            synchronized (this) {
                if (!this.A01) {
                    AnonymousClass1xV<AbstractC12271xB> r3 = this.A00;
                    if (r3 == null) {
                        r3 = new AnonymousClass1xV<>();
                        this.A00 = r3;
                    }
                    Object[] objArr = r3.A03;
                    int i = r3.A00;
                    int hashCode = r11.hashCode() * -1640531527;
                    int i2 = (hashCode ^ (hashCode >>> 16)) & i;
                    Object obj = objArr[i2];
                    if (obj != null) {
                        if (!obj.equals(r11)) {
                            while (true) {
                                i2 = (i2 + 1) & i;
                                Object obj2 = objArr[i2];
                                if (obj2 != null) {
                                    if (obj2.equals(r11)) {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                        return true;
                    }
                    objArr[i2] = r11;
                    int i3 = r3.A02 + 1;
                    r3.A02 = i3;
                    if (i3 >= r3.A01) {
                        T[] tArr = r3.A03;
                        int length = tArr.length;
                        int i4 = length << 1;
                        int i5 = i4 - 1;
                        T[] tArr2 = (T[]) new Object[i4];
                        while (true) {
                            int i6 = i3 - 1;
                            if (i3 == 0) {
                                break;
                            }
                            do {
                                length--;
                            } while (tArr[length] == null);
                            int hashCode2 = tArr[length].hashCode() * -1640531527;
                            int i7 = (hashCode2 ^ (hashCode2 >>> 16)) & i5;
                            if (tArr2[i7] != null) {
                                do {
                                    i7 = (i7 + 1) & i5;
                                } while (tArr2[i7] != null);
                            }
                            tArr2[i7] = tArr[length];
                            i3 = i6;
                        }
                        r3.A00 = i5;
                        r3.A01 = (int) (((float) i4) * r3.A04);
                        r3.A03 = tArr2;
                    }
                    return true;
                }
            }
        }
        r11.dispose();
        return false;
    }

    @Override // X.AbstractC12231x7
    public final boolean A2Z(@NonNull AbstractC12271xB r9) {
        AnonymousClass1xV<AbstractC12271xB> r1;
        T t;
        int i;
        T t2;
        AnonymousClass219.A01(r9, "Disposable item is null");
        if (this.A01) {
            return false;
        }
        synchronized (this) {
            if (!this.A01 && (r1 = this.A00) != null) {
                T[] tArr = r1.A03;
                int i2 = r1.A00;
                int hashCode = r9.hashCode() * -1640531527;
                int i3 = (hashCode ^ (hashCode >>> 16)) & i2;
                T t3 = tArr[i3];
                if (t3 != null) {
                    if (!t3.equals(r9)) {
                        do {
                            i3 = (i3 + 1) & i2;
                            t = tArr[i3];
                            if (t != null) {
                            }
                        } while (!t.equals(r9));
                    }
                    r1.A02--;
                    while (true) {
                        int i4 = i3 + 1;
                        while (true) {
                            i = i4 & i2;
                            t2 = tArr[i];
                            if (t2 == null) {
                                tArr[i3] = null;
                                return true;
                            }
                            int hashCode2 = t2.hashCode() * -1640531527;
                            int i5 = (hashCode2 ^ (hashCode2 >>> 16)) & i2;
                            if (i3 <= i) {
                                if (i3 >= i5) {
                                    break;
                                }
                            } else if (i3 < i5) {
                                continue;
                                i4 = i + 1;
                            }
                            if (i5 > i) {
                                break;
                            }
                            i4 = i + 1;
                        }
                        tArr[i3] = t2;
                        i3 = i;
                    }
                }
            }
            return false;
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.A01) {
            synchronized (this) {
                if (!this.A01) {
                    this.A01 = true;
                    AnonymousClass1xV<AbstractC12271xB> r1 = this.A00;
                    this.A00 = null;
                    A00(r1);
                }
            }
        }
    }

    @Override // X.AbstractC12231x7
    public final boolean A95(@NonNull AbstractC12271xB r2) {
        if (!A2Z(r2)) {
            return false;
        }
        r2.dispose();
        return true;
    }
}
