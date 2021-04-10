package com.oculus.vrshell.panels.telemetry;

public interface SectionTracker {
    String[] addSectionData(String str, String[] strArr);

    void onAppBackground();

    void onAppForeground();

    void subSectionEnter(String str, String str2);

    void subSectionExit(String str, String str2);
}
