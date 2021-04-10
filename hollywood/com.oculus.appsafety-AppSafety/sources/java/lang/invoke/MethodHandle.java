package java.lang.invoke;

import dalvik.system.EmulatedStackFrame;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.Transformers;
import java.util.List;

public abstract class MethodHandle {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int IGET = 9;
    public static final int INVOKE_CALLSITE_TRANSFORM = 6;
    public static final int INVOKE_DIRECT = 2;
    public static final int INVOKE_INTERFACE = 4;
    public static final int INVOKE_STATIC = 3;
    public static final int INVOKE_SUPER = 1;
    public static final int INVOKE_TRANSFORM = 5;
    public static final int INVOKE_VAR_HANDLE = 7;
    public static final int INVOKE_VAR_HANDLE_EXACT = 8;
    public static final int INVOKE_VIRTUAL = 0;
    public static final int IPUT = 10;
    public static final int SGET = 11;
    public static final int SPUT = 12;
    protected final long artFieldOrMethod;
    private MethodHandle cachedSpreadInvoker;
    protected final int handleKind;
    private MethodType nominalType;
    private final MethodType type;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PolymorphicSignature {
    }

    @PolymorphicSignature
    public final native Object invoke(Object... objArr) throws Throwable;

    @PolymorphicSignature
    public final native Object invokeExact(Object... objArr) throws Throwable;

    protected MethodHandle(long artFieldOrMethod2, int handleKind2, MethodType type2) {
        this.artFieldOrMethod = artFieldOrMethod2;
        this.handleKind = handleKind2;
        this.type = type2;
    }

    public MethodType type() {
        MethodType methodType = this.nominalType;
        if (methodType != null) {
            return methodType;
        }
        return this.type;
    }

