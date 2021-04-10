package org.apache.harmony.xml.parsers;

import com.android.org.kxml2.io.KXmlParser;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import libcore.io.IoUtils;
import org.apache.harmony.xml.dom.CDATASectionImpl;
import org.apache.harmony.xml.dom.DOMImplementationImpl;
import org.apache.harmony.xml.dom.DocumentImpl;
import org.apache.harmony.xml.dom.DocumentTypeImpl;
import org.apache.harmony.xml.dom.TextImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

class DocumentBuilderImpl extends DocumentBuilder {
    private static DOMImplementationImpl dom = DOMImplementationImpl.getInstance();
    private boolean coalescing;
    private EntityResolver entityResolver;
    private ErrorHandler errorHandler;
    private boolean ignoreComments;
    private boolean ignoreElementContentWhitespace;
    private boolean namespaceAware;

    DocumentBuilderImpl() {
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public void reset() {
        this.coalescing = false;
        this.entityResolver = null;
        this.errorHandler = null;
        this.ignoreComments = false;
        this.ignoreElementContentWhitespace = false;
        this.namespaceAware = false;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public DOMImplementation getDOMImplementation() {
        return dom;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public boolean isNamespaceAware() {
        return this.namespaceAware;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public boolean isValidating() {
        return false;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public Document newDocument() {
        return dom.createDocument(null, null, null);
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public Document parse(InputSource source) throws SAXException, IOException {
        if (source != null) {
            String inputEncoding = source.getEncoding();
            String systemId = source.getSystemId();
            DocumentImpl document = new DocumentImpl(dom, null, null, null, inputEncoding);
            document.setDocumentURI(systemId);
            KXmlParser parser = new KXmlParser();
            try {
                parser.keepNamespaceAttributes();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, this.namespaceAware);
                if (source.getByteStream() != null) {
                    parser.setInput(source.getByteStream(), inputEncoding);
                } else if (source.getCharacterStream() != null) {
                    parser.setInput(source.getCharacterStream());
                } else if (systemId != null) {
                    URLConnection urlConnection = new URL(systemId).openConnection();
                    urlConnection.connect();
                    parser.setInput(urlConnection.getInputStream(), inputEncoding);
                } else {
                    throw new SAXParseException("InputSource needs a stream, reader or URI", null);
                }
                if (parser.nextToken() != 1) {
                    parse(parser, document, document, 1);
                    parser.require(1, null, null);
                    IoUtils.closeQuietly(parser);
                    return document;
                }
                throw new SAXParseException("Unexpected end of document", null);
            } catch (XmlPullParserException ex) {
                Throwable detail = ex.getDetail();
                if (detail instanceof IOException) {
                    throw ((IOException) detail);
                } else if (!(detail instanceof RuntimeException)) {
                    LocatorImpl locator = new LocatorImpl();
                    locator.setPublicId(source.getPublicId());
                    locator.setSystemId(systemId);
                    locator.setLineNumber(ex.getLineNumber());
                    locator.setColumnNumber(ex.getColumnNumber());
                    SAXParseException newEx = new SAXParseException(ex.getMessage(), locator);
                    if (this.errorHandler != null) {
                        this.errorHandler.error(newEx);
                    }
                    throw newEx;
                } else {
                    throw ((RuntimeException) detail);
                }
            } catch (Throwable th) {
                IoUtils.closeQuietly(parser);
                throw th;
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    private void parse(KXmlParser parser, DocumentImpl document, Node node, int endToken) throws XmlPullParserException, IOException {
        int token = parser.getEventType();
        while (token != endToken && token != 1) {
            String data = "";
            if (token == 8) {
                String text = parser.getText();
                int dot = text.indexOf(32);
                String target = dot != -1 ? text.substring(0, dot) : text;
                if (dot != -1) {
                    data = text.substring(dot + 1);
                }
                node.appendChild(document.createProcessingInstruction(target, data));
            } else if (token == 10) {
                document.appendChild(new DocumentTypeImpl(document, parser.getRootElementName(), parser.getPublicId(), parser.getSystemId()));
            } else if (token == 9) {
                if (!this.ignoreComments) {
                    node.appendChild(document.createComment(parser.getText()));
                }
            } else if (token == 7) {
                if (!this.ignoreElementContentWhitespace && document != node) {
                    appendText(document, node, token, parser.getText());
                }
            } else if (token == 4 || token == 5) {
                appendText(document, node, token, parser.getText());
            } else if (token == 6) {
                String entity = parser.getName();
                EntityResolver entityResolver2 = this.entityResolver;
                String resolved = resolvePredefinedOrCharacterEntity(entity);
                if (resolved != null) {
                    appendText(document, node, token, resolved);
                } else {
                    node.appendChild(document.createEntityReference(entity));
                }
            } else if (token == 2) {
                if (this.namespaceAware) {
                    String namespace = parser.getNamespace();
                    String name = parser.getName();
                    String prefix = parser.getPrefix();
                    if (data.equals(namespace)) {
                        namespace = null;
                    }
                    Element element = document.createElementNS(namespace, name);
                    element.setPrefix(prefix);
                    node.appendChild(element);
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        String attrNamespace = parser.getAttributeNamespace(i);
                        String attrPrefix = parser.getAttributePrefix(i);
                        String attrName = parser.getAttributeName(i);
                        String attrValue = parser.getAttributeValue(i);
                        if (data.equals(attrNamespace)) {
                            attrNamespace = null;
                        }
                        Attr attr = document.createAttributeNS(attrNamespace, attrName);
                        attr.setPrefix(attrPrefix);
                        attr.setValue(attrValue);
                        element.setAttributeNodeNS(attr);
                    }
                    parser.nextToken();
                    parse(parser, document, element, 3);
                    parser.require(3, namespace, name);
                } else {
                    String name2 = parser.getName();
                    Element element2 = document.createElement(name2);
                    node.appendChild(element2);
                    for (int i2 = 0; i2 < parser.getAttributeCount(); i2++) {
                        String attrName2 = parser.getAttributeName(i2);
                        String attrValue2 = parser.getAttributeValue(i2);
                        Attr attr2 = document.createAttribute(attrName2);
                        attr2.setValue(attrValue2);
                        element2.setAttributeNode(attr2);
                    }
                    parser.nextToken();
                    parse(parser, document, element2, 3);
                    parser.require(3, data, name2);
                }
            }
            token = parser.nextToken();
        }
    }

    private void appendText(DocumentImpl document, Node parent, int token, String text) {
        Node node;
        Node lastChild;
        if (!text.isEmpty()) {
            if ((this.coalescing || token != 5) && (lastChild = parent.getLastChild()) != null && lastChild.getNodeType() == 3) {
                ((Text) lastChild).appendData(text);
                return;
            }
            if (token == 5) {
                node = new CDATASectionImpl(document, text);
            } else {
                node = new TextImpl(document, text);
            }
            parent.appendChild(node);
        }
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public void setEntityResolver(EntityResolver resolver) {
        this.entityResolver = resolver;
    }

    @Override // javax.xml.parsers.DocumentBuilder
    public void setErrorHandler(ErrorHandler handler) {
        this.errorHandler = handler;
    }

    public void setIgnoreComments(boolean value) {
        this.ignoreComments = value;
    }

    public void setCoalescing(boolean value) {
        this.coalescing = value;
    }

    public void setIgnoreElementContentWhitespace(boolean value) {
        this.ignoreElementContentWhitespace = value;
    }

    public void setNamespaceAware(boolean value) {
        this.namespaceAware = value;
    }

    private String resolvePredefinedOrCharacterEntity(String entityName) {
        if (entityName.startsWith("#x")) {
            return resolveCharacterReference(entityName.substring(2), 16);
        }
        if (entityName.startsWith("#")) {
            return resolveCharacterReference(entityName.substring(1), 10);
        }
        if ("lt".equals(entityName)) {
            return "<";
        }
        if ("gt".equals(entityName)) {
            return ">";
        }
        if ("amp".equals(entityName)) {
            return "&";
        }
        if ("apos".equals(entityName)) {
            return "'";
        }
        if ("quot".equals(entityName)) {
            return "\"";
        }
        return null;
    }

    private String resolveCharacterReference(String value, int base) {
        try {
            int codePoint = Integer.parseInt(value, base);
            if (Character.isBmpCodePoint(codePoint)) {
                return String.valueOf((char) codePoint);
            }
            return new String(Character.toChars(codePoint));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
