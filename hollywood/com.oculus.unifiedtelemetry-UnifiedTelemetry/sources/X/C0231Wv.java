package X;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* renamed from: X.Wv  reason: case insensitive filesystem */
public final class C0231Wv implements q5, Serializable {
    public transient String A00;
    public char[] _quotedChars;
    public byte[] _quotedUTF8Ref;
    public byte[] _unquotedUTF8Ref;
    public final String _value;

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(this._value);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._value.equals(((C0231Wv) obj)._value);
    }

    public final int hashCode() {
        return this._value.hashCode();
    }

    public Object readResolve() {
        return new C0231Wv(this.A00);
    }

    public C0231Wv(String str) {
        if (str != null) {
            this._value = str;
            return;
        }
        throw new IllegalStateException("Null String illegal for SerializedString");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        this.A00 = objectInputStream.readUTF();
    }

    public final String toString() {
        return this._value;
    }
}
