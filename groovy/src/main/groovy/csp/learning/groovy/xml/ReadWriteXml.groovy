package csp.learning.groovy.xml

import groovy.xml.StreamingMarkupBuilder

def resourcesDirRelativePath = '../../../../../resources'
def gpxFile = new File(resourcesDirRelativePath + '/fells_loop_full.gpx')
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

def outFile = new File(resourcesDirRelativePath + '/fells_loop_truncated.xml')
outFile.write(xml.toString())
