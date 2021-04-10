package java.io;

public class DataInputStream extends FilterInputStream implements DataInput {
    private byte[] bytearr = new byte[80];
    private char[] chararr = new char[80];
    private byte[] readBuffer = new byte[8];

    public DataInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) {
        return this.in.read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        return this.in.read(bArr, i, i2);
    }

    public final void readFully(byte[] bArr, int i, int i2) {
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                int read = this.in.read(bArr, i + i3, i2 - i3);
                if (read >= 0) {
                    i3 += read;
                } else {
                    throw new EOFException();
                }
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
