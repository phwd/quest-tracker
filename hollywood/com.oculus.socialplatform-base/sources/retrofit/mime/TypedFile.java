package retrofit.mime;

import X.AnonymousClass006;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TypedFile implements TypedOutput, TypedInput {
    public static final int BUFFER_SIZE = 4096;
    public final File file;
    public final String mimeType;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TypedFile) {
            return this.file.equals(((TypedFile) obj).file);
        }
        return false;
    }

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return this.file.getName();
    }

    public int hashCode() {
        return this.file.hashCode();
    }

    @Override // retrofit.mime.TypedInput
    public InputStream in() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public long length() {
        return this.file.length();
    }

    public String toString() {
        return AnonymousClass006.A0B(this.file.getAbsolutePath(), " (", mimeType(), ")");
    }

    @Override // retrofit.mime.TypedOutput
    public void writeTo(OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        FileInputStream fileInputStream = new FileInputStream(this.file);
        while (true) {
            try {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                } else {
                    return;
                }
            } finally {
                fileInputStream.close();
            }
        }
    }

    public TypedFile(String str, File file2) {
        if (str == null) {
            throw new NullPointerException("mimeType");
        } else if (file2 != null) {
            this.mimeType = str;
            this.file = file2;
        } else {
            throw new NullPointerException("file");
        }
    }

    public File file() {
        return this.file;
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public String mimeType() {
        return this.mimeType;
    }

    public void moveTo(TypedFile typedFile) throws IOException {
        if (!mimeType().equals(typedFile.mimeType())) {
            throw new IOException("Type mismatch.");
        } else if (!this.file.renameTo(typedFile.file)) {
            throw new IOException("Rename failed!");
        }
    }
}
