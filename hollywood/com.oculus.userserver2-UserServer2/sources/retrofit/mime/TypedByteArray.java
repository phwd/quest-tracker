package retrofit.mime;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TypedByteArray implements TypedOutput, TypedInput {
    public final byte[] bytes;
    public final String mimeType;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                TypedByteArray typedByteArray = (TypedByteArray) obj;
                if (!Arrays.equals(this.bytes, typedByteArray.bytes) || !this.mimeType.equals(typedByteArray.mimeType)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return null;
    }

    public int hashCode() {
        return (this.mimeType.hashCode() * 31) + Arrays.hashCode(this.bytes);
    }

    @Override // retrofit.mime.TypedInput
    public InputStream in() throws IOException {
        return new ByteArrayInputStream(this.bytes);
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public long length() {
        return (long) this.bytes.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("TypedByteArray[length=");
        sb.append(length());
        sb.append("]");
        return sb.toString();
    }

    @Override // retrofit.mime.TypedOutput
    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.bytes);
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
}
