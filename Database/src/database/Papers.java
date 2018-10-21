package database;

public class Papers  {
    private int id;
    private String title;
    private String type;
    private String sectionname;
    private String sdate;
    public Papers(int id,String title,String type,String sectionname,String sdate) throws Exception {
        if(title==null||sectionname==null||sdate==null) throw new Exception("Wrong input data");
        this.id=id;
        this.title=title;
        this.type=type;
        this.sectionname=sectionname;
        this.sdate=sdate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }
}
