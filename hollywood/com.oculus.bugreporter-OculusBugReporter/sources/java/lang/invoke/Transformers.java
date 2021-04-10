package java.lang.invoke;

import dalvik.system.EmulatedStackFrame;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import sun.invoke.util.Wrapper;
import sun.misc.Unsafe;

public class Transformers {
    private static final Method TRANSFORM_INTERNAL;

    private Transformers() {
    }

    static {
        try {
            TRANSFORM_INTERNAL = MethodHandle.class.getDeclaredMethod("transformInternal", EmulatedStackFrame.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError();
        }
    }

    public static abstract class Transformer extends MethodHandle implements Cloneable {
        protected Transformer(MethodType type) {
            super(Transformers.TRANSFORM_INTERNAL.getArtMethod(), 5, type);
        }

        protected Transformer(MethodType type, int invokeKind) {
            super(Transformers.TRANSFORM_INTERNAL.getArtMethod(), invokeKind, type);
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class AlwaysThrow extends Transformer {
        private final Class<? extends Throwable> exceptionType;

        public AlwaysThrow(Class<?> nominalReturnType, Class<? extends Throwable> exType) {
            super(MethodType.methodType(nominalReturnType, exType));
            this.exceptionType = exType;
        }

        @Override // java.lang.invoke.MethodHandle
        public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
            throw ((Throwable) emulatedStackFrame.getReference(0, this.exceptionType));
        }
    }

    public static class DropArguments extends Transformer {
        private final MethodHandle delegate;
        private final EmulatedStackFrame.Range range1;
        private final EmulatedStackFrame.Range range2;

        public DropArguments(MethodType type, MethodHandle delegate2, int startPos, int numDropped) {
            super(type);
            this.delegate = delegate2;
            this.range1 = EmulatedStackFrame.Range.of(type, 0, startPos);
            int numArgs = type.ptypes().length;
            if (startPos + numDropped < numArgs) {
                this.range2 = EmulatedStackFrame.Range.of(type, startPos + numDropped, numArgs);
            } else {
                this.range2 = null;
            }
        }

        @Override // java.lang.invoke.MethodHandle
        public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
            EmulatedStackFrame calleeFrame = EmulatedStackFrame.create(this.delegate.type());
            emulatedStackFrame.copyRangeTo(calleeFrame, this.range1, 0, 0);
            if (this.range2 != null) {
                emulatedStackFrame.copyRangeTo(calleeFrame, this.range2, this.range1.numReferences, this.range1.numBytes);
            }
            MethodHandle methodHandle = this.delegate;
            calleeFrame.copyReturnValueTo(emulatedStackFrame);
        }
    }

    public static class CatchException extends Transformer {
        private final Class<?> exType;
        private final MethodHandle handler;
        private final EmulatedStackFrame.Range handlerArgsRange;
        private final MethodHandle target;

        public CatchException(MethodHandle target2, MethodHandle handler2, Class<?> exType2) {
            super(target2.type());
            this.target = target2;
            this.handler = handler2;
            this.exType = exType2;
            this.handlerArgsRange = EmulatedStackFrame.Range.of(target2.type(), 0, handler2.type().parameterCount() - 1);
        }

        @Override // java.lang.invoke.MethodHandle
        public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
            try {
                MethodHandle methodHandle = this.target;
            } catch (Throwable th) {
                if (th.getClass() == this.exType) {
                    EmulatedStackFrame fallback = EmulatedStackFrame.create(this.handler.type());
                    fallback.setReference(0, th);
                    emulatedStackFrame.copyRangeTo(fallback, this.handlerArgsRange, 1, 0);
                    MethodHandle methodHandle2 = this.handler;
                    fallback.copyReturnValueTo(emulatedStackFrame);
                    return;
                }
                throw th;
            }
        }
    }

    public static class GuardWithTest extends Transformer {
        private final MethodHandle fallback;
        private final MethodHandle target;
        private final MethodHandle test;
        private final EmulatedStackFrame.Range testArgsRange;

        public GuardWithTest(MethodHandle test2, MethodHandle target2, MethodHandle fallback2) {
            super(target2.type());
            this.test = test2;
            this.target = target2;
            this.fallback = fallback2;
            this.testArgsRange = EmulatedStackFrame.Range.of(target2.type(), 0, test2.type().parameterCount());
        }

