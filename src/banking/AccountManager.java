package banking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;


public class AccountManager {
	Scanner sc=new Scanner(System.in);
//	private Account[] myAccount;
//	private int numOfAccount;
	HashSet<Account> accHashSet;
	public AccountManager() {
//		myAccount=new Account[num];
//		numOfAccount=0;
		accHashSet=new HashSet<Account>();
		//생성자 생성과 동시에 복원할 파일 읽어오기
		readAccInfo();
	}
	public void makeAccount(int select) {
		String ano, owner, grade;
		int balance, rate;
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호:");
		ano=sc.nextLine();
		System.out.print("고객이름:");
		owner=sc.nextLine();
		System.out.print("잔고:");
		balance=sc.nextInt();
		
		if(select==1) {
			System.out.print("기본이자%(정수형태로입력):");
			rate=sc.nextInt();
			sc.nextLine();
//			myAccount[numOfAccount++]=
			NormalAccount nomAcc=
					new NormalAccount(ano, owner, balance, rate);
			
			//중복계좌 체크
			boolean duplicateCheck=accHashSet.add(nomAcc);
			if(duplicateCheck==false) {
				System.out.println("이미 등록된 계좌번호입니다. 덮어쓸까요?(y/n)");
				String openAccount=sc.nextLine();
				if(openAccount.equalsIgnoreCase("y")) {
					accHashSet.remove(nomAcc);
					accHashSet.add(nomAcc);
					System.out.println("계좌 개설이 완료되었습니다.");
				}
				else if(openAccount.equalsIgnoreCase("n")) {
					return;
				}
			}
			
		}
		else if(select==2) {
			System.out.print("기본이자%(정수형태로입력):");
			rate=sc.nextInt();
			sc.nextLine();
			System.out.print("신용등급(A, B, C등급):");
			grade=sc.nextLine();
//			myAccount[numOfAccount++]=
			HighCreditAccount highAcc=
					new HighCreditAccount(ano, owner, balance, rate,
							grade);
			
			//중복계좌 체크
			boolean duplicateCheck=accHashSet.add(highAcc);
			if(duplicateCheck==false) {
				System.out.println("이미 등록된 계좌번호입니다. 덮어쓸까요?(y/n)");
				String openAccount=sc.nextLine();
				if(openAccount.equalsIgnoreCase("y")) {
					accHashSet.remove(highAcc);
					accHashSet.add(highAcc);
					System.out.println("계좌 개설이 완료되었습니다.");
				}
				else if(openAccount.equalsIgnoreCase("n")) {
					return;
				}
			}
			
		}
//		System.out.println("계좌개설이 완료되었습니다.");
	}
	public void depositMoney() {
		try {
			boolean findAccount=false;
			System.out.println("***입   금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.print("계좌번호:");
			String accountNo=sc.nextLine();
			System.out.print("입금액:");
			int deposit=sc.nextInt();
			sc.nextLine();
			
			if(deposit<=0) {
				NgtNumberException e=new NgtNumberException();
				throw e;
			}
			if(deposit%500!=0) {
				ErrMoneyException e=new ErrMoneyException();
				throw e;
			}
			
//			for(int i=0; i<numOfAccount; i++) {
//				if(accountNo.compareTo(myAccount[i].ano)==0) {
//					myAccount[i].deposit(deposit);
//					System.out.println("입금이 완료되었습니다.");
//					findAccount = true;				
//				}
//			}
			
			//이터레이터를 사용하여 계좌 보유여부 체크 및 입금
			Iterator<Account> itr=accHashSet.iterator();
			while(itr.hasNext()) {
				Account account=itr.next();
				if(accountNo.compareTo(account.ano)==0) {
					account.deposit(deposit);
					System.out.println("입금이 완료되었습니다.");
					findAccount=true;
				}
			}
			
			if(findAccount==false)
				System.out.println("등록되지 않은 계좌번호입니다.");
		}
		catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("[오류발생]문자는 입력할 수 없습니다.");
		}
		catch(NgtNumberException e) {
			System.out.println(e.getMessage());
		}
		catch(ErrMoneyException e) {
			System.out.println(e.getMessage());
		}
	}
	public void withdrawMoney() {
		try {
			boolean findAccount=false;
			System.out.println("***출   금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요");
			System.out.print("계좌번호:");
			String accountNo=sc.nextLine();
			System.out.print("출금액:");
			int withdraw=sc.nextInt();
			sc.nextLine();
			
			if(withdraw<0) {
				NgtNumberException e=new NgtNumberException();
				throw e;
			}
			if(withdraw%1000!=0) {
				ErrMoneyException e=new ErrMoneyException();
				throw e;
			}
			
			//계좌번호 동일 여부 체크
//			for(int i=0 ; i<numOfAccount ; i++) {
//				if(accountNo.compareTo(myAccount[i].ano)==0) {
//					if(myAccount[i].balance > withdraw) {
//						myAccount[i].balance-=withdraw;
			
			Iterator<Account> itr=accHashSet.iterator();
			while(itr.hasNext()) {
				Account account=itr.next();
				if(accountNo.compareTo(account.ano)==0) {
					if(account.balance>withdraw) {
						account.balance-=withdraw;
						System.out.println("출금이 완료되었습니다.");
						findAccount = true;						
					}
					//잔액이 부족한 경우
					else {
						System.out.println("[잔액부족]금액 전체를 출금할까요?");
						System.out.println("yes입력:전체 잔액 출금처리");
						System.out.println("no입력:출금 요청 취소");
						while(true) {
							String withdrawChoice=sc.nextLine();
							if(withdrawChoice.equalsIgnoreCase("yes")) {
								System.out.println("전체 잔액을 출금합니다.");
//								myAccount[i].balance-=myAccount[i].balance;
								account.balance-=account.balance;
								findAccount=true;
								break;
							}
							else if(withdrawChoice.equalsIgnoreCase("no")) {
								System.out.println("출금 요청이 취소되었습니다.");
								findAccount=true;
								break;
							}
						}
					}
				}
			}
			if(findAccount==false)
				System.out.println("등록되지 않은 계좌번호입니다.");
		}
		catch(InputMismatchException e) {
			sc.nextLine();
			System.out.println("[오류발생]문자는 입력할 수 없습니다.");
		}
		catch(NgtNumberException e) {
			System.out.println(e.getMessage());
		}
		catch(ErrMoneyException e) {
			System.out.println(e.getMessage());
		}
	}
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		System.out.println("-------------");
//		for(int i=0; i<numOfAccount; i++) {
//			myAccount[i].showAccInfo();
//		}
		
