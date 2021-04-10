package com.oculus.bugreportservice;

import java.io.File;
import java.util.List;

public class BugReport {
    public String appId;
    public String categoryId;
    public String description;
    public File descriptionRecording;
    public List extraMedia;
    public String id;
    public File logs;
    public File pastAudioData;
    public File screenshot;
    public String sessionId;
}
