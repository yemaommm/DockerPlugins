package plugins;

import com.google.gson.*;
import com.intellij.openapi.project.Project;
import dto.docker.ContainerDto;
import dto.docker.ImageDto;
import utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DockerPlugins {

    private String host;

    private List<ContainerDto> containers = new ArrayList<>(16);
    private List<ImageDto> images = new ArrayList<>(16);

    private Gson gson = new GsonBuilder().create();

    //Json的解析类对象
    private JsonParser parser = new JsonParser();

    public DockerPlugins(String host){
        this.host = host;
    }

    public List<ContainerDto> getContainers() throws IOException {
        String url = host + "/containers/json?all=1";
        String s = http.get(url);

        containers.clear();

        JsonArray jsonArray = parser.parse(s).getAsJsonArray();
        for (JsonElement value : jsonArray) {
            ContainerDto container = gson.fromJson(value, ContainerDto.class);
            containers.add(container);
        }

        return containers;
    }


    public List<ImageDto> getImages() throws IOException {
        String url = host + "/images/json?all=0";
        String s = http.get(url);

        images.clear();

        JsonArray jsonArray = parser.parse(s).getAsJsonArray();
        for (JsonElement value : jsonArray) {
            ImageDto container = gson.fromJson(value, ImageDto.class);
            images.add(container);
        }

        return images;
    }

    public String buildImage(Project project) throws Exception {
        String url = host + "/build?dockerfile=" + project.getBaseDir().getName() + "/Dockerfile";
        String contentType = "application/tar";

        String s = BZip2Utils.bzip2(project.getBasePath());
        byte[] read = ReadFromFileUtils.read(s);

        String post = http.post(url, read, contentType);

        return post;
    }

    public String deleteImage(String name) throws IOException {
        String url = host + "/images/" + name;
        String res = http.any(url, null, "DELETE", null);

        return res;
    }

    public String deleteContainer(String name) throws IOException {
        String url = host + "/containers/" + name + "?v=true&force=true";
        String res = http.any(url, null, "DELETE", null);

        return res;
    }
}
