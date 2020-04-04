package com.order66.parlor.employees;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.order66.parlor.Dessert;
import com.order66.parlor.Teenager;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;


public class ParlorServer extends Teenager implements RequestHandler<Dessert, Dessert> {

    @Override
    public Dessert handleRequest(Dessert dessert, Context context) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            setContext(context);
            List<String> names = readFromS3();
            if (CollectionUtils.isNotEmpty(names)) {
                Collections.shuffle(names);
                String name = names.get(0);
                writeToS3("WINNER!!!-"+ name + ".json", mapper.writeValueAsString(dessert));
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            getLogger().log("Error: " + ex);
        }
        return dessert;
    }

}
