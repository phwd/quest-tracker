package com.oculus.deviceconfigclient.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StorageInternalRepresentation implements Serializable {
    public static final long serialVersionUID = 1;
    public final List Params = new ArrayList();
    public String ParamsMapVersion;
}
