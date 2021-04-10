package com.oculus.panelapp.messenger.util;

import com.oculus.messengervr.interfaces.BlockedByViewerStatus;
import com.oculus.messengervr.interfaces.MessengerParticipant;
import java.util.ArrayList;
import java.util.List;

public class ThreadHelper {
    public static MessengerParticipant getOtherThreadParticipant(String str, List<MessengerParticipant> list) {
        MessengerParticipant messengerParticipant;
        if (Long.toString(list.get(0).getParticipantId()).equals(str)) {
            messengerParticipant = list.get(1);
        } else {
            messengerParticipant = list.get(0);
        }
        return messengerParticipant;
    }

    public static List<MessengerParticipant> getBlockedParticipants(List<MessengerParticipant> list) {
        ArrayList arrayList = new ArrayList();
        for (MessengerParticipant messengerParticipant : list) {
            if (messengerParticipant.getBlockedByViewerStatus() == BlockedByViewerStatus.FULLY_BLOCKED || messengerParticipant.getBlockedByViewerStatus() == BlockedByViewerStatus.MESSAGE_BLOCKED) {
                arrayList.add(messengerParticipant);
            }
        }
        return arrayList;
    }

    public static boolean isGroupContainingBlockedThread(List<MessengerParticipant> list, List<MessengerParticipant> list2) {
        if (list.size() <= 2 || list2.size() <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isOneOnOneBlockedThread(List<MessengerParticipant> list, List<MessengerParticipant> list2) {
        if (!isOneOnOneThread(list) || list2.size() != 1) {
            return false;
        }
        return true;
    }

    public static boolean isOneOnOneThread(List<MessengerParticipant> list) {
        if (list.size() == 2) {
            return true;
        }
        return false;
    }
}
