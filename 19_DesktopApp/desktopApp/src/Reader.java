import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reader extends JFrame implements ActionListener {
    JPanel panel1 = new JPanel();
    private JTextField surnameField = new JTextField(10);
    private JLabel surname = new JLabel("Фамилия");
    private JTextField nameField = new JTextField(10);
    private JLabel name = new JLabel("Имя");
    private JLabel patronymic = new JLabel("Отчество");
    private JTextField patronField = new JTextField(10);
    JTextField fullNameField = new JTextField();

    JButton button = new JButton("Collapse");

    public Reader() {
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        makePanel1();
        add(panel1);
        add(button);
        setVisible(true);
        setLocationRelativeTo(null);
        button.addActionListener(this);
    }

    private void makePanel1() {
        panel1.add(surname);
        panel1.add(surnameField);
        panel1.add(name);
        panel1.add(nameField);
        panel1.add(patronymic);
        panel1.add(patronField);
        panel1.repaint();
    }

    private void makePanel2(String s) {
        JLabel fullName = new JLabel("ФИО");
        fullNameField.setText(s);
        panel1.add(fullName);
        panel1.add(fullNameField);
        add(panel1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (button.getText().equals("Collapse")) {
                if (nameField.getText().isEmpty() ||
                        surnameField.getText().isEmpty() ||
                        patronField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Заполните поля");
                } else {
                    String fullName = surnameField.getText() + " "
                            + nameField.getText() + " "
                            + patronField.getText();
                    panel1.removeAll();
                    remove(button);
                    button = new JButton("Expand");
                    makePanel2(fullName);
                    add(button);
                    button.addActionListener(this);
                    repaint();
                    revalidate();
                }
            } else {
                if (!IsFieldFilled(fullNameField.getText())) {
                    JOptionPane.showMessageDialog(null, "Заполните поле ФИО");
                } else {
                    String[] fio = fullNameField.getText().split(" ");
                    surnameField = new JTextField(fio[0]);
                    nameField = new JTextField(fio[1]);
                    patronField = new JTextField(fio[2]);
                    panel1.removeAll();
                    makePanel1();
                    remove(button);
                    button = new JButton("Collapse");
                    add(button);
                    button.addActionListener(this);
                    repaint();
                    revalidate();
                }
            }
        }
    }

    public boolean IsFieldFilled(String s) {
        if (s.isEmpty()) {
            return false;
        }
        String[] fio = s.split(" ");
        if (fio.length < 3) {
            return false;
        } else {
            return true;
        }
    }
}
