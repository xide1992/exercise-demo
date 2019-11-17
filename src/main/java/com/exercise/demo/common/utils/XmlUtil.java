package com.exercise.demo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.util.List;

/**
 * @Author: xdz
 * @Descrption:
 * @Date: 2019/9/7 23:55
 */
public class XmlUtil {

    private SAXReader reader = new SAXReader();
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtil.class);

    /**
     * 从网络中(URL)读取XML内容
     */
    public Document readDocument(String url) {
        try {
            return reader.read(new URL(url));
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 读取XML文件
     */
    public Document readDocumentFromFile(String filePath) {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filePath);
            Document document = reader.read(inputStream);
            return document;
        } catch (Exception e) {
            LOGGER.error("XML HELP EX:", e);
        }
        return null;
    }

    /**
     * String 转 org.dom4j.Document
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Document strToDocument(@NotNull String xml) throws DocumentException {
        return DocumentHelper.parseText(xml);
    }

    /**
     * 获取的根节点
     *
     * @param doc dom4j文档对象
     * @return 根节点元素
     */
    public static Element rootElement(@NotNull Document doc) {
        return doc.getRootElement();
    }

    /**
     * 获取某个节点的值
     *
     * @param elt      某节点
     * @param nodeName 节点名称
     * @return 返回某节点对应节点值
     */
    public static String elementTextTrim(Element elt, String nodeName) {
        return elt.elementTextTrim(nodeName);
    }

    /**
     * 获取节点列表
     *
     * @param doc             dom4j文档对象
     * @param xpathExpression xpath表达式
     * @return 节点列表
     */
    public static List selectNodes(@NotNull Document doc, @NotNull String xpathExpression) {
        return doc.selectNodes(xpathExpression);
    }

    /**
     * 获取节点列表里的每个节点值
     *
     * @param node     节点元素
     * @param nodeName 节点名称
     * @param defaulte 默认值
     * @return
     */
    public static String selectSingleNode(Node node, String nodeName, String defaulte) {
        return node.selectSingleNode(nodeName) == null ? defaulte : node.selectSingleNode(nodeName).getText();
    }

    /**
     * org.dom4j.Document 转  com.alibaba.fastjson.JSONObject
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject documentToJSONObject(String xml) throws DocumentException {
        return elementToJSONObject(strToDocument(xml).getRootElement());
    }

    /**
     * org.dom4j.Element 转  com.alibaba.fastjson.JSONObject
     *
     * @param node
     * @return
     */
    public static JSONObject elementToJSONObject(Element node) {
        JSONObject result = new JSONObject();
        // 当前节点的名称、文本内容和属性
        List<Attribute> listAttr = node.attributes();// 当前节点的所有属性的list
        for (Attribute attr : listAttr) {// 遍历当前节点的所有属性
            result.put(attr.getName(), attr.getValue());
        }
        // 递归遍历当前节点所有的子节点
        List<Element> listElement = node.elements();// 所有一级子节点的list
        if (!listElement.isEmpty()) {
            for (Element e : listElement) {// 遍历所有一级子节点
                if (e.attributes().isEmpty() && e.elements().isEmpty()) // 判断一级节点是否有属性和子节点
                    result.put(e.getName(), e.getTextTrim());// 沒有则将当前节点作为上级节点的属性对待
                else {
                    if (!result.containsKey(e.getName())) // 判断父节点是否存在该一级节点名称的属性
                        result.put(e.getName(), new JSONArray());// 没有则创建
                    ((JSONArray) result.get(e.getName())).add(elementToJSONObject(e));// 将该一级节点放入该节点名称的属性对应的值中
                }
            }
        }
        return result;
    }

    /**
     * @param objecct 需要转化的实体
     * @return 转化之后的字符串
     * @throws
     * @Description: 将XML实体转化为字符串
     */
    public static String objectToXml(Object objecct) throws Exception {
        JAXBContext context = JAXBContext.newInstance(objecct.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");//编码格式
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);//是否省略xml头信息，默认false
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化生成的xml串,默认false
        Writer writer = new StringWriter();
        marshaller.marshal(objecct, writer);
        return writer.toString();
    }

    /**
     * @param xml       需要转化的实体
     * @param classType 需要转化后返还的实体泛型类型
     * @return 转串化之后的实体
     * @throws
     * @Description: 将XML实体转化为字符串
     */
    public static <T> T xmlToObject(String xml, Class<T> classType) throws Exception {
        JAXBContext context = JAXBContext.newInstance(classType);//需要转换的类.calss
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));//xml字符串
    }
}
