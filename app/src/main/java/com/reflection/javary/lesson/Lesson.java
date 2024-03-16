package com.reflection.javary.lesson;

import android.preference.PreferenceDataStore;

import com.reflection.javary.parser.XMLHandler;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Lesson {

    private String title;
    private List<Page> pages = new ArrayList<>();



    public static Lesson parseFrom(InputStream xml)  {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = parserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        XMLHandler handler = new XMLHandler();
        try {
            try {
                parser.parse(xml,handler);
            } catch (SAXException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (handler.getResult()==null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return (Lesson) new LessonConverter().convertToObject(handler.getResult());
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
}
    public int getSize(){return pages.size();}
    public List<Page> getPages() {
        return pages;
    }
    public Page getPage(int index) {
        return pages.get(index);
    }
    public void addPage(Page page) {
        pages.add(page);
    }
}
