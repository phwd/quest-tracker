package com.google.android.gms.signin.internal;

import android.os.IInterface;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public interface zae extends IInterface {
    void A64(ConnectionResult connectionResult, zaa zaa);

    void A65(Status status);

    void A66(Status status, GoogleSignInAccount googleSignInAccount);

    void A6A(zai zai);

    void A6C(zak zak);

    void A6E(Status status);
}
