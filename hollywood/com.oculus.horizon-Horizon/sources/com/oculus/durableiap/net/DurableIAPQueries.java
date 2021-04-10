package com.oculus.durableiap.net;

public class DurableIAPQueries {
    public static final String IAP_ENTITLEMENTS = "query {  entitlements: me {    active_android_entitlements {      nodes {        ... on Entitlement {          item {            ... on Application {              grouping {                id,              }              iap_entitlements {                ... on IAPEntitlement {                  id,                      grant_time,                      expiration_time,                      active_state,                      item {                    ... on IAPItem {                      sku,                    }                  }                }              }            }          }        }      }    }  }}";
}
