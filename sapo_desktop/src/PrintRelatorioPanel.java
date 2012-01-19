/*
 * PrintRelatorioPanel.java
 *
 * Created on 3 de Dezembro de 2005, 15:20
 */
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import com.adobe.acrobat.Viewer;


/**
 *
 * @author  Anderson Zanardi de Freitas
 */

public class PrintRelatorioPanel extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5990704378503105930L;
	SAPO sapo;
        boolean config[];
        String coment;
	
	/** Creates new form PrintRelatorioPanel */
	public PrintRelatorioPanel(final SAPO sapo) {
            this.sapo = sapo;
            initComponents();
            setIconImage(new ImageIcon(SAPO.ICONPATH).getImage());
            showPDF();
            this.setExtendedState(Frame.MAXIMIZED_BOTH);
            setVisible(true);
	}
	
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jbtnSalvarPDF = new javax.swing.JButton();
        jbtnFechar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SAPO - Relat\u00f3rio");
        setFont(new java.awt.Font("Dialog", 0, 10));
        setIconImage(getIconImage());
        jbtnSalvarPDF.setText("Salvar");
        jbtnSalvarPDF.setToolTipText("Imprime o relat\u00f3rio");
        jbtnSalvarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalvarPDFActionPerformed(evt);
            }
        });

        jPanel1.add(jbtnSalvarPDF);

        jbtnFechar.setText("Fechar");
        jbtnFechar.setToolTipText("Cancela a impress\u00e3o");
        jbtnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFecharActionPerformed(evt);
            }
        });

        jPanel1.add(jbtnFechar);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jbtnSalvarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalvarPDFActionPerformed
        java.io.File in = new java.io.File(sapo.figDirPath + StartSAPO.sep + "temp.pdf");
	JFileChooser chooser = new JFileChooser();
        javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter(){
            public boolean accept(java.io.File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
            }
            public String getDescription() {
                return ".pdf files";
            }
        };
        
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            java.io.File out = chooser.getSelectedFile();
            if(!(out.getName().toLowerCase().endsWith(".pdf")))
                out = new java.io.File(out.toString()+".pdf");
            try{
                StartSAPO.copy(in,out);
                javax.swing.JOptionPane.showMessageDialog(this, "O relat�rio foi salvo com sucesso!");
                dispose();
            }
            catch(IOException e){
                javax.swing.JOptionPane.showMessageDialog(this, "Erro ao gravar o relat�tio", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jbtnSalvarPDFActionPerformed
    
    private void jbtnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFecharActionPerformed
        dispose();
    }//GEN-LAST:event_jbtnFecharActionPerformed
    
        public void showPDF(){
            try{
                final Viewer acrobat = new Viewer();
                if (acrobat != null) {
			acrobat.deactivate();
		    }
                FileInputStream in = new FileInputStream(sapo.figDirPath + StartSAPO.sep + "temp.pdf");
                acrobat.setDocumentInputStream(in);
                this.add(acrobat, java.awt.BorderLayout.CENTER);
        	acrobat.activate(); //WithoutBars();
            }
            catch(Exception erro){
                javax.swing.JOptionPane.showMessageDialog(this, "Erro na cria��o do relat�rio!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel jPanel1;
    public javax.swing.JButton jbtnFechar;
    public javax.swing.JButton jbtnSalvarPDF;
    // End of variables declaration//GEN-END:variables
	
}