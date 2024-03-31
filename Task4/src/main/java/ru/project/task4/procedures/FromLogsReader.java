package ru.project.task4.procedures;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import org.springframework.stereotype.Component;
import ru.project.task4.*;


//Сканирует файлы в заданной папке

@Component
public class FromLogsReader {

    //для записи строк из файлов
    public ArrayList<Model> lines = new ArrayList<>();

    //читаем строки из файлов
    public ArrayList<Model> read(String dirName) throws FileNotFoundException, ParseException {
        File Selected_Folder = new File(dirName);
        File[] list_of_files = Selected_Folder.listFiles();
        for (int i = 0; i < list_of_files.length; i++) {
            Scanner scanner = new Scanner(new File("" + list_of_files[i]));
            while (scanner.hasNextLine()) {
                lines.add(this.srtingToModel(scanner.nextLine(), new Model()));
            }
            scanner.close();
        }

        return lines;

    }


    //Преобразуем строки в объект Model
    private Model srtingToModel(String st, Model md) throws ParseException {
        String[] words = st.split("\\|");
        for (int i = 0; i < words.length; i++) {
            if (i == 0)
                md.login = words[0];
            if (i == 1)
                md.fio = words[1];
            if (i == 2) {
                try {
                    Date parsedDate = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(words[2]);
                    md.access_date = new Timestamp(parsedDate.getTime());
                }
                catch (Exception e)
                {
                    md.access_date =null;
                }
            }
            if (i == 3)
                md.application = words[3];


        }

       // System.out.println(md.fio);
        return md;

    }

}