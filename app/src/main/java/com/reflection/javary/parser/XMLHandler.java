package com.reflection.javary.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import java.util.ArrayList;

public class XMLHandler extends DefaultHandler {
    private XMLElement element;
    private ArrayList<XMLElement> parents;
    private boolean handled = false;
    private int level=-1;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        level+=1;
        element= new XMLElement(qName);
        for (int i = 0; attributes.getQName(i)!=null;i++){
            System.out.println(attributes.getQName(i));
            element.setAttribute(attributes.getQName(i),attributes.getValue(i));
        }
        parents.add(level,element);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (level>0){
            parents.get(level-1).add(element);

            if (parents.get(level) == element){
                parents.remove(level);
                level-=1;
                element= parents.get(level);
            }else {
                parents.set(level,element);
            }

        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        element.setCharacters(element.getCharacters() + new String(ch, start, length).replace("\t","").replace("\n",""));
        parents.set(level,element);
    }

    @Override
    public void startDocument() throws SAXException {
        level=-1;
        element= null;
        parents= new ArrayList<>();
        handled =false;
    }

    @Override
    public void endDocument() throws SAXException {
        handled= true;
    }

    public XMLElement getResult(){
        if (handled){
            return parents.get(0);
        }else {
            return null;
        }
    }

}