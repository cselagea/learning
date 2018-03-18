package csp.learning.groovy

def configSlurper = new ConfigSlurper()
def config = configSlurper.parse(getClass().getClassLoader().getResource('config.properties'))
println "Database user: ${config.database.user}"
println "Database password: ${config.database.password}"
