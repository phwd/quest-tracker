package java.io;

import dalvik.system.VMRuntime;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

public class ObjectStreamClass implements Serializable {
    static final int MAX_SDK_TARGET_FOR_CLINIT_UIDGEN_WORKAROUND = 23;
    public static final ObjectStreamField[] NO_FIELDS = new ObjectStreamField[0];
    private static final ObjectStreamField[] serialPersistentFields = NO_FIELDS;
    private static final long serialVersionUID = -6120832682080437368L;
    public static DefaultSUIDCompatibilityListener suidCompatibilityListener = $$Lambda$ObjectStreamClass$GVMp_cBEBrBo_ZKh_HiLSOfGo.INSTANCE;
    private Class<?> cl;
    private Constructor<?> cons;
    private volatile ClassDataSlot[] dataLayout;
    private ExceptionInfo defaultSerializeEx;
    private ExceptionInfo deserializeEx;
    private boolean externalizable;
    private FieldReflector fieldRefl;
    private ObjectStreamField[] fields;
    private boolean hasBlockExternalData = true;
    private boolean hasWriteObjectData;
    private boolean initialized;
    private boolean isEnum;
    private boolean isProxy;
    private ObjectStreamClass localDesc;
    private String name;
    private int numObjFields;
    private int primDataSize;
    private Method readObjectMethod;
    private Method readObjectNoDataMethod;
    private Method readResolveMethod;
    private ClassNotFoundException resolveEx;
    private boolean serializable;
    private ExceptionInfo serializeEx;
    private volatile Long suid;
    private ObjectStreamClass superDesc;
    private Method writeObjectMethod;
    private Method writeReplaceMethod;

    public interface DefaultSUIDCompatibilityListener {
        void warnDefaultSUIDTargetVersionDependent(Class<?> cls, long j);
    }

    private static native boolean hasStaticInitializer(Class<?> cls, boolean z);

    /* access modifiers changed from: private */
    public static class Caches {
        static final ConcurrentMap<WeakClassKey, Reference<?>> localDescs = new ConcurrentHashMap();
        private static final ReferenceQueue<Class<?>> localDescsQueue = new ReferenceQueue<>();
        static final ConcurrentMap<FieldReflectorKey, Reference<?>> reflectors = new ConcurrentHashMap();
        private static final ReferenceQueue<Class<?>> reflectorsQueue = new ReferenceQueue<>();

        private Caches() {
        }
    }

    /* access modifiers changed from: private */
    public static class ExceptionInfo {
        private final String className;
        private final String message;

        ExceptionInfo(String cn, String msg) {
            this.className = cn;
            this.message = msg;
        }

        /* access modifiers changed from: package-private */
        public InvalidClassException newInvalidClassException() {
            return new InvalidClassException(this.className, this.message);
        }
    }

    public static ObjectStreamClass lookup(Class<?> cl2) {
        return lookup(cl2, false);
    }

    public static ObjectStreamClass lookupAny(Class<?> cl2) {
        return lookup(cl2, true);
    }

    public String getName() {
        return this.name;
    }

    public long getSerialVersionUID() {
        if (this.suid == null) {
            this.suid = (Long) AccessController.doPrivileged(new PrivilegedAction<Long>() {
                /* class java.io.ObjectStreamClass.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public Long run() {
                    return Long.valueOf(ObjectStreamClass.computeDefaultSUID(ObjectStreamClass.this.cl));
                }
            });
        }
        return this.suid.longValue();
    }

    @CallerSensitive
    public Class<?> forClass() {
        if (this.cl == null) {
            return null;
        }
        requireInitialized();
        if (System.getSecurityManager() != null && ReflectUtil.needsPackageAccessCheck(Reflection.getCallerClass().getClassLoader(), this.cl.getClassLoader())) {
            ReflectUtil.checkPackageAccess(this.cl);
        }
        return this.cl;
    }

    public ObjectStreamField[] getFields() {
        return getFields(true);
    }

    public ObjectStreamField getField(String name2) {
        return getField(name2, null);
    }

    public String toString() {
        return this.name + ": static final long serialVersionUID = " + getSerialVersionUID() + "L;";
    }

    static ObjectStreamClass lookup(Class<?> cl2, boolean all) {
        if (!all && !Serializable.class.isAssignableFrom(cl2)) {
            return null;
        }
        processQueue(Caches.localDescsQueue, Caches.localDescs);
        WeakClassKey key = new WeakClassKey(cl2, Caches.localDescsQueue);
        Reference<?> ref = Caches.localDescs.get(key);
        Object entry = null;
        if (ref != null) {
            entry = ref.get();
        }
        EntryFuture future = null;
        if (entry == null) {
            EntryFuture newEntry = new EntryFuture();
            Reference<?> newRef = new SoftReference<>(newEntry);
            do {
                if (ref != null) {
                    Caches.localDescs.remove(key, ref);
                }
                ref = Caches.localDescs.putIfAbsent(key, newRef);
                if (ref != null) {
                    entry = ref.get();
                }
                if (ref == null) {
                    break;
                }
            } while (entry == null);
            if (entry == null) {
                future = newEntry;
            }
        }
        if (entry instanceof ObjectStreamClass) {
            return (ObjectStreamClass) entry;
        }
        if (entry instanceof EntryFuture) {
            future = (EntryFuture) entry;
            if (future.getOwner() == Thread.currentThread()) {
                entry = null;
            } else {
                entry = future.get();
            }
        }
        if (entry == null) {
            try {
                entry = new ObjectStreamClass(cl2);
            } catch (Throwable th) {
                entry = th;
            }
            if (future.set(entry)) {
                Caches.localDescs.put(key, new SoftReference(entry));
            } else {
                entry = future.get();
            }
        }
        if (entry instanceof ObjectStreamClass) {
            return (ObjectStreamClass) entry;
        }
        if (entry instanceof RuntimeException) {
            throw ((RuntimeException) entry);
        } else if (entry instanceof Error) {
            throw ((Error) entry);
        } else {
            throw new InternalError("unexpected entry: " + entry);
        }
    }

    /* access modifiers changed from: private */
    public static class EntryFuture {
        private static final Object unset = new Object();
        private Object entry;
        private final Thread owner;

        private EntryFuture() {
            this.owner = Thread.currentThread();
            this.entry = unset;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean set(Object entry2) {
            if (this.entry != unset) {
                return false;
            }
            this.entry = entry2;
            notifyAll();
            return true;
        }

        /* access modifiers changed from: package-private */
        public synchronized Object get() {
            boolean interrupted = false;
            while (this.entry == unset) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
            if (interrupted) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    /* class java.io.ObjectStreamClass.EntryFuture.AnonymousClass1 */

                    @Override // java.security.PrivilegedAction
                    public Void run() {
                        Thread.currentThread().interrupt();
                        return null;
                    }
                });
            }
            return this.entry;
        }

        /* access modifiers changed from: package-private */
        public Thread getOwner() {
            return this.owner;
        }
    }

    private ObjectStreamClass(final Class<?> cl2) {
        this.cl = cl2;
        this.name = cl2.getName();
        this.isProxy = Proxy.isProxyClass(cl2);
        this.isEnum = Enum.class.isAssignableFrom(cl2);
        this.serializable = Serializable.class.isAssignableFrom(cl2);
        this.externalizable = Externalizable.class.isAssignableFrom(cl2);
        Class<?> superCl = cl2.getSuperclass();
        this.superDesc = superCl != null ? lookup(superCl, false) : null;
        this.localDesc = this;
        if (this.serializable) {
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                /* class java.io.ObjectStreamClass.AnonymousClass2 */

                @Override // java.security.PrivilegedAction
                public Void run() {
                    if (ObjectStreamClass.this.isEnum) {
                        ObjectStreamClass.this.suid = 0L;
                        ObjectStreamClass.this.fields = ObjectStreamClass.NO_FIELDS;
                        return null;
                    } else if (cl2.isArray()) {
                        ObjectStreamClass.this.fields = ObjectStreamClass.NO_FIELDS;
                        return null;
                    } else {
                        ObjectStreamClass.this.suid = ObjectStreamClass.getDeclaredSUID(cl2);
                        try {
                            ObjectStreamClass.this.fields = ObjectStreamClass.getSerialFields(cl2);
                            ObjectStreamClass.this.computeFieldOffsets();
                        } catch (InvalidClassException e) {
                            ObjectStreamClass objectStreamClass = ObjectStreamClass.this;
                            objectStreamClass.serializeEx = objectStreamClass.deserializeEx = new ExceptionInfo(e.classname, e.getMessage());
                            ObjectStreamClass.this.fields = ObjectStreamClass.NO_FIELDS;
                        }
                        if (ObjectStreamClass.this.externalizable) {
                            ObjectStreamClass.this.cons = ObjectStreamClass.getExternalizableConstructor(cl2);
                        } else {
                            ObjectStreamClass.this.cons = ObjectStreamClass.getSerializableConstructor(cl2);
                            boolean z = true;
                            ObjectStreamClass.this.writeObjectMethod = ObjectStreamClass.getPrivateMethod(cl2, "writeObject", new Class[]{ObjectOutputStream.class}, Void.TYPE);
                            ObjectStreamClass.this.readObjectMethod = ObjectStreamClass.getPrivateMethod(cl2, "readObject", new Class[]{ObjectInputStream.class}, Void.TYPE);
                            ObjectStreamClass.this.readObjectNoDataMethod = ObjectStreamClass.getPrivateMethod(cl2, "readObjectNoData", null, Void.TYPE);
                            ObjectStreamClass objectStreamClass2 = ObjectStreamClass.this;
                            if (objectStreamClass2.writeObjectMethod == null) {
                                z = false;
                            }
                            objectStreamClass2.hasWriteObjectData = z;
                        }
                        ObjectStreamClass.this.writeReplaceMethod = ObjectStreamClass.getInheritableMethod(cl2, "writeReplace", null, Object.class);
                        ObjectStreamClass.this.readResolveMethod = ObjectStreamClass.getInheritableMethod(cl2, "readResolve", null, Object.class);
                        return null;
                    }
                }
            });
        } else {
            this.suid = 0L;
            this.fields = NO_FIELDS;
        }
        try {
            this.fieldRefl = getReflector(this.fields, this);
            if (this.deserializeEx == null) {
                if (this.isEnum) {
                    this.deserializeEx = new ExceptionInfo(this.name, "enum type");
                } else if (this.cons == null) {
                    this.deserializeEx = new ExceptionInfo(this.name, "no valid constructor");
                }
            }
            int i = 0;
            while (true) {
                ObjectStreamField[] objectStreamFieldArr = this.fields;
                if (i < objectStreamFieldArr.length) {
                    if (objectStreamFieldArr[i].getField() == null) {
                        this.defaultSerializeEx = new ExceptionInfo(this.name, "unmatched serializable field(s) declared");
                    }
                    i++;
                } else {
                    this.initialized = true;
                    return;
                }
            }
        } catch (InvalidClassException ex) {
            throw new InternalError(ex);
        }
    }

