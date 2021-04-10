package org.apache.harmony.xml.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;

public final class DocumentTypeImpl extends LeafNodeImpl implements DocumentType {
    private String publicId;
    private String qualifiedName;
    private String systemId;

    public DocumentTypeImpl(DocumentImpl document, String qualifiedName2, String publicId2, String systemId2) {
        super(document);
        if (qualifiedName2 == null || "".equals(qualifiedName2)) {
            throw new DOMException(14, qualifiedName2);
        }
        int prefixSeparator = qualifiedName2.lastIndexOf(":");
        if (prefixSeparator != -1) {
            String prefix = qualifiedName2.substring(0, prefixSeparator);
            String localName = qualifiedName2.substring(prefixSeparator + 1);
            if (!DocumentImpl.isXMLIdentifier(prefix)) {
                throw new DOMException(14, qualifiedName2);
            } else if (!DocumentImpl.isXMLIdentifier(localName)) {
                throw new DOMException(5, qualifiedName2);
            }
        } else if (!DocumentImpl.isXMLIdentifier(qualifiedName2)) {
            throw new DOMException(5, qualifiedName2);
        }
        this.qualifiedName = qualifiedName2;
        this.publicId = publicId2;
        this.systemId = systemId2;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return this.qualifiedName;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 10;
    }

    @Override // org.w3c.dom.DocumentType
    public NamedNodeMap getEntities() {
        return null;
    }

    @Override // org.w3c.dom.DocumentType
    public String getInternalSubset() {
        return null;
    }

    @Override // org.w3c.dom.DocumentType
    public String getName() {
        return this.qualifiedName;
    }

    @Override // org.w3c.dom.DocumentType
    public NamedNodeMap getNotations() {
        return null;
    }

    @Override // org.w3c.dom.DocumentType
    public String getPublicId() {
        return this.publicId;
    }

    @Override // org.w3c.dom.DocumentType
    public String getSystemId() {
        return this.systemId;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return null;
    }
}
