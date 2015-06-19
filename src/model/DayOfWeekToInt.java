package model;

public class DayOfWeekToInt {
	private int intdayofweek;

	public DayOfWeekToInt(){

	}

	public int getIntDayOfWeek(String dayofweek){
		if(dayofweek.equals("SUNDAY")){
			this.intdayofweek=1;
		}else if(dayofweek.equals("MONDAY")){
			this.intdayofweek=2;
		}else if(dayofweek.equals("TUESDAY")){
			this.intdayofweek=3;
		}else if(dayofweek.equals("WEDNESDAY")){
			this.intdayofweek=4;
		}else if(dayofweek.equals("THURSDAY")){
			this.intdayofweek=5;
		}else if(dayofweek.equals("FRIDAY")){
			this.intdayofweek=6;
		}else if(dayofweek.equals("SATURDAY")){
			this.intdayofweek=7;
		}

		return intdayofweek;
	}
}