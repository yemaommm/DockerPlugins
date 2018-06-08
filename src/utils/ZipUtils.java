package utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    public static void zip(String zipFileName, String srcFile) throws Exception {
        File inputFile = new File(srcFile);
        System.out.println("压缩中...");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zip(out, inputFile, "", bo);
        bo.close();
        out.close(); // 输出流关闭
        System.out.println("压缩完成");
    }

    /**
     * 方法重载
     * @param out
     * @param f
     * @param base
     * @param bo
     * @throws Exception
     */
    public static void zip(ZipOutputStream out, File f, String base,
                           BufferedOutputStream bo) throws Exception {
        if (f.isDirectory()){
            File[] fl = f.listFiles();
            if (fl.length == 0){
                // 创建zip压缩进入点base
                out.putNextEntry(new ZipEntry(base.equals("")?base:base + "/"));
                System.out.println(base.equals("")?base:base + "/");
            }
            for (int i = 0; i < fl.length; i++) {
                // 递归遍历子文件夹
                zip(out, fl[i], (base.equals("")?base:base + "/") + fl[i].getName(), bo);
            }
        } else {
            // 创建zip压缩进入点base
            out.putNextEntry(new ZipEntry(base));
            System.out.println(base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                // 将字节流写入当前zip目录
                bo.write(b);
            }
            bi.close();
            // 输入流关闭
            in.close();
        }
    }
}
