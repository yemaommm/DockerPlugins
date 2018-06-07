package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import component.TestComponent;
import plugins.DockerPlugins;

import java.io.IOException;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Application application = ApplicationManager.getApplication();
        TestComponent testComponent = application.getComponent(TestComponent.class);
        testComponent.sayHello();

        try {
            new DockerPlugins().getContainers();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
