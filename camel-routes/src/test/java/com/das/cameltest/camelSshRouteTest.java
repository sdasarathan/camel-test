package com.das.cameltest;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.command.Command;
import org.apache.sshd.server.command.CommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.PublicKey;

import static org.junit.Assert.*;

public class camelSshRouteTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new CamelSshRoute();
    }

    @Test
    public void test() throws IOException {
        SshServer sshServer = SshServer.setUpDefaultServer();
        sshServer.setPort(10000);
        sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Paths.get("target/generatedkey.pem")));
        sshServer.setPasswordAuthenticator((String username, String password, ServerSession serverSession) -> {
            if ("testUserName".equals(username) && "testPassword".equals(password)) {
                return true;
            }
            return false;
        });
        sshServer.setPublickeyAuthenticator((String username, PublicKey publicKey, ServerSession serverSession) -> {
            if ("testUserName".equals(username) && publicKey instanceof PublicKey) {
                return true;
            }
            return false;
        });

        // Simple CommandFactory to run commands in a process
        sshServer.setCommandFactory(new CommandFactory() {
            public Command createCommand(String command) {
                return new ProcessShellFactory(command.split(";")).create();
            }
        });

        sshServer.start();

        //ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:startSSH", "test");
    }

}