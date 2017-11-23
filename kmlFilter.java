package Shana_B;

import java.util.Scanner;

public  class kmlFilter {
	public static Scanner reader = new Scanner(System.in);
	public static String filt(String[][] kml)
	{
		System.out.println("if you dont want to filter enter '0'");
		System.out.println("if you want to filt according to place enter '1'");
		System.out.println("if you want to filt according to ID enter '2'");
		System.out.println("if you want to filt according to time enter '3'");
		try
		{
			switch (reader.nextInt())
			{
			case 0:
			{
				return writeKML.write(kml);
			}
			case 1:
			{
				System.out.println("enter from lat:");
				Double lat1=reader.nextDouble();
				System.out.println("enter to lat:");
				Double lat2=reader.nextDouble();
				System.out.println("enter from lon:");
				Double lon1=reader.nextDouble();
				System.out.println("enter to lon:");
				Double lon2=reader.nextDouble();
				String scv[][]=new String[kml.length][36];
				for(int i=1;i<kml.length;i++)
				{
					if(Double.parseDouble(kml[i][2])>=lat1&&
							Double.parseDouble(kml[i][2])<=lat2&&
							Double.parseDouble(kml[i][3])>=lon1&&
							Double.parseDouble(kml[i][3])<=lon2)
					{
						scv[i]=kml[i];
					}
				}
				return writeKML.write(scv);
			}
			case 2:
			{
				System.out.println("enter ID:");
				String id=reader.next();
				String scv[][]=new String[kml.length][36];
				for(int i=1;i<kml.length;i++)
				{
					if(kml[i][1].equals(id))
					{
						scv[i]=kml[i];
					}
				}
				return writeKML.write(scv);
			}
			case 3:
			{
				System.out.println("enter year:");
				int year=reader.nextInt();
				System.out.println("enter month:");
				int month=reader.nextInt();
				System.out.println("enter day:");
				int day=reader.nextInt();
				System.out.println("enter hour:");
				int hour=reader.nextInt();
				System.out.println("enter minutes:");
				int minutes=reader.nextInt();
				System.out.println("enter secont:");
				int secont=reader.nextInt();
				String date=year+"-"+month+"-"+day+" "+hour+":"+minutes+":"+secont;
				String scv[][]=new String[kml.length][36];
				for(int i=1;i<kml.length;i++)
				{
					if(kml[i][0].equals(date))
					{
						scv[i]=kml[i];
					}
				}
				return writeKML.write(scv);
			}
			}
			System.out.println("done");

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "";

	}
}
