package com.oculus.assistant.service.api.recording;

public interface RecordingStatusListener {
    void onRecordingStarted(String str);

    void onRecordingStopped(String str);
}
