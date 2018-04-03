package login.sumit.registration;

public interface PersonDB {
	
	public int insertPerson(Person p);
	public Person getPerson(String username, String pwd);
	public int insertComment(Comments c);
	public String getComments();
	public boolean checkPerson(String username);
}
