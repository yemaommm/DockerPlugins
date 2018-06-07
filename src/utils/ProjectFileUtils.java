package utils;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.impl.file.PsiDirectoryFactory;

public class ProjectFileUtils {

    private PsiDirectory baseDir;

    public PsiDirectory getBasePath(Project project){
        if (baseDir == null){
            baseDir = PsiDirectoryFactory.getInstance(project).createDirectory(project.getBaseDir());
        }
        return baseDir;
    }

    public static String toTar(Project project) throws Exception {
        String archive = TarUtils.archive(project.getBasePath());
        return archive;
    }
}
