
public class GetInput {

	public GetInput(){
		
	}
	
	private void showPrompt(){
		System.out.println("Please enter a city to start from:");		
	}
	
	private void showError(String the_input){
		System.out.println(the_input + " is not in the grpah.");
	}
}
