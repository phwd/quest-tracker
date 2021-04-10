package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

/* renamed from: HG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HG1 implements AbstractC4040oH1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f8148a;
    public final /* synthetic */ int b;

    public HG1(String str, int i) {
        this.f8148a = str;
        this.b = i;
    }

    @Override // defpackage.AbstractC4040oH1
    public final Object a(IBinder iBinder) {
        AbstractC2328eG1 c = AbstractBinderC4379qG1.c(iBinder);
        AccountChangeEventsRequest accountChangeEventsRequest = new AccountChangeEventsRequest();
        accountChangeEventsRequest.H = this.f8148a;
        accountChangeEventsRequest.G = this.b;
        FG1 fg1 = (FG1) c;
        Parcel c2 = fg1.c();
        AbstractC1984cF1.b(c2, accountChangeEventsRequest);
        Parcel d = fg1.d(3, c2);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) AbstractC1984cF1.a(d, AccountChangeEventsResponse.CREATOR);
        d.recycle();
        EF1.d(accountChangeEventsResponse);
        return accountChangeEventsResponse.G;
    }
}
