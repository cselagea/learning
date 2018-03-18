package csp.learning.groovy.integration;

import groovy.lang.GroovyShell;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static java.util.Collections.singletonList;

public class JavaIntegration {

    public static void main(String[] args) {
        System.out.println(runGroovyScript("return_hello_name.groovy", singletonList("Cosmin")));
    }

    private static Object runGroovyScript(String script, List<String> args) {
        URL scriptUrl = JavaIntegration.class.getClassLoader().getResource(script);

        if (scriptUrl != null) {
            try {
                GroovyShell shell = new GroovyShell();
                return shell.run(scriptUrl.toURI(), args);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
