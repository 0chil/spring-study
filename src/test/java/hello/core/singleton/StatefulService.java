package hello.core.singleton;

public class StatefulService {

	private int price; //상태 유지 필드(stateful)

	public void order(String name, int price) {
		System.out.println("name = " + name + " price = " + price);
		this.price = price; // 특정 클라이언트가 객체 값을 변경하고 있음.
	}

	public int orderFix(String name, int price) {
		System.out.println("name = " + name + " price = " + price);
		return price; // 파라미터 사용
	}

	public int getPrice() {
		return price;
	}
}
