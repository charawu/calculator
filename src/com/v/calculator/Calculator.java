package com.v.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    private JTextField textField;
    private String input = "";
    private String operand1 = "";
    private String opertor = "";

    public Calculator(){
        this.setTitle("计算器");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,600);

        textField = new JTextField(20);
        textField.setText("?");
        this.add(textField,BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();  //定义buttonPanel
        buttonPanel.setLayout(new GridLayout(5,4,5,5));

        String[] buttonLabels = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","+",
                "c","0",".","-",
                "V","A","T","="
        };

        for (int i = 0; i < buttonLabels.length; i++) { // 这里需要显式地写出判断条件 i < buttonLabels.length
            String label = buttonLabels[i];
            JButton button = new JButton(label);
            button.addActionListener(this);  //注册监听
            buttonPanel.add(button);
        }
        this.add(buttonPanel);  //将buttonPanel添加到主窗口
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {  //使用正则表达式匹配
            input += command;  //实现字符累加
            //input = command;
            textField.setText(input);
        }

        else if (command.matches("[+\\-*/]")) {
            if (!input.isEmpty()) {  //判断input是否为空
                operand1 = input;  //存储第一个输入数字
                opertor = command;  //存储运算符
                input = "";  //清空输入
            }
        }

        else if (command.matches("[A]")) {
            textField.setText("https://github.com/charawu/calculator");
        }else if (command.matches("[V]")) {
            textField.setText("Creator:.v");
        }else if(command.matches("[T]")) {
            textField.setText("Test");
        }

        else if (command.matches("[=]")) {
            if (!operand1.isEmpty() && !opertor.isEmpty() && !input.isEmpty()) {  //判断条件当三者都为非空状态才会计算
                double result = operation(
                        Double.parseDouble(operand1),
                        Double.parseDouble(input),
                        opertor
                );
                textField.setText(String.valueOf(result));  //将计算结果输出到text.Field,同时也就作为下一次的输入数
                opertor = "";  //清空运算符
                operand1 = "";  //清空存储的数
            }
        }

        else if (command.matches("[c]")) {
            input = "";
            opertor = "";
            operand1 = "";
            textField.setText("?");
        }
    }

    private double operation(double num1,double num2,String opertor) {
        switch (opertor){
            case "+":return num1 + num2;
            case "-":return num1 - num2;
            case "*":return num1 * num2;
            //case "/":return num1 / num2;
            case "/":
                if (num1 != 0) {  //通过判断除数是否为0
                    return num1 / num2;
                }else {
                    JOptionPane.showMessageDialog(this,"除数不能为0");
                    return 0;
                }
            default:return 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}