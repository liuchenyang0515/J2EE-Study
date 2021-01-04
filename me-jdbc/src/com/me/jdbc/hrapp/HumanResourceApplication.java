package com.me.jdbc.hrapp;

import com.me.jdbc.hrapp.command.Command;
import com.me.jdbc.hrapp.command.PstmtQueryCommand;
import com.me.jdbc.hrapp.command.QueryCommand;

import java.util.Scanner;

public class HumanResourceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        Scanner in = new Scanner(System.in);
        Integer cmd = in.nextInt();
        switch (cmd) {
            case 1: // 查询部门员工
                Command command = new PstmtQueryCommand();
                command.execute();
                break;
            default:
                break;
        }
    }
}
