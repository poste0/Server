package database;

/**
 * Created by Сергей on 21.09.2017.
 */
public class Employee {
    private int id;
    private String name;
    private String job;
    private int mgr;
    private int sal;
    private int comm;
    private int deptno;
    private String date;
    public Employee(int id,String name,String job,int mgr,String date,int sal,int comm,int deptno) throws Exception {
        if(getCount(id)>4||name.length()>10||job.length()>9
                ||getCount(mgr)>4||getCount(sal)>7||getCount(comm)>7||getCount(deptno)>2) throw new Exception("Ошибка формата данных");
        this.id=id;
        this.name=name;
        this.job=job;
        this.mgr=mgr;
        this.sal=sal;
        this.comm=comm;
        this.date=date;
        this.deptno=deptno;
    }
    private int getCount(int id){
        return (int) Math.ceil(Math.log10(id));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getMgr() {
        return mgr;
    }

    public int getSal() {
        return sal;
    }

    public int getComm() {
        return comm;
    }

    public int getDeptno() {
        return deptno;
    }

    public String getDate() {
        return date;
    }

}
