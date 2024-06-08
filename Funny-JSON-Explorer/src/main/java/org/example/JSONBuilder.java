package org.example;

// JSONBuilder.java

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JSONBuilder implements abstractBuilder {
    private JSONStyleFactory styleFactory;

    private IconFamilyConfig iconFamilyConfig;

    private String icon;

    private String style;

    JSONExplorer explorer;
//
    public JSONBuilder() {
        // 默认使用树形风格工厂
        this.styleFactory = new TreeJSONStyleFactory();

    }

    public void setStyle(String style) {
        this.style = style;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }

    public JSONExplorer getExplorer() {
        return explorer;
    }

    @Override
    public void buildstyle() {
        if ("tree".equals(style)) {
            this.styleFactory = new TreeJSONStyleFactory();
        } else if ("rectangle".equals(style)) {
            this.styleFactory = new RectangleJSONStyleFactory();
        }
    }

    @Override
    public void buildIcon() {
        this.iconFamilyConfig = new IconFamilyConfigImpl();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 从配置文件中读取JSON数据
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.json");
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            // 遍历JSON数据，添加图标族信息
            for (JsonNode node : jsonNode) {
                String name = node.get("name").asText();
                String nodeIcon = node.get("nodeIcon").asText();
                String leafIcon = node.get("leafIcon").asText();
                iconFamilyConfig.addIconFamily(name, nodeIcon, leafIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.explorer = styleFactory.createExplorer();
        explorer.setIcon(icon);
        explorer.setIconFamilyConfig(iconFamilyConfig);
    }
}



