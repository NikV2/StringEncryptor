package me.nik.stringencryptor.gui;

import me.nik.stringencryptor.utils.CryptUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private JTextField key;
    private JTextField text;
    private JButton encrypt;

    public void build() {
        final JFrame frame = new JFrame();
        frame.setSize(350, 200);

        this.key = new JTextField(50);
        this.key.setBounds(10, 20, 80, 20);

        final JLabel keyLabel = new JLabel("Secret Key");
        keyLabel.setVisible(true);

        this.text = new JTextField(50);
        this.text.setBounds(10, 20, 80, 20);

        final JLabel textLabel = new JLabel("String to Encrypt - Decrypt");
        textLabel.setVisible(true);

        this.encrypt = new JButton("Encrypt");
        final JButton decrypt = new JButton("Decrypt");

        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(this.encrypt);
        panel.add(decrypt);
        panel.add(textLabel);
        panel.add(this.text);
        panel.add(keyLabel);
        panel.add(this.key);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("String Encryptor");
        frame.pack();
        frame.setVisible(true);

        this.encrypt.addActionListener(this);
        decrypt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String str = text.getText();

        final byte[] key = this.key.getText().getBytes();

        if (e.getSource() == encrypt) {
            text.setText(CryptUtils.encrypt(str, key));
        } else text.setText(CryptUtils.decrypt(str, key));
    }
}