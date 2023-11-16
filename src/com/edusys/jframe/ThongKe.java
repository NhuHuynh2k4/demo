/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.edusys.jframe;

import com.edusys.utils.XImage;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.ThongKeDAO;
import com.edusys.entity.KhoaHoc;
import com.edusys.jdbc.JdbcHelper;
import com.edusys.utils.Auth;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class ThongKe extends javax.swing.JDialog {

    ThongKeDAO dao = new ThongKeDAO();
    KhoaHocDAO khdao = new KhoaHocDAO();


    /**
     * Creates new form ThongKe
     */
    public ThongKe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();

    }

    public ThongKe(java.awt.Frame parent, boolean modal, int index) {
        super(parent, modal);
        initComponents();
        this.setIconImage(XImage.getAppIcon());
        this.getContentPane().setBackground(Color.decode("#58B5E6"));
        setLocationRelativeTo(null);
        selectTab(index);
        init();

    }

    void init() {
        fillComboBoxKhoaHoc();
        fillTableBangDiem();
        fillTableLuongNguoiHoc();
        fillTableDiemChuyenDe();
        fillComboBoxNam();
        fillTableDoanhThu();
        if (!Auth.isManager()) {
            tabs.remove(3);
        }
    }

    public void selectTab(int index) {
        tabs.setSelectedIndex(index);
    }

    void fillComboBoxKhoaHoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoaHoc.getModel();
        model.removeAllElements();
        List<KhoaHoc> list = khdao.selectAll();
        for (KhoaHoc kh : list) {
            model.addElement(kh);
        }
    }
//

    String getXepLoai(double score) {
        if (score < 5) {
            return "Yếu";
        } else if (score < 6.5) {
            return "Trung Bình";
        } else if (score < 7.5) {
            return "Khá";
        } else if (score < 9) {
            return "Tốt";
        }
        return "Xuất Sắc";
    }

    void fillTableBangDiem() {
        DefaultTableModel model = (DefaultTableModel) tblBangDiem.getModel();
        model.setRowCount(0);
        // Check if the selected item in the cboKhoaHoc combo box is null
        if (cboKhoaHoc.getSelectedItem() == null) {
            return;
        }
        KhoaHoc kh = (KhoaHoc) cboKhoaHoc.getSelectedItem();
        List<Object[]> list = dao.getBangDiem(kh.getMaKH());
        for (Object[] row : list) {
            double score = (double) row[2];
            model.addRow(new Object[]{row[0], row[1], score, getXepLoai(score)});
        }
    }

    void fillTableLuongNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiHoc.getModel();
        model.setRowCount(0);
        List<Object[]> list = dao.getLuongNguoiHoc();
        for (Object[] row : list) {
            model.addRow(row);
        }
    }

    void fillTableDiemChuyenDe() {
        DefaultTableModel model = (DefaultTableModel) tblDiemChuyenDe.getModel();
        model.setRowCount(0);
        List<Object[]> list = dao.getDiemChuyenDe();
        for (Object[] row : list) {
            model.addRow(new Object[]{
                row[0],
                row[1],
                row[2],
                row[3],
                String.format("%.1f", row[4])
            });
        }
    }

    void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<Integer> list = khdao.selectYears();
        for (Integer year : list) {
            model.addElement(year);
        }
    }

    void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        if (cboNam.getSelectedItem() == null) {
            return;
        }
        int nam = (Integer) cboNam.getSelectedItem();
        List<Object[]> list = dao.getDoanhThu(nam);
        for (Object[] row : list) {
            model.addRow(new Object[]{row[0], row[1], row[2],
                row[3],
                row[4],
                row[5],
                String.format("%.1f", row[6])
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        panBangDiem = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangDiem = new javax.swing.JTable();
        panNguoiHoc = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguoiHoc = new javax.swing.JTable();
        panDiemChuyenDe = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDiemChuyenDe = new javax.swing.JTable();
        panDoanhThu = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("EduSys - Tổng hợp & Thống kê");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TỔNG HỢP & THỐNG KÊ");

        jLabel2.setText("Khoá học");

        cboKhoaHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhoaHocActionPerformed(evt);
            }
        });

        tblBangDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NH", "Họ tên", "Điểm", "Xếp loại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblBangDiem);

        javax.swing.GroupLayout panBangDiemLayout = new javax.swing.GroupLayout(panBangDiem);
        panBangDiem.setLayout(panBangDiemLayout);
        panBangDiemLayout.setHorizontalGroup(
            panBangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBangDiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboKhoaHoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );
        panBangDiemLayout.setVerticalGroup(
            panBangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panBangDiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panBangDiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Bảng điểm", panBangDiem);

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Năm", "Số NH", "Đăng kí sớm nhất", "Đăng kí muộn nhất"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblNguoiHoc);

        javax.swing.GroupLayout panNguoiHocLayout = new javax.swing.GroupLayout(panNguoiHoc);
        panNguoiHoc.setLayout(panNguoiHocLayout);
        panNguoiHocLayout.setHorizontalGroup(
            panNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );
        panNguoiHocLayout.setVerticalGroup(
            panNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panNguoiHocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
        );

        tabs.addTab("Người học", panNguoiHoc);

        tblDiemChuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Chuyên đề", "Số lượng học viên", "Điểm thấp nhất", "Điểm cao nhất", "Điểm trung bình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblDiemChuyenDe);

        javax.swing.GroupLayout panDiemChuyenDeLayout = new javax.swing.GroupLayout(panDiemChuyenDe);
        panDiemChuyenDe.setLayout(panDiemChuyenDeLayout);
        panDiemChuyenDeLayout.setHorizontalGroup(
            panDiemChuyenDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
        );
        panDiemChuyenDeLayout.setVerticalGroup(
            panDiemChuyenDeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDiemChuyenDeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
        );

        tabs.addTab("Điểm chuyên đề", panDiemChuyenDe);

        jLabel3.setText("Năm");

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Chuyên đề", "Số khoá học", "Số học viên", "Doanh thu", "Học phí thấp nhất", "Học phí cao nhất", "Học phí trung bình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblDoanhThu);

        javax.swing.GroupLayout panDoanhThuLayout = new javax.swing.GroupLayout(panDoanhThu);
        panDoanhThu.setLayout(panDoanhThuLayout);
        panDoanhThuLayout.setHorizontalGroup(
            panDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panDoanhThuLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboNam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE))
                .addContainerGap())
        );
        panDoanhThuLayout.setVerticalGroup(
            panDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabs.addTab("Doanh thu", panDoanhThu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabs)
                        .addContainerGap())
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhoaHocActionPerformed
        // TODO add your handling code here:
        fillTableBangDiem();
    }//GEN-LAST:event_cboKhoaHocActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThu();
    }//GEN-LAST:event_cboNamActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongKe dialog = new ThongKe(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel panBangDiem;
    private javax.swing.JPanel panDiemChuyenDe;
    private javax.swing.JPanel panDoanhThu;
    private javax.swing.JPanel panNguoiHoc;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblBangDiem;
    private javax.swing.JTable tblDiemChuyenDe;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblNguoiHoc;
    // End of variables declaration//GEN-END:variables
}
