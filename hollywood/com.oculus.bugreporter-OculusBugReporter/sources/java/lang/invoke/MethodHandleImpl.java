package java.lang.invoke;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;

public class MethodHandleImpl extends MethodHandle implements Cloneable {
    private HandleInfo info;

    public native Member getMemberInternal();

    MethodHandleImpl(long artFieldOrMethod, int handleKind, MethodType type) {
        super(artFieldOrMethod, handleKind, type);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /* access modifiers changed from: package-private */
    public MethodHandleInfo reveal() {
        if (this.info == null) {
            this.info = new HandleInfo(getMemberInternal(), this);
        }
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public static class HandleInfo implements MethodHandleInfo {
        private final MethodHandle handle;
        private final Member member;

        HandleInfo(Member member2, MethodHandle handle2) {
            this.member = member2;
            this.handle = handle2;
        }

        @Override // java.lang.invoke.MethodHandleInfo
        public int getReferenceKind() {
            int handleKind = this.handle.getHandleKind();
            if (handleKind != 0) {
                if (handleKind == 1) {
                    return 7;
                }
                if (handleKind != 2) {
                    if (handleKind == 3) {
                        return 6;
                    }
                    switch (handleKind) {
                        case 9:
                            return 1;
                        case 10:
                            return 3;
                        case 11:
                            return 2;
                        case 12:
                            return 4;
                        default:
                            throw new AssertionError((Object) ("Unexpected handle kind: " + this.handle.getHandleKind()));
                    }
                } else if (this.member instanceof Constructor) {
                    return 8;
                } else {
                    return 7;
                }
            } else if (this.member.getDeclaringClass().isInterface()) {
                return 9;
            } else {
                return 5;
            }
        }

        @Override // java.lang.invoke.MethodHandleInfo
        public Class<?> getDeclaringClass() {
            return this.member.getDeclaringClass();
        }

        @Override // java.lang.invoke.MethodHandleInfo
        public String getName() {
            Member member2 = this.member;
            if (member2 instanceof Constructor) {
                return "<init>";
            }
            return member2.getName();
        }

        @Override // java.lang.invoke.MethodHandleInfo
        public MethodType getMethodType() {
            MethodType handleType = this.handle.type();
            boolean omitLeadingParam = false;
            if (this.member instanceof Constructor) {
                handleType = handleType.changeReturnType(Void.TYPE);
                omitLeadingParam = true;
            }
            int handleKind = this.handle.getHandleKind();
            if (handleKind == 0 || handleKind == 1 || handleKind == 2 || handleKind == 4 || handleKind == 9 || handleKind == 10) {
                omitLeadingParam = true;
            }
            return omitLeadingParam ? handleType.dropParameterTypes(0, 1) : handleType;
        }

        @Override // java.lang.invoke.MethodHandleInfo
        public <T extends Member> T reflectAs(Class<T> cls, MethodHandles.Lookup lookup) {
            try {
                lookup.checkAccess(this.member.getDeclaringClass(), this.member.getDeclaringClass(), this.member.getModifiers(), this.member.getName());
                return (T) this.member;
            } catch (IllegalAccessException exception) {
                throw new IllegalArgumentException("Unable to access member.", exception);
            }
        }

        @Override // java.lang.invoke.MethodHandleInfo
        public int getModifiers() {
            return this.member.getModifiers();
        }
    }
}
