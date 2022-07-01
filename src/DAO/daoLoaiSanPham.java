/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.LoaiSanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author longn
 */
public class daoLoaiSanPham {

    private static daoLoaiSanPham instance;

    public static daoLoaiSanPham getInstance() {
        if (instance == null) {
            instance = new daoLoaiSanPham();
        }
        return instance;
    }

    public daoLoaiSanPham() {
    }

    //Lấy ra danh sách thông tin từ bảng loại sản phẩm
    public ArrayList<LoaiSanPham> getListLoaiSanPham() {
        ArrayList<LoaiSanPham> result = new ArrayList<>();
        String query = "select * from Loai_sp";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                result.add(new LoaiSanPham(rs.getInt("id_loai_sp"), rs.getString("ten_loai_sp"), rs.getString("dvt"), rs.getInt("id_exist"), rs.getInt("id_khu_vuc")));
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }

    //Lấy ra 1 loại sản phẩm từ id
    public LoaiSanPham getLoaiSanPham(int id_loai_sp) {
        LoaiSanPham result = null;
        String query = "select *from Loai_sp where id_loai_sp = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(id_loai_sp);
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = (new LoaiSanPham(rs.getInt("id_loai_sp"), rs.getString("ten_loai_sp"), rs.getString("dvt"), rs.getInt("id_exist"), rs.getInt("id_khu_vuc")));
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }

    //Lấy ra loại sản phẩm từ tên loại sản phẩm
    public LoaiSanPham getIDLoaiSanPham(String ten_loai_sp) {
        LoaiSanPham result = null;
        String query = "select *from Loai_sp where ten_loai_sp = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(ten_loai_sp);
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = (new LoaiSanPham(rs.getInt("id_loai_sp"), rs.getString("ten_loai_sp"), rs.getString("dvt"), rs.getInt("id_exist"), rs.getInt("id_khu_vuc")));
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }

    //Thêm loại sản phẩm mới
    public void ThemLoaiSanPham(String ten, String dvt, String khuvuc, int id_nv) {
        
        
        String query = "INSERT INTO `loai_sp`(`ten_loai_sp`, `dvt`, `id_exist`, `id_khu_vuc`) VALUES ('" + ten + "','" + dvt + "',1," +")";
        try {
            DataProvider.getIntance().open();
            DataProvider.getIntance().excuteQuery(query);
            DataProvider.getIntance().close();
            JOptionPane.showMessageDialog(null, "Đã thêm loại sản phẩm " + ten + " thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm " + ten + " Thất bại", "Thông báo", 1);
        }
    }
    //Sửa thông tin loại sản phẩm

    public boolean UpdateLoaiSanPham(
            int IdLoaiSanPham,
            String Ten,
            String TenKhuVuc,
            String Dvt,
            int id_nv) {
        
        if ("".equals(Ten) || "".equals(TenKhuVuc) || "".equals(Dvt)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String query = "UPDATE `Loai_sp` SET `ten_loai_sp`='" + Ten + "',`dvt`='" + Dvt + "' WHERE `id_loai_sp`=" + IdLoaiSanPham;
        //System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        DataProvider.getIntance().open();
        DataProvider.getIntance().excuteUpdate(query, arr);
        DataProvider.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin loại sản phẩm thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
}
