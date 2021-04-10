package sun.security.action;

import java.security.PrivilegedAction;

public class GetPropertyAction implements PrivilegedAction {
    private String defaultVal;
    private String theProp;

    public GetPropertyAction(String str) {
        this.theProp = str;
    }

    public GetPropertyAction(String str, String str2) {
        this.theProp = str;
        this.defaultVal = str2;
    }

    @Override // java.security.PrivilegedAction
    public String run() {
        String property = System.getProperty(this.theProp);
        return property == null ? this.defaultVal : property;
    }
}
