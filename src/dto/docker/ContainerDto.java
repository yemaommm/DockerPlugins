package dto.docker;

import java.util.List;
import java.util.Map;

public class ContainerDto {

    private String Id;
    private String Command;
    private Long Created;
    private String Image;
    private String ImageID;
    private Map<String, String> Labels;
    private List<Map<String, String>> Mounts;
    private List<String> Names;
    private Object NetworkSettings;
    private List<Map<String, String>> Ports;
    private String State;
    private String Status;
    private Map<String, String> HostConfig;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCommand() {
        return Command;
    }

    public void setCommand(String command) {
        Command = command;
    }

    public Long getCreated() {
        return Created;
    }

    public void setCreated(Long created) {
        Created = created;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public Map<String, String> getLabels() {
        return Labels;
    }

    public void setLabels(Map<String, String> labels) {
        Labels = labels;
    }

    public List<String> getNames() {
        return Names;
    }

    public void setNames(List<String> names) {
        Names = names;
    }

    public List<Map<String, String>> getPorts() {
        return Ports;
    }

    public void setPorts(List<Map<String, String>> ports) {
        Ports = ports;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<Map<String, String>> getMounts() {
        return Mounts;
    }

    public void setMounts(List<Map<String, String>> mounts) {
        Mounts = mounts;
    }

    public Object getNetworkSettings() {
        return NetworkSettings;
    }

    public void setNetworkSettings(Object networkSettings) {
        NetworkSettings = networkSettings;
    }

    public Map<String, String> getHostConfig() {
        return HostConfig;
    }

    public void setHostConfig(Map<String, String> hostConfig) {
        HostConfig = hostConfig;
    }

    @Override
    public String toString() {
        return "ContainerDto{" +
                "Id='" + Id + '\'' +
                ", Command='" + Command + '\'' +
                ", Created=" + Created +
                ", Image='" + Image + '\'' +
                ", ImageID='" + ImageID + '\'' +
                ", Labels=" + Labels +
                ", Mounts='" + Mounts + '\'' +
                ", Names=" + Names +
                ", NetworkSettings='" + NetworkSettings + '\'' +
                ", Ports='" + Ports + '\'' +
                ", State='" + State + '\'' +
                ", Status='" + Status + '\'' +
                ", HostConfig='" + HostConfig + '\'' +
                '}';
    }
}
