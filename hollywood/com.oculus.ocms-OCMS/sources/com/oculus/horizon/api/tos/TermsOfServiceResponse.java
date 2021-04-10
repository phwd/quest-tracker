package com.oculus.horizon.api.tos;

import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

public class TermsOfServiceResponse implements ValidatableApiResponse {
    public ArrayList<Data> data;

    public static class Data {
        public String content;
        public String id;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        ArrayList<Data> arrayList = this.data;
        if (arrayList == null || arrayList.isEmpty()) {
            throw new NullPointerException("TermsOfServiceResponse had no data");
        }
    }

    public Data getData() {
        return this.data.get(0);
    }
}
