package com.adobe.xmp.impl;

import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPError;
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

public class XMPMetaImpl implements XMPConst, XMPMeta {
    static final /* synthetic */ boolean $assertionsDisabled = (!XMPMetaImpl.class.desiredAssertionStatus());
    private static final int VALUE_BASE64 = 7;
    private static final int VALUE_BOOLEAN = 1;
    private static final int VALUE_CALENDAR = 6;
    private static final int VALUE_DATE = 5;
    private static final int VALUE_DOUBLE = 4;
    private static final int VALUE_INTEGER = 2;
    private static final int VALUE_LONG = 3;
    private static final int VALUE_STRING = 0;
    private String packetHeader;
    private XMPNode tree;

    public XMPMetaImpl() {
        this.packetHeader = null;
        this.tree = new XMPNode(null, null, null);
    }

    public XMPMetaImpl(XMPNode tree2) {
        this.packetHeader = null;
        this.tree = tree2;
    }

    @Override // com.adobe.xmp.XMPMeta
    public void appendArrayItem(String schemaNS, String arrayName, PropertyOptions arrayOptions, String itemValue, PropertyOptions itemOptions) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        if (arrayOptions == null) {
            arrayOptions = new PropertyOptions();
        }
        if (!arrayOptions.isOnlyArrayOptions()) {
            throw new XMPException("Only array form flags allowed for arrayOptions", XMPError.BADOPTIONS);
        }
        PropertyOptions arrayOptions2 = XMPNodeUtils.verifySetOptions(arrayOptions, null);
        XMPPath arrayPath = XMPPathParser.expandXPath(schemaNS, arrayName);
        XMPNode arrayNode = XMPNodeUtils.findNode(this.tree, arrayPath, false, null);
        if (arrayNode != null) {
            if (!arrayNode.getOptions().isArray()) {
                throw new XMPException("The named property is not an array", XMPError.BADXPATH);
            }
        } else if (arrayOptions2.isArray()) {
            arrayNode = XMPNodeUtils.findNode(this.tree, arrayPath, true, arrayOptions2);
            if (arrayNode == null) {
                throw new XMPException("Failure creating array node", XMPError.BADXPATH);
            }
        } else {
            throw new XMPException("Explicit arrayOptions required to create new array", XMPError.BADOPTIONS);
        }
        doSetArrayItem(arrayNode, -1, itemValue, itemOptions, true);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void appendArrayItem(String schemaNS, String arrayName, String itemValue) throws XMPException {
        appendArrayItem(schemaNS, arrayName, null, itemValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public int countArrayItems(String schemaNS, String arrayName) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        XMPNode arrayNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, arrayName), false, null);
        if (arrayNode == null) {
            return 0;
        }
        if (arrayNode.getOptions().isArray()) {
            return arrayNode.getChildrenLength();
        }
        throw new XMPException("The named property is not an array", XMPError.BADXPATH);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteArrayItem(String schemaNS, String arrayName, int itemIndex) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertArrayName(arrayName);
            deleteProperty(schemaNS, XMPPathFactory.composeArrayItemPath(arrayName, itemIndex));
        } catch (XMPException e) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteProperty(String schemaNS, String propName) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertPropName(propName);
            XMPNode propNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, propName), false, null);
            if (propNode != null) {
                XMPNodeUtils.deleteNode(propNode);
            }
        } catch (XMPException e) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteQualifier(String schemaNS, String propName, String qualNS, String qualName) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertPropName(propName);
            deleteProperty(schemaNS, propName + XMPPathFactory.composeQualifierPath(qualNS, qualName));
        } catch (XMPException e) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public void deleteStructField(String schemaNS, String structName, String fieldNS, String fieldName) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertStructName(structName);
            deleteProperty(schemaNS, structName + XMPPathFactory.composeStructFieldPath(fieldNS, fieldName));
        } catch (XMPException e) {
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesPropertyExist(String schemaNS, String propName) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertPropName(propName);
            if (XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, propName), false, null) != null) {
                return true;
            }
            return false;
        } catch (XMPException e) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesArrayItemExist(String schemaNS, String arrayName, int itemIndex) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertArrayName(arrayName);
            return doesPropertyExist(schemaNS, XMPPathFactory.composeArrayItemPath(arrayName, itemIndex));
        } catch (XMPException e) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesStructFieldExist(String schemaNS, String structName, String fieldNS, String fieldName) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertStructName(structName);
            return doesPropertyExist(schemaNS, structName + XMPPathFactory.composeStructFieldPath(fieldNS, fieldName));
        } catch (XMPException e) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public boolean doesQualifierExist(String schemaNS, String propName, String qualNS, String qualName) {
        try {
            ParameterAsserts.assertSchemaNS(schemaNS);
            ParameterAsserts.assertPropName(propName);
            return doesPropertyExist(schemaNS, propName + XMPPathFactory.composeQualifierPath(qualNS, qualName));
        } catch (XMPException e) {
            return false;
        }
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getArrayItem(String schemaNS, String arrayName, int itemIndex) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        return getProperty(schemaNS, XMPPathFactory.composeArrayItemPath(arrayName, itemIndex));
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getLocalizedText(String schemaNS, String altTextName, String genericLang, String specificLang) throws XMPException {
        String genericLang2;
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(altTextName);
        ParameterAsserts.assertSpecificLang(specificLang);
        if (genericLang != null) {
            genericLang2 = Utils.normalizeLangValue(genericLang);
        } else {
            genericLang2 = null;
        }
        String specificLang2 = Utils.normalizeLangValue(specificLang);
        XMPNode arrayNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, altTextName), false, null);
        if (arrayNode == null) {
            return null;
        }
        Object[] result = XMPNodeUtils.chooseLocalizedText(arrayNode, genericLang2, specificLang2);
        int match = ((Integer) result[0]).intValue();
        final XMPNode itemNode = (XMPNode) result[1];
        if (match != 0) {
            return new XMPProperty() {
                /* class com.adobe.xmp.impl.XMPMetaImpl.AnonymousClass1 */

                @Override // com.adobe.xmp.properties.XMPProperty
                public Object getValue() {
                    return itemNode.getValue();
                }

                @Override // com.adobe.xmp.properties.XMPProperty
                public PropertyOptions getOptions() {
                    return itemNode.getOptions();
                }

                @Override // com.adobe.xmp.properties.XMPProperty
                public String getLanguage() {
                    return itemNode.getQualifier(1).getValue();
                }

                public String toString() {
                    return itemNode.getValue().toString();
                }
            };
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a8, code lost:
        if (r11 == null) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00af, code lost:
        if (r2.getChildrenLength() <= 1) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b1, code lost:
        r2.removeChild(r11);
        r2.addChild(1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b8, code lost:
        r9 = com.adobe.xmp.impl.XMPNodeUtils.chooseLocalizedText(r2, r19, r20);
        r8 = ((java.lang.Integer) r9[0]).intValue();
        r7 = (com.adobe.xmp.impl.XMPNode) r9[1];
        r10 = com.adobe.xmp.XMPConst.X_DEFAULT.equals(r20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d6, code lost:
        switch(r8) {
            case 0: goto L_0x00e3;
            case 1: goto L_0x0105;
            case 2: goto L_0x0166;
            case 3: goto L_0x0186;
            case 4: goto L_0x0192;
            case 5: goto L_0x01a9;
            default: goto L_0x00d9;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00e2, code lost:
        throw new com.adobe.xmp.XMPException("Unexpected result from ChooseLocalizedText", 9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00e3, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, com.adobe.xmp.XMPConst.X_DEFAULT, r21);
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00eb, code lost:
        if (r10 != false) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00ed, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, r20, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f4, code lost:
        if (r5 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fb, code lost:
        if (r2.getChildrenLength() != 1) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00fd, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, com.adobe.xmp.XMPConst.X_DEFAULT, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0105, code lost:
        if (r10 != false) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0107, code lost:
        if (r5 == false) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0109, code lost:
        if (r11 == r7) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x010b, code lost:
        if (r11 == null) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0119, code lost:
        if (r11.getValue().equals(r7.getValue()) == false) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011b, code lost:
        r11.setValue(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0120, code lost:
        r7.setValue(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0128, code lost:
        if (com.adobe.xmp.impl.XMPMetaImpl.$assertionsDisabled != false) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012a, code lost:
        if (r5 == false) goto L_0x012e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x012c, code lost:
        if (r11 == r7) goto L_0x0134;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0133, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0134, code lost:
        r6 = r2.iterateChildren();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x013c, code lost:
        if (r6.hasNext() == false) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x013e, code lost:
        r4 = (com.adobe.xmp.impl.XMPNode) r6.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0144, code lost:
        if (r4 == r11) goto L_0x0138;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0146, code lost:
        r13 = r4.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014a, code lost:
        if (r11 == null) goto L_0x015c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014c, code lost:
        r12 = r11.getValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0154, code lost:
        if (r13.equals(r12) == false) goto L_0x0138;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0156, code lost:
        r4.setValue(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x015c, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x015e, code lost:
        if (r11 == null) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0160, code lost:
        r11.setValue(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0166, code lost:
        if (r5 == false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0168, code lost:
        if (r11 == r7) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x016a, code lost:
        if (r11 == null) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0178, code lost:
        if (r11.getValue().equals(r7.getValue()) == false) goto L_0x017f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x017a, code lost:
        r11.setValue(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x017f, code lost:
        r7.setValue(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0186, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, r20, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x018d, code lost:
        if (r10 == false) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x018f, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0192, code lost:
        if (r11 == null) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0199, code lost:
        if (r2.getChildrenLength() != 1) goto L_0x01a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x019b, code lost:
        r11.setValue(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01a0, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, r20, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01a9, code lost:
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, r20, r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b0, code lost:
        if (r10 == false) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01b2, code lost:
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:?, code lost:
        return;
     */
    @Override // com.adobe.xmp.XMPMeta
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLocalizedText(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, com.adobe.xmp.options.PropertyOptions r22) throws com.adobe.xmp.XMPException {
        /*
        // Method dump skipped, instructions count: 454
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPMetaImpl.setLocalizedText(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.adobe.xmp.options.PropertyOptions):void");
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setLocalizedText(String schemaNS, String altTextName, String genericLang, String specificLang, String itemValue) throws XMPException {
        setLocalizedText(schemaNS, altTextName, genericLang, specificLang, itemValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getProperty(String schemaNS, String propName) throws XMPException {
        return getProperty(schemaNS, propName, 0);
    }

    /* access modifiers changed from: protected */
    public XMPProperty getProperty(String schemaNS, String propName, int valueType) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertPropName(propName);
        final XMPNode propNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, propName), false, null);
        if (propNode == null) {
            return null;
        }
        if (valueType == 0 || !propNode.getOptions().isCompositeProperty()) {
            final Object value = evaluateNodeValue(valueType, propNode);
            return new XMPProperty() {
                /* class com.adobe.xmp.impl.XMPMetaImpl.AnonymousClass2 */

                @Override // com.adobe.xmp.properties.XMPProperty
                public Object getValue() {
                    return value;
                }

                @Override // com.adobe.xmp.properties.XMPProperty
                public PropertyOptions getOptions() {
                    return propNode.getOptions();
                }

                @Override // com.adobe.xmp.properties.XMPProperty
                public String getLanguage() {
                    return null;
                }

                public String toString() {
                    return value.toString();
                }
            };
        }
        throw new XMPException("Property must be simple when a value type is requested", XMPError.BADXPATH);
    }

    /* access modifiers changed from: protected */
    public Object getPropertyObject(String schemaNS, String propName, int valueType) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertPropName(propName);
        XMPNode propNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, propName), false, null);
        if (propNode == null) {
            return null;
        }
        if (valueType == 0 || !propNode.getOptions().isCompositeProperty()) {
            return evaluateNodeValue(valueType, propNode);
        }
        throw new XMPException("Property must be simple when a value type is requested", XMPError.BADXPATH);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Boolean getPropertyBoolean(String schemaNS, String propName) throws XMPException {
        return (Boolean) getPropertyObject(schemaNS, propName, 1);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBoolean(String schemaNS, String propName, boolean propValue, PropertyOptions options) throws XMPException {
        setProperty(schemaNS, propName, propValue ? XMPConst.TRUESTR : XMPConst.FALSESTR, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBoolean(String schemaNS, String propName, boolean propValue) throws XMPException {
        setProperty(schemaNS, propName, propValue ? XMPConst.TRUESTR : XMPConst.FALSESTR, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Integer getPropertyInteger(String schemaNS, String propName) throws XMPException {
        return (Integer) getPropertyObject(schemaNS, propName, 2);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyInteger(String schemaNS, String propName, int propValue, PropertyOptions options) throws XMPException {
        setProperty(schemaNS, propName, new Integer(propValue), options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyInteger(String schemaNS, String propName, int propValue) throws XMPException {
        setProperty(schemaNS, propName, new Integer(propValue), null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Long getPropertyLong(String schemaNS, String propName) throws XMPException {
        return (Long) getPropertyObject(schemaNS, propName, 3);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyLong(String schemaNS, String propName, long propValue, PropertyOptions options) throws XMPException {
        setProperty(schemaNS, propName, new Long(propValue), options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyLong(String schemaNS, String propName, long propValue) throws XMPException {
        setProperty(schemaNS, propName, new Long(propValue), null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Double getPropertyDouble(String schemaNS, String propName) throws XMPException {
        return (Double) getPropertyObject(schemaNS, propName, 4);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDouble(String schemaNS, String propName, double propValue, PropertyOptions options) throws XMPException {
        setProperty(schemaNS, propName, new Double(propValue), options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDouble(String schemaNS, String propName, double propValue) throws XMPException {
        setProperty(schemaNS, propName, new Double(propValue), null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPDateTime getPropertyDate(String schemaNS, String propName) throws XMPException {
        return (XMPDateTime) getPropertyObject(schemaNS, propName, 5);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDate(String schemaNS, String propName, XMPDateTime propValue, PropertyOptions options) throws XMPException {
        setProperty(schemaNS, propName, propValue, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyDate(String schemaNS, String propName, XMPDateTime propValue) throws XMPException {
        setProperty(schemaNS, propName, propValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public Calendar getPropertyCalendar(String schemaNS, String propName) throws XMPException {
        return (Calendar) getPropertyObject(schemaNS, propName, 6);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyCalendar(String schemaNS, String propName, Calendar propValue, PropertyOptions options) throws XMPException {
        setProperty(schemaNS, propName, propValue, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyCalendar(String schemaNS, String propName, Calendar propValue) throws XMPException {
        setProperty(schemaNS, propName, propValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public byte[] getPropertyBase64(String schemaNS, String propName) throws XMPException {
        return (byte[]) getPropertyObject(schemaNS, propName, 7);
    }

    @Override // com.adobe.xmp.XMPMeta
    public String getPropertyString(String schemaNS, String propName) throws XMPException {
        return (String) getPropertyObject(schemaNS, propName, 0);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBase64(String schemaNS, String propName, byte[] propValue, PropertyOptions options) throws XMPException {
        setProperty(schemaNS, propName, propValue, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setPropertyBase64(String schemaNS, String propName, byte[] propValue) throws XMPException {
        setProperty(schemaNS, propName, propValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getQualifier(String schemaNS, String propName, String qualNS, String qualName) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertPropName(propName);
        return getProperty(schemaNS, propName + XMPPathFactory.composeQualifierPath(qualNS, qualName));
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPProperty getStructField(String schemaNS, String structName, String fieldNS, String fieldName) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertStructName(structName);
        return getProperty(schemaNS, structName + XMPPathFactory.composeStructFieldPath(fieldNS, fieldName));
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPIterator iterator() throws XMPException {
        return iterator(null, null, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPIterator iterator(IteratorOptions options) throws XMPException {
        return iterator(null, null, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public XMPIterator iterator(String schemaNS, String propName, IteratorOptions options) throws XMPException {
        return new XMPIteratorImpl(this, schemaNS, propName, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setArrayItem(String schemaNS, String arrayName, int itemIndex, String itemValue, PropertyOptions options) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        XMPNode arrayNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, arrayName), false, null);
        if (arrayNode != null) {
            doSetArrayItem(arrayNode, itemIndex, itemValue, options, false);
            return;
        }
        throw new XMPException("Specified array does not exist", XMPError.BADXPATH);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setArrayItem(String schemaNS, String arrayName, int itemIndex, String itemValue) throws XMPException {
        setArrayItem(schemaNS, arrayName, itemIndex, itemValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void insertArrayItem(String schemaNS, String arrayName, int itemIndex, String itemValue, PropertyOptions options) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertArrayName(arrayName);
        XMPNode arrayNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, arrayName), false, null);
        if (arrayNode != null) {
            doSetArrayItem(arrayNode, itemIndex, itemValue, options, true);
            return;
        }
        throw new XMPException("Specified array does not exist", XMPError.BADXPATH);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void insertArrayItem(String schemaNS, String arrayName, int itemIndex, String itemValue) throws XMPException {
        insertArrayItem(schemaNS, arrayName, itemIndex, itemValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setProperty(String schemaNS, String propName, Object propValue, PropertyOptions options) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertPropName(propName);
        PropertyOptions options2 = XMPNodeUtils.verifySetOptions(options, propValue);
        XMPNode propNode = XMPNodeUtils.findNode(this.tree, XMPPathParser.expandXPath(schemaNS, propName), true, options2);
        if (propNode != null) {
            setNode(propNode, propValue, options2, false);
            return;
        }
        throw new XMPException("Specified property does not exist", XMPError.BADXPATH);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setProperty(String schemaNS, String propName, Object propValue) throws XMPException {
        setProperty(schemaNS, propName, propValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setQualifier(String schemaNS, String propName, String qualNS, String qualName, String qualValue, PropertyOptions options) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertPropName(propName);
        if (!doesPropertyExist(schemaNS, propName)) {
            throw new XMPException("Specified property does not exist!", XMPError.BADXPATH);
        }
        setProperty(schemaNS, propName + XMPPathFactory.composeQualifierPath(qualNS, qualName), qualValue, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setQualifier(String schemaNS, String propName, String qualNS, String qualName, String qualValue) throws XMPException {
        setQualifier(schemaNS, propName, qualNS, qualName, qualValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setStructField(String schemaNS, String structName, String fieldNS, String fieldName, String fieldValue, PropertyOptions options) throws XMPException {
        ParameterAsserts.assertSchemaNS(schemaNS);
        ParameterAsserts.assertStructName(structName);
        setProperty(schemaNS, structName + XMPPathFactory.composeStructFieldPath(fieldNS, fieldName), fieldValue, options);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setStructField(String schemaNS, String structName, String fieldNS, String fieldName, String fieldValue) throws XMPException {
        setStructField(schemaNS, structName, fieldNS, fieldName, fieldValue, null);
    }

    @Override // com.adobe.xmp.XMPMeta
    public String getObjectName() {
        return this.tree.getName() != null ? this.tree.getName() : "";
    }

    @Override // com.adobe.xmp.XMPMeta
    public void setObjectName(String name) {
        this.tree.setName(name);
    }

    @Override // com.adobe.xmp.XMPMeta
    public String getPacketHeader() {
        return this.packetHeader;
    }

    public void setPacketHeader(String packetHeader2) {
        this.packetHeader = packetHeader2;
    }

    @Override // com.adobe.xmp.XMPMeta, java.lang.Object
    public Object clone() {
        return new XMPMetaImpl((XMPNode) this.tree.clone());
    }

    @Override // com.adobe.xmp.XMPMeta
    public String dumpObject() {
        return getRoot().dumpNode(true);
    }

    @Override // com.adobe.xmp.XMPMeta
    public void sort() {
        this.tree.sort();
    }

    @Override // com.adobe.xmp.XMPMeta
    public void normalize(ParseOptions options) throws XMPException {
        if (options == null) {
            options = new ParseOptions();
        }
        XMPNormalizer.process(this, options);
    }

    public XMPNode getRoot() {
        return this.tree;
    }

    private void doSetArrayItem(XMPNode arrayNode, int itemIndex, String itemValue, PropertyOptions itemOptions, boolean insert) throws XMPException {
        XMPNode itemNode = new XMPNode(XMPConst.ARRAY_ITEM_NAME, null);
        PropertyOptions itemOptions2 = XMPNodeUtils.verifySetOptions(itemOptions, itemValue);
        int maxIndex = insert ? arrayNode.getChildrenLength() + 1 : arrayNode.getChildrenLength();
        if (itemIndex == -1) {
            itemIndex = maxIndex;
        }
        if (1 > itemIndex || itemIndex > maxIndex) {
            throw new XMPException("Array index out of bounds", XMPError.BADINDEX);
        }
        if (!insert) {
            arrayNode.removeChild(itemIndex);
        }
        arrayNode.addChild(itemIndex, itemNode);
        setNode(itemNode, itemValue, itemOptions2, false);
    }

    /* access modifiers changed from: package-private */
    public void setNode(XMPNode node, Object value, PropertyOptions newOptions, boolean deleteExisting) throws XMPException {
        if (deleteExisting) {
            node.clear();
        }
        node.getOptions().mergeWith(newOptions);
        if (!node.getOptions().isCompositeProperty()) {
            XMPNodeUtils.setNodeValue(node, value);
        } else if (value == null || value.toString().length() <= 0) {
            node.removeChildren();
        } else {
            throw new XMPException("Composite nodes can't have values", XMPError.BADXPATH);
        }
    }

    private Object evaluateNodeValue(int valueType, XMPNode propNode) throws XMPException {
        String rawValue = propNode.getValue();
        switch (valueType) {
            case 1:
                return new Boolean(XMPUtils.convertToBoolean(rawValue));
            case 2:
                return new Integer(XMPUtils.convertToInteger(rawValue));
            case 3:
                return new Long(XMPUtils.convertToLong(rawValue));
            case 4:
                return new Double(XMPUtils.convertToDouble(rawValue));
            case 5:
                return XMPUtils.convertToDate(rawValue);
            case 6:
                return XMPUtils.convertToDate(rawValue).getCalendar();
            case 7:
                return XMPUtils.decodeBase64(rawValue);
            default:
                if (rawValue != null || propNode.getOptions().isCompositeProperty()) {
                    return rawValue;
                }
                return "";
        }
    }
}
