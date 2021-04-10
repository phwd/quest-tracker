package java.nio.file;

import java.io.IOException;

public class FileSystemException extends IOException {
    static final long serialVersionUID = -3055425747967319812L;
    private final String file;
    private final String other = null;

    public FileSystemException(String str) {
        super((String) null);
        this.file = str;
    }

    public String getReason() {
        return super.getMessage();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.file == null && this.other == null) {
            return getReason();
        }
        StringBuilder sb = new StringBuilder();
        String str = this.file;
        if (str != null) {
            sb.append(str);
        }
        if (this.other != null) {
            sb.append(" -> ");
            sb.append(this.other);
        }
        if (getReason() != null) {
            sb.append(": ");
            sb.append(getReason());
        }
        return sb.toString();
    }
}
