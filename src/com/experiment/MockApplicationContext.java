package com.experiment;

import org.apache.commons.beanutils.BeanUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MockApplicationContext {
    private Map<String, Object> beanMap;

    public MockApplicationContext(String fileName) {
        beanMap = new HashMap();

        try {
            File fXmlFile = new File(getClass().getClassLoader().getResource(fileName).getFile());
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            processNode(doc.getChildNodes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processNode(NodeList nodeList) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (tempNode.getNodeName().equals("bean")) {
                    processBeanNode(tempNode);
                }

                processNode(tempNode.getChildNodes());
            }
        }
    }

    private void processBeanNode(Node beanNode) {
        NamedNodeMap nodeMap = beanNode.getAttributes();
        String id = "";
        String className = "";
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node node = nodeMap.item(i);
            if (node.getNodeName().equals("id")) {
                id = node.getNodeValue();
            }

            if (node.getNodeName().equals("class")) {
                className = node.getNodeValue();
            }
        }

        try {
            Object bean = Class.forName(className).newInstance();
            beanMap.put(id, bean);

            NodeList nodeList = beanNode.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("property")) {
                    processBeanProperty(node, bean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processBeanProperty(Node node, Object bean) {
        NamedNodeMap nodeMap = node.getAttributes();
        String fieldName = "";
        String fieldValue = "";

        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node innerNode = nodeMap.item(i);
            if (innerNode.getNodeName().equals("name")) {
                fieldName = innerNode.getNodeValue();
            }

            if (innerNode.getNodeName().equals("value")) {
                fieldValue = innerNode.getNodeValue();
            }
        }

        try {
            BeanUtils.setProperty(bean, fieldName, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String beanName) {
        return beanMap.get(beanName);
    }
}
