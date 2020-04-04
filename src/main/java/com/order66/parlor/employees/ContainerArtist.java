package com.order66.parlor.employees;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.order66.parlor.Dessert;
import com.order66.parlor.Teenager;

import java.util.List;

public class ContainerArtist extends Teenager implements RequestHandler<Dessert, Dessert> {


    @Override
    public Dessert handleRequest(Dessert dessert, Context context) {
        try {
            setContext(context);
            List<String> items = readFromS3();
            items = randomizeListAndReturnSubset(context, items);
            dessert.setContainers(items);
            getLogger().log("Here are your items: \n" + items );
        } catch ( Exception ex ) {
            ex.printStackTrace();
            getLogger().log("Error: " + ex);
        }
        return dessert;
    }
}
