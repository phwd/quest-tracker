package com.facebook.acra.sender;

import java.net.Proxy;

public interface FlexibleReportSender extends ReportSender {
    boolean setHost(String str);

    void setProxy(Proxy proxy);

    void setSkipSslCertsChecks(boolean z);
}
