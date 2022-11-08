package shopping;
/*
 인터페이스에 선언되는
 	모든 변수는 public static final이 추가되어 정적상수가 된다.
 	모든 메소드는 public abstract가 추가되어 추상메소드가 된다.
 */
public interface IConnect {
	
	//멤버상수:오라클 드라이버명과 커넥션URL을 선언
	String ORACLE_DRIVER="oracle.jdbc.OracleDriver";
	String ORACLE_URL="jdbc:oracle:thin:@localhost:1521:xe";
	//멤버 추상 메소드
	void connect(String user, String pass);	//DB 연결
	void execute();	//쿼리 실행
	void close();	//자원 반납
	//사용자로부터 입력을 받기위해 정의
	String scanValue(String title);
}
