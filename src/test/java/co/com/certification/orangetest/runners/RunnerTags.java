
package co.com.certification.orangetest.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "co.com.certification.orangetest")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@authentication or @pim")
@ConfigurationParameter(key = Constants.SNIPPET_TYPE_PROPERTY_NAME, value = "camelcase")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporter,pretty")
public class RunnerTags {
}

