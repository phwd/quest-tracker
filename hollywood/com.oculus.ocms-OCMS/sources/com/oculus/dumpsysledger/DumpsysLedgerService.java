package com.oculus.dumpsysledger;

import com.oculus.os.DumpsysProxyClientService;

public class DumpsysLedgerService extends DumpsysProxyClientService {
    public String dumpState() {
        return DumpsysLedgerMapper.getSingletonMapper().getAllLedgerLogs();
    }

    public String getAppName() {
        DumpsysLedgerMapper singletonMapper = DumpsysLedgerMapper.getSingletonMapper();
        return singletonMapper.getAppName() + " (DumpsysLedger)";
    }
}
