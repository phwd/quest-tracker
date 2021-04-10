package com.adobe.xmp.impl.xpath;

import X.AnonymousClass006;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.Utils;
import com.adobe.xmp.properties.XMPAliasInfo;

public final class XMPPathParser {
    public static XMPPath expandXPath(String str, String str2) throws XMPException {
        XMPPathSegment parseIndexSegment;
        String str3;
        if (str == null || str2 == null) {
            throw new XMPException("Parameter must not be null", 4);
        }
        XMPPath xMPPath = new XMPPath();
        PathPosition pathPosition = new PathPosition();
        pathPosition.path = str2;
        parseRootNode(str, pathPosition, xMPPath);
        while (true) {
            int i = pathPosition.stepEnd;
            if (i >= str2.length()) {
                return xMPPath;
            }
            pathPosition.stepBegin = i;
            skipPathDelimiter(str2, pathPosition);
            int i2 = pathPosition.stepBegin;
            pathPosition.stepEnd = i2;
            if (str2.charAt(i2) != '[') {
                parseIndexSegment = parseStructSegment(pathPosition);
            } else {
                parseIndexSegment = parseIndexSegment(pathPosition);
            }
            int i3 = parseIndexSegment.kind;
            if (i3 == 1) {
                if (parseIndexSegment.name.charAt(0) == '@') {
                    String A07 = AnonymousClass006.A07("?", parseIndexSegment.name.substring(1));
                    parseIndexSegment.name = A07;
                    if (!"?xml:lang".equals(A07)) {
                        throw new XMPException("Only xml:lang allowed with '@'", 102);
                    }
                }
                if (parseIndexSegment.name.charAt(0) == '?') {
                    pathPosition.nameStart++;
                    parseIndexSegment.kind = 2;
                }
                str3 = pathPosition.path.substring(pathPosition.nameStart, pathPosition.nameEnd);
            } else {
                if (i3 != 6) {
                    continue;
                } else {
                    if (parseIndexSegment.name.charAt(1) == '@') {
                        String A072 = AnonymousClass006.A07("[?", parseIndexSegment.name.substring(2));
                        parseIndexSegment.name = A072;
                        if (!A072.startsWith("[?xml:lang=")) {
                            throw new XMPException("Only xml:lang allowed with '@'", 102);
                        }
                    }
                    if (parseIndexSegment.name.charAt(1) == '?') {
                        int i4 = pathPosition.nameStart + 1;
                        pathPosition.nameStart = i4;
                        parseIndexSegment.kind = 5;
                        str3 = pathPosition.path.substring(i4, pathPosition.nameEnd);
                    }
                }
                xMPPath.add(parseIndexSegment);
            }
            verifyQualName(str3);
            xMPPath.add(parseIndexSegment);
        }
    }

