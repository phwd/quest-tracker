package java.io;

import dalvik.system.VMStack;
import java.io.ObjectStreamClass;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.reflect.misc.ReflectUtil;

public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants {
    private static final int NULL_HANDLE = -1;
    private static final HashMap<String, Class<?>> primClasses = new HashMap<>(8, 1.0f);
    private static final Object unsharedMarker = new Object();
    private final BlockDataInputStream bin;
    private boolean closed;
    private SerialCallbackContext curContext;
    private boolean defaultDataEnd = false;
    private int depth;
    private final boolean enableOverride;
    private boolean enableResolve;
    private final HandleTable handles;
    private int passHandle = -1;
    private byte[] primVals;
    private final ValidationList vlist;

    public static abstract class GetField {
        public abstract boolean defaulted(String str) throws IOException;

        public abstract byte get(String str, byte b) throws IOException;

        public abstract char get(String str, char c) throws IOException;

        public abstract double get(String str, double d) throws IOException;

        public abstract float get(String str, float f) throws IOException;

        public abstract int get(String str, int i) throws IOException;

        public abstract long get(String str, long j) throws IOException;

        public abstract Object get(String str, Object obj) throws IOException;

        public abstract short get(String str, short s) throws IOException;

        public abstract boolean get(String str, boolean z) throws IOException;

        public abstract ObjectStreamClass getObjectStreamClass();
    }

    /* access modifiers changed from: private */
    public static native void bytesToDoubles(byte[] bArr, int i, double[] dArr, int i2, int i3);

    /* access modifiers changed from: private */
    public static native void bytesToFloats(byte[] bArr, int i, float[] fArr, int i2, int i3);

    static {
        primClasses.put("boolean", Boolean.TYPE);
        primClasses.put("byte", Byte.TYPE);
        primClasses.put("char", Character.TYPE);
        primClasses.put("short", Short.TYPE);
        primClasses.put("int", Integer.TYPE);
        primClasses.put("long", Long.TYPE);
        primClasses.put("float", Float.TYPE);
        primClasses.put("double", Double.TYPE);
        primClasses.put("void", Void.TYPE);
    }

    /* access modifiers changed from: private */
    public static class Caches {
        static final ConcurrentMap<ObjectStreamClass.WeakClassKey, Boolean> subclassAudits = new ConcurrentHashMap();
        static final ReferenceQueue<Class<?>> subclassAuditsQueue = new ReferenceQueue<>();

        private Caches() {
        }
    }

    public ObjectInputStream(InputStream in) throws IOException {
        verifySubclass();
        this.bin = new BlockDataInputStream(in);
        this.handles = new HandleTable(10);
        this.vlist = new ValidationList();
        this.enableOverride = false;
        readStreamHeader();
        this.bin.setBlockDataMode(true);
    }

    protected ObjectInputStream() throws IOException, SecurityException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
        }
        this.bin = null;
        this.handles = null;
        this.vlist = null;
        this.enableOverride = true;
    }

    @Override // java.io.ObjectInput
    public final Object readObject() throws IOException, ClassNotFoundException {
        if (this.enableOverride) {
            return readObjectOverride();
        }
        int outerHandle = this.passHandle;
        try {
            Object obj = readObject0(false);
            this.handles.markDependency(outerHandle, this.passHandle);
            ClassNotFoundException ex = this.handles.lookupException(this.passHandle);
            if (ex == null) {
                if (this.depth == 0) {
                    this.vlist.doCallbacks();
                }
                return obj;
            }
            throw ex;
        } finally {
            this.passHandle = outerHandle;
            if (this.closed && this.depth == 0) {
                clear();
            }
        }
    }

    /* access modifiers changed from: protected */
    public Object readObjectOverride() throws IOException, ClassNotFoundException {
        return null;
    }

    public Object readUnshared() throws IOException, ClassNotFoundException {
        int outerHandle = this.passHandle;
        try {
            Object obj = readObject0(true);
            this.handles.markDependency(outerHandle, this.passHandle);
            ClassNotFoundException ex = this.handles.lookupException(this.passHandle);
            if (ex == null) {
                if (this.depth == 0) {
                    this.vlist.doCallbacks();
                }
                return obj;
            }
            throw ex;
        } finally {
            this.passHandle = outerHandle;
            if (this.closed && this.depth == 0) {
                clear();
            }
        }
    }

    public void defaultReadObject() throws IOException, ClassNotFoundException {
        SerialCallbackContext ctx = this.curContext;
        if (ctx != null) {
            Object curObj = ctx.getObj();
            ObjectStreamClass curDesc = ctx.getDesc();
            this.bin.setBlockDataMode(false);
            defaultReadFields(curObj, curDesc);
            this.bin.setBlockDataMode(true);
            if (!curDesc.hasWriteObjectData()) {
                this.defaultDataEnd = true;
            }
            ClassNotFoundException ex = this.handles.lookupException(this.passHandle);
            if (ex != null) {
                throw ex;
            }
            return;
        }
        throw new NotActiveException("not in call to readObject");
    }

    public GetField readFields() throws IOException, ClassNotFoundException {
        SerialCallbackContext ctx = this.curContext;
        if (ctx != null) {
            ctx.getObj();
            ObjectStreamClass curDesc = ctx.getDesc();
            this.bin.setBlockDataMode(false);
            GetFieldImpl getField = new GetFieldImpl(curDesc);
            getField.readFields();
            this.bin.setBlockDataMode(true);
            if (!curDesc.hasWriteObjectData()) {
                this.defaultDataEnd = true;
            }
            return getField;
        }
        throw new NotActiveException("not in call to readObject");
    }

    public void registerValidation(ObjectInputValidation obj, int prio) throws NotActiveException, InvalidObjectException {
        if (this.depth != 0) {
            this.vlist.register(obj, prio);
            return;
        }
        throw new NotActiveException("stream inactive");
    }

    /* access modifiers changed from: protected */
    public Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        String name = desc.getName();
        try {
            return Class.forName(name, false, latestUserDefinedLoader());
        } catch (ClassNotFoundException ex) {
            Class<?> cl = primClasses.get(name);
            if (cl != null) {
                return cl;
            }
            throw ex;
        }
    }

    /* access modifiers changed from: protected */
    public Class<?> resolveProxyClass(String[] interfaces) throws IOException, ClassNotFoundException {
        ClassLoader latestLoader = latestUserDefinedLoader();
        ClassLoader nonPublicLoader = null;
        boolean hasNonPublicInterface = false;
        Class<?>[] classObjs = new Class[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            Class<?> cl = Class.forName(interfaces[i], false, latestLoader);
            if ((cl.getModifiers() & 1) == 0) {
                if (!hasNonPublicInterface) {
                    nonPublicLoader = cl.getClassLoader();
                    hasNonPublicInterface = true;
                } else if (nonPublicLoader != cl.getClassLoader()) {
                    throw new IllegalAccessError("conflicting non-public interface class loaders");
                }
            }
            classObjs[i] = cl;
        }
        try {
            return Proxy.getProxyClass(hasNonPublicInterface ? nonPublicLoader : latestLoader, classObjs);
        } catch (IllegalArgumentException e) {
            throw new ClassNotFoundException(null, e);
        }
    }

    /* access modifiers changed from: protected */
    public Object resolveObject(Object obj) throws IOException {
        return obj;
    }

    /* access modifiers changed from: protected */
    public boolean enableResolveObject(boolean enable) throws SecurityException {
        SecurityManager sm;
        if (enable == this.enableResolve) {
            return enable;
        }
        if (enable && (sm = System.getSecurityManager()) != null) {
            sm.checkPermission(SUBSTITUTION_PERMISSION);
        }
        this.enableResolve = enable;
        return !this.enableResolve;
    }

    /* access modifiers changed from: protected */
    public void readStreamHeader() throws IOException, StreamCorruptedException {
        short s0 = this.bin.readShort();
        short s1 = this.bin.readShort();
        if (s0 != -21267 || s1 != 5) {
            throw new StreamCorruptedException(String.format("invalid stream header: %04X%04X", Short.valueOf(s0), Short.valueOf(s1)));
        }
    }

    /* access modifiers changed from: protected */
    public ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass desc = new ObjectStreamClass();
        desc.readNonProxy(this);
        return desc;
    }

    @Override // java.io.ObjectInput, java.io.InputStream
    public int read() throws IOException {
        return this.bin.read();
    }

    @Override // java.io.ObjectInput, java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        if (buf != null) {
            int endoff = off + len;
            if (off >= 0 && len >= 0 && endoff <= buf.length && endoff >= 0) {
                return this.bin.read(buf, off, len, false);
            }
            throw new IndexOutOfBoundsException();
        }
        throw new NullPointerException();
    }

    @Override // java.io.ObjectInput, java.io.InputStream
    public int available() throws IOException {
        return this.bin.available();
    }

    @Override // java.io.Closeable, java.io.ObjectInput, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        this.closed = true;
        if (this.depth == 0) {
            clear();
        }
        this.bin.close();
    }

    @Override // java.io.DataInput
    public boolean readBoolean() throws IOException {
        return this.bin.readBoolean();
    }

    @Override // java.io.DataInput
    public byte readByte() throws IOException {
        return this.bin.readByte();
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() throws IOException {
        return this.bin.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public char readChar() throws IOException {
        return this.bin.readChar();
    }

    @Override // java.io.DataInput
    public short readShort() throws IOException {
        return this.bin.readShort();
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() throws IOException {
        return this.bin.readUnsignedShort();
    }

    @Override // java.io.DataInput
    public int readInt() throws IOException {
        return this.bin.readInt();
    }

    @Override // java.io.DataInput
    public long readLong() throws IOException {
        return this.bin.readLong();
    }

    @Override // java.io.DataInput
    public float readFloat() throws IOException {
        return this.bin.readFloat();
    }

    @Override // java.io.DataInput
    public double readDouble() throws IOException {
        return this.bin.readDouble();
    }

    @Override // java.io.DataInput
    public void readFully(byte[] buf) throws IOException {
        this.bin.readFully(buf, 0, buf.length, false);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] buf, int off, int len) throws IOException {
        int endoff = off + len;
        if (off < 0 || len < 0 || endoff > buf.length || endoff < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.bin.readFully(buf, off, len, false);
    }

    @Override // java.io.DataInput
    public int skipBytes(int len) throws IOException {
        return this.bin.skipBytes(len);
    }

    @Override // java.io.DataInput
    @Deprecated
    public String readLine() throws IOException {
        return this.bin.readLine();
    }

    @Override // java.io.DataInput
    public String readUTF() throws IOException {
        return this.bin.readUTF();
    }

    private void verifySubclass() {
        SecurityManager sm;
        Class<?> cl = getClass();
        if (cl != ObjectInputStream.class && (sm = System.getSecurityManager()) != null) {
            ObjectStreamClass.processQueue(Caches.subclassAuditsQueue, Caches.subclassAudits);
            ObjectStreamClass.WeakClassKey key = new ObjectStreamClass.WeakClassKey(cl, Caches.subclassAuditsQueue);
            Boolean result = Caches.subclassAudits.get(key);
            if (result == null) {
                result = Boolean.valueOf(auditSubclass(cl));
                Caches.subclassAudits.putIfAbsent(key, result);
            }
            if (!result.booleanValue()) {
                sm.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }
    }

    private static boolean auditSubclass(final Class<?> subcl) {
        return ((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
            /* class java.io.ObjectInputStream.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Boolean run() {
                for (Class<?> cl = Class.this; cl != ObjectInputStream.class; cl = cl.getSuperclass()) {
                    try {
                        cl.getDeclaredMethod("readUnshared", null);
                        return Boolean.FALSE;
                    } catch (NoSuchMethodException e) {
                        try {
                            cl.getDeclaredMethod("readFields", null);
                            return Boolean.FALSE;
                        } catch (NoSuchMethodException e2) {
                        }
                    }
                }
                return Boolean.TRUE;
            }
        })).booleanValue();
    }

    private void clear() {
        this.handles.clear();
        this.vlist.clear();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Object readObject0(boolean unshared) throws IOException {
        byte tc;
        boolean oldMode = this.bin.getBlockDataMode();
        if (oldMode) {
            int remain = this.bin.currentBlockRemaining();
            if (remain > 0) {
                throw new OptionalDataException(remain);
            } else if (!this.defaultDataEnd) {
                this.bin.setBlockDataMode(false);
            } else {
                throw new OptionalDataException(true);
            }
        }
        while (true) {
            tc = this.bin.peekByte();
            if (tc != 121) {
                break;
            }
            this.bin.readByte();
            handleReset();
        }
        this.depth++;
        switch (tc) {
            case 112:
                Object readNull = readNull();
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return readNull;
            case 113:
                Object readHandle = readHandle(unshared);
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return readHandle;
            case 114:
            case 125:
                ObjectStreamClass readClassDesc = readClassDesc(unshared);
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return readClassDesc;
            case 115:
                Object checkResolve = checkResolve(readOrdinaryObject(unshared));
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return checkResolve;
            case 116:
            case 124:
                Object checkResolve2 = checkResolve(readString(unshared));
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return checkResolve2;
            case 117:
                Object checkResolve3 = checkResolve(readArray(unshared));
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return checkResolve3;
            case 118:
                Class<?> readClass = readClass(unshared);
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return readClass;
            case 119:
            case 122:
                if (oldMode) {
                    this.bin.setBlockDataMode(true);
                    this.bin.peek();
                    throw new OptionalDataException(this.bin.currentBlockRemaining());
                }
                throw new StreamCorruptedException("unexpected block data");
            case 120:
                if (oldMode) {
                    throw new OptionalDataException(true);
                }
                throw new StreamCorruptedException("unexpected end of block data");
            case 121:
            default:
                try {
                    throw new StreamCorruptedException(String.format("invalid type code: %02X", Byte.valueOf(tc)));
                } catch (Throwable th) {
                    this.depth--;
                    this.bin.setBlockDataMode(oldMode);
                    throw th;
                }
            case 123:
                throw new WriteAbortedException("writing aborted", readFatalException());
            case 126:
                Object checkResolve4 = checkResolve(readEnum(unshared));
                this.depth--;
                this.bin.setBlockDataMode(oldMode);
                return checkResolve4;
        }
    }

    private Object checkResolve(Object obj) throws IOException {
        if (!this.enableResolve || this.handles.lookupException(this.passHandle) != null) {
            return obj;
        }
        Object rep = resolveObject(obj);
        if (rep != obj) {
            this.handles.setObject(this.passHandle, rep);
        }
        return rep;
    }

    /* access modifiers changed from: package-private */
    public String readTypeString() throws IOException {
        int oldHandle = this.passHandle;
        try {
            byte tc = this.bin.peekByte();
            if (tc == 112) {
                String str = (String) readNull();
                this.passHandle = oldHandle;
                return str;
            } else if (tc != 113) {
                if (tc != 116) {
                    if (tc != 124) {
                        throw new StreamCorruptedException(String.format("invalid type code: %02X", Byte.valueOf(tc)));
                    }
                }
                return readString(false);
            } else {
                String str2 = (String) readHandle(false);
                this.passHandle = oldHandle;
                return str2;
            }
        } finally {
            this.passHandle = oldHandle;
        }
    }

    private Object readNull() throws IOException {
        if (this.bin.readByte() == 112) {
            this.passHandle = -1;
            return null;
        }
        throw new InternalError();
    }

    private Object readHandle(boolean unshared) throws IOException {
        if (this.bin.readByte() == 113) {
            this.passHandle = this.bin.readInt() - ObjectStreamConstants.baseWireHandle;
            int i = this.passHandle;
            if (i < 0 || i >= this.handles.size()) {
                throw new StreamCorruptedException(String.format("invalid handle value: %08X", Integer.valueOf(this.passHandle + ObjectStreamConstants.baseWireHandle)));
            } else if (!unshared) {
                Object obj = this.handles.lookupObject(this.passHandle);
                if (obj != unsharedMarker) {
                    return obj;
                }
                throw new InvalidObjectException("cannot read back reference to unshared object");
            } else {
                throw new InvalidObjectException("cannot read back reference as unshared");
            }
        } else {
            throw new InternalError();
        }
    }

    private Class<?> readClass(boolean unshared) throws IOException {
        if (this.bin.readByte() == 118) {
            ObjectStreamClass desc = readClassDesc(false);
            Class<?> cl = desc.forClass();
            this.passHandle = this.handles.assign(unshared ? unsharedMarker : cl);
            ClassNotFoundException resolveEx = desc.getResolveException();
            if (resolveEx != null) {
                this.handles.markException(this.passHandle, resolveEx);
            }
            this.handles.finish(this.passHandle);
            return cl;
        }
        throw new InternalError();
    }

    private ObjectStreamClass readClassDesc(boolean unshared) throws IOException {
        byte tc = this.bin.peekByte();
        if (tc == 125) {
            return readProxyDesc(unshared);
        }
        switch (tc) {
            case 112:
                return (ObjectStreamClass) readNull();
            case 113:
                return (ObjectStreamClass) readHandle(unshared);
            case 114:
                return readNonProxyDesc(unshared);
            default:
                throw new StreamCorruptedException(String.format("invalid type code: %02X", Byte.valueOf(tc)));
        }
    }

    private boolean isCustomSubclass() {
        return getClass().getClassLoader() != ObjectInputStream.class.getClassLoader();
    }

    private ObjectStreamClass readProxyDesc(boolean unshared) throws IOException {
        if (this.bin.readByte() == 125) {
            ObjectStreamClass desc = new ObjectStreamClass();
            int descHandle = this.handles.assign(unshared ? unsharedMarker : desc);
            this.passHandle = -1;
            int numIfaces = this.bin.readInt();
            String[] ifaces = new String[numIfaces];
            for (int i = 0; i < numIfaces; i++) {
                ifaces[i] = this.bin.readUTF();
            }
            Class<?> cl = null;
            ClassNotFoundException resolveEx = null;
            this.bin.setBlockDataMode(true);
            try {
                Class<?> resolveProxyClass = resolveProxyClass(ifaces);
                cl = resolveProxyClass;
                if (resolveProxyClass == null) {
                    resolveEx = new ClassNotFoundException("null class");
                } else if (Proxy.isProxyClass(cl)) {
                    ReflectUtil.checkProxyPackageAccess(getClass().getClassLoader(), cl.getInterfaces());
                } else {
                    throw new InvalidClassException("Not a proxy");
                }
            } catch (ClassNotFoundException ex) {
                resolveEx = ex;
            }
            skipCustomData();
            desc.initProxy(cl, resolveEx, readClassDesc(false));
            this.handles.finish(descHandle);
            this.passHandle = descHandle;
            return desc;
        }
        throw new InternalError();
    }

    private ObjectStreamClass readNonProxyDesc(boolean unshared) throws IOException {
        if (this.bin.readByte() == 114) {
            ObjectStreamClass desc = new ObjectStreamClass();
            int descHandle = this.handles.assign(unshared ? unsharedMarker : desc);
            this.passHandle = -1;
            try {
                ObjectStreamClass readDesc = readClassDescriptor();
                Class<?> cl = null;
                ClassNotFoundException resolveEx = null;
                this.bin.setBlockDataMode(true);
                boolean checksRequired = isCustomSubclass();
                try {
                    Class<?> resolveClass = resolveClass(readDesc);
                    cl = resolveClass;
                    if (resolveClass == null) {
                        resolveEx = new ClassNotFoundException("null class");
                    } else if (checksRequired) {
                        ReflectUtil.checkPackageAccess(cl);
                    }
                } catch (ClassNotFoundException ex) {
                    resolveEx = ex;
                }
                skipCustomData();
                desc.initNonProxy(readDesc, cl, resolveEx, readClassDesc(false));
                this.handles.finish(descHandle);
                this.passHandle = descHandle;
                return desc;
            } catch (ClassNotFoundException ex2) {
                throw ((IOException) new InvalidClassException("failed to read class descriptor").initCause(ex2));
            }
        } else {
            throw new InternalError();
        }
    }

    private String readString(boolean unshared) throws IOException {
        String str;
        byte tc = this.bin.readByte();
        if (tc == 116) {
            str = this.bin.readUTF();
        } else if (tc == 124) {
            str = this.bin.readLongUTF();
        } else {
            throw new StreamCorruptedException(String.format("invalid type code: %02X", Byte.valueOf(tc)));
        }
        this.passHandle = this.handles.assign(unshared ? unsharedMarker : str);
        this.handles.finish(this.passHandle);
        return str;
    }

    private Object readArray(boolean unshared) throws IOException {
        if (this.bin.readByte() == 117) {
            ObjectStreamClass desc = readClassDesc(false);
            int len = this.bin.readInt();
            Object array = null;
            Class<?> ccl = null;
            Class<?> cl = desc.forClass();
            if (cl != null) {
                ccl = cl.getComponentType();
                array = Array.newInstance(ccl, len);
            }
            int arrayHandle = this.handles.assign(unshared ? unsharedMarker : array);
            ClassNotFoundException resolveEx = desc.getResolveException();
            if (resolveEx != null) {
                this.handles.markException(arrayHandle, resolveEx);
            }
            if (ccl == null) {
                for (int i = 0; i < len; i++) {
                    readObject0(false);
                }
            } else if (!ccl.isPrimitive()) {
                Object[] oa = (Object[]) array;
                for (int i2 = 0; i2 < len; i2++) {
                    oa[i2] = readObject0(false);
                    this.handles.markDependency(arrayHandle, this.passHandle);
                }
            } else if (ccl == Integer.TYPE) {
                this.bin.readInts((int[]) array, 0, len);
            } else if (ccl == Byte.TYPE) {
                this.bin.readFully((byte[]) array, 0, len, true);
            } else if (ccl == Long.TYPE) {
                this.bin.readLongs((long[]) array, 0, len);
            } else if (ccl == Float.TYPE) {
                this.bin.readFloats((float[]) array, 0, len);
            } else if (ccl == Double.TYPE) {
                this.bin.readDoubles((double[]) array, 0, len);
            } else if (ccl == Short.TYPE) {
                this.bin.readShorts((short[]) array, 0, len);
            } else if (ccl == Character.TYPE) {
                this.bin.readChars((char[]) array, 0, len);
            } else if (ccl == Boolean.TYPE) {
                this.bin.readBooleans((boolean[]) array, 0, len);
            } else {
                throw new InternalError();
            }
            this.handles.finish(arrayHandle);
            this.passHandle = arrayHandle;
            return array;
        }
        throw new InternalError();
    }

    private Enum<?> readEnum(boolean unshared) throws IOException {
        if (this.bin.readByte() == 126) {
            ObjectStreamClass desc = readClassDesc(false);
            if (desc.isEnum()) {
                int enumHandle = this.handles.assign(unshared ? unsharedMarker : null);
                ClassNotFoundException resolveEx = desc.getResolveException();
                if (resolveEx != null) {
                    this.handles.markException(enumHandle, resolveEx);
                }
                String name = readString(false);
                Enum<?> result = null;
                Class<?> cl = desc.forClass();
                if (cl != null) {
                    try {
                        result = Enum.valueOf(cl, name);
                        if (!unshared) {
                            this.handles.setObject(enumHandle, result);
                        }
                    } catch (IllegalArgumentException ex) {
                        throw ((IOException) new InvalidObjectException("enum constant " + name + " does not exist in " + ((Object) cl)).initCause(ex));
                    }
                }
                this.handles.finish(enumHandle);
                this.passHandle = enumHandle;
                return result;
            }
            throw new InvalidClassException("non-enum class: " + ((Object) desc));
        }
        throw new InternalError();
    }

    private Object readOrdinaryObject(boolean unshared) throws IOException {
        if (this.bin.readByte() == 115) {
            ObjectStreamClass desc = readClassDesc(false);
            desc.checkDeserialize();
            Class<?> cl = desc.forClass();
            if (cl == String.class || cl == Class.class || cl == ObjectStreamClass.class) {
                throw new InvalidClassException("invalid class descriptor");
            }
            try {
                Object obj = desc.isInstantiable() ? desc.newInstance() : null;
                this.passHandle = this.handles.assign(unshared ? unsharedMarker : obj);
                ClassNotFoundException resolveEx = desc.getResolveException();
                if (resolveEx != null) {
                    this.handles.markException(this.passHandle, resolveEx);
                }
                if (desc.isExternalizable()) {
                    readExternalData((Externalizable) obj, desc);
                } else {
                    readSerialData(obj, desc);
                }
                this.handles.finish(this.passHandle);
                if (obj == null || this.handles.lookupException(this.passHandle) != null || !desc.hasReadResolveMethod()) {
                    return obj;
                }
                Object rep = desc.invokeReadResolve(obj);
                if (unshared && rep.getClass().isArray()) {
                    rep = cloneArray(rep);
                }
                if (rep == obj) {
                    return obj;
                }
                this.handles.setObject(this.passHandle, rep);
                return rep;
            } catch (Exception ex) {
                throw ((IOException) new InvalidClassException(desc.forClass().getName(), "unable to create instance").initCause(ex));
            }
        } else {
            throw new InternalError();
        }
    }

    private void readExternalData(Externalizable obj, ObjectStreamClass desc) throws IOException {
        SerialCallbackContext oldContext = this.curContext;
        if (oldContext != null) {
            oldContext.check();
        }
        this.curContext = null;
        try {
            boolean blocked = desc.hasBlockExternalData();
            if (blocked) {
                this.bin.setBlockDataMode(true);
            }
            if (obj != null) {
                try {
                    obj.readExternal(this);
                } catch (ClassNotFoundException ex) {
                    this.handles.markException(this.passHandle, ex);
                }
            }
            if (blocked) {
                skipCustomData();
            }
        } finally {
            if (oldContext != null) {
                oldContext.check();
            }
            this.curContext = oldContext;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0042, code lost:
        if (r4 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0054, code lost:
        if (r4 == null) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        r4.check();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        r8.curContext = r4;
        r8.defaultDataEnd = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readSerialData(java.lang.Object r9, java.io.ObjectStreamClass r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: java.io.ObjectInputStream.readSerialData(java.lang.Object, java.io.ObjectStreamClass):void");
    }

    private void skipCustomData() throws IOException {
        int oldHandle = this.passHandle;
        while (true) {
            if (this.bin.getBlockDataMode()) {
                this.bin.skipBlockData();
                this.bin.setBlockDataMode(false);
            }
            byte peekByte = this.bin.peekByte();
            if (peekByte != 119) {
                if (peekByte == 120) {
                    this.bin.readByte();
                    this.passHandle = oldHandle;
                    return;
                } else if (peekByte != 122) {
                    readObject0(false);
                }
            }
            this.bin.setBlockDataMode(true);
        }
    }

    private void defaultReadFields(Object obj, ObjectStreamClass desc) throws IOException {
        Class<?> cl = desc.forClass();
        if (cl == null || obj == null || cl.isInstance(obj)) {
            int primDataSize = desc.getPrimDataSize();
            byte[] bArr = this.primVals;
            if (bArr == null || bArr.length < primDataSize) {
                this.primVals = new byte[primDataSize];
            }
            this.bin.readFully(this.primVals, 0, primDataSize, false);
            if (obj != null) {
                desc.setPrimFieldValues(obj, this.primVals);
            }
            int objHandle = this.passHandle;
            ObjectStreamField[] fields = desc.getFields(false);
            Object[] objVals = new Object[desc.getNumObjFields()];
            int numPrimFields = fields.length - objVals.length;
            for (int i = 0; i < objVals.length; i++) {
                ObjectStreamField f = fields[numPrimFields + i];
                objVals[i] = readObject0(f.isUnshared());
                if (f.getField() != null) {
                    this.handles.markDependency(objHandle, this.passHandle);
                }
            }
            if (obj != null) {
                desc.setObjFieldValues(obj, objVals);
            }
            this.passHandle = objHandle;
            return;
        }
        throw new ClassCastException();
    }

    private IOException readFatalException() throws IOException {
        if (this.bin.readByte() == 123) {
            clear();
            IOException e = (IOException) readObject0(false);
            clear();
            return e;
        }
        throw new InternalError();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void handleReset() throws StreamCorruptedException {
        if (this.depth <= 0) {
            clear();
            return;
        }
        throw new StreamCorruptedException("unexpected reset; recursion depth: " + this.depth);
    }

    private static ClassLoader latestUserDefinedLoader() {
        return VMStack.getClosestUserClassLoader();
    }

    /* access modifiers changed from: private */
    public class GetFieldImpl extends GetField {
        private final ObjectStreamClass desc;
        private final int[] objHandles = new int[this.objVals.length];
        private final Object[] objVals;
        private final byte[] primVals;

        GetFieldImpl(ObjectStreamClass desc2) {
            this.desc = desc2;
            this.primVals = new byte[desc2.getPrimDataSize()];
            this.objVals = new Object[desc2.getNumObjFields()];
        }

        @Override // java.io.ObjectInputStream.GetField
        public ObjectStreamClass getObjectStreamClass() {
            return this.desc;
        }

        @Override // java.io.ObjectInputStream.GetField
        public boolean defaulted(String name) throws IOException {
            return getFieldOffset(name, null) < 0;
        }

        @Override // java.io.ObjectInputStream.GetField
        public boolean get(String name, boolean val) throws IOException {
            int off = getFieldOffset(name, Boolean.TYPE);
            return off >= 0 ? Bits.getBoolean(this.primVals, off) : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public byte get(String name, byte val) throws IOException {
            int off = getFieldOffset(name, Byte.TYPE);
            return off >= 0 ? this.primVals[off] : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public char get(String name, char val) throws IOException {
            int off = getFieldOffset(name, Character.TYPE);
            return off >= 0 ? Bits.getChar(this.primVals, off) : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public short get(String name, short val) throws IOException {
            int off = getFieldOffset(name, Short.TYPE);
            return off >= 0 ? Bits.getShort(this.primVals, off) : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public int get(String name, int val) throws IOException {
            int off = getFieldOffset(name, Integer.TYPE);
            return off >= 0 ? Bits.getInt(this.primVals, off) : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public float get(String name, float val) throws IOException {
            int off = getFieldOffset(name, Float.TYPE);
            return off >= 0 ? Bits.getFloat(this.primVals, off) : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public long get(String name, long val) throws IOException {
            int off = getFieldOffset(name, Long.TYPE);
            return off >= 0 ? Bits.getLong(this.primVals, off) : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public double get(String name, double val) throws IOException {
            int off = getFieldOffset(name, Double.TYPE);
            return off >= 0 ? Bits.getDouble(this.primVals, off) : val;
        }

        @Override // java.io.ObjectInputStream.GetField
        public Object get(String name, Object val) throws IOException {
            int off = getFieldOffset(name, Object.class);
            if (off < 0) {
                return val;
            }
            int objHandle = this.objHandles[off];
            ObjectInputStream.this.handles.markDependency(ObjectInputStream.this.passHandle, objHandle);
            if (ObjectInputStream.this.handles.lookupException(objHandle) == null) {
                return this.objVals[off];
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public void readFields() throws IOException {
            BlockDataInputStream blockDataInputStream = ObjectInputStream.this.bin;
            byte[] bArr = this.primVals;
            blockDataInputStream.readFully(bArr, 0, bArr.length, false);
            int oldHandle = ObjectInputStream.this.passHandle;
            ObjectStreamField[] fields = this.desc.getFields(false);
            int numPrimFields = fields.length - this.objVals.length;
            int i = 0;
            while (true) {
                Object[] objArr = this.objVals;
                if (i < objArr.length) {
                    objArr[i] = ObjectInputStream.this.readObject0(fields[numPrimFields + i].isUnshared());
                    this.objHandles[i] = ObjectInputStream.this.passHandle;
                    i++;
                } else {
                    ObjectInputStream.this.passHandle = oldHandle;
                    return;
                }
            }
        }

        private int getFieldOffset(String name, Class<?> type) {
            ObjectStreamField field = this.desc.getField(name, type);
            if (field != null) {
                return field.getOffset();
            }
            if (this.desc.getLocalDesc().getField(name, type) != null) {
                return -1;
            }
            throw new IllegalArgumentException("no such field " + name + " with type " + ((Object) type));
        }
    }

    /* access modifiers changed from: private */
    public static class ValidationList {
        private Callback list;

        /* access modifiers changed from: private */
        public static class Callback {
            final AccessControlContext acc;
            Callback next;
            final ObjectInputValidation obj;
            final int priority;

            Callback(ObjectInputValidation obj2, int priority2, Callback next2, AccessControlContext acc2) {
                this.obj = obj2;
                this.priority = priority2;
                this.next = next2;
                this.acc = acc2;
            }
        }

        ValidationList() {
        }

        /* access modifiers changed from: package-private */
        public void register(ObjectInputValidation obj, int priority) throws InvalidObjectException {
            if (obj != null) {
                Callback prev = null;
                Callback cur = this.list;
                while (cur != null && priority < cur.priority) {
                    prev = cur;
                    cur = cur.next;
                }
                AccessControlContext acc = AccessController.getContext();
                if (prev != null) {
                    prev.next = new Callback(obj, priority, cur, acc);
                } else {
                    this.list = new Callback(obj, priority, this.list, acc);
                }
            } else {
                throw new InvalidObjectException("null callback");
            }
        }

        /* access modifiers changed from: package-private */
        public void doCallbacks() throws InvalidObjectException {
            while (this.list != null) {
                try {
                    AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                        /* class java.io.ObjectInputStream.ValidationList.AnonymousClass1 */

                        @Override // java.security.PrivilegedExceptionAction
                        public Void run() throws InvalidObjectException {
                            ValidationList.this.list.obj.validateObject();
                            return null;
                        }
                    }, this.list.acc);
                    this.list = this.list.next;
                } catch (PrivilegedActionException ex) {
                    this.list = null;
                    throw ((InvalidObjectException) ex.getException());
                }
            }
        }

        public void clear() {
            this.list = null;
        }
    }

    /* access modifiers changed from: private */
    public static class PeekInputStream extends InputStream {
        private final InputStream in;
        private int peekb = -1;
        private long totalBytesRead = 0;

        PeekInputStream(InputStream in2) {
            this.in = in2;
        }

        /* access modifiers changed from: package-private */
        public int peek() throws IOException {
            int i = this.peekb;
            if (i >= 0) {
                return i;
            }
            this.peekb = this.in.read();
            this.totalBytesRead += this.peekb >= 0 ? 1 : 0;
            return this.peekb;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.peekb >= 0) {
                int v = this.peekb;
                this.peekb = -1;
                return v;
            }
            int nbytes = this.in.read();
            this.totalBytesRead += nbytes >= 0 ? 1 : 0;
            return nbytes;
        }

        @Override // java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            int i = this.peekb;
            long j = 0;
            if (i < 0) {
                int nbytes = this.in.read(b, off, len);
                long j2 = this.totalBytesRead;
                if (nbytes >= 0) {
                    j = (long) nbytes;
                }
                this.totalBytesRead = j2 + j;
                return nbytes;
            }
            b[off] = (byte) i;
            this.peekb = -1;
            int nbytes2 = this.in.read(b, off + 1, len - 1);
            long j3 = this.totalBytesRead;
            if (nbytes2 >= 0) {
                j = (long) nbytes2;
            }
            this.totalBytesRead = j3 + j;
            if (nbytes2 >= 0) {
                return nbytes2 + 1;
            }
            return 1;
        }

        /* access modifiers changed from: package-private */
        public void readFully(byte[] b, int off, int len) throws IOException {
            int n = 0;
            while (n < len) {
                int count = read(b, off + n, len - n);
                if (count >= 0) {
                    n += count;
                } else {
                    throw new EOFException();
                }
            }
        }

        @Override // java.io.InputStream
        public long skip(long n) throws IOException {
            if (n <= 0) {
                return 0;
            }
            int skipped = 0;
            if (this.peekb >= 0) {
                this.peekb = -1;
                skipped = 0 + 1;
                n--;
            }
            long n2 = ((long) skipped) + this.in.skip(n);
            this.totalBytesRead += n2;
            return n2;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.in.available() + (this.peekb >= 0 ? 1 : 0);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            this.in.close();
        }

        public long getBytesRead() {
            return this.totalBytesRead;
        }
    }

    /* access modifiers changed from: private */
    public class BlockDataInputStream extends InputStream implements DataInput {
        private static final int CHAR_BUF_SIZE = 256;
        private static final int HEADER_BLOCKED = -2;
        private static final int MAX_BLOCK_SIZE = 1024;
        private static final int MAX_HEADER_SIZE = 5;
        private boolean blkmode = false;
        private final byte[] buf = new byte[1024];
        private final char[] cbuf = new char[256];
        private final DataInputStream din;
        private int end = -1;
        private final byte[] hbuf = new byte[5];
        private final PeekInputStream in;
        private int pos = 0;
        private int unread = 0;

        BlockDataInputStream(InputStream in2) {
            this.in = new PeekInputStream(in2);
            this.din = new DataInputStream(this);
        }

        /* access modifiers changed from: package-private */
        public boolean setBlockDataMode(boolean newmode) throws IOException {
            boolean z = this.blkmode;
            if (z == newmode) {
                return z;
            }
            if (newmode) {
                this.pos = 0;
                this.end = 0;
                this.unread = 0;
            } else if (this.pos < this.end) {
                throw new IllegalStateException("unread block data");
            }
            this.blkmode = newmode;
            return !this.blkmode;
        }

        /* access modifiers changed from: package-private */
        public boolean getBlockDataMode() {
            return this.blkmode;
        }

        /* access modifiers changed from: package-private */
        public void skipBlockData() throws IOException {
            if (this.blkmode) {
                while (this.end >= 0) {
                    refill();
                }
                return;
            }
            throw new IllegalStateException("not in block data mode");
        }

        private int readBlockHeader(boolean canBlock) throws IOException {
            int avail;
            if (ObjectInputStream.this.defaultDataEnd) {
                return -1;
            }
            while (true) {
                if (canBlock) {
                    avail = Integer.MAX_VALUE;
                } else {
                    try {
                        avail = this.in.available();
                    } catch (EOFException e) {
                        throw new StreamCorruptedException("unexpected EOF while reading block data header");
                    }
                }
                if (avail == 0) {
                    return -2;
                }
                int tc = this.in.peek();
                if (tc != 119) {
                    if (tc == 121) {
                        this.in.read();
                        ObjectInputStream.this.handleReset();
                    } else if (tc != 122) {
                        if (tc >= 0) {
                            if (tc < 112 || tc > 126) {
                                throw new StreamCorruptedException(String.format("invalid type code: %02X", Integer.valueOf(tc)));
                            }
                        }
                        return -1;
                    } else if (avail < 5) {
                        return -2;
                    } else {
                        this.in.readFully(this.hbuf, 0, 5);
                        int len = Bits.getInt(this.hbuf, 1);
                        if (len >= 0) {
                            return len;
                        }
                        throw new StreamCorruptedException("illegal block data header length: " + len);
                    }
                } else if (avail < 2) {
                    return -2;
                } else {
                    this.in.readFully(this.hbuf, 0, 2);
                    return this.hbuf[1] & 255;
                }
            }
        }

        private void refill() throws IOException {
            do {
                try {
                    this.pos = 0;
                    if (this.unread > 0) {
                        int n = this.in.read(this.buf, 0, Math.min(this.unread, 1024));
                        if (n >= 0) {
                            this.end = n;
                            this.unread -= n;
                        } else {
                            throw new StreamCorruptedException("unexpected EOF in middle of data block");
                        }
                    } else {
                        int n2 = readBlockHeader(true);
                        if (n2 >= 0) {
                            this.end = 0;
                            this.unread = n2;
                        } else {
                            this.end = -1;
                            this.unread = 0;
                        }
                    }
                } catch (IOException ex) {
                    this.pos = 0;
                    this.end = -1;
                    this.unread = 0;
                    throw ex;
                }
            } while (this.pos == this.end);
        }

        /* access modifiers changed from: package-private */
        public int currentBlockRemaining() {
            if (this.blkmode) {
                int i = this.end;
                if (i >= 0) {
                    return (i - this.pos) + this.unread;
                }
                return 0;
            }
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public int peek() throws IOException {
            if (!this.blkmode) {
                return this.in.peek();
            }
            if (this.pos == this.end) {
                refill();
            }
            if (this.end >= 0) {
                return this.buf[this.pos] & 255;
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public byte peekByte() throws IOException {
            int val = peek();
            if (val >= 0) {
                return (byte) val;
            }
            throw new EOFException();
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (!this.blkmode) {
                return this.in.read();
            }
            if (this.pos == this.end) {
                refill();
            }
            if (this.end < 0) {
                return -1;
            }
            byte[] bArr = this.buf;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i] & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            return read(b, off, len, false);
        }

        @Override // java.io.InputStream
        public long skip(long len) throws IOException {
            long remain = len;
            while (remain > 0) {
                if (this.blkmode) {
                    if (this.pos == this.end) {
                        refill();
                    }
                    int i = this.end;
                    if (i < 0) {
                        break;
                    }
                    int nread = (int) Math.min(remain, (long) (i - this.pos));
                    remain -= (long) nread;
                    this.pos += nread;
                } else {
                    int nread2 = this.in.read(this.buf, 0, (int) Math.min(remain, 1024L));
                    if (nread2 < 0) {
                        break;
                    }
                    remain -= (long) nread2;
                }
            }
            return len - remain;
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            int n;
            if (!this.blkmode) {
                return this.in.available();
            }
            if (this.pos == this.end && this.unread == 0) {
                do {
                    n = readBlockHeader(false);
                } while (n == 0);
                if (n != -2) {
                    if (n != -1) {
                        this.pos = 0;
                        this.end = 0;
                        this.unread = n;
                    } else {
                        this.pos = 0;
                        this.end = -1;
                    }
                }
            }
            int unreadAvail = this.unread > 0 ? Math.min(this.in.available(), this.unread) : 0;
            int i = this.end;
            if (i >= 0) {
                return (i - this.pos) + unreadAvail;
            }
            return 0;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            if (this.blkmode) {
                this.pos = 0;
                this.end = -1;
                this.unread = 0;
            }
            this.in.close();
        }

        /* access modifiers changed from: package-private */
        public int read(byte[] b, int off, int len, boolean copy) throws IOException {
            if (len == 0) {
                return 0;
            }
            if (this.blkmode) {
                if (this.pos == this.end) {
                    refill();
                }
                int i = this.end;
                if (i < 0) {
                    return -1;
                }
                int nread = Math.min(len, i - this.pos);
                System.arraycopy(this.buf, this.pos, b, off, nread);
                this.pos += nread;
                return nread;
            } else if (!copy) {
                return this.in.read(b, off, len);
            } else {
                int nread2 = this.in.read(this.buf, 0, Math.min(len, 1024));
                if (nread2 > 0) {
                    System.arraycopy(this.buf, 0, b, off, nread2);
                }
                return nread2;
            }
        }

        @Override // java.io.DataInput
        public void readFully(byte[] b) throws IOException {
            readFully(b, 0, b.length, false);
        }

        @Override // java.io.DataInput
        public void readFully(byte[] b, int off, int len) throws IOException {
            readFully(b, off, len, false);
        }

        public void readFully(byte[] b, int off, int len, boolean copy) throws IOException {
            while (len > 0) {
                int n = read(b, off, len, copy);
                if (n >= 0) {
                    off += n;
                    len -= n;
                } else {
                    throw new EOFException();
                }
            }
        }

        @Override // java.io.DataInput
        public int skipBytes(int n) throws IOException {
            return this.din.skipBytes(n);
        }

        @Override // java.io.DataInput
        public boolean readBoolean() throws IOException {
            int v = read();
            if (v >= 0) {
                return v != 0;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public byte readByte() throws IOException {
            int v = read();
            if (v >= 0) {
                return (byte) v;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public int readUnsignedByte() throws IOException {
            int v = read();
            if (v >= 0) {
                return v;
            }
            throw new EOFException();
        }

        @Override // java.io.DataInput
        public char readChar() throws IOException {
            if (!this.blkmode) {
                this.pos = 0;
                this.in.readFully(this.buf, 0, 2);
            } else if (this.end - this.pos < 2) {
                return this.din.readChar();
            }
            char v = Bits.getChar(this.buf, this.pos);
            this.pos += 2;
            return v;
        }

        @Override // java.io.DataInput
        public short readShort() throws IOException {
            if (!this.blkmode) {
                this.pos = 0;
                this.in.readFully(this.buf, 0, 2);
            } else if (this.end - this.pos < 2) {
                return this.din.readShort();
            }
            short v = Bits.getShort(this.buf, this.pos);
            this.pos += 2;
            return v;
        }

        @Override // java.io.DataInput
        public int readUnsignedShort() throws IOException {
            if (!this.blkmode) {
                this.pos = 0;
                this.in.readFully(this.buf, 0, 2);
            } else if (this.end - this.pos < 2) {
                return this.din.readUnsignedShort();
            }
            int v = Bits.getShort(this.buf, this.pos) & 65535;
            this.pos += 2;
            return v;
        }

        @Override // java.io.DataInput
        public int readInt() throws IOException {
            if (!this.blkmode) {
                this.pos = 0;
                this.in.readFully(this.buf, 0, 4);
            } else if (this.end - this.pos < 4) {
                return this.din.readInt();
            }
            int v = Bits.getInt(this.buf, this.pos);
            this.pos += 4;
            return v;
        }

        @Override // java.io.DataInput
        public float readFloat() throws IOException {
            if (!this.blkmode) {
                this.pos = 0;
                this.in.readFully(this.buf, 0, 4);
            } else if (this.end - this.pos < 4) {
                return this.din.readFloat();
            }
            float v = Bits.getFloat(this.buf, this.pos);
            this.pos += 4;
            return v;
        }

        @Override // java.io.DataInput
        public long readLong() throws IOException {
            if (!this.blkmode) {
                this.pos = 0;
                this.in.readFully(this.buf, 0, 8);
            } else if (this.end - this.pos < 8) {
                return this.din.readLong();
            }
            long v = Bits.getLong(this.buf, this.pos);
            this.pos += 8;
            return v;
        }

        @Override // java.io.DataInput
        public double readDouble() throws IOException {
            if (!this.blkmode) {
                this.pos = 0;
                this.in.readFully(this.buf, 0, 8);
            } else if (this.end - this.pos < 8) {
                return this.din.readDouble();
            }
            double v = Bits.getDouble(this.buf, this.pos);
            this.pos += 8;
            return v;
        }

        @Override // java.io.DataInput
        public String readUTF() throws IOException {
            return readUTFBody((long) readUnsignedShort());
        }

        @Override // java.io.DataInput
        public String readLine() throws IOException {
            return this.din.readLine();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x003a A[LOOP:1: B:9:0x0038->B:10:0x003a, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void readBooleans(boolean[] r7, int r8, int r9) throws java.io.IOException {
            /*
                r6 = this;
                int r0 = r8 + r9
            L_0x0002:
                if (r8 >= r0) goto L_0x004c
                boolean r1 = r6.blkmode
                if (r1 != 0) goto L_0x001d
                int r1 = r0 - r8
                r2 = 1024(0x400, float:1.435E-42)
                int r1 = java.lang.Math.min(r1, r2)
                java.io.ObjectInputStream$PeekInputStream r2 = r6.in
                byte[] r3 = r6.buf
                r4 = 0
                r2.readFully(r3, r4, r1)
                int r2 = r8 + r1
                r6.pos = r4
                goto L_0x0038
            L_0x001d:
                int r1 = r6.end
                int r2 = r6.pos
                int r3 = r1 - r2
                r4 = 1
                if (r3 >= r4) goto L_0x0032
                int r1 = r8 + 1
                java.io.DataInputStream r2 = r6.din
                boolean r2 = r2.readBoolean()
                r7[r8] = r2
                r8 = r1
                goto L_0x0002
            L_0x0032:
                int r1 = r1 + r8
                int r1 = r1 - r2
                int r2 = java.lang.Math.min(r0, r1)
            L_0x0038:
                if (r8 >= r2) goto L_0x0002
                int r1 = r8 + 1
                byte[] r3 = r6.buf
                int r4 = r6.pos
                int r5 = r4 + 1
                r6.pos = r5
                boolean r3 = java.io.Bits.getBoolean(r3, r4)
                r7[r8] = r3
                r8 = r1
                goto L_0x0038
            L_0x004c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.io.ObjectInputStream.BlockDataInputStream.readBooleans(boolean[], int, int):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x003e A[LOOP:1: B:9:0x003c->B:10:0x003e, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void readChars(char[] r8, int r9, int r10) throws java.io.IOException {
            /*
                r7 = this;
                int r0 = r9 + r10
            L_0x0002:
                if (r9 >= r0) goto L_0x0051
                boolean r1 = r7.blkmode
                r2 = 2
                if (r1 != 0) goto L_0x0020
                int r1 = r0 - r9
                r3 = 512(0x200, float:7.175E-43)
                int r1 = java.lang.Math.min(r1, r3)
                java.io.ObjectInputStream$PeekInputStream r3 = r7.in
                byte[] r4 = r7.buf
                int r5 = r1 << 1
                r6 = 0
                r3.readFully(r4, r6, r5)
                int r3 = r9 + r1
                r7.pos = r6
                goto L_0x003c
            L_0x0020:
                int r1 = r7.end
                int r3 = r7.pos
                int r4 = r1 - r3
                if (r4 >= r2) goto L_0x0034
                int r1 = r9 + 1
                java.io.DataInputStream r2 = r7.din
                char r2 = r2.readChar()
                r8[r9] = r2
                r9 = r1
                goto L_0x0002
            L_0x0034:
                int r1 = r1 - r3
                int r1 = r1 >> 1
                int r1 = r1 + r9
                int r3 = java.lang.Math.min(r0, r1)
            L_0x003c:
                if (r9 >= r3) goto L_0x0002
                int r1 = r9 + 1
                byte[] r4 = r7.buf
                int r5 = r7.pos
                char r4 = java.io.Bits.getChar(r4, r5)
                r8[r9] = r4
                int r9 = r7.pos
                int r9 = r9 + r2
                r7.pos = r9
                r9 = r1
                goto L_0x003c
            L_0x0051:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.io.ObjectInputStream.BlockDataInputStream.readChars(char[], int, int):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x003e A[LOOP:1: B:9:0x003c->B:10:0x003e, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void readShorts(short[] r8, int r9, int r10) throws java.io.IOException {
            /*
                r7 = this;
                int r0 = r9 + r10
            L_0x0002:
                if (r9 >= r0) goto L_0x0051
                boolean r1 = r7.blkmode
                r2 = 2
                if (r1 != 0) goto L_0x0020
                int r1 = r0 - r9
                r3 = 512(0x200, float:7.175E-43)
                int r1 = java.lang.Math.min(r1, r3)
                java.io.ObjectInputStream$PeekInputStream r3 = r7.in
                byte[] r4 = r7.buf
                int r5 = r1 << 1
                r6 = 0
                r3.readFully(r4, r6, r5)
                int r3 = r9 + r1
                r7.pos = r6
                goto L_0x003c
            L_0x0020:
                int r1 = r7.end
                int r3 = r7.pos
                int r4 = r1 - r3
                if (r4 >= r2) goto L_0x0034
                int r1 = r9 + 1
                java.io.DataInputStream r2 = r7.din
                short r2 = r2.readShort()
                r8[r9] = r2
                r9 = r1
                goto L_0x0002
            L_0x0034:
                int r1 = r1 - r3
                int r1 = r1 >> 1
                int r1 = r1 + r9
                int r3 = java.lang.Math.min(r0, r1)
            L_0x003c:
                if (r9 >= r3) goto L_0x0002
                int r1 = r9 + 1
                byte[] r4 = r7.buf
                int r5 = r7.pos
                short r4 = java.io.Bits.getShort(r4, r5)
                r8[r9] = r4
                int r9 = r7.pos
                int r9 = r9 + r2
                r7.pos = r9
                r9 = r1
                goto L_0x003c
            L_0x0051:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.io.ObjectInputStream.BlockDataInputStream.readShorts(short[], int, int):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x003e A[LOOP:1: B:9:0x003c->B:10:0x003e, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void readInts(int[] r8, int r9, int r10) throws java.io.IOException {
            /*
                r7 = this;
                int r0 = r9 + r10
            L_0x0002:
                if (r9 >= r0) goto L_0x0051
                boolean r1 = r7.blkmode
                r2 = 4
                if (r1 != 0) goto L_0x0020
                int r1 = r0 - r9
                r3 = 256(0x100, float:3.59E-43)
                int r1 = java.lang.Math.min(r1, r3)
                java.io.ObjectInputStream$PeekInputStream r3 = r7.in
                byte[] r4 = r7.buf
                int r5 = r1 << 2
                r6 = 0
                r3.readFully(r4, r6, r5)
                int r3 = r9 + r1
                r7.pos = r6
                goto L_0x003c
            L_0x0020:
                int r1 = r7.end
                int r3 = r7.pos
                int r4 = r1 - r3
                if (r4 >= r2) goto L_0x0034
                int r1 = r9 + 1
                java.io.DataInputStream r2 = r7.din
                int r2 = r2.readInt()
                r8[r9] = r2
                r9 = r1
                goto L_0x0002
            L_0x0034:
                int r1 = r1 - r3
                int r1 = r1 >> 2
                int r1 = r1 + r9
                int r3 = java.lang.Math.min(r0, r1)
            L_0x003c:
                if (r9 >= r3) goto L_0x0002
                int r1 = r9 + 1
                byte[] r4 = r7.buf
                int r5 = r7.pos
                int r4 = java.io.Bits.getInt(r4, r5)
                r8[r9] = r4
                int r9 = r7.pos
                int r9 = r9 + r2
                r7.pos = r9
                r9 = r1
                goto L_0x003c
            L_0x0051:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.io.ObjectInputStream.BlockDataInputStream.readInts(int[], int, int):void");
        }

        /* access modifiers changed from: package-private */
        public void readFloats(float[] v, int off, int len) throws IOException {
            int span;
            int endoff = off + len;
            while (off < endoff) {
                if (!this.blkmode) {
                    span = Math.min(endoff - off, 256);
                    this.in.readFully(this.buf, 0, span << 2);
                    this.pos = 0;
                } else {
                    int span2 = this.end;
                    int i = this.pos;
                    if (span2 - i < 4) {
                        v[off] = this.din.readFloat();
                        off++;
                    } else {
                        span = Math.min(endoff - off, (span2 - i) >> 2);
                    }
                }
                ObjectInputStream.bytesToFloats(this.buf, this.pos, v, off, span);
                off += span;
                this.pos += span << 2;
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x003f A[LOOP:1: B:9:0x003d->B:10:0x003f, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void readLongs(long[] r8, int r9, int r10) throws java.io.IOException {
            /*
                r7 = this;
                int r0 = r9 + r10
            L_0x0002:
                if (r9 >= r0) goto L_0x0052
                boolean r1 = r7.blkmode
                r2 = 8
                if (r1 != 0) goto L_0x0021
                int r1 = r0 - r9
                r3 = 128(0x80, float:1.794E-43)
                int r1 = java.lang.Math.min(r1, r3)
                java.io.ObjectInputStream$PeekInputStream r3 = r7.in
                byte[] r4 = r7.buf
                int r5 = r1 << 3
                r6 = 0
                r3.readFully(r4, r6, r5)
                int r3 = r9 + r1
                r7.pos = r6
                goto L_0x003d
            L_0x0021:
                int r1 = r7.end
                int r3 = r7.pos
                int r4 = r1 - r3
                if (r4 >= r2) goto L_0x0035
                int r1 = r9 + 1
                java.io.DataInputStream r2 = r7.din
                long r2 = r2.readLong()
                r8[r9] = r2
                r9 = r1
                goto L_0x0002
            L_0x0035:
                int r1 = r1 - r3
                int r1 = r1 >> 3
                int r1 = r1 + r9
                int r3 = java.lang.Math.min(r0, r1)
            L_0x003d:
                if (r9 >= r3) goto L_0x0002
                int r1 = r9 + 1
                byte[] r4 = r7.buf
                int r5 = r7.pos
                long r4 = java.io.Bits.getLong(r4, r5)
                r8[r9] = r4
                int r9 = r7.pos
                int r9 = r9 + r2
                r7.pos = r9
                r9 = r1
                goto L_0x003d
            L_0x0052:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.io.ObjectInputStream.BlockDataInputStream.readLongs(long[], int, int):void");
        }

        /* access modifiers changed from: package-private */
        public void readDoubles(double[] v, int off, int len) throws IOException {
            int span;
            int endoff = off + len;
            while (off < endoff) {
                if (!this.blkmode) {
                    span = Math.min(endoff - off, 128);
                    this.in.readFully(this.buf, 0, span << 3);
                    this.pos = 0;
                } else {
                    int span2 = this.end;
                    int i = this.pos;
                    if (span2 - i < 8) {
                        v[off] = this.din.readDouble();
                        off++;
                    } else {
                        span = Math.min(endoff - off, (span2 - i) >> 3);
                    }
                }
                ObjectInputStream.bytesToDoubles(this.buf, this.pos, v, off, span);
                off += span;
                this.pos += span << 3;
            }
        }

        /* access modifiers changed from: package-private */
        public String readLongUTF() throws IOException {
            return readUTFBody(readLong());
        }

        private String readUTFBody(long utflen) throws IOException {
            StringBuilder sbuf = new StringBuilder();
            if (!this.blkmode) {
                this.pos = 0;
                this.end = 0;
            }
            while (utflen > 0) {
                int i = this.end;
                int i2 = this.pos;
                int avail = i - i2;
                if (avail >= 3 || ((long) avail) == utflen) {
                    utflen -= readUTFSpan(sbuf, utflen);
                } else if (this.blkmode) {
                    utflen -= (long) readUTFChar(sbuf, utflen);
                } else {
                    if (avail > 0) {
                        byte[] bArr = this.buf;
                        System.arraycopy(bArr, i2, bArr, 0, avail);
                    }
                    this.pos = 0;
                    this.end = (int) Math.min(1024L, utflen);
                    this.in.readFully(this.buf, avail, this.end - avail);
                }
            }
            return sbuf.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:56:0x00d4  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private long readUTFSpan(java.lang.StringBuilder r17, long r18) throws java.io.IOException {
            /*
            // Method dump skipped, instructions count: 302
            */
            throw new UnsupportedOperationException("Method not decompiled: java.io.ObjectInputStream.BlockDataInputStream.readUTFSpan(java.lang.StringBuilder, long):long");
        }

        private int readUTFChar(StringBuilder sbuf, long utflen) throws IOException {
            int b1 = readByte() & 255;
            switch (b1 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    sbuf.append((char) b1);
                    return 1;
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    throw new UTFDataFormatException();
                case 12:
                case 13:
                    if (utflen >= 2) {
                        int b2 = readByte();
                        if ((b2 & 192) == 128) {
                            sbuf.append((char) (((b1 & 31) << 6) | ((b2 & 63) << 0)));
                            return 2;
                        }
                        throw new UTFDataFormatException();
                    }
                    throw new UTFDataFormatException();
                case 14:
                    if (utflen < 3) {
                        if (utflen == 2) {
                            readByte();
                        }
                        throw new UTFDataFormatException();
                    }
                    int b22 = readByte();
                    int b3 = readByte();
                    if ((b22 & 192) == 128 && (b3 & 192) == 128) {
                        sbuf.append((char) (((b1 & 15) << 12) | ((b22 & 63) << 6) | ((b3 & 63) << 0)));
                        return 3;
                    }
                    throw new UTFDataFormatException();
            }
        }

        /* access modifiers changed from: package-private */
        public long getBytesRead() {
            return this.in.getBytesRead();
        }
    }

    /* access modifiers changed from: private */
    public static class HandleTable {
        private static final byte STATUS_EXCEPTION = 3;
        private static final byte STATUS_OK = 1;
        private static final byte STATUS_UNKNOWN = 2;
        HandleList[] deps;
        Object[] entries;
        int lowDep = -1;
        int size = 0;
        byte[] status;

        HandleTable(int initialCapacity) {
            this.status = new byte[initialCapacity];
            this.entries = new Object[initialCapacity];
            this.deps = new HandleList[initialCapacity];
        }

        /* access modifiers changed from: package-private */
        public int assign(Object obj) {
            if (this.size >= this.entries.length) {
                grow();
            }
            byte[] bArr = this.status;
            int i = this.size;
            bArr[i] = 2;
            this.entries[i] = obj;
            this.size = i + 1;
            return i;
        }

        /* access modifiers changed from: package-private */
        public void markDependency(int dependent, int target) {
            if (dependent != -1 && target != -1) {
                byte[] bArr = this.status;
                byte b = bArr[dependent];
                if (b == 2) {
                    byte b2 = bArr[target];
                    if (b2 == 1) {
                        return;
                    }
                    if (b2 == 2) {
                        HandleList[] handleListArr = this.deps;
                        if (handleListArr[target] == null) {
                            handleListArr[target] = new HandleList();
                        }
                        this.deps[target].add(dependent);
                        int i = this.lowDep;
                        if (i < 0 || i > target) {
                            this.lowDep = target;
                        }
                    } else if (b2 == 3) {
                        markException(dependent, (ClassNotFoundException) this.entries[target]);
                    } else {
                        throw new InternalError();
                    }
                } else if (b != 3) {
                    throw new InternalError();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void markException(int handle, ClassNotFoundException ex) {
            byte[] bArr = this.status;
            byte b = bArr[handle];
            if (b == 2) {
                bArr[handle] = 3;
                this.entries[handle] = ex;
                HandleList dlist = this.deps[handle];
                if (dlist != null) {
                    int ndeps = dlist.size();
                    for (int i = 0; i < ndeps; i++) {
                        markException(dlist.get(i), ex);
                    }
                    this.deps[handle] = null;
                }
            } else if (b != 3) {
                throw new InternalError();
            }
        }

        /* access modifiers changed from: package-private */
        public void finish(int handle) {
            int end;
            int end2 = this.lowDep;
            if (end2 < 0) {
                end = handle + 1;
            } else if (end2 >= handle) {
                end = this.size;
                this.lowDep = -1;
            } else {
                return;
            }
            for (int i = handle; i < end; i++) {
                byte[] bArr = this.status;
                byte b = bArr[i];
                if (b != 1) {
                    if (b == 2) {
                        bArr[i] = 1;
                        this.deps[i] = null;
                    } else if (b != 3) {
                        throw new InternalError();
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setObject(int handle, Object obj) {
            byte b = this.status[handle];
            if (b == 1 || b == 2) {
                this.entries[handle] = obj;
            } else if (b != 3) {
                throw new InternalError();
            }
        }

        /* access modifiers changed from: package-private */
        public Object lookupObject(int handle) {
            if (handle == -1 || this.status[handle] == 3) {
                return null;
            }
            return this.entries[handle];
        }

        /* access modifiers changed from: package-private */
        public ClassNotFoundException lookupException(int handle) {
            if (handle == -1 || this.status[handle] != 3) {
                return null;
            }
            return (ClassNotFoundException) this.entries[handle];
        }

        /* access modifiers changed from: package-private */
        public void clear() {
            Arrays.fill(this.status, 0, this.size, (byte) 0);
            Arrays.fill(this.entries, 0, this.size, (Object) null);
            Arrays.fill(this.deps, 0, this.size, (Object) null);
            this.lowDep = -1;
            this.size = 0;
        }

        /* access modifiers changed from: package-private */
        public int size() {
            return this.size;
        }

        private void grow() {
            int newCapacity = (this.entries.length << 1) + 1;
            byte[] newStatus = new byte[newCapacity];
            Object[] newEntries = new Object[newCapacity];
            HandleList[] newDeps = new HandleList[newCapacity];
            System.arraycopy(this.status, 0, newStatus, 0, this.size);
            System.arraycopy(this.entries, 0, newEntries, 0, this.size);
            System.arraycopy(this.deps, 0, newDeps, 0, this.size);
            this.status = newStatus;
            this.entries = newEntries;
            this.deps = newDeps;
        }

        /* access modifiers changed from: private */
        public static class HandleList {
            private int[] list = new int[4];
            private int size = 0;

            public void add(int handle) {
                int i = this.size;
                int[] iArr = this.list;
                if (i >= iArr.length) {
                    int[] newList = new int[(iArr.length << 1)];
                    System.arraycopy((Object) iArr, 0, (Object) newList, 0, iArr.length);
                    this.list = newList;
                }
                int[] newList2 = this.list;
                int i2 = this.size;
                this.size = i2 + 1;
                newList2[i2] = handle;
            }

            public int get(int index) {
                if (index < this.size) {
                    return this.list[index];
                }
                throw new ArrayIndexOutOfBoundsException();
            }

            public int size() {
                return this.size;
            }
        }
    }

    private static Object cloneArray(Object array) {
        if (array instanceof Object[]) {
            return ((Object[]) array).clone();
        }
        if (array instanceof boolean[]) {
            return ((boolean[]) array).clone();
        }
        if (array instanceof byte[]) {
            return ((byte[]) array).clone();
        }
        if (array instanceof char[]) {
            return ((char[]) array).clone();
        }
        if (array instanceof double[]) {
            return ((double[]) array).clone();
        }
        if (array instanceof float[]) {
            return ((float[]) array).clone();
        }
        if (array instanceof int[]) {
            return ((int[]) array).clone();
        }
        if (array instanceof long[]) {
            return ((long[]) array).clone();
        }
        if (array instanceof short[]) {
            return ((short[]) array).clone();
        }
        throw new AssertionError();
    }
}