    ObjectStreamClass() {
    }

    /* access modifiers changed from: package-private */
    public void initProxy(Class<?> cl2, ClassNotFoundException resolveEx2, ObjectStreamClass superDesc2) throws InvalidClassException {
        ObjectStreamClass osc = null;
        if (cl2 != null) {
            osc = lookup(cl2, true);
            if (!osc.isProxy) {
                throw new InvalidClassException("cannot bind proxy descriptor to a non-proxy class");
            }
        }
        this.cl = cl2;
        this.resolveEx = resolveEx2;
        this.superDesc = superDesc2;
        this.isProxy = true;
        this.serializable = true;
        this.suid = 0L;
        this.fields = NO_FIELDS;
        if (osc != null) {
            this.localDesc = osc;
            ObjectStreamClass objectStreamClass = this.localDesc;
            this.name = objectStreamClass.name;
            this.externalizable = objectStreamClass.externalizable;
            this.writeReplaceMethod = objectStreamClass.writeReplaceMethod;
            this.readResolveMethod = objectStreamClass.readResolveMethod;
            this.deserializeEx = objectStreamClass.deserializeEx;
            this.cons = objectStreamClass.cons;
        }
        this.fieldRefl = getReflector(this.fields, this.localDesc);
        this.initialized = true;
    }

    /* access modifiers changed from: package-private */
    public void initNonProxy(ObjectStreamClass model, Class<?> cl2, ClassNotFoundException resolveEx2, ObjectStreamClass superDesc2) throws InvalidClassException {
        boolean z;
        String str;
        long suid2 = Long.valueOf(model.getSerialVersionUID()).longValue();
        ObjectStreamClass osc = null;
        if (cl2 != null) {
            osc = lookup(cl2, true);
            if (!osc.isProxy) {
                boolean z2 = model.isEnum;
                if (z2 != osc.isEnum) {
                    if (z2) {
                        str = "cannot bind enum descriptor to a non-enum class";
                    } else {
                        str = "cannot bind non-enum descriptor to an enum class";
                    }
                    throw new InvalidClassException(str);
                } else if (model.serializable == osc.serializable && !cl2.isArray() && suid2 != osc.getSerialVersionUID()) {
                    String str2 = osc.name;
                    throw new InvalidClassException(str2, "local class incompatible: stream classdesc serialVersionUID = " + suid2 + ", local class serialVersionUID = " + osc.getSerialVersionUID());
                } else if (!classNamesEqual(model.name, osc.name)) {
                    String str3 = osc.name;
                    throw new InvalidClassException(str3, "local class name incompatible with stream class name \"" + model.name + "\"");
                } else if (!model.isEnum) {
                    if (model.serializable != osc.serializable || model.externalizable == osc.externalizable) {
                        boolean z3 = model.serializable;
                        if (!(z3 == osc.serializable && (z = model.externalizable) == osc.externalizable && (z3 || z))) {
                            this.deserializeEx = new ExceptionInfo(osc.name, "class invalid for deserialization");
                        }
                    } else {
                        throw new InvalidClassException(osc.name, "Serializable incompatible with Externalizable");
                    }
                }
            } else {
                throw new InvalidClassException("cannot bind non-proxy descriptor to a proxy class");
            }
        }
        this.cl = cl2;
        this.resolveEx = resolveEx2;
        this.superDesc = superDesc2;
        this.name = model.name;
        this.suid = Long.valueOf(suid2);
        this.isProxy = false;
        this.isEnum = model.isEnum;
        this.serializable = model.serializable;
        this.externalizable = model.externalizable;
        this.hasBlockExternalData = model.hasBlockExternalData;
        this.hasWriteObjectData = model.hasWriteObjectData;
        this.fields = model.fields;
        this.primDataSize = model.primDataSize;
        this.numObjFields = model.numObjFields;
        if (osc != null) {
            this.localDesc = osc;
            ObjectStreamClass objectStreamClass = this.localDesc;
            this.writeObjectMethod = objectStreamClass.writeObjectMethod;
            this.readObjectMethod = objectStreamClass.readObjectMethod;
            this.readObjectNoDataMethod = objectStreamClass.readObjectNoDataMethod;
            this.writeReplaceMethod = objectStreamClass.writeReplaceMethod;
            this.readResolveMethod = objectStreamClass.readResolveMethod;
            if (this.deserializeEx == null) {
                this.deserializeEx = objectStreamClass.deserializeEx;
            }
            this.cons = this.localDesc.cons;
        }
        this.fieldRefl = getReflector(this.fields, this.localDesc);
        this.fields = this.fieldRefl.getFields();
        this.initialized = true;
    }

