package X;

import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import com.facebook.rti.push.service.FbnsService;
import java.util.ArrayList;

/* renamed from: X.0v2  reason: invalid class name */
public class AnonymousClass0v2 extends ArrayList<SubscribeTopic> {
    public AnonymousClass0v2() {
        add(new SubscribeTopic("/fbns_reg_resp", 1));
        addAll(FbnsService.A0A);
    }
}
