package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import java.util.Iterator;

public class XMPUtilsImpl implements XMPConst {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String COMMAS = ",，､﹐﹑、،՝";
    public static final String CONTROLS = "  ";
    public static final String QUOTES = "\"[]«»〝〞〟―‹›";
    public static final String SEMICOLA = ";；﹔؛;";
    public static final String SPACES = " 　〿";
    public static final int UCK_COMMA = 2;
    public static final int UCK_CONTROL = 5;
    public static final int UCK_NORMAL = 0;
    public static final int UCK_QUOTE = 4;
    public static final int UCK_SEMICOLON = 3;
    public static final int UCK_SPACE = 1;

    public static void checkSeparator(String str) throws XMPException {
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            int classifyCharacter = classifyCharacter(str.charAt(i));
            if (classifyCharacter == 3) {
                if (!z) {
                    z = true;
                } else {
                    throw new XMPException("Separator can have only one semicolon", 4);
                }
            } else if (classifyCharacter != 1) {
                throw new XMPException("Separator can have only spaces and one semicolon", 4);
            }
        }
        if (!z) {
            throw new XMPException("Separator must have one semicolon", 4);
        }
    }

    public static char getClosingQuote(char c) {
        switch (c) {
            case '\"':
                return '\"';
            case '[':
                return ']';
            case 171:
                return 187;
            case 187:
                return 171;
            case 8213:
                return 8213;
            case 8216:
                return 8217;
            case 8218:
                return 8219;
            case 8220:
                return 8221;
            case 8222:
                return 8223;
            case 8249:
                return 8250;
            case 8250:
                return 8249;
            case 12296:
                return 12297;
            case 12298:
                return 12299;
            case 12300:
                return 12301;
            case 12302:
                return 12303;
            case 12317:
                return 12319;
            default:
                return 0;
        }
    }

    public static boolean isClosingingQuote(char c, char c2, char c3) {
        return c == c3 || (c2 == 12317 && c == 12318) || c == 12319;
    }

    public static XMPNode separateFindCreateArray(String str, String str2, PropertyOptions propertyOptions, XMPMetaImpl xMPMetaImpl) throws XMPException {
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, null);
        if (verifySetOptions.isOnlyArrayOptions()) {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode = XMPNodeUtils.findNode(xMPMetaImpl.tree, expandXPath, false, null);
            if (findNode != null) {
                PropertyOptions options = findNode.getOptions();
                if (!options.getOption(512) || options.getOption(2048)) {
                    throw new XMPException("Named property must be non-alternate array", 102);
                } else if (verifySetOptions.equalArrayTypes(options)) {
                    throw new XMPException("Mismatch of specified and existing array form", 102);
                }
            } else {
                XMPNode xMPNode = xMPMetaImpl.tree;
                verifySetOptions.setOption(512, true);
                findNode = XMPNodeUtils.findNode(xMPNode, expandXPath, true, verifySetOptions);
                if (findNode == null) {
                    throw new XMPException("Failed to create named array", 102);
                }
            }
            return findNode;
        }
        throw new XMPException("Options can only provide array form", 103);
    }

    public static String applyQuotes(String str, char c, char c2, boolean z) {
        int length;
        if (str == null) {
            str = "";
        }
        int i = 0;
        boolean z2 = false;
        while (true) {
            length = str.length();
            if (i >= length) {
                break;
            }
            int classifyCharacter = classifyCharacter(str.charAt(i));
            if (i == 0 && classifyCharacter == 4) {
                break;
            }
            if (classifyCharacter != 1) {
                if (classifyCharacter == 3 || classifyCharacter == 5 || (classifyCharacter == 2 && !z)) {
                    break;
                }
                z2 = false;
            } else if (z2) {
                break;
            } else {
                z2 = true;
            }
            i++;
        }
        if (i >= length) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(length + 2);
        int i2 = 0;
        while (i2 <= i && classifyCharacter(str.charAt(i)) != 4) {
            i2++;
        }
        stringBuffer.append(c);
        stringBuffer.append(str.substring(0, i2));
        while (i2 < length) {
            stringBuffer.append(str.charAt(i2));
            if (classifyCharacter(str.charAt(i2)) == 4 && isSurroundingQuote(str.charAt(i2), c, c2)) {
                stringBuffer.append(str.charAt(i2));
            }
            i2++;
        }
        stringBuffer.append(c2);
        return stringBuffer.toString();
    }

    public static int classifyCharacter(char c) {
        if (SPACES.indexOf(c) >= 0 || (8192 <= c && c <= 8203)) {
            return 1;
        }
        if (COMMAS.indexOf(c) >= 0) {
            return 2;
        }
        if (SEMICOLA.indexOf(c) >= 0) {
            return 3;
        }
        if (QUOTES.indexOf(c) >= 0) {
            return 4;
        }
        if (12296 <= c && c <= 12303) {
            return 4;
        }
        if (8216 <= c && c <= 8223) {
            return 4;
        }
        if (c < ' ' || CONTROLS.indexOf(c) >= 0) {
            return 5;
        }
        return 0;
    }

    public static boolean isSurroundingQuote(char c, char c2, char c3) {
        if (c == c2 || isClosingingQuote(c, c2, c3)) {
            return true;
        }
        return false;
    }

    public static void appendProperties(XMPMeta xMPMeta, XMPMeta xMPMeta2, boolean z, boolean z2, boolean z3) throws XMPException {
        ParameterAsserts.assertImplementation(xMPMeta);
        ParameterAsserts.assertImplementation(xMPMeta2);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta2;
        Iterator iterateChildren = ((XMPMetaImpl) xMPMeta).tree.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode = (XMPNode) iterateChildren.next();
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.tree, xMPNode.name, false);
            boolean z4 = true;
            if (findSchemaNode == null) {
                String str = xMPNode.name;
                String str2 = xMPNode.value;
                PropertyOptions propertyOptions = new PropertyOptions();
                propertyOptions.setOption(Integer.MIN_VALUE, true);
                findSchemaNode = new XMPNode(str, str2, propertyOptions);
                xMPMetaImpl.tree.addChild(findSchemaNode);
            } else {
                z4 = false;
            }
            Iterator iterateChildren2 = xMPNode.iterateChildren();
            while (iterateChildren2.hasNext()) {
                XMPNode xMPNode2 = (XMPNode) iterateChildren2.next();
                if (z || !Utils.isInternalProperty(xMPNode.name, xMPNode2.name)) {
                    appendSubtree(xMPMetaImpl, xMPNode2, findSchemaNode, z2, z3);
                }
            }
            if (!findSchemaNode.hasChildren() && (z4 || z3)) {
                xMPMetaImpl.tree.removeChild(findSchemaNode);
            }
        }
    }

    public static String catenateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, String str4, boolean z) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        ParameterAsserts.assertImplementation(xMPMeta);
        if (str3 == null || str3.length() == 0) {
            str3 = "; ";
        }
        if (str4 == null || str4.length() == 0) {
            str4 = "\"";
        }
        XMPNode findNode = XMPNodeUtils.findNode(((XMPMetaImpl) xMPMeta).tree, XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode == null) {
            return "";
        }
        if (!findNode.getOptions().getOption(512) || findNode.getOptions().getOption(2048)) {
            throw new XMPException("Named property must be non-alternate array", 4);
        }
        checkSeparator(str3);
        char charAt = str4.charAt(0);
        char checkQuotes = checkQuotes(str4, charAt);
        StringBuffer stringBuffer = new StringBuffer();
        Iterator iterateChildren = findNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode = (XMPNode) iterateChildren.next();
            if (!xMPNode.getOptions().isCompositeProperty()) {
                stringBuffer.append(applyQuotes(xMPNode.value, charAt, checkQuotes, z));
                if (iterateChildren.hasNext()) {
                    stringBuffer.append(str3);
                }
            } else {
                throw new XMPException("Array items must be simple", 4);
            }
        }
        return stringBuffer.toString();
    }

    public static char checkQuotes(String str, char c) throws XMPException {
        char charAt;
        if (classifyCharacter(c) == 4) {
            if (str.length() == 1) {
                charAt = c;
            } else {
                charAt = str.charAt(1);
                if (classifyCharacter(charAt) != 4) {
                    throw new XMPException("Invalid quoting character", 4);
                }
            }
            if (charAt == getClosingQuote(c)) {
                return charAt;
            }
            throw new XMPException("Mismatched quote pair", 4);
        }
        throw new XMPException("Invalid quoting character", 4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean itemValuesMatch(com.adobe.xmp.impl.XMPNode r6, com.adobe.xmp.impl.XMPNode r7) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 170
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPUtilsImpl.itemValuesMatch(com.adobe.xmp.impl.XMPNode, com.adobe.xmp.impl.XMPNode):boolean");
    }

    public static void removeProperties(XMPMeta xMPMeta, String str, String str2, boolean z, boolean z2) throws XMPException {
        ParameterAsserts.assertImplementation(xMPMeta);
        XMPMetaImpl xMPMetaImpl = (XMPMetaImpl) xMPMeta;
        if (str2 == null || str2.length() <= 0) {
            if (str == null || str.length() <= 0) {
                Iterator iterateChildren = xMPMetaImpl.tree.iterateChildren();
                while (iterateChildren.hasNext()) {
                    if (removeSchemaChildren((XMPNode) iterateChildren.next(), z)) {
                        iterateChildren.remove();
                    }
                }
                return;
            }
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPMetaImpl.tree, str, false);
            if (findSchemaNode != null && removeSchemaChildren(findSchemaNode, z)) {
                xMPMetaImpl.tree.removeChild(findSchemaNode);
            }
            if (z2) {
                XMPAliasInfo[] findAliases = XMPMetaFactory.schema.findAliases(str);
                for (XMPAliasInfo xMPAliasInfo : findAliases) {
                    XMPNode findNode = XMPNodeUtils.findNode(xMPMetaImpl.tree, XMPPathParser.expandXPath(xMPAliasInfo.getNamespace(), xMPAliasInfo.getPropName()), false, null);
                    if (findNode != null) {
                        findNode.parent.removeChild(findNode);
                    }
                }
            }
        } else if (str == null || str.length() == 0) {
            throw new XMPException("Property name requires schema namespace", 4);
        } else {
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode2 = XMPNodeUtils.findNode(xMPMetaImpl.tree, expandXPath, false, null);
            if (findNode2 == null) {
                return;
            }
            if (z || !Utils.isInternalProperty(expandXPath.getSegment(0).name, expandXPath.getSegment(1).name)) {
                XMPNode xMPNode = findNode2.parent;
                xMPNode.removeChild(findNode2);
                if (xMPNode.getOptions().isSchemaNode() && !xMPNode.hasChildren()) {
                    xMPNode.parent.removeChild(xMPNode);
                }
            }
        }
    }

    public static boolean removeSchemaChildren(XMPNode xMPNode, boolean z) {
        Iterator iterateChildren = xMPNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
            if (z || !Utils.isInternalProperty(xMPNode.name, xMPNode2.name)) {
                iterateChildren.remove();
            }
        }
        return !xMPNode.hasChildren();
    }

    public static void separateArrayItems(XMPMeta xMPMeta, String str, String str2, String str3, PropertyOptions propertyOptions, boolean z) throws XMPException {
        String str4;
        char c;
        int i;
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        if (str3 != null) {
            ParameterAsserts.assertImplementation(xMPMeta);
            XMPNode separateFindCreateArray = separateFindCreateArray(str, str2, propertyOptions, (XMPMetaImpl) xMPMeta);
            int length = str3.length();
            int i2 = 0;
            int i3 = 0;
            char c2 = 0;
            while (i2 < length) {
                while (i2 < length) {
                    c2 = str3.charAt(i2);
                    i3 = classifyCharacter(c2);
                    if (i3 == 0 || i3 == 4) {
                        break;
                    }
                    i2++;
                }
                if (i2 < length) {
                    int i4 = 1;
                    if (i3 != 4) {
                        int i5 = i2;
                        while (i5 < length) {
                            c2 = str3.charAt(i5);
                            i3 = classifyCharacter(c2);
                            if (!(i3 == 0 || i3 == 4)) {
                                if (i3 != 2) {
                                    if (i3 != 1 || (i = i5 + 1) >= length) {
                                        break;
                                    }
                                    c2 = str3.charAt(i);
                                    int classifyCharacter = classifyCharacter(c2);
                                    if (!(classifyCharacter == 0 || classifyCharacter == 4)) {
                                        if (classifyCharacter != 2) {
                                            break;
                                        }
                                    }
                                }
                                if (!z) {
                                    break;
                                }
                            }
                            i5++;
                        }
                        str4 = str3.substring(i2, i5);
                        i2 = i5;
                    } else {
                        char closingQuote = getClosingQuote(c2);
                        i2++;
                        str4 = "";
                        char c3 = c2;
                        while (true) {
                            if (i2 >= length) {
                                c2 = c3;
                                break;
                            }
                            c3 = str3.charAt(i2);
                            i3 = classifyCharacter(c3);
                            if (i3 == 4 && isSurroundingQuote(c3, c2, closingQuote)) {
                                int i6 = i2 + 1;
                                if (i6 < length) {
                                    c = str3.charAt(i6);
                                } else {
                                    c = ';';
                                }
                                if (c3 != c) {
                                    if (isClosingingQuote(c3, c2, closingQuote)) {
                                        c2 = c3;
                                        i2 = i6;
                                        break;
                                    }
                                } else {
                                    str4 = AnonymousClass006.A01(str4, c3);
                                    i2 = i6;
                                    i2++;
                                }
                            }
                            str4 = AnonymousClass006.A01(str4, c3);
                            i2++;
                        }
                    }
                    while (true) {
                        if (i4 > separateFindCreateArray.getChildrenLength()) {
                            break;
                        } else if (!str4.equals(separateFindCreateArray.getChild(i4).value)) {
                            i4++;
                        } else if (i4 >= 0) {
                        }
                    }
                    separateFindCreateArray.addChild(new XMPNode("[]", str4, null));
                } else {
                    return;
                }
            }
            return;
        }
        throw new XMPException("Parameter must not be null", 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r1 == false) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void appendSubtree(com.adobe.xmp.impl.XMPMetaImpl r6, com.adobe.xmp.impl.XMPNode r7, com.adobe.xmp.impl.XMPNode r8, boolean r9, boolean r10) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPUtilsImpl.appendSubtree(com.adobe.xmp.impl.XMPMetaImpl, com.adobe.xmp.impl.XMPNode, com.adobe.xmp.impl.XMPNode, boolean, boolean):void");
    }
}
