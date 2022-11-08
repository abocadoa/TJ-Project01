package shopping;

import java.util.Scanner;

public class InsertShop extends IConnectImpl{
	public InsertShop() {
		super(ORACLE_DRIVER, "education", "1234");
	}
	
	@Override
	public void execute() {
		try {
			String query="INSERT INTO sh_goods "
					+ " VALUES(seq_total_idx.nextval, ?, ?, sysdate, ?)";
			psmt=con.prepareStatement(query);
			Scanner sc=new Scanner(System.in);
			System.out.print("상품명");
			String goods_name=sc.nextLine();
			System.out.print("상품가격");
			String goods_price=sc.nextLine();
			System.out.print("상품코드");
			String p_code=sc.nextLine();
			
			
			psmt.setString(1, goods_name);
			psmt.setString(2, goods_price);
			psmt.setString(3, p_code);
			int affected=psmt.executeUpdate();
			System.out.println(affected+"행이 입력되었습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
	@Override
	public String scanValue(String title) {
		Scanner sc=new Scanner(System.in);
		System.out.print(title+"을(를) 입력(exit->종료):");
		String inputStr=sc.nextLine();
		if("EXIT".equalsIgnoreCase(inputStr)) {
			System.out.println("프로그램을 종료합니다.");
			close();
			System.exit(0);
		}
		return inputStr;
	}
	public static void main(String[] args) {
		new InsertShop().execute();
	}

}
