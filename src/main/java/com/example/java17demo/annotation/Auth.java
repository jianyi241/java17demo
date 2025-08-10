package com.example.java17demo.annotation;

import java.lang.annotation.*;

@Inherited  // 在使用此自定义注解时，如果注解在类上面时，子类会自动继承此注解，否则，子类不会继承此注解。这里一定要记住，使用Inherited声明出来的注解，只有在类上使用时才会有效，对方法，属性等其他无效。
@Target({ElementType.TYPE, ElementType.METHOD}) // 表示此注解可以放置的位置。常见的位置有：TYPE=枚举或注解上，FIELD=字段上，METHOD=方法上，PARAMETER=函数形参列表中，CONSTRUCTOR=构造函数上，LOCAL_VARIABLE=局部变量上 等等其他位置。
@Retention(RetentionPolicy.RUNTIME) // 此注解的生命周期。常见的有：SOURCE=源码时期；CLASS=字节码时期（已编译）；RUNTIME=运行时期，通常是用这个的时候要多。
@Documented // 生成注解文档。
public @interface Auth {
    String user() default "";
}