package com.oculus.horizon.api.registration;

import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

public class CheckEmailResponse implements ValidatableApiResponse {
    public ArrayList<Data> data;

    public static class Data {
        public boolean does_email_exist;
    }

    public boolean isAvailable() {
        return !this.data.get(0).does_email_exist;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        ArrayList<Data> arrayList = this.data;
        if (arrayList == null) {
            throw new NullPointerException("CheckEmailResponse data was null");
        } else if (arrayList.isEmpty()) {
            throw new NullPointerException("CheckEmailResponse data was empty");
        }
    }
}
