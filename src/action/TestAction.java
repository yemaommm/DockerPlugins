package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import component.TestComponent;
import dto.docker.ContainerDto;
import org.jetbrains.annotations.NotNull;
import plugins.DockerPlugins;
import toolWindow.DockerUtils;
import toolWindow.LogTool;
import utils.ProjectFileUtils;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Application application = ApplicationManager.getApplication();
        TestComponent testComponent = application.getComponent(TestComponent.class);
        testComponent.sayHello();

        try {
            DockerUtils.buildImage(e.getProject());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
