package org.xml.sax;

public class SAXException extends Exception {
    private Exception exception;

    public SAXException() {
        this.exception = null;
    }

    public SAXException(String message) {
        super(message);
        this.exception = null;
    }

    public SAXException(Exception e) {
        this.exception = e;
    }

    public SAXException(String message, Exception e) {
        super(message);
        this.exception = e;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        Exception exc;
        String message = super.getMessage();
        if (message != null || (exc = this.exception) == null) {
            return message;
        }
        return exc.getMessage();
    }

    public Exception getException() {
        return this.exception;
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
