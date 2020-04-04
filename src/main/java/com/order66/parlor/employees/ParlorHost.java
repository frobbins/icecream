package com.order66.parlor.employees;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.order66.parlor.Dessert;
import com.order66.parlor.Teenager;

public class ParlorHost extends Teenager implements RequestHandler<String, Dessert> {

    @Override
    public Dessert handleRequest(String order, Context context) {
        return new Dessert(order);
    }

}
