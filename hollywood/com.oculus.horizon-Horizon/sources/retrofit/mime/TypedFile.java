package retrofit.mime;

import X.AnonymousClass006;
import com.facebook.GraphRequest;
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
        return AnonymousClass006.A08(this.file.getAbsolutePath(), " (", mimeType(), ")");
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
        String str2;
        if (str == null) {
            str2 = "mimeType";
        } else if (file2 != null) {
            this.mimeType = str;
            this.file = file2;
            return;
        } else {
            str2 = GraphRequest.ATTACHMENT_FILENAME_PREFIX;
        }
        throw new NullPointerException(str2);
    }

    public File file() {
        return this.file;
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public String mimeType() {
        return this.mimeType;
    }

    public void moveTo(TypedFile typedFile) throws IOException {
        String str;
        if (!mimeType().equals(typedFile.mimeType())) {
            str = "Type mismatch.";
        } else if (!this.file.renameTo(typedFile.file)) {
            str = "Rename failed!";
        } else {
            return;
        }
        throw new IOException(str);
    }
}
