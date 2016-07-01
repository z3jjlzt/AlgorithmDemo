package com.kkk;

import java.util.Arrays;

/**
 * Created by kkk on 2016/6/30.
 */
public class MyStack {
    private Object[] elements;
    private int curCapacity;
    private int top;
    private int base;
    private int capIncrement;

    public MyStack() {
        base = 0;
        top = 0;
        curCapacity = 16;
        capIncrement = 16;
        elements = new Object[16];
    }

    public void push(Object element) {
        if (top < curCapacity) {
            elements[top++] = element;
        } else {
            curCapacity += capIncrement;
            elements = Arrays.copyOf(elements, curCapacity);
            elements[top++] = element;
        }
    }

    public Object pop() {
        if (top > base) {
            Object tmp = elements[top - 1];
            elements[top--] = null;
            return tmp;
        } else {
            try {
                throw new MyException("stack is empty");
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object peek() {
        if (top > base) {
            Object tmp = elements[top - 1];
            return tmp;
        } else {
            try {
                throw new MyException("stack is empty");
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    class MyException extends Exception {
        public MyException(String mes) {
            super(mes);
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        for (int i = 0; i < 30; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 31; i++) {
            System.out.println(stack.pop());
            System.out.println(stack.peek());
        }
    }
}
