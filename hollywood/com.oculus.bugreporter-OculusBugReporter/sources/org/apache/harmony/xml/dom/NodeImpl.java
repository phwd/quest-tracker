package org.apache.harmony.xml.dom;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import org.w3c.dom.Attr;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

public abstract class NodeImpl implements Node {
    private static final NodeList EMPTY_LIST = new NodeListImpl();
    static final TypeInfo NULL_TYPE_INFO = new TypeInfo() {
        /* class org.apache.harmony.xml.dom.NodeImpl.AnonymousClass1 */

        @Override // org.w3c.dom.TypeInfo
        public String getTypeName() {
            return null;
        }

        @Override // org.w3c.dom.TypeInfo
        public String getTypeNamespace() {
            return null;
        }

        @Override // org.w3c.dom.TypeInfo
        public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
            return false;
        }
    };
    DocumentImpl document;

    @Override // org.w3c.dom.Node
    public abstract short getNodeType();

    NodeImpl(DocumentImpl document2) {
        this.document = document2;
    }

    @Override // org.w3c.dom.Node
    public Node appendChild(Node newChild) throws DOMException {
        throw new DOMException(3, null);
    }

    @Override // org.w3c.dom.Node
    public final Node cloneNode(boolean deep) {
        return this.document.cloneOrImportNode(1, this, deep);
    }

    @Override // org.w3c.dom.Node
    public NamedNodeMap getAttributes() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public NodeList getChildNodes() {
        return EMPTY_LIST;
    }

    @Override // org.w3c.dom.Node
    public Node getFirstChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getLastChild() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getLocalName() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNamespaceURI() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getNextSibling() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNodeName() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getNodeValue() throws DOMException {
        return null;
    }

    @Override // org.w3c.dom.Node
    public final Document getOwnerDocument() {
        DocumentImpl documentImpl = this.document;
        if (documentImpl == this) {
            return null;
        }
        return documentImpl;
    }

    @Override // org.w3c.dom.Node
    public Node getParentNode() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public String getPrefix() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public Node getPreviousSibling() {
        return null;
    }

    @Override // org.w3c.dom.Node
    public boolean hasAttributes() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public boolean hasChildNodes() {
        return false;
    }

    @Override // org.w3c.dom.Node
    public Node insertBefore(Node newChild, Node refChild) throws DOMException {
        throw new DOMException(3, null);
    }

    @Override // org.w3c.dom.Node
    public boolean isSupported(String feature, String version) {
        return DOMImplementationImpl.getInstance().hasFeature(feature, version);
    }

    @Override // org.w3c.dom.Node
    public void normalize() {
    }

    @Override // org.w3c.dom.Node
    public Node removeChild(Node oldChild) throws DOMException {
        throw new DOMException(3, null);
    }

    @Override // org.w3c.dom.Node
    public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
        throw new DOMException(3, null);
    }

    @Override // org.w3c.dom.Node
    public final void setNodeValue(String nodeValue) throws DOMException {
        switch (getNodeType()) {
            case 1:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
            case 12:
                return;
            case 2:
                ((Attr) this).setValue(nodeValue);
                return;
            case 3:
            case 4:
            case 8:
                ((CharacterData) this).setData(nodeValue);
                return;
            case 7:
                ((ProcessingInstruction) this).setData(nodeValue);
                return;
            default:
                throw new DOMException(9, "Unsupported node type " + ((int) getNodeType()));
        }
    }

    @Override // org.w3c.dom.Node
    public void setPrefix(String prefix) throws DOMException {
    }

    static String validatePrefix(String prefix, boolean namespaceAware, String namespaceURI) {
        if (!namespaceAware) {
            throw new DOMException(14, prefix);
        } else if (prefix == null || (namespaceURI != null && DocumentImpl.isXMLIdentifier(prefix) && ((!XMLConstants.XML_NS_PREFIX.equals(prefix) || "http://www.w3.org/XML/1998/namespace".equals(namespaceURI)) && (!XMLConstants.XMLNS_ATTRIBUTE.equals(prefix) || XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI))))) {
            return prefix;
        } else {
            throw new DOMException(14, prefix);
        }
    }

    static void setNameNS(NodeImpl node, String namespaceURI, String qualifiedName) {
        if (qualifiedName != null) {
            String prefix = null;
            int p = qualifiedName.lastIndexOf(":");
            if (p != -1) {
                prefix = validatePrefix(qualifiedName.substring(0, p), true, namespaceURI);
                qualifiedName = qualifiedName.substring(p + 1);
            }
            if (DocumentImpl.isXMLIdentifier(qualifiedName)) {
                short nodeType = node.getNodeType();
                if (nodeType == 1) {
                    ElementImpl element = (ElementImpl) node;
                    element.namespaceAware = true;
                    element.namespaceURI = namespaceURI;
                    element.prefix = prefix;
                    element.localName = qualifiedName;
                } else if (nodeType != 2) {
                    throw new DOMException(9, "Cannot rename nodes of type " + ((int) node.getNodeType()));
                } else if (!XMLConstants.XMLNS_ATTRIBUTE.equals(qualifiedName) || XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(namespaceURI)) {
                    AttrImpl attr = (AttrImpl) node;
                    attr.namespaceAware = true;
                    attr.namespaceURI = namespaceURI;
                    attr.prefix = prefix;
                    attr.localName = qualifiedName;
                } else {
                    throw new DOMException(14, qualifiedName);
                }
            } else {
                throw new DOMException(5, qualifiedName);
            }
        } else {
            throw new DOMException(14, qualifiedName);
        }
    }

    static void setName(NodeImpl node, String name) {
        int prefixSeparator = name.lastIndexOf(":");
        if (prefixSeparator != -1) {
            String prefix = name.substring(0, prefixSeparator);
            String localName = name.substring(prefixSeparator + 1);
            if (!DocumentImpl.isXMLIdentifier(prefix) || !DocumentImpl.isXMLIdentifier(localName)) {
                throw new DOMException(5, name);
            }
        } else if (!DocumentImpl.isXMLIdentifier(name)) {
            throw new DOMException(5, name);
        }
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            ElementImpl element = (ElementImpl) node;
            element.namespaceAware = false;
            element.localName = name;
        } else if (nodeType == 2) {
            AttrImpl attr = (AttrImpl) node;
            attr.namespaceAware = false;
            attr.localName = name;
        } else {
            throw new DOMException(9, "Cannot rename nodes of type " + ((int) node.getNodeType()));
        }
    }

    @Override // org.w3c.dom.Node
    public final String getBaseURI() {
        switch (getNodeType()) {
            case 1:
                String uri = ((Element) this).getAttributeNS("http://www.w3.org/XML/1998/namespace", "base");
                if (uri != null) {
                    try {
                        if (!uri.isEmpty()) {
                            if (new URI(uri).isAbsolute()) {
                                return uri;
                            }
                            String parentUri = getParentBaseUri();
                            if (parentUri == null) {
                                return null;
                            }
                            return new URI(parentUri).resolve(uri).toString();
                        }
                    } catch (URISyntaxException e) {
                        return null;
                    }
                }
                return getParentBaseUri();
            case 2:
            case 3:
            case 4:
            case 8:
            case 10:
            case 11:
                return null;
            case 5:
                return null;
            case 6:
            case 12:
                return null;
            case 7:
                return getParentBaseUri();
            case 9:
                return sanitizeUri(((Document) this).getDocumentURI());
            default:
                throw new DOMException(9, "Unsupported node type " + ((int) getNodeType()));
        }
    }

    private String getParentBaseUri() {
        Node parentNode = getParentNode();
        if (parentNode != null) {
            return parentNode.getBaseURI();
        }
        return null;
    }

    private String sanitizeUri(String uri) {
        if (uri == null || uri.length() == 0) {
            return null;
        }
        try {
            return new URI(uri).toString();
        } catch (URISyntaxException e) {
            return null;
        }
    }

    @Override // org.w3c.dom.Node
    public short compareDocumentPosition(Node other) throws DOMException {
        throw new UnsupportedOperationException();
    }

    @Override // org.w3c.dom.Node
    public String getTextContent() throws DOMException {
        return getNodeValue();
    }

    /* access modifiers changed from: package-private */
    public void getTextContent(StringBuilder buf) throws DOMException {
        String content = getNodeValue();
        if (content != null) {
            buf.append(content);
        }
    }

    @Override // org.w3c.dom.Node
    public final void setTextContent(String textContent) throws DOMException {
        switch (getNodeType()) {
            case 1:
            case 5:
            case 6:
            case 11:
                break;
            case 2:
            case 3:
            case 4:
            case 7:
            case 8:
            case 12:
                setNodeValue(textContent);
                return;
            case 9:
            case 10:
                return;
            default:
                throw new DOMException(9, "Unsupported node type " + ((int) getNodeType()));
        }
        while (true) {
            Node child = getFirstChild();
            if (child != null) {
                removeChild(child);
            } else if (textContent != null && textContent.length() != 0) {
                appendChild(this.document.createTextNode(textContent));
                return;
            } else {
                return;
            }
        }
    }

    @Override // org.w3c.dom.Node
    public boolean isSameNode(Node other) {
        return this == other;
    }

    private NodeImpl getNamespacingElement() {
        switch (getNodeType()) {
            case 1:
                return this;
            case 2:
                return (NodeImpl) ((Attr) this).getOwnerElement();
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
                return getContainingElement();
            case 6:
            case 10:
            case 11:
            case 12:
                return null;
            case 9:
                return (NodeImpl) ((Document) this).getDocumentElement();
            default:
                throw new DOMException(9, "Unsupported node type " + ((int) getNodeType()));
        }
    }

    private NodeImpl getContainingElement() {
        for (Node p = getParentNode(); p != null; p = p.getParentNode()) {
            if (p.getNodeType() == 1) {
                return (NodeImpl) p;
            }
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public final String lookupPrefix(String namespaceURI) {
        if (namespaceURI == null) {
            return null;
        }
        NodeImpl target = getNamespacingElement();
        for (NodeImpl node = target; node != null; node = node.getContainingElement()) {
            if (namespaceURI.equals(node.getNamespaceURI()) && target.isPrefixMappedToUri(node.getPrefix(), namespaceURI)) {
                return node.getPrefix();
            }
            if (node.hasAttributes()) {
                NamedNodeMap attributes = node.getAttributes();
                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    Node attr = attributes.item(i);
                    if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(attr.getNamespaceURI()) && XMLConstants.XMLNS_ATTRIBUTE.equals(attr.getPrefix()) && namespaceURI.equals(attr.getNodeValue()) && target.isPrefixMappedToUri(attr.getLocalName(), namespaceURI)) {
                        return attr.getLocalName();
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isPrefixMappedToUri(String prefix, String uri) {
        if (prefix == null) {
            return false;
        }
        return uri.equals(lookupNamespaceURI(prefix));
    }

    @Override // org.w3c.dom.Node
    public final boolean isDefaultNamespace(String namespaceURI) {
        String actual = lookupNamespaceURI(null);
        if (namespaceURI == null) {
            return actual == null;
        }
        return namespaceURI.equals(actual);
    }

    @Override // org.w3c.dom.Node
    public final String lookupNamespaceURI(String prefix) {
        for (NodeImpl node = getNamespacingElement(); node != null; node = node.getContainingElement()) {
            String nodePrefix = node.getPrefix();
            if (node.getNamespaceURI() != null) {
                if (prefix == null) {
                    if (nodePrefix == null) {
                    }
                } else if (prefix.equals(nodePrefix)) {
                }
                return node.getNamespaceURI();
            }
            if (node.hasAttributes()) {
                NamedNodeMap attributes = node.getAttributes();
                int length = attributes.getLength();
                for (int i = 0; i < length; i++) {
                    Node attr = attributes.item(i);
                    if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(attr.getNamespaceURI())) {
                        if (prefix == null) {
                            if (XMLConstants.XMLNS_ATTRIBUTE.equals(attr.getNodeName())) {
                            }
                        } else if (XMLConstants.XMLNS_ATTRIBUTE.equals(attr.getPrefix()) && prefix.equals(attr.getLocalName())) {
                        }
                        String value = attr.getNodeValue();
                        if (value.length() > 0) {
                            return value;
                        }
                        return null;
                    }
                }
                continue;
            }
        }
        return null;
    }

    private static List<Object> createEqualityKey(Node node) {
        List<Object> values = new ArrayList<>();
        values.add(Short.valueOf(node.getNodeType()));
        values.add(node.getNodeName());
        values.add(node.getLocalName());
        values.add(node.getNamespaceURI());
        values.add(node.getPrefix());
        values.add(node.getNodeValue());
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            values.add(child);
        }
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            values.add(((Element) node).getAttributes());
        } else if (nodeType == 10) {
            DocumentTypeImpl doctype = (DocumentTypeImpl) node;
            values.add(doctype.getPublicId());
            values.add(doctype.getSystemId());
            values.add(doctype.getInternalSubset());
            values.add(doctype.getEntities());
            values.add(doctype.getNotations());
        }
        return values;
    }

    @Override // org.w3c.dom.Node
    public final boolean isEqualNode(Node arg) {
        if (arg == this) {
            return true;
        }
        List<Object> listA = createEqualityKey(this);
        List<Object> listB = createEqualityKey(arg);
        if (listA.size() != listB.size()) {
            return false;
        }
        for (int i = 0; i < listA.size(); i++) {
            Object a = listA.get(i);
            Object b = listB.get(i);
            if (a != b) {
                if (a == null || b == null) {
                    return false;
                }
                if ((a instanceof String) || (a instanceof Short)) {
                    if (!a.equals(b)) {
                        return false;
                    }
                } else if (a instanceof NamedNodeMap) {
                    if (!(b instanceof NamedNodeMap) || !namedNodeMapsEqual((NamedNodeMap) a, (NamedNodeMap) b)) {
                        return false;
                    }
                } else if (!(a instanceof Node)) {
                    throw new AssertionError();
                } else if (!(b instanceof Node) || !((Node) a).isEqualNode((Node) b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean namedNodeMapsEqual(NamedNodeMap a, NamedNodeMap b) {
        Node bNode;
        if (a.getLength() != b.getLength()) {
            return false;
        }
        for (int i = 0; i < a.getLength(); i++) {
            Node aNode = a.item(i);
            if (aNode.getLocalName() == null) {
                bNode = b.getNamedItem(aNode.getNodeName());
            } else {
                bNode = b.getNamedItemNS(aNode.getNamespaceURI(), aNode.getLocalName());
            }
            if (bNode == null || !aNode.isEqualNode(bNode)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.w3c.dom.Node
    public final Object getFeature(String feature, String version) {
        if (isSupported(feature, version)) {
            return this;
        }
        return null;
    }

    @Override // org.w3c.dom.Node
    public final Object setUserData(String key, Object data, UserDataHandler handler) {
        UserData previous;
        if (key != null) {
            Map<String, UserData> map = this.document.getUserDataMap(this);
            if (data == null) {
                previous = map.remove(key);
            } else {
                previous = map.put(key, new UserData(data, handler));
            }
            if (previous != null) {
                return previous.value;
            }
            return null;
        }
        throw new NullPointerException("key == null");
    }

    @Override // org.w3c.dom.Node
    public final Object getUserData(String key) {
        if (key != null) {
            UserData userData = this.document.getUserDataMapForRead(this).get(key);
            if (userData != null) {
                return userData.value;
            }
            return null;
        }
        throw new NullPointerException("key == null");
    }

    /* access modifiers changed from: package-private */
    public static class UserData {
        final UserDataHandler handler;
        final Object value;

        UserData(Object value2, UserDataHandler handler2) {
            this.value = value2;
            this.handler = handler2;
        }
    }
}
