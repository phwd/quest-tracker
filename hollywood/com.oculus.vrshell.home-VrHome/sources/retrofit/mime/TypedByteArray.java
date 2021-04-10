package retrofit.mime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TypedByteArray implements TypedInput, TypedOutput {
    private final byte[] bytes;
    private final String mimeType;

    public TypedByteArray(String mimeType2, byte[] bytes2) {
        mimeType2 = mimeType2 == null ? "application/unknown" : mimeType2;
        if (bytes2 == null) {
            throw new NullPointerException("bytes");
        }
        this.mimeType = mimeType2;
        this.bytes = bytes2;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return null;
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public String mimeType() {
        return this.mimeType;
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public long length() {
        return (long) this.bytes.length;
    }

    @Override // retrofit.mime.TypedOutput
    public void writeTo(OutputStream out) throws IOException {
        out.write(this.bytes);
    }

    @Override // retrofit.mime.TypedInput
    public InputStream in() throws IOException {
        return new ByteArrayInputStream(this.bytes);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TypedByteArray that = (TypedByteArray) o;
        if (!Arrays.equals(this.bytes, that.bytes)) {
            return false;
        }
        return this.mimeType.equals(that.mimeType);
    }

    public int hashCode() {
        return (this.mimeType.hashCode() * 31) + Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return "TypedByteArray[length=" + length() + "]";
    }
}
