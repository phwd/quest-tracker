package libcore.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

public final class TypeVariableImpl implements TypeVariable {
    private ListOfTypes bounds;
    private final GenericDeclaration declOfVarUser;
    private TypeVariableImpl formalVar;
    private GenericDeclaration genericDeclaration;
    private final String name;

    public boolean equals(Object obj) {
        if (!(obj instanceof TypeVariable)) {
            return false;
        }
        TypeVariable typeVariable = (TypeVariable) obj;
        if (!getName().equals(typeVariable.getName()) || !getGenericDeclaration().equals(typeVariable.getGenericDeclaration())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getName().hashCode() * 31) + getGenericDeclaration().hashCode();
    }

    TypeVariableImpl(GenericDeclaration genericDeclaration2, String str, ListOfTypes listOfTypes) {
        this.genericDeclaration = genericDeclaration2;
        this.name = str;
        this.bounds = listOfTypes;
        this.formalVar = this;
        this.declOfVarUser = null;
    }

    TypeVariableImpl(GenericDeclaration genericDeclaration2, String str) {
        this.name = str;
        this.declOfVarUser = genericDeclaration2;
    }

    static TypeVariable findFormalVar(GenericDeclaration genericDeclaration2, String str) {
        TypeVariable[] typeParameters = genericDeclaration2.getTypeParameters();
        for (TypeVariable typeVariable : typeParameters) {
            if (str.equals(typeVariable.getName())) {
                return typeVariable;
            }
        }
        return null;
    }

    private static GenericDeclaration nextLayer(GenericDeclaration genericDeclaration2) {
        if (genericDeclaration2 instanceof Class) {
            Class cls = (Class) genericDeclaration2;
            GenericDeclaration enclosingMethod = cls.getEnclosingMethod();
            if (enclosingMethod == null) {
                enclosingMethod = cls.getEnclosingConstructor();
            }
            if (enclosingMethod != null) {
                return enclosingMethod;
            }
            return cls.getEnclosingClass();
        } else if (genericDeclaration2 instanceof Method) {
            return ((Method) genericDeclaration2).getDeclaringClass();
        } else {
            if (genericDeclaration2 instanceof Constructor) {
                return ((Constructor) genericDeclaration2).getDeclaringClass();
            }
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public void resolve() {
        if (this.formalVar == null) {
            GenericDeclaration genericDeclaration2 = this.declOfVarUser;
            do {
                TypeVariable findFormalVar = findFormalVar(genericDeclaration2, this.name);
                if (findFormalVar == null) {
                    genericDeclaration2 = nextLayer(genericDeclaration2);
                } else {
                    this.formalVar = (TypeVariableImpl) findFormalVar;
                    TypeVariableImpl typeVariableImpl = this.formalVar;
                    this.genericDeclaration = typeVariableImpl.genericDeclaration;
                    this.bounds = typeVariableImpl.bounds;
                    return;
                }
            } while (genericDeclaration2 != null);
            throw new AssertionError((Object) "illegal type variable reference");
        }
    }

    @Override // java.lang.reflect.TypeVariable
    public GenericDeclaration getGenericDeclaration() {
        resolve();
        return this.genericDeclaration;
    }

    @Override // java.lang.reflect.TypeVariable
    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
