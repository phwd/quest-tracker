package com.oculus.horizon.api.graphql;

public class GraphQLSettingsNuxPreferencesQuery {
    public static final String SET_NUX_PREFERENCE_MUTATION = "Mutation ChangeClientPreferencesMutation : ClientPreferenceDataChangeResponsePayload {  client_preference_data_change(<input>) {    user {      client_preference_data {        type,        value      }    }  }}";
}
