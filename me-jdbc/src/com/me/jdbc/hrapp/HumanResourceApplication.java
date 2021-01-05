package com.me.jdbc.hrapp;

import com.me.jdbc.hrapp.command.Command;
import com.me.jdbc.hrapp.command.InsertCommand;
import com.me.jdbc.hrapp.command.PstmtQueryCommand;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        System.out.println("2-办理员工入职");
        System.out.println("请选择功能：");
        Scanner in = new Scanner(System.in);
        Integer cmd = in.nextInt();
        Command command = null;
        switch (cmd) {
            case 1: // 查询部门员工
                command = new PstmtQueryCommand();
                command.execute();
                break;
            case 2:
                command = new InsertCommand();
                command.execute();
                break;
            default:
                break;
        }
    }
}
