package com.oculus.common.quickpromotion;

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

    public static JSONObject getParamsForExposure(String str, String str2, int i, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(PARAM_CLIENT_MUTATION_ID, UUID.randomUUID().toString());
            jSONObject2.put("event", EVENT_EXPOSURE);
            jSONObject2.put("promotion_id", str);
            jSONObject2.put(PARAM_PROMOTION_LOGGING_DATA, str2);
            jSONObject2.put(PARAM_SURFACE_NUX_ID, i);
            jSONObject2.put(PARAM_TRIGGER_NAME, str3);
            jSONObject.put(ARGUMENT_MUTATION_INPUT, jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static JSONObject getParamsForView(String str, String str2, int i, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(PARAM_CLIENT_MUTATION_ID, UUID.randomUUID().toString());
            jSONObject2.put("event", EVENT_VIEW);
            jSONObject2.put("promotion_id", str);
            jSONObject2.put(PARAM_PROMOTION_LOGGING_DATA, str2);
            jSONObject2.put(PARAM_SURFACE_NUX_ID, i);
            jSONObject2.put(PARAM_TRIGGER_NAME, str3);
            jSONObject.put(ARGUMENT_MUTATION_INPUT, jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static JSONObject getParamsForAction(String str, String str2, int i, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(PARAM_CLIENT_MUTATION_ID, UUID.randomUUID().toString());
            jSONObject2.put("event", EVENT_ACTION);
            jSONObject2.put("promotion_id", str);
            jSONObject2.put(PARAM_PROMOTION_LOGGING_DATA, str2);
            jSONObject2.put(PARAM_SURFACE_NUX_ID, i);
            jSONObject2.put(PARAM_TRIGGER_NAME, str3);
            jSONObject2.put("action", str4);
            jSONObject.put(ARGUMENT_MUTATION_INPUT, jSONObject2);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }
}
