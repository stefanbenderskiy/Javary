package com.reflection.javary.lesson;

import android.preference.PreferenceDataStore;
import android.util.Log;

import com.reflection.javary.lesson.elements.Image;
import com.reflection.javary.lesson.elements.Text;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LessonHandler extends DefaultHandler {
    private Page page= null;
    private String qName;

    private Lesson lesson = new Lesson();
    private boolean hasResult = false;
    private Element element = null;
    private String[] elementNames={"text","image"};
    private boolean isContent =false;
    private boolean charactersHandled =false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.qName =qName;
        charactersHandled =false;
        if (qName.equals("page")){
            page = new Page();
        } else if (qName.equals("text")) {
            Text text = new Text(null);
            if (attributes.getValue("type") != null){
                switch (attributes.getValue("type").replace(" ","")){
                    case "header":
                        text.setType(Text.TYPE.HEADER);
                        break;
                    case "title":
                        text.setType(Text.TYPE.TITLE);
                        break;
                    default:
                        text.setType(Text.TYPE.PARAGRAPH);
                }
            }
            element = text;

        } else if (qName.equals("content")) {
            isContent =true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.replace(" ","")){
            case "page":
                lesson.addPage(page);
                page = null;
                break;
            case "content":
                isContent= false;
        }

    }

    @Override
    public void endDocument() throws SAXException {
        hasResult =true;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!charactersHandled){
            charactersHandled =true;
            String str = new String(ch,start,length);
            if ( !isContent){

                switch (qName.replace(" ","")){
                    case "title":

                        lesson.setTitle(str);
                        break;



                }
            }else{
                Log.println(Log.INFO,"LESSON_HANDLER",qName);
                switch (qName.replace(" ","")){
                    case "text":
                        Text text = (Text) element;
                        text.setText(str);
                        page.add(text);
                        Log.println(Log.INFO,"LESSON_HANDLER",str);
                        break;
                    case "image":
                        Image img = (Image) element;
                        page.add(img);
                }
            }
        }


    }
    public Lesson getResult(){
        return hasResult ? lesson : null;

    }
}
