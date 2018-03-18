package csp.learning.groovy.rest

@Grab(
        group = 'org.codehaus.groovy.modules.http-builder',
        module = 'http-builder',
        version = '0.7.1'
)

class RESTClient {

    private restClient

    RESTClient() {
        restClient = new groovyx.net.http.RESTClient('https://my-json-server.typicode.com/')
    }

    def get() {
        restClient.get(path: 'typicode/demo/db')
    }

}
