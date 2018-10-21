package database;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Сергей on 20.09.2017.
 */
public class OracleDatabase implements Database {

    /*Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","netCrackerUser","12345");
    Statement statement=connection.createStatement();
    ResultSet rs=statement.executeQuery("select ename from emp");
    String result="";
    while(rs.next()){
        System.out.println(rs.getString("ename"));
    }*/
    private Connection connection;
    private Statement statement;
    private PreparedStatement ps;

    public OracleDatabase(String user, String password, String port, String ip) throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        String sql = "jdbc:oracle:thin:@" + ip + ":" + port + ":XE";
        connection = DriverManager.getConnection(sql, user, password);
        statement = connection.createStatement();
    }


    @Override
    public Participant getInfo(int id) throws Exception {


            ResultSet rs = statement.executeQuery("select * from participants where id=" + String.valueOf(id));
            int id1;
            String fname, academicdegree, position, placeofwork, citizenship, bdate;
            Date date;
       /*while(rs.next()){
           result+=rs.getString("EMPNO"); result+=" ";
           result+=rs.getString("ENAME"); result+=" ";
           result+=rs.getString("JOB"); result+=" ";
           result+=rs.getString("MGR"); result+=" ";
           //result+=rs.getString("HIREDATE"); result+=" ";
           date=rs.getDate("HIREDATE");
           result+=date.toLocalDate().toString(); result+=" ";
           result+=rs.getString("SAL"); result+=" ";
           result+=rs.getString("COMM"); result+=" ";
           result+=rs.getString("DEPTNO"); result+=" ";
       }*/
            rs.next();
            Participant participant;
            id1 = Integer.parseInt(rs.getString("ID"));
            fname = rs.getString("FNAME");
            academicdegree = rs.getString("ACADEMICDEGREE");
            date = rs.getDate("BDATE");
            bdate = date.toLocalDate().toString();

            placeofwork = rs.getString("PLACEOFWORK");

            position = rs.getString("POSITION");

            citizenship = rs.getString("CITIZENSHIP");


            participant=new Participant(id1, fname, academicdegree, placeofwork, position, citizenship, bdate);
            return participant;



    }

    @Override
    public List<Papers> show() throws Exception {
        ResultSet rs = statement.executeQuery("select * from papers");
        int id1;
        String type,title,sectionname,sdate;
        List<Papers> papers=new ArrayList<>();
        while(rs.next()){
            id1=rs.getInt("ID");
            title=rs.getString("TITLE");
            type=rs.getString("TYPE");
            sectionname=rs.getString("SECTIONNAME");
            Date date=rs.getDate("SDATE");
            sdate=date.toLocalDate().toString();
            papers.add(new Papers(id1,title,type,sectionname,sdate));
        }
        return papers;
    }

    @Override
    public List<Participant> showParticipants() throws Exception {
        ResultSet rs = statement.executeQuery("select * from participants");
        int id1 = 0;
        String fname, academicdegree, position, placeofwork, citizenship, bdate;
        Date date;
       /*while(rs.next()){
           result+=rs.getString("EMPNO"); result+=" ";
           result+=rs.getString("ENAME"); result+=" ";
           result+=rs.getString("JOB"); result+=" ";
           result+=rs.getString("MGR"); result+=" ";
           //result+=rs.getString("HIREDATE"); result+=" ";
           date=rs.getDate("HIREDATE");
           result+=date.toLocalDate().toString(); result+=" ";
           result+=rs.getString("SAL"); result+=" ";
           result+=rs.getString("COMM"); result+=" ";
           result+=rs.getString("DEPTNO"); result+=" ";
       }*/
        List<Participant> participant=new ArrayList<>();
       while(rs.next()) {


           id1 = Integer.parseInt(rs.getString("ID"));
           fname = rs.getString("FNAME");
           academicdegree = rs.getString("ACADEMICDEGREE");
           date = rs.getDate("BDATE");
           bdate = date.toLocalDate().toString();

           placeofwork = rs.getString("PLACEOFWORK");

           position = rs.getString("POSITION");

           citizenship = rs.getString("CITIZENSHIP");


           participant.add(new Participant(id1, fname, academicdegree, placeofwork, position, citizenship, bdate));
       }
        return participant;
    }

    @Override
    public void add(Participant participant) throws SQLException {

        statement.executeUpdate(insert(participant));

        /*ps=connection.prepareStatement("insert into participants values (?,?,?,?,?,?,?);");
        ps.setInt(1,participant.getId());
        ps.setString(2,participant.getFname());
        ps.setString(3,participant.getAcademicdegree());
        ps.setString(4,participant.getPlaceOfWork());
        ps.setString(5,participant.getPosition());
        ps.setString(6,participant.getCitizenship());

        ps.setString(7,participant.getBdate());*/

    }

    @Override
    public void delete(int id) throws SQLException {
        statement.executeUpdate("delete from participants where id=" + String.valueOf(id));

    }

    @Override
    public List<Papers> getPapers(int id) throws Exception {

        ResultSet rs = statement.executeQuery("select * from papers where id=" + String.valueOf(id));
        int id1;
        String type,title,sectionname,sdate;
        List<Papers> papers=new ArrayList<>();
        while(rs.next()){
            id1=rs.getInt("ID");
            title=rs.getString("TITLE");
            type=rs.getString("TYPE");
            sectionname=rs.getString("SECTIONNAME");
            Date date=rs.getDate("SDATE");
            sdate=date.toLocalDate().toString();
            papers.add(new Papers(id1,title,type,sectionname,sdate));
        }
        return papers;
    }

    @Override
    public void deletePapers(int id) throws SQLException {
        statement.executeUpdate("delete from papers where id=" + String.valueOf(id));

    }

    @Override
    public void addPapers(Papers paper) throws SQLException {
        statement.executeUpdate(insert(paper));
    }

    private String insert(Participant participant) {
        String result = "insert into participants values(" + String.valueOf(participant.getId()) +
                ", '" + participant.getFname() + "' , '" + participant.getAcademicdegree() + "' , '" + participant.getPlaceOfWork() + "' ,'" + participant.getPosition() + "','" +
                participant.getCitizenship() + "' , TO_DATE('" + participant.getBdate() + "'))";
        return result;
    }
    private String insert(Papers paper){
        String result="insert into papers values("+String.valueOf(paper.getId())+",'"+
                paper.getTitle()+"','"+paper.getType()+"','"+paper.getSectionname()+"',TO_DATE('"
                +paper.getSdate()+"'))";
        return result;
    }

    public static void main(String[] args) throws Exception {
        OracleDatabase database = new OracleDatabase("SSO_6307", "12345", "1521", "localhost");
        System.out.println(database.getInfo(1002));
        database.add(new Participant(1234,"s","Ph.D","s"
        ,"s","s","20-AUG-1991"));
        System.out.println(database.getInfo(1234));

    }
}
