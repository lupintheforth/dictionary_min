package clientterminal;

public class clientdemo {

	public static void main(String[] args) {
		
		
		
		clientdoing newrequest = new clientdoing("localhost",5001);
		String result = newrequest.sendRequest(1,"paper","something sofe");
		
		System.out.println(result);
		// TODO Auto-generated method stub

	}

}
