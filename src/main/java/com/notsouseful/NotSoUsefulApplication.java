package com.notsouseful;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NotSoUsefulApplication extends Application<NotSoUsefulConfiguration> {

    public static void main(final String[] args) throws Exception {
        new NotSoUsefulApplication().run(args);
    }

    @Override
    public String getName() {
        return "NotSoUseful";
    }

    @Override
    public void initialize(final Bootstrap<NotSoUsefulConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final NotSoUsefulConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}