package com.clouway.testing;

import com.clouway.EndpointPackage.Endpoint;
import com.clouway.EndpointPackage.EndpointFilter;
import com.clouway.EndpointPackage.StartsWithKeyword;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class EndpointFilterTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private Endpoint endpointMock = context.mock(Endpoint.class);
    private final String gitUrl = "https://github.com/clouway/courses/blob/master/docs/java/Testing-with-mocks.md";

    /**
     * Tests if matches returns true EndpointFilter.shouldFilter returns also true
     */
    @Test
    public void testingMatchesDefault() {
        context.checking(new Expectations(){{
            oneOf(endpointMock).matches("end");
            will(returnValue(true));
        }});
        assertTrue(new EndpointFilter(endpointMock).shouldFilter("end"));
    }

    /**
     * Tests if matches returns false EndpointFilter.shouldFilter returns also false
     */
    @Test
    public void testingMatchesFail() {
        context.checking(new Expectations(){{
            oneOf(endpointMock).matches("fail");
            will(returnValue(false));
        }});
        assertFalse(new EndpointFilter(endpointMock).shouldFilter("fail"));
    }

    /**
     * Tests if first endpoint in gitUrl starts with "clo" returns true
     */
    @Test
    public void startsWithKeywordTest() {
        assertTrue(new EndpointFilter(new StartsWithKeyword("clo")).shouldFilter(gitUrl));
    }

    /**
     * Tests if StartsWithKeyword returns false if endpoint is contained in but doesn't start with
     */
    @Test
    public void startsWithNotContains() {
        assertFalse(new EndpointFilter(new StartsWithKeyword("louway")).shouldFilter(gitUrl));
    }


}
