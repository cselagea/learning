package csp.learning.groovy.rest

@Grab(
        group = 'org.codehaus.groovy.modules.http-builder',
        module = 'http-builder',
        version = '0.7.1'
)

import groovyx.net.http.RESTClient

class RESTApi {

    private restClient

    RESTApi() {
        restClient = new RESTClient('https://my-json-server.typicode.com/')
    }

    def get() {
        restClient.get(path: 'typicode/demo/db')
    }

}
