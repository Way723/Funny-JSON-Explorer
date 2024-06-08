# Funny-JSON-Explorer

### 设计要求

基于上述需求描述和领域模型，按照设计模式要求，进行软件设计，并编码实现（任何语言均可）。

#### 设计模式

使用**工厂方法**（Factory）、**抽象工厂**（Abstract Factory）、**建造者**（Builder）模式、**组合模式**（Composition），完成功能的同时，使得程序易于扩展和维护。

1. （必做）：不改变现有代码，只需添加新的抽象工厂，即可添加新的风格
2. （选做）：通过配置文件，可添加新的图标族

### 类图

![uml.drawio](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje\uml.drawio.png)

### 设计模式说明

#### 工厂方法模式

抽象产品接口为JSONExplorer，抽象工厂接口为JSONStyleFactory，具体产品类为TreeJSONExplorer和RectangleJSONExplorer，具体工厂类为TreeJSONStyleFactory和RectangleJSONStyleFactory。由于这里只涉及单一产品，所以没有使用抽象工厂模式。

#### 建造者模式

定义一个建造者抽象类abstractBuilder，实现建造者具体类JSONBuilder，其中包括buildStyle()和buildIcon()方法，最后定于一个指挥者类JSONDirector用于控制建造过程，在main函数中就可以利用这些类来构建explorer。

#### 组合模式

这里将JSON格式文件用组合模式存储起来，定义了如何将容器对象和叶子对象进行递归组合，使得explorer在使用的过程中无需区分，可以对它们进行一致的处理。

在这个设计中，添加新的抽象工厂并实现对应的explorer即可添加新的Style。对于图标族的添加，只需要修改项目中配置文件config.json即可：

```json
[
  {
    "name": "circle",
    "nodeIcon": "○",
    "leafIcon": "●"
  },
  {
    "name": "square",
    "nodeIcon": "□",
    "leafIcon": "■"
  },
  {
    "name": "math",
    "nodeIcon": "+",
    "leafIcon": "*"
  }
]
```

### 运行截图

![1](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje\1.png)

![2](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje\2.png)

![3](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje\3.png)

![4](C:\mac\OneDrive - 中山大学\软工\Design Pattern 习题\fje\4.png)
