
package vista.tipodocumento;

import controlador.TipoDocumentoControlador;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.TipoDocumentoModelo;
import java.util.List;

public class JInternalFrameTipoDocumento extends javax.swing.JInternalFrame {

    public JInternalFrameTipoDocumento() {
        initComponents();
        this.setSize(new Dimension(700, 650));
        this.setTitle("Tipo Documento");
        this.getContentPane().setBackground(Color.GRAY);
        listarTipoDocumento();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textNombreTipoDocumento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textEstado = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTipoDocumento = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(textNombreTipoDocumento, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 260, 50));

        jLabel1.setText("Nombre Tipo Documento");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel2.setText("Descripción");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));
        getContentPane().add(textDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 260, 50));

        jLabel3.setText("Estado");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));
        getContentPane().add(textEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 260, 50));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 260, 50));

        TablaTipoDocumento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(TablaTipoDocumento);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 590, 280));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 260, 50));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 520, 260, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        TipoDocumentoControlador tdc = new TipoDocumentoControlador();
        TipoDocumentoModelo tdm = new TipoDocumentoModelo();
        
        tdm.setNombre(textNombreTipoDocumento.getText());
        tdm.setDescripcion(textDescripcion.getText());
        tdm.setEstado(Integer.parseInt(textEstado.getText()));
        String mensaje;
        int filaSeleccionada = TablaTipoDocumento.getSelectedRow();
        if(filaSeleccionada == -1){
            mensaje = tdc.crearTipoDocumento(tdm);
        }else{
            int idTipoDocumento = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
            tdm.setIdTipoDocumento(idTipoDocumento);
            mensaje = tdc.editarTipoDocumento(tdm);
        }
        JOptionPane.showMessageDialog(this, mensaje);
        limpiarFormulario();
        listarTipoDocumento();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSelecionada = TablaTipoDocumento.getSelectedRow();
        if(filaSelecionada == -1){
            JOptionPane.showMessageDialog(this, "Debes seleccionar una fila de la tabla");
            return;
        }
        int idTipoDocumento = Integer.parseInt(modeloTabla.getValueAt(filaSelecionada, 0).toString());
        TipoDocumentoControlador tdc = new TipoDocumentoControlador();
        String mensaje = tdc.eliminarTipoDocumento(idTipoDocumento);
        JOptionPane.showMessageDialog(this, mensaje);
        listarTipoDocumento();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int filaSelecionada = TablaTipoDocumento.getSelectedRow();
        if(filaSelecionada == -1){
            JOptionPane.showMessageDialog(this, "Debes seleccionar una fila de la tabla");
            return;
        }
        
        textNombreTipoDocumento.setText(modeloTabla.getValueAt(filaSelecionada, 1).toString());
        textDescripcion.setText(modeloTabla.getValueAt(filaSelecionada, 2).toString());
        textEstado.setText(modeloTabla.getValueAt(filaSelecionada, 3)+"");
        
    }//GEN-LAST:event_btnEditarActionPerformed

    public void limpiarFormulario(){
        textNombreTipoDocumento.setText("");
        textDescripcion.setText("");
        textEstado.setText("");
    }
    
    private DefaultTableModel modeloTabla;
    public void listarTipoDocumento(){
        modeloTabla = new DefaultTableModel(new String[]{"ID", "NOMBRE", "DESCRIPCIÓN","ESTADO"},0);
        TablaTipoDocumento.setModel(modeloTabla);
        
        modeloTabla.setRowCount(0);
        TipoDocumentoControlador tdc = new TipoDocumentoControlador();
        List<TipoDocumentoModelo> lista = tdc.ListarTipoDocumento();
        
        for(TipoDocumentoModelo tdm: lista){
            modeloTabla.addRow(new Object[]{
                tdm.getIdTipoDocumento(),
                tdm.getNombre(),
                tdm.getDescripcion(),
                tdm.getEstado()
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaTipoDocumento;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField textDescripcion;
    private javax.swing.JTextField textEstado;
    private javax.swing.JTextField textNombreTipoDocumento;
    // End of variables declaration//GEN-END:variables
}
