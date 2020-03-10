package com.das.cameltest;

import org.apache.camel.builder.RouteBuilder;

public class CamelDockerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("docker://info?host=localhost&port=2375").to("log:info");
    }
}
