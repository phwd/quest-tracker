package com.oculus.panellib.quickpromotion;

import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class QuickPromotionGraphQL {
    public static final String ARGUMENT_MUTATION_INPUT = "input";
    public static final String ARGUMENT_SURFACE_ID = "surface_id";
    public static final String EVENT_ACTION = "ACTION";
    public static final String EVENT_EXPOSURE = "EXPOSURE";
    public static final String EVENT_VIEW = "VIEW";
    public static final String LOG_EVENT_MUTATION = "Mutation QuickPromotionLogEventData : QuickPromotionLogEventResponsePayload {quick_promotion_log_event(<input>) {client_mutation_id,}}";
    private static final String PARAM_ACTION = "action";
    private static final String PARAM_CLIENT_MUTATION_ID = "client_mutation_id";
    private static final String PARAM_EVENT = "event";
    private static final String PARAM_PROMOTION_ID = "promotion_id";
    private static final String PARAM_PROMOTION_LOGGING_DATA = "promotion_logging_data";
    private static final String PARAM_SURFACE_NUX_ID = "surface_nux_id";
    private static final String PARAM_TRIGGER_NAME = "trigger_name";
    private static final String PROMOTION_ACTION_FIELDS = "dismiss_promotion,limit,title_text,url,";
    private static final String PROMOTION_CREATIVES_FIELDS = "auto_size_image {dimensionless_cache_key,height,is_silhouette,mime_type,name,scale,uri,width,},content_text,dismiss_action {dismiss_promotion,limit,title_text,url,},primary_action {dismiss_promotion,limit,title_text,url,},secondary_action {dismiss_promotion,limit,title_text,url,},title_text,";
    private static final String PROMOTION_FILTER_CLAUSE_FIELDS = "clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},";
    private static final String PROMOTION_IMAGE_FIELDS = "dimensionless_cache_key,height,is_silhouette,mime_type,name,scale,uri,width,";
    private static final String PROMOTION_PARAMETER_FIELDS = "name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,";
    private static final String PROMOTION_TEMPLATE_FIELDS = "name,parameters {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,}";
    public static final String QUICK_PROMOTION_QUERY = "viewer() {eligible_promotions.surface_nux_id(<surface_id>).include_holdouts(true).supports_client_filters(true) {edges {client_ttl_seconds,is_holdout,node {bypass_surface_delay,contextual_filters {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,filters {extra_datas {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},filter_type,unknown_action,value {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,},},clauses {clause_type,},},},},},logging_data,max_impressions,promotion_creatives {auto_size_image {dimensionless_cache_key,height,is_silhouette,mime_type,name,scale,uri,width,},content_text,dismiss_action {dismiss_promotion,limit,title_text,url,},primary_action {dismiss_promotion,limit,title_text,url,},secondary_action {dismiss_promotion,limit,title_text,url,},title_text,},promotion_id,promotion_template {name,parameters {name,required,bool_value,color_value,float_value,int_value,string_value,uri_value,}},triggers,},time_range {start,end},},},}";

    public static JSONObject getParamsForExposure(String promotionID, String promotionLoggingData, int surfaceNuxID, String triggerName) {
        try {
            JSONObject params = new JSONObject();
            JSONObject input = new JSONObject();
            input.put(PARAM_CLIENT_MUTATION_ID, UUID.randomUUID().toString());
            input.put(PARAM_EVENT, EVENT_EXPOSURE);
            input.put("promotion_id", promotionID);
            input.put(PARAM_PROMOTION_LOGGING_DATA, promotionLoggingData);
            input.put(PARAM_SURFACE_NUX_ID, surfaceNuxID);
            input.put(PARAM_TRIGGER_NAME, triggerName);
            params.put(ARGUMENT_MUTATION_INPUT, input);
            return params;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject getParamsForView(String promotionID, String promotionLoggingData, int surfaceNuxID, String triggerName) {
        try {
            JSONObject params = new JSONObject();
            JSONObject input = new JSONObject();
            input.put(PARAM_CLIENT_MUTATION_ID, UUID.randomUUID().toString());
            input.put(PARAM_EVENT, EVENT_VIEW);
            input.put("promotion_id", promotionID);
            input.put(PARAM_PROMOTION_LOGGING_DATA, promotionLoggingData);
            input.put(PARAM_SURFACE_NUX_ID, surfaceNuxID);
            input.put(PARAM_TRIGGER_NAME, triggerName);
            params.put(ARGUMENT_MUTATION_INPUT, input);
            return params;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject getParamsForAction(String promotionID, String promotionLoggingData, int surfaceNuxID, String triggerName, String actionName) {
        try {
            JSONObject params = new JSONObject();
            JSONObject input = new JSONObject();
            input.put(PARAM_CLIENT_MUTATION_ID, UUID.randomUUID().toString());
            input.put(PARAM_EVENT, EVENT_ACTION);
            input.put("promotion_id", promotionID);
            input.put(PARAM_PROMOTION_LOGGING_DATA, promotionLoggingData);
            input.put(PARAM_SURFACE_NUX_ID, surfaceNuxID);
            input.put(PARAM_TRIGGER_NAME, triggerName);
            input.put(PARAM_ACTION, actionName);
            params.put(ARGUMENT_MUTATION_INPUT, input);
            return params;
        } catch (JSONException e) {
            return null;
        }
    }
}
