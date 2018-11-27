package system;

import java.time.LocalDateTime;

public class DateAndTime {
 
	private int day, month, year, hour, minute;
	private LocalDateTime dateNtime;	
	
	public DateAndTime(String date, String time)
	{		
		String[] d = date.split("/");
		day = Integer.parseInt(d[0]);
		month = Integer.parseInt(d[1]);
		year = Integer.parseInt(d[2]);
		
		String[] h = time.split(":");
		hour = Integer.parseInt(h[0]);
		minute = Integer.parseInt(h[1]);
		
		dateNtime = LocalDateTime.of(year, month, day, hour, minute);
	}
	
	public int getDay(){
		return day;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
	public int getHour(){
		return hour;
	}
	public int getMinute(){
		return minute;
	}
	public LocalDateTime getDateNtime(){
		return dateNtime;
	}
	
}
