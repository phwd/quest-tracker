package org.apache.harmony.xml.dom;

import android.icu.impl.number.Padder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import sun.security.x509.PolicyInformation;

public class ElementImpl extends InnerNodeImpl implements Element {
    private List<AttrImpl> attributes = new ArrayList();
    String localName;
    boolean namespaceAware;
    String namespaceURI;
    String prefix;

    ElementImpl(DocumentImpl document, String namespaceURI2, String qualifiedName) {
        super(document);
        setNameNS(this, namespaceURI2, qualifiedName);
    }

    ElementImpl(DocumentImpl document, String name) {
        super(document);
        setName(this, name);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int indexOfAttribute(String name) {
        for (int i = 0; i < this.attributes.size(); i++) {
            if (Objects.equals(name, this.attributes.get(i).getNodeName())) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int indexOfAttributeNS(String namespaceURI2, String localName2) {
        for (int i = 0; i < this.attributes.size(); i++) {
            AttrImpl attr = this.attributes.get(i);
            if (Objects.equals(namespaceURI2, attr.getNamespaceURI()) && Objects.equals(localName2, attr.getLocalName())) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.w3c.dom.Element
    public String getAttribute(String name) {
        Attr attr = getAttributeNode(name);
        if (attr == null) {
            return "";
        }
        return attr.getValue();
    }

    @Override // org.w3c.dom.Element
    public String getAttributeNS(String namespaceURI2, String localName2) {
        Attr attr = getAttributeNodeNS(namespaceURI2, localName2);
        if (attr == null) {
            return "";
        }
        return attr.getValue();
    }

    @Override // org.w3c.dom.Element
    public AttrImpl getAttributeNode(String name) {
        int i = indexOfAttribute(name);
        if (i == -1) {
            return null;
        }
        return this.attributes.get(i);
    }

    @Override // org.w3c.dom.Element
    public AttrImpl getAttributeNodeNS(String namespaceURI2, String localName2) {
        int i = indexOfAttributeNS(namespaceURI2, localName2);
        if (i == -1) {
            return null;
        }
        return this.attributes.get(i);
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        return new ElementAttrNamedNodeMapImpl();
    }

    /* access modifiers changed from: package-private */
    public Element getElementById(String name) {
        Element element;
        for (Attr attr : this.attributes) {
            if (attr.isId() && name.equals(attr.getValue())) {
                return this;
            }
        }
        if (name.equals(getAttribute(PolicyInformation.ID))) {
            return this;
        }
        for (NodeImpl node : this.children) {
            if (node.getNodeType() == 1 && (element = ((ElementImpl) node).getElementById(name)) != null) {
                return element;
            }
        }
        return null;
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagName(String name) {
        NodeListImpl result = new NodeListImpl();
        getElementsByTagName(result, name);
        return result;
    }

    @Override // org.w3c.dom.Element
    public NodeList getElementsByTagNameNS(String namespaceURI2, String localName2) {
        NodeListImpl result = new NodeListImpl();
        getElementsByTagNameNS(result, namespaceURI2, localName2);
        return result;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getLocalName() {
        if (this.namespaceAware) {
            return this.localName;
        }
        return null;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNamespaceURI() {
        return this.namespaceURI;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getNodeName() {
        return getTagName();
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public short getNodeType() {
        return 1;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public String getPrefix() {
        return this.prefix;
    }

    @Override // org.w3c.dom.Element
    public String getTagName() {
        if (this.prefix == null) {
            return this.localName;
        }
        return this.prefix + ":" + this.localName;
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttribute(String name) {
        return indexOfAttribute(name) != -1;
    }

    @Override // org.w3c.dom.Element
    public boolean hasAttributeNS(String namespaceURI2, String localName2) {
        return indexOfAttributeNS(namespaceURI2, localName2) != -1;
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public boolean hasAttributes() {
        return !this.attributes.isEmpty();
    }

    @Override // org.w3c.dom.Element
    public void removeAttribute(String name) throws DOMException {
        int i = indexOfAttribute(name);
        if (i != -1) {
            this.attributes.remove(i);
        }
    }

    @Override // org.w3c.dom.Element
    public void removeAttributeNS(String namespaceURI2, String localName2) throws DOMException {
        int i = indexOfAttributeNS(namespaceURI2, localName2);
        if (i != -1) {
            this.attributes.remove(i);
        }
    }

    @Override // org.w3c.dom.Element
    public Attr removeAttributeNode(Attr oldAttr) throws DOMException {
        AttrImpl oldAttrImpl = (AttrImpl) oldAttr;
        if (oldAttrImpl.getOwnerElement() == this) {
            this.attributes.remove(oldAttrImpl);
            oldAttrImpl.ownerElement = null;
            return oldAttrImpl;
        }
        throw new DOMException(8, null);
    }

    @Override // org.w3c.dom.Element
    public void setAttribute(String name, String value) throws DOMException {
        Attr attr = getAttributeNode(name);
        if (attr == null) {
            attr = this.document.createAttribute(name);
            setAttributeNode(attr);
        }
        attr.setValue(value);
    }

    @Override // org.w3c.dom.Element
    public void setAttributeNS(String namespaceURI2, String qualifiedName, String value) throws DOMException {
        Attr attr = getAttributeNodeNS(namespaceURI2, qualifiedName);
        if (attr == null) {
            attr = this.document.createAttributeNS(namespaceURI2, qualifiedName);
            setAttributeNodeNS(attr);
        }
        attr.setValue(value);
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNode(Attr newAttr) throws DOMException {
        AttrImpl newAttrImpl = (AttrImpl) newAttr;
        if (newAttrImpl.document != this.document) {
            throw new DOMException(4, null);
        } else if (newAttrImpl.getOwnerElement() == null) {
            AttrImpl oldAttrImpl = null;
            int i = indexOfAttribute(newAttr.getName());
            if (i != -1) {
                oldAttrImpl = this.attributes.get(i);
                this.attributes.remove(i);
            }
            this.attributes.add(newAttrImpl);
            newAttrImpl.ownerElement = this;
            return oldAttrImpl;
        } else {
            throw new DOMException(10, null);
        }
    }

    @Override // org.w3c.dom.Element
    public Attr setAttributeNodeNS(Attr newAttr) throws DOMException {
        AttrImpl newAttrImpl = (AttrImpl) newAttr;
        if (newAttrImpl.document != this.document) {
            throw new DOMException(4, null);
        } else if (newAttrImpl.getOwnerElement() == null) {
            AttrImpl oldAttrImpl = null;
            int i = indexOfAttributeNS(newAttr.getNamespaceURI(), newAttr.getLocalName());
            if (i != -1) {
                oldAttrImpl = this.attributes.get(i);
                this.attributes.remove(i);
            }
            this.attributes.add(newAttrImpl);
            newAttrImpl.ownerElement = this;
            return oldAttrImpl;
        } else {
            throw new DOMException(10, null);
        }
    }

    @Override // org.apache.harmony.xml.dom.NodeImpl, org.w3c.dom.Node
    public void setPrefix(String prefix2) {
        this.prefix = validatePrefix(prefix2, this.namespaceAware, this.namespaceURI);
    }

    public class ElementAttrNamedNodeMapImpl implements NamedNodeMap {
        public ElementAttrNamedNodeMapImpl() {
        }

        @Override // org.w3c.dom.NamedNodeMap
        public int getLength() {
            return ElementImpl.this.attributes.size();
        }

        private int indexOfItem(String name) {
            return ElementImpl.this.indexOfAttribute(name);
        }

        private int indexOfItemNS(String namespaceURI, String localName) {
            return ElementImpl.this.indexOfAttributeNS(namespaceURI, localName);
        }

        @Override // org.w3c.dom.NamedNodeMap
        public Node getNamedItem(String name) {
            return ElementImpl.this.getAttributeNode(name);
        }

        @Override // org.w3c.dom.NamedNodeMap
        public Node getNamedItemNS(String namespaceURI, String localName) {
            return ElementImpl.this.getAttributeNodeNS(namespaceURI, localName);
        }

        @Override // org.w3c.dom.NamedNodeMap
        public Node item(int index) {
            return (Node) ElementImpl.this.attributes.get(index);
        }

        @Override // org.w3c.dom.NamedNodeMap
        public Node removeNamedItem(String name) throws DOMException {
            int i = indexOfItem(name);
            if (i != -1) {
                return (Node) ElementImpl.this.attributes.remove(i);
            }
            throw new DOMException(8, null);
        }

        @Override // org.w3c.dom.NamedNodeMap
        public Node removeNamedItemNS(String namespaceURI, String localName) throws DOMException {
            int i = indexOfItemNS(namespaceURI, localName);
            if (i != -1) {
                return (Node) ElementImpl.this.attributes.remove(i);
            }
            throw new DOMException(8, null);
        }

        @Override // org.w3c.dom.NamedNodeMap
        public Node setNamedItem(Node arg) throws DOMException {
            if (arg instanceof Attr) {
                return ElementImpl.this.setAttributeNode((Attr) arg);
            }
            throw new DOMException(3, null);
        }

        @Override // org.w3c.dom.NamedNodeMap
        public Node setNamedItemNS(Node arg) throws DOMException {
            if (arg instanceof Attr) {
                return ElementImpl.this.setAttributeNodeNS((Attr) arg);
            }
            throw new DOMException(3, null);
        }
    }

    @Override // org.w3c.dom.Element
    public TypeInfo getSchemaTypeInfo() {
        return NULL_TYPE_INFO;
    }

    @Override // org.w3c.dom.Element
    public void setIdAttribute(String name, boolean isId) throws DOMException {
        AttrImpl attr = getAttributeNode(name);
        if (attr != null) {
            attr.isId = isId;
            return;
        }
        throw new DOMException(8, "No such attribute: " + name);
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNS(String namespaceURI2, String localName2, boolean isId) throws DOMException {
        AttrImpl attr = getAttributeNodeNS(namespaceURI2, localName2);
        if (attr != null) {
            attr.isId = isId;
            return;
        }
        throw new DOMException(8, "No such attribute: " + namespaceURI2 + Padder.FALLBACK_PADDING_STRING + localName2);
    }

    @Override // org.w3c.dom.Element
    public void setIdAttributeNode(Attr idAttr, boolean isId) throws DOMException {
        ((AttrImpl) idAttr).isId = isId;
    }
}
