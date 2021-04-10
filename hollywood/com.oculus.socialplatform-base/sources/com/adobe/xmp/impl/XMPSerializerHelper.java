package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.options.SerializeOptions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class XMPSerializerHelper {
    public static void serialize(XMPMetaImpl xMPMetaImpl, OutputStream outputStream, SerializeOptions serializeOptions) throws XMPException {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        if (serializeOptions.getOption(4096)) {
            xMPMetaImpl.sort();
        }
        new XMPSerializerRDF().serialize(xMPMetaImpl, outputStream, serializeOptions);
    }

    public static byte[] serializeToBuffer(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) throws XMPException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        serialize(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        return byteArrayOutputStream.toByteArray();
    }

    public static String serializeToString(XMPMetaImpl xMPMetaImpl, SerializeOptions serializeOptions) throws XMPException {
        if (serializeOptions == null) {
            serializeOptions = new SerializeOptions();
        }
        serializeOptions.setEncodeUTF16BE(true);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(2048);
        serialize(xMPMetaImpl, byteArrayOutputStream, serializeOptions);
        try {
            return byteArrayOutputStream.toString(serializeOptions.getEncoding());
        } catch (UnsupportedEncodingException unused) {
            return byteArrayOutputStream.toString();
        }
    }
}