    /* access modifiers changed from: package-private */
    public void readNonProxy(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.suid = Long.valueOf(in.readLong());
        this.isProxy = false;
        byte flags = in.readByte();
        this.hasWriteObjectData = (flags & 1) != 0;
        this.hasBlockExternalData = (flags & 8) != 0;
        this.externalizable = (flags & 4) != 0;
        boolean sflag = (flags & 2) != 0;
        if (!this.externalizable || !sflag) {
            this.serializable = this.externalizable || sflag;
            this.isEnum = (flags & 16) != 0;
            if (!this.isEnum || this.suid.longValue() == 0) {
                int numFields = in.readShort();
                if (!this.isEnum || numFields == 0) {
                    this.fields = numFields > 0 ? new ObjectStreamField[numFields] : NO_FIELDS;
                    for (int i = 0; i < numFields; i++) {
                        char tcode = (char) in.readByte();
                        String fname = in.readUTF();
                        try {
                            this.fields[i] = new ObjectStreamField(fname, (tcode == 'L' || tcode == '[') ? in.readTypeString() : new String(new char[]{tcode}), false);
                        } catch (RuntimeException e) {
                            String str = this.name;
                            throw ((IOException) new InvalidClassException(str, "invalid descriptor for field " + fname).initCause(e));
                        }
                    }
                    computeFieldOffsets();
                    return;
                }
                String str2 = this.name;
                throw new InvalidClassException(str2, "enum descriptor has non-zero field count: " + numFields);
            }
            String str3 = this.name;
            throw new InvalidClassException(str3, "enum descriptor has non-zero serialVersionUID: " + ((Object) this.suid));
        }
        throw new InvalidClassException(this.name, "serializable and externalizable flags conflict");
    }

