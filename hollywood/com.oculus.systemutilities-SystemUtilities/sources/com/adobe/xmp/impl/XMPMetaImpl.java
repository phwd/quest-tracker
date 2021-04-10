package com.adobe.xmp.impl;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPMeta;
import com.adobe.xmp.XMPUtils;
import com.adobe.xmp.impl.xpath.XMPPathParser;
import com.adobe.xmp.properties.XMPProperty;

public class XMPMetaImpl implements XMPMeta {
    static final /* synthetic */ boolean $assertionsDisabled = (!XMPMetaImpl.class.desiredAssertionStatus());
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
        r10 = "x-default".equals(r20);
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
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, "x-default", r21);
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
        com.adobe.xmp.impl.XMPNodeUtils.appendLangItem(r2, "x-default", r21);
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

                public String toString() {
                    return value.toString();
                }
            };
        }
        throw new XMPException("Property must be simple when a value type is requested", 102);
    }

    public void setPacketHeader(String packetHeader2) {
        this.packetHeader = packetHeader2;
    }

    @Override // com.adobe.xmp.XMPMeta, java.lang.Object
    public Object clone() {
        return new XMPMetaImpl((XMPNode) this.tree.clone());
    }

    public XMPNode getRoot() {
        return this.tree;
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
