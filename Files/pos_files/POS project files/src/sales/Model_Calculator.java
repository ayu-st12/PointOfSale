package sales;

public class Model_Calculator {
	
	public float calculate(float num1,float num2, String op){
		switch(op){
		case "+": 
			return (num1 + num2);
		case "-":
			return (num1 - num2);
		case "*":
			return (num1 * num2);	
		default:
			return 0;
		}		
	}

	public float validate(String s){
		try{
			float f=Float.parseFloat(s);
			return f;
		}catch(NumberFormatException e){
			//e.getMessage();
			System.out.println("Invalid Number! Amount truncated to zero!");
		}
		return 0;
	}
	
	public String backspace_func(String s){
		try{
		 return s.substring(0, s.length()-5);
		}catch(StringIndexOutOfBoundsException exp){
			exp.getMessage();
		}
		return "";
	}
}
