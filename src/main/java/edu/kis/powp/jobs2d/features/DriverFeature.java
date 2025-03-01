package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.SelectDriverMenuOptionListener;
import edu.kis.powp.jobs2d.drivers.ApplicationInfoUpdateDriverChangeObserver;

public class DriverFeature implements FeatureInterface {

    private static final DriverManager driverManager = new DriverManager();
    private static Application app = new Application("");

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public DriverFeature(Application application) {
        app = application;
    }

    /**
     * Setup jobs2d drivers Plugin and add to application.
     */
    @Override
    public void setup() {
        app.addComponentMenu(DriverFeature.class, "Drivers");
    }

    /**
     * Add driver to context, create button in driver menu.
     *
     * @param name   Button name.
     * @param driver Job2dDriver object.
     */

    public void addDriver(String name, Job2dDriver driver) {
        SelectDriverMenuOptionListener listener = new SelectDriverMenuOptionListener(driver, driverManager);
        app.addComponentMenuElement(DriverFeature.class, name, listener);
    }

    public static void setUpDriverNameLabelChangeManager() {
        ApplicationInfoUpdateDriverChangeObserver applicationInfoUpdateDriverChangeObserver = new ApplicationInfoUpdateDriverChangeObserver(driverManager, app);
        driverManager.getChangePublisher().addSubscriber(applicationInfoUpdateDriverChangeObserver);
    }


}
