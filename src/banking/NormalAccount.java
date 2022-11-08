package banking;

public class NormalAccount extends Account{
	public NormalAccount(String ano, String owner, int balance,
			int rate) {
		super(ano, owner, balance, rate);
	}
	
	@Override
	public void deposit(int deposit) {
		balance=balance+(balance*rate/100)+deposit;
	}
	
	public void showAccInfo() {
		super.showAccInfo();
		System.out.println("계좌번호:"+getAno());
		System.out.println("고객이름:"+getOwner());
		System.out.println("잔고:"+getBalance());
		System.out.println("기본이자%(정수형태로입력):"+getRate()+"%");
	}
}
