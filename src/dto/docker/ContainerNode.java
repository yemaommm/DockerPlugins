package dto.docker;

import javax.swing.tree.DefaultMutableTreeNode;

public class ContainerNode extends DefaultMutableTreeNode {

    private ContainerDto container;

    public ContainerNode(String name, ContainerDto container){
        super(name);
        this.container = container;
    }

    public ContainerDto getContainer() {
        return container;
    }

    public void setContainer(ContainerDto container) {
        this.container = container;
    }
}
