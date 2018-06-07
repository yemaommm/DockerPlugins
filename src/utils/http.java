package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class http {

    private static Logger LOGGER = LoggerFactory.getLogger(http.class);

    public static String get(String path) throws IOException {
        HttpURLConnection conn = connect(path);
        //表示连接
        conn.connect();

        int code = conn.getResponseCode();
        LOGGER.info("ResponseCode: " + String.valueOf(code));

        InputStream is = conn.getInputStream();
        String value = readStream(is);

        is.close();

        return value;
    }

    public static String post(String path, byte[] body) throws IOException {
        HttpURLConnection conn = connect(path);
        conn.setRequestMethod("POST");

        conn.connect();

        OutputStream os = conn.getOutputStream();
        os.write(body);
        os.flush();

        int code = conn.getResponseCode();
        LOGGER.info("ResponseCode: " + String.valueOf(code));
        InputStream is = null;
        if (code == 200){
            is = conn.getInputStream();
        }else{
            is = conn.getErrorStream();
        }
        String value = readStream(is);

        is.close();

        return value;
    }

    public static HttpURLConnection connect(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection  conn = (HttpURLConnection) url.openConnection();

        //设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
        conn.setRequestMethod("GET");
        //设置连接超时
        conn.setConnectTimeout(5000);
        //是否打开输入流 ， 此方法默认为true
        conn.setDoInput(true);
        //是否打开输出流， 此方法默认为false
        conn.setDoOutput(true);

        return conn;
    }

    /**
     * 读取 InputStream 到 String字符串中
     */
    public static String readStream(InputStream in) {
        try {
            //<1>创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //<2>创建缓存大小
            byte[] buffer = new byte[1024]; // 1KB
            //每次读取到内容的长度
            int len = -1;
            //<3>开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
            }
            //<4> 把字节数组转换为字符串
            String content = baos.toString();
            //<5>关闭输入流和输出流
            in.close();
            baos.close();
            //<6>返回字符串结果
            return content;
        } catch (Exception e) {
            LOGGER.error("readStream: ", e);
        }
        return "";
    }
}
