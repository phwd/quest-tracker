package androidx.versionedparcelable;

import android.os.Parcelable;
import androidx.collection.SimpleArrayMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class VersionedParcel {
    final SimpleArrayMap<String, Class<?>> mParcelizerCache;
    final SimpleArrayMap<String, Method> mReadCache;
    final SimpleArrayMap<String, Method> mWriteCache;

    /* access modifiers changed from: protected */
    public abstract void closeField();

    /* access modifiers changed from: protected */
    public abstract VersionedParcel createSubParcel();

    /* access modifiers changed from: protected */
    public abstract boolean readBoolean();

    /* access modifiers changed from: protected */
    public abstract byte[] readByteArray();

    /* access modifiers changed from: protected */
    public abstract CharSequence readCharSequence();

    /* access modifiers changed from: protected */
    public abstract boolean readField(int i);

    /* access modifiers changed from: protected */
    public abstract int readInt();

    /* access modifiers changed from: protected */
    public abstract <T extends Parcelable> T readParcelable();

    /* access modifiers changed from: protected */
    public abstract String readString();

    /* access modifiers changed from: protected */
    public abstract void setOutputField(int i);

    /* access modifiers changed from: protected */
    public abstract void writeBoolean(boolean z);

    /* access modifiers changed from: protected */
    public abstract void writeByteArray(byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void writeCharSequence(CharSequence charSequence);

    /* access modifiers changed from: protected */
    public abstract void writeInt(int i);

    /* access modifiers changed from: protected */
    public abstract void writeParcelable(Parcelable parcelable);

    /* access modifiers changed from: protected */
    public abstract void writeString(String str);

    VersionedParcel(SimpleArrayMap<String, Method> simpleArrayMap, SimpleArrayMap<String, Method> simpleArrayMap2, SimpleArrayMap<String, Class<?>> simpleArrayMap3) {
        this.mReadCache = simpleArrayMap;
        this.mWriteCache = simpleArrayMap2;
        this.mParcelizerCache = simpleArrayMap3;
    }

    public final void writeBoolean(boolean z, int i) {
        setOutputField(i);
        writeBoolean(z);
    }

    public final void writeByteArray(byte[] bArr, int i) {
        setOutputField(2);
        writeByteArray(bArr);
    }

    public final void writeCharSequence(CharSequence charSequence, int i) {
        setOutputField(i);
        writeCharSequence(charSequence);
    }

    public final void writeInt(int i, int i2) {
        setOutputField(i2);
        writeInt(i);
    }

    public final void writeString(String str, int i) {
        setOutputField(7);
        writeString(str);
    }

    public final void writeParcelable(Parcelable parcelable, int i) {
        setOutputField(i);
        writeParcelable(parcelable);
    }

    public final boolean readBoolean(boolean z, int i) {
        if (!readField(i)) {
            return z;
        }
        return readBoolean();
    }

    public final int readInt(int i, int i2) {
        if (!readField(i2)) {
            return i;
        }
        return readInt();
    }

    public final String readString(String str, int i) {
        if (!readField(7)) {
            return str;
        }
        return readString();
    }

    public final byte[] readByteArray(byte[] bArr, int i) {
        if (!readField(2)) {
            return bArr;
        }
        return readByteArray();
    }

    public final <T extends Parcelable> T readParcelable(T t, int i) {
        return !readField(i) ? t : (T) readParcelable();
    }

    public final CharSequence readCharSequence(CharSequence charSequence, int i) {
        if (!readField(i)) {
            return charSequence;
        }
        return readCharSequence();
    }

    public final void writeVersionedParcelable(VersionedParcelable versionedParcelable, int i) {
        setOutputField(1);
        writeVersionedParcelable(versionedParcelable);
    }

    /* access modifiers changed from: protected */
    public final void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        if (versionedParcelable == null) {
            writeString(null);
            return;
        }
        writeVersionedParcelableCreator(versionedParcelable);
        VersionedParcel createSubParcel = createSubParcel();
        writeToParcel(versionedParcelable, createSubParcel);
        createSubParcel.closeField();
    }

    private void writeVersionedParcelableCreator(VersionedParcelable versionedParcelable) {
        try {
            writeString(findParcelClass(versionedParcelable.getClass()).getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(versionedParcelable.getClass().getSimpleName() + " does not have a Parcelizer", e);
        }
    }

    public final <T extends VersionedParcelable> T readVersionedParcelable(T t, int i) {
        return !readField(1) ? t : (T) readVersionedParcelable();
    }

    /* access modifiers changed from: protected */
    public final <T extends VersionedParcelable> T readVersionedParcelable() {
        String readString = readString();
        if (readString == null) {
            return null;
        }
        return (T) readFromParcel(readString, createSubParcel());
    }

    private <T extends VersionedParcelable> void writeToParcel(T t, VersionedParcel versionedParcel) {
        try {
            getWriteMethod(t.getClass()).invoke(null, t, versionedParcel);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException(e4);
        }
    }

    private Method getWriteMethod(Class<?> cls) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        Method method = this.mWriteCache.get(cls.getName());
        if (method != null) {
            return method;
        }
        Method declaredMethod = findParcelClass(cls).getDeclaredMethod("write", cls, VersionedParcel.class);
        this.mWriteCache.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    private Class<?> findParcelClass(Class<?> cls) throws ClassNotFoundException {
        Class<?> cls2 = this.mParcelizerCache.get(cls.getName());
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(String.format("%s.%sParcelizer", cls.getPackage().getName(), cls.getSimpleName()), false, cls.getClassLoader());
        this.mParcelizerCache.put(cls.getName(), cls3);
        return cls3;
    }

    private <T extends VersionedParcelable> T readFromParcel(String str, VersionedParcel versionedParcel) {
        try {
            Method method = this.mReadCache.get(str);
            if (method == null) {
                method = Class.forName(str, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", VersionedParcel.class);
                this.mReadCache.put(str, method);
            }
            return (T) ((VersionedParcelable) method.invoke(null, versionedParcel));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (ClassNotFoundException e4) {
            throw new RuntimeException(e4);
        }
    }
}
