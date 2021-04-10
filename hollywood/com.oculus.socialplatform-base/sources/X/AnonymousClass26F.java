package X;

import com.facebook.messengervrcqljava.TempMessageList;
import com.facebook.msys.mci.CQLResultSet;
import java.util.Map;
import javax.annotation.Nullable;

/* renamed from: X.26F  reason: invalid class name */
public class AnonymousClass26F implements AnonymousClass1ZW {
    public final /* synthetic */ AnonymousClass26E A00;

    public AnonymousClass26F(AnonymousClass26E r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1ZW
    public final void A7y(@Nullable Map<Object, Object> map) {
        TempMessageList tempMessageList;
        if (map != null) {
            Object obj = map.get(AnonymousClass269.A00("MCAMailboxMessengerVrThreadModelUserInfoKey"));
            if (obj != null) {
                CQLResultSet cQLResultSet = (CQLResultSet) obj;
                Object obj2 = map.get(AnonymousClass269.A00("MCAMailboxMessengerVrParticipantListUserInfoKey"));
                if (obj2 != null) {
                    CQLResultSet cQLResultSet2 = (CQLResultSet) map.get(AnonymousClass269.A00("MCAMailboxMessengerVrTempMessageListUserInfoKey"));
                    Number number = (Number) map.get(AnonymousClass269.A00("MCAMailboxMessengerVrTempMessageListMinTimestampMsUserInfoKey"));
                    Number number2 = (Number) map.get(AnonymousClass269.A00("MCAMailboxMessengerVrTempMessageListMaxTimestampMsUserInfoKey"));
                    Number number3 = (Number) map.get(AnonymousClass269.A00("MCAMailboxMessengerVrTempMessageListLoadMoreStateOlderInfoKey"));
                    Number number4 = (Number) map.get(AnonymousClass269.A00("MCAMailboxMessengerVrTempMessageListLoadMoreStateNewerInfoKey"));
                    AnonymousClass1Zb r0 = this.A00.A05;
                    AnonymousClass299 r4 = new AnonymousClass299(cQLResultSet);
                    AnonymousClass294 r5 = new AnonymousClass294((CQLResultSet) obj2);
                    if (cQLResultSet2 == null) {
                        tempMessageList = null;
                    } else {
                        tempMessageList = new TempMessageList(cQLResultSet2);
                    }
                    r0.A03(new AnonymousClass26Z(r4, r5, tempMessageList, number, number2, number3, number4));
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }
}