        @Override // java.lang.invoke.MethodHandle
        public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
            emulatedStackFrame.copyRangeTo(EmulatedStackFrame.create(this.test.type()), this.testArgsRange, 0, 0);
            MethodHandle methodHandle = this.test;
            if (

            public static class ReferenceArrayElementGetter extends Transformer {
                private final Class<?> arrayClass;

                public ReferenceArrayElementGetter(Class<?> arrayClass2) {
                    super(MethodType.methodType(arrayClass2.getComponentType(), new Class[]{arrayClass2, Integer.TYPE}));
                    this.arrayClass = arrayClass2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                    reader.attach(emulatedStackFrame);
                    int index = reader.nextInt();
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(emulatedStackFrame);
                    writer.makeReturnValueAccessor();
                    writer.putNextReference(((Object[]) reader.nextReference(this.arrayClass))[index], this.arrayClass.getComponentType());
                }
            }

            public static class ReferenceArrayElementSetter extends Transformer {
                private final Class<?> arrayClass;

                public ReferenceArrayElementSetter(Class<?> arrayClass2) {
                    super(MethodType.methodType(Void.TYPE, new Class[]{arrayClass2, Integer.TYPE, arrayClass2.getComponentType()}));
                    this.arrayClass = arrayClass2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                    reader.attach(emulatedStackFrame);
                    ((Object[]) reader.nextReference(this.arrayClass))[reader.nextInt()] = reader.nextReference(this.arrayClass.getComponentType());
                }
            }

            public static class ReferenceIdentity extends Transformer {
                private final Class<?> type;

                public ReferenceIdentity(Class<?> type2) {
                    super(MethodType.methodType(type2, type2));
                    this.type = type2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                    reader.attach(emulatedStackFrame);
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(emulatedStackFrame);
                    writer.makeReturnValueAccessor();
                    writer.putNextReference(reader.nextReference(this.type), this.type);
                }
            }

            public static class Constant extends Transformer {
                private double asDouble;
                private float asFloat;
                private int asInt;
                private long asLong;
                private Object asReference;
                private final Class<?> type;
                private char typeChar;

                public Constant(Class<?> type2, Object value) {
                    super(MethodType.methodType(type2));
                    this.type = type2;
                    if (!type2.isPrimitive()) {
                        this.asReference = value;
                        this.typeChar = 'L';
                    } else if (type2 == Integer.TYPE) {
                        this.asInt = ((Integer) value).intValue();
                        this.typeChar = 'I';
                    } else if (type2 == Character.TYPE) {
                        this.asInt = ((Character) value).charValue();
                        this.typeChar = 'C';
                    } else if (type2 == Short.TYPE) {
                        this.asInt = ((Short) value).shortValue();
                        this.typeChar = 'S';
                    } else if (type2 == Byte.TYPE) {
                        this.asInt = ((Byte) value).byteValue();
                        this.typeChar = 'B';
                    } else if (type2 == Boolean.TYPE) {
                        this.asInt = ((Boolean) value).booleanValue() ? 1 : 0;
                        this.typeChar = 'Z';
                    } else if (type2 == Long.TYPE) {
                        this.asLong = ((Long) value).longValue();
                        this.typeChar = 'J';
                    } else if (type2 == Float.TYPE) {
                        this.asFloat = ((Float) value).floatValue();
                        this.typeChar = 'F';
                    } else if (type2 == Double.TYPE) {
                        this.asDouble = ((Double) value).doubleValue();
                        this.typeChar = 'D';
                    } else {
                        throw new AssertionError((Object) ("unknown type: " + this.typeChar));
                    }
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(emulatedStackFrame);
                    writer.makeReturnValueAccessor();
                    char c = this.typeChar;
                    if (c == 'F') {
                        writer.putNextFloat(this.asFloat);
                    } else if (c == 'L') {
                        writer.putNextReference(this.asReference, this.type);
                    } else if (c == 'S') {
                        writer.putNextShort((short) this.asInt);
                    } else if (c == 'Z') {
                        boolean z = true;
                        if (this.asInt != 1) {
                            z = false;
                        }
                        writer.putNextBoolean(z);
                    } else if (c == 'I') {
                        writer.putNextInt(this.asInt);
                    } else if (c != 'J') {
                        switch (c) {
                            case 'B':
                                writer.putNextByte((byte) this.asInt);
                                return;
                            case 'C':
                                writer.putNextChar((char) this.asInt);
                                return;
                            case 'D':
                                writer.putNextDouble(this.asDouble);
                                return;
                            default:
                                throw new AssertionError((Object) ("Unexpected typeChar: " + this.typeChar));
                        }
                    } else {
                        writer.putNextLong(this.asLong);
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public static class Construct extends Transformer {
                private final EmulatedStackFrame.Range callerRange = EmulatedStackFrame.Range.all(type());
                private final MethodHandle constructorHandle;

                Construct(MethodHandle constructorHandle2, MethodType returnedType) {
                    super(returnedType);
                    this.constructorHandle = constructorHandle2;
                }

                /* access modifiers changed from: package-private */
                public MethodHandle getConstructorHandle() {
                    return this.constructorHandle;
                }

                private static boolean isAbstract(Class<?> klass) {
                    return (klass.getModifiers() & 1024) == 1024;
                }

                private static void checkInstantiable(Class<?> klass) throws InstantiationException {
                    if (isAbstract(klass)) {
                        String s = klass.isInterface() ? "interface " : "abstract class ";
                        throw new InstantiationException("Can't instantiate " + s + ((Object) klass));
                    }
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    Class<?> receiverType = type().rtype();
                    checkInstantiable(receiverType);
                    Object receiver = Unsafe.getUnsafe().allocateInstance(receiverType);
                    EmulatedStackFrame constructorFrame = EmulatedStackFrame.create(this.constructorHandle.type());
                    constructorFrame.setReference(0, receiver);
                    emulatedStackFrame.copyRangeTo(constructorFrame, this.callerRange, 1, 0);
                    MethodHandle methodHandle = this.constructorHandle;
                    emulatedStackFrame.setReturnValueTo(receiver);
                }
            }

            public static class BindTo extends Transformer {
                private final MethodHandle delegate;
                private final EmulatedStackFrame.Range range = EmulatedStackFrame.Range.all(type());
                private final Object receiver;

                public BindTo(MethodHandle delegate2, Object receiver2) {
                    super(delegate2.type().dropParameterTypes(0, 1));
                    this.delegate = delegate2;
                    this.receiver = receiver2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    EmulatedStackFrame stackFrame = EmulatedStackFrame.create(this.delegate.type());
                    stackFrame.setReference(0, this.receiver);
                    emulatedStackFrame.copyRangeTo(stackFrame, this.range, 1, 0);
                    MethodHandle methodHandle = this.delegate;
                    stackFrame.copyReturnValueTo(emulatedStackFrame);
                }
            }

            public static class FilterReturnValue extends Transformer {
                private final EmulatedStackFrame.Range allArgs = EmulatedStackFrame.Range.all(type());
                private final MethodHandle filter;
                private final MethodHandle target;

                public FilterReturnValue(MethodHandle target2, MethodHandle filter2) {
                    super(MethodType.methodType(filter2.type().rtype(), target2.type().ptypes()));
                    this.target = target2;
                    this.filter = filter2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    EmulatedStackFrame targetFrame = EmulatedStackFrame.create(this.target.type());
                    emulatedStackFrame.copyRangeTo(targetFrame, this.allArgs, 0, 0);
                    MethodHandle methodHandle = this.target;
                    EmulatedStackFrame.StackFrameReader returnValueReader = new EmulatedStackFrame.StackFrameReader();
                    returnValueReader.attach(targetFrame);
                    returnValueReader.makeReturnValueAccessor();
                    EmulatedStackFrame filterFrame = EmulatedStackFrame.create(this.filter.type());
                    EmulatedStackFrame.StackFrameWriter filterWriter = new EmulatedStackFrame.StackFrameWriter();
                    filterWriter.attach(filterFrame);
                    Class<?> returnType = this.target.type().rtype();
                    if (!returnType.isPrimitive()) {
                        filterWriter.putNextReference(returnValueReader.nextReference(returnType), returnType);
                    } else if (returnType == Boolean.TYPE) {
                        filterWriter.putNextBoolean(returnValueReader.nextBoolean());
                    } else if (returnType == Byte.TYPE) {
                        filterWriter.putNextByte(returnValueReader.nextByte());
                    } else if (returnType == Character.TYPE) {
                        filterWriter.putNextChar(returnValueReader.nextChar());
                    } else if (returnType == Short.TYPE) {
                        filterWriter.putNextShort(returnValueReader.nextShort());
                    } else if (returnType == Integer.TYPE) {
                        filterWriter.putNextInt(returnValueReader.nextInt());
                    } else if (returnType == Long.TYPE) {
                        filterWriter.putNextLong(returnValueReader.nextLong());
                    } else if (returnType == Float.TYPE) {
                        filterWriter.putNextFloat(returnValueReader.nextFloat());
                    } else if (returnType == Double.TYPE) {
                        filterWriter.putNextDouble(returnValueReader.nextDouble());
                    }
                    MethodHandle methodHandle2 = this.filter;
                    filterFrame.copyReturnValueTo(emulatedStackFrame);
                }
            }

            public static class PermuteArguments extends Transformer {
                private final int[] reorder;
                private final MethodHandle target;

                public PermuteArguments(MethodType type, MethodHandle target2, int[] reorder2) {
                    super(type);
                    this.target = target2;
                    this.reorder = reorder2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                    reader.attach(emulatedStackFrame);
                    Object[] arguments = new Object[this.reorder.length];
                    Class<?>[] ptypes = type().ptypes();
                    for (int i = 0; i < ptypes.length; i++) {
                        Class<?> ptype = ptypes[i];
                        if (!ptype.isPrimitive()) {
                            arguments[i] = reader.nextReference(ptype);
                        } else if (ptype == Boolean.TYPE) {
                            arguments[i] = Boolean.valueOf(reader.nextBoolean());
                        } else if (ptype == Byte.TYPE) {
                            arguments[i] = Byte.valueOf(reader.nextByte());
                        } else if (ptype == Character.TYPE) {
                            arguments[i] = Character.valueOf(reader.nextChar());
                        } else if (ptype == Short.TYPE) {
                            arguments[i] = Short.valueOf(reader.nextShort());
                        } else if (ptype == Integer.TYPE) {
                            arguments[i] = Integer.valueOf(reader.nextInt());
                        } else if (ptype == Long.TYPE) {
                            arguments[i] = Long.valueOf(reader.nextLong());
                        } else if (ptype == Float.TYPE) {
                            arguments[i] = Float.valueOf(reader.nextFloat());
                        } else if (ptype == Double.TYPE) {
                            arguments[i] = Double.valueOf(reader.nextDouble());
                        } else {
                            throw new AssertionError((Object) ("Unexpected type: " + ((Object) ptype)));
                        }
                    }
                    EmulatedStackFrame calleeFrame = EmulatedStackFrame.create(this.target.type());
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(calleeFrame);
                    for (int i2 = 0; i2 < ptypes.length; i2++) {
                        int idx = this.reorder[i2];
                        Class<?> ptype2 = ptypes[idx];
                        Object argument = arguments[idx];
                        if (!ptype2.isPrimitive()) {
                            writer.putNextReference(argument, ptype2);
                        } else if (ptype2 == Boolean.TYPE) {
                            writer.putNextBoolean(((Boolean) argument).booleanValue());
                        } else if (ptype2 == Byte.TYPE) {
                            writer.putNextByte(((Byte) argument).byteValue());
                        } else if (ptype2 == Character.TYPE) {
                            writer.putNextChar(((Character) argument).charValue());
                        } else if (ptype2 == Short.TYPE) {
                            writer.putNextShort(((Short) argument).shortValue());
                        } else if (ptype2 == Integer.TYPE) {
                            writer.putNextInt(((Integer) argument).intValue());
                        } else if (ptype2 == Long.TYPE) {
                            writer.putNextLong(((Long) argument).longValue());
                        } else if (ptype2 == Float.TYPE) {
                            writer.putNextFloat(((Float) argument).floatValue());
                        } else if (ptype2 == Double.TYPE) {
                            writer.putNextDouble(((Double) argument).doubleValue());
                        } else {
                            throw new AssertionError((Object) ("Unexpected type: " + ((Object) ptype2)));
                        }
                    }
                    MethodHandle methodHandle = this.target;
                    calleeFrame.copyReturnValueTo(emulatedStackFrame);
                }
            }

            /* access modifiers changed from: package-private */
            public static class VarargsCollector extends Transformer {
                final MethodHandle target;

                VarargsCollector(MethodHandle target2) {
                    super(target2.type(), 6);
                    if (lastParameterTypeIsAnArray(target2.type().ptypes())) {
                        this.target = target2;
                        return;
                    }
                    throw new IllegalArgumentException("target does not have array as last parameter");
                }

                private static boolean lastParameterTypeIsAnArray(Class<?>[] parameterTypes) {
                    if (parameterTypes.length == 0) {
                        return false;
                    }
                    return parameterTypes[parameterTypes.length - 1].isArray();
                }

                @Override // java.lang.invoke.MethodHandle
                public boolean isVarargsCollector() {
                    return true;
                }

                @Override // java.lang.invoke.MethodHandle
                public MethodHandle asFixedArity() {
                    return this.target;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame callerFrame) throws Throwable {
                    MethodType callerFrameType = callerFrame.getMethodType();
                    Class<?>[] callerPTypes = callerFrameType.ptypes();
                    Class<?>[] targetPTypes = type().ptypes();
                    int lastTargetIndex = targetPTypes.length - 1;
                    if (callerPTypes.length != targetPTypes.length || !targetPTypes[lastTargetIndex].isAssignableFrom(callerPTypes[lastTargetIndex])) {
                        if (callerPTypes.length < targetPTypes.length - 1) {
                            throwWrongMethodTypeException(callerFrameType, type());
                        }
                        if (!MethodType.canConvert(type().rtype(), callerFrameType.rtype())) {
                            throwWrongMethodTypeException(callerFrameType, type());
                        }
                        if (!arityArgumentsConvertible(callerPTypes, lastTargetIndex, targetPTypes[lastTargetIndex].getComponentType())) {
                            throwWrongMethodTypeException(callerFrameType, type());
                        }
                        EmulatedStackFrame targetFrame = EmulatedStackFrame.create(makeTargetFrameType(callerFrameType, type()));
                        prepareFrame(callerFrame, targetFrame);
                        MethodHandle methodHandle = this.target;
                        targetFrame.copyReturnValueTo(callerFrame);
                        return;
                    }
                    MethodHandle methodHandle2 = this.target;
                }

                private static void throwWrongMethodTypeException(MethodType from, MethodType to) {
                    throw new WrongMethodTypeException("Cannot convert " + ((Object) from) + " to " + ((Object) to));
                }

                private static boolean arityArgumentsConvertible(Class<?>[] ptypes, int arityStart, Class<?> elementType) {
                    if (ptypes.length - 1 == arityStart && ptypes[arityStart].isArray() && ptypes[arityStart].getComponentType() == elementType) {
                        return true;
                    }
                    for (int i = arityStart; i < ptypes.length; i++) {
                        if (!MethodType.canConvert(ptypes[i], elementType)) {
                            return false;
                        }
                    }
                    return true;
                }

                private static Object referenceArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, Class<?> elementType, int offset, int length) {
                    Object arityArray = Array.newInstance(elementType, length);
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        Object o = null;
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'F') {
                            o = Float.valueOf(reader.nextFloat());
                        } else if (basicTypeChar == 'L') {
                            o = reader.nextReference(argumentType);
                        } else if (basicTypeChar == 'S') {
                            o = Short.valueOf(reader.nextShort());
                        } else if (basicTypeChar == 'Z') {
                            o = Boolean.valueOf(reader.nextBoolean());
                        } else if (basicTypeChar == 'I') {
                            o = Integer.valueOf(reader.nextInt());
                        } else if (basicTypeChar != 'J') {
                            switch (basicTypeChar) {
                                case 'B':
                                    o = Byte.valueOf(reader.nextByte());
                                    continue;
                                case 'C':
                                    o = Character.valueOf(reader.nextChar());
                                    continue;
                                case 'D':
                                    o = Double.valueOf(reader.nextDouble());
                                    continue;
                            }
                        } else {
                            o = Long.valueOf(reader.nextLong());
                        }
                        Array.set(arityArray, i, elementType.cast(o));
                    }
                    return arityArray;
                }

                private static Object intArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    int[] arityArray = new int[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'B') {
                            arityArray[i] = reader.nextByte();
                        } else if (basicTypeChar == 'I') {
                            arityArray[i] = reader.nextInt();
                        } else if (basicTypeChar != 'S') {
                            arityArray[i] = ((Integer) reader.nextReference(argumentType)).intValue();
                        } else {
                            arityArray[i] = reader.nextShort();
                        }
                    }
                    return arityArray;
                }

