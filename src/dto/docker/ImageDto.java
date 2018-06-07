package dto.docker;

import clojure.lang.Obj;

import java.util.List;
import java.util.Map;

public class ImageDto {

    private int Containers;
    private Long Created;
    private String Id;
    private Map<String, String> Labels;
    private String ParentId;
    private Object RepoDigests;
    private List<String> RepoTags;
    private int SharedSize;
    private Long Size;
    private Long VirtualSize;

    public int getContainers() {
        return Containers;
    }

    public void setContainers(int containers) {
        Containers = containers;
    }

    public Long getCreated() {
        return Created;
    }

    public void setCreated(Long created) {
        Created = created;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Map<String, String> getLabels() {
        return Labels;
    }

    public void setLabels(Map<String, String> labels) {
        Labels = labels;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public Object getRepoDigests() {
        return RepoDigests;
    }

    public void setRepoDigests(Object repoDigests) {
        RepoDigests = repoDigests;
    }

    public List<String> getRepoTags() {
        return RepoTags;
    }

    public void setRepoTags(List<String> repoTags) {
        RepoTags = repoTags;
    }

    public int getSharedSize() {
        return SharedSize;
    }

    public void setSharedSize(int sharedSize) {
        SharedSize = sharedSize;
    }

    public Long getSize() {
        return Size;
    }

    public void setSize(Long size) {
        Size = size;
    }

    public Long getVirtualSize() {
        return VirtualSize;
    }

    public void setVirtualSize(Long virtualSize) {
        VirtualSize = virtualSize;
    }
}
