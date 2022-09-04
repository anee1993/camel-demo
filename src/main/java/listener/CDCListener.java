package listener;

import org.apache.camel.CamelContext;
import org.apache.camel.main.BaseMainSupport;
import org.apache.camel.main.MainListener;

public class CDCListener implements MainListener {
    @Override
    public void beforeInitialize(BaseMainSupport main) {
        // noop
    }

    @Override
    public void beforeConfigure(BaseMainSupport main) {
        // noop
    }

    @Override
    public void afterConfigure(BaseMainSupport main) {
        // noop
    }

    @Override
    public void configure(CamelContext context) {
        // noop
    }

    @Override
    public void beforeStart(BaseMainSupport main) {
        // noop
    }

    @Override
    public void afterStart(BaseMainSupport main) {
        // noop
    }

    @Override
    public void beforeStop(BaseMainSupport main) {
        // noop
    }

    @Override
    public void afterStop(BaseMainSupport main) {
        // noop
    }
}
