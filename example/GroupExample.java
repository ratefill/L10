package by.epam.l10.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GroupExample
{
	public static void main(String[] args)
	{
		//System.out.println("a\tb");
		String text = "test a=\"1\" b=\"2\" c=\"3\" bar d=\"4\" e=\"5\"";
		System.out.println(text + "\n");
		
		Matcher m1 = Pattern.compile("([a-z]*)(([ \t]+[a-z]=\"[0-9]\")*)").matcher(text);
		//(последовательность букв)((сколько угодно табов, одна буква, =, "одна цифра") любые символы)
		//
		
		
		/*
		 * Matcher m1 = Pattern .compile(
		 * "([a-z]*)( +[a-z]=\"[0-9]\")( +[a-z]=\"[0-9]\")( +[a-z]=\"[0-9]\")")
		 * .matcher(text);
		 */
		while (m1.find())
		{
			System.out.println(m1.group());
			System.out.println(m1.group(1));
			Matcher m2 = Pattern.compile("([a-z])=\"([0-9])\"").matcher(m1.group(2));
			while (m2.find())
			{
				System.out.println(" " + m2.group(1) + " -> " + m2.group(2));
			}
		}
	}
}