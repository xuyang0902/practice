package com.zx.yuren.spring;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 测试spring里面提供的一些好用的类
 * @author xu.qiang
 * @date 19/3/14
 */
public class SpringTest {


    public static void main(String[] args) throws Exception {

        //testResources();

        testParseXml();

    }

    //测试资源读取
    public static void testResources() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:**/spring/aop-annotation.xml");

        for (Resource resource : resources) {

            //resouce
            System.out.println(resource.getDescription());

            InputStream inputStream1 = resource.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i ;

            while ((i = inputStream1.read()) != -1){
                byteArrayOutputStream.write(i);
            }

            System.out.println(byteArrayOutputStream.toString());
        }

    }

    public static void testParseXml() throws IOException, ParserConfigurationException, SAXException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:**/spring/aop-annotation.xml");

        Resource resource1 = resources[0];


        DocumentBuilderFactory docFac=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= docFac.newDocumentBuilder();
        //解析流

        Document document = builder.parse(resource1.getInputStream());
        NodeList childNodes = document.getChildNodes();


        showChiledNOdes(childNodes);


    }


    public static void showChiledNOdes(NodeList childNodes){

        if(childNodes == null){
            return;
        }

        int length = childNodes.getLength();

        for(int x = 0 ; x < length;x++){

            Node item = childNodes.item(x);

            System.out.println(item.getBaseURI() + " || " + item.getNamespaceURI() + " || " + item.getNodeName() + "||" + item.getNodeValue());
            NodeList child = item.getChildNodes();
            if( child!= null){
                showChiledNOdes(child);
            }
        }
    }


}
