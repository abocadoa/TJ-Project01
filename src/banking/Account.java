package banking;

import java.io.Serializable;
import java.util.HashSet;

public abstract class Account implements Serializable{
	public String ano;
	public String owner;
	public int balance;
	public int rate;
	public String grade;
	
	public Account(HashSet<Account> accHashSet) {
		
	}
	
	public Account(String ano, String owner, int balance, int rate) {
		this.ano = ano;
		this.owner = owner;
		this.balance = balance;
		this.rate = rate;
	}
	public void showAccInfo() {
		
	}
	public void deposit(int deposit) {
		
	}
	
	@Override
	public int hashCode() {
		int idHCode=this.ano.hashCode();
		return idHCode;
	}
	@Override
	public boolean equals(Object obj) {
		Account account=(Account)obj;
		if(this.ano.equals(account.ano)) {
			return true;
		}
		else
			return false;
	}
	
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
