package com.oculus.deviceconfigclient.shared;

import com.facebook.annotations.DoNotRename;
import com.facebook.infer.annotation.Nullsafe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@DoNotRename
@Nullsafe(Nullsafe.Mode.LOCAL)
public class StorageInternalRepresentation implements Serializable {
    public static final long serialVersionUID = 1;
    public final List<StorageParam> Params = new ArrayList();
    @Nullable
    public String ParamsMapVersion;
}
