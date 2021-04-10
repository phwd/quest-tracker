package com.google.gson.internal;

import X.AnonymousClass07F;
import X.AnonymousClass0C3;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Excluder implements AnonymousClass0C3, Cloneable {
    public static final Excluder A02 = new Excluder();
    public List<AnonymousClass07F> A00 = Collections.emptyList();
    public List<AnonymousClass07F> A01 = Collections.emptyList();

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (A00(r9, r3, true) != false) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        if (r2 == false) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0027, code lost:
        r5 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (A00(r9, r3, false) == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002e, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r6 != false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        if (r5 != false) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003d, code lost:
        return new X.AnonymousClass0XA(r9, r5, r6, r10, r11);
     */
    @Override // X.AnonymousClass0C3
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> X.AnonymousClass0Bd<T> A1v(X.AnonymousClass08D r10, X.AnonymousClass0Fe<T> r11) {
        /*
            r9 = this;
            r8 = r11
            java.lang.Class<? super T> r3 = r11.A01
            java.lang.Class<java.lang.Enum> r0 = java.lang.Enum.class
            boolean r0 = r0.isAssignableFrom(r3)
            if (r0 != 0) goto L_0x0035
            boolean r0 = r3.isAnonymousClass()
            if (r0 != 0) goto L_0x0017
            boolean r0 = r3.isLocalClass()
            if (r0 == 0) goto L_0x0035
        L_0x0017:
            r2 = 1
        L_0x0018:
            r1 = 0
            r0 = 1
            r4 = r9
            if (r2 != 0) goto L_0x0024
            boolean r0 = A00(r9, r3, r0)
            r6 = 0
            if (r0 == 0) goto L_0x0027
        L_0x0024:
            r6 = 1
            if (r2 != 0) goto L_0x002e
        L_0x0027:
            boolean r0 = A00(r9, r3, r1)
            r5 = 0
            if (r0 == 0) goto L_0x002f
        L_0x002e:
            r5 = 1
        L_0x002f:
            if (r6 != 0) goto L_0x0037
            if (r5 != 0) goto L_0x0037
            r0 = 0
            return r0
        L_0x0035:
            r2 = 0
            goto L_0x0018
        L_0x0037:
            r7 = r10
            X.0XA r3 = new X.0XA
            r3.<init>(r4, r5, r6, r7, r8)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.Excluder.A1v(X.08D, X.0Fe):X.0Bd");
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Class<*>;Z)Z */
    public static boolean A00(Excluder excluder, Class cls, boolean z) {
        List<AnonymousClass07F> list;
        if (z) {
            list = excluder.A01;
        } else {
            list = excluder.A00;
        }
        for (AnonymousClass07F r0 : list) {
            if (r0.A8N(cls)) {
                return true;
            }
        }
        return false;
    }

    public final Excluder A01(AnonymousClass07F r4, boolean z, boolean z2) {
        try {
            Excluder excluder = (Excluder) super.clone();
            if (z) {
                ArrayList arrayList = new ArrayList(this.A01);
                excluder.A01 = arrayList;
                arrayList.add(r4);
            }
            if (z2) {
                ArrayList arrayList2 = new ArrayList(this.A00);
                excluder.A00 = arrayList2;
                arrayList2.add(r4);
            }
            return excluder;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override // java.lang.Object
    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
