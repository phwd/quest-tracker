package com.fasterxml.jackson.databind.ext;

import X.AnonymousClass08;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;

public class DOMSerializer extends StdSerializer {
    public final DOMImplementationLS A00;

    public DOMSerializer() {
        super(Node.class);
        try {
            this.A00 = (DOMImplementationLS) DOMImplementationRegistry.newInstance().getDOMImplementation("LS");
        } catch (Exception e) {
            throw new IllegalStateException(AnonymousClass08.A04("Could not instantiate DOMImplementationRegistry: ", e.getMessage()), e);
        }
    }
}
