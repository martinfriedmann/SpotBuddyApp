package edu.umiami.ece513.project.vo;

public interface PersonDeclarations {
	
	public int insertPerson(Person p);
	public Person getPerson(String username, String pwd);
	public int insertComment(Comments c);
	public String getComments(String username);
	public boolean checkPerson(String username);
}
