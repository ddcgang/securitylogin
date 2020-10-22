package com.demo.securitylogin.controller.doc;

import com.demo.securitylogin.util.PathUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


@RequestMapping("doc")
@RestController
public class DocController {
//https://www.cnblogs.com/yunfeiyang-88/p/10984740.html
    /**
     * iText
     *
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    @GetMapping("doc1")
    public void doc1() throws FileNotFoundException, DocumentException {
        String DEST = "target/classes/static/doc/pdf/1/HelloWorld.pdf";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        document.add(new Paragraph("Hello World !"));
        document.close();
        writer.close();
    }

    /**
     * iText-中文支持
     *
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    @GetMapping("doc2")
    public void doc2() throws FileNotFoundException, DocumentException {
        String DEST = "target/classes/static/doc/pdf/2/HelloWorld.pdf";
        String FONT = "C:/WINDOWS/Fonts/simhei.ttf";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        Font f1 = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        document.add(new Paragraph("Hello World123，中国", f1));
        document.close();
        writer.close();
    }

    /**
     * iText-Html渲染
     *
     * @throws IOException
     * @throws DocumentException
     */
    @GetMapping("doc3")
    public void doc3() throws IOException, DocumentException {
        String DEST = "target/classes/static/doc/pdf/3/HelloWorld.pdf";
        String HTML = PathUtil.getCurrentPath() + "static/doc/doc3.html";
        String FONT = "C:/WINDOWS/Fonts/simhei.ttf";
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(HTML), null, Charset.forName("UTF-8"), fontImp);
        document.close();
    }

    /**
     * iText-Html-Freemarker渲染
     *
     * @throws IOException
     * @throws DocumentException
     */
    @GetMapping("doc4")
    public void doc4() throws IOException, DocumentException {
        String DEST = "target/classes/static/doc/pdf/4/HelloWorld.pdf";
        String HTML = "static/doc/doc4.html";
        String FONT = "C:/WINDOWS/Fonts/simhei.ttf";
        Map<String, Object> data = new HashMap();
        data.put("name", "我爱中国");
        data.put("user", "管理员！@#￥%……&*（）-=（）");
        String content = freeMarkerRender(data, HTML);
        createPdf(content, DEST, FONT);
    }

    private void createPdf(String content, String dest, String font) throws IOException, DocumentException {
        Rectangle rectangle=new Rectangle(0,0,600,400);
        Document document = new Document(rectangle, 10, 10, 10, 10);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(font);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new ByteArrayInputStream(content.getBytes()), null, Charset.forName("UTF-8"), fontImp);
        document.close();
    }

    /**
     * freemarker渲染html
     */
    private String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Configuration freemarkerCfg = new Configuration();
            freemarkerCfg.setDirectoryForTemplateLoading(new File(PathUtil.getCurrentPath()));
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


    /**
     * Flying Saucer-CSS高级特性支持
     *
     * @throws IOException
     * @throws DocumentException
     */
    @GetMapping("doc5")
    public void doc5() throws IOException, DocumentException, com.lowagie.text.DocumentException {
        String DEST = "target/classes/static/doc/pdf/5/HelloWorld.pdf";
        String HTML = "static/doc/doc5.html";
        String FONT = "C:/WINDOWS/Fonts/simhei.ttf";
        String LOGO = "file://"+PathUtil.getCurrentPath()+"static/img/ico_head.jpg";
        System.out.println(LOGO);
        Map<String, Object> data = new HashMap();
        data.put("name", "我爱中国");
        data.put("user", "s");
        data.put("url", "/");
        String content = freeMarkerRender5(data, HTML);
        createPdf5(content, DEST, FONT, LOGO);
    }

    /**
     * freemarker渲染html
     */
    private String freeMarkerRender5(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            Configuration freemarkerCfg = new Configuration();
            freemarkerCfg.setDirectoryForTemplateLoading(new File(PathUtil.getCurrentPath()));
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private void createPdf5(String content, String dest, String font, String logoPath) throws IOException, DocumentException, com.lowagie.text.DocumentException {
        ITextRenderer render = new ITextRenderer();
        ITextFontResolver fontResolver = render.getFontResolver();
        fontResolver.addFont(font, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        //解决图片相对路径的问题
        render.getSharedContext().setBaseURL(logoPath);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
    }
}
