package com.facebook.graphql.calls;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C05910ld;
import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.TreeMap;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class GraphQlCallInputSerializer extends JsonSerializer<GraphQlCallInput> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0D(GraphQlCallInput graphQlCallInput, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C05910ld {
        GraphQlCallInput graphQlCallInput2 = graphQlCallInput;
        if (graphQlCallInput2 == null) {
            r4.A0D();
        }
        TreeMap treeMap = new TreeMap();
        GraphQlCallInput.A02(graphQlCallInput2, graphQlCallInput2.mPoolableParams, treeMap);
        r4.A09(treeMap);
    }
}
