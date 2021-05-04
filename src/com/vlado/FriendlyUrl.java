package com.vlado;

import java.util.ArrayList;
import java.util.List;

public class FriendlyUrl {
	
	public static String createFriendlyUrl(String input) {
			
			char[] inputCharArray = input.toCharArray();
			List<Character> output = new ArrayList<>();
			
			for(char c : inputCharArray) {	
				if(Character.compare(c, ' ') == 0
						||(Character.compare(c, ':') == 0)
						||(Character.compare(c, '´') == 0)
						||(Character.compare(c, ',') == 0)
						||(Character.compare(c, '\'') == 0)
						||(Character.compare(c, '.') == 0)
						||(Character.compare(c, '(') == 0)
						||(Character.compare(c, ')') == 0))			
					output.add('-');
				else
					output.add(c);
			}
			
			char[] outputChar = new char[output.size()];
			for(int i=0; i<output.size(); i++) {
				outputChar[i] = output.get(i);
			}
			
			return new String(outputChar);
		}

}
