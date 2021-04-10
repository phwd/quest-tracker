package com.oculus.horizon.api.registration;

import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

public class CheckEmailAndPasswordResponse implements ValidatableApiResponse {
    public final ArrayList<Data> data;

    public static class Data {
        public final boolean does_email_exist;
        public final boolean is_password_valid;
    }

    public boolean isEmailAvailable() {
        return !this.data.get(0).does_email_exist;
    }

    public boolean isPasswordValid() {
        return this.data.get(0).is_password_valid;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        ArrayList<Data> arrayList = this.data;
        if (arrayList == null) {
            str = "CheckEmailResponse data was null";
        } else if (arrayList.isEmpty()) {
            str = "CheckEmailResponse data was empty";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
