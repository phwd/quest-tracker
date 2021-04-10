package com.facebook.graphql.query;

import X.C03130be;
import com.facebook.graphql.calls.GraphQlCallInput;
import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = GraphQlQueryParamSetDeserializer.class)
@JsonSerialize(using = GraphQlQueryParamSetSerializer.class)
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class GraphQlQueryParamSet {
    public C03130be A00 = new C03130be();

    public final void A00(String str, GraphQlCallInput graphQlCallInput) {
        if (graphQlCallInput != null) {
            this.A00.A03().A05(str, graphQlCallInput.A03());
        }
    }
}
