
package letrasrepetidas;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class JFrameVerifica extends javax.swing.JFrame {

    public JFrameVerifica() {
        initComponents();
        setTitle("Desafio 1: Verificador de Letras");
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerificar = new javax.swing.JButton();
        campoString = new javax.swing.JTextField();
        labelDigite = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnVerificar.setText("Verificar");
        btnVerificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarActionPerformed(evt);
            }
        });

        labelDigite.setText("Digite uma string:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(labelDigite, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerificar)
                    .addComponent(campoString, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDigite)
                    .addComponent(campoString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(btnVerificar)
                .addGap(85, 85, 85))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnVerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarActionPerformed

        String string = campoString.getText(); //resgata o texto do JTextField

        HashMap<Character, Integer> map = new HashMap<>(); //mapa para guardar caractere e total de ocorrências
        HashMap<Character, Integer> repetidas = new HashMap<>(); //mapa para guardar caracteres repetidos número par de vezes

            if(string.length() < 2) {
                try { //lança exceção caso o input for menor que 2
                    throw new TamanhoException(string);
                }
            
                catch (TamanhoException tamanhoException) { //captura e trata a exceção do tipo tamanhoException
                    JOptionPane.showMessageDialog(rootPane, "A string deve conter no mínimo 2 caracteres. Tente novamente.");
                    limpaJTxtField();
                    campoString.requestFocus();
                }
            }
            if(!somenteTexto(string)) {
                try { //lança exceção caso o input contenha caracteres inválidos
                    throw new InvalidoException(string);
                }
                catch (InvalidoException invalidoException ) { //captura e trata exceção do tipo invalidoException
                    JOptionPane.showMessageDialog(rootPane, "Contém caractere(s) inválido(s). Tente novamente.");
                    limpaJTxtField();
                    campoString.requestFocus();
                }
            }
        
        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i); //transforma o input em chars individuais
            if(map.containsKey(c)) { //se o caractere existe em map, soma-se 1 ao seu valor total
                map.put(c, map.get(c).intValue() + 1);
            }else{ //se o caractere não existe em map, atribui-se 1 ao seu valor total
                map.put(c, 1);
            }
        }

        if(string.length() >= 2 && somenteTexto(string)) { //caso as condições tenham sido atentidas
            //percorre todo o map
            for(Map.Entry<Character, Integer> entry : map.entrySet()){
                if(entry.getValue() % 2 == 0) { //avalia se o caractere é repetido um número par de vezes
                    repetidas.put(entry.getKey(), entry.getValue()); //grava o caractere repetido um número par de vezes
                                                                                                     //em um novo mapa
                }
            }
            if(repetidas.size() > 1 || repetidas.isEmpty(  )) { //avalia o tamanho do mapa ou se ele está vazio
                JOptionPane.showMessageDialog(rootPane, "--");
                limpaJTxtField();
                campoString.requestFocus();
            } else {
                JOptionPane.showMessageDialog(rootPane, repetidas.keySet()); //retorna o único caractere repetido um número
                limpaJTxtField();                                                                          //par de vezes
                campoString.requestFocus();
            }
        }
    }
    
    //método que define uma regra para o input
    private static boolean somenteTexto(String string) {
        return string.matches("[^\\dA-Z]+");
    }

    //método que trata a exceção lançada se houver caracteres inválidos
    public static class InvalidoException extends RuntimeException {

        public InvalidoException(String string) {
            super("A string contém caracteres não permitidos, como números ou letras maiúsculas.");
        }
    }

    //método que trata a exceção lançada se o input não tiver pelo menos 2 caracteres
    public static class TamanhoException extends RuntimeException {

        public TamanhoException(String string) {
            super("A string não possui tamanho mínimo de elementos.");
        }
    }//GEN-LAST:event_btnVerificarActionPerformed
    
    //método para limpar o campo após verificação
    public void limpaJTxtField() {
        campoString.setText(null);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameVerifica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVerificar;
    private javax.swing.JTextField campoString;
    private javax.swing.JLabel labelDigite;
    // End of variables declaration//GEN-END:variables
}
