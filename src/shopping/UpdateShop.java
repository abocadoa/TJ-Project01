package shopping;

import java.sql.Types;

public class UpdateShop extends IConnectImpl{
	public UpdateShop() {
		super("education", "1234");
	}
	@Override
	public void execute() {
		try {
			csmt=con.prepareCall("{call ShopUpdateGoods(?, ?, ?, ?, ?)}");
			csmt.setString(1, scanValue("상품명"));
			csmt.setString(2, scanValue("가격"));
			csmt.setString(3, scanValue("제품코드"));
			csmt.setString(4, scanValue("수정할 상품의 일련번호"));
			csmt.registerOutParameter(5, Types.VARCHAR);
			csmt.execute();
			System.out.println("레코드 수정 결과(1 혹은 0");
			System.out.println(csmt.getString(5));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		new UpdateShop().execute();
	}

}
