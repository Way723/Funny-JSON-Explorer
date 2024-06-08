package org.example;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.apache.commons.cli.*;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option jsonFile = new Option("f", "file", true, "JSON file path");
        jsonFile.setRequired(true);
        options.addOption(jsonFile);

        Option styleOption = new Option("s", "style", true, "Display style (tree/rectangle)");
        styleOption.setRequired(true);
        options.addOption(styleOption);

        Option iconFamilyOption = new Option("i", "iconFamily", true, "Icon family (poker/love)");
        iconFamilyOption.setRequired(true);
        options.addOption(iconFamilyOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            String jsonFilePath = cmd.getOptionValue("file");
            String style = cmd.getOptionValue("style");
            String iconFamily = cmd.getOptionValue("iconFamily");

            // 读取 JSON 文件内容
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            // 使用建造者模式创建不同样式的 JSONExplorer
            JSONBuilder builder = new JSONBuilder();

            // 根据参数设置样式工厂
//            if ("tree".equals(style)) {
//                builder.setStyleFactory(new TreeJSONStyleFactory());
//            } else if ("rectangle".equals(style)) {
//                builder.setStyleFactory(new RectangleJSONStyleFactory());
//            } else {
//                throw new IllegalArgumentException("Unsupported style: " + style);
//            }
            builder.setStyle(style);
            // 根据参数设置图标族工厂
//            if ("circle".equals(iconFamily)) {
//                builder.setIconFamily(new CircleIconFamily());
//            } else if ("math".equals(iconFamily)) {
//                builder.setIconFamily(new MathIconFamily());
//            } else {
//                throw new IllegalArgumentException("Unsupported icon family: " + iconFamily);
//            }
            builder.setIcon(iconFamily);
            JSONDirector director = new JSONDirector(builder);
            JSONExplorer explorer = director.build();
            explorer.display(jsonContent);

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("fje", options);

            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//public class Main {
//    public static void main(String[] args) {
//        try {
//            // 从配置文件读取配置
//            String configContent = new String(Files.readAllBytes(Paths.get("src/main/java/org/example/config.json")));
//            JSONObject config = new JSONObject(new JSONTokener(configContent));
//
//            // 获取样式和图标族的配置
//            String style = config.getString("style");
//            String iconFamily = config.getString("iconFamily");
//            System.out.println("图标族：" + iconFamily);
//            // 使用建造者模式创建不同样式的 JSONExplorer
//            JSONBuilder builder = new JSONBuilder();
//
//            // 根据配置设置样式工厂
//            if ("tree".equals(style)) {
//                builder.setStyleFactory(new TreeJSONStyleFactory());
//            } else if ("rectangle".equals(style)) {
//                builder.setStyleFactory(new RectangleJSONStyleFactory());
//            } else {
//                throw new IllegalArgumentException("Unsupported style: " + style);
//            }
//
//            // 根据配置设置图标族工厂
//            if ("poker".equals(iconFamily)) {
//                builder.setIconFamily(new PokerFaceIconFamily());
//            } else if ("love".equals(iconFamily)) {
//                builder.setIconFamily(new LoveIconFamily());
//            } else {
//                throw new IllegalArgumentException("Unsupported icon family: " + iconFamily);
//            }
//
//            JSONExplorer explorer = builder.build();
//
//            // 从文件读取 JSON 内容
//            String json = new String(Files.readAllBytes(Paths.get("src/main/data/test.json")));
//            explorer.display(json);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


