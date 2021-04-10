package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPathSegment;
import com.adobe.xmp.options.PropertyOptions;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class XMPNodeUtils implements XMPConst {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CLT_FIRST_ITEM = 5;
    public static final int CLT_MULTIPLE_GENERIC = 3;
    public static final int CLT_NO_VALUES = 0;
    public static final int CLT_SINGLE_GENERIC = 2;
    public static final int CLT_SPECIFIC_MATCH = 1;
    public static final int CLT_XDEFAULT = 4;

    public static void appendLangItem(XMPNode xMPNode, String str, String str2) throws XMPException {
        XMPNode xMPNode2 = new XMPNode("[]", str2, null);
        XMPNode xMPNode3 = new XMPNode(XMPConst.XML_LANG, str, null);
        xMPNode2.addQualifier(xMPNode3);
        if (!XMPConst.X_DEFAULT.equals(xMPNode3.value)) {
            xMPNode.addChild(xMPNode2);
        } else {
            xMPNode.addChild(1, xMPNode2);
        }
    }

    public static int lookupFieldSelector(XMPNode xMPNode, String str, String str2) throws XMPException {
        int i = -1;
        for (int i2 = 1; i2 <= xMPNode.getChildrenLength() && i < 0; i2++) {
            XMPNode child = xMPNode.getChild(i2);
            if (child.getOptions().getOption(256)) {
                int i3 = 1;
                while (true) {
                    if (i3 > child.getChildrenLength()) {
                        break;
                    }
                    XMPNode child2 = child.getChild(i3);
                    if (str.equals(child2.name) && str2.equals(child2.value)) {
                        i = i2;
                        break;
                    }
                    i3++;
                }
            } else {
                throw new XMPException("Field selector must be used on array of struct", 102);
            }
        }
        return i;
    }

    public static String serializeNodeValue(Object obj) {
        String obj2;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            obj2 = XMPUtils.convertFromBoolean(((Boolean) obj).booleanValue());
        } else if (obj instanceof Integer) {
            obj2 = String.valueOf(((Number) obj).intValue());
        } else if (obj instanceof Long) {
            obj2 = String.valueOf(((Number) obj).longValue());
        } else if (obj instanceof Double) {
            obj2 = String.valueOf(((Number) obj).doubleValue());
        } else if (obj instanceof XMPDateTime) {
            obj2 = ISO8601Converter.render((XMPDateTime) obj);
        } else if (obj instanceof GregorianCalendar) {
            obj2 = ISO8601Converter.render(new XMPDateTimeImpl((Calendar) obj));
        } else if (obj instanceof byte[]) {
            obj2 = XMPUtils.encodeBase64((byte[]) obj);
        } else {
            obj2 = obj.toString();
        }
        if (obj2 != null) {
            return Utils.removeControlChars(obj2);
        }
        return null;
    }

    public static void deleteNode(XMPNode xMPNode) {
        XMPNode xMPNode2 = xMPNode.parent;
        if (xMPNode.getOptions().getOption(32)) {
            xMPNode2.removeQualifier(xMPNode);
        } else {
            xMPNode2.removeChild(xMPNode);
        }
        if (!xMPNode2.hasChildren() && xMPNode2.getOptions().isSchemaNode()) {
            xMPNode2.parent.removeChild(xMPNode2);
        }
    }

    public static int findIndexedItem(XMPNode xMPNode, String str, boolean z) throws XMPException {
        try {
            int parseInt = Integer.parseInt(str.substring(1, str.length() - 1));
            if (parseInt >= 1) {
                if (z && parseInt == xMPNode.getChildrenLength() + 1) {
                    XMPNode xMPNode2 = new XMPNode("[]", null);
                    xMPNode2.implicit = true;
                    xMPNode.addChild(xMPNode2);
                }
                return parseInt;
            }
            throw new XMPException("Array index must be larger than zero", 102);
        } catch (NumberFormatException unused) {
            throw new XMPException("Array index not digits.", 102);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0081 A[Catch:{ XMPException -> 0x009b }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0082 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.adobe.xmp.impl.XMPNode findNode(com.adobe.xmp.impl.XMPNode r7, com.adobe.xmp.impl.xpath.XMPPath r8, boolean r9, com.adobe.xmp.options.PropertyOptions r10) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPNodeUtils.findNode(com.adobe.xmp.impl.XMPNode, com.adobe.xmp.impl.xpath.XMPPath, boolean, com.adobe.xmp.options.PropertyOptions):com.adobe.xmp.impl.XMPNode");
    }

    public static XMPNode followXPathStep(XMPNode xMPNode, XMPPathSegment xMPPathSegment, boolean z) throws XMPException {
        int lookupQualSelector;
        int i = xMPPathSegment.kind;
        if (i == 1) {
            return findChildNode(xMPNode, xMPPathSegment.name, z);
        }
        if (i == 2) {
            return findQualifierNode(xMPNode, xMPPathSegment.name.substring(1), z);
        }
        if (xMPNode.getOptions().getOption(512)) {
            if (i == 3) {
                lookupQualSelector = findIndexedItem(xMPNode, xMPPathSegment.name, z);
            } else if (i == 4) {
                lookupQualSelector = xMPNode.getChildrenLength();
            } else if (i == 6) {
                String[] splitNameAndValue = Utils.splitNameAndValue(xMPPathSegment.name);
                lookupQualSelector = lookupFieldSelector(xMPNode, splitNameAndValue[0], splitNameAndValue[1]);
            } else if (i == 5) {
                String[] splitNameAndValue2 = Utils.splitNameAndValue(xMPPathSegment.name);
                lookupQualSelector = lookupQualSelector(xMPNode, splitNameAndValue2[0], splitNameAndValue2[1], xMPPathSegment.aliasForm);
            } else {
                throw new XMPException("Unknown array indexing step in FollowXPathStep", 9);
            }
            if (1 > lookupQualSelector || lookupQualSelector > xMPNode.getChildrenLength()) {
                return null;
            }
            return xMPNode.getChild(lookupQualSelector);
        }
        throw new XMPException("Indexing applied to non-array", 102);
    }

    public static int lookupQualSelector(XMPNode xMPNode, String str, String str2, int i) throws XMPException {
        if (XMPConst.XML_LANG.equals(str)) {
            int lookupLanguageItem = lookupLanguageItem(xMPNode, Utils.normalizeLangValue(str2));
            if (lookupLanguageItem >= 0 || (i & 4096) <= 0) {
                return lookupLanguageItem;
            }
            XMPNode xMPNode2 = new XMPNode("[]", null);
            xMPNode2.addQualifier(new XMPNode(XMPConst.XML_LANG, XMPConst.X_DEFAULT, null));
            xMPNode.addChild(1, xMPNode2);
            return 1;
        }
        for (int i2 = 1; i2 < xMPNode.getChildrenLength(); i2++) {
            Iterator iterateQualifier = xMPNode.getChild(i2).iterateQualifier();
            while (iterateQualifier.hasNext()) {
                XMPNode xMPNode3 = (XMPNode) iterateQualifier.next();
                if (str.equals(xMPNode3.name) && str2.equals(xMPNode3.value)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    public static PropertyOptions verifySetOptions(PropertyOptions propertyOptions, Object obj) throws XMPException {
        if (propertyOptions == null) {
            propertyOptions = new PropertyOptions();
        }
        if (propertyOptions.getOption(4096)) {
            propertyOptions.setOption(2048, true);
        }
        if (propertyOptions.getOption(2048)) {
            propertyOptions.setOption(1024, true);
        }
        if (propertyOptions.getOption(1024)) {
            propertyOptions.setOption(512, true);
        }
        if (!propertyOptions.isCompositeProperty() || obj == null || obj.toString().length() <= 0) {
            propertyOptions.assertConsistency(propertyOptions.options);
            return propertyOptions;
        }
        throw new XMPException("Structs and arrays can't have values", 103);
    }

    public static Object[] chooseLocalizedText(XMPNode xMPNode, String str, String str2) throws XMPException {
        Object[] objArr;
        Integer num;
        if (xMPNode.getOptions().getOption(4096)) {
            XMPNode xMPNode2 = null;
            if (!xMPNode.hasChildren()) {
                return new Object[]{new Integer(0), null};
            }
            Iterator iterateChildren = xMPNode.iterateChildren();
            XMPNode xMPNode3 = null;
            int i = 0;
            while (iterateChildren.hasNext()) {
                XMPNode xMPNode4 = (XMPNode) iterateChildren.next();
                if (xMPNode4.getOptions().isCompositeProperty()) {
                    throw new XMPException("Alt-text array item is not simple", 102);
                } else if (!xMPNode4.hasQualifier() || !XMPConst.XML_LANG.equals(xMPNode4.getQualifier(1).name)) {
                    throw new XMPException("Alt-text array item has no language qualifier", 102);
                } else {
                    String str3 = xMPNode4.getQualifier(1).value;
                    if (str2.equals(str3)) {
                        return new Object[]{new Integer(1), xMPNode4};
                    }
                    if (str != null && str3.startsWith(str)) {
                        if (xMPNode2 == null) {
                            xMPNode2 = xMPNode4;
                        }
                        i++;
                    } else if (XMPConst.X_DEFAULT.equals(str3)) {
                        xMPNode3 = xMPNode4;
                    }
                }
            }
            if (i == 1) {
                objArr = new Object[2];
                num = new Integer(2);
            } else if (i > 1) {
                objArr = new Object[2];
                num = new Integer(3);
            } else if (xMPNode3 != null) {
                return new Object[]{new Integer(4), xMPNode3};
            } else {
                return new Object[]{new Integer(5), xMPNode.getChild(1)};
            }
            objArr[0] = num;
            objArr[1] = xMPNode2;
            return objArr;
        }
        throw new XMPException("Localized text array is not alt-text", 102);
    }

    public static void detectAltText(XMPNode xMPNode) {
        if (xMPNode.getOptions().getOption(2048) && xMPNode.hasChildren()) {
            Iterator iterateChildren = xMPNode.iterateChildren();
            while (iterateChildren.hasNext()) {
                if (((XMPNode) iterateChildren.next()).getOptions().getOption(64)) {
                    xMPNode.getOptions().setOption(4096, true);
                    normalizeLangArray(xMPNode);
                    return;
                }
            }
        }
    }

    public static XMPNode findChildNode(XMPNode xMPNode, String str, boolean z) throws XMPException {
        if (!xMPNode.getOptions().isSchemaNode() && !xMPNode.getOptions().getOption(256)) {
            if (!xMPNode.implicit) {
                throw new XMPException("Named children only allowed for schemas and structs", 102);
            } else if (xMPNode.getOptions().getOption(512)) {
                throw new XMPException("Named children not allowed for arrays", 102);
            } else if (z) {
                xMPNode.getOptions().setOption(256, true);
            }
        }
        XMPNode findChildByName = xMPNode.findChildByName(str);
        if (findChildByName != null || !z) {
            return findChildByName;
        }
        XMPNode xMPNode2 = new XMPNode(str, new PropertyOptions());
        xMPNode2.implicit = true;
        xMPNode.addChild(xMPNode2);
        return xMPNode2;
    }

    public static XMPNode findQualifierNode(XMPNode xMPNode, String str, boolean z) throws XMPException {
        XMPNode findQualifierByName = xMPNode.findQualifierByName(str);
        if (findQualifierByName != null || !z) {
            return findQualifierByName;
        }
        XMPNode xMPNode2 = new XMPNode(str, null);
        xMPNode2.implicit = true;
        xMPNode.addQualifier(xMPNode2);
        return xMPNode2;
    }

    public static int lookupLanguageItem(XMPNode xMPNode, String str) throws XMPException {
        if (xMPNode.getOptions().getOption(512)) {
            for (int i = 1; i <= xMPNode.getChildrenLength(); i++) {
                XMPNode child = xMPNode.getChild(i);
                if (child.hasQualifier() && XMPConst.XML_LANG.equals(child.getQualifier(1).name) && str.equals(child.getQualifier(1).value)) {
                    return i;
                }
            }
            return -1;
        }
        throw new XMPException("Language item must be used on array", 102);
    }

    public static void normalizeLangArray(XMPNode xMPNode) {
        if (xMPNode.getOptions().getOption(4096)) {
            for (int i = 2; i <= xMPNode.getChildrenLength(); i++) {
                XMPNode child = xMPNode.getChild(i);
                if (child.hasQualifier() && XMPConst.X_DEFAULT.equals(child.getQualifier(1).value)) {
                    try {
                        xMPNode.removeChild(i);
                        xMPNode.addChild(1, child);
                    } catch (XMPException unused) {
                    }
                    if (i == 2) {
                        xMPNode.getChild(2).value = child.value;
                        return;
                    }
                    return;
                }
            }
        }
    }

    public static void setNodeValue(XMPNode xMPNode, Object obj) {
        String serializeNodeValue = serializeNodeValue(obj);
        if (xMPNode.getOptions().getOption(32) && XMPConst.XML_LANG.equals(xMPNode.name)) {
            serializeNodeValue = Utils.normalizeLangValue(serializeNodeValue);
        }
        xMPNode.value = serializeNodeValue;
    }

    public static XMPNode findSchemaNode(XMPNode xMPNode, String str, String str2, boolean z) throws XMPException {
        XMPNode findChildByName = xMPNode.findChildByName(str);
        if (findChildByName == null && z) {
            PropertyOptions propertyOptions = new PropertyOptions();
            propertyOptions.setOption(Integer.MIN_VALUE, true);
            findChildByName = new XMPNode(str, propertyOptions);
            findChildByName.implicit = true;
            String namespacePrefix = XMPMetaFactory.schema.getNamespacePrefix(str);
            if (namespacePrefix == null) {
                if (str2 == null || str2.length() == 0) {
                    throw new XMPException("Unregistered schema namespace URI", 101);
                }
                namespacePrefix = XMPMetaFactory.schema.registerNamespace(str, str2);
            }
            findChildByName.value = namespacePrefix;
            xMPNode.addChild(findChildByName);
        }
        return findChildByName;
    }

    public static XMPNode findSchemaNode(XMPNode xMPNode, String str, boolean z) throws XMPException {
        return findSchemaNode(xMPNode, str, null, z);
    }
}