    public static XMPPathSegment parseIndexSegment(PathPosition pathPosition) throws XMPException {
        int i;
        int i2 = pathPosition.stepEnd + 1;
        pathPosition.stepEnd = i2;
        if ('0' > pathPosition.path.charAt(i2) || pathPosition.path.charAt(pathPosition.stepEnd) > '9') {
            while (true) {
                int i3 = pathPosition.stepEnd;
                String str = pathPosition.path;
                if (i3 >= str.length() || str.charAt(i3) == ']' || pathPosition.path.charAt(pathPosition.stepEnd) == '=') {
                    int i4 = pathPosition.stepEnd;
                    String str2 = pathPosition.path;
                } else {
                    pathPosition.stepEnd++;
                }
            }
            int i42 = pathPosition.stepEnd;
            String str22 = pathPosition.path;
            if (i42 >= str22.length()) {
                throw new XMPException("Missing ']' or '=' for array index", 102);
            } else if (str22.charAt(i42) != ']') {
                pathPosition.nameStart = pathPosition.stepBegin + 1;
                int i5 = pathPosition.stepEnd;
                pathPosition.nameEnd = i5;
                int i6 = i5 + 1;
                pathPosition.stepEnd = i6;
                char charAt = pathPosition.path.charAt(i6);
                if (charAt == '\'' || charAt == '\"') {
                    while (true) {
                        int i7 = pathPosition.stepEnd + 1;
                        pathPosition.stepEnd = i7;
                        String str3 = pathPosition.path;
                        if (i7 >= str3.length()) {
                            break;
                        } else if (str3.charAt(i7) == charAt) {
                            int i8 = pathPosition.stepEnd + 1;
                            String str4 = pathPosition.path;
                            if (i8 >= str4.length() || str4.charAt(i8) != charAt) {
                                break;
                            }
                            pathPosition.stepEnd++;
                        }
                    }
                    int i9 = pathPosition.stepEnd;
                    if (i9 < pathPosition.path.length()) {
                        pathPosition.stepEnd = i9 + 1;
                        i = 6;
                    } else {
                        throw new XMPException("No terminating quote for array selector", 102);
                    }
                } else {
                    throw new XMPException("Invalid quote in array selector", 102);
                }
            } else if ("[last()".equals(pathPosition.path.substring(pathPosition.stepBegin, pathPosition.stepEnd))) {
                i = 4;
            } else {
                throw new XMPException("Invalid non-numeric array index", 102);
            }
        } else {
            while (true) {
                int i10 = pathPosition.stepEnd;
                String str5 = pathPosition.path;
                if (i10 >= str5.length() || '0' > str5.charAt(i10) || pathPosition.path.charAt(pathPosition.stepEnd) > '9') {
                    i = 3;
                } else {
                    pathPosition.stepEnd++;
                }
            }
            i = 3;
        }
        XMPPathSegment xMPPathSegment = new XMPPathSegment(null, i);
        int i11 = pathPosition.stepEnd;
        String str6 = pathPosition.path;
        if (i11 >= str6.length() || str6.charAt(i11) != ']') {
            throw new XMPException("Missing ']' for array index", 102);
        }
        int i12 = pathPosition.stepEnd + 1;
        pathPosition.stepEnd = i12;
        xMPPathSegment.name = pathPosition.path.substring(pathPosition.stepBegin, i12);
        return xMPPathSegment;
    }

    public static void parseRootNode(String str, PathPosition pathPosition, XMPPath xMPPath) throws XMPException {
        int i;
        String str2;
        XMPPathSegment xMPPathSegment;
        while (true) {
            int i2 = pathPosition.stepEnd;
            String str3 = pathPosition.path;
            if (i2 >= str3.length() || "/[*".indexOf(str3.charAt(i2)) >= 0) {
                int i3 = pathPosition.stepEnd;
                int i4 = pathPosition.stepBegin;
            } else {
                pathPosition.stepEnd++;
            }
        }
        int i32 = pathPosition.stepEnd;
        int i42 = pathPosition.stepBegin;
        if (i32 != i42) {
            String verifyXPathRoot = verifyXPathRoot(str, pathPosition.path.substring(i42, i32));
            XMPAliasInfo findAlias = XMPMetaFactory.schema.findAlias(verifyXPathRoot);
            if (findAlias == null) {
                xMPPath.add(new XMPPathSegment(str, Integer.MIN_VALUE));
                xMPPathSegment = new XMPPathSegment(verifyXPathRoot, 1);
            } else {
                xMPPath.add(new XMPPathSegment(findAlias.getNamespace(), Integer.MIN_VALUE));
                XMPPathSegment xMPPathSegment2 = new XMPPathSegment(verifyXPathRoot(findAlias.getNamespace(), findAlias.getPropName()), 1);
                xMPPathSegment2.alias = true;
                xMPPathSegment2.aliasForm = findAlias.getAliasForm().options;
                xMPPath.add(xMPPathSegment2);
                if (findAlias.getAliasForm().getOption(4096)) {
                    i = 5;
                    str2 = "[?xml:lang='x-default']";
                } else if (findAlias.getAliasForm().getOption(512)) {
                    i = 3;
                    str2 = "[1]";
                } else {
                    return;
                }
                xMPPathSegment = new XMPPathSegment(str2, i);
                xMPPathSegment.alias = true;
                xMPPathSegment.aliasForm = findAlias.getAliasForm().options;
            }
            xMPPath.add(xMPPathSegment);
            return;
        }
        throw new XMPException("Empty initial XMPPath step", 102);
    }

