package com.fasterxml.jackson.databind.ext;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AnonymousClass006;
import X.C02180iD;
import X.C02310iT;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;

public class DOMSerializer extends StdSerializer<Node> {
    public final DOMImplementationLS A00;

    public DOMSerializer() {
        super(Node.class);
        try {
            this.A00 = (DOMImplementationLS) DOMImplementationRegistry.newInstance().getDOMImplementation("LS");
        } catch (Exception e) {
            throw new IllegalStateException(AnonymousClass006.A07("Could not instantiate DOMImplementationRegistry: ", e.getMessage()), e);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectAnyFormat");
        }
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Node node, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        Node node2 = node;
        DOMImplementationLS dOMImplementationLS = this.A00;
        if (dOMImplementationLS != null) {
            r4.A0U(dOMImplementationLS.createLSSerializer().writeToString(node2));
            return;
        }
        throw new IllegalStateException("Could not find DOM LS");
    }
}
