package java.nio.file;

import android.icu.text.PluralRules;
import java.io.IOException;

public class FileSystemException extends IOException {
    static final long serialVersionUID = -3055425747967319812L;
    private final String file;
    private final String other;

    public FileSystemException(String file2) {
        super((String) null);
        this.file = file2;
        this.other = null;
    }

    public FileSystemException(String file2, String other2, String reason) {
        super(reason);
        this.file = file2;
        this.other = other2;
    }

    public String getFile() {
        return this.file;
    }

    public String getOtherFile() {
        return this.other;
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
            sb.append(PluralRules.KEYWORD_RULE_SEPARATOR);
            sb.append(getReason());
        }
        return sb.toString();
    }
}
