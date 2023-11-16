/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.jdbc.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String> {

    String insert_sql = "insert into ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) values(?,?,?,?,?,?)";
    String update_sql = "update ChuyenDe set TenCD=?,HocPhi=?,ThoiLuong=?,Hinh=?,MoTa=? where MaCD=?";
    String delete_sql = "delete from ChuyenDe where MaCD=?";
    String select_all_sql = "select * from ChuyenDe";
    String select_by_id_sql = "select * from ChuyenDe where MaCD=?";

    @Override
    public void insert(ChuyenDe entity) {
        JdbcHelper.update(insert_sql,
                entity.getMaCD(),
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa()
        );
    }

    @Override
    public void update(ChuyenDe entity) {
        JdbcHelper.update(update_sql,
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa(),
                entity.getMaCD()
        );
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBySql(select_all_sql);
    }

    @Override
    public ChuyenDe selectById(String key) {
        List<ChuyenDe> list = selectBySql(select_by_id_sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                ChuyenDe entity = new ChuyenDe();
                entity.setMaCD(rs.getString("MaCD"));
                entity.setTenCD(rs.getString("TenCD"));
                entity.setHocPhi(rs.getFloat("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMoTa(rs.getString("MoTa"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
