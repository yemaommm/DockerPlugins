package toolWindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.treeStructure.Tree;
import org.jetbrains.annotations.NotNull;
import panel.DockerPanel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class LogTool implements ToolWindowFactory {

    private static DefaultMutableTreeNode containers = new DefaultMutableTreeNode("容器");
    private static DefaultMutableTreeNode images = new DefaultMutableTreeNode("镜像");
    private static DockerPanel leftPanel = new DockerPanel(containers, images);

    private JSplitPane mainPanel;
    private JBPanel rightPanel = new JBPanel();

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        DockerUtils.setProject(project);
        ContentFactory factory = ContentFactory.SERVICE.getInstance();
        Content content = factory.createContent(mainPanel, "Panel2", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(ToolWindow window) {
        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        mainPanel.setDividerLocation(0.5);
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        System.out.println("isDoNotActivateOnStart");
        return false;
    }

    public static DefaultMutableTreeNode getContainers() {
        return containers;
    }

    public static DefaultMutableTreeNode getImages() {
        return images;
    }

    public static DockerPanel getLeftPanel() {
        return leftPanel;
    }
}
