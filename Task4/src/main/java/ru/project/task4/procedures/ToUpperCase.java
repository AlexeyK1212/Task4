package ru.project.task4.procedures;

import org.springframework.stereotype.Component;
import ru.project.task4.LogTransformation;
import ru.project.task4.Model;

import java.util.ArrayList;


//Промежуточная компонента проверки данных исправляет ФИО

//"1"  аннотации Component означает что запустится во 1-ю очередь
@Component("1")
@LogTransformation("LogFileName.txt") //Будет лог по этой компоненте
public class ToUpperCase implements Actions{
    @Override
    public ArrayList<Model> apply(ArrayList<Model> modelArrayList) {
        for(int i=0;i<modelArrayList.size();i++)
        {
            String[] words = modelArrayList.get(i).fio.split(" ");
            for(int j=0;j<words.length;j++) {
                if (words[j].length() ==1)
                    words[j] = words[j].substring(0, 1).toUpperCase();
                if (words[j].length() >1)
                    words[j] = words[j].substring(0, 1).toUpperCase() + words[j].substring(1);
            }
            modelArrayList.get(i).fio=String.join(" ", words);
        }

        return modelArrayList;
    }
}
