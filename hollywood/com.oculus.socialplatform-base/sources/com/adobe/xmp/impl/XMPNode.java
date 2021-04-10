package com.adobe.xmp.impl;

import X.AnonymousClass006;
import com.adobe.xmp.XMPConst;
import com.adobe.xmp.XMPException;
import com.adobe.xmp.options.PropertyOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class XMPNode implements Comparable {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public boolean alias;
    public List children;
    public boolean hasAliases;
    public boolean hasValueChild;
    public boolean implicit;
    public String name;
    public PropertyOptions options;
    public XMPNode parent;
    public List qualifier;
    public String value;

    public void clear() {
        this.options = null;
        this.name = null;
        this.value = null;
        this.children = null;
        this.qualifier = null;
    }

    public void removeChildren() {
        this.children = null;
    }

    private void assertChildNotExisting(String str) throws XMPException {
        if (!"[]".equals(str) && findChildByName(str) != null) {
            throw new XMPException(AnonymousClass006.A09("Duplicate property or field node '", str, "'"), 203);
        }
    }

    private void assertQualifierNotExisting(String str) throws XMPException {
        if (!"[]".equals(str) && find(this.qualifier, str) != null) {
            throw new XMPException(AnonymousClass006.A09("Duplicate '", str, "' qualifier"), 203);
        }
    }

    private XMPNode find(List list, String str) {
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            XMPNode xMPNode = (XMPNode) it.next();
            if (xMPNode.name.equals(str)) {
                return xMPNode;
            }
        }
        return null;
    }

    private List getChildren() {
        List list = this.children;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList(0);
        this.children = arrayList;
        return arrayList;
    }

    private boolean isLanguageNode() {
        return XMPConst.XML_LANG.equals(this.name);
    }

    private boolean isTypeNode() {
        return XMPConst.RDF_TYPE.equals(this.name);
    }

    public void addQualifier(XMPNode xMPNode) throws XMPException {
        List qualifier2;
        boolean option;
        assertQualifierNotExisting(xMPNode.name);
        xMPNode.parent = this;
        xMPNode.getOptions().setOption(32, true);
        getOptions().setOption(16, true);
        if (xMPNode.isLanguageNode()) {
            this.options.setOption(64, true);
            qualifier2 = getQualifier();
            option = false;
        } else if (xMPNode.isTypeNode()) {
            PropertyOptions propertyOptions = this.options;
            propertyOptions.setOption(128, true);
            qualifier2 = getQualifier();
            option = propertyOptions.getOption(64);
        } else {
            getQualifier().add(xMPNode);
            return;
        }
        int i = option ? 1 : 0;
        int i2 = option ? 1 : 0;
        int i3 = option ? 1 : 0;
        qualifier2.add(i, xMPNode);
    }

    public void cleanupChildren() {
        if (this.children.isEmpty()) {
            this.children = null;
        }
    }

    public XMPNode findQualifierByName(String str) {
        return find(this.qualifier, str);
    }

    public int getChildrenLength() {
        List list = this.children;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public PropertyOptions getOptions() {
        PropertyOptions propertyOptions = this.options;
        if (propertyOptions != null) {
            return propertyOptions;
        }
        PropertyOptions propertyOptions2 = new PropertyOptions();
        this.options = propertyOptions2;
        return propertyOptions2;
    }

    public int getQualifierLength() {
        List list = this.qualifier;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public boolean hasChildren() {
        List list = this.children;
        if (list == null || list.size() <= 0) {
            return false;
        }
        return true;
    }

    public boolean hasQualifier() {
        List list = this.qualifier;
        if (list == null || list.size() <= 0) {
            return false;
        }
        return true;
    }

    public Iterator iterateChildren() {
        if (this.children != null) {
            return getChildren().iterator();
        }
        return Collections.EMPTY_LIST.listIterator();
    }

    public Iterator iterateQualifier() {
        if (this.qualifier == null) {
            return Collections.EMPTY_LIST.iterator();
        }
        final Iterator it = getQualifier().iterator();
        return new Iterator() {
            /* class com.adobe.xmp.impl.XMPNode.AnonymousClass1 */

            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public Object next() {
                return it.next();
            }

            public void remove() {
                throw new UnsupportedOperationException("remove() is not allowed due to the internal contraints");
            }
        };
    }

    public void replaceChild(int i, XMPNode xMPNode) {
        xMPNode.parent = this;
        getChildren().set(i - 1, xMPNode);
    }

    @Override // java.lang.Object
    public Object clone() {
        PropertyOptions propertyOptions;
        try {
            propertyOptions = new PropertyOptions(getOptions().options);
        } catch (XMPException unused) {
            propertyOptions = new PropertyOptions();
        }
        XMPNode xMPNode = new XMPNode(this.name, this.value, propertyOptions);
        cloneSubtree(xMPNode);
        return xMPNode;
    }

    public void cloneSubtree(XMPNode xMPNode) {
        try {
            Iterator iterateChildren = iterateChildren();
            while (iterateChildren.hasNext()) {
                xMPNode.addChild((XMPNode) ((XMPNode) iterateChildren.next()).clone());
            }
            Iterator iterateQualifier = iterateQualifier();
            while (iterateQualifier.hasNext()) {
                xMPNode.addQualifier((XMPNode) ((XMPNode) iterateQualifier.next()).clone());
            }
        } catch (XMPException unused) {
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        String str;
        String str2;
        if (getOptions().isSchemaNode()) {
            str = this.value;
            str2 = ((XMPNode) obj).value;
        } else {
            str = this.name;
            str2 = ((XMPNode) obj).name;
        }
        return str.compareTo(str2);
    }

    public XMPNode findChildByName(String str) {
        return find(getChildren(), str);
    }

    public XMPNode getChild(int i) {
        return (XMPNode) getChildren().get(i - 1);
    }

    public boolean getHasAliases() {
        return this.hasAliases;
    }

    public boolean getHasValueChild() {
        return this.hasValueChild;
    }

    public String getName() {
        return this.name;
    }

    public XMPNode getParent() {
        return this.parent;
    }

    public List getUnmodifiableChildren() {
        return Collections.unmodifiableList(new ArrayList(getChildren()));
    }

    public String getValue() {
        return this.value;
    }

    public boolean isAlias() {
        return this.alias;
    }

    public boolean isImplicit() {
        return this.implicit;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeQualifier(com.adobe.xmp.impl.XMPNode r4) {
        /*
            r3 = this;
            com.adobe.xmp.options.PropertyOptions r2 = r3.getOptions()
            boolean r0 = r4.isLanguageNode()
            if (r0 == 0) goto L_0x0029
            r1 = 0
            r0 = 64
        L_0x000d:
            r2.setOption(r0, r1)
        L_0x0010:
            java.util.List r0 = r3.getQualifier()
            r0.remove(r4)
            java.util.List r0 = r3.qualifier
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0028
            r1 = 0
            r0 = 16
            r2.setOption(r0, r1)
            r0 = 0
            r3.qualifier = r0
        L_0x0028:
            return
        L_0x0029:
            boolean r0 = r4.isTypeNode()
            if (r0 == 0) goto L_0x0010
            r1 = 0
            r0 = 128(0x80, float:1.794E-43)
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.XMPNode.removeQualifier(com.adobe.xmp.impl.XMPNode):void");
    }

    public void removeQualifiers() {
        PropertyOptions options2 = getOptions();
        options2.setOption(16, false);
        options2.setOption(64, false);
        options2.setOption(128, false);
        this.qualifier = null;
    }

    public void sort() {
        int length;
        if (hasQualifier()) {
            XMPNode[] xMPNodeArr = (XMPNode[]) getQualifier().toArray(new XMPNode[getQualifierLength()]);
            int i = 0;
            while (true) {
                length = xMPNodeArr.length;
                if (length <= i || (!XMPConst.XML_LANG.equals(xMPNodeArr[i].name) && !XMPConst.RDF_TYPE.equals(xMPNodeArr[i].name))) {
                    Arrays.sort(xMPNodeArr, i, length);
                    ListIterator listIterator = this.qualifier.listIterator();
                } else {
                    xMPNodeArr[i].sort();
                    i++;
                }
            }
            Arrays.sort(xMPNodeArr, i, length);
            ListIterator listIterator2 = this.qualifier.listIterator();
            for (int i2 = 0; i2 < length; i2++) {
                listIterator2.next();
                listIterator2.set(xMPNodeArr[i2]);
                xMPNodeArr[i2].sort();
            }
        }
        if (hasChildren()) {
            if (!getOptions().getOption(512)) {
                Collections.sort(this.children);
            }
            Iterator iterateChildren = iterateChildren();
            while (iterateChildren.hasNext()) {
                ((XMPNode) iterateChildren.next()).sort();
            }
        }
    }

    public void setAlias(boolean z) {
        this.alias = z;
    }

    public void setHasAliases(boolean z) {
        this.hasAliases = z;
    }

    public void setHasValueChild(boolean z) {
        this.hasValueChild = z;
    }

    public void setImplicit(boolean z) {
        this.implicit = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOptions(PropertyOptions propertyOptions) {
        this.options = propertyOptions;
    }

    public void setParent(XMPNode xMPNode) {
        this.parent = xMPNode;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public XMPNode(String str, PropertyOptions propertyOptions) {
        this(str, null, propertyOptions);
    }

    public XMPNode(String str, String str2, PropertyOptions propertyOptions) {
        this.children = null;
        this.qualifier = null;
        this.options = null;
        this.name = str;
        this.value = str2;
        this.options = propertyOptions;
    }

    private void dumpNode(StringBuffer stringBuffer, boolean z, int i, int i2) {
        int length;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            stringBuffer.append('\t');
        }
        XMPNode xMPNode = this.parent;
        if (xMPNode != null) {
            if (getOptions().getOption(32)) {
                stringBuffer.append('?');
            } else if (xMPNode.getOptions().getOption(512)) {
                stringBuffer.append('[');
                stringBuffer.append(i2);
                stringBuffer.append(']');
            }
            stringBuffer.append(this.name);
        } else {
            stringBuffer.append("ROOT NODE");
            String str = this.name;
            if (str != null && str.length() > 0) {
                stringBuffer.append(" (");
                stringBuffer.append(this.name);
                stringBuffer.append(')');
            }
        }
        String str2 = this.value;
        if (str2 != null && str2.length() > 0) {
            stringBuffer.append(" = \"");
            stringBuffer.append(this.value);
            stringBuffer.append('\"');
        }
        if (getOptions().containsOneOf(-1)) {
            stringBuffer.append("\t(");
            stringBuffer.append(getOptions().toString());
            stringBuffer.append(" : ");
            stringBuffer.append(getOptions().getOptionsString());
            stringBuffer.append(')');
        }
        stringBuffer.append('\n');
        if (z) {
            if (hasQualifier()) {
                XMPNode[] xMPNodeArr = (XMPNode[]) getQualifier().toArray(new XMPNode[getQualifierLength()]);
                int i5 = 0;
                while (true) {
                    length = xMPNodeArr.length;
                    if (length <= i5 || (!XMPConst.XML_LANG.equals(xMPNodeArr[i5].name) && !XMPConst.RDF_TYPE.equals(xMPNodeArr[i5].name))) {
                        Arrays.sort(xMPNodeArr, i5, length);
                        int i6 = 0;
                    } else {
                        i5++;
                    }
                }
                Arrays.sort(xMPNodeArr, i5, length);
                int i62 = 0;
                while (i62 < length) {
                    i62++;
                    xMPNodeArr[i62].dumpNode(stringBuffer, z, i + 2, i62);
                }
            }
            if (hasChildren()) {
                XMPNode[] xMPNodeArr2 = (XMPNode[]) getChildren().toArray(new XMPNode[getChildrenLength()]);
                if (!getOptions().getOption(512)) {
                    Arrays.sort(xMPNodeArr2);
                }
                while (i3 < xMPNodeArr2.length) {
                    i3++;
                    xMPNodeArr2[i3].dumpNode(stringBuffer, z, i + 1, i3);
                }
            }
        }
    }

    private List getQualifier() {
        List list = this.qualifier;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList(0);
        this.qualifier = arrayList;
        return arrayList;
    }

    public void addChild(int i, XMPNode xMPNode) throws XMPException {
        assertChildNotExisting(xMPNode.name);
        xMPNode.parent = this;
        getChildren().add(i - 1, xMPNode);
    }

    public void addChild(XMPNode xMPNode) throws XMPException {
        assertChildNotExisting(xMPNode.name);
        xMPNode.parent = this;
        getChildren().add(xMPNode);
    }

    public String dumpNode(boolean z) {
        StringBuffer stringBuffer = new StringBuffer(512);
        dumpNode(stringBuffer, z, 0, 0);
        return stringBuffer.toString();
    }

    public XMPNode getQualifier(int i) {
        return (XMPNode) getQualifier().get(i - 1);
    }

    public void removeChild(int i) {
        getChildren().remove(i - 1);
        cleanupChildren();
    }

    public void removeChild(XMPNode xMPNode) {
        getChildren().remove(xMPNode);
        cleanupChildren();
    }
}
