package com.adobe.xmp.properties;

import com.adobe.xmp.options.PropertyOptions;

public interface XMPPropertyInfo extends XMPProperty {
    String getNamespace();

    @Override // com.adobe.xmp.properties.XMPProperty
    PropertyOptions getOptions();

    String getPath();

    @Override // com.adobe.xmp.properties.XMPProperty
    Object getValue();
}
