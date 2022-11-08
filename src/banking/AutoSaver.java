package banking;

public class AutoSaver extends Thread{
	String accThread;
	AccountManager mgr;
	public AutoSaver(String name, AccountManager mgr) {
		accThread=name;
		this.mgr=mgr;
	}
	@Override
	public void run() {
		while(true) {
			try {
				mgr.autoSaveFile(mgr);
				sleep(5000);
				System.out.println("자동 저장...(5초)");
			}
			catch(InterruptedException e) {
				System.out.println("자동 저장이 중지되었습니다.");
				break;
			}
			catch(Exception e) {
				System.out.println("자동 저장중 오류가 발생했습니다.");
			}
		}
	}
}
