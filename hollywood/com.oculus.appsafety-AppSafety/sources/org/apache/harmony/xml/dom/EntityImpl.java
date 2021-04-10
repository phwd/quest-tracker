package org.apache.harmony.xml.dom;

import org.w3c.dom.Entity;

public class EntityImpl extends NodeImpl implements Entity {
    private String notationName;
    private String publicID;
    private String systemID;

    EntityImpl(DocumentImpl document, String notationName2, String publicID2, String systemID2) {
        super(document);
        this.notationName = notationName2;
        this.publicID = publicID2;
        this.systemID = systemID2;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return getNotationName();
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 6;
    }

    @Override // org.w3c.dom.Entity
    public String getNotationName() {
        return this.notationName;
    }

    @Override // org.w3c.dom.Entity
    public String getPublicId() {
        return this.publicID;
    }

    @Override // org.w3c.dom.Entity
    public String getSystemId() {
        return this.systemID;
    }

    @Override // org.w3c.dom.Entity
    public String getInputEncoding() {
        throw new UnsupportedOperationException();
    }

    @Override // org.w3c.dom.Entity
    public String getXmlEncoding() {
        throw new UnsupportedOperationException();
    }

    @Override // org.w3c.dom.Entity
    public String getXmlVersion() {
        throw new UnsupportedOperationException();
    }
}
