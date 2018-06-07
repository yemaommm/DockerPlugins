package dto.docker;

import java.util.List;
import java.util.Map;

public class Container {

    private String Id;
    private String Command;
    private Long Created;
    private String Image;
    private String ImageID;
    private Map<String, String> Labels;
    private String Mounts;
    private List<String> Names;
    private String NetworkSettings;
    private String Ports;
    private String State;
    private String Status;
    private String HostConfig;

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

    public String getMounts() {
        return Mounts;
    }

    public void setMounts(String mounts) {
        Mounts = mounts;
    }

    public List<String> getNames() {
        return Names;
    }

    public void setNames(List<String> names) {
        Names = names;
    }

    public String getNetworkSettings() {
        return NetworkSettings;
    }

    public void setNetworkSettings(String networkSettings) {
        NetworkSettings = networkSettings;
    }

    public String getPorts() {
        return Ports;
    }

    public void setPorts(String ports) {
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

    public String getHostConfig() {
        return HostConfig;
    }

    public void setHostConfig(String hostConfig) {
        HostConfig = hostConfig;
    }
}
