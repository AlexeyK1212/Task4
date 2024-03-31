package ru.project.task4;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.project.task4.procedures.Actions;
import ru.project.task4.procedures.FromLogsReader;
import ru.project.task4.procedures.toDataBaseWriter.WriteUtils;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@Service
public class Maker {

    @Autowired
    private FromLogsReader fromLogsReader;

    @Autowired
    private WriteUtils writeUtils;

    //В эту мапу @Autowired автоматом добавит
    // промежуточные компоненты реализующие интерфейс Actions
    //также в ней будут очередности для их запуска
    @Autowired
    public Map<String, Actions> actions;

    private ArrayList<Model> modelArrayList;
    private ArrayList<Actions> actionsArray=new ArrayList<>();;


    @PostConstruct
    public void Make() throws Exception {

        //Запускается чтение из файлов
        modelArrayList=fromLogsReader.read(System.getProperty("user.dir")+"/src/main/resources/inputFiles/");


        //Запускаем промежуточные компоненты в порядке очредности
        //Если они  помечены аннотацией @LogTransformation то логируем
        this.SortActionsByPriority(this.actions);
        for(int i=0;i<actionsArray.size();i++) {
            String stringForLog="";
            if(actionsArray.get(i).getClass().isAnnotationPresent(LogTransformation.class))
               stringForLog=(new java.util.Date()).toString()+"\n"+
                       actionsArray.get(i).getClass().getName().toString()+"\n"+
                       this.modelArrayList.toString()+"\n";

            actionsArray.get(i).apply(this.modelArrayList);

            if(actionsArray.get(i).getClass().isAnnotationPresent(LogTransformation.class)) {
                stringForLog = stringForLog +this.modelArrayList.toString()+"\n";
                Files.write(Paths.get(System.getProperty("user.dir")+"/src/main/resources/"+actionsArray.get(i).getClass().getAnnotation(LogTransformation.class).value()),stringForLog.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }

        }



//Записываем в базу данных
        writeUtils.WriteFromArrayList(modelArrayList);


    }

    //сортируем мапу с промежуточными компонентами по очередности
    //их зпуска
    private void SortActionsByPriority(Map<String, Actions> actions)
    {
        TreeMap<Integer,Actions> tm = new TreeMap();
        actions.forEach((k, v) -> tm.put(Integer.parseInt(k),v));

        for (Map.Entry<Integer, Actions> entry : tm.entrySet())
            actionsArray.add(entry.getValue());

    }






}
