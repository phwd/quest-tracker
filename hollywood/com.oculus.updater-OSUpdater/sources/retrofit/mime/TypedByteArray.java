package retrofit.mime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TypedByteArray implements TypedInput, TypedOutput {
    private final byte[] bytes;
    private final String mimeType;

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return null;
    }

    public TypedByteArray(String str, byte[] bArr) {
        str = str == null ? "application/unknown" : str;
        if (bArr != null) {
            this.mimeType = str;
            this.bytes = bArr;
            return;
        }
        throw new NullPointerException("bytes");
    }

    public byte[] getBytes() {
        return this.bytes;
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
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.bytes);
    }

    @Override // retrofit.mime.TypedInput
    public InputStream in() throws IOException {
        return new ByteArrayInputStream(this.bytes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypedByteArray typedByteArray = (TypedByteArray) obj;
        return Arrays.equals(this.bytes, typedByteArray.bytes) && this.mimeType.equals(typedByteArray.mimeType);
    }

    public int hashCode() {
        return (this.mimeType.hashCode() * 31) + Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return "TypedByteArray[length=" + length() + "]";
    }
}
