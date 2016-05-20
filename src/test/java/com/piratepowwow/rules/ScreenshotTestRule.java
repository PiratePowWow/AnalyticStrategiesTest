package com.piratepowwow.rules;

import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.IOException;

/**
 * Created by Jame on 5/20/2016.
 */
public class ScreenshotTestRule implements MethodRule {

    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } catch (Throwable t) {
                    captureScreenshot(frameworkMethod.getName());
                    throw t; // rethrow to allow the failure to be reported to JUnit
                }
            }

            void captureScreenshot(String fileName) throws AWTException, IOException {
                try {
                    new File("C:/screenshots").mkdirs(); // Insure directory is there
                    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage capture = new Robot().createScreenCapture(screenRect);
                    ImageIO.write(capture, "bmp", new File("C:/screenshots/screenshot-failed-test-" + fileName + ".bmp"));
                } catch (Exception e) {
                    // No need to crash the tests if the screenshot fails
                }
            }
        };
    }

    public ScreenshotTestRule() {
    }
}
