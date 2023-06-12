package com.odd.common.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.lang.reflect.Field;

public class XmlUtil {
    public static String objectToXml(Object object) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // 创建 Document 对象
            Document doc = docBuilder.newDocument();

            // 创建根节点 <xml>
            Element rootElement = doc.createElement("xml");
            doc.appendChild(rootElement);

            // 转换对象为 XML
            convertObjectToXml(doc, rootElement, object);

            // 将 Document 对象转换为字符串
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void convertObjectToXml(Document doc, Element parentElement, Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);

            String tagName = capitalizeFirstLetter(field.getName());

            Element element = doc.createElement(tagName);
            parentElement.appendChild(element);

            if (fieldValue == null) {
                continue;
            }

            if (fieldValue instanceof String) {
                String value = (String) fieldValue;
                element.appendChild(doc.createCDATASection(value));
            } else if (fieldValue.getClass().isPrimitive() || fieldValue instanceof Number) {
                String value = String.valueOf(fieldValue);
                element.appendChild(doc.createTextNode(value));
            } else {
                convertObjectToXml(doc, element, fieldValue);
            }
        }
    }

    private static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }
}

