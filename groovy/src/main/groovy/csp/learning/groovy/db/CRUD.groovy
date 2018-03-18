package csp.learning.groovy.db

@GrabConfig(systemClassLoader = true)
@Grab('com.h2database:h2:1.4.196')

import csp.learning.groovy.rest.RESTClient
import groovy.sql.Sql

def sql = Sql.newInstance('jdbc:h2:mem:test', 'org.h2.Driver')

sql.execute '''
    CREATE TABLE comments (
        id      INT PRIMARY KEY,
        body    VARCHAR(20),
        postId  VARCHAR(20)
);
'''

def response = new RESTClient().get()
response.data.comments.each { comment ->
    def comments = sql.dataSet('comments')
    comments.add(id: comment.id, body: comment.body, postId: comment.postId)
}

sql.executeUpdate('UPDATE comments SET body = ? WHERE id = 1', '123')

sql.eachRow('SELECT * FROM comments') {
    println it
}

println sql.firstRow('SELECT * FROM comments WHERE postId = 1')

sql.close()