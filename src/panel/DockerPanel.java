package panel;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import menu.DockerPopupMenu;

public class DockerPanel extends JBScrollPane {
    private static Logger LOGGER = LoggerFactory.getLogger(DockerPanel.class);

    private DefaultMutableTreeNode containers;
    private DefaultMutableTreeNode images;
    //构造一个treeModel 对象，进行刷新树操作
    private DefaultTreeModel dt;
    private DockerPopupMenu popupMenu = new DockerPopupMenu("工具栏");
    private Tree tree1;

    public DockerPanel(DefaultMutableTreeNode containers, DefaultMutableTreeNode images){
        this.containers = containers;
        this.images = images;

        this.initTree();

        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (e.getButton()){
                    case MouseEvent.BUTTON3:
                        if (tree1.getSelectionPath() != null){
                            switch (tree1.getSelectionPath().getPathCount()){
                                case 1:
                                    popupMenu.set1();
                                    break;
                                case 2:
                                    popupMenu.set2();
                                    break;
                                case 3:
                                    popupMenu.set3();
                                    break;
                                default:
                                    break;
                            }
                        }
                        popupMenu.show(DockerPanel.this, e.getX(), e.getY());
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initTree(){
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
        root.add(containers);
        root.add(images);

        dt = new DefaultTreeModel(root);
        tree1 = new Tree(dt);
        this.setVisible(true);

        this.setViewportView(tree1);
    }

    public DefaultMutableTreeNode getContainers() {
        return containers;
    }

    public void setContainers(DefaultMutableTreeNode containers) {
        this.containers = containers;
    }

    public DefaultMutableTreeNode getImages() {
        return images;
    }

    public void setImages(DefaultMutableTreeNode images) {
        this.images = images;
    }

    public DefaultTreeModel getDt() {
        return dt;
    }

    public void setDt(DefaultTreeModel dt) {
        this.dt = dt;
    }
}
