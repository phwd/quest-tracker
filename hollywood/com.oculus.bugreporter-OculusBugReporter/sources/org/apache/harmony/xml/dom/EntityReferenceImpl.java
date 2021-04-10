package org.apache.harmony.xml.dom;

import org.w3c.dom.EntityReference;

public class EntityReferenceImpl extends LeafNodeImpl implements EntityReference {
    private String name;

    EntityReferenceImpl(DocumentImpl document, String name2) {
        super(document);
        this.name = name2;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return this.name;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 5;
    }
}
