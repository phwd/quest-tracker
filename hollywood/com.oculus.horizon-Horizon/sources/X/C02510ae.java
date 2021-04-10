package X;

import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import com.facebook.rti.push.service.FbnsService;
import java.util.ArrayList;

/* renamed from: X.0ae  reason: invalid class name and case insensitive filesystem */
public class C02510ae extends ArrayList<SubscribeTopic> {
    public C02510ae() {
        add(new SubscribeTopic("/fbns_reg_resp", 1));
        addAll(FbnsService.A0A);
    }
}
