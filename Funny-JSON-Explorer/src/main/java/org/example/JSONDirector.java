package org.example;

public class JSONDirector {
    JSONBuilder builder;

    JSONDirector(JSONBuilder builder){
        this.builder = builder;
    }

    public JSONExplorer build(){
        builder.buildstyle();
        builder.buildIcon();
        return builder.getExplorer();
    }



}
