package java.util.zip;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CheckedOutputStream extends FilterOutputStream {
    private Checksum cksum;

    public CheckedOutputStream(OutputStream out, Checksum cksum2) {
        super(out);
        this.cksum = cksum2;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int b) throws IOException {
        this.out.write(b);
        this.cksum.update(b);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
        this.cksum.update(b, off, len);
    }

    public Checksum getChecksum() {
        return this.cksum;
    }
}
