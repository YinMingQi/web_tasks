/*获取网页的HTML*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
public class test {

    public static void main(String[] args) throws IOException{
        try {
        	String url = "https://www.zhihu.com/question/31819473";
            URL uri = new URL(url);
            InputStream in = uri.openStream();                    //将html下载到 IStream 流
            FileOutputStream fo = new FileOutputStream(new File("D:/大学文件/web/src/text.txt"));
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = in.read(b, 0, b.length)) != -1) {   //写入文件
                fo.write(b, 0, length);
            }
            in.close();
            fo.close();
            System.out.println("页面html下载完毕");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

