package com.oculus.deviceconfigclient;

public class SnapshotQuery {
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
        SnapshotQuery query = new SnapshotQuery();
        query.Header = true;
        query.Footer = true;
        query.ParamsMapVersion = true;
        query.SummaryPerType = true;
        query.ValueForSerialization = true;
        query.ValueSetFlag = true;
        query.ExposureLogged = true;
        query.Sessionless = true;
        query.LoggingId = true;
        return query;
    }
}
