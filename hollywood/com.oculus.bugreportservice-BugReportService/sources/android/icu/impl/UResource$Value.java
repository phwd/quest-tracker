package android.icu.impl;

public abstract class UResource$Value {
    public abstract String getAliasString();

    public abstract UResource$Array getArray();

    public abstract int getInt();

    public abstract int[] getIntVector();

    public abstract String getString();

    public abstract String[] getStringArray();

    public abstract UResource$Table getTable();

    public abstract int getType();

    protected UResource$Value() {
    }

    public String toString() {
        int type = getType();
        if (type == 0) {
            return getString();
        }
        if (type == 1) {
            return "(binary blob)";
        }
        if (type == 2) {
            return "(table)";
        }
        if (type == 7) {
            return Integer.toString(getInt());
        }
        if (type == 8) {
            return "(array)";
        }
        if (type != 14) {
            return "???";
        }
        int[] intVector = getIntVector();
        StringBuilder sb = new StringBuilder("[");
        sb.append(intVector.length);
        sb.append("]{");
        if (intVector.length != 0) {
            sb.append(intVector[0]);
            for (int i = 1; i < intVector.length; i++) {
                sb.append(", ");
                sb.append(intVector[i]);
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
