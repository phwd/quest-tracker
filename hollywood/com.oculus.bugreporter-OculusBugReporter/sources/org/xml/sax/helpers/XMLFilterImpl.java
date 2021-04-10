package org.xml.sax.helpers;

import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLFilter;
import org.xml.sax.XMLReader;

public class XMLFilterImpl implements XMLFilter, EntityResolver, DTDHandler, ContentHandler, ErrorHandler {
    private ContentHandler contentHandler = null;
    private DTDHandler dtdHandler = null;
    private EntityResolver entityResolver = null;
    private ErrorHandler errorHandler = null;
    private Locator locator = null;
    private XMLReader parent = null;

    public XMLFilterImpl() {
    }

    public XMLFilterImpl(XMLReader parent2) {
        setParent(parent2);
    }

    @Override // org.xml.sax.XMLFilter
    public void setParent(XMLReader parent2) {
        this.parent = parent2;
    }

    @Override // org.xml.sax.XMLFilter
    public XMLReader getParent() {
        return this.parent;
    }

    @Override // org.xml.sax.XMLReader
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {
        XMLReader xMLReader = this.parent;
        if (xMLReader != null) {
            xMLReader.setFeature(name, value);
            return;
        }
        throw new SAXNotRecognizedException("Feature: " + name);
    }

    @Override // org.xml.sax.XMLReader
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        XMLReader xMLReader = this.parent;
        if (xMLReader != null) {
            return xMLReader.getFeature(name);
        }
        throw new SAXNotRecognizedException("Feature: " + name);
    }

    @Override // org.xml.sax.XMLReader
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {
        XMLReader xMLReader = this.parent;
        if (xMLReader != null) {
            xMLReader.setProperty(name, value);
            return;
        }
        throw new SAXNotRecognizedException("Property: " + name);
    }

    @Override // org.xml.sax.XMLReader
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        XMLReader xMLReader = this.parent;
        if (xMLReader != null) {
            return xMLReader.getProperty(name);
        }
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
    public void parse(InputSource input) throws SAXException, IOException {
        setupParse();
        this.parent.parse(input);
    }

    @Override // org.xml.sax.XMLReader
    public void parse(String systemId) throws SAXException, IOException {
        parse(new InputSource(systemId));
    }

    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        EntityResolver entityResolver2 = this.entityResolver;
        if (entityResolver2 != null) {
            return entityResolver2.resolveEntity(publicId, systemId);
        }
        return null;
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        DTDHandler dTDHandler = this.dtdHandler;
        if (dTDHandler != null) {
            dTDHandler.notationDecl(name, publicId, systemId);
        }
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        DTDHandler dTDHandler = this.dtdHandler;
        if (dTDHandler != null) {
            dTDHandler.unparsedEntityDecl(name, publicId, systemId, notationName);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator2) {
        this.locator = locator2;
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.setDocumentLocator(locator2);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.startDocument();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.endDocument();
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.startPrefixMapping(prefix, uri);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String prefix) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.endPrefixMapping(prefix);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.startElement(uri, localName, qName, atts);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.endElement(uri, localName, qName);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.characters(ch, start, length);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.ignorableWhitespace(ch, start, length);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String target, String data) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.processingInstruction(target, data);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String name) throws SAXException {
        ContentHandler contentHandler2 = this.contentHandler;
        if (contentHandler2 != null) {
            contentHandler2.skippedEntity(name);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException e) throws SAXException {
        ErrorHandler errorHandler2 = this.errorHandler;
        if (errorHandler2 != null) {
            errorHandler2.warning(e);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException e) throws SAXException {
        ErrorHandler errorHandler2 = this.errorHandler;
        if (errorHandler2 != null) {
            errorHandler2.error(e);
        }
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException e) throws SAXException {
        ErrorHandler errorHandler2 = this.errorHandler;
        if (errorHandler2 != null) {
            errorHandler2.fatalError(e);
        }
    }

    private void setupParse() {
        XMLReader xMLReader = this.parent;
        if (xMLReader != null) {
            xMLReader.setEntityResolver(this);
            this.parent.setDTDHandler(this);
            this.parent.setContentHandler(this);
            this.parent.setErrorHandler(this);
            return;
        }
        throw new NullPointerException("No parent for filter");
    }
}
