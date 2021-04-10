package java.lang.reflect;

public final class Field extends AccessibleObject implements Member {
    private int accessFlags;
    private Class declaringClass;
    private int dexFieldIndex;
    private int offset;
    private Class type;

    private native String getNameInternal();

    public native Object get(Object obj);

    private Field() {
    }

    public Class getDeclaringClass() {
        return this.declaringClass;
    }

    public String getName() {
        if (this.dexFieldIndex != -1) {
            return getNameInternal();
        }
        if (this.declaringClass.isProxy()) {
            return "throws";
        }
        throw new AssertionError();
    }

    public int getModifiers() {
        return this.accessFlags & 65535;
    }

    public Class getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        if (getDeclaringClass() == field.getDeclaringClass() && getName() == field.getName() && getType() == field.getType()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return getName().hashCode() ^ getDeclaringClass().getName().hashCode();
    }

    public String toString() {
        String str;
        int modifiers = getModifiers();
        StringBuilder sb = new StringBuilder();
        if (modifiers == 0) {
            str = "";
        } else {
            str = Modifier.toString(modifiers) + " ";
        }
        sb.append(str);
        sb.append(getType().getTypeName());
        sb.append(" ");
        sb.append(getDeclaringClass().getTypeName());
        sb.append(".");
        sb.append(getName());
        return sb.toString();
    }

    public int getOffset() {
        return this.offset;
    }
}
