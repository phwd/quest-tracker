package com.oculus.deviceconfigclient.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StorageInternalRepresentation implements Serializable {
    public final List<StorageParam> Params = new ArrayList();
    public String ParamsMapVersion;
}
