package myshop.model;

import java.sql.Timestamp;

public class myshopDto {

		private String num;
		private String sangpum;
		private String photo;
		private int price;
		private String ipgoday;
		private Timestamp writeday;
		
		
		public String getNum() {
			return num;
		}
		public void setNum(String num) {
			this.num = num;
		}
		public String getSangpum() {
			return sangpum;
		}
		public void setSangpum(String sangpum) {
			this.sangpum = sangpum;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getIpgoday() {
			return ipgoday;
		}
		public void setIpgoday(String ipgoday) {
			this.ipgoday = ipgoday;
		}
		public Timestamp getWriteday() {
			return writeday;
		}
		public void setWriteday(Timestamp writeday) {
			this.writeday = writeday;
		}
		
}
