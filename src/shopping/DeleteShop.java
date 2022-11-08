package shopping;

import java.sql.Types;

public class DeleteShop extends IConnectImpl{
	public DeleteShop() {
		super("education", "1234");
	}
	@Override
	public void execute() {
		try {
			csmt=con.prepareCall("{call ShopDeleteGoods(?, ?)}");
			csmt.setString(1, scanValue("삭제할 상품의 일련번호"));
			csmt.registerOutParameter(2, Types.VARCHAR);
			csmt.execute();
			System.out.println("레코드 삭제 결과(1 혹은 0");
			System.out.println(csmt.getString(2));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		new DeleteShop().execute();
	}

}
