package com.oculus.license;

public enum FilterClass {
    SecurityPrincipalFilter("security_principal"),
    SystemAttributeFilter("system_attribute");
    
    private final String value;

    private FilterClass(String value2) {
        this.value = value2;
    }

    public static FilterClass fromString(String value2) throws IllegalArgumentException {
        FilterClass[] values = values();
        for (FilterClass fc : values) {
            if (fc.value.equals(value2)) {
                return fc;
            }
        }
        throw new IllegalArgumentException(String.format("%s: unrecognized filter class", value2));
    }

    public String toString() {
        return this.value;
    }
}
