package shopping;

import java.sql.SQLException;

public class SelectShop extends IConnectImpl{
	public SelectShop() {
		super("education", "1234");
	}
	
	@Override
	public void execute() {
		try {
			String scan=scanValue("찾는 이름");
			stmt=con.createStatement();
			
			String query="select g_idx, goods_name, goods_price, regidate, "
					+ " to_char(regidate, 'yyyy-mm-dd hh24:mi'), p_code "
					+ " from sh_goods where goods_name like'%"+scan+"%'";
			
			rs=stmt.executeQuery(query);
			while(rs.next()) {
				String g_idx=rs.getString(1);
				String goods_name=rs.getString(2);
				String goods_price=rs.getString(3);
				String regidate=rs.getString(4);
				String p_code=rs.getString(5);
				
				System.out.printf("%s %s %s %s %s\n",
						g_idx, goods_name, goods_price, regidate, p_code);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	public static void main(String[] args) {
		new SelectShop().execute();
	}

}
