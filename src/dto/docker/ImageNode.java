package dto.docker;

import javax.swing.tree.DefaultMutableTreeNode;

public class ImageNode extends DefaultMutableTreeNode {

    private ImageDto image;

    public ImageNode(String name, ImageDto image){
        super(name);
        this.image = image;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }
}
