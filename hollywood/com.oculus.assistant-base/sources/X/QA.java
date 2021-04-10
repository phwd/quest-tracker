package X;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public final class QA<K, V> extends LinkedHashMap<K, V> implements Serializable {
    public static final long serialVersionUID = 1;
    public transient int A00;
    public final int _maxEntries;

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeInt(this.A00);
    }

    public Object readResolve() {
        int i = this.A00;
        return new QA(i, i);
    }

    public QA(int i, int i2) {
        super(i, 0.8f, true);
        this._maxEntries = i2;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        this.A00 = objectInputStream.readInt();
    }

    @Override // java.util.LinkedHashMap
    public final boolean removeEldestEntry(Map.Entry entry) {
        if (size() > this._maxEntries) {
            return true;
        }
        return false;
    }
}
