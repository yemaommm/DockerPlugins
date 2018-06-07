package component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class TestComponent implements ApplicationComponent {
    public TestComponent() {
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "test";
    }

    public void sayHello(){
        Messages.showMessageDialog("hello", "bbody", Messages.getInformationIcon());
    }
}
