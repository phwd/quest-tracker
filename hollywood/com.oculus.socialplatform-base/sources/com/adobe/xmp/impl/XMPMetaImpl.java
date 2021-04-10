package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPPathFactory;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPath;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.options.IteratorOptions;
import com.adobe.xmp.options.ParseOptions;
import com.adobe.xmp.options.PropertyOptions;
import com.adobe.xmp.properties.XMPProperty;
import java.util.Calendar;

public class XMPMetaImpl implements XMPMeta, XMPConst {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int VALUE_BASE64 = 7;
    public static final int VALUE_BOOLEAN = 1;
    public static final int VALUE_CALENDAR = 6;
    public static final int VALUE_DATE = 5;
    public static final int VALUE_DOUBLE = 4;
    public static final int VALUE_INTEGER = 2;
    public static final int VALUE_LONG = 3;
    public static final int VALUE_STRING = 0;
    public String packetHeader;
    public XMPNode tree;

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesPropertyExist(String str, String str2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            return XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null) != null;
        } catch (XMPException unused) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public byte[] getPropertyBase64(String str, String str2) throws XMPException {
        return (byte[]) getPropertyObject(str, str2, 7);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Boolean getPropertyBoolean(String str, String str2) throws XMPException {
        return (Boolean) getPropertyObject(str, str2, 1);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Calendar getPropertyCalendar(String str, String str2) throws XMPException {
        return (Calendar) getPropertyObject(str, str2, 6);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPDateTime getPropertyDate(String str, String str2) throws XMPException {
        return (XMPDateTime) getPropertyObject(str, str2, 5);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Double getPropertyDouble(String str, String str2) throws XMPException {
        return (Double) getPropertyObject(str, str2, 4);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Integer getPropertyInteger(String str, String str2) throws XMPException {
        return (Integer) getPropertyObject(str, str2, 2);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Long getPropertyLong(String str, String str2) throws XMPException {
        return (Long) getPropertyObject(str, str2, 3);
    }

    @Override // com.adobe.xmp.XMPMeta
    public String getPropertyString(String str, String str2) throws XMPException {
        return (String) getPropertyObject(str, str2, 0);
    }

    private void doSetArrayItem(XMPNode xMPNode, int i, String str, PropertyOptions propertyOptions, boolean z) throws XMPException {
        XMPNode xMPNode2 = new XMPNode("[]", null);
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, str);
        int childrenLength = xMPNode.getChildrenLength();
        if (z) {
            childrenLength++;
        }
        if (i == -1) {
            i = childrenLength;
        }
        if (1 > i || i > childrenLength) {
            throw new XMPException("Array index out of bounds", 104);
        }
        if (!z) {
            xMPNode.removeChild(i);
        }
        xMPNode.addChild(i, xMPNode2);
        setNode(xMPNode2, str, verifySetOptions, false);
    }

    private Object evaluateNodeValue(int i, XMPNode xMPNode) throws XMPException {
        String str = xMPNode.value;
        switch (i) {
            case 1:
                return new Boolean(XMPUtils.convertToBoolean(str));
            case 2:
                return new Integer(XMPUtils.convertToInteger(str));
            case 3:
                return new Long(XMPUtils.convertToLong(str));
            case 4:
                return new Double(XMPUtils.convertToDouble(str));
            case 5:
                return XMPUtils.convertToDate(str);
            case 6:
                return XMPUtils.convertToDate(str).getCalendar();
            case 7:
                return XMPUtils.decodeBase64(str);
            default:
                if (str != null || xMPNode.getOptions().isCompositeProperty()) {
                    return str;
                }
                return "";
        }
    }

    @Override // com.adobe.xmp.XMPMeta, java.lang.Object
    public Object clone() {
        return new XMPMetaImpl((XMPNode) this.tree.clone());
    }

    @Override // com.adobe.xmp.XMPMeta
    public String dumpObject() {
        return this.tree.dumpNode(true);
    }

    @Override // com.adobe.xmp.XMPMeta
    public String getObjectName() {
        String str = this.tree.name;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // com.adobe.xmp.XMPMeta
    public void normalize(ParseOptions parseOptions) throws XMPException {
        if (parseOptions == null) {
            parseOptions = new ParseOptions();
        }
        XMPNormalizer.process(this, parseOptions);
    }

    public void setNode(XMPNode xMPNode, Object obj, PropertyOptions propertyOptions, boolean z) throws XMPException {
        if (z) {
            xMPNode.clear();
        }
        xMPNode.getOptions().mergeWith(propertyOptions);
        if (!xMPNode.getOptions().isCompositeProperty()) {
            XMPNodeUtils.setNodeValue(xMPNode, obj);
        } else if (obj == null || obj.toString().length() <= 0) {
            xMPNode.children = null;
        } else {
            throw new XMPException("Composite nodes can't have values", 102);
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setObjectName(String str) {
        this.tree.name = str;
    }

    @Override // com.adobe.xmp.XMPMeta
    public void sort() {
        this.tree.sort();
    }

    @Override // com.adobe.xmp.XMPMeta
    public int countArrayItems(String str, String str2) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode == null) {
            return 0;
        }
        if (findNode.getOptions().getOption(512)) {
            return findNode.getChildrenLength();
        }
        throw new XMPException("The named property is not an array", 102);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteArrayItem(String str, String str2, int i) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertArrayName(str2);
            deleteProperty(str, XMPPathFactory.composeArrayItemPath(str2, i));
        } catch (XMPException unused) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteProperty(String str, String str2) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null);
            if (findNode != null) {
                XMPNodeUtils.deleteNode(findNode);
            }
        } catch (XMPException unused) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteQualifier(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            deleteProperty(str, AnonymousClass006.A07(str2, XMPPathFactory.composeQualifierPath(str3, str4)));
        } catch (XMPException unused) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteStructField(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertStructName(str2);
            deleteProperty(str, AnonymousClass006.A07(str2, XMPPathFactory.composeStructFieldPath(str3, str4)));
        } catch (XMPException unused) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesArrayItemExist(String str, String str2, int i) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertArrayName(str2);
            return doesPropertyExist(str, XMPPathFactory.composeArrayItemPath(str2, i));
        } catch (XMPException unused) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesQualifierExist(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertPropName(str2);
            return doesPropertyExist(str, AnonymousClass006.A07(str2, XMPPathFactory.composeQualifierPath(str3, str4)));
        } catch (XMPException unused) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesStructFieldExist(String str, String str2, String str3, String str4) {
        try {
            ParameterAsserts.assertSchemaNS(str);
            ParameterAsserts.assertStructName(str2);
            return doesPropertyExist(str, AnonymousClass006.A07(str2, XMPPathFactory.composeStructFieldPath(str3, str4)));
        } catch (XMPException unused) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getArrayItem(String str, String str2, int i) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        return getProperty(str, XMPPathFactory.composeArrayItemPath(str2, i));
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getLocalizedText(String str, String str2, String str3, String str4) throws XMPException {
        String str5;
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        ParameterAsserts.assertSpecificLang(str4);
        if (str3 != null) {
            str5 = Utils.normalizeLangValue(str3);
        } else {
            str5 = null;
        }
        String normalizeLangValue = Utils.normalizeLangValue(str4);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode != null) {
            Object[] chooseLocalizedText = XMPNodeUtils.chooseLocalizedText(findNode, str5, normalizeLangValue);
            int intValue = ((Number) chooseLocalizedText[0]).intValue();
            final XMPNode xMPNode = (XMPNode) chooseLocalizedText[1];
            if (intValue != 0) {
                return new XMPProperty() {
                    /* class com.adobe.xmp.impl.XMPMetaImpl.AnonymousClass1 */

                    @Override // com.adobe.xmp.properties.XMPProperty
                    public String getLanguage() {
                        return xMPNode.getQualifier(1).value;
                    }

                    @Override // com.adobe.xmp.properties.XMPProperty
                    public PropertyOptions getOptions() {
                        return xMPNode.getOptions();
                    }

                    @Override // com.adobe.xmp.properties.XMPProperty
                    public Object getValue() {
                        return xMPNode.value;
                    }

                    public String toString() {
                        return xMPNode.value;
                    }
                };
            }
        }
        return null;
    }

    @Override // com.adobe.xmp.XMPMeta
    public String getPacketHeader() {
        return this.packetHeader;
    }

    public Object getPropertyObject(String str, String str2, int i) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode == null) {
            return null;
        }
        if (i == 0 || !findNode.getOptions().isCompositeProperty()) {
            return evaluateNodeValue(i, findNode);
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getQualifier(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        return getProperty(str, AnonymousClass006.A07(str2, XMPPathFactory.composeQualifierPath(str3, str4)));
    }

    public XMPNode getRoot() {
        return this.tree;
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getStructField(String str, String str2, String str3, String str4) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertStructName(str2);
        return getProperty(str, AnonymousClass006.A07(str2, XMPPathFactory.composeStructFieldPath(str3, str4)));
    }

    public void setPacketHeader(String str) {
        this.packetHeader = str;
    }

    public XMPMetaImpl() {
        this.packetHeader = null;
        this.tree = new XMPNode(null, null, null);
    }

    public XMPMetaImpl(XMPNode xMPNode) {
        this.packetHeader = null;
        this.tree = xMPNode;
    }

    @Override // com.adobe.xmp.XMPMeta
    public void appendArrayItem(String str, String str2, PropertyOptions propertyOptions, String str3, PropertyOptions propertyOptions2) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        if (propertyOptions == null) {
            propertyOptions = new PropertyOptions();
        }
        if (propertyOptions.isOnlyArrayOptions()) {
            PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, null);
            XMPPath expandXPath = XMPPathParser.expandXPath(str, str2);
            XMPNode findNode = XMPNodeUtils.findNode(this.tree, expandXPath, false, null);
            if (findNode != null) {
                if (!findNode.getOptions().getOption(512)) {
                    throw new XMPException("The named property is not an array", 102);
                }
            } else if (verifySetOptions.getOption(512)) {
                findNode = XMPNodeUtils.findNode(this.tree, expandXPath, true, verifySetOptions);
                if (findNode == null) {
                    throw new XMPException("Failure creating array node", 102);
                }
            } else {
                throw new XMPException("Explicit arrayOptions required to create new array", 103);
            }
            doSetArrayItem(findNode, -1, str3, propertyOptions2, true);
            return;
        }
        throw new XMPException("Only array form flags allowed for arrayOptions", 103);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void appendArrayItem(String str, String str2, String str3) throws XMPException {
        appendArrayItem(str, str2, null, str3, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getProperty(String str, String str2) throws XMPException {
        return getProperty(str, str2, 0);
    }

    public XMPProperty getProperty(String str, String str2, int i) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        final XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode == null) {
            return null;
        }
        if (i == 0 || !findNode.getOptions().isCompositeProperty()) {
            final Object evaluateNodeValue = evaluateNodeValue(i, findNode);
            return new XMPProperty() {
                /* class com.adobe.xmp.impl.XMPMetaImpl.AnonymousClass2 */

                @Override // com.adobe.xmp.properties.XMPProperty
                public String getLanguage() {
                    return null;
                }

                @Override // com.adobe.xmp.properties.XMPProperty
                public PropertyOptions getOptions() {
                    return findNode.getOptions();
                }

                public String toString() {
                    return evaluateNodeValue.toString();
                }

                @Override // com.adobe.xmp.properties.XMPProperty
                public Object getValue() {
                    return evaluateNodeValue;
                }
            };
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void insertArrayItem(String str, String str2, int i, String str3) throws XMPException {
        insertArrayItem(str, str2, i, str3, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void insertArrayItem(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode != null) {
            doSetArrayItem(findNode, i, str3, propertyOptions, true);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPIterator iterator() throws XMPException {
        return iterator(null, null, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPIterator iterator(IteratorOptions iteratorOptions) throws XMPException {
        return iterator(null, null, iteratorOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPIterator iterator(String str, String str2, IteratorOptions iteratorOptions) throws XMPException {
        return new XMPIteratorImpl(this, str, str2, iteratorOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setArrayItem(String str, String str2, int i, String str3) throws XMPException {
        setArrayItem(str, str2, i, str3, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setArrayItem(String str, String str2, int i, String str3, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertArrayName(str2);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), false, null);
        if (findNode != null) {
            doSetArrayItem(findNode, i, str3, propertyOptions, false);
            return;
        }
        throw new XMPException("Specified array does not exist", 102);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setLocalizedText(String str, String str2, String str3, String str4, String str5) throws XMPException {
        setLocalizedText(str, str2, str3, str4, str5, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0135, code lost:
        throw new com.adobe.xmp.XMPException("Language qualifier must be first", 102);
     */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:94:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // com.adobe.xmp.XMPMeta
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLocalizedText(java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, com.adobe.xmp.options.PropertyOptions r18) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 326
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPMetaImpl.setLocalizedText(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.adobe.xmp.options.PropertyOptions):void");
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setProperty(String str, String str2, Object obj) throws XMPException {
        setProperty(str, str2, obj, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setProperty(String str, String str2, Object obj, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        PropertyOptions verifySetOptions = XMPNodeUtils.verifySetOptions(propertyOptions, obj);
        XMPNode findNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(str, str2), true, verifySetOptions);
        if (findNode != null) {
            setNode(findNode, obj, verifySetOptions, false);
            return;
        }
        throw new XMPException("Specified property does not exist", 102);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBase64(String str, String str2, byte[] bArr) throws XMPException {
        setProperty(str, str2, bArr, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBase64(String str, String str2, byte[] bArr, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, bArr, propertyOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBoolean(String str, String str2, boolean z) throws XMPException {
        setProperty(str, str2, z ? XMPConst.TRUESTR : XMPConst.FALSESTR, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBoolean(String str, String str2, boolean z, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, z ? XMPConst.TRUESTR : XMPConst.FALSESTR, propertyOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyCalendar(String str, String str2, Calendar calendar) throws XMPException {
        setProperty(str, str2, calendar, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyCalendar(String str, String str2, Calendar calendar, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, calendar, propertyOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDate(String str, String str2, XMPDateTime xMPDateTime) throws XMPException {
        setProperty(str, str2, xMPDateTime, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDate(String str, String str2, XMPDateTime xMPDateTime, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, xMPDateTime, propertyOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDouble(String str, String str2, double d) throws XMPException {
        setProperty(str, str2, new Double(d), null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDouble(String str, String str2, double d, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, new Double(d), propertyOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyInteger(String str, String str2, int i) throws XMPException {
        setProperty(str, str2, new Integer(i), null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyInteger(String str, String str2, int i, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, new Integer(i), propertyOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyLong(String str, String str2, long j) throws XMPException {
        setProperty(str, str2, new Long(j), null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyLong(String str, String str2, long j, PropertyOptions propertyOptions) throws XMPException {
        setProperty(str, str2, new Long(j), propertyOptions);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setQualifier(String str, String str2, String str3, String str4, String str5) throws XMPException {
        setQualifier(str, str2, str3, str4, str5, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setQualifier(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertPropName(str2);
        if (doesPropertyExist(str, str2)) {
            setProperty(str, AnonymousClass006.A07(str2, XMPPathFactory.composeQualifierPath(str3, str4)), str5, propertyOptions);
            return;
        }
        throw new XMPException("Specified property does not exist!", 102);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setStructField(String str, String str2, String str3, String str4, String str5) throws XMPException {
        setStructField(str, str2, str3, str4, str5, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setStructField(String str, String str2, String str3, String str4, String str5, PropertyOptions propertyOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(str);
        ParameterAsserts.assertStructName(str2);
        setProperty(str, AnonymousClass006.A07(str2, XMPPathFactory.composeStructFieldPath(str3, str4)), str5, propertyOptions);
    }
}
