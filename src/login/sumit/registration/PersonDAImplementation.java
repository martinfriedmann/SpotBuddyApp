package login.sumit.registration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonDAImplementation implements PersonDB {

	static Connection conn;
	static PreparedStatement ps;
	static Comments cm=new Comments();
	@Override
	public int insertPerson(Person p) {
		int status = 0;
		try {
			conn=MyConnectionProvider.getConnection();
			ps=conn.prepareStatement("insert into person values(?,?,?,?)");
			ps.setString(1, p.getUsername());
			ps.setString(2, p.getPwd());
			ps.setString(3, p.getFname());
			ps.setString(4, p.getLname());
			status=ps.executeUpdate();
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	@Override
	public boolean checkPerson(String username) {		
		int status = 0;
		try {
			conn=MyConnectionProvider.getConnection();
			ps=conn.prepareStatement("select * from person where username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			rs=ps.executeQuery();
			if(rs.next()) return true;
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public Person getPerson(String username, String pwd) {
		Person p=new Person();
		try {
			conn=MyConnectionProvider.getConnection();
			ps=conn.prepareStatement("select * from person where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				cm.setUsername(username);
				p.setUsername(rs.getString(1));
				p.setPwd(rs.getString(2));
				p.setFname(rs.getString(3));
				p.setLname(rs.getString(4));
			}
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return p;
	}
	
	@Override
	public int insertComment(Comments c) {
		int status = 0;
		try{
			conn=MyConnectionProvider.getConnection();
			ps=conn.prepareStatement("select MAX(commentID) from comments where username=?");
			ps.setString(1, cm.getUsername());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) c.setCommentID(rs.getInt(1) + 1);
			else c.setCommentID(1);
			ps=conn.prepareStatement("insert into comments values(?,?,?)");
			ps.setInt(1, c.getCommentID());
			ps.setString(2, cm.getUsername());
			ps.setString(3, c.getComment());
			status=ps.executeUpdate();
			conn.close();			
		}catch (Exception e){
			System.out.println(e);
		}
		return status;
	}
	
	public String getComments() {
		Comments c=new Comments();
		String wholething="<br />";
		try {
			conn=MyConnectionProvider.getConnection();
			c.setUsername(cm.getUsername());
			ps=conn.prepareStatement("select commentID, comment from comments where username=? order by commentID desc");
			ps.setString(1, cm.getUsername());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				c.setCommentID(rs.getInt(1));
				c.setComment(rs.getString(2));
				wholething = "" + wholething + c.getCommentID() + " " + c.getComment() + "<br />";
 			}
			conn.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return wholething;
	}

}
