package java.lang;

public class TypeNotPresentException extends RuntimeException {
    private static final long serialVersionUID = -5101214195716534496L;
    private String typeName;

    public TypeNotPresentException(String str, Throwable th) {
        super("Type " + str + " not present", th);
        this.typeName = str;
    }
}
