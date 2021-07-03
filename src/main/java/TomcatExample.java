import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class TomcatExample {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context context = tomcat.addContext("api", new File(".").getAbsolutePath());

        tomcat.addServlet("/api", "main", new HttpServlet() {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
                Writer w = response.getWriter();
                w.write("Product Star online! \n");
                w.flush();
                w.close();
            }
        });

        context.addServletMappingDecoded("/*", "main");

        tomcat.start();
        tomcat.getServer().await();
    }
}
