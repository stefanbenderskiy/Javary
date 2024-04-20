package com.reflection.javary.lesson;

import android.util.Log;
import android.widget.ImageView;

import com.reflection.javary.lesson.elements.Image;
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
                                            break;
                                        case "img":
                                            Image image = new Image(e.getAttribute("src").value);
                                            if (e.getAttribute("shape")!= null){

                                                switch (e.getAttribute("shape").value.replace(" ","")){
                                                    case "rect":
                                                        image.setShape(Image.SHAPE.RECT);
                                                        break;
                                                    case "rounded":
                                                        image.setShape(Image.SHAPE.ROUNDED);
                                                    case "circular":
                                                        image.setShape(Image.SHAPE.CIRCLE);
                                                    default:
                                                        image.setShape(Image.SHAPE.RECT);
                                                }
                                            }

                                            page.add(image);
                                            break;

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
