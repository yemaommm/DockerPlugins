package toolWindow;

import com.intellij.openapi.project.Project;
import dto.docker.ContainerDto;
import dto.docker.ImageDto;
import plugins.DockerPlugins;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.util.List;

public class DockerUtils {

    public final static DockerPlugins dockerPlugins = new DockerPlugins("http://39.105.48.129:2375");

    public static void setContainer(DefaultMutableTreeNode root, List<ContainerDto> containerDtos){
        root.removeAllChildren();
        for (ContainerDto dto : containerDtos) {
            StringBuffer name = new StringBuffer("NULL");
            if (dto.getNames() != null && dto.getNames().size() > 0) {
                name = new StringBuffer(dto.getNames().get(0));
            }
            name.append(" [");
            name.append(" State: " + dto.getState());
            name.append(", ID: " + dto.getId().substring(0, 12));
            name.append(", images: " + dto.getImage());
            name.append(", Status: " + dto.getStatus());
            name.append(" ]");
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
            root.add(node);
        }
        root.setAllowsChildren(true);
    }

    public static void setImage(DefaultMutableTreeNode root, List<ImageDto> containerDtos){
        root.removeAllChildren();
        for (ImageDto dto : containerDtos) {
            StringBuffer name = new StringBuffer("NULL");
            if (dto.getRepoTags() != null && dto.getRepoTags().size() > 0) {
                name = new StringBuffer(dto.getRepoTags().get(0));
            }
            name.append(" [");
            name.append(" Size: " + dto.getSize()/1024/1024 + "M");
            name.append(" ]");
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(name);
            root.add(node);
        }
        root.setAllowsChildren(true);
    }

    /**
     * 刷新显示容器
     * @throws IOException
     */
    public static void refreshContainer() throws IOException {
        List<ContainerDto> containers = dockerPlugins.getContainers();
        DockerUtils.setContainer(LogTool.getContainers(), containers);
    }

    /**
     * 刷新显示镜像
     * @throws IOException
     */
    public static void refreshImage() throws IOException {
        List<ImageDto> images = dockerPlugins.getImages();
        DockerUtils.setImage(LogTool.getImages(), images);
    }

    public static String buildImage(Project project) throws Exception {
        String s = dockerPlugins.buildImage(project);
        return s;
    }
}
