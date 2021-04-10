package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.options.SerializeOptions;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class XMPSerializerHelper {
    public static void serialize(XMPMetaImpl xmp, OutputStream out, SerializeOptions options) throws XMPException {
        if (options == null) {
            options = new SerializeOptions();
        }
        if (options.getSort()) {
            xmp.sort();
        }
        new XMPSerializerRDF().serialize(xmp, out, options);
    }

    public static String serializeToString(XMPMetaImpl xmp, SerializeOptions options) throws XMPException {
        if (options == null) {
            options = new SerializeOptions();
        }
        options.setEncodeUTF16BE(true);
        ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
        serialize(xmp, out, options);
        try {
            return out.toString(options.getEncoding());
        } catch (UnsupportedEncodingException e) {
            return out.toString();
        }
    }

    public static byte[] serializeToBuffer(XMPMetaImpl xmp, SerializeOptions options) throws XMPException {
        ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
        serialize(xmp, out, options);
        return out.toByteArray();
    }
}
