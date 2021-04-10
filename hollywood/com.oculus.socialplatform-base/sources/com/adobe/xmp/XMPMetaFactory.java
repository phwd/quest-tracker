package com.adobe.xmp;

import com.adobe.xmp.impl.XMPMetaImpl;
import com.adobe.xmp.impl.XMPMetaParser;
import com.adobe.xmp.impl.XMPSchemaRegistryImpl;
import com.adobe.xmp.impl.XMPSerializerHelper;
import com.adobe.xmp.options.ParseOptions;
import com.adobe.xmp.options.SerializeOptions;
import java.io.InputStream;
import java.io.OutputStream;

public final class XMPMetaFactory {
    public static XMPSchemaRegistry schema = new XMPSchemaRegistryImpl();
    public static XMPVersionInfo versionInfo;

    public static void assertImplementation(XMPMeta xMPMeta) {
        if (!(xMPMeta instanceof XMPMetaImpl)) {
            throw new UnsupportedOperationException("The serializing service works onlywith the XMPMeta implementation of this library");
        }
    }

    public static XMPMeta create() {
        return new XMPMetaImpl();
    }

    public static synchronized XMPVersionInfo getVersionInfo() {
        XMPVersionInfo xMPVersionInfo;
        synchronized (XMPMetaFactory.class) {
            if (versionInfo == null) {
                try {
                    versionInfo = new XMPVersionInfo() {
                        /* class com.adobe.xmp.XMPMetaFactory.AnonymousClass1 */

                        @Override // com.adobe.xmp.XMPVersionInfo
                        public int getBuild() {
                            return 3;
                        }

                        @Override // com.adobe.xmp.XMPVersionInfo
                        public int getMajor() {
                            return 5;
                        }

                        @Override // com.adobe.xmp.XMPVersionInfo
                        public String getMessage() {
                            return "Adobe XMP Core 5.1.0-jc003";
                        }

                        @Override // com.adobe.xmp.XMPVersionInfo
                        public int getMicro() {
                            return 0;
                        }

                        @Override // com.adobe.xmp.XMPVersionInfo
                        public int getMinor() {
                            return 1;
                        }

                        @Override // com.adobe.xmp.XMPVersionInfo
                        public boolean isDebug() {
                            return false;
                        }

                        public String toString() {
                            return "Adobe XMP Core 5.1.0-jc003";
                        }
                    };
                } catch (Throwable th) {
                    System.out.println(th);
                }
            }
            xMPVersionInfo = versionInfo;
        }
        return xMPVersionInfo;
    }

    public static void reset() {
        schema = new XMPSchemaRegistryImpl();
    }

    public static XMPSchemaRegistry getSchemaRegistry() {
        return schema;
    }

    public static byte[] serializeToBuffer(XMPMeta xMPMeta, SerializeOptions serializeOptions) throws XMPException {
        assertImplementation(xMPMeta);
        return XMPSerializerHelper.serializeToBuffer((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    public static String serializeToString(XMPMeta xMPMeta, SerializeOptions serializeOptions) throws XMPException {
        assertImplementation(xMPMeta);
        return XMPSerializerHelper.serializeToString((XMPMetaImpl) xMPMeta, serializeOptions);
    }

    public static XMPMeta parse(InputStream inputStream) throws XMPException {
        return XMPMetaParser.parse(inputStream, null);
    }

    public static XMPMeta parse(InputStream inputStream, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.parse(inputStream, parseOptions);
    }

    public static XMPMeta parseFromBuffer(byte[] bArr) throws XMPException {
        return XMPMetaParser.parse(bArr, null);
    }

    public static XMPMeta parseFromBuffer(byte[] bArr, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.parse(bArr, parseOptions);
    }

    public static XMPMeta parseFromString(String str) throws XMPException {
        return XMPMetaParser.parse(str, null);
    }

    public static XMPMeta parseFromString(String str, ParseOptions parseOptions) throws XMPException {
        return XMPMetaParser.parse(str, parseOptions);
    }

    public static void serialize(XMPMeta xMPMeta, OutputStream outputStream) throws XMPException {
        serialize(xMPMeta, outputStream, null);
    }

    public static void serialize(XMPMeta xMPMeta, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        assertImplementation(xMPMeta);
        XMPSerializerHelper.serialize((XMPMetaImpl) xMPMeta, outputStream, serializeOptions);
    }
}
