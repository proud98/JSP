package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import data.dto.CartDto;
import data.dto.MemberDto;
import data.dto.ShopDto;
import mysql.db.DbConnect;

public class ShopDao {
	
	DbConnect db=new DbConnect();
	
	//insert
	public void insertShop(ShopDto dto) {
		
		Connection conn=db.getConnection();
		PreparedStatement pstmt=null;
		
		String sql="insert into shop (category,sangpum,photo,price,ipgoday) values (?,?,?,?,?)";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getCategory());
			pstmt.setString(2, dto.getSangpum());
			pstmt.setString(3, dto.getPhoto());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setString(5, dto.getIpgoday());
			
			pstmt.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	//select
	 public List<ShopDto> getAllSangpum(){
		 
		 List<ShopDto> list=new Vector<>();
		 
		 Connection conn=db.getConnection();
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 
		 String sql="select * from shop order by shopnum desc";
		 
		 try {
			 
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				ShopDto dto=new ShopDto();
				
				dto.setShopnum(rs.getString("shopnum"));
				dto.setSangpum(rs.getString("sangpum"));
				dto.setCategory(rs.getString("category"));
				dto.setPhoto(rs.getString("photo"));
				dto.setPrice(rs.getInt("price"));
				dto.setIpgoday(rs.getString("ipgoday"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(rs, pstmt, conn);
		}
				 
				 
		return list;
	 }
	 
	 //shopnum에 dto얻기
	 public ShopDto getShopData(String shopnum) {
		 
		 ShopDto dto=new ShopDto();
		 
		 Connection conn=db.getConnection();
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 
		 String sql="select * from shop where shopnum=?";
		 
		 try {
			 
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, shopnum);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setShopnum(rs.getString("shopnum"));
				dto.setSangpum(rs.getString("sangpum"));
				dto.setCategory(rs.getString("category"));
				dto.setPhoto(rs.getString("photo"));
				dto.setPrice(rs.getInt("price"));
				dto.setIpgoday(rs.getString("ipgoday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(rs, pstmt, conn);
		}
		
		 return dto;
	 }
	 
	 //장바구니 insert
	 public void insertCart(CartDto dto) {
		 
		Connection conn=db.getConnection();
		PreparedStatement pstmt=null;
		
		String sql="insert into cart values (null,?,?,?,now())";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getShopnum());
			pstmt.setString(2, dto.getNum());
			pstmt.setInt(3, dto.getCnt());
			
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(pstmt, conn);
		}		
	 }
	 
	 //장바구니 출력
	 public List<HashMap<String, String>> getCartList(String id){
		 
		 List<HashMap<String, String>> list=new ArrayList<>();
		 
		 Connection conn=db.getConnection();
		 PreparedStatement pstmt=null;
		 ResultSet rs=null;
		 
		 String sql="select c.idx, s.sangpum, s.shopnum, s.photo, s.price, c.cnt, c.cartday"
		 		+ " from cart c,shop s, member m"
		 		+ " where c.shopnum=s.shopnum and c.num=m.num and m.id=?"; //조인한 select문에서 id값을 얻기 m.id=?
		 
		
		 try {
			pstmt=conn.prepareStatement(sql);
			 //바인딩
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				HashMap<String, String> map=new HashMap<>();
				
				map.put("idx", rs.getString("idx"));
				map.put("sangpum", rs.getString("sangpum"));
				map.put("shopnum", rs.getString("shopnum"));
				map.put("photo", rs.getString("photo"));
				map.put("price", rs.getString("price"));
				map.put("cnt", rs.getString("cnt"));
				map.put("cartday", rs.getString("cartday"));
				
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			db.dbClose(rs, pstmt, conn);
		}
		 
		 return list;
		 
		 
	 }
	 
}
