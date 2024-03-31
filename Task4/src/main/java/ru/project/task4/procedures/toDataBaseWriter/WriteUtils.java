package ru.project.task4.procedures.toDataBaseWriter;

import org.springframework.stereotype.Component;
import ru.project.task4.LogTransformation;
import ru.project.task4.Model;


import java.util.ArrayList;
import java.util.List;

@LogTransformation("LogFileName.log")
@Component
public class WriteUtils {// implements CommandLineRunner {



    private final WriteService writeService;

    public WriteUtils (WriteService writeService) {//незабываем конструктор для внедрения
        this. writeService = writeService;
    }

    //@Override
    public void WriteOne(Model model) throws Exception {
        LoginEntity loginEntity1 = new LoginEntity();
        UserEntity userEntity1 = new UserEntity();

        userEntity1.setFio(model.fio);
        userEntity1.setUserName(model.login);

        loginEntity1.setUser(userEntity1);
        loginEntity1.setApplication(model.application);
        loginEntity1.setAccessDate(model.access_date);
        writeService.saveUser(userEntity1);
        writeService.saveLogin(loginEntity1);
    }

    public void WriteFromArrayList(ArrayList<Model> arrayList) throws Exception {
        for(int i=0;i<arrayList.size();i++)
            this.WriteOne(arrayList.get(i));
    }


    public ArrayList<Model> GetAll() throws Exception {
        ArrayList<Model> res =new ArrayList();


        List<LoginEntity> all = writeService.getAllLogin();

//и выводим что получилось

        for (LoginEntity entity : all) {
            Model md = new Model();
            md.fio=entity.getUser().getFio();
            md.login=entity.getUser().getUserName();
            md.access_date=entity.getAccessDate();
            md.application=entity.getApplication();
            res.add(md);
            //System.out.println(entity.getAccessDate()+" "+entity.getApplication()
            //+" "+entity.getUser().getUserName()+" "+entity.getUser().getFio());

        }
        return res;
    }
}