package org.chromium.services.device;

import java.util.Objects;
import org.chromium.device.nfc.NfcDelegate;
import org.chromium.mojo.system.impl.CoreImpl;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class InterfaceRegistrar {
    public static void createInterfaceRegistryForContext(int i, NfcDelegate nfcDelegate) {
        CoreImpl coreImpl = (CoreImpl) VA.f9067a;
        Objects.requireNonNull(coreImpl);
        A30 f0 = A30.f0(new C1709ak0(new Rp1(coreImpl, i)));
        int i2 = AbstractC1420Xg.i;
        f0.F.put("device.mojom.BatteryMonitor", new C5870z30(AbstractC3074ih.f10155a, new C1542Zg()));
        int i3 = AbstractC0099Bo0.w;
        f0.F.put("device.mojom.NFCProvider", new C5870z30(AbstractC0648Ko0.f8389a, new C0160Co0(nfcDelegate)));
        int i4 = Ps1.E;
        f0.F.put("device.mojom.VibrationManager", new C5870z30(AbstractC2088ct1.f9727a, new Qs1()));
    }
}
