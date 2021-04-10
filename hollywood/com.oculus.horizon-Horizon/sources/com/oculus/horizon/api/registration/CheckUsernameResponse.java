package com.oculus.horizon.api.registration;

import com.oculus.http.core.base.ValidatableApiResponse;
import java.util.ArrayList;

public class CheckUsernameResponse implements ValidatableApiResponse {
    public final ArrayList<Data> data;

    public static class Data {
        public final boolean does_alias_exist;
    }

    public boolean isAvailable() {
        return !this.data.get(0).does_alias_exist;
    }

    @Override // com.oculus.http.core.base.ValidatableApiResponse
    public void validate() throws RuntimeException {
        String str;
        ArrayList<Data> arrayList = this.data;
        if (arrayList == null) {
            str = "CheckUsernameResponse data was null";
        } else if (arrayList.isEmpty()) {
            str = "CheckUsernameResponse data was empty";
        } else {
            return;
        }
        throw new NullPointerException(str);
    }
}
