package com.oculus.deviceconfigclient.shared;

import com.facebook.infer.annotation.Nullsafe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StorageInternalRepresentation implements Serializable {
    public final List<StorageParam> Params = new ArrayList();
    public String ParamsMapVersion;
}
