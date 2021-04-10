package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.messengervr.msys.MessengerVrTableToProcedureNameMapRegisterer;
import com.facebook.msys.mci.AuthData;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mcs.DasmConfigCreator;
import com.oculus.messengervr.fb.VrMsysMailboxCreator;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Yj  reason: invalid class name and case insensitive filesystem */
public final class C06281Yj {
    @Nullable
    public final MessengerVrTableToProcedureNameMapRegisterer A00;
    public final C06321Yt A01;
    public final AuthData A02;
    @Nullable
    public final Database.OpenCallback A03;
    public final DasmConfigCreator A04;
    public final VrMsysMailboxCreator.AnonymousClass2 A05;
    @Nullable
    public final VrMsysMqttClientCallbacks A06;
    public final Boolean A07;
    public final Boolean A08;
    public final Boolean A09;
    public final Boolean A0A;
    public final String A0B;
    public final String A0C;
    public final String A0D;

    public C06281Yj(C06291Yl r4) {
        AuthData authData = r4.A05;
        this.A02 = authData;
        this.A04 = r4.A06;
        String str = r4.A03;
        this.A0B = str == null ? AnonymousClass006.A07("msys_database_", authData.getFacebookUserID()) : str;
        this.A03 = r4.A01;
        this.A00 = r4.A00;
        this.A05 = r4.A07;
        this.A07 = false;
        this.A08 = false;
        this.A09 = true;
        this.A0A = false;
        this.A01 = r4.A04;
        this.A0C = r4.A08;
        this.A06 = r4.A02;
        this.A0D = "3657845087671377";
    }
}
