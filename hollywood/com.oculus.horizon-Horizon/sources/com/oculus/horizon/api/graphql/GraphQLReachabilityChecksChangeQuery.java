package com.oculus.horizon.api.graphql;

public class GraphQLReachabilityChecksChangeQuery {
    public static final String SET_REACHABILITY_CHECK_MUTATION = "Mutation ChangeReachabilityMutation : User {reachability_data_change(<input>) {user {id,reachability_data {type,value}}}}";
}
