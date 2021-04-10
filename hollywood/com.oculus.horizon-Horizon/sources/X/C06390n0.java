package X;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.0n0  reason: invalid class name and case insensitive filesystem */
public final class C06390n0<K, V> extends LinkedHashMap<K, V> implements Serializable {
    public static final long serialVersionUID = 1;
    public transient int A00;
    public final int _maxEntries;

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.A00);
    }

    public Object readResolve() {
        int i = this.A00;
        return new C06390n0(i, i);
    }

    public C06390n0(int i, int i2) {
        super(i, 0.8f, true);
        this._maxEntries = i2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        this.A00 = objectInputStream.readInt();
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry<K, V> entry) {
        if (size() > this._maxEntries) {
            return true;
        }
        return false;
    }
}
