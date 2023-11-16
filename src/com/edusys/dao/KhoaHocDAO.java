/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import com.edusys.jdbc.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer>  {

    String insert_sql = "insert into KhoaHoc(MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao) values(?,?,?,?,?,?,?)";
    String update_sql = "update KhoaHoc set MaCD=?,HocPhi=?,ThoiLuong=?,NgayKG=?,GhiChu=?,MaNV=?,NgayTao=? where MaKH=?";
    String delete_sql = "delete from KhoaHoc where MaKH=?";
    String select_all_sql = "select * from KhoaHoc";
    String select_by_id_sql = "select * from KhoaHoc where MaKH=?";
    String select_by_cd_sql = "SELECT * FROM KhoaHoc WHERE MaCD=?";
//    String select_years_sql = "SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY year(NgayKG) DESC";

    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.update(insert_sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao()
        );
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.update(update_sql,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao(),
                entity.getMaKH()
        );
    }

    @Override
    public void delete(Integer key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySql(select_all_sql);
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getFloat("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));

                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public KhoaHoc selectById(Integer key) {
        List<KhoaHoc> list = selectBySql(select_by_id_sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Integer> selectYears() {
        String sql = "Select distinct year(NgayKG) Year from KhoaHoc order by year(NgayKG) desc";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<KhoaHoc> selectByChuyenDe(String macd) {
        
        return this.selectBySql(select_by_cd_sql, macd);
    }
}
