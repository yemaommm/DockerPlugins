package menu;

import com.intellij.openapi.ui.JBMenuItem;
import com.intellij.openapi.ui.JBPopupMenu;
import com.intellij.ui.treeStructure.Tree;
import dto.docker.ContainerNode;
import dto.docker.ImageNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import panel.DockerPanel;
import toolWindow.DockerUtils;
import toolWindow.LogTool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DockerPopupMenu extends JBPopupMenu implements ActionListener {
    private static Logger LOGGER = LoggerFactory.getLogger(DockerPopupMenu.class);

    private List<JBMenuItem> menuItemList = new ArrayList<>(16);

    private List<JBMenuItem> menuItemList1 = new ArrayList<>(16);

    private List<JBMenuItem> menuItemList2 = new ArrayList<>(16);

    private List<JBMenuItem> menuItemList3 = new ArrayList<>(16);

    public DockerPopupMenu(String label) {
        super(label);

        initPopupMenu();
    }

    private void initPopupMenu(){
        JBMenuItem flash = this.addMenuItem("刷新");
        menuItemList1.add(flash);
        menuItemList2.add(flash);
        JBMenuItem build = this.addMenuItem("build image");
        menuItemList1.add(build);
        menuItemList2.add(build);
        menuItemList3.add(build);
        JBMenuItem delete = this.addMenuItem("delete");
        menuItemList3.add(delete);

        menuItemList.forEach(DockerPopupMenu.this::add);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 刷新
            if (e.getSource().equals(menuItemList.get(0))){
                DockerUtils.refreshContainer();
                DockerUtils.refreshImage();
            }
            //build image
            else if (e.getSource().equals(menuItemList.get(1))){
                DockerUtils.buildImage();
            }
            // delete
            else if (e.getSource().equals(menuItemList.get(2))){
                Object lastPathComponent = LogTool.getLeftPanel().getTree1().getSelectionPath().getLastPathComponent();
                if (lastPathComponent instanceof ImageNode){
                    DockerUtils.deleteImage(((ImageNode)lastPathComponent).getImage().getId());
                }else if (lastPathComponent instanceof ContainerNode){
                    DockerUtils.deleteContainer(((ContainerNode)lastPathComponent).getContainer().getId());
                }
            }
        } catch (Exception e1) {
            LOGGER.error("refresh: ", e1);
        }
    }

    private void set(List<JBMenuItem> setList){
        for (JBMenuItem ji : menuItemList) {
            ji.setEnabled(false);
        }
        for (JBMenuItem ji : setList) {
            ji.setEnabled(true);
        }
    }

    public void set1(){
        this.set(menuItemList1);
    }

    public void set2(){
        this.set(menuItemList2);
    }

    public void set3(){
        this.set(menuItemList3);
    }

    private JBMenuItem addMenuItem(String text){
        JBMenuItem item = new JBMenuItem(text);
        item.addActionListener(this);

        menuItemList.add(item);

        return item;
    }
}
