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

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

public class LogTool implements ToolWindowFactory {

    private JSplitPane mainPanel;
    private JBScrollPane leftPanel = new JBScrollPane();
    private JBPanel rightPanel = new JBPanel();

    private static DefaultMutableTreeNode containers;
    private static DefaultMutableTreeNode images;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory factory = ContentFactory.SERVICE.getInstance();
        Content content = factory.createContent(mainPanel, "test", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(ToolWindow window) {
        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        mainPanel.setDividerLocation(0.5);

        this.addTree();
    }

    public void addTree(){
        //定义字体
        Font fnt = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        //找到字体的资源管理
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
        //定义tree 的根目录
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Docker");
        //定义根节点下面的子节点
        containers = new DefaultMutableTreeNode("容器");
        images = new DefaultMutableTreeNode("镜像");
        root.add(containers);
        root.add(images);
        //构造一个treeModel 对象，进行刷新树操作
        DefaultTreeModel dt = new DefaultTreeModel(root);
        Tree tree1 = new Tree(dt);
        leftPanel.setVisible(true);

        leftPanel.setViewportView(tree1);
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
}
