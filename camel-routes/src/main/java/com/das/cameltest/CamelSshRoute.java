package com.das.cameltest;

import org.apache.camel.builder.RouteBuilder;

public class CamelSshRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:startSSH")
                .log("logging ssh")
                //.to("mock:aaa");
                .to("ssh:testUserName:testPassword@localhost:10000");
    }
}
