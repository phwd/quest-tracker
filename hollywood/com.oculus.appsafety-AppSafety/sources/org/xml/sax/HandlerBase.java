package org.xml.sax;

@Deprecated
public class HandlerBase implements EntityResolver, DTDHandler, DocumentHandler, ErrorHandler {
    @Override // org.xml.sax.EntityResolver
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException {
        return null;
    }

    @Override // org.xml.sax.DTDHandler
    public void notationDecl(String name, String publicId, String systemId) {
    }

    @Override // org.xml.sax.DTDHandler
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) {
    }

    @Override // org.xml.sax.DocumentHandler
    public void setDocumentLocator(Locator locator) {
    }

    @Override // org.xml.sax.DocumentHandler
    public void startDocument() throws SAXException {
    }

    @Override // org.xml.sax.DocumentHandler
    public void endDocument() throws SAXException {
    }

    @Override // org.xml.sax.DocumentHandler
    public void startElement(String name, AttributeList attributes) throws SAXException {
    }

    @Override // org.xml.sax.DocumentHandler
    public void endElement(String name) throws SAXException {
    }

    @Override // org.xml.sax.DocumentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
    }

    @Override // org.xml.sax.DocumentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
    }

    @Override // org.xml.sax.DocumentHandler
    public void processingInstruction(String target, String data) throws SAXException {
    }

    @Override // org.xml.sax.ErrorHandler
    public void warning(SAXParseException e) throws SAXException {
    }

    @Override // org.xml.sax.ErrorHandler
    public void error(SAXParseException e) throws SAXException {
    }

    @Override // org.xml.sax.ErrorHandler
    public void fatalError(SAXParseException e) throws SAXException {
        throw e;
    }
}
