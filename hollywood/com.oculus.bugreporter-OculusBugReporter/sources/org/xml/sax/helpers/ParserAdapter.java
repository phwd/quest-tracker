package org.xml.sax.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import javax.xml.XMLConstants;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

public class ParserAdapter implements XMLReader, DocumentHandler {
    private static final String FEATURES = "http://xml.org/sax/features/";
    private static final String NAMESPACES = "http://xml.org/sax/features/namespaces";
    private static final String NAMESPACE_PREFIXES = "http://xml.org/sax/features/namespace-prefixes";
    private static final String XMLNS_URIs = "http://xml.org/sax/features/xmlns-uris";
    private AttributeListAdapter attAdapter;
    private AttributesImpl atts;
    ContentHandler contentHandler;
    DTDHandler dtdHandler;
    EntityResolver entityResolver;
    ErrorHandler errorHandler;
    Locator locator;
    private String[] nameParts;
    private boolean namespaces;
    private NamespaceSupport nsSupport;
    private Parser parser;
    private boolean parsing;
    private boolean prefixes;
    private boolean uris;

    public ParserAdapter() throws SAXException {
        this.parsing = false;
        this.nameParts = new String[3];
        this.parser = null;
        this.atts = null;
        this.namespaces = true;
        this.prefixes = false;
        this.uris = false;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
        String driver = System.getProperty("org.xml.sax.parser");
        try {
            setup(ParserFactory.makeParser());
        } catch (ClassNotFoundException e1) {
            throw new SAXException("Cannot find SAX1 driver class " + driver, e1);
        } catch (IllegalAccessException e2) {
            throw new SAXException("SAX1 driver class " + driver + " found but cannot be loaded", e2);
        } catch (InstantiationException e3) {
            throw new SAXException("SAX1 driver class " + driver + " loaded but cannot be instantiated", e3);
        } catch (ClassCastException e) {
            throw new SAXException("SAX1 driver class " + driver + " does not implement org.xml.sax.Parser");
        } catch (NullPointerException e4) {
            throw new SAXException("System property org.xml.sax.parser not specified");
        }
    }

    public ParserAdapter(Parser parser2) {
        this.parsing = false;
        this.nameParts = new String[3];
        this.parser = null;
        this.atts = null;
        this.namespaces = true;
        this.prefixes = false;
        this.uris = false;
        this.entityResolver = null;
        this.dtdHandler = null;
        this.contentHandler = null;
        this.errorHandler = null;
        setup(parser2);
    }

    private void setup(Parser parser2) {
        if (parser2 != null) {
            this.parser = parser2;
            this.atts = new AttributesImpl();
            this.nsSupport = new NamespaceSupport();
            this.attAdapter = new AttributeListAdapter();
            return;
        }
        throw new NullPointerException("Parser argument must not be null");
    }

