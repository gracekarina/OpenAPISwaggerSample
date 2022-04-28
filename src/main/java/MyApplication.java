import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/sample")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        //super.packages("resource", "io.swagger.v3.jaxrs2.integration.resources");
        property(ServerProperties.WADL_FEATURE_DISABLE, true);
    }


}
