package org.grails

import grails.persistence.Entity
import grails.testing.gorm.DataTest
import spock.lang.Specification

class UniqueConstraintSpec extends Specification implements DataTest {

    @Override
    Class[] getDomainClassesToMock() {
        [Child1, Child2]
    }

    void "instances of concrete classes can have the same name"() {
        given:
        def child1 = new Child1(name: 'test')
        child1.save(failOnError: true, flush: true)

        when:
        def child2 = new Child2(name: 'test')

        then:
        child2.save(failOnError: true, flush: true)
    }

}

@Entity
abstract class Parent {
    String name
}

@Entity
class Child1 extends Parent {
    static constraints = {
        name unique: true
    }
}

@Entity
class Child2 extends Parent  {
    static constraints = {
        name unique: true
    }
}
