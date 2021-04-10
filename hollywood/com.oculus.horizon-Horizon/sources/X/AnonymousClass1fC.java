package X;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.quicklog.AppStates;
import com.facebook.quicklog.BackgroundExecution;
import com.facebook.quicklog.DebugAndTestConfig;
import com.facebook.quicklog.QPLConfiguration;
import com.oculus.perflogs.impl.fbquicklog.PerfLogsHelper;
import javax.inject.Provider;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1fC  reason: invalid class name */
public final class AnonymousClass1fC {
    public AnonymousClass0TF A00 = new AnonymousClass1MY();
    public final AnonymousClass0LA A01;
    public final AnonymousClass0LF A02;
    public final AnonymousClass1uD A03;
    public final AnonymousClass1iH A04;
    public final AnonymousClass1hU A05;
    public final AnonymousClass0VH A06;
    public final PerfLogsHelper.AnonymousClass2 A07;
    public final Provider<AnonymousClass1n9> A08;

    public AnonymousClass1fC(Provider<AnonymousClass1n9> provider, QPLConfiguration qPLConfiguration, AnonymousClass0LF r4, AnonymousClass0LA r5, DebugAndTestConfig debugAndTestConfig, AppStates appStates, BackgroundExecution backgroundExecution, AnonymousClass0VH r9) {
        this.A08 = provider;
        this.A03 = qPLConfiguration;
        this.A02 = r4;
        this.A01 = r5;
        this.A07 = debugAndTestConfig;
        this.A05 = appStates;
        this.A04 = backgroundExecution;
        this.A06 = r9;
    }
}
