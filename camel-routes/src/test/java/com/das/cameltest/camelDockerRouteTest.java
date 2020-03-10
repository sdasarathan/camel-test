package com.das.cameltest;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.command.CommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.PublicKey;

public class camelDockerRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new CamelDockerRoute();
    }

    @Test
    public void test() throws IOException {

        //ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:startSSH", "test");
    }

}