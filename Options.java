public class Options {
	public int attempts = 10;
	public String code;

	private boolean a_flag;
	private boolean c_flag;
	private char first_flag;

	public Options parseInput(String[] args) {
		for (String arg: args) {
			if (arg.charAt(0) == '-') {
				for (int i = 1; i < arg.length(); i++) {
					switch (arg.charAt(i)) {
						case 'a': a_flag = true;
								  if (first_flag == '\0') {
									  first_flag = 'a';
								  } 
								  break;
						case 'c': this.c_flag = true;
								  if (first_flag == '\0') {
									  first_flag = 'c';
								  } 
								  break;
						case '-': break;
						default : throw new IllegalArgumentException("Invalid Flag");

					}
				}
			}
			else if (first_flag == 'c' && c_flag) {
				if (!validCode(arg)) {
					System.out.println("You have entered an invalid code.");
					System.out.println("Generating a valid code to commence the game...");
					System.out.println("- - - - - - - - - - - - - - -");
				}
				else {
					code = arg;
				}
				first_flag = 'a';
			}
			else if (first_flag == 'a' && a_flag) {
				attempts = Integer.parseInt(arg);
				first_flag = 'c';
			}
			else {
				throw new IllegalArgumentException("Invalid Argument");
			}
		}
		return this;
	}

	private boolean validCode(String code) {
		char[] ok_values = {'1', '2', '3', '4', '5', '6', '7'};
		if (code.length() != 4) {
			return false;
		}
		for (String s: code.split("")) {
			if (!findValInArray(ok_values, s.charAt(0))) {
				return false;
			}
		}
		return true;
	}

	private boolean findValInArray(char[] arr, char c) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == c) {
				arr[i] = '\0';
				return true;
			}
		}
		return false;
	}
}


