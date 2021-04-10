package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.ParseOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPAliasInfo;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XMPNormalizer {
    public static Map dcArrayForms;

    public static void fixGPSTimeStamp(XMPNode xMPNode) throws XMPException {
        XMPNode findChildNode = XMPNodeUtils.findChildNode(xMPNode, "exif:GPSTimeStamp", false);
        if (findChildNode != null) {
            try {
                XMPDateTime convertToDate = XMPUtils.convertToDate(findChildNode.value);
                if (convertToDate.getYear() == 0 && convertToDate.getMonth() == 0 && convertToDate.getDay() == 0) {
                    XMPNode findChildNode2 = XMPNodeUtils.findChildNode(xMPNode, "exif:DateTimeOriginal", false);
                    if (findChildNode2 == null) {
                        findChildNode2 = XMPNodeUtils.findChildNode(xMPNode, "exif:DateTimeDigitized", false);
                    }
                    XMPDateTime convertToDate2 = XMPUtils.convertToDate(findChildNode2.value);
                    Calendar calendar = convertToDate.getCalendar();
                    calendar.set(1, convertToDate2.getYear());
                    calendar.set(2, convertToDate2.getMonth());
                    calendar.set(5, convertToDate2.getDay());
                    findChildNode.value = ISO8601Converter.render(new XMPDateTimeImpl(calendar));
                }
            } catch (XMPException unused) {
            }
        }
    }

    public static void normalizeDCArrays(XMPNode xMPNode) throws XMPException {
        for (int i = 1; i <= xMPNode.getChildrenLength(); i++) {
            XMPNode child = xMPNode.getChild(i);
            PropertyOptions propertyOptions = (PropertyOptions) dcArrayForms.get(child.name);
            if (propertyOptions != null) {
                if (child.getOptions().isSimple()) {
                    XMPNode xMPNode2 = new XMPNode(child.name, propertyOptions);
                    child.name = "[]";
                    xMPNode2.addChild(child);
                    xMPNode.replaceChild(i, xMPNode2);
                    if (propertyOptions.getOption(4096) && !child.getOptions().getOption(64)) {
                        child.addQualifier(new XMPNode(XMPConst.XML_LANG, XMPConst.X_DEFAULT, null));
                    }
                } else {
                    child.getOptions().setOption(7680, false);
                    child.getOptions().mergeWith(propertyOptions);
                    if (propertyOptions.getOption(4096)) {
                        repairAltText(child);
                    }
                }
            }
        }
    }

    public static void compareAliasedSubtrees(XMPNode xMPNode, XMPNode xMPNode2, boolean z) throws XMPException {
        if (!xMPNode.value.equals(xMPNode2.value) || xMPNode.getChildrenLength() != xMPNode2.getChildrenLength()) {
            throw new XMPException("Mismatch between alias and base nodes", 203);
        } else if (z || (xMPNode.name.equals(xMPNode2.name) && xMPNode.getOptions().equals(xMPNode2.getOptions()) && xMPNode.getQualifierLength() == xMPNode2.getQualifierLength())) {
            Iterator iterateChildren = xMPNode.iterateChildren();
            Iterator iterateChildren2 = xMPNode2.iterateChildren();
            while (iterateChildren.hasNext() && iterateChildren2.hasNext()) {
                compareAliasedSubtrees((XMPNode) iterateChildren.next(), (XMPNode) iterateChildren2.next(), false);
            }
            Iterator iterateQualifier = xMPNode.iterateQualifier();
            Iterator iterateQualifier2 = xMPNode2.iterateQualifier();
            while (iterateQualifier.hasNext() && iterateQualifier2.hasNext()) {
                compareAliasedSubtrees((XMPNode) iterateQualifier.next(), (XMPNode) iterateQualifier2.next(), false);
            }
        } else {
            throw new XMPException("Mismatch between alias and base nodes", 203);
        }
    }

    public static void initDCArrays() {
        HashMap hashMap = new HashMap();
        dcArrayForms = hashMap;
        PropertyOptions propertyOptions = new PropertyOptions();
        propertyOptions.setOption(512, true);
        hashMap.put("dc:contributor", propertyOptions);
        dcArrayForms.put("dc:language", propertyOptions);
        dcArrayForms.put("dc:publisher", propertyOptions);
        dcArrayForms.put("dc:relation", propertyOptions);
        dcArrayForms.put("dc:subject", propertyOptions);
        dcArrayForms.put("dc:type", propertyOptions);
        PropertyOptions propertyOptions2 = new PropertyOptions();
        propertyOptions2.setOption(512, true);
        propertyOptions2.setOption(1024, true);
        dcArrayForms.put("dc:creator", propertyOptions2);
        dcArrayForms.put("dc:date", propertyOptions2);
        PropertyOptions propertyOptions3 = new PropertyOptions();
        propertyOptions3.setOption(512, true);
        propertyOptions3.setOption(1024, true);
        propertyOptions3.setOption(2048, true);
        propertyOptions3.setOption(4096, true);
        dcArrayForms.put("dc:description", propertyOptions3);
        dcArrayForms.put("dc:rights", propertyOptions3);
        dcArrayForms.put("dc:title", propertyOptions3);
    }

    public static void migrateAudioCopyright(XMPMeta xMPMeta, XMPNode xMPNode) {
        try {
            XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(((XMPMetaImpl) xMPMeta).tree, XMPConst.NS_DC, true);
            String str = xMPNode.value;
            XMPNode findChildNode = XMPNodeUtils.findChildNode(findSchemaNode, "dc:rights", false);
            if (findChildNode == null || !findChildNode.hasChildren()) {
                xMPMeta.setLocalizedText(XMPConst.NS_DC, "rights", "", XMPConst.X_DEFAULT, AnonymousClass006.A07("\n\n", str), null);
            } else {
                int lookupLanguageItem = XMPNodeUtils.lookupLanguageItem(findChildNode, XMPConst.X_DEFAULT);
                if (lookupLanguageItem < 0) {
                    xMPMeta.setLocalizedText(XMPConst.NS_DC, "rights", "", XMPConst.X_DEFAULT, findChildNode.getChild(1).value, null);
                    lookupLanguageItem = XMPNodeUtils.lookupLanguageItem(findChildNode, XMPConst.X_DEFAULT);
                }
                XMPNode child = findChildNode.getChild(lookupLanguageItem);
                String str2 = child.value;
                int indexOf = str2.indexOf("\n\n");
                if (indexOf >= 0) {
                    int i = indexOf + 2;
                    if (!str2.substring(i).equals(str)) {
                        child.value = AnonymousClass006.A07(str2.substring(0, i), str);
                    }
                } else if (!str.equals(str2)) {
                    child.value = AnonymousClass006.A09(str2, "\n\n", str);
                }
            }
            xMPNode.parent.removeChild(xMPNode);
        } catch (XMPException unused) {
        }
    }

    public static void moveExplicitAliases(XMPNode xMPNode, ParseOptions parseOptions) throws XMPException {
        XMPNode child;
        if (xMPNode.hasAliases) {
            xMPNode.hasAliases = false;
            boolean option = parseOptions.getOption(4);
            for (XMPNode xMPNode2 : xMPNode.getUnmodifiableChildren()) {
                if (xMPNode2.hasAliases) {
                    Iterator iterateChildren = xMPNode2.iterateChildren();
                    while (iterateChildren.hasNext()) {
                        XMPNode xMPNode3 = (XMPNode) iterateChildren.next();
                        if (xMPNode3.alias) {
                            xMPNode3.alias = false;
                            XMPAliasInfo findAlias = XMPMetaFactory.schema.findAlias(xMPNode3.name);
                            if (findAlias != null) {
                                XMPNode findSchemaNode = XMPNodeUtils.findSchemaNode(xMPNode, findAlias.getNamespace(), null, true);
                                findSchemaNode.implicit = false;
                                XMPNode findChildNode = XMPNodeUtils.findChildNode(findSchemaNode, AnonymousClass006.A07(findAlias.getPrefix(), findAlias.getPropName()), false);
                                if (findChildNode == null) {
                                    if (findAlias.getAliasForm().isSimple()) {
                                        xMPNode3.name = AnonymousClass006.A07(findAlias.getPrefix(), findAlias.getPropName());
                                        findSchemaNode.addChild(xMPNode3);
                                    } else {
                                        XMPNode xMPNode4 = new XMPNode(AnonymousClass006.A07(findAlias.getPrefix(), findAlias.getPropName()), findAlias.getAliasForm().toPropertyOptions());
                                        findSchemaNode.addChild(xMPNode4);
                                        transplantArrayItemAlias(iterateChildren, xMPNode3, xMPNode4);
                                    }
                                } else if (!findAlias.getAliasForm().isSimple()) {
                                    if (findAlias.getAliasForm().getOption(4096)) {
                                        int lookupLanguageItem = XMPNodeUtils.lookupLanguageItem(findChildNode, XMPConst.X_DEFAULT);
                                        if (lookupLanguageItem != -1) {
                                            child = findChildNode.getChild(lookupLanguageItem);
                                        }
                                        transplantArrayItemAlias(iterateChildren, xMPNode3, findChildNode);
                                    } else {
                                        if (findChildNode.hasChildren()) {
                                            child = findChildNode.getChild(1);
                                        }
                                        transplantArrayItemAlias(iterateChildren, xMPNode3, findChildNode);
                                    }
                                    if (child != null) {
                                        if (option) {
                                            compareAliasedSubtrees(xMPNode3, child, true);
                                        }
                                    }
                                    transplantArrayItemAlias(iterateChildren, xMPNode3, findChildNode);
                                } else if (option) {
                                    compareAliasedSubtrees(xMPNode3, findChildNode, true);
                                }
                                iterateChildren.remove();
                            }
                        }
                    }
                    xMPNode2.hasAliases = false;
                }
            }
        }
    }

    public static XMPMeta process(XMPMetaImpl xMPMetaImpl, ParseOptions parseOptions) throws XMPException {
        XMPNode xMPNode = xMPMetaImpl.tree;
        touchUpDataModel(xMPMetaImpl);
        moveExplicitAliases(xMPNode, parseOptions);
        tweakOldXMP(xMPNode);
        deleteEmptySchemas(xMPNode);
        return xMPMetaImpl;
    }

    public static void repairAltText(XMPNode xMPNode) throws XMPException {
        if (xMPNode != null && xMPNode.getOptions().getOption(512)) {
            PropertyOptions options = xMPNode.getOptions();
            options.setOption(1024, true);
            options.setOption(2048, true);
            options.setOption(4096, true);
            Iterator iterateChildren = xMPNode.iterateChildren();
            while (iterateChildren.hasNext()) {
                XMPNode xMPNode2 = (XMPNode) iterateChildren.next();
                if (!xMPNode2.getOptions().isCompositeProperty()) {
                    if (!xMPNode2.getOptions().getOption(64)) {
                        String str = xMPNode2.value;
                        if (!(str == null || str.length() == 0)) {
                            xMPNode2.addQualifier(new XMPNode(XMPConst.XML_LANG, "x-repair", null));
                        }
                    }
                }
                iterateChildren.remove();
            }
        }
    }

    public static void touchUpDataModel(XMPMetaImpl xMPMetaImpl) throws XMPException {
        String str;
        XMPNodeUtils.findSchemaNode(xMPMetaImpl.tree, XMPConst.NS_DC, true);
        Iterator iterateChildren = xMPMetaImpl.tree.iterateChildren();
        while (iterateChildren.hasNext()) {
            XMPNode xMPNode = (XMPNode) iterateChildren.next();
            String str2 = xMPNode.name;
            if (XMPConst.NS_DC.equals(str2)) {
                normalizeDCArrays(xMPNode);
            } else {
                if (XMPConst.NS_EXIF.equals(str2)) {
                    fixGPSTimeStamp(xMPNode);
                    str = "exif:UserComment";
                } else if (XMPConst.NS_DM.equals(str2)) {
                    XMPNode findChildNode = XMPNodeUtils.findChildNode(xMPNode, "xmpDM:copyright", false);
                    if (findChildNode != null) {
                        migrateAudioCopyright(xMPMetaImpl, findChildNode);
                    }
                } else if (XMPConst.NS_XMP_RIGHTS.equals(str2)) {
                    str = "xmpRights:UsageTerms";
                }
                XMPNode findChildNode2 = XMPNodeUtils.findChildNode(xMPNode, str, false);
                if (findChildNode2 != null) {
                    repairAltText(findChildNode2);
                }
            }
        }
    }

    public static void tweakOldXMP(XMPNode xMPNode) throws XMPException {
        String str = xMPNode.name;
        if (str != null && str.length() >= 36) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.startsWith("uuid:")) {
                lowerCase = lowerCase.substring(5);
            }
            if (Utils.checkUUIDFormat(lowerCase)) {
                XMPNode findNode = XMPNodeUtils.findNode(xMPNode, XMPPathParser.expandXPath(XMPConst.NS_XMP_MM, "InstanceID"), true, null);
                if (findNode != null) {
                    findNode.options = null;
                    findNode.value = AnonymousClass006.A07("uuid:", lowerCase);
                    findNode.children = null;
                    findNode.removeQualifiers();
                    xMPNode.name = null;
                    return;
                }
                throw new XMPException("Failure creating xmpMM:InstanceID", 9);
            }
        }
    }

    static {
        initDCArrays();
    }

    public static void deleteEmptySchemas(XMPNode xMPNode) {
        Iterator iterateChildren = xMPNode.iterateChildren();
        while (iterateChildren.hasNext()) {
            if (!((XMPNode) iterateChildren.next()).hasChildren()) {
                iterateChildren.remove();
            }
        }
    }

    public static void transplantArrayItemAlias(Iterator it, XMPNode xMPNode, XMPNode xMPNode2) throws XMPException {
        if (xMPNode2.getOptions().getOption(4096)) {
            if (!xMPNode.getOptions().getOption(64)) {
                xMPNode.addQualifier(new XMPNode(XMPConst.XML_LANG, XMPConst.X_DEFAULT, null));
            } else {
                throw new XMPException("Alias to x-default already has a language qualifier", 203);
            }
        }
        it.remove();
        xMPNode.name = "[]";
        xMPNode2.addChild(xMPNode);
    }
}
