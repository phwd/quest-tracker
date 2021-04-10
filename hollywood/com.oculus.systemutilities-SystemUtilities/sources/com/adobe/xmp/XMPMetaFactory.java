package com.adobe.xmp;

import com.adobe.xmp.impl.XMPMetaParser;
import com.adobe.xmp.impl.XMPSchemaRegistryImpl;
import com.adobe.xmp.options.ParseOptions;

public final class XMPMetaFactory {
    private static XMPSchemaRegistry schema = new XMPSchemaRegistryImpl();
    private static XMPVersionInfo versionInfo = null;

    public static XMPSchemaRegistry getSchemaRegistry() {
        return schema;
    }

    public static XMPMeta parseFromString(String packet) throws XMPException {
        return parseFromString(packet, null);
    }

    public static XMPMeta parseFromString(String packet, ParseOptions options) throws XMPException {
        return XMPMetaParser.parse(packet, options);
    }
}
