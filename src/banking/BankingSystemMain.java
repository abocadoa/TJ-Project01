package banking;

import java.util.InputMismatchException;
import java.util.Scanner;


public class BankingSystemMain {
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입   금");
		System.out.println("3. 출   금");
		System.out.println("4. 계좌정보출력");
		System.out.println("5. 저장옵션");
		System.out.println("6. 프로그램종료");
		System.out.print("선택:");
	}
	public static void main(String[] args) {
		
		System.out.println("1차프로젝트(학원)");
		
		Scanner sc=new Scanner(System.in);
		AccountManager manager=new AccountManager();
		while(true) {
			try {
				showMenu();
				int choice=sc.nextInt();
				if(choice<1||choice>6) {
					NumErrException ex=new NumErrException();
					throw ex;
				}
				switch(choice) {
				case ICustomDefine.MAKE:
					System.out.println("***신규계좌개설***");
					System.out.println("1. 보통계좌");
					System.out.println("2. 신용신뢰계좌");
					System.out.print("선택:");
					sc.nextLine();
					int select=sc.nextInt();
					sc.nextLine();
					manager.makeAccount(select);
					break;
				case ICustomDefine.DEPOSIT:
					manager.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					manager.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					manager.showAccInfo();
					break;
				case ICustomDefine.AUTOSAVE:
					manager.autoSaveChoice(manager);
					break;
				case ICustomDefine.EXIT:
					System.out.println("프로그램종료");
					manager.saveAccInfo();
					return;
				}
			}
			catch(InputMismatchException e) {
				sc.nextLine();
				System.out.println("[오류발생]올바른 메뉴를 선택해주세요.");
			}
			catch(NumErrException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
}

class NumErrException extends Exception{
	public NumErrException() {
		super("[오류발생]올바른 숫자를 입력해주세요.");
	}
}