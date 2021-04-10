package java.io;

public class ObjectOutputStream extends OutputStream implements ObjectOutput, ObjectStreamConstants {

    public static abstract class PutField {
    }

    public void defaultWriteObject() {
        throw null;
    }

    public PutField putFields() {
        throw null;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        throw null;
    }

    public void writeInt(int i) {
        throw null;
    }

    public void writeLong(long j) {
        throw null;
    }

    public final void writeObject(Object obj) {
        throw null;
    }

    public void writeUTF(String str) {
        throw null;
    }
}
