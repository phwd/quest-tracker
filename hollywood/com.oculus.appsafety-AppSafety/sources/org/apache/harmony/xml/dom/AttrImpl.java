package org.apache.harmony.xml.dom;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.TypeInfo;

public final class AttrImpl extends NodeImpl implements Attr {
    boolean isId;
    String localName;
    boolean namespaceAware;
    String namespaceURI;
    ElementImpl ownerElement;
    String prefix;
    private String value = "";

    AttrImpl(DocumentImpl document, String namespaceURI2, String qualifiedName) {
        super(document);
        setNameNS(this, namespaceURI2, qualifiedName);
    }

    AttrImpl(DocumentImpl document, String name) {
        super(document);
        setName(this, name);
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getLocalName() {
        if (this.namespaceAware) {
            return this.localName;
        }
        return null;
    }

    @Override // org.w3c.dom.Attr
    public String getName() {
        if (this.prefix == null) {
            return this.localName;
        }
        return this.prefix + ":" + this.localName;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNamespaceURI() {
        return this.namespaceURI;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return getName();
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 2;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeValue() {
        return getValue();
    }

    @Override // org.w3c.dom.Attr
    public Element getOwnerElement() {
        return this.ownerElement;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.w3c.dom.Attr
    public boolean getSpecified() {
        return this.value != null;
    }

    @Override // org.w3c.dom.Attr
    public String getValue() {
        return this.value;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public void setPrefix(String prefix2) {
        this.prefix = validatePrefix(prefix2, this.namespaceAware, this.namespaceURI);
    }

    @Override // org.w3c.dom.Attr
    public void setValue(String value2) throws DOMException {
        this.value = value2;
    }

    @Override // org.w3c.dom.Attr
    public TypeInfo getSchemaTypeInfo() {
        return NULL_TYPE_INFO;
    }

    @Override // org.w3c.dom.Attr
    public boolean isId() {
        return this.isId;
    }
}