    /*  JADX ERROR: MOVE_RESULT instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: MOVE_RESULT instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:604)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:542)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:230)
        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:119)
        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:103)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:313)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:249)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:217)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:110)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:56)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:93)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:59)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:244)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:237)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:342)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:295)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:264)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1259)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */
    public java.lang.Object invokeWithArguments(java.lang.Object... r4) throws java.lang.Throwable {
        /*
            r3 = this;
            r0 = 0
            monitor-enter(r3)
            java.lang.invoke.MethodHandle r1 = r3.cachedSpreadInvoker     // Catch:{ all -> 0x001b }
            if (r1 != 0) goto L_0x0011
            java.lang.invoke.MethodType r1 = r3.type()     // Catch:{ all -> 0x001b }
            r2 = 0
            java.lang.invoke.MethodHandle r1 = java.lang.invoke.MethodHandles.spreadInvoker(r1, r2)     // Catch:{ all -> 0x001b }
            r3.cachedSpreadInvoker = r1     // Catch:{ all -> 0x001b }
        L_0x0011:
            java.lang.invoke.MethodHandle r1 = r3.cachedSpreadInvoker     // Catch:{ all -> 0x001b }
            r0 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x001b }
            r1 = move-result
            return r1
        L_0x001b:
            r1 = move-exception
            monitor-exit(r3)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: java.lang.invoke.MethodHandle.invokeWithArguments(java.lang.Object[]):java.lang.Object");
    }

    public Object invokeWithArguments(List<?> arguments) throws Throwable {
        return invokeWithArguments(arguments.toArray());
    }

    public MethodHandle asType(MethodType newType) {
        if (newType == this.type) {
            return this;
        }
        return asTypeUncached(newType);
    }

    /* access modifiers changed from: package-private */
    public MethodHandle asTypeUncached(MethodType newType) {
        if (this.type.isConvertibleTo(newType)) {
            MethodHandle mh = duplicate();
            mh.nominalType = newType;
            return mh;
        }
        throw new WrongMethodTypeException("cannot convert " + ((Object) this) + " to " + ((Object) newType));
    }

    public MethodHandle asSpreader(Class<?> arrayType, int arrayLength) {
        MethodType postSpreadType = asSpreaderChecks(arrayType, arrayLength);
        int targetParamCount = postSpreadType.parameterCount();
        return new Transformers.Spreader(this, postSpreadType.dropParameterTypes(targetParamCount - arrayLength, targetParamCount).appendParameterTypes(arrayType), arrayLength);
    }

    private MethodType asSpreaderChecks(Class<?> arrayType, int arrayLength) {
        spreadArrayChecks(arrayType, arrayLength);
        int nargs = type().parameterCount();
        if (nargs < arrayLength || arrayLength < 0) {
            throw MethodHandleStatics.newIllegalArgumentException("bad spread array length");
        }
        Class<?> arrayElement = arrayType.getComponentType();
        MethodType mtype = type();
        boolean match = true;
        boolean fail = false;
        int i = nargs - arrayLength;
        while (true) {
            if (i >= nargs) {
                break;
            }
            Class<?> ptype = mtype.parameterType(i);
            if (ptype != arrayElement) {
                match = false;
                if (!MethodType.canConvert(arrayElement, ptype)) {
                    fail = true;
                    break;
                }
            }
            i++;
        }
        if (match) {
            return mtype;
        }
        MethodType needType = mtype.asSpreaderType(arrayType, arrayLength);
        if (!fail) {
            return needType;
        }
        asType(needType);
        throw MethodHandleStatics.newInternalError("should not return", null);
    }

    private void spreadArrayChecks(Class<?> arrayType, int arrayLength) {
        Class<?> arrayElement = arrayType.getComponentType();
        if (arrayElement == null) {
            throw MethodHandleStatics.newIllegalArgumentException("not an array type", arrayType);
        } else if ((arrayLength & 127) == arrayLength) {
        } else {
            if ((arrayLength & 255) != arrayLength) {
                throw MethodHandleStatics.newIllegalArgumentException("array length is not legal", Integer.valueOf(arrayLength));
            } else if (arrayElement == Long.TYPE || arrayElement == Double.TYPE) {
                throw MethodHandleStatics.newIllegalArgumentException("array length is not legal for long[] or double[]", Integer.valueOf(arrayLength));
            }
        }
    }

    public MethodHandle asCollector(Class<?> arrayType, int arrayLength) {
        asCollectorChecks(arrayType, arrayLength);
        return new Transformers.Collector(this, arrayType, arrayLength);
    }

    /* access modifiers changed from: package-private */
    public boolean asCollectorChecks(Class<?> arrayType, int arrayLength) {
        spreadArrayChecks(arrayType, arrayLength);
        int nargs = type().parameterCount();
        if (nargs != 0) {
            Class<?> lastParam = type().parameterType(nargs - 1);
            if (lastParam == arrayType) {
                return true;
            }
            if (lastParam.isAssignableFrom(arrayType)) {
                return false;
            }
        }
        throw MethodHandleStatics.newIllegalArgumentException("array type not assignable to trailing argument", this, arrayType);
    }

    public MethodHandle asVarargsCollector(Class<?> arrayType) {
        arrayType.getClass();
        boolean lastMatch = asCollectorChecks(arrayType, 0);
        if (!isVarargsCollector() || !lastMatch) {
            return new Transformers.VarargsCollector(this);
        }
        return this;
    }

    public boolean isVarargsCollector() {
        return false;
    }

    public MethodHandle asFixedArity() {
        if (isVarargsCollector()) {
            return ((Transformers.VarargsCollector) this).asFixedArity();
        }
        return this;
    }

    public MethodHandle bindTo(Object x) {
        return new Transformers.BindTo(this, this.type.leadingReferenceParameter().cast(x));
    }

    public String toString() {
        return standardString();
    }

    /* access modifiers changed from: package-private */
    public String standardString() {
        return "MethodHandle" + ((Object) this.type);
    }

    public int getHandleKind() {
        return this.handleKind;
    }

    /* access modifiers changed from: protected */
    public void transform(EmulatedStackFrame arguments) throws Throwable {
        throw new AssertionError((Object) "MethodHandle.transform should never be called.");
    }

    /* access modifiers changed from: protected */
    public MethodHandle duplicate() {
        try {
            return (MethodHandle) clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError((Object) "Subclass of Transformer is not cloneable");
        }
    }

    private void transformInternal(EmulatedStackFrame arguments) throws Throwable {
        transform(arguments);
    }
}
