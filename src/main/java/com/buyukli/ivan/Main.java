package com.buyukli.ivan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.buyukli.ivan");
        ProductService productService = context.getBean(ProductService.class);
        Scanner console = new Scanner(System.in);
        Cart cart;
        String userAnswer;
        System.out.println("Приветствую тебя пользователь! Доступные тебе действия в нашем магазине:\n" +
                "1) Введи 'add + id продукта' - чтобы добавить продукт в корзину.\n" +
                "2) Введи 'delete + id продукта' - чтобы удалить продукт из корзины.\n" +
                "3) Введи 'show' - чтобы увидеть список продуктов в своей корзине.\n" +
                "4) Введи 'quit' - для выхода из своей корзины.");
        while (true) {
            cart = context.getBean(Cart.class);
            System.out.println("Напишите 'exit' для выхода из программы или введите 'start'," +
                    " затем в следующей строке одну из команд.");
            if (!((console.nextLine()).equals("exit"))) {
                while (true) {
                    userAnswer = console.nextLine();
                    if (userAnswer.startsWith("delete")) {
                        String[] st = userAnswer.split(" ");
                        Long id = Long.parseLong(st[1]);
                        try {
                            cart.deleteProduct(id);
                            System.out.println("Ваш продукт " + productService.getProductById(id).getTitle() + " удален из корзины.");
                            System.out.println("Номер вашей корзины: " + cart);
                        }catch (RuntimeException exception) {
                            System.out.println("В вашей корзине нет этого продукта!");
                        }
                    } else if (userAnswer.startsWith("add")) {
                        String[] st = userAnswer.split(" ");
                        Long id = Long.parseLong(st[1]);
                        try {
                            cart.addProduct(id);
                            System.out.println("Ваш продукт " + productService.getProductById(id).getTitle() + " добавлен в корзину.");
                            System.out.println("Номер вашей корзины: " + cart);
                        }catch (RuntimeException exception){
                            System.out.println("Такого продукта пока нет в нашем магазине!");
                        }

                    } else if (userAnswer.equals("show")) {
                        System.out.println(cart.getAllProductsInCart());
                        System.out.println("Номер вашей корзины: " + cart);
                    } else if (userAnswer.equals("quit")) {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }
}