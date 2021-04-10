package com.oculus.panellib.quickpromotion;

import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class QuickPromotionGraphQL {
    public static JSONObject getParamsForExposure(String promotionID, String promotionLoggingData, int surfaceNuxID, String triggerName) {
        try {
            JSONObject params = new JSONObject();
            JSONObject input = new JSONObject();
            input.put("client_mutation_id", UUID.randomUUID().toString());
            input.put("event", "EXPOSURE");
            input.put("promotion_id", promotionID);
            input.put("promotion_logging_data", promotionLoggingData);
            input.put("surface_nux_id", surfaceNuxID);
            input.put("trigger_name", triggerName);
            params.put("input", input);
            return params;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject getParamsForView(String promotionID, String promotionLoggingData, int surfaceNuxID, String triggerName) {
        try {
            JSONObject params = new JSONObject();
            JSONObject input = new JSONObject();
            input.put("client_mutation_id", UUID.randomUUID().toString());
            input.put("event", "VIEW");
            input.put("promotion_id", promotionID);
            input.put("promotion_logging_data", promotionLoggingData);
            input.put("surface_nux_id", surfaceNuxID);
            input.put("trigger_name", triggerName);
            params.put("input", input);
            return params;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject getParamsForAction(String promotionID, String promotionLoggingData, int surfaceNuxID, String triggerName, String actionName) {
        try {
            JSONObject params = new JSONObject();
            JSONObject input = new JSONObject();
            input.put("client_mutation_id", UUID.randomUUID().toString());
            input.put("event", "ACTION");
            input.put("promotion_id", promotionID);
            input.put("promotion_logging_data", promotionLoggingData);
            input.put("surface_nux_id", surfaceNuxID);
            input.put("trigger_name", triggerName);
            input.put("action", actionName);
            params.put("input", input);
            return params;
        } catch (JSONException e) {
            return null;
        }
    }
}
