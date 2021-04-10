package com.oculus.logging.utils;

public interface FunnelData {
    FunnelData addExtra(String str, int i);

    FunnelData addExtra(String str, long j);

    FunnelData addExtra(String str, String str2);
}
