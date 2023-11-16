/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.entity.NhanVien;
import com.edusys.jdbc.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    String insert_sql = "insert into NguoiHoc(MaNH,HoTen,GioiTinh,NgaySinh,DienThoai,Email,GhiChu,MaNV,NgayDK) values(?,?,?,?,?,?,?,?,?)";
    String update_sql = "update NguoiHoc set HoTen=?,GioiTinh=?,NgaySinh=?,DienThoai=?,Email=?,GhiChu=?,MaNV=?,NgayDK=? where MaNH=?";
    String delete_sql = "delete from NguoiHoc where MaNH=?";
    String select_all_sql = "select * from NguoiHoc";
    String select_by_id_sql = "select * from NguoiHoc where MaNH=?";

    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.update(insert_sql,
                entity.getMaNH(),
                entity.getTenNH(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK()
        );
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.update(update_sql,
                entity.getTenNH(),
                entity.isGioiTinh(),
                entity.getNgaySinh(),
                entity.getSdt(),
                entity.getEmail(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayDK(),
                entity.getMaNH()
        );
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectBySql(select_all_sql);
    }

    @Override
    public NguoiHoc selectById(String key) {
        List<NguoiHoc> list = selectBySql(select_by_id_sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc entity = new NguoiHoc();                
                entity.setMaNH(rs.getString("MaNH"));
                entity.setTenNH(rs.getString("HoTen"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setSdt(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
    public List<NguoiHoc> selectNotInCourse(int makh,String keyword){
        String sql="SELECT * FROM NguoiHoc " 
                +"WHERE HoTen LIKE ? AND "
                +"MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return this.selectBySql(sql, "%"+keyword+"%",makh);
    }
}
