package org.appfuse.webapp.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.cactus.ServletTestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.appfuse.Constants;
import org.springframework.web.context.WebApplicationContext;


/**
 * This class tests the StartupListener class to
 * verify that variables are placed into the application context.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version $Revision: 1.5 $ $Date: 2004/08/19 05:11:45 $
 */
public class StartupListenerTest extends ServletTestCase {
    //~ Instance fields ========================================================

    private final Log log = LogFactory.getLog(StartupListenerTest.class);
    StartupListener listener = null;
    ServletContext context = null;

    //~ Methods ================================================================

    protected void setUp() throws Exception {
        super.setUp();
        listener = new StartupListener();
        context = config.getServletContext();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        listener = null;
    }

    public void testContextInitialized() {
        // setup
        ServletContextEvent sce = new ServletContextEvent(context);

        // test
        listener.contextInitialized(sce);

        assertTrue(context.getAttribute(Constants.CONFIG) != null);
        assertTrue(context.getAttribute(WebApplicationContext
                .ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null);
        assertTrue(context.getAttribute(Constants.AVAILABLE_ROLES) != null);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(StartupListenerTest.class);
    }
}
