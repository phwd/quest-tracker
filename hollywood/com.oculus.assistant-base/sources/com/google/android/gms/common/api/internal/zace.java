package com.google.android.gms.common.api.internal;

import X.AbstractC1086sA;
import X.AbstractC1087sB;
import X.C1083s7;
import X.E2;
import X.RB;
import X.RD;
import X.RQ;
import X.RZ;
import X.Sg;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.signin.internal.zaa;
import com.google.android.gms.signin.internal.zad;
import com.google.android.gms.signin.internal.zai;
import com.google.android.gms.signin.internal.zak;
import java.util.Set;

public final class zace extends zad implements AbstractC1086sA, AbstractC1087sB {
    public static C1083s7 A07 = Sg.A00;
    public RD A00;
    public RQ A01;
    public E2 A02;
    public Set A03;
    public final Context A04;
    public final Handler A05;
    public final C1083s7 A06;

    @Override // com.google.android.gms.signin.internal.zae
    public final void A64(ConnectionResult connectionResult, zaa zaa) {
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void A65(Status status) {
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void A66(Status status, GoogleSignInAccount googleSignInAccount) {
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void A6A(zai zai) {
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void A6E(Status status) {
    }

    @Override // X.Qq
    public final void A3x(Bundle bundle) {
        this.A02.A69(this);
    }

    @Override // X.AbstractC0323Qx
    public final void A3y(ConnectionResult connectionResult) {
        this.A00.A63(connectionResult);
    }

    @Override // X.Qq
    public final void A3z(int i) {
        this.A02.A1h();
    }

    @Override // com.google.android.gms.signin.internal.zae
    public final void A6C(zak zak) {
        this.A05.post(new RB(this, zak));
    }

    public zace() {
    }

    public zace(Context context, Handler handler, RQ rq) {
        C1083s7 s7Var = A07;
        this.A04 = context;
        this.A05 = handler;
        RZ.A02(rq, "ClientSettings must not be null");
        this.A01 = rq;
        this.A03 = rq.A04;
        this.A06 = s7Var;
    }
}
