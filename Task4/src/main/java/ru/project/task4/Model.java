package ru.project.task4;


import java.sql.Timestamp;
import java.util.Date;

public class Model {
  public String login;
    public String fio;
    public Timestamp access_date;
    public String application;

@Override
public String toString()
{
  String str="";
  if (this.access_date!=null)
    return "{" +
          "login=" + this.login +
          ", fio=" + this.fio +
          ", access_date="+this.access_date.toString()+
          ", application='" + this.application +
          "}\n";
  else
    return "{" +
            "login=" + this.login +
            ", fio=" + this.fio +
            ", access_date=null"+
            ", application='" + this.application +
            "}\n";


}




}
