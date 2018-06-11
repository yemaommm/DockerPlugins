package toolWindow;

import com.intellij.openapi.project.Project;
import dto.docker.ContainerDto;
import dto.docker.ContainerNode;
import dto.docker.ImageDto;
import dto.docker.ImageNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plugins.DockerPlugins;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.util.List;

public class DockerUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(DockerUtils.class);

    public final static DockerPlugins dockerPlugins = new DockerPlugins("http://39.105.48.129:2375");

    private static Project project;

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
            ContainerNode node = new ContainerNode(name.toString(), dto);
            root.add(node);
        }
        root.setAllowsChildren(true);
    }

    public static void setImage(DefaultMutableTreeNode root, List<ImageDto> imageDtos){
        root.removeAllChildren();
        for (ImageDto dto : imageDtos) {
            StringBuffer name = new StringBuffer("NULL");
            if (dto.getRepoTags() != null && dto.getRepoTags().size() > 0) {
                name = new StringBuffer(dto.getRepoTags().get(0));
            }
            name.append(" [");
            name.append(" Size: " + dto.getSize()/1024/1024 + "M");
            name.append(" ]");
            ImageNode node = new ImageNode(name.toString(), dto);
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
        LogTool.getLeftPanel().getDt().reload();
    }

    /**
     * 刷新显示镜像
     * @throws IOException
     */
    public static void refreshImage() throws IOException {
        List<ImageDto> images = dockerPlugins.getImages();
        DockerUtils.setImage(LogTool.getImages(), images);
        LogTool.getLeftPanel().getDt().reload();
    }

    public static String buildImage() throws Exception {
        String s = dockerPlugins.buildImage(project);
        System.out.println(s);
        return s;
    }

    public static String deleteImage(String name) throws IOException {
        String s = dockerPlugins.deleteImage(name);
        System.out.println(s);

        refreshImage();
        return s;
    }

    public static String deleteContainer(String name) throws IOException {
        String s = dockerPlugins.deleteContainer(name);
        System.out.println(s);

        refreshContainer();
        return s;
    }

    public static Project getProject() {
        return project;
    }

    public static void setProject(Project project) {
        DockerUtils.project = project;
    }
}
