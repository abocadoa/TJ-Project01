package banking;

public class HighCreditAccount extends Account{
	public HighCreditAccount(String ano, String owner, int balance,
			int rate, String grade) {
		super(ano, owner, balance, rate);
		this.grade=grade;
	}
	@Override
	public void deposit(int deposit) {
		if(grade.equalsIgnoreCase("A"))
			balance=balance+(balance*rate/100)+(balance*ICustomDefine.A/100)+deposit;
		else if(grade.equalsIgnoreCase("B"))
			balance=balance+(balance*rate/100)+(balance*ICustomDefine.B/100)+deposit;
		else if(grade.equalsIgnoreCase("C"))
			balance=balance+(balance*rate/100)+(balance*ICustomDefine.C/100)+deposit;
	}
	@Override
	public void showAccInfo() {
		System.out.println("계좌번호:"+getAno());
		System.out.println("고객이름:"+getOwner());
		System.out.println("잔고:"+getBalance());
		System.out.println("기본이자%(정수형태로입력):"+getRate()+"%");
		System.out.println("신용등급(A, B, C등급):"+grade);
	}
}
