package by.epam.l10.homework;

/*С помощью регулярных выражений разбейте xml на узлы(nodes) 
и создайте список, их содержащий.

************************
для интереса немного усложним задачу - распарсим xml и положим данные в таблицу
(массив объектов food)

*/


import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HW01stringbuilder
{
	public static void main(String[] args)
	{
		//читаем xml из файла
		final String f = Xml.getfile();

		//определяем содержание класса и id с помощью групп
		final String regex_food = "(<food id=\")(\\d+)(\">)(.*?)(</food>)";

		//таблица food
		ArrayList<Food> foodlist = new ArrayList<Food>();

		Food food;
		//важно докинуть DOTALL что б точка включаля еще и все разделители типа \n
		Pattern p = Pattern.compile(regex_food, Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
		Matcher m = p.matcher(f);

		while (m.find())
		{
			//парсим поля по очереди а не за раз что бы исключить проблемы
			//с возможной перестановкой  
			String node = m.group(4);
			food = new Food();
			food.id = m.group(2);
			food.name = getvalue(node, "name");
			food.price = getvalue(node, "price");			
			food.description = getvalue(node, "description");
			food.calories = getvalue(node, "calories");

			foodlist.add(food);
		};
		
		//Печатаем таблицу food
		printarr(foodlist);
	}
	
	public static void printarr(ArrayList<Food> f)
	{
		for(Food i : f)
		{
			System.out.println("------------------------------");
			System.out.print("id : ");
			System.out.println(i.id);
			System.out.print("name : ");
			System.out.println(i.name);
			System.out.print("price : ");
			System.out.println(i.price);
			System.out.print("description : ");
			System.out.println(i.description);
			System.out.print("calories : ");
			System.out.println(i.calories);
		}
	}

	public static String getvalue(String source, String name)
	{
		String result = "";

		final String regex = "(<" + name + ">)(.*?)(</" + name + ">)";

		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE + Pattern.DOTALL);
		Matcher m = p.matcher(source);

		if (m.find())
		{
			result = m.group(2).trim();
		}

		return result;
	}
}

class Xml
{
	public static String getfile()
	{
		final String file = "d:\\Documents\\JAVA\\src\\by\\epam\\l10\\homework\\sample.xml";

		StringBuilder s = new StringBuilder();

		try (Reader r = new FileReader(file))
		{
			// читаем посимвольно, хотя надо б через буфер (но мы эту тиму типа еще не проходили)
			int c;

			while ((c = r.read()) != -1)
			{
				s.appendCodePoint(c);
			}
		}
		catch (IOException ex)
		{
			System.out.println(ex.getMessage());
		}

		return s.toString();

	}
}

class Food
{
	public String id;
	public String name;
	public String price;
	public String description;
	public String calories;
}
