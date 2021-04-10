package java.lang.reflect;

public interface TypeVariable extends Type {
    GenericDeclaration getGenericDeclaration();

    String getName();
}
