package com.fasterxml.jackson.databind.ext;

import X.AbstractC02640aV;
import X.AnonymousClass006;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;

public final class DOMSerializer extends StdSerializer<Node> {
    public final DOMImplementationLS A00;

    public DOMSerializer() {
        super(Node.class);
        try {
            this.A00 = (DOMImplementationLS) DOMImplementationRegistry.newInstance().getDOMImplementation("LS");
        } catch (Exception e) {
            throw new IllegalStateException(AnonymousClass006.A05("Could not instantiate DOMImplementationRegistry: ", e.getMessage()), e);
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Node node, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        Node node2 = node;
        DOMImplementationLS dOMImplementationLS = this.A00;
        if (dOMImplementationLS != null) {
            r4.A0S(dOMImplementationLS.createLSSerializer().writeToString(node2));
            return;
        }
        throw new IllegalStateException("Could not find DOM LS");
    }
}
