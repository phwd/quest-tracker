package org.xml.sax.helpers;

import java.io.IOException;
import java.util.Locale;
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
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

public class XMLReaderAdapter implements Parser, ContentHandler {
    DocumentHandler documentHandler;
    AttributesAdapter qAtts;
    XMLReader xmlReader;

    public XMLReaderAdapter() throws SAXException {
        setup(XMLReaderFactory.createXMLReader());
    }

    public XMLReaderAdapter(XMLReader xmlReader2) {
        setup(xmlReader2);
    }

    private void setup(XMLReader xmlReader2) {
        if (xmlReader2 != null) {
            this.xmlReader = xmlReader2;
            this.qAtts = new AttributesAdapter();
            return;
        }
        throw new NullPointerException("XMLReader must not be null");
    }

    @Override // org.xml.sax.Parser
    public void setLocale(Locale locale) throws SAXException {
        throw new SAXNotSupportedException("setLocale not supported");
    }

    @Override // org.xml.sax.Parser
    public void setEntityResolver(EntityResolver resolver) {
        this.xmlReader.setEntityResolver(resolver);
    }

    @Override // org.xml.sax.Parser
    public void setDTDHandler(DTDHandler handler) {
        this.xmlReader.setDTDHandler(handler);
    }

    @Override // org.xml.sax.Parser
    public void setDocumentHandler(DocumentHandler handler) {
        this.documentHandler = handler;
    }

    @Override // org.xml.sax.Parser
    public void setErrorHandler(ErrorHandler handler) {
        this.xmlReader.setErrorHandler(handler);
    }

    @Override // org.xml.sax.Parser
    public void parse(String systemId) throws IOException, SAXException {
        parse(new InputSource(systemId));
    }

    @Override // org.xml.sax.Parser
    public void parse(InputSource input) throws IOException, SAXException {
        setupXMLReader();
        this.xmlReader.parse(input);
    }

    private void setupXMLReader() throws SAXException {
        this.xmlReader.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
        try {
            this.xmlReader.setFeature("http://xml.org/sax/features/namespaces", false);
        } catch (SAXException e) {
        }
        this.xmlReader.setContentHandler(this);
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        DocumentHandler documentHandler2 = this.documentHandler;
        if (documentHandler2 != null) {
            documentHandler2.setDocumentLocator(locator);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        DocumentHandler documentHandler2 = this.documentHandler;
        if (documentHandler2 != null) {
            documentHandler2.startDocument();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        DocumentHandler documentHandler2 = this.documentHandler;
        if (documentHandler2 != null) {
            documentHandler2.endDocument();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String prefix, String uri) {
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String prefix) {
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (this.documentHandler != null) {
            this.qAtts.setAttributes(atts);
            this.documentHandler.startElement(qName, this.qAtts);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        DocumentHandler documentHandler2 = this.documentHandler;
        if (documentHandler2 != null) {
            documentHandler2.endElement(qName);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        DocumentHandler documentHandler2 = this.documentHandler;
        if (documentHandler2 != null) {
            documentHandler2.characters(ch, start, length);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        DocumentHandler documentHandler2 = this.documentHandler;
        if (documentHandler2 != null) {
            documentHandler2.ignorableWhitespace(ch, start, length);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String target, String data) throws SAXException {
        DocumentHandler documentHandler2 = this.documentHandler;
        if (documentHandler2 != null) {
            documentHandler2.processingInstruction(target, data);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String name) throws SAXException {
    }

    /* access modifiers changed from: package-private */
    public static final class AttributesAdapter implements AttributeList {
        private Attributes attributes;

        AttributesAdapter() {
        }

        /* access modifiers changed from: package-private */
        public void setAttributes(Attributes attributes2) {
            this.attributes = attributes2;
        }

        @Override // org.xml.sax.AttributeList
        public int getLength() {
            return this.attributes.getLength();
        }

        @Override // org.xml.sax.AttributeList
        public String getName(int i) {
            return this.attributes.getQName(i);
        }

        @Override // org.xml.sax.AttributeList
        public String getType(int i) {
            return this.attributes.getType(i);
        }

        @Override // org.xml.sax.AttributeList
        public String getValue(int i) {
            return this.attributes.getValue(i);
        }

        @Override // org.xml.sax.AttributeList
        public String getType(String qName) {
            return this.attributes.getType(qName);
        }

        @Override // org.xml.sax.AttributeList
        public String getValue(String qName) {
            return this.attributes.getValue(qName);
        }
    }
}
