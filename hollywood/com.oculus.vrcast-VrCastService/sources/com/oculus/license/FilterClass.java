package com.oculus.license;

public enum FilterClass {
    SecurityPrincipalFilter("security_principal"),
    SystemAttributeFilter("system_attribute");
    
    private final String value;

    private FilterClass(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}
