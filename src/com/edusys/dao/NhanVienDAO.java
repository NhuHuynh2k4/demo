/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import java.util.List;
import com.edusys.entity.NhanVien;
import com.edusys.jdbc.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
/*MaNV nvarchar(20) primary key,
	MatKhau nvarchar(50) not null,
	HoTen nvarchar(50) not null,
	VaiTro bit not null*/
    String insert_sql = "insert into NhanVien(MaNV,MatKhau,HoTen,VaiTro) values(?,?,?,?)";
    String update_sql = "update NhanVien set MatKhau=?,HoTen=?,VaiTro=? where MaNV=?";
    String delete_sql = "delete from NhanVien where MaNV=?";
    String select_all_sql = "select * from NhanVien";
    String select_by_id_sql = "select * from NhanVien where MaNV=?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(insert_sql, 
                entity.getMaNV(),
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getVaiTro()
        );
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(update_sql, 
                entity.getMatKhau(),
                entity.getHoTen(),
                entity.getVaiTro(),
                entity.getMaNV());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(select_all_sql);
    }

    @Override
    public NhanVien selectById(String key) {
        List<NhanVien> list = selectBySql(select_by_id_sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
