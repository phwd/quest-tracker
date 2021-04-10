package X;

import android.content.Context;
import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.messenger.assistant.thrift.DeviceCapabilities;
import com.facebook.messenger.assistant.thrift.SupportedAssistantAction;
import com.facebook.messenger.assistant.thrift.SupportedDeviceControlCommand;
import com.facebook.messenger.assistant.thrift.SupportedPreAssistantActionCommands;
import com.facebook.messenger.assistant.thrift.SupportedResponseType;

/* renamed from: X.8Q  reason: invalid class name */
public final class AnonymousClass8Q {
    public static DeviceCapabilities A00(AnonymousClass8P r7, Context context) {
        C0881nS nSVar;
        int i;
        C0884nc ncVar;
        int i2;
        C0872lx lxVar = new C0872lx();
        lxVar.A02(0, r7.A0G);
        lxVar.A02(1, null);
        lxVar.A02(20, 0);
        lxVar.A02(2, r7.A08);
        lxVar.A02(3, null);
        lxVar.A02(12, null);
        Object[] A03 = r7.A06.A03();
        SupportedAssistantAction supportedAssistantAction = new SupportedAssistantAction();
        supportedAssistantAction.A02("com.facebook.messenger.assistant.thrift.SupportedAssistantAction", A03);
        lxVar.A02(13, supportedAssistantAction);
        Object[] A032 = r7.A07.A03();
        SupportedResponseType supportedResponseType = new SupportedResponseType();
        supportedResponseType.A02("com.facebook.messenger.assistant.thrift.SupportedResponseType", A032);
        lxVar.A02(14, supportedResponseType);
        lxVar.A02(4, true);
        lxVar.A02(5, false);
        lxVar.A02(6, Boolean.valueOf(r7.A0K));
        lxVar.A02(26, false);
        lxVar.A02(25, false);
        lxVar.A02(27, r7.A09);
        lxVar.A02(11, 5);
        lxVar.A02(29, null);
        FQ fq = r7.A05;
        if (!(fq instanceof C1382yJ)) {
            nSVar = new C0881nS();
            nSVar.A02(0, true);
            nSVar.A02(1, true);
            nSVar.A02(9, true);
            nSVar.A02(20, true);
            nSVar.A02(8, true);
            i = 27;
        } else {
            nSVar = new C0881nS();
            nSVar.A02(0, true);
            nSVar.A02(1, true);
            nSVar.A02(9, true);
            nSVar.A02(27, true);
            i = 31;
        }
        nSVar.A02(i, true);
        Object[] A033 = nSVar.A03();
        SupportedAssistantAction supportedAssistantAction2 = new SupportedAssistantAction();
        supportedAssistantAction2.A02("com.facebook.messenger.assistant.thrift.SupportedAssistantAction", A033);
        lxVar.A02(13, supportedAssistantAction2);
        boolean z = fq instanceof C1382yJ;
        if (!z) {
            ncVar = new C0884nc();
            ncVar.A02(6, true);
            ncVar.A02(24, true);
            i2 = 36;
        } else {
            ncVar = new C0884nc();
            ncVar.A02(6, true);
            ncVar.A02(36, true);
            i2 = 40;
        }
        ncVar.A02(i2, true);
        Object[] A034 = ncVar.A03();
        SupportedResponseType supportedResponseType2 = new SupportedResponseType();
        supportedResponseType2.A02("com.facebook.messenger.assistant.thrift.SupportedResponseType", A034);
        lxVar.A02(14, supportedResponseType2);
        SupportedDeviceControlCommand supportedDeviceControlCommand = null;
        lxVar.A02(17, null);
        if (z) {
            Object[] A035 = new C0882nW().A03();
            supportedDeviceControlCommand = new SupportedDeviceControlCommand();
            supportedDeviceControlCommand.A02("com.facebook.messenger.assistant.thrift.SupportedDeviceControlCommand", A035);
        }
        lxVar.A02(16, supportedDeviceControlCommand);
        SupportedPreAssistantActionCommands supportedPreAssistantActionCommands = null;
        lxVar.A02(15, null);
        lxVar.A02(18, null);
        lxVar.A02(19, null);
        if (z) {
            C0883na naVar = new C0883na();
            naVar.A02(7, true);
            Object[] A036 = naVar.A03();
            supportedPreAssistantActionCommands = new SupportedPreAssistantActionCommands();
            supportedPreAssistantActionCommands.A02("com.facebook.messenger.assistant.thrift.SupportedPreAssistantActionCommands", A036);
        }
        lxVar.A02(21, supportedPreAssistantActionCommands);
        BJ bj = r7.A03;
        if (bj != null) {
            lxVar.A02(28, Short.valueOf(bj.value()));
        }
        Object obj = lxVar.A01[28];
        if (obj == HyperThriftBase.A04 || obj == null) {
            C0139Dd.A0D("AssistantClientPlatformUtils", "CuClientType is not set. This may lead to unexpected behavior.");
        }
        Object[] A037 = lxVar.A03();
        HyperThriftBase.Builder.A01(A037, 29);
        HyperThriftBase.Builder.A01(A037, 30);
        DeviceCapabilities deviceCapabilities = new DeviceCapabilities();
        deviceCapabilities.A02("com.facebook.messenger.assistant.thrift.DeviceCapabilities", A037);
        return deviceCapabilities;
    }
}
