package X;

import com.facebook.messengervr.msys.MessengerVrTableToProcedureNameMapRegisterer;
import com.facebook.msys.mci.AuthData;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mcs.DasmConfigCreator;
import com.oculus.messengervr.fb.VrMsysMailboxCreator;
import com.oculus.messengervr.fb.VrMsysMqttClientCallbacks;
import javax.annotation.Nullable;

/* renamed from: X.1Yl  reason: invalid class name and case insensitive filesystem */
public class C06291Yl {
    @Nullable
    public MessengerVrTableToProcedureNameMapRegisterer A00;
    @Nullable
    public Database.OpenCallback A01;
    @Nullable
    public VrMsysMqttClientCallbacks A02;
    @Nullable
    public String A03;
    public final C06321Yt A04;
    public final AuthData A05;
    public final DasmConfigCreator A06;
    public final VrMsysMailboxCreator.AnonymousClass2 A07;
    public final String A08;

    public C06291Yl(AuthData authData, DasmConfigCreator dasmConfigCreator, VrMsysMailboxCreator.AnonymousClass2 r4, String str, C06321Yt r6) {
        if (authData != null) {
            this.A05 = authData;
            this.A06 = dasmConfigCreator;
            this.A07 = r4;
            if (r6 != null) {
                this.A04 = r6;
                if (str != null) {
                    this.A08 = str;
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }
}
