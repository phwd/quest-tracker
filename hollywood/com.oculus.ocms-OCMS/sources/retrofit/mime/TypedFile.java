package retrofit.mime;

import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TypedFile implements TypedInput, TypedOutput {
    private static final int BUFFER_SIZE = 4096;
    private final File file;
    private final String mimeType;

    public TypedFile(String str, File file2) {
        if (str == null) {
            throw new NullPointerException("mimeType");
        } else if (file2 != null) {
            this.mimeType = str;
            this.file = file2;
        } else {
            throw new NullPointerException(UriUtil.LOCAL_FILE_SCHEME);
        }
    }

    public File file() {
        return this.file;
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public String mimeType() {
        return this.mimeType;
    }

    @Override // retrofit.mime.TypedInput, retrofit.mime.TypedOutput
    public long length() {
        return this.file.length();
    }

    @Override // retrofit.mime.TypedOutput
    public String fileName() {
        return this.file.getName();
    }

    @Override // retrofit.mime.TypedInput
    public InputStream in() throws IOException {
        return new FileInputStream(this.file);
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

    public void moveTo(TypedFile typedFile) throws IOException {
        if (!mimeType().equals(typedFile.mimeType())) {
            throw new IOException("Type mismatch.");
        } else if (!this.file.renameTo(typedFile.file())) {
            throw new IOException("Rename failed!");
        }
    }

    public String toString() {
        return this.file.getAbsolutePath() + " (" + mimeType() + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TypedFile) {
            return this.file.equals(((TypedFile) obj).file);
        }
        return false;
    }

    public int hashCode() {
        return this.file.hashCode();
    }
}
