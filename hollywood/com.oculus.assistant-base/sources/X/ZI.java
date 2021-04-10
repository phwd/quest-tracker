package X;

import com.oculus.deviceconfigclient.shared.StorageInternalRepresentation;
import com.oculus.deviceconfigclient.shared.StorageParam;
import java.util.Map;

public final class ZI {
    public static void A00(StorageInternalRepresentation storageInternalRepresentation, String str, Map map) {
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            ZR zr = (ZR) entry.getValue();
            Object obj = zr.A01;
            if (obj == null) {
                C0139Dd.A0O("ConfigStorageAdapter", "No value set for param name %s", str2);
            } else {
                storageInternalRepresentation.Params.add(new StorageParam(str2, str, obj.toString(), zr.A03, zr.A05));
            }
        }
    }
}
