package ru.project.task4.procedures;

import org.springframework.stereotype.Component;
import ru.project.task4.Model;

import java.util.ArrayList;


//Промежуточная компонента проверяет что тип приложения соответствует одному из: “web”,
// “mobile”. Если там записано что-либо иное, то оно преобразуется к виду “other:”+значение.

//"2" означает что запустится во вторую очередь
@Component("2")
public class ApplicationChecking  implements Actions{

    @Override
    public ArrayList<Model> apply(ArrayList<Model> modelArrayList) {
        for(int i=0;i<modelArrayList.size();i++)
          if((modelArrayList.get(i).application!="web")&&(modelArrayList.get(i).application!="mobile"))
            modelArrayList.get(i).application="other:"+modelArrayList.get(i).application;


        return modelArrayList;
    }



}