                private static Object longArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    long[] arityArray = new long[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'B') {
                            arityArray[i] = (long) reader.nextByte();
                        } else if (basicTypeChar == 'S') {
                            arityArray[i] = (long) reader.nextShort();
                        } else if (basicTypeChar == 'I') {
                            arityArray[i] = (long) reader.nextInt();
                        } else if (basicTypeChar != 'J') {
                            arityArray[i] = ((Long) reader.nextReference(argumentType)).longValue();
                        } else {
                            arityArray[i] = reader.nextLong();
                        }
                    }
                    return arityArray;
                }

                private static Object byteArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    byte[] arityArray = new byte[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        if (Wrapper.basicTypeChar(argumentType) != 'B') {
                            arityArray[i] = ((Byte) reader.nextReference(argumentType)).byteValue();
                        } else {
                            arityArray[i] = reader.nextByte();
                        }
                    }
                    return arityArray;
                }

                private static Object shortArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    short[] arityArray = new short[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'B') {
                            arityArray[i] = (short) reader.nextByte();
                        } else if (basicTypeChar != 'S') {
                            arityArray[i] = ((Short) reader.nextReference(argumentType)).shortValue();
                        } else {
                            arityArray[i] = reader.nextShort();
                        }
                    }
                    return arityArray;
                }

                private static Object charArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    char[] arityArray = new char[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        if (Wrapper.basicTypeChar(argumentType) != 'C') {
                            arityArray[i] = ((Character) reader.nextReference(argumentType)).charValue();
                        } else {
                            arityArray[i] = reader.nextChar();
                        }
                    }
                    return arityArray;
                }

                private static Object booleanArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    boolean[] arityArray = new boolean[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        if (Wrapper.basicTypeChar(argumentType) != 'Z') {
                            arityArray[i] = ((Boolean) reader.nextReference(argumentType)).booleanValue();
                        } else {
                            arityArray[i] = reader.nextBoolean();
                        }
                    }
                    return arityArray;
                }

                private static Object floatArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    float[] arityArray = new float[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'B') {
                            arityArray[i] = (float) reader.nextByte();
                        } else if (basicTypeChar == 'F') {
                            arityArray[i] = reader.nextFloat();
                        } else if (basicTypeChar == 'S') {
                            arityArray[i] = (float) reader.nextShort();
                        } else if (basicTypeChar == 'I') {
                            arityArray[i] = (float) reader.nextInt();
                        } else if (basicTypeChar != 'J') {
                            arityArray[i] = ((Float) reader.nextReference(argumentType)).floatValue();
                        } else {
                            arityArray[i] = (float) reader.nextLong();
                        }
                    }
                    return arityArray;
                }

                private static Object doubleArray(EmulatedStackFrame.StackFrameReader reader, Class<?>[] ptypes, int offset, int length) {
                    double[] arityArray = new double[length];
                    for (int i = 0; i < length; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'B') {
                            arityArray[i] = (double) reader.nextByte();
                        } else if (basicTypeChar == 'D') {
                            arityArray[i] = reader.nextDouble();
                        } else if (basicTypeChar == 'F') {
                            arityArray[i] = (double) reader.nextFloat();
                        } else if (basicTypeChar == 'S') {
                            arityArray[i] = (double) reader.nextShort();
                        } else if (basicTypeChar == 'I') {
                            arityArray[i] = (double) reader.nextInt();
                        } else if (basicTypeChar != 'J') {
                            arityArray[i] = ((Double) reader.nextReference(argumentType)).doubleValue();
                        } else {
                            arityArray[i] = (double) reader.nextLong();
                        }
                    }
                    return arityArray;
                }

                private static Object makeArityArray(MethodType callerFrameType, EmulatedStackFrame.StackFrameReader callerFrameReader, int indexOfArityArray, Class<?> arityArrayType) {
                    int arityArrayLength = callerFrameType.ptypes().length - indexOfArityArray;
                    Class<?> elementType = arityArrayType.getComponentType();
                    Class<?>[] callerPTypes = callerFrameType.ptypes();
                    char elementBasicType = Wrapper.basicTypeChar(elementType);
                    if (elementBasicType == 'F') {
                        return floatArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                    }
                    if (elementBasicType == 'L') {
                        return referenceArray(callerFrameReader, callerPTypes, elementType, indexOfArityArray, arityArrayLength);
                    }
                    if (elementBasicType == 'S') {
                        return shortArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                    }
                    if (elementBasicType == 'Z') {
                        return booleanArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                    }
                    if (elementBasicType == 'I') {
                        return intArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                    }
                    if (elementBasicType == 'J') {
                        return longArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                    }
                    switch (elementBasicType) {
                        case 'B':
                            return byteArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                        case 'C':
                            return charArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                        case 'D':
                            return doubleArray(callerFrameReader, callerPTypes, indexOfArityArray, arityArrayLength);
                        default:
                            throw new InternalError("Unexpected type: " + ((Object) elementType));
                    }
                }

                public static Object collectArguments(char basicComponentType, Class<?> componentType, EmulatedStackFrame.StackFrameReader reader, Class<?>[] types, int startIdx, int length) {
                    if (basicComponentType == 'F') {
                        return floatArray(reader, types, startIdx, length);
                    }
                    if (basicComponentType == 'L') {
                        return referenceArray(reader, types, componentType, startIdx, length);
                    }
                    if (basicComponentType == 'S') {
                        return shortArray(reader, types, startIdx, length);
                    }
                    if (basicComponentType == 'Z') {
                        return booleanArray(reader, types, startIdx, length);
                    }
                    if (basicComponentType == 'I') {
                        return intArray(reader, types, startIdx, length);
                    }
                    if (basicComponentType == 'J') {
                        return longArray(reader, types, startIdx, length);
                    }
                    switch (basicComponentType) {
                        case 'B':
                            return byteArray(reader, types, startIdx, length);
                        case 'C':
                            return charArray(reader, types, startIdx, length);
                        case 'D':
                            return doubleArray(reader, types, startIdx, length);
                        default:
                            throw new InternalError("Unexpected type: " + basicComponentType);
                    }
                }

                private static void copyParameter(EmulatedStackFrame.StackFrameReader reader, EmulatedStackFrame.StackFrameWriter writer, Class<?> ptype) {
                    char basicTypeChar = Wrapper.basicTypeChar(ptype);
                    if (basicTypeChar == 'F') {
                        writer.putNextFloat(reader.nextFloat());
                    } else if (basicTypeChar == 'L') {
                        writer.putNextReference(reader.nextReference(ptype), ptype);
                    } else if (basicTypeChar == 'S') {
                        writer.putNextShort(reader.nextShort());
                    } else if (basicTypeChar == 'Z') {
                        writer.putNextBoolean(reader.nextBoolean());
                    } else if (basicTypeChar == 'I') {
                        writer.putNextInt(reader.nextInt());
                    } else if (basicTypeChar != 'J') {
                        switch (basicTypeChar) {
                            case 'B':
                                writer.putNextByte(reader.nextByte());
                                return;
                            case 'C':
                                writer.putNextChar(reader.nextChar());
                                return;
                            case 'D':
                                writer.putNextDouble(reader.nextDouble());
                                return;
                            default:
                                throw new InternalError("Unexpected type: " + ((Object) ptype));
                        }
                    } else {
                        writer.putNextLong(reader.nextLong());
                    }
                }

                private static void prepareFrame(EmulatedStackFrame callerFrame, EmulatedStackFrame targetFrame) {
                    EmulatedStackFrame.StackFrameWriter targetWriter = new EmulatedStackFrame.StackFrameWriter();
                    targetWriter.attach(targetFrame);
                    EmulatedStackFrame.StackFrameReader callerReader = new EmulatedStackFrame.StackFrameReader();
                    callerReader.attach(callerFrame);
                    MethodType targetMethodType = targetFrame.getMethodType();
                    int indexOfArityArray = targetMethodType.ptypes().length - 1;
                    for (int i = 0; i < indexOfArityArray; i++) {
                        copyParameter(callerReader, targetWriter, targetMethodType.ptypes()[i]);
                    }
                    Class<?> arityArrayType = targetMethodType.ptypes()[indexOfArityArray];
                    targetWriter.putNextReference(makeArityArray(callerFrame.getMethodType(), callerReader, indexOfArityArray, arityArrayType), arityArrayType);
                }

                private static MethodType makeTargetFrameType(MethodType callerType, MethodType targetType) {
                    int ptypesLength = targetType.ptypes().length;
                    Class<?>[] ptypes = new Class[ptypesLength];
                    System.arraycopy(callerType.ptypes(), 0, ptypes, 0, ptypesLength - 1);
                    ptypes[ptypesLength - 1] = targetType.ptypes()[ptypesLength - 1];
                    return MethodType.methodType(callerType.rtype(), ptypes);
                }
            }

            /* access modifiers changed from: package-private */
            public static class Invoker extends Transformer {
                private final EmulatedStackFrame.Range copyRange = EmulatedStackFrame.Range.of(type(), 1, type().parameterCount());
                private final boolean isExactInvoker;
                private final MethodType targetType;

                Invoker(MethodType targetType2, boolean isExactInvoker2) {
                    super(targetType2.insertParameterTypes(0, MethodHandle.class));
                    this.targetType = targetType2;
                    this.isExactInvoker = isExactInvoker2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame emulatedStackFrame) throws Throwable {
                    if (this.isExactInvoker) {
                        MethodType callType = emulatedStackFrame.getCallsiteType().dropParameterTypes(0, 1);
                        if (!this.targetType.equals((Object) callType)) {
                            throw new WrongMethodTypeException("Wrong type, Expected: " + ((Object) this.targetType) + " was: " + ((Object) callType));
                        }
                    }
                    MethodHandle methodHandle = (MethodHandle) emulatedStackFrame.getReference(0, MethodHandle.class);
                    EmulatedStackFrame targetFrame = EmulatedStackFrame.create(this.targetType);
                    emulatedStackFrame.copyRangeTo(targetFrame, this.copyRange, 0, 0);
                    targetFrame.copyReturnValueTo(emulatedStackFrame);
                }
            }

            /* access modifiers changed from: package-private */
            public static class Spreader extends Transformer {
                private final int arrayOffset;
                private final char arrayTypeChar;
                private final EmulatedStackFrame.Range copyRange;
                private final int numArrayArgs;
                private final MethodHandle target;

                Spreader(MethodHandle target2, MethodType spreaderType, int numArrayArgs2) {
                    super(spreaderType);
                    this.target = target2;
                    this.arrayOffset = spreaderType.parameterCount() - 1;
                    Class<?> componentType = spreaderType.ptypes()[this.arrayOffset].getComponentType();
                    if (componentType != null) {
                        this.arrayTypeChar = Wrapper.basicTypeChar(componentType);
                        this.numArrayArgs = numArrayArgs2;
                        this.copyRange = EmulatedStackFrame.Range.of(spreaderType, 0, this.arrayOffset);
                        return;
                    }
                    throw new AssertionError((Object) "Trailing argument must be an array.");
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame callerFrame) throws Throwable {
                    EmulatedStackFrame targetFrame = EmulatedStackFrame.create(this.target.type());
                    callerFrame.copyRangeTo(targetFrame, this.copyRange, 0, 0);
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(targetFrame, this.arrayOffset, this.copyRange.numReferences, this.copyRange.numBytes);
                    Object arrayObj = callerFrame.getReference(this.copyRange.numReferences, type().ptypes()[this.arrayOffset]);
                    int arrayLength = Array.getLength(arrayObj);
                    if (arrayLength == this.numArrayArgs) {
                        MethodType type = this.target.type();
                        char c = this.arrayTypeChar;
                        if (c == 'F') {
                            spreadArray((float[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                        } else if (c == 'L') {
                            spreadArray((Object[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                        } else if (c == 'S') {
                            spreadArray((short[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                        } else if (c == 'Z') {
                            spreadArray((boolean[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                        } else if (c == 'I') {
                            spreadArray((int[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                        } else if (c != 'J') {
                            switch (c) {
                                case 'B':
                                    spreadArray((byte[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                                    break;
                                case 'C':
                                    spreadArray((char[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                                    break;
                                case 'D':
                                    spreadArray((double[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                                    break;
                            }
                        } else {
                            spreadArray((long[]) arrayObj, writer, type, this.numArrayArgs, this.arrayOffset);
                        }
                        MethodHandle methodHandle = this.target;
                        targetFrame.copyReturnValueTo(callerFrame);
                        return;
                    }
                    throw new IllegalArgumentException("Invalid array length: " + arrayLength);
                }

                public static void spreadArray(Object[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        Object o = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'F') {
                            writer.putNextFloat(((Float) o).floatValue());
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(o, argumentType);
                        } else if (basicTypeChar == 'S') {
                            writer.putNextShort(((Short) o).shortValue());
                        } else if (basicTypeChar == 'Z') {
                            writer.putNextBoolean(((Boolean) o).booleanValue());
                        } else if (basicTypeChar == 'I') {
                            writer.putNextInt(((Integer) o).intValue());
                        } else if (basicTypeChar != 'J') {
                            switch (basicTypeChar) {
                                case 'B':
                                    writer.putNextByte(((Byte) o).byteValue());
                                    continue;
                                case 'C':
                                    writer.putNextChar(((Character) o).charValue());
                                    continue;
                                case 'D':
                                    writer.putNextDouble(((Double) o).doubleValue());
                                    continue;
                            }
                        } else {
                            writer.putNextLong(((Long) o).longValue());
                        }
                    }
                }

                public static void spreadArray(int[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        int j = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'D') {
                            writer.putNextDouble((double) j);
                        } else if (basicTypeChar == 'F') {
                            writer.putNextFloat((float) j);
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(Integer.valueOf(j), argumentType);
                        } else if (basicTypeChar == 'I') {
                            writer.putNextInt(j);
                        } else if (basicTypeChar == 'J') {
                            writer.putNextLong((long) j);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }

                public static void spreadArray(long[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        long l = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'D') {
                            writer.putNextDouble((double) l);
                        } else if (basicTypeChar == 'F') {
                            writer.putNextFloat((float) l);
                        } else if (basicTypeChar == 'J') {
                            writer.putNextLong(l);
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(Long.valueOf(l), argumentType);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }

                public static void spreadArray(byte[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        byte b = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'B') {
                            writer.putNextByte(b);
                        } else if (basicTypeChar == 'D') {
                            writer.putNextDouble((double) b);
                        } else if (basicTypeChar == 'F') {
                            writer.putNextFloat((float) b);
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(Byte.valueOf(b), argumentType);
                        } else if (basicTypeChar == 'S') {
                            writer.putNextShort((short) b);
                        } else if (basicTypeChar == 'I') {
                            writer.putNextInt(b);
                        } else if (basicTypeChar == 'J') {
                            writer.putNextLong((long) b);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }

                public static void spreadArray(short[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        short s = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'D') {
                            writer.putNextDouble((double) s);
                        } else if (basicTypeChar == 'F') {
                            writer.putNextFloat((float) s);
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(Short.valueOf(s), argumentType);
                        } else if (basicTypeChar == 'S') {
                            writer.putNextShort(s);
                        } else if (basicTypeChar == 'I') {
                            writer.putNextInt(s);
                        } else if (basicTypeChar == 'J') {
                            writer.putNextLong((long) s);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }

                public static void spreadArray(char[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        char c = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'C') {
                            writer.putNextChar(c);
                        } else if (basicTypeChar == 'D') {
                            writer.putNextDouble((double) c);
                        } else if (basicTypeChar == 'F') {
                            writer.putNextFloat((float) c);
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(Character.valueOf(c), argumentType);
                        } else if (basicTypeChar == 'I') {
                            writer.putNextInt(c);
                        } else if (basicTypeChar == 'J') {
                            writer.putNextLong((long) c);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }

                public static void spreadArray(boolean[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        boolean z = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'L') {
                            writer.putNextReference(Boolean.valueOf(z), argumentType);
                        } else if (basicTypeChar == 'Z') {
                            writer.putNextBoolean(z);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }

                public static void spreadArray(double[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        double d = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'D') {
                            writer.putNextDouble(d);
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(Double.valueOf(d), argumentType);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }

                public static void spreadArray(float[] array, EmulatedStackFrame.StackFrameWriter writer, MethodType type, int numArgs, int offset) {
                    Class<?>[] ptypes = type.ptypes();
                    for (int i = 0; i < numArgs; i++) {
                        Class<?> argumentType = ptypes[i + offset];
                        float f = array[i];
                        char basicTypeChar = Wrapper.basicTypeChar(argumentType);
                        if (basicTypeChar == 'D') {
                            writer.putNextDouble((double) f);
                        } else if (basicTypeChar == 'F') {
                            writer.putNextFloat(f);
                        } else if (basicTypeChar == 'L') {
                            writer.putNextReference(Float.valueOf(f), argumentType);
                        } else {
                            throw new AssertionError();
                        }
                    }
                }
            }

            static class Collector extends Transformer {
                private final int arrayOffset;
                private final char arrayTypeChar;
                private final EmulatedStackFrame.Range copyRange;
                private final int numArrayArgs;
                private final MethodHandle target;

                Collector(MethodHandle delegate, Class<?> arrayType, int length) {
                    super(delegate.type().asCollectorType(arrayType, length));
                    this.target = delegate;
                    this.arrayOffset = delegate.type().parameterCount() - 1;
                    this.arrayTypeChar = Wrapper.basicTypeChar(arrayType.getComponentType());
                    this.numArrayArgs = length;
                    this.copyRange = EmulatedStackFrame.Range.of(delegate.type(), 0, this.arrayOffset);
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame callerFrame) throws Throwable {
                    EmulatedStackFrame targetFrame = EmulatedStackFrame.create(this.target.type());
                    callerFrame.copyRangeTo(targetFrame, this.copyRange, 0, 0);
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(targetFrame, this.arrayOffset, this.copyRange.numReferences, this.copyRange.numBytes);
                    EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                    reader.attach(callerFrame, this.arrayOffset, this.copyRange.numReferences, this.copyRange.numBytes);
                    char c = this.arrayTypeChar;
                    if (c == 'F') {
                        float[] array = new float[this.numArrayArgs];
                        for (int i = 0; i < this.numArrayArgs; i++) {
                            array[i] = reader.nextFloat();
                        }
                        writer.putNextReference(array, float[].class);
                    } else if (c == 'L') {
                        Class<?> targetType = this.target.type().ptypes()[this.arrayOffset];
                        Class<?> targetComponentType = targetType.getComponentType();
                        Class<?> adapterComponentType = type().lastParameterType();
                        Object[] arr = (Object[]) Array.newInstance(targetComponentType, this.numArrayArgs);
                        for (int i2 = 0; i2 < this.numArrayArgs; i2++) {
                            arr[i2] = reader.nextReference(adapterComponentType);
                        }
                        writer.putNextReference(arr, targetType);
                    } else if (c == 'S') {
                        short[] array2 = new short[this.numArrayArgs];
                        for (int i3 = 0; i3 < this.numArrayArgs; i3++) {
                            array2[i3] = reader.nextShort();
                        }
                        writer.putNextReference(array2, short[].class);
                    } else if (c == 'Z') {
                        boolean[] array3 = new boolean[this.numArrayArgs];
                        for (int i4 = 0; i4 < this.numArrayArgs; i4++) {
                            array3[i4] = reader.nextBoolean();
                        }
                        writer.putNextReference(array3, boolean[].class);
                    } else if (c == 'I') {
                        int[] array4 = new int[this.numArrayArgs];
                        for (int i5 = 0; i5 < this.numArrayArgs; i5++) {
                            array4[i5] = reader.nextInt();
                        }
                        writer.putNextReference(array4, int[].class);
                    } else if (c != 'J') {
                        switch (c) {
                            case 'B':
                                byte[] array5 = new byte[this.numArrayArgs];
                                for (int i6 = 0; i6 < this.numArrayArgs; i6++) {
                                    array5[i6] = reader.nextByte();
                                }
                                writer.putNextReference(array5, byte[].class);
                                break;
                            case 'C':
                                char[] array6 = new char[this.numArrayArgs];
                                for (int i7 = 0; i7 < this.numArrayArgs; i7++) {
                                    array6[i7] = reader.nextChar();
                                }
                                writer.putNextReference(array6, char[].class);
                                break;
                            case 'D':
                                double[] array7 = new double[this.numArrayArgs];
                                for (int i8 = 0; i8 < this.numArrayArgs; i8++) {
                                    array7[i8] = reader.nextDouble();
                                }
                                writer.putNextReference(array7, double[].class);
                                break;
                        }
                    } else {
                        long[] array8 = new long[this.numArrayArgs];
                        for (int i9 = 0; i9 < this.numArrayArgs; i9++) {
                            array8[i9] = reader.nextLong();
                        }
                        writer.putNextReference(array8, long[].class);
                    }
                    MethodHandle methodHandle = this.target;
                    targetFrame.copyReturnValueTo(callerFrame);
                }
            }

            static class FilterArguments extends Transformer {
                private final MethodHandle[] filters;
                private final int pos;
                private final MethodHandle target;

                FilterArguments(MethodHandle target2, int pos2, MethodHandle[] filters2) {
                    super(deriveType(target2, pos2, filters2));
                    this.target = target2;
                    this.pos = pos2;
                    this.filters = filters2;
                }

                private static MethodType deriveType(MethodHandle target2, int pos2, MethodHandle[] filters2) {
                    Class<?>[] filterArgs = new Class[filters2.length];
                    for (int i = 0; i < filters2.length; i++) {
                        filterArgs[i] = filters2[i].type().parameterType(0);
                    }
                    return target2.type().replaceParameterTypes(pos2, filters2.length + pos2, filterArgs);
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame stackFrame) throws Throwable {
                    MethodHandle filter;
                    EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                    reader.attach(stackFrame);
                    EmulatedStackFrame transformedFrame = EmulatedStackFrame.create(this.target.type());
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(transformedFrame);
                    Class<?>[] ptypes = this.target.type().ptypes();
                    for (int i = 0; i < ptypes.length; i++) {
                        Class<?> ptype = ptypes[i];
                        int i2 = this.pos;
                        if (i < i2) {
                            filter = null;
                        } else {
                            MethodHandle[] methodHandleArr = this.filters;
                            if (i >= methodHandleArr.length + i2) {
                                filter = null;
                            } else {
                                filter = methodHandleArr[i - i2];
                            }
                        }
                        if (filter != null) {
                            EmulatedStackFrame filterFrame = EmulatedStackFrame.create(filter.type());
                            EmulatedStackFrame.StackFrameWriter filterWriter = new EmulatedStackFrame.StackFrameWriter();
                            filterWriter.attach(filterFrame);
                            EmulatedStackFrame.StackFrameAccessor.copyNext(reader, filterWriter, filter.type().ptypes()[0]);
                            EmulatedStackFrame.StackFrameReader filterReader = new EmulatedStackFrame.StackFrameReader();
                            filterReader.attach(filterFrame);
                            filterReader.makeReturnValueAccessor();
                            EmulatedStackFrame.StackFrameAccessor.copyNext(filterReader, writer, ptype);
                        } else {
                            EmulatedStackFrame.StackFrameAccessor.copyNext(reader, writer, ptype);
                        }
                    }
                    MethodHandle methodHandle = this.target;
                    transformedFrame.copyReturnValueTo(stackFrame);
                }
            }

            static class CollectArguments extends Transformer {
                private final MethodHandle collector;
                private final EmulatedStackFrame.Range collectorRange;
                private final int pos;
                private final EmulatedStackFrame.Range range1;
                private final EmulatedStackFrame.Range range2;
                private final int referencesOffset;
                private final int stackFrameOffset;
                private final MethodHandle target;

                CollectArguments(MethodHandle target2, MethodHandle collector2, int pos2, MethodType adapterType) {
                    super(adapterType);
                    this.target = target2;
                    this.collector = collector2;
                    this.pos = pos2;
                    int numFilterArgs = collector2.type().parameterCount();
                    int numAdapterArgs = type().parameterCount();
                    this.collectorRange = EmulatedStackFrame.Range.of(type(), pos2, pos2 + numFilterArgs);
                    this.range1 = EmulatedStackFrame.Range.of(type(), 0, pos2);
                    if (pos2 + numFilterArgs < numAdapterArgs) {
                        this.range2 = EmulatedStackFrame.Range.of(type(), pos2 + numFilterArgs, numAdapterArgs);
                    } else {
                        this.range2 = null;
                    }
                    Class<?> collectorRType = collector2.type().rtype();
                    if (collectorRType == Void.TYPE) {
                        this.stackFrameOffset = 0;
                        this.referencesOffset = 0;
                    } else if (collectorRType.isPrimitive()) {
                        this.stackFrameOffset = EmulatedStackFrame.getSize(collectorRType);
                        this.referencesOffset = 0;
                    } else {
                        this.stackFrameOffset = 0;
                        this.referencesOffset = 1;
                    }
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame stackFrame) throws Throwable {
                    EmulatedStackFrame filterFrame = EmulatedStackFrame.create(this.collector.type());
                    stackFrame.copyRangeTo(filterFrame, this.collectorRange, 0, 0);
                    MethodHandle methodHandle = this.collector;
                    EmulatedStackFrame targetFrame = EmulatedStackFrame.create(this.target.type());
                    stackFrame.copyRangeTo(targetFrame, this.range1, 0, 0);
                    if (!(this.referencesOffset == 0 && this.stackFrameOffset == 0)) {
                        EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                        reader.attach(filterFrame).makeReturnValueAccessor();
                        EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                        writer.attach(targetFrame, this.pos, this.range1.numReferences, this.range1.numBytes);
                        EmulatedStackFrame.StackFrameAccessor.copyNext(reader, writer, this.target.type().ptypes()[0]);
                    }
                    EmulatedStackFrame.Range range = this.range2;
                    if (range != null) {
                        stackFrame.copyRangeTo(targetFrame, range, this.range1.numReferences + this.referencesOffset, this.range2.numBytes + this.stackFrameOffset);
                    }
                    MethodHandle methodHandle2 = this.target;
                    targetFrame.copyReturnValueTo(stackFrame);
                }
            }

            /* access modifiers changed from: package-private */
            public static class FoldArguments extends Transformer {
                private final MethodHandle combiner;
                private final EmulatedStackFrame.Range combinerArgs;
                private final int referencesOffset;
                private final int stackFrameOffset;
                private final MethodHandle target;
                private final EmulatedStackFrame.Range targetArgs = EmulatedStackFrame.Range.all(type());

                FoldArguments(MethodHandle target2, MethodHandle combiner2) {
                    super(deriveType(target2, combiner2));
                    this.target = target2;
                    this.combiner = combiner2;
                    this.combinerArgs = EmulatedStackFrame.Range.all(combiner2.type());
                    Class<?> combinerRType = combiner2.type().rtype();
                    if (combinerRType == Void.TYPE) {
                        this.stackFrameOffset = 0;
                        this.referencesOffset = 0;
                    } else if (combinerRType.isPrimitive()) {
                        this.stackFrameOffset = EmulatedStackFrame.getSize(combinerRType);
                        this.referencesOffset = 0;
                    } else {
                        this.stackFrameOffset = 0;
                        this.referencesOffset = 1;
                    }
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame stackFrame) throws Throwable {
                    EmulatedStackFrame combinerFrame = EmulatedStackFrame.create(this.combiner.type());
                    stackFrame.copyRangeTo(combinerFrame, this.combinerArgs, 0, 0);
                    MethodHandle methodHandle = this.combiner;
                    EmulatedStackFrame targetFrame = EmulatedStackFrame.create(this.target.type());
                    if (!(this.referencesOffset == 0 && this.stackFrameOffset == 0)) {
                        EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                        reader.attach(combinerFrame).makeReturnValueAccessor();
                        EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                        writer.attach(targetFrame);
                        EmulatedStackFrame.StackFrameAccessor.copyNext(reader, writer, this.target.type().ptypes()[0]);
                    }
                    stackFrame.copyRangeTo(targetFrame, this.targetArgs, this.referencesOffset, this.stackFrameOffset);
                    MethodHandle methodHandle2 = this.target;
                    targetFrame.copyReturnValueTo(stackFrame);
                }

                private static MethodType deriveType(MethodHandle target2, MethodHandle combiner2) {
                    if (combiner2.type().rtype() == Void.TYPE) {
                        return target2.type();
                    }
                    return target2.type().dropParameterTypes(0, 1);
                }
            }

            /* access modifiers changed from: package-private */
            public static class InsertArguments extends Transformer {
                private final int pos;
                private final EmulatedStackFrame.Range range1;
                private final EmulatedStackFrame.Range range2;
                private final MethodHandle target;
                private final Object[] values;

                InsertArguments(MethodHandle target2, int pos2, Object[] values2) {
                    super(target2.type().dropParameterTypes(pos2, values2.length + pos2));
                    this.target = target2;
                    this.pos = pos2;
                    this.values = values2;
                    MethodType type = type();
                    this.range1 = EmulatedStackFrame.Range.of(type, 0, pos2);
                    this.range2 = EmulatedStackFrame.Range.of(type, pos2, type.parameterCount());
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame stackFrame) throws Throwable {
                    EmulatedStackFrame calleeFrame = EmulatedStackFrame.create(this.target.type());
                    stackFrame.copyRangeTo(calleeFrame, this.range1, 0, 0);
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(calleeFrame, this.pos, this.range1.numReferences, this.range1.numBytes);
                    int referencesCopied = 0;
                    int bytesCopied = 0;
                    Class<?>[] ptypes = this.target.type().ptypes();
                    for (int i = 0; i < this.values.length; i++) {
                        Class<?> ptype = ptypes[this.pos + i];
                        if (ptype.isPrimitive()) {
                            if (ptype == Boolean.TYPE) {
                                writer.putNextBoolean(((Boolean) this.values[i]).booleanValue());
                            } else if (ptype == Byte.TYPE) {
                                writer.putNextByte(((Byte) this.values[i]).byteValue());
                            } else if (ptype == Character.TYPE) {
                                writer.putNextChar(((Character) this.values[i]).charValue());
                            } else if (ptype == Short.TYPE) {
                                writer.putNextShort(((Short) this.values[i]).shortValue());
                            } else if (ptype == Integer.TYPE) {
                                writer.putNextInt(((Integer) this.values[i]).intValue());
                            } else if (ptype == Long.TYPE) {
                                writer.putNextLong(((Long) this.values[i]).longValue());
                            } else if (ptype == Float.TYPE) {
                                writer.putNextFloat(((Float) this.values[i]).floatValue());
                            } else if (ptype == Double.TYPE) {
                                writer.putNextDouble(((Double) this.values[i]).doubleValue());
                            }
                            bytesCopied += EmulatedStackFrame.getSize(ptype);
                        } else {
                            writer.putNextReference(this.values[i], ptype);
                            referencesCopied++;
                        }
                    }
                    EmulatedStackFrame.Range range = this.range2;
                    if (range != null) {
                        stackFrame.copyRangeTo(calleeFrame, range, this.range1.numReferences + referencesCopied, this.range1.numBytes + bytesCopied);
                    }
                    MethodHandle methodHandle = this.target;
                    calleeFrame.copyReturnValueTo(stackFrame);
                }
            }

            public static class ExplicitCastArguments extends Transformer {
                private final MethodHandle target;

                public ExplicitCastArguments(MethodHandle target2, MethodType type) {
                    super(type);
                    this.target = target2;
                }

                @Override // java.lang.invoke.MethodHandle
                public void transform(EmulatedStackFrame callerFrame) throws Throwable {
                    EmulatedStackFrame targetFrame = EmulatedStackFrame.create(this.target.type());
                    explicitCastArguments(callerFrame, targetFrame);
                    MethodHandle methodHandle = this.target;
                    explicitCastReturnValue(callerFrame, targetFrame);
                }

                private void explicitCastArguments(EmulatedStackFrame callerFrame, EmulatedStackFrame targetFrame) {
                    EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                    reader.attach(callerFrame);
                    EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                    writer.attach(targetFrame);
                    Class<?>[] fromTypes = type().ptypes();
                    Class<?>[] toTypes = this.target.type().ptypes();
                    for (int i = 0; i < fromTypes.length; i++) {
                        explicitCast(reader, fromTypes[i], writer, toTypes[i]);
                    }
                }

                private void explicitCastReturnValue(EmulatedStackFrame callerFrame, EmulatedStackFrame targetFrame) {
                    Class<?> from = this.target.type().rtype();
                    Class<?> to = type().rtype();
                    if (to != Void.TYPE) {
                        EmulatedStackFrame.StackFrameWriter writer = new EmulatedStackFrame.StackFrameWriter();
                        writer.attach(callerFrame);
                        writer.makeReturnValueAccessor();
                        if (from != Void.TYPE) {
                            EmulatedStackFrame.StackFrameReader reader = new EmulatedStackFrame.StackFrameReader();
                            reader.attach(targetFrame);
                            reader.makeReturnValueAccessor();
                            explicitCast(reader, this.target.type().rtype(), writer, type().rtype());
                        } else if (to.isPrimitive()) {
                            unboxNull(writer, to);
                        } else {
                            writer.putNextReference(null, to);
                        }
                    }
                }

                private static void throwUnexpectedType(Class<?> unexpectedType) {
                    throw new InternalError("Unexpected type: " + ((Object) unexpectedType));
                }

                /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: boolean */
                /* JADX WARN: Multi-variable type inference failed */
                private static void explicitCastFromBoolean(boolean fromValue, EmulatedStackFrame.StackFrameWriter writer, Class<?> to) {
                    if (to == Byte.TYPE) {
                        writer.putNextByte(fromValue ? (byte) 1 : 0);
                    } else if (to == Character.TYPE) {
                        writer.putNextChar(fromValue ? (char) 1 : 0);
                    } else if (to == Short.TYPE) {
                        writer.putNextShort(fromValue ? (short) 1 : 0);
                    } else if (to == Integer.TYPE) {
                        writer.putNextInt(fromValue);
                    } else if (to == Long.TYPE) {
                        writer.putNextLong(fromValue ? 1 : 0);
                    } else if (to == Float.TYPE) {
                        writer.putNextFloat(fromValue ? 1.0f : 0.0f);
                    } else if (to == Double.TYPE) {
                        writer.putNextDouble(fromValue ? 1.0d : 0.0d);
                    } else {
                        throwUnexpectedType(to);
                    }
                }

                private static boolean toBoolean(byte value) {
                    return (value & 1) == 1;
                }

                private static byte readPrimitiveAsByte(EmulatedStackFrame.StackFrameReader reader, Class<?> from) {
                    if (from == Byte.TYPE) {
                        return reader.nextByte();
                    }
                    if (from == Character.TYPE) {
                        return (byte) reader.nextChar();
                    }
                    if (from == Short.TYPE) {
                        return (byte) reader.nextShort();
                    }
                    if (from == Integer.TYPE) {
                        return (byte) reader.nextInt();
                    }
                    if (from == Long.TYPE) {
                        return (byte) ((int) reader.nextLong());
                    }
                    if (from == Float.TYPE) {
                        return (byte) ((int) reader.nextFloat());
                    }
                    if (from == Double.TYPE) {
                        return (byte) ((int) reader.nextDouble());
                    }
                    throwUnexpectedType(from);
                    return 0;
                }

                private static char readPrimitiveAsChar(EmulatedStackFrame.StackFrameReader reader, Class<?> from) {
                    if (from == Byte.TYPE) {
                        return (char) reader.nextByte();
                    }
                    if (from == Character.TYPE) {
                        return reader.nextChar();
                    }
                    if (from == Short.TYPE) {
                        return (char) reader.nextShort();
                    }
                    if (from == Integer.TYPE) {
                        return (char) reader.nextInt();
                    }
                    if (from == Long.TYPE) {
                        return (char) ((int) reader.nextLong());
                    }
                    if (from == Float.TYPE) {
                        return (char) ((int) reader.nextFloat());
                    }
                    if (from == Double.TYPE) {
                        return (char) ((int) reader.nextDouble());
                    }
                    throwUnexpectedType(from);
                    return 0;
                }

                private static short readPrimitiveAsShort(EmulatedStackFrame.StackFrameReader reader, Class<?> from) {
                    if (from == Byte.TYPE) {
                        return (short) reader.nextByte();
                    }
                    if (from == Character.TYPE) {
                        return (short) reader.nextChar();
                    }
                    if (from == Short.TYPE) {
                        return reader.nextShort();
                    }
                    if (from == Integer.TYPE) {
                        return (short) reader.nextInt();
                    }
                    if (from == Long.TYPE) {
                        return (short) ((int) reader.nextLong());
                    }
                    if (from == Float.TYPE) {
                        return (short) ((int) reader.nextFloat());
                    }
                    if (from == Double.TYPE) {
                        return (short) ((int) reader.nextDouble());
                    }
                    throwUnexpectedType(from);
                    return 0;
                }

                private static int readPrimitiveAsInt(EmulatedStackFrame.StackFrameReader reader, Class<?> from) {
                    if (from == Byte.TYPE) {
                        return reader.nextByte();
                    }
                    if (from == Character.TYPE) {
                        return reader.nextChar();
                    }
                    if (from == Short.TYPE) {
                        return reader.nextShort();
                    }
                    if (from == Integer.TYPE) {
                        return reader.nextInt();
                    }
                    if (from == Long.TYPE) {
                        return (int) reader.nextLong();
                    }
                    if (from == Float.TYPE) {
                        return (int) reader.nextFloat();
                    }
                    if (from == Double.TYPE) {
                        return (int) reader.nextDouble();
                    }
                    throwUnexpectedType(from);
                    return 0;
                }

                private static long readPrimitiveAsLong(EmulatedStackFrame.StackFrameReader reader, Class<?> from) {
                    if (from == Byte.TYPE) {
                        return (long) reader.nextByte();
                    }
                    if (from == Character.TYPE) {
                        return (long) reader.nextChar();
                    }
                    if (from == Short.TYPE) {
                        return (long) reader.nextShort();
                    }
                    if (from == Integer.TYPE) {
                        return (long) reader.nextInt();
                    }
                    if (from == Long.TYPE) {
                        return reader.nextLong();
                    }
                    if (from == Float.TYPE) {
                        return (long) reader.nextFloat();
                    }
                    if (from == Double.TYPE) {
                        return (long) reader.nextDouble();
                    }
                    throwUnexpectedType(from);
                    return 0;
                }

                private static float readPrimitiveAsFloat(EmulatedStackFrame.StackFrameReader reader, Class<?> from) {
                    if (from == Byte.TYPE) {
                        return (float) reader.nextByte();
                    }
                    if (from == Character.TYPE) {
                        return (float) reader.nextChar();
                    }
                    if (from == Short.TYPE) {
                        return (float) reader.nextShort();
                    }
                    if (from == Integer.TYPE) {
                        return (float) reader.nextInt();
                    }
                    if (from == Long.TYPE) {
                        return (float) reader.nextLong();
                    }
                    if (from == Float.TYPE) {
                        return reader.nextFloat();
                    }
                    if (from == Double.TYPE) {
                        return (float) reader.nextDouble();
                    }
                    throwUnexpectedType(from);
                    return 0.0f;
                }

                private static double readPrimitiveAsDouble(EmulatedStackFrame.StackFrameReader reader, Class<?> from) {
                    if (from == Byte.TYPE) {
                        return (double) reader.nextByte();
                    }
                    if (from == Character.TYPE) {
                        return (double) reader.nextChar();
                    }
                    if (from == Short.TYPE) {
                        return (double) reader.nextShort();
                    }
                    if (from == Integer.TYPE) {
                        return (double) reader.nextInt();
                    }
                    if (from == Long.TYPE) {
                        return (double) reader.nextLong();
                    }
                    if (from == Float.TYPE) {
                        return (double) reader.nextFloat();
                    }
                    if (from == Double.TYPE) {
                        return reader.nextDouble();
                    }
                    throwUnexpectedType(from);
                    return 0.0d;
                }

                private static void explicitCastToBoolean(EmulatedStackFrame.StackFrameReader reader, Class<?> from, EmulatedStackFrame.StackFrameWriter writer) {
                    writer.putNextBoolean(toBoolean(readPrimitiveAsByte(reader, from)));
                }

                private static void explicitCastPrimitives(EmulatedStackFrame.StackFrameReader reader, Class<?> from, EmulatedStackFrame.StackFrameWriter writer, Class<?> to) {
                    if (to == Byte.TYPE) {
                        writer.putNextByte(readPrimitiveAsByte(reader, from));
                    } else if (to == Character.TYPE) {
                        writer.putNextChar(readPrimitiveAsChar(reader, from));
                    } else if (to == Short.TYPE) {
                        writer.putNextShort(readPrimitiveAsShort(reader, from));
                    } else if (to == Integer.TYPE) {
                        writer.putNextInt(readPrimitiveAsInt(reader, from));
                    } else if (to == Long.TYPE) {
                        writer.putNextLong(readPrimitiveAsLong(reader, from));
                    } else if (to == Float.TYPE) {
                        writer.putNextFloat(readPrimitiveAsFloat(reader, from));
                    } else if (to == Double.TYPE) {
                        writer.putNextDouble(readPrimitiveAsDouble(reader, from));
                    } else {
                        throwUnexpectedType(to);
                    }
                }

                private static void unboxNull(EmulatedStackFrame.StackFrameWriter writer, Class<?> to) {
                    if (to == Boolean.TYPE) {
                        writer.putNextBoolean(false);
                    } else if (to == Byte.TYPE) {
                        writer.putNextByte((byte) 0);
                    } else if (to == Character.TYPE) {
                        writer.putNextChar(0);
                    } else if (to == Short.TYPE) {
                        writer.putNextShort(0);
                    } else if (to == Integer.TYPE) {
                        writer.putNextInt(0);
                    } else if (to == Long.TYPE) {
                        writer.putNextLong(0);
                    } else if (to == Float.TYPE) {
                        writer.putNextFloat(0.0f);
                    } else if (to == Double.TYPE) {
                        writer.putNextDouble(0.0d);
                    } else {
                        throwUnexpectedType(to);
                    }
                }

                private static void unboxNonNull(Object ref, Class<?> from, EmulatedStackFrame.StackFrameWriter writer, Class<?> to) {
                    if (to == Boolean.TYPE) {
                        if (from == Boolean.class) {
                            writer.putNextBoolean(((Boolean) ref).booleanValue());
                        } else if (from == Float.class || from == Double.class) {
                            writer.putNextBoolean(toBoolean((byte) ((int) ((Double) ref).doubleValue())));
                        } else {
                            writer.putNextBoolean(toBoolean((byte) ((int) ((Long) ref).longValue())));
                        }
                    } else if (to == Byte.TYPE) {
                        writer.putNextByte(((Byte) ref).byteValue());
                    } else if (to == Character.TYPE) {
                        writer.putNextChar(((Character) ref).charValue());
                    } else if (to == Short.TYPE) {
                        writer.putNextShort(((Short) ref).shortValue());
                    } else if (to == Integer.TYPE) {
                        writer.putNextInt(((Integer) ref).intValue());
                    } else if (to == Long.TYPE) {
                        writer.putNextLong(((Long) ref).longValue());
                    } else if (to == Float.TYPE) {
                        writer.putNextFloat(((Float) ref).floatValue());
                    } else if (to == Double.TYPE) {
                        writer.putNextDouble(((Double) ref).doubleValue());
                    } else {
                        throwUnexpectedType(to);
                    }
                }

                private static void unbox(Object ref, Class<?> from, EmulatedStackFrame.StackFrameWriter writer, Class<?> to) {
                    if (ref == null) {
                        unboxNull(writer, to);
                    } else {
                        unboxNonNull(ref, from, writer, to);
                    }
                }

                private static void box(EmulatedStackFrame.StackFrameReader reader, Class<?> from, EmulatedStackFrame.StackFrameWriter writer, Class<?> to) {
                    Object boxed = null;
                    if (from == Boolean.TYPE) {
                        boxed = Boolean.valueOf(reader.nextBoolean());
                    } else if (from == Byte.TYPE) {
                        boxed = Byte.valueOf(reader.nextByte());
                    } else if (from == Character.TYPE) {
                        boxed = Character.valueOf(reader.nextChar());
                    } else if (from == Short.TYPE) {
                        boxed = Short.valueOf(reader.nextShort());
                    } else if (from == Integer.TYPE) {
                        boxed = Integer.valueOf(reader.nextInt());
                    } else if (from == Long.TYPE) {
                        boxed = Long.valueOf(reader.nextLong());
                    } else if (from == Float.TYPE) {
                        boxed = Float.valueOf(reader.nextFloat());
                    } else if (from == Double.TYPE) {
                        boxed = Double.valueOf(reader.nextDouble());
                    } else {
                        throwUnexpectedType(from);
                    }
                    writer.putNextReference(to.cast(boxed), to);
                }

                private static void explicitCast(EmulatedStackFrame.StackFrameReader reader, Class<?> from, EmulatedStackFrame.StackFrameWriter writer, Class<?> to) {
                    if (from.equals(to)) {
                        EmulatedStackFrame.StackFrameAccessor.copyNext(reader, writer, from);
                    } else if (!from.isPrimitive()) {
                        Object ref = reader.nextReference(from);
                        if (to.isInterface()) {
                            writer.putNextReference(ref, to);
                        } else if (!to.isPrimitive()) {
                            writer.putNextReference(to.cast(ref), to);
                        } else {
                            unbox(ref, from, writer, to);
                        }
                    } else if (!to.isPrimitive()) {
                        box(reader, from, writer, to);
                    } else if (from == Boolean.TYPE) {
                        explicitCastFromBoolean(reader.nextBoolean(), writer, to);
                    } else if (to == Boolean.TYPE) {
                        explicitCastToBoolean(reader, from, writer);
                    } else {
                        explicitCastPrimitives(reader, from, writer, to);
                    }
                }
            }
        }
