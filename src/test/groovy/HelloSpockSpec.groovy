import spock.lang.*

class HelloSpockSpec extends Specification {
    def "some string behavior"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 4
        "Scotty" | 6
    }

    def "a basic feature method"() {
        setup:
        def str = 'abcdef'

        when:
        str.reverse()

        then:
        str.size() == 6
        str[2] == 'c'
    }

    @Unroll // to intercept specific iteration of failure
    def "a feature with expectation"() {
        when:
        b = a * 10

        then:
        b > 0

        where:
        a << (1 .. 10)
        b = 0
    }

    def "verify an exception"() {
        when:
        throw new RuntimeException()

        then:
        Exception e = thrown()
        e instanceof RuntimeException
    }

    def "verify no exception"() {
        setup:
        def m = [:]

        when:
        m.put(null, 'abc')

        then:
        notThrown(Exception)
    }

    def "a story driven flow"() {
        given:
        def v = 123

        when:
        v++

        then:
        v == 124
    }

    @Timeout(4)  // time out value in seconds
    def "a time sensitive test"() {
        when:
        [1, 2, 3].each {
            Thread.sleep 1000
        }
        then:
        true
    }


}
