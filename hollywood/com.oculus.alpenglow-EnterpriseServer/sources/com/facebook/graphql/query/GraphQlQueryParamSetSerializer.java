package com.facebook.graphql.query;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C03130be;
import X.C05910ld;
import com.facebook.graphql.calls.GraphQlCallInput;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.io.IOException;
import java.util.TreeMap;

public final class GraphQlQueryParamSetSerializer extends JsonSerializer<GraphQlQueryParamSet> {
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0D(GraphQlQueryParamSet graphQlQueryParamSet, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C05910ld {
        GraphQlQueryParamSet graphQlQueryParamSet2 = graphQlQueryParamSet;
        if (graphQlQueryParamSet2 == null) {
            r5.A0D();
        }
        r5.A0F();
        r5.A0P("params");
        C03130be r2 = graphQlQueryParamSet2.A00;
        TreeMap treeMap = new TreeMap();
        GraphQlCallInput.A02(r2, r2.mPoolableParams, treeMap);
        r5.A09(treeMap);
        r5.A0P("input_name");
        r5.A0S(null);
        r5.A0C();
    }
}
