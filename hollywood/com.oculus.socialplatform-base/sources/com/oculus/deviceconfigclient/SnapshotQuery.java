package com.oculus.deviceconfigclient;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SnapshotQuery {
    @Nullable
    public String DebugText = null;
    public boolean ExposureLogged = false;
    public boolean Footer = false;
    public boolean Header = false;
    public boolean LoggingId = false;
    public boolean ParamName = true;
    public boolean ParamsMapVersion = false;
    public boolean Sessionless = false;
    public boolean SummaryPerType = false;
    public boolean Type = true;
    public boolean ValueForSerialization = false;
    public boolean ValueReturned = false;
    public boolean ValueSetFlag = false;
    public boolean ValueToReturn = true;

    public static SnapshotQuery createWithAllEnabled() {
        SnapshotQuery snapshotQuery = new SnapshotQuery();
        snapshotQuery.Header = true;
        snapshotQuery.Footer = true;
        snapshotQuery.ParamsMapVersion = true;
        snapshotQuery.SummaryPerType = true;
        snapshotQuery.ValueForSerialization = true;
        snapshotQuery.ValueSetFlag = true;
        snapshotQuery.ExposureLogged = true;
        snapshotQuery.Sessionless = true;
        snapshotQuery.LoggingId = true;
        return snapshotQuery;
    }
}
