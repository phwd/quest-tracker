package java.lang.invoke;

import sun.invoke.util.Wrapper;

/* access modifiers changed from: package-private */
public final class MethodTypeForm {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ERASE = 1;
    public static final int INTS = 4;
    public static final int LONGS = 5;
    public static final int NO_CHANGE = 0;
    public static final int RAW_RETURN = 6;
    public static final int UNWRAP = 3;
    public static final int WRAP = 2;
    final long argCounts;
    final int[] argToSlotTable;
    final MethodType basicType;
    final MethodType erasedType;
    final long primCounts;
    final int[] slotToArgTable;

    public MethodType erasedType() {
        return this.erasedType;
    }

    public MethodType basicType() {
        return this.basicType;
    }

    private boolean assertIsBasicType() {
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MethodTypeForm(java.lang.invoke.MethodType r22) {
        /*
        // Method dump skipped, instructions count: 339
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.invoke.MethodTypeForm.<init>(java.lang.invoke.MethodType):void");
    }

    private static long pack(int a, int b, int c, int d) {
        return (((long) ((a << 16) | b)) << 32) | ((long) ((c << 16) | d));
    }

    private static char unpack(long packed, int word) {
        return (char) ((int) (packed >> ((3 - word) * 16)));
    }

    public int parameterCount() {
        return unpack(this.argCounts, 3);
    }

    public int parameterSlotCount() {
        return unpack(this.argCounts, 2);
    }

    public int returnCount() {
        return unpack(this.argCounts, 1);
    }

    public int returnSlotCount() {
        return unpack(this.argCounts, 0);
    }

    public int primitiveParameterCount() {
        return unpack(this.primCounts, 3);
    }

    public int longPrimitiveParameterCount() {
        return unpack(this.primCounts, 2);
    }

    public int primitiveReturnCount() {
        return unpack(this.primCounts, 1);
    }

    public int longPrimitiveReturnCount() {
        return unpack(this.primCounts, 0);
    }

    public boolean hasPrimitives() {
        return this.primCounts != 0;
    }

    public boolean hasNonVoidPrimitives() {
        if (this.primCounts == 0) {
            return false;
        }
        if (primitiveParameterCount() != 0) {
            return true;
        }
        if (primitiveReturnCount() == 0 || returnCount() == 0) {
            return false;
        }
        return true;
    }

    public boolean hasLongPrimitives() {
        return (longPrimitiveParameterCount() | longPrimitiveReturnCount()) != 0;
    }

    public int parameterToArgSlot(int i) {
        return this.argToSlotTable[i + 1];
    }

    public int argSlotToParameter(int argSlot) {
        return this.slotToArgTable[argSlot] - 1;
    }

    static MethodTypeForm findForm(MethodType mt) {
        MethodType erased = canonicalize(mt, 1, 1);
        if (erased == null) {
            return new MethodTypeForm(mt);
        }
        return erased.form();
    }

    public static MethodType canonicalize(MethodType mt, int howRet, int howArgs) {
        Class<?>[] ptypes = mt.ptypes();
        Class<?>[] ptc = canonicalizeAll(ptypes, howArgs);
        Class<?> rtype = mt.returnType();
        Class<?> rtc = canonicalize(rtype, howRet);
        if (ptc == null && rtc == null) {
            return null;
        }
        if (rtc == null) {
            rtc = rtype;
        }
        if (ptc == null) {
            ptc = ptypes;
        }
        return MethodType.makeImpl(rtc, ptc, true);
    }

    static Class<?> canonicalize(Class<?> t, int how) {
        if (t != Object.class) {
            if (!t.isPrimitive()) {
                if (how == 1) {
                    return Object.class;
                }
                if (how == 3) {
                    Class<?> ct = Wrapper.asPrimitiveType(t);
                    if (ct != t) {
                        return ct;
                    }
                } else if (how != 6) {
                    return null;
                } else {
                    return Object.class;
                }
            } else if (t == Void.TYPE) {
                if (how == 2) {
                    return Void.class;
                }
                if (how == 6) {
                    return Integer.TYPE;
                }
            } else if (how == 2) {
                return Wrapper.asWrapperType(t);
            } else {
                if (how != 4) {
                    if (how != 5) {
                        if (how != 6 || t == Integer.TYPE || t == Long.TYPE || t == Float.TYPE || t == Double.TYPE) {
                            return null;
                        }
                        return Integer.TYPE;
                    } else if (t == Long.TYPE) {
                        return null;
                    } else {
                        return Long.TYPE;
                    }
                } else if (t == Integer.TYPE || t == Long.TYPE) {
                    return null;
                } else {
                    if (t == Double.TYPE) {
                        return Long.TYPE;
                    }
                    return Integer.TYPE;
                }
            }
        }
        return null;
    }

    static Class<?>[] canonicalizeAll(Class<?>[] ts, int how) {
        Class<?>[] cs = null;
        int imax = ts.length;
        for (int i = 0; i < imax; i++) {
            Class<?> c = canonicalize(ts[i], how);
            if (c == Void.TYPE) {
                c = null;
            }
            if (c != null) {
                if (cs == null) {
                    cs = (Class[]) ts.clone();
                }
                cs[i] = c;
            }
        }
        return cs;
    }

    public String toString() {
        return "Form" + ((Object) this.erasedType);
    }
}
