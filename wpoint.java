package Shana_B;

import java.sql.Date;

public class wpoint {//point of wifi
	private Date tim;
	private String id;
	private double lat;
	private double lon;
	private double alt;
	private String ssid;
	private String mac;
	private int frequncy;
	private int signal;
	
	public  wpoint()
	{
	}
	public  wpoint(Date d, String i, double la, double lo, double al, String ss, String m, int fre, int s )
	{
		tim=d;
		id=i;
		lat=la;
		lon=lo;
		alt=al;
		ssid=ss;
		mac=m;
		frequncy=fre;
		signal=s;
	}
	public wpoint(wpoint p1,wpoint p2)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)/(m1+m2));
		lon=((p1.lon*m1)+(p2.lon*m2)/(m1+m2));
		alt=((p1.alt*m1)+(p2.alt*m2)/(m1+m2));
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p[])
	{
		tim =p[0].tim;
		id=p[0].id;
		ssid=p[0].ssid;
		mac=p[0].mac;
		frequncy=p[0].frequncy;
		signal=-1;
		double la=0;
		double lo=0;
		double al=0;
		double m=0;
		for(int i=0;i<p.length;i++)
		{
			if(mac.equals(p[i].mac))
			{
				double m1=p[i].memutza();
				la+=p[i].lat*m1;
				lo+=p[i].lon*m1;
				al+=p[i].alt*m1;
				m+=m1;


			}
		}
		lat=la/m;
		lon=lo/m;
		alt=al/m;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3))/(m1+m2+m3);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3))/(m1+m2+m3);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3))/(m1+m2+m3);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3,wpoint p4)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		double m4=p4.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3)+(p4.lat*m4))/(m1+m2+m3+m4);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3)+(p4.lon*m4))/(m1+m2+m3+m4);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3)+(p4.alt*m4))/(m1+m2+m3+m4);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3,wpoint p4,wpoint p5)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		double m4=p4.memutza();
		double m5=p5.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3)+(p4.lat*m4)+(p5.lat*m5))/(m1+m2+m3+m4+m5);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3)+(p4.lon*m4)+(p5.lon*m5))/(m1+m2+m3+m4+m5);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3)+(p4.alt*m4)+(p5.alt*m5))/(m1+m2+m3+m4+m5);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3,wpoint p4,wpoint p5,wpoint p6)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		double m4=p4.memutza();
		double m5=p5.memutza();
		double m6=p6.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3)+(p4.lat*m4)+(p5.lat*m5)+(p6.lat*m6))/(m1+m2+m3+m4+m5+m6);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3)+(p4.lon*m4)+(p5.lon*m5)+(p6.lon*m6))/(m1+m2+m3+m4+m5+m6);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3)+(p4.alt*m4)+(p5.alt*m5)+(p6.alt*m6))/(m1+m2+m3+m4+m5+m6);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3,wpoint p4,wpoint p5,wpoint p6,wpoint p7)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		double m4=p4.memutza();
		double m5=p5.memutza();
		double m6=p6.memutza();
		double m7=p7.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3)+(p4.lat*m4)+(p5.lat*m5)+(p6.lat*m6)+(p7.lat*m7))/(m1+m2+m3+m4+m5+m6+m7);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3)+(p4.lon*m4)+(p5.lon*m5)+(p6.lon*m6)+(p7.lon*m7))/(m1+m2+m3+m4+m5+m6+m7);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3)+(p4.alt*m4)+(p5.alt*m5)+(p6.alt*m6)+(p7.alt*m7))/(m1+m2+m3+m4+m5+m6+m7);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3,wpoint p4,wpoint p5,wpoint p6,wpoint p7,wpoint p8)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		double m4=p4.memutza();
		double m5=p5.memutza();
		double m6=p6.memutza();
		double m7=p7.memutza();
		double m8=p8.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3)+(p4.lat*m4)+(p5.lat*m5)+(p6.lat*m6)+(p7.lat*m7)+(p8.lat*m8))/(m1+m2+m3+m4+m5+m6+m7+m8);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3)+(p4.lon*m4)+(p5.lon*m5)+(p6.lon*m6)+(p7.lon*m7)+(p8.lon*m8))/(m1+m2+m3+m4+m5+m6+m7+m8);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3)+(p4.alt*m4)+(p5.alt*m5)+(p6.alt*m6)+(p7.alt*m7)+(p8.alt*m8))/(m1+m2+m3+m4+m5+m6+m7+m8);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3,wpoint p4,wpoint p5,wpoint p6,wpoint p7,wpoint p8,wpoint p9)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		double m4=p4.memutza();
		double m5=p5.memutza();
		double m6=p6.memutza();
		double m7=p7.memutza();
		double m8=p8.memutza();
		double m9=p9.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3)+(p4.lat*m4)+(p5.lat*m5)+(p6.lat*m6)+(p7.lat*m7)+(p8.lat*m8)+(p9.lat*m9))/(m1+m2+m3+m4+m5+m6+m7+m8+m9);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3)+(p4.lon*m4)+(p5.lon*m5)+(p6.lon*m6)+(p7.lon*m7)+(p8.lon*m8)+(p9.lon*m9))/(m1+m2+m3+m4+m5+m6+m7+m8+m9);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3)+(p4.alt*m4)+(p5.alt*m5)+(p6.alt*m6)+(p7.alt*m7)+(p8.alt*m8)+(p9.alt*m9))/(m1+m2+m3+m4+m5+m6+m7+m8+m9);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public wpoint(wpoint p1,wpoint p2,wpoint p3,wpoint p4,wpoint p5,wpoint p6,wpoint p7,wpoint p8,wpoint p9,wpoint p10)
	{
		double m1=p1.memutza();
		double m2=p2.memutza();
		double m3=p3.memutza();
		double m4=p4.memutza();
		double m5=p5.memutza();
		double m6=p6.memutza();
		double m7=p7.memutza();
		double m8=p8.memutza();
		double m9=p9.memutza();
		double m10=p10.memutza();
		tim =p1.tim;
		id=p1.id;
		lat=((p1.lat*m1)+(p2.lat*m2)+(p3.lat*m3)+(p4.lat*m4)+(p5.lat*m5)+(p6.lat*m6)+(p7.lat*m7)+(p8.lat*m8)+(p9.lat*m9)+(p10.lat*m10))/(m1+m2+m3+m4+m5+m6+m7+m8+m9+m10);
		lon=((p1.lon*m1)+(p2.lon*m2)+(p3.lon*m3)+(p4.lon*m4)+(p5.lon*m5)+(p6.lon*m6)+(p7.lon*m7)+(p8.lon*m8)+(p9.lon*m9)+(p10.lon*m10))/(m1+m2+m3+m4+m5+m6+m7+m8+m9+m10);
		alt=((p1.alt*m1)+(p2.alt*m2)+(p3.alt*m3)+(p4.alt*m4)+(p5.alt*m5)+(p6.alt*m6)+(p7.alt*m7)+(p8.alt*m8)+(p9.alt*m9)+(p10.alt*m10))/(m1+m2+m3+m4+m5+m6+m7+m8+m9+m10);
		ssid=p1.ssid;
		mac=p1.mac;
		frequncy=p1.frequncy;
		signal=-1;
	}
	public double memutza ()
	{
		return 1/(signal*signal);
	}
}
