package java.lang;

import java.io.Serializable;
import java.util.Objects;

public final class StackTraceElement implements Serializable {
    private static final long serialVersionUID = 6992337162326171013L;
    private String declaringClass;
    private String fileName;
    private int lineNumber;
    private String methodName;

    public StackTraceElement(String str, String str2, String str3, int i) {
        Objects.requireNonNull(str, "Declaring class is null");
        this.declaringClass = str;
        Objects.requireNonNull(str2, "Method name is null");
        this.methodName = str2;
        this.fileName = str3;
        this.lineNumber = i;
    }

    public String getClassName() {
        return this.declaringClass;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public boolean isNativeMethod() {
        return this.lineNumber == -2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClassName());
        sb.append(".");
        sb.append(this.methodName);
        if (isNativeMethod()) {
            sb.append("(Native Method)");
        } else if (this.fileName != null) {
            if (this.lineNumber >= 0) {
                sb.append("(");
                sb.append(this.fileName);
                sb.append(":");
                sb.append(this.lineNumber);
                sb.append(")");
            } else {
                sb.append("(");
                sb.append(this.fileName);
                sb.append(")");
            }
        } else if (this.lineNumber >= 0) {
            sb.append("(Unknown Source:");
            sb.append(this.lineNumber);
            sb.append(")");
        } else {
            sb.append("(Unknown Source)");
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StackTraceElement)) {
            return false;
        }
        StackTraceElement stackTraceElement = (StackTraceElement) obj;
        return stackTraceElement.declaringClass.equals(this.declaringClass) && stackTraceElement.lineNumber == this.lineNumber && Objects.equals(this.methodName, stackTraceElement.methodName) && Objects.equals(this.fileName, stackTraceElement.fileName);
    }

    public int hashCode() {
        return (((((this.declaringClass.hashCode() * 31) + this.methodName.hashCode()) * 31) + Objects.hashCode(this.fileName)) * 31) + this.lineNumber;
    }
}
