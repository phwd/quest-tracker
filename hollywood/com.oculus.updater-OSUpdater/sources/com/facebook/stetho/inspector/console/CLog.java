package com.facebook.stetho.inspector.console;

import com.facebook.stetho.common.LogRedirector;
import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console;

public class CLog {
    public static void writeToConsole(ChromePeerManager chromePeerManager, Console.MessageLevel messageLevel, Console.MessageSource messageSource, String str) {
        LogRedirector.d("CLog", str);
        Console.ConsoleMessage consoleMessage = new Console.ConsoleMessage();
        consoleMessage.source = messageSource;
        consoleMessage.level = messageLevel;
        consoleMessage.text = str;
        Console.MessageAddedRequest messageAddedRequest = new Console.MessageAddedRequest();
        messageAddedRequest.message = consoleMessage;
        chromePeerManager.sendNotificationToPeers("Console.messageAdded", messageAddedRequest);
    }
}
