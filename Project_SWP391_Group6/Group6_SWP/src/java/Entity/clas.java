/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Duy Anh
 */
public class clas {
    private int class_id;
    private int name;
    private Date date;

    public clas() {
    }

    public clas(int class_id, int name, Date date) {
        this.class_id = class_id;
        this.name = name;
        this.date = date;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "clas{" + "class_id=" + class_id + ", name=" + name + ", date=" + date + '}';
    }
    
    
}