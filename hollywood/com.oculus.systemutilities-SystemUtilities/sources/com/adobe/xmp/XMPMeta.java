package com.adobe.xmp;

import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPProperty;

public interface XMPMeta extends Cloneable {
    @Override // java.lang.Object
    Object clone();

    boolean doesPropertyExist(String str, String str2);

    XMPProperty getProperty(String str, String str2) throws XMPException;

    void setLocalizedText(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException;
}
