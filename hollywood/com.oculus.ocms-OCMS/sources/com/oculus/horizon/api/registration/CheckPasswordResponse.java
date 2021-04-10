package com.oculus.horizon.api.registration;

import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

public class CheckPasswordResponse implements ValidatableApiResponse {
    public ArrayList<Data> data;

    public static class Data {
        public boolean is_password_valid;
    }

    public boolean isValid() {
        return this.data.get(0).is_password_valid;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        ArrayList<Data> arrayList = this.data;
        if (arrayList == null) {
            throw new NullPointerException("CheckPasswordResponse data was null");
        } else if (arrayList.isEmpty()) {
            throw new NullPointerException("CheckPasswordResponse data was empty");
        }
    }
}