    public static XMPPathSegment parseStructSegment(PathPosition pathPosition) throws XMPException {
        pathPosition.nameStart = pathPosition.stepBegin;
        while (true) {
            int i = pathPosition.stepEnd;
            String str = pathPosition.path;
            if (i >= str.length() || "/[*".indexOf(str.charAt(i)) >= 0) {
                int i2 = pathPosition.stepEnd;
                pathPosition.nameEnd = i2;
                int i3 = pathPosition.stepBegin;
            } else {
                pathPosition.stepEnd++;
            }
        }
        int i22 = pathPosition.stepEnd;
        pathPosition.nameEnd = i22;
        int i32 = pathPosition.stepBegin;
        if (i22 != i32) {
            return new XMPPathSegment(pathPosition.path.substring(i32, i22), 1);
        }
        throw new XMPException("Empty XMPPath segment", 102);
    }

    public static void skipPathDelimiter(String str, PathPosition pathPosition) throws XMPException {
        if (str.charAt(pathPosition.stepBegin) == '/') {
            int i = pathPosition.stepBegin + 1;
            pathPosition.stepBegin = i;
            if (i >= str.length()) {
                throw new XMPException("Empty XMPPath segment", 102);
            }
        }
        if (str.charAt(pathPosition.stepBegin) == '*') {
            int i2 = pathPosition.stepBegin + 1;
            pathPosition.stepBegin = i2;
            if (i2 >= str.length() || str.charAt(i2) != '[') {
                throw new XMPException("Missing '[' after '*'", 102);
            }
        }
    }

    public static void verifyQualName(String str) throws XMPException {
        int indexOf = str.indexOf(58);
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            if (Utils.isXMLNameNS(substring)) {
                if (XMPMetaFactory.schema.getNamespaceURI(substring) == null) {
                    throw new XMPException("Unknown namespace prefix for qualified name", 102);
                }
                return;
            }
        }
        throw new XMPException("Ill-formed qualified name", 102);
    }

    public static String verifyXPathRoot(String str, String str2) throws XMPException {
        if (str == null || str.length() == 0) {
            throw new XMPException("Schema namespace URI is required", 101);
        } else if (str2.charAt(0) == '?' || str2.charAt(0) == '@') {
            throw new XMPException("Top level name must not be a qualifier", 102);
        } else if (str2.indexOf(47) >= 0 || str2.indexOf(91) >= 0) {
            throw new XMPException("Top level name must be simple", 102);
        } else {
            String namespacePrefix = XMPMetaFactory.schema.getNamespacePrefix(str);
            if (namespacePrefix != null) {
                int indexOf = str2.indexOf(58);
                if (indexOf < 0) {
                    verifySimpleXMLName(str2);
                    return AnonymousClass006.A07(namespacePrefix, str2);
                }
                verifySimpleXMLName(str2.substring(0, indexOf));
                verifySimpleXMLName(str2.substring(indexOf));
                String substring = str2.substring(0, indexOf + 1);
                String namespacePrefix2 = XMPMetaFactory.schema.getNamespacePrefix(str);
                if (namespacePrefix2 == null) {
                    throw new XMPException("Unknown schema namespace prefix", 101);
                } else if (substring.equals(namespacePrefix2)) {
                    return str2;
                } else {
                    throw new XMPException("Schema namespace URI and prefix mismatch", 101);
                }
            } else {
                throw new XMPException("Unregistered schema namespace URI", 101);
            }
        }
    }

    public static void verifySimpleXMLName(String str) throws XMPException {
        if (!Utils.isXMLName(str)) {
            throw new XMPException("Bad XML name", 102);
        }
    }
}
