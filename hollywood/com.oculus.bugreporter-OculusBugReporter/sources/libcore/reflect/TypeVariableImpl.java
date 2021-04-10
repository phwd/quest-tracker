package libcore.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public final class TypeVariableImpl<D extends GenericDeclaration> implements TypeVariable<D> {
    private ListOfTypes bounds;
    private final GenericDeclaration declOfVarUser;
    private TypeVariableImpl<D> formalVar;
    private D genericDeclaration;
    private final String name;

    public boolean equals(Object o) {
        if (!(o instanceof TypeVariable)) {
            return false;
        }
        TypeVariable<?> that = (TypeVariable) o;
        if (!getName().equals(that.getName()) || !getGenericDeclaration().equals(that.getGenericDeclaration())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (getName().hashCode() * 31) + getGenericDeclaration().hashCode();
    }

    TypeVariableImpl(D genericDecl, String name2, ListOfTypes bounds2) {
        this.genericDeclaration = genericDecl;
        this.name = name2;
        this.bounds = bounds2;
        this.formalVar = this;
        this.declOfVarUser = null;
    }

    TypeVariableImpl(D genericDecl, String name2) {
        this.name = name2;
        this.declOfVarUser = genericDecl;
    }

    static TypeVariable findFormalVar(GenericDeclaration layer, String name2) {
        TypeVariable[] formalVars = layer.getTypeParameters();
        for (TypeVariable var : formalVars) {
            if (name2.equals(var.getName())) {
                return var;
            }
        }
        return null;
    }

    private static GenericDeclaration nextLayer(GenericDeclaration decl) {
        if (decl instanceof Class) {
            Class cl = (Class) decl;
            GenericDeclaration m = cl.getEnclosingMethod();
            GenericDeclaration decl2 = m != null ? m : cl.getEnclosingConstructor();
            if (decl2 != null) {
                return decl2;
            }
            return cl.getEnclosingClass();
        } else if (decl instanceof Method) {
            return ((Method) decl).getDeclaringClass();
        } else {
            if (decl instanceof Constructor) {
                return ((Constructor) decl).getDeclaringClass();
            }
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public void resolve() {
        if (this.formalVar == null) {
            GenericDeclaration curLayer = this.declOfVarUser;
            do {
                TypeVariable var = findFormalVar(curLayer, this.name);
                if (var == null) {
                    curLayer = nextLayer(curLayer);
                } else {
                    this.formalVar = (TypeVariableImpl) var;
                    TypeVariableImpl<D> typeVariableImpl = this.formalVar;
                    this.genericDeclaration = typeVariableImpl.genericDeclaration;
                    this.bounds = typeVariableImpl.bounds;
                    return;
                }
            } while (curLayer != null);
            throw new AssertionError((Object) "illegal type variable reference");
        }
    }

    @Override // java.lang.reflect.TypeVariable
    public Type[] getBounds() {
        resolve();
        return (Type[]) this.bounds.getResolvedTypes().clone();
    }

    @Override // java.lang.reflect.TypeVariable
    public D getGenericDeclaration() {
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
