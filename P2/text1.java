/*爬取图片信息*/
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class text1 {
	
    private static String url = "https://www.zhihu.com/question/31819473";                // 地址
    private String tag = "src=\"(.+?)\"|data-original=\"(.+?)\"|data-actualsrc=\"(.+?)\"";// 获取要下载的标签正则
    private String src =  "\"https(.+?)\"";                                               // 获取src路径的正则


    public static void main(String[] args) {
        try {
        	text1 pc=new text1();
            String HTML = pc.getHtml(url);                     //获得html文本内容
            List<String> imgUrl = pc.getImageUrl(HTML);        //获取要下载的图片地址
            List<String> imgSrc = pc.getImageSrc(imgUrl);      //获取图片src地址
            pc.Download(imgSrc);                               //下载图片

        }catch (Exception e){
            System.out.println("发生错误");
        }

    }

   //获取HTML内容并以字符串的形式返回
    private String getHtml(String url)throws Exception{
        //从Internet 获取网页, 发送请求, 将网页以流的形式读回来
    	URL url1=new URL(url);
        URLConnection connection=url1.openConnection();
        InputStream in=connection.getInputStream();
        InputStreamReader isr=new InputStreamReader(in);
        BufferedReader br=new BufferedReader(isr);

        String line;
        StringBuffer sb=new StringBuffer();
        while((line=br.readLine())!=null){
            sb.append(line,0,line.length());
            sb.append('\n');
        }
        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }

    //获取网页中图片的地址
    private List<String> getImageUrl(String html){
        Pattern pattern=Pattern.compile(tag);         //Patter对象所编译的正则表达式
    	Matcher matcher=pattern.matcher(html);        //依据Pattern对象对字符串展开匹配检查
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){						  //将获取的字符串存入List对象
            listimgurl.add(matcher.group());
        }
        return listimgurl;
    }

    //获取ImageSrc地址
    private List<String> getImageSrc(List<String> listimageurl){
        List<String> listImageSrc=new ArrayList<String>();
        for (String image:listimageurl){
        	Pattern pattern=Pattern.compile(src);	   //Patter对象所编译的正则表达式
            Matcher matcher=pattern.matcher(image);    //依据Pattern对象对字符串展开匹配检查
            while (matcher.find()){                    //将获取的字符串存入List对象
                listImageSrc.add(matcher.group().substring(0, matcher.group().length()-1));
                //System.out.println(matcher.group());
            }
        }
        return listImageSrc;
    }

    //下载图片
    private void Download(List<String> listImgSrc) {
        try {
            for (String url : listImgSrc) {
            	String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());//找到最后的“/”获取图片的名字
                url=url.substring(1, url.length());     //提取网址，生成url对象
                URL uri = new URL(url);       
                InputStream in = uri.openStream();      //将图片下载到 IStream 流
                FileOutputStream fo = new FileOutputStream(new File("D:/大学文件/web/src/"+imageName));
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);            
                while ((length = in.read(buf, 0, buf.length)) != -1) {//读取流文件，下载到文件输出流的目的地址
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
            }
            System.out.println("全部下载完毕");
        } catch (Exception e) {
            System.out.println("下载失败");
        }
    }
}
