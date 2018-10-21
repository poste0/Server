package database;

import java.util.Objects;

public class Participant {
    private int id;
    private String academicdegree;
    private String fname;
    private String placeOfWork;
    private String position;
    private String citizenship;
    private String bdate;
    public Participant(int id,String fname,String academicdegree,String placeOfWork,String position,String citizenship,String bdate) throws Exception {
        if(Objects.isNull(id)||fname==null||placeOfWork==null||position==null||citizenship==null) throw new Exception("Incorrect input data");
        this.id=id;
        this.fname=fname;
        this.academicdegree=academicdegree;
        this.placeOfWork=placeOfWork;
        this.position=position;
        this.citizenship=citizenship;
        this.bdate=bdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcademicdegree() {
        return academicdegree;
    }

    public void setAcademicdegree(String academicdegree) {
        this.academicdegree = academicdegree;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }
}
