package org.xml.sax.ext;

import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DefaultHandler2 extends DefaultHandler implements LexicalHandler, DeclHandler, EntityResolver2 {
    @Override // org.xml.sax.ext.LexicalHandler
    public void startCDATA() throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endCDATA() throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endDTD() throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void startEntity(String name) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void endEntity(String name) throws SAXException {
    }

    @Override // org.xml.sax.ext.LexicalHandler
    public void comment(char[] ch, int start, int length) throws SAXException {
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void attributeDecl(String eName, String aName, String type, String mode, String value) throws SAXException {
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void elementDecl(String name, String model) throws SAXException {
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {
    }

    @Override // org.xml.sax.ext.DeclHandler
    public void internalEntityDecl(String name, String value) throws SAXException {
    }

    @Override // org.xml.sax.ext.EntityResolver2
    public InputSource getExternalSubset(String name, String baseURI) throws SAXException, IOException {
        return null;
    }

    @Override // org.xml.sax.ext.EntityResolver2
    public InputSource resolveEntity(String name, String publicId, String baseURI, String systemId) throws SAXException, IOException {
        return null;
    }

    @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.EntityResolver
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        return resolveEntity(null, publicId, null, systemId);
    }
}
