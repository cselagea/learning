package csp.learning.groovy.xml

import groovy.xml.StreamingMarkupBuilder

def gpxFile = new File(getClass().getClassLoader().getResource('fells_loop_full.gpx').toURI())
def slurper = new XmlSlurper()
def gpsData = slurper.parse(gpxFile)

def xml = new StreamingMarkupBuilder().bind {
    route {
        mkp.comment(gpsData.name)
        gpsData.rte.rtept.each { point ->
            routepoint(timestamp: point.time.toString()) {
                latitude(point.@lat)
                longitude(point.@lon)
            }
        }
    }
}

def outFile = new File(gpxFile.parentFile, 'fells_loop_truncated.xml')
outFile.write(xml.toString())
