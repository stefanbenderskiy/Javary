package com.reflection.javary.lesson;

import android.util.Log;

import com.reflection.javary.lesson.elements.Text;
import com.reflection.javary.parser.XMLConverter;
import com.reflection.javary.parser.XMLElement;

public class LessonConverter implements XMLConverter {

    @Override
    public Object convertToObject(XMLElement root) {
        Lesson lesson = new Lesson();

        if (root.getQName()== "lesson"){
            for (XMLElement p:root.getChildren()){
                switch (p.getQName()){
                    case "title":

                        lesson.setTitle(p.getCharacters());
                        break;
                    case "content":

                        for (XMLElement c :p.getChildren()){

                            if (c.getQName() == "page"){
                                Page page = new Page();

                                for (XMLElement e: c.getChildren()){
                                    switch (e.getQName()){
                                        case "text":
                                            Text text = new Text(e.getCharacters());
                                            if (e.getAttribute("type")!= null){
                                                Log.println(Log.INFO,"LESSON_CONVERTER",e.getAttribute("type").value.replace(" ",""));
                                                switch (e.getAttribute("type").value.replace(" ","")){
                                                    case "title":
                                                        text.setType(Text.TYPE.TITLE);
                                                        break;
                                                    case "header":
                                                        text.setType(Text.TYPE.HEADER);
                                                    case "paragraph":
                                                        text.setType(Text.TYPE.PARAGRAPH);
                                                    default:
                                                        text.setType(Text.TYPE.PARAGRAPH);
                                                }
                                            }

                                            page.add(text);

                                    }
                                }
                                lesson.addPage(page);
                            }
                        }
                        break;

                }

            }
        }

        return lesson;
    }

    @Override
    public XMLElement convertToXMLElement(Object object) {
        return null;
    }
}
