package java.lang.invoke;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.invoke.util.BytecodeDescriptor;
import sun.invoke.util.Wrapper;

public final class MethodType implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final int MAX_JVM_ARITY = 255;
    static final int MAX_MH_ARITY = 254;
    static final int MAX_MH_INVOKER_ARITY = 253;
    static final Class<?>[] NO_PTYPES = new Class[0];
    static final ConcurrentWeakInternSet<MethodType> internTable = new ConcurrentWeakInternSet<>();
    private static final MethodType[] objectOnlyTypes = new MethodType[20];
    private static final long ptypesOffset;
    private static final long rtypeOffset;
    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];
    private static final long serialVersionUID = 292;
    @Stable
    private MethodTypeForm form;
    @Stable
    private String methodDescriptor;
    private final Class<?>[] ptypes;
    private final Class<?> rtype;
    @Stable
    private MethodType wrapAlt;

    static {
        try {
            rtypeOffset = MethodHandleStatics.UNSAFE.objectFieldOffset(MethodType.class.getDeclaredField("rtype"));
            ptypesOffset = MethodHandleStatics.UNSAFE.objectFieldOffset(MethodType.class.getDeclaredField("ptypes"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private MethodType(Class<?> rtype2, Class<?>[] ptypes2, boolean trusted) {
        checkRtype(rtype2);
        checkPtypes(ptypes2);
        this.rtype = rtype2;
        this.ptypes = trusted ? ptypes2 : (Class[]) Arrays.copyOf(ptypes2, ptypes2.length);
    }

    private MethodType(Class<?>[] ptypes2, Class<?> rtype2) {
        this.rtype = rtype2;
        this.ptypes = ptypes2;
    }

    /* access modifiers changed from: package-private */
    public MethodTypeForm form() {
        return this.form;
    }

    public Class<?> rtype() {
        return this.rtype;
    }

    public Class<?>[] ptypes() {
        return this.ptypes;
    }

    private static void checkRtype(Class<?> rtype2) {
        Objects.requireNonNull(rtype2);
    }

    private static void checkPtype(Class<?> ptype) {
        Objects.requireNonNull(ptype);
        if (ptype == Void.TYPE) {
            throw MethodHandleStatics.newIllegalArgumentException("parameter type cannot be void");
        }
    }

    private static int checkPtypes(Class<?>[] ptypes2) {
        int slots = 0;
        for (Class<?> ptype : ptypes2) {
            checkPtype(ptype);
            if (ptype == Double.TYPE || ptype == Long.TYPE) {
                slots++;
            }
        }
        checkSlotCount(ptypes2.length + slots);
        return slots;
    }

    static void checkSlotCount(int count) {
        if ((count & 255) != count) {
            throw MethodHandleStatics.newIllegalArgumentException("bad parameter count " + count);
        }
    }

    private static IndexOutOfBoundsException newIndexOutOfBoundsException(Object num) {
        if (num instanceof Integer) {
            num = "bad index: " + num;
        }
        return new IndexOutOfBoundsException(num.toString());
    }

    public static MethodType methodType(Class<?> rtype2, Class<?>[] ptypes2) {
        return makeImpl(rtype2, ptypes2, false);
    }

    public static MethodType methodType(Class<?> rtype2, List<Class<?>> ptypes2) {
        return makeImpl(rtype2, listToArray(ptypes2), false);
    }

    private static Class<?>[] listToArray(List<Class<?>> ptypes2) {
        checkSlotCount(ptypes2.size());
        return (Class[]) ptypes2.toArray(NO_PTYPES);
    }

    public static MethodType methodType(Class<?> rtype2, Class<?> ptype0, Class<?>... ptypes2) {
        Class<?>[] ptypes1 = new Class[(ptypes2.length + 1)];
        ptypes1[0] = ptype0;
        System.arraycopy(ptypes2, 0, ptypes1, 1, ptypes2.length);
        return makeImpl(rtype2, ptypes1, true);
    }

    public static MethodType methodType(Class<?> rtype2) {
        return makeImpl(rtype2, NO_PTYPES, true);
    }

    public static MethodType methodType(Class<?> rtype2, Class<?> ptype0) {
        return makeImpl(rtype2, new Class[]{ptype0}, true);
    }

    public static MethodType methodType(Class<?> rtype2, MethodType ptypes2) {
        return makeImpl(rtype2, ptypes2.ptypes, true);
    }

    static MethodType makeImpl(Class<?> rtype2, Class<?>[] ptypes2, boolean trusted) {
        MethodType mt = internTable.get(new MethodType(ptypes2, rtype2));
        if (mt != null) {
            return mt;
        }
        if (ptypes2.length == 0) {
            ptypes2 = NO_PTYPES;
            trusted = true;
        }
        MethodType mt2 = new MethodType(rtype2, ptypes2, trusted);
        mt2.form = MethodTypeForm.findForm(mt2);
        return internTable.add(mt2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public static MethodType genericMethodType(int objectArgCount, boolean finalArray) {
        MethodType mt;
        checkSlotCount(objectArgCount);
        int ootIndex = (objectArgCount * 2) + (finalArray ? 1 : 0);
        MethodType[] methodTypeArr = objectOnlyTypes;
        if (ootIndex < methodTypeArr.length && (mt = methodTypeArr[ootIndex]) != null) {
            return mt;
        }
        Class<?>[] ptypes2 = new Class[(objectArgCount + finalArray)];
        Arrays.fill(ptypes2, Object.class);
        if (finalArray != 0) {
            ptypes2[objectArgCount] = Object[].class;
        }
        MethodType mt2 = makeImpl(Object.class, ptypes2, true);
        MethodType[] methodTypeArr2 = objectOnlyTypes;
        if (ootIndex < methodTypeArr2.length) {
            methodTypeArr2[ootIndex] = mt2;
        }
        return mt2;
    }

    public static MethodType genericMethodType(int objectArgCount) {
        return genericMethodType(objectArgCount, false);
    }

    public MethodType changeParameterType(int num, Class<?> nptype) {
        if (parameterType(num) == nptype) {
            return this;
        }
        checkPtype(nptype);
        Class<?>[] nptypes = (Class[]) this.ptypes.clone();
        nptypes[num] = nptype;
        return makeImpl(this.rtype, nptypes, true);
    }

    public MethodType insertParameterTypes(int num, Class<?>... ptypesToInsert) {
        int len = this.ptypes.length;
        if (num < 0 || num > len) {
            throw newIndexOutOfBoundsException(Integer.valueOf(num));
        }
        checkSlotCount(parameterSlotCount() + ptypesToInsert.length + checkPtypes(ptypesToInsert));
        int ilen = ptypesToInsert.length;
        if (ilen == 0) {
            return this;
        }
        Class<?>[] nptypes = (Class[]) Arrays.copyOfRange(this.ptypes, 0, len + ilen);
        System.arraycopy(nptypes, num, nptypes, num + ilen, len - num);
        System.arraycopy(ptypesToInsert, 0, nptypes, num, ilen);
        return makeImpl(this.rtype, nptypes, true);
    }

    public MethodType appendParameterTypes(Class<?>... ptypesToInsert) {
        return insertParameterTypes(parameterCount(), ptypesToInsert);
    }

    public MethodType insertParameterTypes(int num, List<Class<?>> ptypesToInsert) {
        return insertParameterTypes(num, listToArray(ptypesToInsert));
    }

    public MethodType appendParameterTypes(List<Class<?>> ptypesToInsert) {
        return insertParameterTypes(parameterCount(), ptypesToInsert);
    }

    /* access modifiers changed from: package-private */
    public MethodType replaceParameterTypes(int start, int end, Class<?>... ptypesToInsert) {
        if (start == end) {
            return insertParameterTypes(start, ptypesToInsert);
        }
        int len = this.ptypes.length;
        if (start < 0 || start > end || end > len) {
            throw newIndexOutOfBoundsException("start=" + start + " end=" + end);
        } else if (ptypesToInsert.length == 0) {
            return dropParameterTypes(start, end);
        } else {
            return dropParameterTypes(start, end).insertParameterTypes(start, ptypesToInsert);
        }
    }

    /* access modifiers changed from: package-private */
    public MethodType asSpreaderType(Class<?> arrayType, int arrayLength) {
        int spreadPos = this.ptypes.length - arrayLength;
        if (arrayLength == 0) {
            return this;
        }
        if (arrayType == Object[].class) {
            if (isGeneric()) {
                return this;
            }
            if (spreadPos == 0) {
                MethodType res = genericMethodType(arrayLength);
                Class<?> cls = this.rtype;
                if (cls != Object.class) {
                    return res.changeReturnType(cls);
                }
                return res;
            }
        }
        Class<?> elemType = arrayType.getComponentType();
        int i = spreadPos;
        while (true) {
            Class<?>[] clsArr = this.ptypes;
            if (i >= clsArr.length) {
                return this;
            }
            if (clsArr[i] != elemType) {
                Class<?>[] fixedPtypes = (Class[]) clsArr.clone();
                Arrays.fill(fixedPtypes, i, this.ptypes.length, elemType);
                return methodType(this.rtype, fixedPtypes);
            }
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public Class<?> leadingReferenceParameter() {
        Class<?>[] clsArr = this.ptypes;
        if (clsArr.length != 0) {
            Class<?> ptype = clsArr[0];
            if (!ptype.isPrimitive()) {
                return ptype;
            }
        }
        throw MethodHandleStatics.newIllegalArgumentException("no leading reference parameter");
    }

    /* access modifiers changed from: package-private */
    public MethodType asCollectorType(Class<?> arrayType, int arrayLength) {
        MethodType res;
        if (arrayType == Object[].class) {
            res = genericMethodType(arrayLength);
            Class<?> cls = this.rtype;
            if (cls != Object.class) {
                res = res.changeReturnType(cls);
            }
        } else {
            res = methodType(this.rtype, Collections.nCopies(arrayLength, arrayType.getComponentType()));
        }
        if (this.ptypes.length == 1) {
            return res;
        }
        return res.insertParameterTypes(0, parameterList().subList(0, this.ptypes.length - 1));
    }

    public MethodType dropParameterTypes(int start, int end) {
        Class<?>[] nptypes;
        Class<?>[] nptypes2 = this.ptypes;
        int len = nptypes2.length;
        if (start < 0 || start > end || end > len) {
            throw newIndexOutOfBoundsException("start=" + start + " end=" + end);
        } else if (start == end) {
            return this;
        } else {
            if (start == 0) {
                if (end == len) {
                    nptypes = NO_PTYPES;
                } else {
                    nptypes = (Class[]) Arrays.copyOfRange(nptypes2, end, len);
                }
            } else if (end == len) {
                nptypes = (Class[]) Arrays.copyOfRange(nptypes2, 0, start);
            } else {
                int tail = len - end;
                nptypes = (Class[]) Arrays.copyOfRange(nptypes2, 0, start + tail);
                System.arraycopy(this.ptypes, end, nptypes, start, tail);
            }
            return makeImpl(this.rtype, nptypes, true);
        }
    }

    public MethodType changeReturnType(Class<?> nrtype) {
        if (returnType() == nrtype) {
            return this;
        }
        return makeImpl(nrtype, this.ptypes, true);
    }

    public boolean hasPrimitives() {
        return this.form.hasPrimitives();
    }

    public boolean hasWrappers() {
        return unwrap() != this;
    }

    public MethodType erase() {
        return this.form.erasedType();
    }

    public MethodType generic() {
        return genericMethodType(parameterCount());
    }

    /* access modifiers changed from: package-private */
    public boolean isGeneric() {
        return this == erase() && !hasPrimitives();
    }

    public MethodType wrap() {
        return hasPrimitives() ? wrapWithPrims(this) : this;
    }

    public MethodType unwrap() {
        return unwrapWithNoPrims(!hasPrimitives() ? this : wrapWithPrims(this));
    }

    private static MethodType wrapWithPrims(MethodType pt) {
        MethodType wt = pt.wrapAlt;
        if (wt != null) {
            return wt;
        }
        MethodType wt2 = MethodTypeForm.canonicalize(pt, 2, 2);
        pt.wrapAlt = wt2;
        return wt2;
    }

    private static MethodType unwrapWithNoPrims(MethodType wt) {
        MethodType uwt = wt.wrapAlt;
        if (uwt == null) {
            uwt = MethodTypeForm.canonicalize(wt, 3, 3);
            if (uwt == null) {
                uwt = wt;
            }
            wt.wrapAlt = uwt;
        }
        return uwt;
    }

    public Class<?> parameterType(int num) {
        return this.ptypes[num];
    }

    public int parameterCount() {
        return this.ptypes.length;
    }

    public Class<?> returnType() {
        return this.rtype;
    }

    public List<Class<?>> parameterList() {
        return Collections.unmodifiableList(Arrays.asList((Class[]) this.ptypes.clone()));
    }

    /* access modifiers changed from: package-private */
    public Class<?> lastParameterType() {
        Class<?>[] clsArr = this.ptypes;
        int len = clsArr.length;
        return len == 0 ? Void.TYPE : clsArr[len - 1];
    }

    public Class<?>[] parameterArray() {
        return (Class[]) this.ptypes.clone();
    }

    public boolean equals(Object x) {
        return this == x || ((x instanceof MethodType) && equals((MethodType) x));
    }

    private boolean equals(MethodType that) {
        return this.rtype == that.rtype && Arrays.equals(this.ptypes, that.ptypes);
    }

    public int hashCode() {
        int hashCode = this.rtype.hashCode() + 31;
        for (Class<?> ptype : this.ptypes) {
            hashCode = (hashCode * 31) + ptype.hashCode();
        }
        return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < this.ptypes.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(this.ptypes[i].getSimpleName());
        }
        sb.append(")");
        sb.append(this.rtype.getSimpleName());
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean isConvertibleTo(MethodType newType) {
        MethodTypeForm oldForm = form();
        MethodTypeForm newForm = newType.form();
        if (oldForm == newForm) {
            return true;
        }
        if (!canConvert(returnType(), newType.returnType())) {
            return false;
        }
        Class<?>[] srcTypes = newType.ptypes;
        Class<?>[] dstTypes = this.ptypes;
        if (srcTypes == dstTypes) {
            return true;
        }
        int argc = srcTypes.length;
        if (argc != dstTypes.length) {
            return false;
        }
        if (argc <= 1) {
            if (argc != 1 || canConvert(srcTypes[0], dstTypes[0])) {
                return true;
            }
            return false;
        } else if ((oldForm.primitiveParameterCount() == 0 && oldForm.erasedType == this) || (newForm.primitiveParameterCount() == 0 && newForm.erasedType == newType)) {
            return true;
        } else {
            return canConvertParameters(srcTypes, dstTypes);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean explicitCastEquivalentToAsType(MethodType newType) {
        if (this == newType) {
            return true;
        }
        if (!explicitCastEquivalentToAsType(this.rtype, newType.rtype)) {
            return false;
        }
        Class<?>[] srcTypes = newType.ptypes;
        Class<?>[] dstTypes = this.ptypes;
        if (dstTypes == srcTypes) {
            return true;
        }
        for (int i = 0; i < dstTypes.length; i++) {
            if (!explicitCastEquivalentToAsType(srcTypes[i], dstTypes[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean explicitCastEquivalentToAsType(Class<?> src, Class<?> dst) {
        if (src == dst || dst == Object.class || dst == Void.TYPE) {
            return true;
        }
        if (src.isPrimitive() && src != Void.TYPE) {
            return canConvert(src, dst);
        }
        if (dst.isPrimitive()) {
            return false;
        }
        if (!dst.isInterface() || dst.isAssignableFrom(src)) {
            return true;
        }
        return false;
    }

    private boolean canConvertParameters(Class<?>[] srcTypes, Class<?>[] dstTypes) {
        for (int i = 0; i < srcTypes.length; i++) {
            if (!canConvert(srcTypes[i], dstTypes[i])) {
                return false;
            }
        }
        return true;
    }

    static boolean canConvert(Class<?> src, Class<?> dst) {
        if (src == dst || src == Object.class || dst == Object.class) {
            return true;
        }
        if (src.isPrimitive()) {
            if (src == Void.TYPE) {
                return true;
            }
            Wrapper sw = Wrapper.forPrimitiveType(src);
            if (dst.isPrimitive()) {
                return Wrapper.forPrimitiveType(dst).isConvertibleFrom(sw);
            }
            return dst.isAssignableFrom(sw.wrapperType());
        } else if (!dst.isPrimitive() || dst == Void.TYPE) {
            return true;
        } else {
            Wrapper dw = Wrapper.forPrimitiveType(dst);
            if (src.isAssignableFrom(dw.wrapperType())) {
                return true;
            }
            if (!Wrapper.isWrapperType(src) || !dw.isConvertibleFrom(Wrapper.forWrapperType(src))) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public int parameterSlotCount() {
        return this.form.parameterSlotCount();
    }

    public static MethodType fromMethodDescriptorString(String descriptor, ClassLoader loader) throws IllegalArgumentException, TypeNotPresentException {
        if (!descriptor.startsWith("(") || descriptor.indexOf(41) < 0 || descriptor.indexOf(46) >= 0) {
            throw MethodHandleStatics.newIllegalArgumentException("not a method descriptor: " + descriptor);
        }
        List<Class<?>> types = BytecodeDescriptor.parseMethod(descriptor, loader);
        checkSlotCount(types.size());
        return makeImpl(types.remove(types.size() - 1), listToArray(types), true);
    }

    public String toMethodDescriptorString() {
        String desc = this.methodDescriptor;
        if (desc != null) {
            return desc;
        }
        String desc2 = BytecodeDescriptor.unparse(this);
        this.methodDescriptor = desc2;
        return desc2;
    }

    static String toFieldDescriptorString(Class<?> cls) {
        return BytecodeDescriptor.unparse(cls);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(returnType());
        s.writeObject(parameterArray());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        Class<?> returnType = (Class) s.readObject();
        Class<?>[] parameterArray = (Class[]) s.readObject();
        checkRtype(returnType);
        checkPtypes(parameterArray);
        MethodType_init(returnType, (Class[]) parameterArray.clone());
    }

    private MethodType() {
        this.rtype = null;
        this.ptypes = null;
    }

    private void MethodType_init(Class<?> rtype2, Class<?>[] ptypes2) {
        checkRtype(rtype2);
        checkPtypes(ptypes2);
        MethodHandleStatics.UNSAFE.putObject(this, rtypeOffset, rtype2);
        MethodHandleStatics.UNSAFE.putObject(this, ptypesOffset, ptypes2);
    }

    private Object readResolve() {
        return methodType(this.rtype, this.ptypes);
    }

    /* access modifiers changed from: private */
    public static class ConcurrentWeakInternSet<T> {
        private final ConcurrentMap<WeakEntry<T>, WeakEntry<T>> map = new ConcurrentHashMap();
        private final ReferenceQueue<T> stale = new ReferenceQueue<>();

        public T get(T elem) {
            T res;
            if (elem != null) {
                expungeStaleElements();
                WeakEntry<T> value = this.map.get(new WeakEntry(elem));
                if (value == null || (res = value.get()) == null) {
                    return null;
                }
                return res;
            }
            throw new NullPointerException();
        }

        public T add(T elem) {
            T interned;
            if (elem != null) {
                WeakEntry<T> e = new WeakEntry<>(elem, this.stale);
                do {
                    expungeStaleElements();
                    WeakEntry<T> exist = this.map.putIfAbsent(e, e);
                    interned = exist == null ? elem : exist.get();
                } while (interned == null);
                return interned;
            }
            throw new NullPointerException();
        }

        private void expungeStaleElements() {
            while (true) {
                Reference<? extends T> reference = this.stale.poll();
                if (reference != null) {
                    this.map.remove(reference);
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: private */
        public static class WeakEntry<T> extends WeakReference<T> {
            public final int hashcode;

            public WeakEntry(T key, ReferenceQueue<T> queue) {
                super(key, queue);
                this.hashcode = key.hashCode();
            }

            public WeakEntry(T key) {
                super(key);
                this.hashcode = key.hashCode();
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof WeakEntry)) {
                    return false;
                }
                Object that = ((WeakEntry) obj).get();
                Object mine = get();
                if (that != null && mine != null) {
                    return mine.equals(that);
                }
                if (this == obj) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return this.hashcode;
            }
        }
    }
}
