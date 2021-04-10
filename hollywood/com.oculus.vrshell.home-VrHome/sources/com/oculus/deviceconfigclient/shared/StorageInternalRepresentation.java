package com.oculus.deviceconfigclient.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class StorageInternalRepresentation implements Serializable {
    private static final long serialVersionUID = 1;
    public final List<StorageParam> Params = new ArrayList();
    @Nullable
    public String ParamsMapVersion;
}
