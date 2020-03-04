package com.das.cameltest;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class camelSshRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new CamelSshRoute();
    }

    @Test
    public void test(){
        //ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:startSSH", "test");
    }

}