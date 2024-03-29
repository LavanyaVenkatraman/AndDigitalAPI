package apiPhone.boundary;

import apiPhone.boundary.Swagger2UiConfiguration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Swagger Config Test
 */
public class Swagger2UiConfigTest {

    /**
     * Configuration check
     */
    @Test
    public void api(){
        Swagger2UiConfiguration swagger2UiConfiguration = new Swagger2UiConfiguration();
        assertThat("No value returned",swagger2UiConfiguration.api(),notNullValue());
    }

}
