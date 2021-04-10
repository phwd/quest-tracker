package org.xml.sax;

public class SAXException extends Exception {
    private Exception exception = null;

    public SAXException() {
    }

    public SAXException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        Exception exc;
        String message = super.getMessage();
        return (message != null || (exc = this.exception) == null) ? message : exc.getMessage();
    }

    @Override // java.lang.Throwable
    public String toString() {
        Exception exc = this.exception;
        if (exc != null) {
            return exc.toString();
        }
        return super.toString();
    }
}
