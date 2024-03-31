package ru.project.task4.procedures;

import org.springframework.stereotype.Component;
import ru.project.task4.Model;

import java.util.ArrayList;
import java.util.Iterator;

//Промежуточная компонента проверки даты проверяет её наличие.

//"3" означает что запустится во 3-ю очередь
@Component("3")
public class EmptyDateChecking  implements Actions {

    @Override
    public ArrayList<Model> apply(ArrayList<Model> modelArrayList) {
        Iterator<Model> modelArrayListIterator = modelArrayList.iterator();
        while (modelArrayListIterator.hasNext())
        {
            Model nextModel = modelArrayListIterator.next();
            if(nextModel.access_date==null)
                modelArrayListIterator.remove();
        }


        return modelArrayList;
    }


}
