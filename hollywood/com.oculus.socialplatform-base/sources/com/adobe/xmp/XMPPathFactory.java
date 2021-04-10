package com.adobe.xmp;

import X.AnonymousClass006;
import com.adobe.xmp.impl.Utils;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;

public final class XMPPathFactory {
    public static void assertFieldNS(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty field namespace URI", 101);
        }
    }

    public static void assertFieldName(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty f name", 102);
        }
    }

    public static void assertQualNS(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier namespace URI", 101);
        }
    }

    public static void assertQualName(String str) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Empty qualifier name", 102);
        }
    }

    public static String composeArrayItemPath(String str, int i) throws XMPException {
        if (i > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append('[');
            sb.append(i);
            sb.append(']');
            return sb.toString();
        } else if (i == -1) {
            return AnonymousClass006.A07(str, "[last()]");
        } else {
            throw new XMPException("Array index must be larger than zero", 104);
        }
    }

    public static String composeLangSelector(String str, String str2) {
        return AnonymousClass006.A0B(str, "[?xml:lang=\"", Utils.normalizeLangValue(str2), "\"]");
    }

    public static String composeFieldSelector(String str, String str2, String str3, String str4) throws XMPException {
        XMPPath expandXPath = XMPPathParser.expandXPath(str2, str3);
        if (expandXPath.segments.size() == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append('[');
            sb.append(expandXPath.getSegment(1).name);
            sb.append("=\"");
            sb.append(str4);
            sb.append("\"]");
            return sb.toString();
        }
        throw new XMPException("The fieldName name must be simple", 102);
    }

    public static String composeQualifierPath(String str, String str2) throws XMPException {
        assertQualNS(str);
        assertQualName(str2);
        XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
        if (expandXPath.segments.size() == 2) {
            return AnonymousClass006.A07("/?", expandXPath.getSegment(1).name);
        }
        throw new XMPException("The qualifier name must be simple", 102);
    }

    public static String composeStructFieldPath(String str, String str2) throws XMPException {
        assertFieldNS(str);
        assertFieldName(str2);
        XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
        if (expandXPath.segments.size() == 2) {
            return AnonymousClass006.A07("/", expandXPath.getSegment(1).name);
        }
        throw new XMPException("The field name must be simple", 102);
    }
}
