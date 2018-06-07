package plugins;

import toolWindow.LogTool;
import utils.http;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;

public class DockerPlugins {

    private String containers;

    public DockerPlugins(){}

    public void getContainers() throws IOException {
        String url = "http://39.105.48.129:2375/containers/json?all=1";
        String s = http.get(url);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Docker");
        root.removeAllChildren();
        root.setAllowsChildren(true);
        LogTool.getContainers().add(root);
        System.out.println(s);


    }
}
