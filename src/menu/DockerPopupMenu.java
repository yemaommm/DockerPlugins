package menu;

import com.intellij.openapi.ui.JBMenuItem;
import com.intellij.openapi.ui.JBPopupMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import toolWindow.DockerUtils;

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
        JBMenuItem flash = new JBMenuItem("刷新");
        flash.addActionListener(this);

        menuItemList.add(flash);

        menuItemList1.add(flash);
        menuItemList2.add(flash);

        this.add(flash);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // 刷新
            if (e.getSource().equals(menuItemList.get(0))){
                DockerUtils.refreshContainer();
                DockerUtils.refreshImage();
            }
        } catch (IOException e1) {
            LOGGER.error("refresh: ", e);
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
}
