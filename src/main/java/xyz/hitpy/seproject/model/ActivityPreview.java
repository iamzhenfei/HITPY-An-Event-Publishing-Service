package xyz.hitpy.seproject.model;

public class ActivityPreview {
			private int aid;
			private String name;
			private String time;
			private String location;
			private String username;
			public ActivityPreview() {
				aid = 0;
				name = null;
				time = null;
				location = null;
				username = null;
			}
			public ActivityPreview(int aid, String name, String time, String location, String username) {
				this.aid = aid;
				this.name = name;
				this.time = time;
				this.username = username;
				this.location = location;
			}
			public int getAid() {
				return aid;
			}
			public String getName() {
				return name;
			}
			public String getTime() {
				return time;
			}
			public String getLocation() {
				return location;
			}
			public String getUsername() {
				return username;
			}
			public void setAid(int aid) {
				this.aid = aid;
			}
			public void setName(String name) { 
				this.name = name;
			}
			public void setTime(String time) {
				this.time = time;
			}
			public void setLocation(String location) {
				this.location = location;
			}
			public void setUsername(String username) {
				this.username = username;
			}
}
