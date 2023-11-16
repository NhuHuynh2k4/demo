/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.HocVien;
import java.sql.ResultSet;
import java.util.List;
import com.edusys.jdbc.JdbcHelper;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class HocVienDAO extends EduSysDAO<HocVien, Integer> {

    public static ResultSet rs = null; // Trả về kết quả truy vấn
    public static String INSERT_SQL = "INSERT INTO HocVien(MaKH,MaNH,Diem) VALUES(?,?,?)";
    public static String UPDATE_SQL = "UPDATE HocVien SET MaKH=?,MaNH=?,Diem=? WHERE MaHV=?";
    public static String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
    public static String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV=?";
    public static String SELECT_BY_KH_SQL = "SELECT * FROM HocVien WHERE MaKH=?";

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(INSERT_SQL,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(UPDATE_SQL,
                entity.getMaKH(),
                entity.getMaNH(),
                entity.getDiem(),
                entity.getMaHV());
    }

    @Override
    public List<HocVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    HocVien entity = new HocVien();
                    entity.setMaHV(rs.getInt("MaHV"));
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setMaNH(rs.getString("MaNH"));
                    entity.setDiem(rs.getDouble("Diem"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<HocVien> selectByKhoahoc(int makh) {
        return this.selectBySql(SELECT_BY_KH_SQL, makh);
    }

    @Override
    public void delete(Integer key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public HocVien selectById(Integer key) {
        List<HocVien> list = selectBySql(SELECT_BY_ID_SQL, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public List<HocVien> selectByKhoaHoc(int maKH){
        String sql = "SELECT * FROM HocVien WHERE MaKH=?";
        return this.selectBySql(sql, maKH);
    }

}