    /* access modifiers changed from: package-private */
    public void writeNonProxy(ObjectOutputStream out) throws IOException {
        out.writeUTF(this.name);
        out.writeLong(getSerialVersionUID());
        byte flags = 0;
        if (this.externalizable) {
            flags = (byte) (0 | 4);
            if (out.getProtocolVersion() != 1) {
                flags = (byte) (flags | 8);
            }
        } else if (this.serializable) {
            flags = (byte) (0 | 2);
        }
        if (this.hasWriteObjectData) {
            flags = (byte) (flags | 1);
        }
        if (this.isEnum) {
            flags = (byte) (flags | 16);
        }
        out.writeByte(flags);
        out.writeShort(this.fields.length);
        int i = 0;
        while (true) {
            ObjectStreamField[] objectStreamFieldArr = this.fields;
            if (i < objectStreamFieldArr.length) {
                ObjectStreamField f = objectStreamFieldArr[i];
                out.writeByte(f.getTypeCode());
                out.writeUTF(f.getName());
                if (!f.isPrimitive()) {
                    out.writeTypeString(f.getTypeString());
                }
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ClassNotFoundException getResolveException() {
        return this.resolveEx;
    }

    private final void requireInitialized() {
        if (!this.initialized) {
            throw new InternalError("Unexpected call when not initialized");
        }
    }

    /* access modifiers changed from: package-private */
    public void checkDeserialize() throws InvalidClassException {
        requireInitialized();
        ExceptionInfo exceptionInfo = this.deserializeEx;
        if (exceptionInfo != null) {
            throw exceptionInfo.newInvalidClassException();
        }
    }

    /* access modifiers changed from: package-private */
    public void checkSerialize() throws InvalidClassException {
        requireInitialized();
        ExceptionInfo exceptionInfo = this.serializeEx;
        if (exceptionInfo != null) {
            throw exceptionInfo.newInvalidClassException();
        }
    }

    /* access modifiers changed from: package-private */
    public void checkDefaultSerialize() throws InvalidClassException {
        requireInitialized();
        ExceptionInfo exceptionInfo = this.defaultSerializeEx;
        if (exceptionInfo != null) {
            throw exceptionInfo.newInvalidClassException();
        }
    }

    /* access modifiers changed from: package-private */
    public ObjectStreamClass getSuperDesc() {
        requireInitialized();
        return this.superDesc;
    }

    /* access modifiers changed from: package-private */
    public ObjectStreamClass getLocalDesc() {
        requireInitialized();
        return this.localDesc;
    }

    /* access modifiers changed from: package-private */
    public ObjectStreamField[] getFields(boolean copy) {
        ObjectStreamField[] objectStreamFieldArr = this.fields;
        return copy ? (ObjectStreamField[]) objectStreamFieldArr.clone() : objectStreamFieldArr;
    }

    /* access modifiers changed from: package-private */
    public ObjectStreamField getField(String name2, Class<?> type) {
        ObjectStreamField f;
        int i = 0;
        while (true) {
            ObjectStreamField[] objectStreamFieldArr = this.fields;
            if (i >= objectStreamFieldArr.length) {
                return null;
            }
            f = objectStreamFieldArr[i];
            if (f.getName().equals(name2)) {
                if (type == null || (type == Object.class && !f.isPrimitive())) {
                    return f;
                }
                Class<?> ftype = f.getType();
                if (ftype != null && type.isAssignableFrom(ftype)) {
                    return f;
                }
            }
            i++;
        }
        return f;
    }

    /* access modifiers changed from: package-private */
    public boolean isProxy() {
        requireInitialized();
        return this.isProxy;
    }

    /* access modifiers changed from: package-private */
    public boolean isEnum() {
        requireInitialized();
        return this.isEnum;
    }

    /* access modifiers changed from: package-private */
    public boolean isExternalizable() {
        requireInitialized();
        return this.externalizable;
    }

    /* access modifiers changed from: package-private */
    public boolean isSerializable() {
        requireInitialized();
        return this.serializable;
    }

    /* access modifiers changed from: package-private */
    public boolean hasBlockExternalData() {
        requireInitialized();
        return this.hasBlockExternalData;
    }

    /* access modifiers changed from: package-private */
    public boolean hasWriteObjectData() {
        requireInitialized();
        return this.hasWriteObjectData;
    }

    /* access modifiers changed from: package-private */
    public boolean isInstantiable() {
        requireInitialized();
        return this.cons != null;
    }

    /* access modifiers changed from: package-private */
    public boolean hasWriteObjectMethod() {
        requireInitialized();
        return this.writeObjectMethod != null;
    }

    /* access modifiers changed from: package-private */
    public boolean hasReadObjectMethod() {
        requireInitialized();
        return this.readObjectMethod != null;
    }

    /* access modifiers changed from: package-private */
    public boolean hasReadObjectNoDataMethod() {
        requireInitialized();
        return this.readObjectNoDataMethod != null;
    }

    /* access modifiers changed from: package-private */
    public boolean hasWriteReplaceMethod() {
        requireInitialized();
        return this.writeReplaceMethod != null;
    }

    /* access modifiers changed from: package-private */
    public boolean hasReadResolveMethod() {
        requireInitialized();
        return this.readResolveMethod != null;
    }

    /* access modifiers changed from: package-private */
    public Object newInstance() throws InstantiationException, InvocationTargetException, UnsupportedOperationException {
        requireInitialized();
        Constructor<?> constructor = this.cons;
        if (constructor != null) {
            try {
                return constructor.newInstance(new Object[0]);
            } catch (IllegalAccessException ex) {
                throw new InternalError(ex);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeWriteObject(Object obj, ObjectOutputStream out) throws IOException, UnsupportedOperationException {
        requireInitialized();
        Method method = this.writeObjectMethod;
        if (method != null) {
            try {
                method.invoke(obj, out);
            } catch (InvocationTargetException ex) {
                Throwable th = ex.getTargetException();
                if (!(th instanceof IOException)) {
                    throwMiscException(th);
                    return;
                }
                throw ((IOException) th);
            } catch (IllegalAccessException ex2) {
                throw new InternalError(ex2);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeReadObject(Object obj, ObjectInputStream in) throws ClassNotFoundException, IOException, UnsupportedOperationException {
        requireInitialized();
        Method method = this.readObjectMethod;
        if (method != null) {
            try {
                method.invoke(obj, in);
            } catch (InvocationTargetException ex) {
                Throwable th = ex.getTargetException();
                if (th instanceof ClassNotFoundException) {
                    throw ((ClassNotFoundException) th);
                } else if (!(th instanceof IOException)) {
                    throwMiscException(th);
                } else {
                    throw ((IOException) th);
                }
            } catch (IllegalAccessException ex2) {
                throw new InternalError(ex2);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public void invokeReadObjectNoData(Object obj) throws IOException, UnsupportedOperationException {
        requireInitialized();
        Method method = this.readObjectNoDataMethod;
        if (method != null) {
            try {
                method.invoke(obj, null);
            } catch (InvocationTargetException ex) {
                Throwable th = ex.getTargetException();
                if (!(th instanceof ObjectStreamException)) {
                    throwMiscException(th);
                    return;
                }
                throw ((ObjectStreamException) th);
            } catch (IllegalAccessException ex2) {
                throw new InternalError(ex2);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public Object invokeWriteReplace(Object obj) throws IOException, UnsupportedOperationException {
        requireInitialized();
        Method method = this.writeReplaceMethod;
        if (method != null) {
            try {
                return method.invoke(obj, null);
            } catch (InvocationTargetException ex) {
                Throwable th = ex.getTargetException();
                if (th instanceof ObjectStreamException) {
                    throw ((ObjectStreamException) th);
                }
                throwMiscException(th);
                throw new InternalError(th);
            } catch (IllegalAccessException ex2) {
                throw new InternalError(ex2);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public Object invokeReadResolve(Object obj) throws IOException, UnsupportedOperationException {
        requireInitialized();
        Method method = this.readResolveMethod;
        if (method != null) {
            try {
                return method.invoke(obj, null);
            } catch (InvocationTargetException ex) {
                Throwable th = ex.getTargetException();
                if (th instanceof ObjectStreamException) {
                    throw ((ObjectStreamException) th);
                }
                throwMiscException(th);
                throw new InternalError(th);
            } catch (IllegalAccessException ex2) {
                throw new InternalError(ex2);
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: package-private */
    public static class ClassDataSlot {
        final ObjectStreamClass desc;
        final boolean hasData;

        ClassDataSlot(ObjectStreamClass desc2, boolean hasData2) {
            this.desc = desc2;
            this.hasData = hasData2;
        }
    }

    /* access modifiers changed from: package-private */
    public ClassDataSlot[] getClassDataLayout() throws InvalidClassException {
        if (this.dataLayout == null) {
            this.dataLayout = getClassDataLayout0();
        }
        return this.dataLayout;
    }

    private ClassDataSlot[] getClassDataLayout0() throws InvalidClassException {
        ArrayList<ClassDataSlot> slots = new ArrayList<>();
        Class<?> start = this.cl;
        Class<?> end = this.cl;
        while (end != null && Serializable.class.isAssignableFrom(end)) {
            end = end.getSuperclass();
        }
        HashSet<String> oscNames = new HashSet<>(3);
        for (ObjectStreamClass d = this; d != null; d = d.superDesc) {
            if (!oscNames.contains(d.name)) {
                oscNames.add(d.name);
                Class<?> cls = d.cl;
                String searchName = cls != null ? cls.getName() : d.name;
                Class<?> match = null;
                Class<?> c = start;
                while (true) {
                    if (c == end) {
                        break;
                    } else if (searchName.equals(c.getName())) {
                        match = c;
                        break;
                    } else {
                        c = c.getSuperclass();
                    }
                }
                if (match != null) {
                    for (Class<?> c2 = start; c2 != match; c2 = c2.getSuperclass()) {
                        slots.add(new ClassDataSlot(lookup(c2, true), false));
                    }
                    start = match.getSuperclass();
                }
                slots.add(new ClassDataSlot(d.getVariantFor(match), true));
            } else {
                throw new InvalidClassException("Circular reference.");
            }
        }
        for (Class<?> c3 = start; c3 != end; c3 = c3.getSuperclass()) {
            slots.add(new ClassDataSlot(lookup(c3, true), false));
        }
        Collections.reverse(slots);
        return (ClassDataSlot[]) slots.toArray(new ClassDataSlot[slots.size()]);
    }

    /* access modifiers changed from: package-private */
    public int getPrimDataSize() {
        return this.primDataSize;
    }

    /* access modifiers changed from: package-private */
    public int getNumObjFields() {
        return this.numObjFields;
    }

    /* access modifiers changed from: package-private */
    public void getPrimFieldValues(Object obj, byte[] buf) {
        this.fieldRefl.getPrimFieldValues(obj, buf);
    }

    /* access modifiers changed from: package-private */
    public void setPrimFieldValues(Object obj, byte[] buf) {
        this.fieldRefl.setPrimFieldValues(obj, buf);
    }

    /* access modifiers changed from: package-private */
    public void getObjFieldValues(Object obj, Object[] vals) {
        this.fieldRefl.getObjFieldValues(obj, vals);
    }

    /* access modifiers changed from: package-private */
    public void setObjFieldValues(Object obj, Object[] vals) {
        this.fieldRefl.setObjFieldValues(obj, vals);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void computeFieldOffsets() throws InvalidClassException {
        this.primDataSize = 0;
        this.numObjFields = 0;
        int firstObjIndex = -1;
        int i = 0;
        while (true) {
            ObjectStreamField[] objectStreamFieldArr = this.fields;
            if (i < objectStreamFieldArr.length) {
                ObjectStreamField f = objectStreamFieldArr[i];
                char typeCode = f.getTypeCode();
                if (typeCode != 'F') {
                    if (typeCode != 'L') {
                        if (typeCode != 'S') {
                            if (typeCode != 'I') {
                                if (typeCode != 'J') {
                                    if (typeCode != 'Z') {
                                        if (typeCode != '[') {
                                            switch (typeCode) {
                                                case 'B':
                                                    break;
                                                case 'C':
                                                    break;
                                                case 'D':
                                                    break;
                                                default:
                                                    throw new InternalError();
                                            }
                                            i++;
                                        }
                                    }
                                    int i2 = this.primDataSize;
                                    this.primDataSize = i2 + 1;
                                    f.setOffset(i2);
                                    i++;
                                }
                                f.setOffset(this.primDataSize);
                                this.primDataSize += 8;
                                i++;
                            }
                        }
                        f.setOffset(this.primDataSize);
                        this.primDataSize += 2;
                        i++;
                    }
                    int i3 = this.numObjFields;
                    this.numObjFields = i3 + 1;
                    f.setOffset(i3);
                    if (firstObjIndex == -1) {
                        firstObjIndex = i;
                    }
                    i++;
                }
                f.setOffset(this.primDataSize);
                this.primDataSize += 4;
                i++;
            } else if (firstObjIndex != -1 && this.numObjFields + firstObjIndex != objectStreamFieldArr.length) {
                throw new InvalidClassException(this.name, "illegal field order");
            } else {
                return;
            }
        }
    }

    private ObjectStreamClass getVariantFor(Class<?> cl2) throws InvalidClassException {
        if (this.cl == cl2) {
            return this;
        }
        ObjectStreamClass desc = new ObjectStreamClass();
        if (this.isProxy) {
            desc.initProxy(cl2, null, this.superDesc);
        } else {
            desc.initNonProxy(this, cl2, null, this.superDesc);
        }
        return desc;
    }

    /* access modifiers changed from: private */
    public static Constructor<?> getExternalizableConstructor(Class<?> cl2) {
        try {
            Constructor<?> cons2 = cl2.getDeclaredConstructor(null);
            cons2.setAccessible(true);
            if ((1 & cons2.getModifiers()) != 0) {
                return cons2;
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static Constructor<?> getSerializableConstructor(Class<?> cl2) {
        Class<?> initCl = cl2;
        while (Serializable.class.isAssignableFrom(initCl)) {
            Class<? super Object> superclass = initCl.getSuperclass();
            initCl = superclass;
            if (superclass == null) {
                return null;
            }
        }
        try {
            Constructor<?> cons2 = initCl.getDeclaredConstructor(null);
            int mods = cons2.getModifiers();
            if ((mods & 2) == 0) {
                if ((mods & 5) != 0 || packageEquals(cl2, initCl)) {
                    if (cons2.getDeclaringClass() != cl2) {
                        cons2 = cons2.serializationCopy(cons2.getDeclaringClass(), cl2);
                    }
                    cons2.setAccessible(true);
                    return cons2;
                }
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static Method getInheritableMethod(Class<?> cl2, String name2, Class<?>[] argTypes, Class<?> returnType) {
        Method meth = null;
        Class<?> defCl = cl2;
        while (true) {
            if (defCl == null) {
                break;
            }
            try {
                meth = defCl.getDeclaredMethod(name2, argTypes);
                break;
            } catch (NoSuchMethodException e) {
                defCl = defCl.getSuperclass();
            }
        }
        if (meth == null || meth.getReturnType() != returnType) {
            return null;
        }
        meth.setAccessible(true);
        int mods = meth.getModifiers();
        if ((mods & 1032) != 0) {
            return null;
        }
        if ((mods & 5) != 0) {
            return meth;
        }
        if ((mods & 2) != 0) {
            if (cl2 == defCl) {
                return meth;
            }
            return null;
        } else if (packageEquals(cl2, defCl)) {
            return meth;
        } else {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static Method getPrivateMethod(Class<?> cl2, String name2, Class<?>[] argTypes, Class<?> returnType) {
        try {
            Method meth = cl2.getDeclaredMethod(name2, argTypes);
            meth.setAccessible(true);
            int mods = meth.getModifiers();
            if (meth.getReturnType() == returnType && (mods & 8) == 0 && (mods & 2) != 0) {
                return meth;
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static boolean packageEquals(Class<?> cl1, Class<?> cl2) {
        return cl1.getClassLoader() == cl2.getClassLoader() && getPackageName(cl1).equals(getPackageName(cl2));
    }

    private static String getPackageName(Class<?> cl2) {
        String s = cl2.getName();
        int i = s.lastIndexOf(91);
        if (i >= 0) {
            s = s.substring(i + 2);
        }
        int i2 = s.lastIndexOf(46);
        return i2 >= 0 ? s.substring(0, i2) : "";
    }

    private static boolean classNamesEqual(String name1, String name2) {
        return name1.substring(name1.lastIndexOf(46) + 1).equals(name2.substring(name2.lastIndexOf(46) + 1));
    }

    /* access modifiers changed from: private */
    public static String getClassSignature(Class<?> cl2) {
        StringBuilder sbuf = new StringBuilder();
        while (cl2.isArray()) {
            sbuf.append('[');
            cl2 = cl2.getComponentType();
        }
        if (!cl2.isPrimitive()) {
            sbuf.append('L' + cl2.getName().replace('.', '/') + ';');
        } else if (cl2 == Integer.TYPE) {
            sbuf.append('I');
        } else if (cl2 == Byte.TYPE) {
            sbuf.append('B');
        } else if (cl2 == Long.TYPE) {
            sbuf.append('J');
        } else if (cl2 == Float.TYPE) {
            sbuf.append('F');
        } else if (cl2 == Double.TYPE) {
            sbuf.append('D');
        } else if (cl2 == Short.TYPE) {
            sbuf.append('S');
        } else if (cl2 == Character.TYPE) {
            sbuf.append('C');
        } else if (cl2 == Boolean.TYPE) {
            sbuf.append('Z');
        } else if (cl2 == Void.TYPE) {
            sbuf.append('V');
        } else {
            throw new InternalError();
        }
        return sbuf.toString();
    }

    /* access modifiers changed from: private */
    public static String getMethodSignature(Class<?>[] paramTypes, Class<?> retType) {
        StringBuilder sbuf = new StringBuilder();
        sbuf.append('(');
        for (Class<?> cls : paramTypes) {
            sbuf.append(getClassSignature(cls));
        }
        sbuf.append(')');
        sbuf.append(getClassSignature(retType));
        return sbuf.toString();
    }

    private static void throwMiscException(Throwable th) throws IOException {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            IOException ex = new IOException("unexpected exception type");
            ex.initCause(th);
            throw ex;
        }
    }

    /* access modifiers changed from: private */
    public static ObjectStreamField[] getSerialFields(Class<?> cl2) throws InvalidClassException {
        if (!Serializable.class.isAssignableFrom(cl2) || Externalizable.class.isAssignableFrom(cl2) || Proxy.isProxyClass(cl2) || cl2.isInterface()) {
            return NO_FIELDS;
        }
        ObjectStreamField[] declaredSerialFields = getDeclaredSerialFields(cl2);
        ObjectStreamField[] fields2 = declaredSerialFields;
        if (declaredSerialFields == null) {
            fields2 = getDefaultSerialFields(cl2);
        }
        Arrays.sort(fields2);
        return fields2;
    }

    private static ObjectStreamField[] getDeclaredSerialFields(Class<?> cl2) throws InvalidClassException {
        ObjectStreamField[] serialPersistentFields2 = null;
        try {
            Field f = cl2.getDeclaredField("serialPersistentFields");
            if ((f.getModifiers() & 26) == 26) {
                f.setAccessible(true);
                serialPersistentFields2 = (ObjectStreamField[]) f.get(null);
            }
        } catch (Exception e) {
        }
        if (serialPersistentFields2 == null) {
            return null;
        }
        if (serialPersistentFields2.length == 0) {
            return NO_FIELDS;
        }
        ObjectStreamField[] boundFields = new ObjectStreamField[serialPersistentFields2.length];
        Set<String> fieldNames = new HashSet<>(serialPersistentFields2.length);
        for (int i = 0; i < serialPersistentFields2.length; i++) {
            ObjectStreamField spf = serialPersistentFields2[i];
            String fname = spf.getName();
            if (!fieldNames.contains(fname)) {
                fieldNames.add(fname);
                try {
                    Field f2 = cl2.getDeclaredField(fname);
                    if (f2.getType() == spf.getType() && (f2.getModifiers() & 8) == 0) {
                        boundFields[i] = new ObjectStreamField(f2, spf.isUnshared(), true);
                    }
                } catch (NoSuchFieldException e2) {
                }
                if (boundFields[i] == null) {
                    boundFields[i] = new ObjectStreamField(fname, spf.getType(), spf.isUnshared());
                }
            } else {
                throw new InvalidClassException("multiple serializable fields named " + fname);
            }
        }
        return boundFields;
    }

    private static ObjectStreamField[] getDefaultSerialFields(Class<?> cl2) {
        Field[] clFields = cl2.getDeclaredFields();
        ArrayList<ObjectStreamField> list = new ArrayList<>();
        for (int i = 0; i < clFields.length; i++) {
            if ((clFields[i].getModifiers() & 136) == 0) {
                list.add(new ObjectStreamField(clFields[i], false, true));
            }
        }
        int size = list.size();
        if (size == 0) {
            return NO_FIELDS;
        }
        return (ObjectStreamField[]) list.toArray(new ObjectStreamField[size]);
    }

    /* access modifiers changed from: private */
    public static Long getDeclaredSUID(Class<?> cl2) {
        try {
            Field f = cl2.getDeclaredField("serialVersionUID");
            if ((f.getModifiers() & 24) == 24) {
                f.setAccessible(true);
                return Long.valueOf(f.getLong(null));
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static long computeDefaultSUID(Class<?> cl2) {
        MemberSignature[] methSigs;
        int classMods;
        int i;
        if (!Serializable.class.isAssignableFrom(cl2) || Proxy.isProxyClass(cl2)) {
            return 0;
        }
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            DataOutputStream dout = new DataOutputStream(bout);
            dout.writeUTF(cl2.getName());
            int classMods2 = cl2.getModifiers() & 1553;
            Method[] methods = cl2.getDeclaredMethods();
            if ((classMods2 & 512) != 0) {
                if (methods.length > 0) {
                    i = classMods2 | 1024;
                } else {
                    i = classMods2 & -1025;
                }
                classMods2 = i;
            }
            dout.writeInt(classMods2);
            if (!cl2.isArray()) {
                Class<?>[] interfaces = cl2.getInterfaces();
                String[] ifaceNames = new String[interfaces.length];
                for (int i2 = 0; i2 < interfaces.length; i2++) {
                    ifaceNames[i2] = interfaces[i2].getName();
                }
                Arrays.sort(ifaceNames);
                for (String str : ifaceNames) {
                    dout.writeUTF(str);
                }
            }
            Field[] fields2 = cl2.getDeclaredFields();
            MemberSignature[] fieldSigs = new MemberSignature[fields2.length];
            for (int i3 = 0; i3 < fields2.length; i3++) {
                fieldSigs[i3] = new MemberSignature(fields2[i3]);
            }
            Arrays.sort(fieldSigs, new Comparator<MemberSignature>() {
                /* class java.io.ObjectStreamClass.AnonymousClass3 */

                public int compare(MemberSignature ms1, MemberSignature ms2) {
                    return ms1.name.compareTo(ms2.name);
                }
            });
            for (MemberSignature sig : fieldSigs) {
                int mods = sig.member.getModifiers() & 223;
                if ((mods & 2) == 0 || (mods & 136) == 0) {
                    dout.writeUTF(sig.name);
                    dout.writeInt(mods);
                    dout.writeUTF(sig.signature);
                }
            }
            boolean inheritStaticInitializer = VMRuntime.getRuntime().getTargetSdkVersion() <= 23;
            boolean warnIncompatibleSUIDChange = false;
            if (hasStaticInitializer(cl2, inheritStaticInitializer)) {
                if (inheritStaticInitializer && !hasStaticInitializer(cl2, false)) {
                    warnIncompatibleSUIDChange = true;
                }
                dout.writeUTF("<clinit>");
                dout.writeInt(8);
                dout.writeUTF("()V");
            }
            Constructor<?>[] cons2 = cl2.getDeclaredConstructors();
            MemberSignature[] consSigs = new MemberSignature[cons2.length];
            for (int i4 = 0; i4 < cons2.length; i4++) {
                consSigs[i4] = new MemberSignature(cons2[i4]);
            }
            Arrays.sort(consSigs, new Comparator<MemberSignature>() {
                /* class java.io.ObjectStreamClass.AnonymousClass4 */

                public int compare(MemberSignature ms1, MemberSignature ms2) {
                    return ms1.signature.compareTo(ms2.signature);
                }
            });
            int i5 = 0;
            while (i5 < consSigs.length) {
                MemberSignature sig2 = consSigs[i5];
                int mods2 = sig2.member.getModifiers() & 3391;
                if ((mods2 & 2) == 0) {
                    dout.writeUTF("<init>");
                    dout.writeInt(mods2);
                    classMods = classMods2;
                    dout.writeUTF(sig2.signature.replace('/', '.'));
                } else {
                    classMods = classMods2;
                }
                i5++;
                classMods2 = classMods;
            }
            MemberSignature[] methSigs2 = new MemberSignature[methods.length];
            for (int i6 = 0; i6 < methods.length; i6++) {
                methSigs2[i6] = new MemberSignature(methods[i6]);
            }
            Arrays.sort(methSigs2, new Comparator<MemberSignature>() {
                /* class java.io.ObjectStreamClass.AnonymousClass5 */

                public int compare(MemberSignature ms1, MemberSignature ms2) {
                    int comp = ms1.name.compareTo(ms2.name);
                    if (comp == 0) {
                        return ms1.signature.compareTo(ms2.signature);
                    }
                    return comp;
                }
            });
            int i7 = 0;
            while (i7 < methSigs2.length) {
                MemberSignature sig3 = methSigs2[i7];
                int mods3 = sig3.member.getModifiers() & 3391;
                if ((mods3 & 2) == 0) {
                    dout.writeUTF(sig3.name);
                    dout.writeInt(mods3);
                    methSigs = methSigs2;
                    dout.writeUTF(sig3.signature.replace('/', '.'));
                } else {
                    methSigs = methSigs2;
                }
                i7++;
                methSigs2 = methSigs;
            }
            dout.flush();
            MessageDigest md = MessageDigest.getInstance("SHA");
            byte[] hashBytes = md.digest(bout.toByteArray());
            long hash = 0;
            char c = '\b';
            int i8 = Math.min(hashBytes.length, 8) - 1;
            while (i8 >= 0) {
                hash = (hash << c) | ((long) (hashBytes[i8] & 255));
                i8--;
                dout = dout;
                md = md;
                c = '\b';
            }
            if (warnIncompatibleSUIDChange) {
                suidCompatibilityListener.warnDefaultSUIDTargetVersionDependent(cl2, hash);
            }
            return hash;
        } catch (IOException ex) {
            throw new InternalError(ex);
        } catch (NoSuchAlgorithmException ex2) {
            throw new SecurityException(ex2.getMessage());
        }
    }

    /* access modifiers changed from: private */
    public static class MemberSignature {
        public final Member member;
        public final String name;
        public final String signature;

        public MemberSignature(Field field) {
            this.member = field;
            this.name = field.getName();
            this.signature = ObjectStreamClass.getClassSignature(field.getType());
        }

        public MemberSignature(Constructor<?> cons) {
            this.member = cons;
            this.name = cons.getName();
            this.signature = ObjectStreamClass.getMethodSignature(cons.getParameterTypes(), Void.TYPE);
        }

        public MemberSignature(Method meth) {
            this.member = meth;
            this.name = meth.getName();
            this.signature = ObjectStreamClass.getMethodSignature(meth.getParameterTypes(), meth.getReturnType());
        }
    }

    /* access modifiers changed from: private */
    public static class FieldReflector {
        private static final Unsafe unsafe = Unsafe.getUnsafe();
        private final ObjectStreamField[] fields;
        private final int numPrimFields;
        private final int[] offsets;
        private final long[] readKeys;
        private final char[] typeCodes;
        private final Class<?>[] types;
        private final long[] writeKeys;

        FieldReflector(ObjectStreamField[] fields2) {
            this.fields = fields2;
            int nfields = fields2.length;
            this.readKeys = new long[nfields];
            this.writeKeys = new long[nfields];
            this.offsets = new int[nfields];
            this.typeCodes = new char[nfields];
            ArrayList<Class<?>> typeList = new ArrayList<>();
            Set<Long> usedKeys = new HashSet<>();
            for (int i = 0; i < nfields; i++) {
                ObjectStreamField f = fields2[i];
                Field rf = f.getField();
                long j = -1;
                long key = rf != null ? unsafe.objectFieldOffset(rf) : -1;
                this.readKeys[i] = key;
                this.writeKeys[i] = usedKeys.add(Long.valueOf(key)) ? key : j;
                this.offsets[i] = f.getOffset();
                this.typeCodes[i] = f.getTypeCode();
                if (!f.isPrimitive()) {
                    typeList.add(rf != null ? rf.getType() : null);
                }
            }
            this.types = (Class[]) typeList.toArray(new Class[typeList.size()]);
            this.numPrimFields = nfields - this.types.length;
        }

        /* access modifiers changed from: package-private */
        public ObjectStreamField[] getFields() {
            return this.fields;
        }

        /* access modifiers changed from: package-private */
        public void getPrimFieldValues(Object obj, byte[] buf) {
            if (obj != null) {
                for (int i = 0; i < this.numPrimFields; i++) {
                    long key = this.readKeys[i];
                    int off = this.offsets[i];
                    char c = this.typeCodes[i];
                    if (c == 'F') {
                        Bits.putFloat(buf, off, unsafe.getFloat(obj, key));
                    } else if (c == 'S') {
                        Bits.putShort(buf, off, unsafe.getShort(obj, key));
                    } else if (c == 'Z') {
                        Bits.putBoolean(buf, off, unsafe.getBoolean(obj, key));
                    } else if (c == 'I') {
                        Bits.putInt(buf, off, unsafe.getInt(obj, key));
                    } else if (c != 'J') {
                        switch (c) {
                            case 'B':
                                buf[off] = unsafe.getByte(obj, key);
                                continue;
                            case 'C':
                                Bits.putChar(buf, off, unsafe.getChar(obj, key));
                                continue;
                            case 'D':
                                Bits.putDouble(buf, off, unsafe.getDouble(obj, key));
                                continue;
                            default:
                                throw new InternalError();
                        }
                    } else {
                        Bits.putLong(buf, off, unsafe.getLong(obj, key));
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void setPrimFieldValues(Object obj, byte[] buf) {
            if (obj != null) {
                for (int i = 0; i < this.numPrimFields; i++) {
                    long key = this.writeKeys[i];
                    if (key != -1) {
                        int off = this.offsets[i];
                        char c = this.typeCodes[i];
                        if (c == 'F') {
                            unsafe.putFloat(obj, key, Bits.getFloat(buf, off));
                        } else if (c == 'S') {
                            unsafe.putShort(obj, key, Bits.getShort(buf, off));
                        } else if (c == 'Z') {
                            unsafe.putBoolean(obj, key, Bits.getBoolean(buf, off));
                        } else if (c == 'I') {
                            unsafe.putInt(obj, key, Bits.getInt(buf, off));
                        } else if (c != 'J') {
                            switch (c) {
                                case 'B':
                                    unsafe.putByte(obj, key, buf[off]);
                                    continue;
                                case 'C':
                                    unsafe.putChar(obj, key, Bits.getChar(buf, off));
                                    continue;
                                case 'D':
                                    unsafe.putDouble(obj, key, Bits.getDouble(buf, off));
                                    continue;
                                default:
                                    throw new InternalError();
                            }
                        } else {
                            unsafe.putLong(obj, key, Bits.getLong(buf, off));
                        }
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void getObjFieldValues(Object obj, Object[] vals) {
            if (obj != null) {
                for (int i = this.numPrimFields; i < this.fields.length; i++) {
                    char c = this.typeCodes[i];
                    if (c == 'L' || c == '[') {
                        vals[this.offsets[i]] = unsafe.getObject(obj, this.readKeys[i]);
                    } else {
                        throw new InternalError();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: package-private */
        public void setObjFieldValues(Object obj, Object[] vals) {
            if (obj != null) {
                for (int i = this.numPrimFields; i < this.fields.length; i++) {
                    long key = this.writeKeys[i];
                    if (key != -1) {
                        char c = this.typeCodes[i];
                        if (c == 'L' || c == '[') {
                            Object val = vals[this.offsets[i]];
                            if (val == null || this.types[i - this.numPrimFields].isInstance(val)) {
                                unsafe.putObject(obj, key, val);
                            } else {
                                Field f = this.fields[i].getField();
                                throw new ClassCastException("cannot assign instance of " + val.getClass().getName() + " to field " + f.getDeclaringClass().getName() + "." + f.getName() + " of type " + f.getType().getName() + " in instance of " + obj.getClass().getName());
                            }
                        } else {
                            throw new InternalError();
                        }
                    }
                }
                return;
            }
            throw new NullPointerException();
        }
    }

    private static FieldReflector getReflector(ObjectStreamField[] fields2, ObjectStreamClass localDesc2) throws InvalidClassException {
        Class<?> cl2 = (localDesc2 == null || fields2.length <= 0) ? null : localDesc2.cl;
        processQueue(Caches.reflectorsQueue, Caches.reflectors);
        FieldReflectorKey key = new FieldReflectorKey(cl2, fields2, Caches.reflectorsQueue);
        Reference<?> ref = Caches.reflectors.get(key);
        Object entry = null;
        if (ref != null) {
            entry = ref.get();
        }
        EntryFuture future = null;
        if (entry == null) {
            EntryFuture newEntry = new EntryFuture();
            Reference<?> newRef = new SoftReference<>(newEntry);
            do {
                if (ref != null) {
                    Caches.reflectors.remove(key, ref);
                }
                ref = Caches.reflectors.putIfAbsent(key, newRef);
                if (ref != null) {
                    entry = ref.get();
                }
                if (ref == null) {
                    break;
                }
            } while (entry == null);
            if (entry == null) {
                future = newEntry;
            }
        }
        if (entry instanceof FieldReflector) {
            return (FieldReflector) entry;
        }
        if (entry instanceof EntryFuture) {
            entry = ((EntryFuture) entry).get();
        } else if (entry == null) {
            try {
                entry = new FieldReflector(matchFields(fields2, localDesc2));
            } catch (Throwable th) {
                entry = th;
            }
            future.set(entry);
            Caches.reflectors.put(key, new SoftReference(entry));
        }
        if (entry instanceof FieldReflector) {
            return (FieldReflector) entry;
        }
        if (entry instanceof InvalidClassException) {
            throw ((InvalidClassException) entry);
        } else if (entry instanceof RuntimeException) {
            throw ((RuntimeException) entry);
        } else if (entry instanceof Error) {
            throw ((Error) entry);
        } else {
            throw new InternalError("unexpected entry: " + entry);
        }
    }

    /* access modifiers changed from: private */
    public static class FieldReflectorKey extends WeakReference<Class<?>> {
        private final int hash;
        private final boolean nullClass;
        private final String sigs;

        FieldReflectorKey(Class<?> cl, ObjectStreamField[] fields, ReferenceQueue<Class<?>> queue) {
            super(cl, queue);
            this.nullClass = cl == null;
            StringBuilder sbuf = new StringBuilder();
            for (ObjectStreamField f : fields) {
                sbuf.append(f.getName());
                sbuf.append(f.getSignature());
            }
            this.sigs = sbuf.toString();
            this.hash = System.identityHashCode(cl) + this.sigs.hashCode();
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            Class<?> referent;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof FieldReflectorKey)) {
                return false;
            }
            FieldReflectorKey other = (FieldReflectorKey) obj;
            if (!this.nullClass ? !((referent = (Class) get()) == null || referent != other.get()) : other.nullClass) {
                if (this.sigs.equals(other.sigs)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static ObjectStreamField[] matchFields(ObjectStreamField[] fields2, ObjectStreamClass localDesc2) throws InvalidClassException {
        ObjectStreamField[] localFields;
        if (localDesc2 != null) {
            localFields = localDesc2.fields;
        } else {
            localFields = NO_FIELDS;
        }
        ObjectStreamField[] matches = new ObjectStreamField[fields2.length];
        for (int i = 0; i < fields2.length; i++) {
            ObjectStreamField f = fields2[i];
            ObjectStreamField m = null;
            for (ObjectStreamField lf : localFields) {
                if (f.getName().equals(lf.getName()) && f.getSignature().equals(lf.getSignature())) {
                    if (lf.getField() != null) {
                        m = new ObjectStreamField(lf.getField(), lf.isUnshared(), false);
                    } else {
                        m = new ObjectStreamField(lf.getName(), lf.getSignature(), lf.isUnshared());
                    }
                }
            }
            if (m == null) {
                m = new ObjectStreamField(f.getName(), f.getSignature(), false);
            }
            m.setOffset(f.getOffset());
            matches[i] = m;
        }
        return matches;
    }

    private static long getConstructorId(Class<?> cls) {
        int targetSdkVersion = VMRuntime.getRuntime().getTargetSdkVersion();
        if (targetSdkVersion <= 0 || targetSdkVersion > 24) {
            throw new UnsupportedOperationException("ObjectStreamClass.getConstructorId(Class<?>) is not supported on SDK " + targetSdkVersion);
        }
        System.logE("WARNING: ObjectStreamClass.getConstructorId(Class<?>) is private API andwill be removed in a future Android release.");
        return 1189998819991197253L;
    }

    private static Object newInstance(Class<?> clazz, long constructorId) {
        int targetSdkVersion = VMRuntime.getRuntime().getTargetSdkVersion();
        if (targetSdkVersion <= 0 || targetSdkVersion > 24) {
            throw new UnsupportedOperationException("ObjectStreamClass.newInstance(Class<?>, long) is not supported on SDK " + targetSdkVersion);
        }
        System.logE("WARNING: ObjectStreamClass.newInstance(Class<?>, long) is private API andwill be removed in a future Android release.");
        return Unsafe.getUnsafe().allocateInstance(clazz);
    }

    static void processQueue(ReferenceQueue<Class<?>> queue, ConcurrentMap<? extends WeakReference<Class<?>>, ?> map) {
        while (true) {
            Reference<? extends Class<?>> ref = queue.poll();
            if (ref != null) {
                map.remove(ref);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class WeakClassKey extends WeakReference<Class<?>> {
        private final int hash;

        WeakClassKey(Class<?> cl, ReferenceQueue<Class<?>> refQueue) {
            super(cl, refQueue);
            this.hash = System.identityHashCode(cl);
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof WeakClassKey)) {
                return false;
            }
            Object referent = get();
            if (referent == null || referent != ((WeakClassKey) obj).get()) {
                return false;
            }
            return true;
        }
    }
}
