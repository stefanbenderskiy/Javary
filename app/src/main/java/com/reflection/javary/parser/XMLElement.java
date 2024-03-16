package com.reflection.javary.parser
        ;


import org.xml.sax.Attributes;

import java.util.ArrayList;

public class XMLElement {
    private String qName;
    private XMLElement parent;
    private String characters= "";
    private ArrayList<XMLElement> children= new ArrayList<>();
    private ArrayList<Attribute> attributes= new ArrayList<>();
    public static class Attribute{
        public String qName;
        public String value;

        public Attribute(String qName, String value) {
            this.qName = qName;
            this.value = value;
        }



    }

    public XMLElement(String qName) {
        this.qName = qName;


    }

    public ArrayList<XMLElement> getChildren() {
        return children;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public String getQName() {
        return qName;
    }
    public Attribute getAttribute(String qName) {
        for (Attribute a: attributes){
            if (a.qName == qName){
                return a;
            }


        }
        return null;
    }
    public void setAttribute(String qName,String value) {
        Attribute a= new Attribute(qName,value);
        if( getAttribute(qName)!= null){
            attributes.set(attributes.indexOf(getAttribute(qName)),a);
        }else {
            attributes.add(a);
        }

    }



    public void setQName(String qName) {
        this.qName = qName;
    }

    public XMLElement getParent() {
        return parent;
    }
    public void add(XMLElement child){
        if (child != null){
            children.add(child);
            child.parent= this;
        }
    }
    public void remove(XMLElement child){
        if (child != null){
            children.remove(child);
            child.parent= null;
        }
    }
    public boolean contains(XMLElement child){
        return children.contains(child);
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<"+qName);
        for (Attribute a: attributes){
            stringBuilder.append(" "+ a.qName+"="+'"'+a.value+'"');
        }
        if (!children.isEmpty() || !characters.isEmpty()){

            stringBuilder.append(">\n");

            stringBuilder.append("\t"+ characters+"\n");
            for (XMLElement child:children){
                stringBuilder.append("\t" +child.toString().replace("\n","\n\t")+"\n");
            }

            stringBuilder.append("<"+qName+"/>\n");
        }else{
            stringBuilder.append("/>\n");
        }

        return stringBuilder.toString();
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}