    @Override // org.xml.sax.XMLReader
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals(NAMESPACES)) {
            checkNotParsing("feature", name);
            this.namespaces = value;
            if (!this.namespaces && !this.prefixes) {
                this.prefixes = true;
            }
        } else if (name.equals(NAMESPACE_PREFIXES)) {
            checkNotParsing("feature", name);
            this.prefixes = value;
            if (!this.prefixes && !this.namespaces) {
                this.namespaces = true;
            }
        } else if (name.equals(XMLNS_URIs)) {
            checkNotParsing("feature", name);
            this.uris = value;
        } else {
            throw new SAXNotRecognizedException("Feature: " + name);
        }
    }

    @Override // org.xml.sax.XMLReader
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (name.equals(NAMESPACES)) {
            return this.namespaces;
        }
        if (name.equals(NAMESPACE_PREFIXES)) {
            return this.prefixes;
        }
        if (name.equals(XMLNS_URIs)) {
            return this.uris;
        }
        throw new SAXNotRecognizedException("Feature: " + name);
    }

    @Override // org.xml.sax.XMLReader
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + name);
    }

    @Override // org.xml.sax.XMLReader
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        throw new SAXNotRecognizedException("Property: " + name);
    }

    @Override // org.xml.sax.XMLReader
    public void setEntityResolver(EntityResolver resolver) {
        this.entityResolver = resolver;
    }

    @Override // org.xml.sax.XMLReader
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    @Override // org.xml.sax.XMLReader
    public void setDTDHandler(DTDHandler handler) {
        this.dtdHandler = handler;
    }

    @Override // org.xml.sax.XMLReader
    public DTDHandler getDTDHandler() {
        return this.dtdHandler;
    }

    @Override // org.xml.sax.XMLReader
    public void setContentHandler(ContentHandler handler) {
        this.contentHandler = handler;
    }

    @Override // org.xml.sax.XMLReader
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    @Override // org.xml.sax.XMLReader
    public void setErrorHandler(ErrorHandler handler) {
        this.errorHandler = handler;
    }

    @Override // org.xml.sax.XMLReader
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override // org.xml.sax.XMLReader
    public void parse(String systemId) throws IOException, SAXException {
        parse(new InputSource(systemId));
    }

    @Override // org.xml.sax.XMLReader
    public void parse(InputSource input) throws IOException, SAXException {
        if (!this.parsing) {
            setupParser();
            this.parsing = true;
            try {
                this.parser.parse(input);
                this.parsing = false;
            } finally {
                this.parsing = false;
            }
        } else {
            throw new SAXException("Parser is already in use");
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void setDocumentLocator(Locator locator2) {
        this.locator = locator2;
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.setDocumentLocator(locator2);
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void startDocument() throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.startDocument();
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void endDocument() throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.endDocument();
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void startElement(String qName, AttributeList qAtts) throws SAXException {
        String prefix;
        String prefix2;
        if (this.namespaces) {
            this.nsSupport.pushContext();
            int length = qAtts.getLength();
            for (int i = 0; i < length; i++) {
                String attQName = qAtts.getName(i);
                if (attQName.startsWith(XMLConstants.XMLNS_ATTRIBUTE)) {
                    int n = attQName.indexOf(58);
                    if (n == -1 && attQName.length() == 5) {
                        prefix2 = "";
                    } else if (n == 5) {
                        prefix2 = attQName.substring(n + 1);
                    }
                    String value = qAtts.getValue(i);
                    if (!this.nsSupport.declarePrefix(prefix2, value)) {
                        reportError("Illegal Namespace prefix: " + prefix2);
                    } else {
                        ContentHandler contentHandler2 = this.contentHandler;
                        if (contentHandler2 != null) {
                            contentHandler2.startPrefixMapping(prefix2, value);
                        }
                    }
                }
            }
            this.atts.clear();
            ArrayList<SAXParseException> exceptions = null;
            for (int i2 = 0; i2 < length; i2++) {
                String attQName2 = qAtts.getName(i2);
                String type = qAtts.getType(i2);
                String value2 = qAtts.getValue(i2);
                if (attQName2.startsWith(XMLConstants.XMLNS_ATTRIBUTE)) {
                    int n2 = attQName2.indexOf(58);
                    if (n2 == -1 && attQName2.length() == 5) {
                        prefix = "";
                    } else if (n2 != 5) {
                        prefix = null;
                    } else {
                        prefix = attQName2.substring(6);
                    }
                    if (prefix != null) {
                        if (this.prefixes) {
                            if (this.uris) {
                                AttributesImpl attributesImpl = this.atts;
                                NamespaceSupport namespaceSupport = this.nsSupport;
                                attributesImpl.addAttribute("http://www.w3.org/XML/1998/namespace", prefix, attQName2.intern(), type, value2);
                            } else {
                                this.atts.addAttribute("", "", attQName2.intern(), type, value2);
                            }
                        }
                    }
                }
                try {
                    String[] attName = processName(attQName2, true, true);
                    this.atts.addAttribute(attName[0], attName[1], attName[2], type, value2);
                } catch (SAXException e) {
                    if (exceptions == null) {
                        exceptions = new ArrayList<>();
                    }
                    exceptions.add((SAXParseException) e);
                    this.atts.addAttribute("", attQName2, attQName2, type, value2);
                }
            }
            if (!(exceptions == null || this.errorHandler == null)) {
                Iterator<SAXParseException> it = exceptions.iterator();
                while (it.hasNext()) {
                    this.errorHandler.error(it.next());
                }
            }
            if (this.contentHandler != null) {
                String[] name = processName(qName, false, false);
                this.contentHandler.startElement(name[0], name[1], name[2], this.atts);
            }
        } else if (this.contentHandler != null) {
            this.attAdapter.setAttributeList(qAtts);
            this.contentHandler.startElement("", "", qName.intern(), this.attAdapter);
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void endElement(String qName) throws SAXException {
        if (!this.namespaces) {
            ContentHandler contentHandler2 = this.contentHandler;
            if (contentHandler2 != null) {
                contentHandler2.endElement("", "", qName.intern());
                return;
            }
            return;
        }
        String[] names = processName(qName, false, false);
        ContentHandler contentHandler3 = this.contentHandler;
        if (contentHandler3 != null) {
            contentHandler3.endElement(names[0], names[1], names[2]);
            Enumeration prefixes2 = this.nsSupport.getDeclaredPrefixes();
            while (prefixes2.hasMoreElements()) {
                this.contentHandler.endPrefixMapping((String) prefixes2.nextElement());
            }
        }
        this.nsSupport.popContext();
    }

    @Override // org.xml.sax.DocumentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.characters(ch, start, length);
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.ignorableWhitespace(ch, start, length);
        }
    }

    @Override // org.xml.sax.DocumentHandler
    public void processingInstruction(String target, String data) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.processingInstruction(target, data);
        }
    }

    private void setupParser() {
        if (this.prefixes || this.namespaces) {
            this.nsSupport.reset();
            if (this.uris) {
                this.nsSupport.setNamespaceDeclUris(true);
            }
            EntityResolver entityResolver2 = this.entityResolver;
            if (entityResolver2 != null) {
                this.parser.setEntityResolver(entityResolver2);
            }
            DTDHandler dTDHandler = this.dtdHandler;
            if (dTDHandler != null) {
                this.parser.setDTDHandler(dTDHandler);
            }
            ErrorHandler errorHandler2 = this.errorHandler;
            if (errorHandler2 != null) {
                this.parser.setErrorHandler(errorHandler2);
            }
            this.parser.setDocumentHandler(this);
            this.locator = null;
            return;
        }
        throw new IllegalStateException();
    }

    private String[] processName(String qName, boolean isAttribute, boolean useException) throws SAXException {
        String[] parts = this.nsSupport.processName(qName, this.nameParts, isAttribute);
        if (parts != null) {
            return parts;
        }
        if (!useException) {
            reportError("Undeclared prefix: " + qName);
            String[] parts2 = new String[3];
            parts2[1] = "";
            parts2[0] = "";
            parts2[2] = qName.intern();
            return parts2;
        }
        throw makeException("Undeclared prefix: " + qName);
    }

    /* access modifiers changed from: package-private */
    public void reportError(String message) throws SAXException {
        ErrorHandler errorHandler2 = this.errorHandler;
        if (errorHandler2 != null) {
            errorHandler2.error(makeException(message));
        }
    }

    private SAXParseException makeException(String message) {
        Locator locator2 = this.locator;
        if (locator2 != null) {
            return new SAXParseException(message, locator2);
        }
        return new SAXParseException(message, null, null, -1, -1);
    }

    private void checkNotParsing(String type, String name) throws SAXNotSupportedException {
        if (this.parsing) {
            throw new SAXNotSupportedException("Cannot change " + type + ' ' + name + " while parsing");
        }
    }

    /* access modifiers changed from: package-private */
    public final class AttributeListAdapter implements Attributes {
        private AttributeList qAtts;

        AttributeListAdapter() {
        }

        /* access modifiers changed from: package-private */
        public void setAttributeList(AttributeList qAtts2) {
            this.qAtts = qAtts2;
        }

        @Override // org.xml.sax.Attributes
        public int getLength() {
            return this.qAtts.getLength();
        }

        @Override // org.xml.sax.Attributes
        public String getURI(int i) {
            return "";
        }

        @Override // org.xml.sax.Attributes
        public String getLocalName(int i) {
            return "";
        }

        @Override // org.xml.sax.Attributes
        public String getQName(int i) {
            return this.qAtts.getName(i).intern();
        }

        @Override // org.xml.sax.Attributes
        public String getType(int i) {
            return this.qAtts.getType(i).intern();
        }

        @Override // org.xml.sax.Attributes
        public String getValue(int i) {
            return this.qAtts.getValue(i);
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String uri, String localName) {
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public int getIndex(String qName) {
            int max = ParserAdapter.this.atts.getLength();
            for (int i = 0; i < max; i++) {
                if (this.qAtts.getName(i).equals(qName)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String uri, String localName) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getType(String qName) {
            return this.qAtts.getType(qName).intern();
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String uri, String localName) {
            return null;
        }

        @Override // org.xml.sax.Attributes
        public String getValue(String qName) {
            return this.qAtts.getValue(qName);
        }
    }
}