		for(Account acc:accHashSet) {
			acc.showAccInfo();
		}
		
		System.out.println("-------------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	//계좌정보 직렬화(프로그램 종료시 모든 계좌 객체를 파일로 저장)
	public void saveAccInfo() {
		try {
			ObjectOutputStream out=
					new ObjectOutputStream(
							new FileOutputStream
							("src/banking/AccountInfo.obj"));
			for(Account acc:accHashSet) {
				out.writeObject(acc);
			}
			out.close();
		}
		catch(Exception e) {
			System.out.println("계좌 정보 파일 저장시 예외 발생");
		}
	}
	
	//복원(역직렬화)을 위한 스트림 생성
	public void readAccInfo() {
		try {
			ObjectInputStream in=
					new ObjectInputStream(
							new FileInputStream
							("src/banking/AccountInfo.obj"));
			while(true) {
				Account acc=(Account)in.readObject();
				accHashSet.add(acc);
				if(acc==null)
					break;
			}
			in.close();
		}
		catch(Exception e) {
			System.out.println("더 이상 읽을 객체가 없습니다.");
		}
		System.out.println("계좌 정보 복원 완료");
	}
	
	public void autoSaveChoice(AccountManager mgr) {
		System.out.println("자동저장옵션선택");
		System.out.println("1. 자동저장on");
		System.out.println("2. 자동저장off");
		int autoselec=sc.nextInt();
		sc.nextLine();
		autoSave(autoselec, mgr);
	}
	AutoSaver as1;
	
	public void autoSave(int autoselec, AccountManager mgr) {
		if(autoselec==1) {
			if(Thread.activeCount()==2) {
				System.out.println("이미 자동저장이 실행중입니다.");
			}
			else {
				System.out.println("자동 저장을 실행합니다.");
				as1=new AutoSaver("ThreadName1", mgr);
				as1.setDaemon(true);
				as1.start();
			}
		}
		else if(autoselec==2) {
			as1.interrupt();
		}
	}
	
	public void autoSaveFile(AccountManager mgr) {
		try {
			PrintWriter out=new PrintWriter(
					new FileWriter("src/banking/AutoSaveAccount.txt"));
			for(Account acc:accHashSet) {
				if(acc instanceof HighCreditAccount) {
					out.printf("계좌번호:%s, 예금주:%s, 잔고:%d, 이율:%d, 신용등급:%s",
						((HighCreditAccount)acc).ano,
						((HighCreditAccount)acc).owner,
						((HighCreditAccount)acc).balance,
						((HighCreditAccount)acc).rate,
						((HighCreditAccount)acc).grade);
					out.println();
				}
				else if(acc instanceof NormalAccount) {
					out.printf("계좌번호:%s, 예금주:%s, 잔고:%d, 이율:%d",
						((NormalAccount)acc).ano,
						((NormalAccount)acc).owner,
						((NormalAccount)acc).balance,
						((NormalAccount)acc).rate);
					out.println();
				}
			}
			out.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

//0원 미만으로 입력한 경우
class NgtNumberException extends Exception{
	public NgtNumberException() {
		super("[오류발생]0원 이하는 입출금할수 없습니다.");
	}
}

//금액 단위가 올바르지 않은 경우
class ErrMoneyException extends Exception{
	public ErrMoneyException() {
		super("[오류발생]입금은 500원 단위, 출금은 1000원 단위만 가능합니다.");
	}
}
